package com.jchat.app.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

// Extende a classe de thread
public class SocketManager extends Thread {

	private Socket client;
	private String nomeClient;

	public SocketManager(Socket client) {
		this.client = client;
		start();
	}

	@Override
	public void run() {
		try {
			// Recebe informações
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			// Envia informações - Parametro boolean, para dizer se é autoflush ou não,
			// neste caso sim
			PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
			
			// Envia informações
			writer.println("Insira seu nome");
			String msg = reader.readLine();
			this.nomeClient = msg;

			while (true) {
				msg = reader.readLine();
				writer.print("Você disse: " + msg);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
