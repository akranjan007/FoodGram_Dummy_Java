package com.databaseObjects;

public class ItemObj {
	private String name;
	private String description;
	private String category;
	private Double price;
	
	
	public ItemObj(String name, String description, String category, Double price) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
