package com.rajan.shoppingmgmt.factory;


import java.util.HashMap;
import java.util.Map;

import com.rajan.shoppingmgmt.model.Brand;

public class BrandFactory {

	private Map<String, Brand> brands = new HashMap<>();

	private static BrandFactory instance;

	private BrandFactory() {
	}

	public static BrandFactory getInstance() {
		if (instance == null) {
			instance = new BrandFactory();
		}
		return instance;
	}

	public void addBrand(String name, Brand brand) {
		brands.put(name, brand);
	}

	public Brand getBrand(String name) {
		if (name != null) {
			return brands.get(name);
		}
		return null;
	}

}
