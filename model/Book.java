package model;

import java.util.ArrayList;
import java.util.List;

public class Book {

	int idBook;
	String title;
	String author;
	int quantity;
	float price;
	String genre;
	List<Sell> sells;
	
	public Book(int idBook, String title, String author, int quantity, float price,String genre, List<Sell> sells) {
		super();
		this.idBook = idBook;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.quantity = quantity;
		this.price = price;
		this.sells = sells;
	}
	
	public Book(int idBook, String title, String author, int quantity, float price, String genre) {
		super();
		this.idBook = idBook;
		this.title = title;
		this.author = author;
		this.quantity = quantity;
		this.price = price;
		this.genre = genre;
		sells = new ArrayList<Sell>();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}


	public int getIdBook() {
		return idBook;
	}


	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public List<Sell> getSells() {
		return sells;
	}

	public void setSells(List<Sell> sells) {
		this.sells = sells;
	}
	
	public void addSell(Sell sell) {
		sells.add(sell);
	}

	@Override
	public String toString() {
		return "Book [idBook=" + idBook + ", title=" + title + ", author=" + author + ", genre=" + genre + ", quantity="
				+ quantity + ", price=" + price + ", sells=" + sells + "]";
	}
}
