package view;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class AdminFrame {

	private JFrame frame;
	private JTable table;
	JPanel panel;
	JPanel panel_1;
	JPanel panel_2 ;
	JScrollPane scroll;
	JScrollPane scroll2;
	JButton btnAddBook;
	JButton btnDeleteBook;
	JButton btnAddEmployee;
	JButton btnDeleteEmployee;
	JButton btnGeneratePdfReport;
	JButton btnGenerateCsvReport;
	private JTable table_1;
	
	public AdminFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 742, 400);
		frame.getContentPane().add(tabbedPane);
		
		panel = new JPanel();
		tabbedPane.addTab("Books", null, panel, null);
		panel.setLayout(new FlowLayout());
		
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Employees", null, panel_1, null);
		panel_1.setLayout(new FlowLayout());
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Generate Report - Books out of stock", null, panel_2, null);
		panel_2.setLayout(new FlowLayout());
		
		btnAddBook = new JButton("Add book");
		
		btnDeleteBook = new JButton("Delete book");
		
		btnAddEmployee = new JButton("Add Employee");
		
		btnDeleteEmployee = new JButton("Delete Employee");
		
		btnGenerateCsvReport=new JButton("Generate CSV");
		
		btnGeneratePdfReport=new JButton("Generate PDF");
		
		panel_2.add(btnGenerateCsvReport);
		panel_2.add(btnGeneratePdfReport);
	
	}
	
	public void addActionListenerButtonPDFreport(ActionListener a)
	{
		btnGeneratePdfReport.addActionListener(a);
	}
	
	public void addActionListenerButtonCSVreport(ActionListener a)
	{
		btnGenerateCsvReport.addActionListener(a);
	}
	
	public void addActionListenerAddButton(ActionListener a)
	{
		btnAddBook.addActionListener(a);
	}
	
	public void addActionListenerDeleteButton(ActionListener a)
	{
		btnDeleteBook.addActionListener(a);
	}
	
	public void addActionListenerAddButtonEmployee(ActionListener a)
	{
		btnAddEmployee.addActionListener(a);
	}
	
	public void addActionListenerDeleteButtonEmployee(ActionListener a)
	{
		btnDeleteEmployee.addActionListener(a);
	}
	
	public void setFrameVisible()
	{
		frame.setVisible(true);
	}
	
	public void setFrameInvisible()
	{
		frame.setVisible(false);
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
		panel.add(btnAddBook);
		panel.add(btnDeleteBook);
	
	}
	
	public void setTableEmployees(String [][] date,String coloane[])
	{		
		DefaultTableModel model = new DefaultTableModel(date,coloane);		
		table_1 =new JTable(model);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

		for(int j=0; j < coloane.length; j++){
			table_1.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }
		
		Border blackline = BorderFactory.createLineBorder(Color.black);
		TitledBorder title = BorderFactory.createTitledBorder(
                blackline, "Employee");
		title.setTitleJustification(TitledBorder.CENTER);

		panel_1.setBorder(title);
		scroll2 =new JScrollPane(table_1);
		panel_1.add(scroll2);
		panel_1.add(btnAddEmployee);
		panel_1.add(btnDeleteEmployee);
	
	}


	public void addListenerTable(ListSelectionListener a){
		table.getSelectionModel().addListSelectionListener(a);
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
	
public void refreshTableEmployee(String [][] date,String coloane[]){
		
		DefaultTableModel model = new DefaultTableModel(date,coloane);
	    table_1.setModel(model);
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );

		for(int j=0; j < coloane.length; j++){
            table_1.getColumnModel().getColumn(j).setCellRenderer(centerRenderer);
        }		
	}
	
	public Object getTableData(){
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		return tableModel.getDataVector().elementAt(table.getSelectedRow());
	}
	
	public Object getTableDataEmployee(){
		DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
		return tableModel.getDataVector().elementAt(table_1.getSelectedRow());
	}
}
