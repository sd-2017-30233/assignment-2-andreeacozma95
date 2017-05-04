package model;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public abstract class SearchStrategy {

	public abstract List<Book> filterBooks(String value);
	
	Book bookMapper(List<Object> parameters)
	{
		return new Book(Integer.parseInt((String) parameters.get(0)),
				(String)parameters.get(1),
				(String)parameters.get(2),
				Integer.parseInt((String)parameters.get(3)),
				Float.parseFloat((String) parameters.get(4)),
				(String)parameters.get(5),
				null);
	}
	
	
}
