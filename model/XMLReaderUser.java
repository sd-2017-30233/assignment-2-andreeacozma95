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

public class XMLReaderUser {

	User userMapper(List<Object> parameters,String role)
	{
		return new User((String)parameters.get(0),(String)parameters.get(1),
				(String)parameters.get(2),role);
	}
	
	public User getUserByUsername(String username)
	{
		User user = null;
		try {
			FileInputStream file = new FileInputStream(new File("User.xml"));
				
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder =  builderFactory.newDocumentBuilder();
			
			Document xmlDocument = builder.parse(file);

			XPath xPath =  XPathFactory.newInstance().newXPath();

			List<Object> parameters = new ArrayList<Object>();
			
			String expression = "/users/user[username='"+username+"' and @role='employee']";
			
			Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
			if(null != node) {
				NodeList nodeList = node.getChildNodes();
				for (int i = 0;null!=nodeList && i < nodeList.getLength(); i++) 
				{
					Node nod = nodeList.item(i);
					if(nod.getNodeType() == Node.ELEMENT_NODE)
						parameters.add( nod.getFirstChild().getNodeValue()); 
				}
				
				user = userMapper(parameters,"employee");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}		
		
		return user;
	}
	
	public User getAdmin()
	{
		User user = null;
		
		try {
			FileInputStream file = new FileInputStream(new File("User.xml"));
				
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder =  builderFactory.newDocumentBuilder();
			
			Document xmlDocument = builder.parse(file);

			XPath xPath =  XPathFactory.newInstance().newXPath();

			List<Object> parameters = new ArrayList<Object>();
			
			String expression = "/users/user[@role='admin']";

			Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
			if(null != node) {
				NodeList nodeList = node.getChildNodes();
				for (int i = 0;null!=nodeList && i < nodeList.getLength(); i++) 
				{
					Node nod = nodeList.item(i);
					if(nod.getNodeType() == Node.ELEMENT_NODE)
						parameters.add( nod.getFirstChild().getNodeValue()); 
				}
				
				user = userMapper(parameters,"admin");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}		
	
		return user;
	}
	
	public List<User> getUsers()
	{
		List<User> books = new ArrayList<User>();
		List<Object> parameters = new ArrayList<Object>();
		
		User user = null;
		try {
			FileInputStream file = new FileInputStream(new File("User.xml"));
				
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder =  builderFactory.newDocumentBuilder();
			
			Document xmlDocument = builder.parse(file);

			XPath xpath =  XPathFactory.newInstance().newXPath();

			XPathExpression expr;
			Object result = null;
			
			try {
			
				expr = xpath.compile("//" + "user[@role='employee']/*");
			
				result = expr.evaluate(xmlDocument, XPathConstants.NODESET);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			    
			NodeList nodes = (NodeList) result;
			    
			boolean term = false;
			
		    for (int i = 0; i < nodes.getLength(); i++) {
		        Element el = (Element) nodes.item(i);

		        if(el.getNodeName().equals("email"))
		        	term = true;
		        if ( el.getFirstChild().getNodeType() == Node.TEXT_NODE)	        		
		        	parameters.add(el.getFirstChild().getNodeValue());

		        if(term)
		        {
		        	user = userMapper(parameters,"employee");
			        books.add(user);
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
	
	public static void main(String args[])
	{
		XMLReaderUser reader = new XMLReaderUser();
		for (User user : reader.getUsers()) {
			System.out.println(user);
		}
		
		System.out.println(reader.getUserByUsername("daiana"));
	}
}
