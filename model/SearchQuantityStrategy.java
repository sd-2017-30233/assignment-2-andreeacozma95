package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SearchQuantityStrategy extends SearchStrategy {

	
	@Override
	public List<Book> filterBooks(String value) {
		
		List<Book> books = new ArrayList<Book>();
		List<Object> parameters = new ArrayList<Object>();
		Book book = null;
		
		try {
			FileInputStream file = new FileInputStream(new File("Book.xml"));
				
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder =  builderFactory.newDocumentBuilder();
			
			Document xmlDocument = builder.parse(file);

			XPath xpath =  XPathFactory.newInstance().newXPath();		

			XPathExpression expr;
			Object result = null;
		
			try {
				expr = xpath.compile("//" + "book[quantity='"+value+"']/*");
				result = expr.evaluate(xmlDocument, XPathConstants.NODESET);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			
			NodeList nodes = (NodeList) result;
			    
			boolean term = false;
			
		    for (int i = 0; i < nodes.getLength(); i++) {
		        Element el = (Element) nodes.item(i);

		        if(el.getNodeName().equals("genre"))
		        	term = true;
		        if ( el.getFirstChild().getNodeType() == Node.TEXT_NODE)
		        {	
		        	if (!el.getNodeName().equals("sell"))		        		
		        		parameters.add(el.getFirstChild().getNodeValue());
		        }

		        if(term)
		        {
		        	book = bookMapper(parameters);
			        books.add(book);
			        parameters.clear();
			        term = false;
		        }
		    }
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}		
		
		return books;
	}
}
