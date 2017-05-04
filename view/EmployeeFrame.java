package view;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class EmployeeFrame {

	private JFrame frame;
	private JTable table;
	private JPanel panel;
	private JScrollPane scroll;
	JComboBox comboBox;
	private JTextField textFieldSearch;
	private JButton btnSell;
	
	public EmployeeFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		comboBox = new JComboBox();
		comboBox.addItem("title");
		comboBox.addItem("author");
		comboBox.addItem("genre");
		
		textFieldSearch = new JTextField();
		textFieldSearch.setSize(new Dimension(150, 30));
		textFieldSearch.setPreferredSize(new Dimension(150, 30));
		frame.getContentPane().add(panel);
		
		btnSell = new JButton("Sell");
		
		panel.add(btnSell);
		
		
	}
	
	public void setFrameVisible()
	{
		frame.setVisible(true);
	}
	
	public void setFrameInvisible()
	{
		frame.setVisible(false);
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	public void setTable(String [][] date,String coloane[])
	{		
		DefaultTableModel model = new DefaultTableModel(date,coloane);		
		table =new JTable(model);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

		for(int j=0; j < coloane.length; j++){
			table.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder title = BorderFactory.createTitledBorder(
                blackline, "Book");
		title.setTitleJustification(TitledBorder.CENTER);

		panel.setBorder(title);
		scroll =new JScrollPane(table);
		panel.add(scroll);
		panel.add(new JLabel("SEARCH BY"));
		panel.add( textFieldSearch);
		panel.add(comboBox);
	}


	public void addListenerTable(ListSelectionListener a){
		table.getSelectionModel().addListSelectionListener(a);
	}
	
	public int getSelectedRow(){
		return table.getSelectedRow();
	}
	
	public void refreshTable(String [][] date,String coloane[]){
		
		DefaultTableModel model = new DefaultTableModel(date,coloane);
	    table.setModel(model);
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

		for(int j=0; j < coloane.length; j++){
            table.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }		
	}
	
	public int getSelectedColumn(){
		return table.getSelectedColumn();
	}
	
	public Object getTableData(){
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		if (table.getSelectedRow()>=0)
			return tableModel.getDataVector().elementAt(table.getSelectedRow());
		else return null;
	}

	public String getTextFieldSearch() {
		return textFieldSearch.getText();
	}

	public void setTextFieldSearch(String textFieldSearch) {
		this.textFieldSearch.setText(textFieldSearch);
	}
	
	public Object getSelectedItem()
	{
		return comboBox.getSelectedItem();
	}
	
	public void addActionListenerSelectedItem(ActionListener a)
	{
		comboBox.addActionListener(a);
	}
	
	public void addActionListenerSellButton(ActionListener a)
	{
		btnSell.addActionListener(a);
	}


}
