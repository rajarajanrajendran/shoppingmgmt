package com.rajan.shoppingmgmt.model;

public class Category {

	private final String name;

	private final Category parent;

	private final double discount;

	public Category(String name, Category parent, double discount) {
		this.name = name;
		this.parent = parent;
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public Category getParent() {
		return parent;
	}

	public double getDiscount() {
		return discount;
	}

	public double getMaxDiscount() {
		if (parent != null) {
			double parentMaxDiscount = parent.getMaxDiscount();
			return discount > parentMaxDiscount ? discount : parentMaxDiscount;
		}
		return discount;
	}
}
