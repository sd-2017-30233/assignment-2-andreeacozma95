package view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddEmployeeFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	JButton btnAddNewEmployee;

	public AddEmployeeFrame() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 422, 258);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAddEmployee = new JLabel("Add Employee");
		lblAddEmployee.setBounds(118, 11, 92, 29);
		panel.add(lblAddEmployee);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(48, 51, 73, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(48, 75, 46, 14);
		panel.add(lblPassword);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(48, 100, 46, 14);
		panel.add(lblEmail);
		
		JLabel lblRole = new JLabel("role");
		lblRole.setBounds(48, 125, 46, 14);
		panel.add(lblRole);
		
		textField = new JTextField();
		textField.setBounds(112, 48, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(112, 72, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(112, 97, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(112, 122, 86, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		btnAddNewEmployee = new JButton("Add new Employee");
		btnAddNewEmployee.setBounds(219, 121, 163, 23);
		panel.add(btnAddNewEmployee);
	}
	
	public void setFrameVisible()
	{
		frame.setVisible(true);
	}
	
	public void setFrameInvisible()
	{
		frame.setVisible(false);
	}
	
	public void addActionListenerAddButton(ActionListener a)
	{
		btnAddNewEmployee.addActionListener(a);
	}
	public String getTextField() {
		return textField.getText();
	}

	public void setTextField(String textField) {
		this.textField.setText(textField);
	}
	public String getTextField_1() {
		return textField_1.getText();
	}

	public void setTextField_1(String textField) {
		this.textField_1.setText(textField);
	}
	public String getTextField_2() {
		return textField_2.getText();
	}

	public void setTextField_2(String textField) {
		this.textField_2.setText(textField);
	}
	public String getTextField_3() {
		return textField_3.getText();
	}

	public void setTextField_3(String textField) {
		this.textField_3.setText(textField);
	}
	

}
