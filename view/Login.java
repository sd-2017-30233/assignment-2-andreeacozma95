package view;


import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;


public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogIn;

	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log in");
		lblLogIn.setBounds(191, 49, 44, 39);
		frame.getContentPane().add(lblLogIn);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(62, 98, 71, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(62, 121, 71, 14);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(143, 95, 92, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(143, 118, 92, 20);
		frame.getContentPane().add(passwordField);
		
		btnLogIn = new JButton("Log in");
		
			
		btnLogIn.setBounds(143, 149, 89, 23);
		frame.getContentPane().add(btnLogIn);
		
		JLabel lblBookStore = new JLabel("BOOK STORE");
		lblBookStore.setBounds(23, 11, 158, 39);
		Font font = new Font("Verdana", Font.BOLD, 20);
		lblBookStore.setFont(font);
		lblBookStore.setForeground(Color.magenta);
		frame.getContentPane().add(lblBookStore);
	}
	
	public void setInvisible()
	{
		frame.setVisible(false);
	}
	public void setVisible()
	{
		frame.setVisible(true);
	}
	
	public void addActionListenerButtonLogIn(ActionListener a)
	{
		btnLogIn.addActionListener(a);
	}

	public String getTextField() {
		return textField.getText();
	}

	public void setTextField(String textField) {
		this.textField.setText(textField);
	}

	public String getPasswordField() {
		return passwordField.getText();
	}

	public void setPasswordField(String passwordField) {
		this.passwordField.setText(passwordField);
	}
	public void resetFields()
	{
		textField.setText("");
		passwordField.setText("");
	}
}
