package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLWritterUser {

	public boolean createUser(User user)
	{
		try {
			String filepath = "User.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
		
			XPath xPath =  XPathFactory.newInstance().newXPath();

			List<Object> parameters = new ArrayList<Object>();
		
			
			String expression = "/users/user[username='"+user.getUsername()+"']";
			
			Node node = null;
			try {
				node = (Node) xPath.compile(expression).evaluate(doc, XPathConstants.NODE);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}
			
			if(node == null) {
				
				Element element = doc.createElement("user");
				
				Attr attr = doc.createAttribute("id");
				attr.setValue("1");
				element.setAttributeNode(attr);
				
				Attr attr2 = doc.createAttribute("role");
				attr2.setValue(user.getRole());
				element.setAttributeNode(attr2);
		
				Element el1 = doc.createElement("username");
				el1.appendChild(doc.createTextNode(user.getUsername()));
				
				Element el2 = doc.createElement("password");
				el2.appendChild(doc.createTextNode(user.getPassword()));
				
				Element el3 = doc.createElement("email");
				el3.appendChild(doc.createTextNode(user.getEmail()));
				
				element.appendChild(el1);
				element.appendChild(el2);
				element.appendChild(el3);
				
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
	
	public void updateUser(User user){
		try {
			String filepath = "User.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
		
			XPath xPath =  XPathFactory.newInstance().newXPath();
				
			String expression = "/users/user[username='"+user.getUsername()+"']";
			
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
	
				   if ("password".equals(nod.getNodeName())) 
					   nod.setTextContent(user.getPassword());
				   if ("email".equals(nod.getNodeName())) 
					   nod.setTextContent(user.getEmail());
				  
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
	
	public void deleteUser(User user)
	{
		try {
			String filepath = "User.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
		
			XPath xPath =  XPathFactory.newInstance().newXPath();
				
			String expression = "/users/user[username='"+user.getUsername()+"']";
			
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
		XMLWritterUser writter = new XMLWritterUser();
		//writter.addSell(1, new Sell(3,new Date(),2));

		boolean create = writter.createUser(new User("eu", "eu", "eu@yahoo.com", "employee"));
		
		//writter.updateUser(new User("laura", "la", "la@yahoo.com", "employee"));
		
		writter.deleteUser(new User("laura", "la", "la@yahoo.com", "employee"));
	}
}
