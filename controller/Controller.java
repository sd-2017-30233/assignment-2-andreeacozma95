package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.*;
import view.*;

public class Controller {


	Login window;
	//AdministratorFrame adminFrame;
	EmployeeFrame employeeFrame;
	AdminFrame adminFrame;
	SellView sellFrame;
	AddBookFrame addBookFrame;
	AddEmployeeFrame addEmployeeFrame;
	String selectedData[];
	String nameLogIn;
	String passwordLogIn;
	
	Book bookForSale;
	
	public Controller()
	{
		try {
			window = new Login();
			window.setVisible();
		} catch (Exception e) {
			e.printStackTrace();
		}
			window.addActionListenerButtonLogIn(new ActionListenerButtonLogIn());
	}
	protected class ActionListenerButtonLogIn implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
				
				nameLogIn = window.getTextField();
				passwordLogIn = window.getPasswordField();
				
				XMLReaderUser reader = new XMLReaderUser();
				User user = reader.getUserByUsername(nameLogIn);
				
				if(user!=null && user.getPassword().equals(passwordLogIn))
				{
					employeeFrame = new EmployeeFrame();
					XMLReaderBook readerBooks = new XMLReaderBook(new SearchAllBooksStrategy());
					List<Book> books = readerBooks.executeStrategy("");
													
					employeeFrame.setTable(findTabelData(books),findTabelColumns());
					
					employeeFrame.addActionListenerSellButton(new ActionListenerSellButton());
					employeeFrame.addActionListenerSelectedItem(new ActionListenerSelectedItem());
					employeeFrame.setFrameVisible();
					window.setInvisible();
				}
				
				else
				{
					user = reader.getAdmin();
					
					if(user.getPassword().equals(passwordLogIn))
					{
						adminFrame = new AdminFrame();
						
						XMLReaderBook readerBooks = new XMLReaderBook(new SearchAllBooksStrategy());
						List<Book> books = readerBooks.executeStrategy("");
														
						adminFrame.setTable(findTabelData(books),findTabelColumns());
						
						XMLReaderUser readerUsers = new XMLReaderUser();
						List<User> users = readerUsers.getUsers();
						adminFrame.setTableEmployees(findTabelDataEmployee(users), findTabelColumnsEmployee());
						
						adminFrame.addActionListenerAddButton(new ActionListenerAddButton());
						adminFrame.addActionListenerDeleteButton(new ActionListenerDeleteButton());
						adminFrame.addActionListenerAddButtonEmployee(new ActionListenerAddButtonEmployee());
						adminFrame.addActionListenerDeleteButtonEmployee(new ActionListenerDeleteButtonEmployee());
						adminFrame.addActionListenerButtonPDFreport(new ActionListenerButtonPDFreport());
						adminFrame.addActionListenerButtonCSVreport(new ActionListenerButtonCSVreport());
						adminFrame.setFrameVisible();
						window.setInvisible();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "User/password incorrect!");
						window.resetFields();
					}
				}					
		}		
    }
	
	protected class ActionListenerButtonPDFreport implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			FactoryAccess<PDFFactory> factoryAccess = new FactoryAccess<>(PDFFactory.class);
			Factory factory = factoryAccess.getFactory();
			Format format = factory.getFormat();
			
			format.createReport();
			JOptionPane.showMessageDialog(null, "Fisier generat!");
		}		
	}
	
	protected class ActionListenerButtonCSVreport implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			FactoryAccess<CSVFactory> factoryAccess = new FactoryAccess<>(CSVFactory.class);
			Factory factory = factoryAccess.getFactory();
			Format format = factory.getFormat();
			
			format.createReport();
			JOptionPane.showMessageDialog(null, "Fisier generat!");
		}		
	}
	
	protected class ActionListenerSelectedItem implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object objSelected = employeeFrame.getSelectedItem();
			XMLReaderBook reader ;
			List<Book> books = null;
			if(objSelected.toString().equals("title"))
			{
				reader = new XMLReaderBook(new SearchTitleStrategy());
				books = reader.executeStrategy(employeeFrame.getTextFieldSearch());
			}
			if(objSelected.toString().equals("author"))
			{
				reader = new XMLReaderBook(new SearchAuthorStrategy());
				books = reader.executeStrategy(employeeFrame.getTextFieldSearch());
			}
			if(objSelected.toString().equals("genre"))
			{
				reader = new XMLReaderBook(new SearchGenreStrategy());
				books = reader.executeStrategy(employeeFrame.getTextFieldSearch());
			}
			
			if (employeeFrame.getTextFieldSearch().equals(""))
			{
				reader = new XMLReaderBook(new SearchAllBooksStrategy());
				books = reader.executeStrategy("");
			}
			employeeFrame.refreshTable(findTabelData(books),findTabelColumns());
			employeeFrame.setTextFieldSearch("");
		}		
	}
	
	
	protected class ActionListenerAddButton implements ActionListener
	{
		@Override
			public void actionPerformed(ActionEvent e) {	
				
				addBookFrame = new AddBookFrame();
				addBookFrame.addActionListenerAddButton(new ActionListenerAddBookButton());										
				addBookFrame.setFrameVisible();
		}				
	}
	
	protected class ActionListenerAddButtonEmployee implements ActionListener
	{
		@Override
			public void actionPerformed(ActionEvent e) {	
				
				addEmployeeFrame = new AddEmployeeFrame();
				addEmployeeFrame.addActionListenerAddButton(new ActionListenerAddEmployeeNewButton());										
				addEmployeeFrame.setFrameVisible();
		}				
	}
	
	protected class ActionListenerAddEmployeeNewButton implements ActionListener
	{
		@Override
			public void actionPerformed(ActionEvent e) {	
				
			XMLWritterUser writter = new XMLWritterUser();
			writter.createUser(new User(addEmployeeFrame.getTextField(),addEmployeeFrame.getTextField_1(),
					addEmployeeFrame.getTextField_2(),addEmployeeFrame.getTextField_3()));
			
			JOptionPane.showMessageDialog(null, "Operatia s-a efectuat!");
			addEmployeeFrame.setFrameInvisible();
			XMLReaderUser reader = new XMLReaderUser();
			List<User> users = reader.getUsers();
			adminFrame.refreshTableEmployee(findTabelDataEmployee(users),findTabelColumnsEmployee());
			addEmployeeFrame.setTextField("");
			addEmployeeFrame.setTextField_1("");
			addEmployeeFrame.setTextField_2("");
			addEmployeeFrame.setTextField_3("");
		}				
	}
	
	protected class ActionListenerAddBookButton implements ActionListener
	{
		@Override
			public void actionPerformed(ActionEvent e) {	
				
			if(InputValidation.validateNumber(addBookFrame.getTextField())&&
					InputValidation.validateNumber(addBookFrame.getTextField_4()))
				{			
					XMLWritterBook writter = new XMLWritterBook();
					writter.createBook(new Book(Integer.parseInt(addBookFrame.getTextField()),addBookFrame.getTextField_1(),
							addBookFrame.getTextField_2(),Integer.parseInt(addBookFrame.getTextField_3()),
							Float.parseFloat(addBookFrame.getTextField_4()),addBookFrame.getTextField_5()));
					
					JOptionPane.showMessageDialog(null, "Operatia s-a efectuat!");
					addBookFrame.setFrameInvisible();
					XMLReaderBook reader = new XMLReaderBook(new SearchAllBooksStrategy());
					List<Book> books = reader.executeStrategy("");
					adminFrame.refreshTable(findTabelData(books),findTabelColumns());
					addBookFrame.setTextField("");
					addBookFrame.setTextField_1("");
					addBookFrame.setTextField_2("");
					addBookFrame.setTextField_3("");
					addBookFrame.setTextField_4("");
					addBookFrame.setTextField_5("");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Date incorecte!");
					addBookFrame.setTextField("");
					addBookFrame.setTextField_1("");
					addBookFrame.setTextField_2("");
					addBookFrame.setTextField_3("");
					addBookFrame.setTextField_4("");
					addBookFrame.setTextField_5("");
				}				
			}
	}
	
	protected class ActionListenerDeleteButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {	
			Object o = adminFrame.getTableData();

			String s = o.toString();
			Matcher matcher = Pattern.compile("\\d+").matcher(s);
			matcher.find();
			int i = Integer.valueOf(matcher.group());
			System.out.println(i);
			XMLReaderBook readerBooks = new XMLReaderBook(new SearchIDBookStrategy());
			List<Book> books = readerBooks.executeStrategy(String.valueOf(i));
			Book bookForDelete = books.get(0);	
			XMLWritterBook writter = new XMLWritterBook();
			writter.deleteBook(bookForDelete);
			
			readerBooks = new XMLReaderBook(new SearchAllBooksStrategy());
			books = readerBooks.executeStrategy("");
			adminFrame.refreshTable(findTabelData(books),findTabelColumns());
		}				
	}
	
	protected class ActionListenerDeleteButtonEmployee implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {	
			Object o = adminFrame.getTableDataEmployee();

			String s = o.toString();
			String substring = s.substring(1, s.length()-1);
			String[] tokens = substring.split(",");
			
			String username = tokens[0];
			System.out.println(username);
			
			XMLReaderUser readerUsers = new XMLReaderUser();
			User user = readerUsers.getUserByUsername(username);
	
			XMLWritterUser writter = new XMLWritterUser();
			writter.deleteUser(user);
			
			List<User> users = readerUsers.getUsers();
			adminFrame.refreshTableEmployee(findTabelDataEmployee(users),findTabelColumnsEmployee());
		}				
	}
	
	protected class ActionListenerSellButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {	
			Object o = employeeFrame.getTableData();
			if (o!=null)
			{
				String s = o.toString();
				Matcher matcher = Pattern.compile("\\d+").matcher(s);
				matcher.find();
				int i = Integer.valueOf(matcher.group());
				System.out.println(i);
				XMLReaderBook readerBooks = new XMLReaderBook(new SearchIDBookStrategy());
				List<Book> books = readerBooks.executeStrategy(String.valueOf(i));
				bookForSale = books.get(0);
				
				sellFrame = new SellView();
				sellFrame.addActionListenerButtonFinish(new ActionListenerButtonFinish());										
				sellFrame.setFrameVisible();
			}
			else
				JOptionPane.showMessageDialog(null, "Selectati mai intai o carte!");
			
		}	
			
	}
	
	protected class ActionListenerButtonFinish implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {	
			if(InputValidation.validateDate(sellFrame.getTextField1())&&
					InputValidation.validateNumber(sellFrame.getTextField())&&
					InputValidation.validateNumber(sellFrame.getTextField2()))
			{
				if (bookForSale.getQuantity()>=Integer.parseInt(sellFrame.getTextField2()))
				{
					Sell sell = null;
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
					try {
						sell = new Sell(Integer.parseInt(sellFrame.getTextField()), dateFormat.parse(sellFrame.getTextField1()), Integer.parseInt(sellFrame.getTextField2()));
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					
					XMLWritterBook writter = new XMLWritterBook();
					writter.addSell(bookForSale.getIdBook(), sell);
					JOptionPane.showMessageDialog(null, "Operatia s-a efectuat!");
				}
				else
					JOptionPane.showMessageDialog(null, "Nu exista cantitatea specificata!");
				
				sellFrame.setFrameInvisible();
				XMLReaderBook reader = new XMLReaderBook(new SearchIDBookStrategy());
				List<Book> books = reader.executeStrategy("");
				employeeFrame.refreshTable(findTabelData(books),findTabelColumns());
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Formatul pentru data este yyyy-mm-dd, iar pentru ID si cantitate trebuie sa fie numar!");
			}
			sellFrame.setTextField("");
			sellFrame.setTextField1("");
			sellFrame.setTextField2("");
		}	
	}
	
	public String[] findTabelColumns()
	{
		Field []f = Book.class.getDeclaredFields();
		String [] coloane=new String[f.length-1];
		
		int i=0;
		for ( Field field : f )
		{
			if (!field.getName().equals("sells"))
			{
				coloane[i]=field.getName();
				i++;
			}				
		}
		return coloane;
	}	
	public String[][] findTabelData(List<Book> books)
	{
		Field []f = Book.class.getDeclaredFields();
		String [] coloane=new String[f.length-1];
		
		int i=0;
		for ( Field field : f )
		{
			if (!field.getName().equals("sells"))
			{
				coloane[i]=field.getName();
				i++;
			}				
		}		
		
		String [][] data=new String[books.size()][f.length-1];
		i=0;
		for(int k=0;k<books.size();k++)
		{
			i=0;
			for ( Field field : f )	
			{
				if (!field.getName().equals("sells"))
				{
					try {
						field.setAccessible(true);
						data[k][i]=field.get(books.get(k)).toString();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
					i++;
				}
			}
		}
		return data;
	}
	public String[] findTabelColumnsEmployee()
	{
		Field []f = User.class.getDeclaredFields();
		String [] coloane=new String[f.length-1];
		
		int i=0;
		for ( Field field : f )
		{
			if (!field.getName().equals("role"))
			{
				coloane[i]=field.getName();
				i++;
			}				
		}
		return coloane;
	}	
	public String[][] findTabelDataEmployee(List<User> users)
	{
		Field []f = User.class.getDeclaredFields();
		String [] coloane=new String[f.length-1];
		
		int i=0;
		for ( Field field : f )
		{
			if (!field.getName().equals("role"))
			{
				coloane[i]=field.getName();
				i++;
			}
					
		}		
		
		String [][] data=new String[users.size()][f.length-1];
		i=0;
		for(int k=0;k<users.size();k++)
		{
			i=0;
			for ( Field field : f )	
			{
				if (!field.getName().equals("role"))
				{
					try {
						field.setAccessible(true);
						data[k][i]=field.get(users.get(k)).toString();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
					i++;
				}
			}
		}
		return data;
	}
	
	public static void main(String[] args) {
		Controller controller = new Controller();
	}
}
