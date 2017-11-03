package com.rajan.shoppingmgmt.model;

public class Brand {

	private final String name;

	private final double discount;

	public Brand(String name, double discount) {
		this.name = name;
		this.discount = discount;
	}
	
	public String getName() {
		return name;
	}

	public double getDiscount() {
		return discount;
	}

}
