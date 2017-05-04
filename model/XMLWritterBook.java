package model;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLWritterBook {

	public void addSell(int idBook, Sell sell)
	{
		try {
			String filepath = "Book.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
		
			XPath xPath =  XPathFactory.newInstance().newXPath();

			List<Object> parameters = new ArrayList<Object>();
		
			
			String expression = "/books/book[idBook='"+idBook+"']";
			
			Node node = null;
			try {
				node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			
			if(null != node) {
				
			Element element = doc.createElement("sell");
		
			Element el1 = doc.createElement("idSell");
			el1.appendChild(doc.createTextNode(Integer.toString(sell.getIdSell())));
			
			Element el2 = doc.createElement("sellingDate");
			java.text.Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			String s = formatter.format(sell.getSellingDate());

			el2.appendChild(doc.createTextNode(s));
			
			Element el3 = doc.createElement("quantity");
			el3.appendChild(doc.createTextNode(Integer.toString(sell.getQuantity())));
			
			element.appendChild(el1);
			element.appendChild(el2);
			element.appendChild(el3);
			
			node.appendChild(element);

			NodeList list = node.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
	                   Node nod = list.item(i);

			   if ("quantity".equals(nod.getNodeName())) {
				   String quantityOld = nod.getFirstChild().getNodeValue();
				   int quantityOldValue = Integer.parseInt(quantityOld);
				   int quantityNewValue = quantityOldValue - sell.getQuantity();
				   
				   nod.setTextContent(Integer.toString(quantityNewValue));
			   }
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Done");
			}
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		
	}
	
	public boolean createBook(Book book)
	{
		try {
			String filepath = "Book.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
		
			XPath xPath =  XPathFactory.newInstance().newXPath();

			List<Object> parameters = new ArrayList<Object>();
		
			
			String expression = "/books/book[idBook='"+book.getIdBook()+"']";
			
			Node node = null;
			try {
				node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			
			if(node == null) {
				
				Element element = doc.createElement("book");
			
				Element el1 = doc.createElement("idBook");
				el1.appendChild(doc.createTextNode(Integer.toString(book.getIdBook())));
				
				Element el2 = doc.createElement("title");
				el2.appendChild(doc.createTextNode(book.getTitle()));
				
				Element el3 = doc.createElement("author");
				el3.appendChild(doc.createTextNode(book.getAuthor()));
				
				Element el4 = doc.createElement("quantity");
				el4.appendChild(doc.createTextNode(Integer.toString(book.getQuantity())));
				
				Element el5 = doc.createElement("price");
				el5.appendChild(doc.createTextNode(Float.toString(book.getPrice())));
				
				Element el6 = doc.createElement("genre");
				el6.appendChild(doc.createTextNode(book.getGenre()));
				
				element.appendChild(el1);
				element.appendChild(el2);
				element.appendChild(el3);
				element.appendChild(el4);
				element.appendChild(el5);
				element.appendChild(el6);
				
				doc.getDocumentElement().appendChild(element);
	
				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(filepath));
				transformer.transform(source, result);
	
				System.out.println("Done");
			}
			else return false;
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		
		return true;
	}
	
	public void updateBook(Book book)
	{
		try {
			String filepath = "Book.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
		
			XPath xPath =  XPathFactory.newInstance().newXPath();
				
			String expression = "/books/book[idBook='"+book.getIdBook()+"']";
			
			Node node = null;
			try {
				node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			
			if(null != node) {
						
				NodeList list = node.getChildNodes();
	
				for (int i = 0; i < list.getLength(); i++) {
		                   Node nod = list.item(i);
	
				   if ("quantity".equals(nod.getNodeName())) 
					   nod.setTextContent(Integer.toString(book.getQuantity()));
				   if ("price".equals(nod.getNodeName())) 
					   nod.setTextContent(Float.toString(book.getPrice()));
				   if ("title".equals(nod.getNodeName())) 
					   nod.setTextContent(book.getTitle());
				   if ("author".equals(nod.getNodeName())) 
					   nod.setTextContent(book.getAuthor());
				   if ("genre".equals(nod.getNodeName())) 
					   nod.setTextContent(book.getGenre());
			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Done");
			}
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
	}
	
	public void deleteBook(Book book)
	{
		try {
			String filepath = "Book.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
		
			XPath xPath =  XPathFactory.newInstance().newXPath();
				
			String expression = "/books/book[idBook='"+book.getIdBook()+"']";
			
			NodeList nodes = null;
			try {
				nodes = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			
			if(null != nodes) {
				for (int i = 0; i < nodes.getLength(); i++)
				{
				    Node node = nodes.item(i);
				    node.getParentNode().removeChild(node);
				}
			
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);

			System.out.println("Done");
			
		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
	}
	
	
	public static void main(String args[])
	{
		XMLWritterBook writter = new XMLWritterBook();
		//writter.addSell(1, new Sell(3,new Date(),2));

		//boolean create = writter.createBook(new Book(6,"aa","ab",20,(float) 15.5,"Cooking"));
		
		writter.deleteBook(new Book(6, "abcd", "a", 40, 20, "Cooking"));
	}
}
