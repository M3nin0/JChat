package com.jchat.app.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.jchat.app.manager.SocketManager;

public class Server{

	@SuppressWarnings("null")
	public static void main(String[] args) {

		int port = 1234;
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(port);
			JOptionPane.showMessageDialog(null, "Servidor inicializado na porta " + port);

			while (true) {
				Socket client = serverSocket.accept();
				new SocketManager(client);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

			try {
				serverSocket.close();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getCause());
			}

		}
	}
}
