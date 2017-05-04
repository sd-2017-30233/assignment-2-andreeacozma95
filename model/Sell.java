package model;

import java.util.Date;

public class Sell {
	
	int idSell;
	Date sellingDate;
	int quantity;
	
	public Sell(int idSell, Date sellingDate, int quantity) {
		super();
		this.idSell = idSell;
		this.sellingDate = sellingDate;
		this.quantity = quantity;
	}

	public int getIdSell() {
		return idSell;
	}

	public void setIdSell(int idSell) {
		this.idSell = idSell;
	}

	public Date getSellingDate() {
		return sellingDate;
	}

	public void setSellingDate(Date sellingDate) {
		this.sellingDate = sellingDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Sell [idSell=" + idSell + ", sellingDate=" + sellingDate + ", quantity=" + quantity + "]";
	}
	
}
