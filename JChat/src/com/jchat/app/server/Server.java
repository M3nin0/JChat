package com.jchat.app.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class Server extends Thread {

	private static Vector<PrintStream> clientes;
	private Socket socket;
	private String name;

	public Server(Socket socket) {
		this.socket = socket;
	}

	// A exceção vai no método pois caso haja algum problema, está sendo tratada
	// dentro mo método run()
	public void broadCast(PrintStream output, String verbo, String linha) throws IOException {
		Enumeration e = clientes.elements();

		while (e.hasMoreElements()) {
			// Pega o fluxo de saída de todos os elementos
			PrintStream chat = (PrintStream) e.nextElement();

			if (chat != output) {
				chat.println(name + verbo + linha);
			}
		}
	}

	@Override
	public void run() {

		try {
			// Criando os responsáveis pelo fluxo de comunicação
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream output = new PrintStream(socket.getOutputStream());

			name = input.readLine();

			if (name == null) {
				return;
			}

			clientes.add(output);

			String linha = input.readLine();

			while (linha != null && !(linha.trim().equals(""))) {
				broadCast(output, " diz: ", linha);
				linha = input.readLine();
			}

			broadCast(output, " saiu: ", " do chat!");
			clientes.remove(output);
			socket.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) {

		// Lista dos clientes conectados
		clientes = new Vector();

		try {
			// Cria um servidor que escuta a porta 2000
			ServerSocket server = new ServerSocket(2000);

			// Loop principal
			while (true) {

				System.out.println("Esperando conexões");
				Socket socket = server.accept();
				System.out.println("Nova conexão");

				// Cria uma thread para a nova conexão
				Thread t = new Server(socket);
				t.start();

			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
