package view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SellView {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	JButton btnFinish ;

	public SellView() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSellBook = new JLabel("Sell Book");
		lblSellBook.setBounds(27, 11, 61, 25);
		frame.getContentPane().add(lblSellBook);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(42, 64, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setBounds(42, 89, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Quantity");
		lblNewLabel_2.setBounds(42, 114, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(112, 61, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(112, 86, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(112, 111, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		btnFinish = new JButton("Finish");
		btnFinish.setBounds(109, 148, 89, 23);
		frame.getContentPane().add(btnFinish);
	}
	
	public String getTextField() {
		return textField.getText();
	}

	public void setTextField(String textField) {
		this.textField.setText(textField);
	}
	
	public String getTextField1() {
		return textField_1.getText();
	}

	public void setTextField1(String textField) {
		this.textField_1.setText(textField);
	}
	
	public String getTextField2() {
		return textField_2.getText();
	}

	public void setTextField2(String textField) {
		this.textField_2.setText(textField);
	}
	
	public void setFrameVisible()
	{
		frame.setVisible(true);
	}
	
	public void setFrameInvisible()
	{
		frame.setVisible(false);
	}
	
	public void addActionListenerButtonFinish(ActionListener a)
	{
		btnFinish.addActionListener(a);
	}

}
