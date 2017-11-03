package com.rajan.shoppingmgmt.model;

public class Item {

	private final String id;
	
	private final Brand brand;
	
	private final Category category;
	
	private final double price;

	public Item(String id, Brand brand, Category category, double price) {
		super();
		this.id = id;
		this.brand = brand;
		this.category = category;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public Brand getBrand() {
		return brand;
	}

	public Category getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

}
