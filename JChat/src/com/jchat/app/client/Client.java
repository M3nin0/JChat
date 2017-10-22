package com.jchat.app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Thread {

	private static boolean connection = false;
	private Socket socket;

	public Client(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// Irá ficar esperando mensagens do servidor
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while (true) {
				String linha = input.readLine();

				if (linha == null) {
					System.out.println("Fim da conexão");
					break;
				}

				System.out.println(linha);
				System.out.println("> ");

			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		connection = true;
	}

	public static void main(String[] args) {
		try {
			// Para se conectar ao servidor, basta criar uma instância de socket
			Socket socket = new Socket("127.0.0.1", 2000);

			// Declarando os objetos de fluxo de comunicação
			BufferedReader keyboad = new BufferedReader(new InputStreamReader(System.in));
			PrintStream output = new PrintStream(socket.getOutputStream());

			System.out.println("Insira seu nome: ");
			String name = keyboad.readLine();
			output.println(name);

			// Criando a thread que irá escutar as mensagens vindas do servidor
			Thread t = new Client(socket);
			t.start();

			// Loop principal
			// Responsável em receber a mensagem do teclado e enviar ao servidor
			while (true) {
				System.out.print("> ");
				String linha = keyboad.readLine();

				// if para verificar se a conexão foi fechada
				if (connection) {
					break;
				}
				output.println(linha);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
