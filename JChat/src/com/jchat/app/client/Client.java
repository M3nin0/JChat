package com.jchat.app.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {

	public static void main(String[] args) {
		try {
			Socket client = new Socket("127.0.0.1", 1234);

			client.getOutputStream();

			// Thread para a leitura das mensagens do servidor
			new Thread() {
				@Override
				public void run() {
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
						while (true) {
							String msg = reader.readLine();
							System.out.println("O servidor disse: " + msg);
						}

					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
			}.start();

			PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
			BufferedReader readTerminal = new BufferedReader(new InputStreamReader(System.in));

			while (true) {
				String msgTerminal = readTerminal.readLine();
				writer.println(msgTerminal);
			}

		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.getCause());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
