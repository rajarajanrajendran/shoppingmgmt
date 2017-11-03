package com.rajan.shoppingmgmt.factory;

import java.util.HashMap;
import java.util.Map;

import com.rajan.shoppingmgmt.model.Category;

public class CategoryFactory {

	private Map<String, Category> categories = new HashMap<>();

	private static CategoryFactory instance;

	private CategoryFactory() {
	}

	public static CategoryFactory getInstance() {
		if (instance == null) {
			instance = new CategoryFactory();
		}
		return instance;
	}

	public void addCategory(String name, Category category) {
		categories.put(name, category);
	}

	public Category getCategory(String name) {
		if (name != null) {
			return categories.get(name);
		}
		return null;
	}

}
