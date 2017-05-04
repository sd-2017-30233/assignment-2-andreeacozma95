package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVFormat implements Format{

	 private static final char DEFAULT_SEPARATOR = ',';
	 private static final char NEW_LINE = '\n';

	@Override
	public void createReport() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("BooksOutOfStockReport.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	        StringBuilder sb = new StringBuilder();
	        sb.append("Title");
	        sb.append(DEFAULT_SEPARATOR);
	        sb.append("Author");
	        sb.append(DEFAULT_SEPARATOR);
	        sb.append("Price");
	        sb.append(DEFAULT_SEPARATOR);
	        sb.append("Genre");
	        sb.append(NEW_LINE);
   
	        XMLReaderBook reader = new XMLReaderBook(new SearchQuantityStrategy());
			List<Book> books = reader.executeStrategy("0");

	        for (Book book : books) {

	            List<String> list = new ArrayList<>();
	            sb.append(book.getTitle());
	            sb.append(DEFAULT_SEPARATOR);
	            sb.append(book.getAuthor());
	            sb.append(DEFAULT_SEPARATOR);
	            sb.append(String.valueOf(book.getPrice()));
	            sb.append(DEFAULT_SEPARATOR);
	            sb.append(book.getGenre());
	            sb.append(NEW_LINE);
	        }
	        
	        pw.write(sb.toString());
	        pw.close();
	    }
	
	public static void main(String args[])
	{
		FactoryAccess<CSVFactory> factoryAccess = new FactoryAccess<>(CSVFactory.class);
		Factory factory = factoryAccess.getFactory();
		Format format = factory.getFormat();
		
		format.createReport();
		
	}
}
