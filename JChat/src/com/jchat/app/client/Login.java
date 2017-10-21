package com.jchat.app.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 442, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txt_name = new JTextPane();
		txt_name.setFont(new Font("DejaVu Serif Condensed", Font.PLAIN, 14));
		txt_name.setText("Seu nome aqui");
		txt_name.setBounds(151, 64, 139, 25);
		contentPane.add(txt_name);
		
		JLabel lblName = new JLabel("Insira seu nome para entrar");
		lblName.setFont(new Font("DejaVu Serif Condensed", Font.BOLD, 14));
		lblName.setBounds(120, 27, 200, 25);
		contentPane.add(lblName);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnEntrar.setBounds(159, 173, 114, 25);
		contentPane.add(btnEntrar);
	}
}
