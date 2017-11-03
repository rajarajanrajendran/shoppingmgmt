package com.rajan.shoppingmgmt.factory;

import java.util.HashMap;
import java.util.Map;

import com.rajan.shoppingmgmt.model.Item;

public class ItemFactory {

	private Map<String, Item> items = new HashMap<>();

	private static ItemFactory instance;

	private ItemFactory() {
	}

	public static ItemFactory getInstance() {
		if (instance == null) {
			instance = new ItemFactory();
		}
		return instance;
	}

	public void addItem(String id, Item item) {
		items.put(id, item);
	}

	public Item getItem(String id) {
		return items.get(id);
	}

}
