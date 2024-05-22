package com.databaseObjects;

public class CartItems {
	
	private int itemId;
	private String itemName;
	private String itemDescription;
	private double price;
	private int quantity;
	
	public CartItems(int itemId, String itemName, String itemDescription, double price, int quantity) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
