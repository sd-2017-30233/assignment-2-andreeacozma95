package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

public class XMLReaderBook {

	 private SearchStrategy strategy;

	   public XMLReaderBook(SearchStrategy strategy){
	      this.strategy = strategy;
	   }

	   public List<Book> executeStrategy(String value){
	      return strategy.filterBooks(value);
	   }
	
	
}
