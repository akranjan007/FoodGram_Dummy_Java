package com.databaseObjects;

public class MenuItemObject {
	private int itemId;
	private String name;
	private String description;
	private String category;
	private float price;
	
	public MenuItemObject(int itemId, String name, String description, String category, float price) {
		this.itemId = itemId;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
