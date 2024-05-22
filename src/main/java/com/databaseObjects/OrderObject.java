package com.databaseObjects;


public class OrderObject {
	private String name;
	private String datetime;
	private double amount;
	
	
	public OrderObject(String name, String datetime, double amount) {
		this.name = name;
		this.datetime = datetime;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
