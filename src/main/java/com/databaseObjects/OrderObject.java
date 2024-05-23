package com.databaseObjects;


public class OrderObject {
	private String name;
	private String datetime;
	private double amount;
	private int orderId;
	
	public OrderObject(String name, String datetime, double amount) {
		this.name = name;
		this.datetime = datetime;
		this.amount = amount;
	}
	public OrderObject(int orderId, String name, String datetime, double amount) {
		this.orderId = orderId;
		this.name = name;
		this.datetime = datetime;
		this.amount = amount;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
