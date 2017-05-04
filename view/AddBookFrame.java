package view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddBookFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	JButton btnAddNewBook;
	
	public AddBookFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 442, 247);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAddBook = new JLabel("Add Book");
		lblAddBook.setBounds(198, 5, 45, 14);
		panel.add(lblAddBook);
		
		JLabel lblIdBook = new JLabel("ID Book");
		lblIdBook.setBounds(25, 40, 46, 14);
		panel.add(lblIdBook);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(25, 64, 46, 14);
		panel.add(lblTitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(25, 89, 46, 14);
		panel.add(lblAuthor);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(25, 114, 46, 14);
		panel.add(lblQuantity);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(25, 138, 46, 14);
		panel.add(lblPrice);
		
		JLabel lblGenre = new JLabel("Genre");
		lblGenre.setBounds(25, 163, 46, 14);
		panel.add(lblGenre);
		
		textField = new JTextField();
		textField.setBounds(93, 37, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(93, 61, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(93, 86, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(93, 111, 86, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(93, 135, 86, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(93, 160, 86, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		btnAddNewBook = new JButton("Add new book");
		btnAddNewBook.setBounds(220, 159, 89, 23);
		panel.add(btnAddNewBook);
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
		btnAddNewBook.addActionListener(a);
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
	
	public String getTextField_4() {
		return textField_4.getText();
	}

	public void setTextField_4(String textField) {
		this.textField_4.setText(textField);
	}
	
	public String getTextField_5() {
		return textField_5.getText();
	}

	public void setTextField_5(String textField) {
		this.textField_5.setText(textField);
	}
}
