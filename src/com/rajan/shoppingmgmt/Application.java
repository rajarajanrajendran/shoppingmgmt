package com.rajan.shoppingmgmt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rajan.shoppingmgmt.billing.Bill;
import com.rajan.shoppingmgmt.billing.BillCalculator;
import com.rajan.shoppingmgmt.billing.MaxDiscountBillCalculator;
import com.rajan.shoppingmgmt.factory.BrandFactory;
import com.rajan.shoppingmgmt.factory.CategoryFactory;
import com.rajan.shoppingmgmt.factory.ItemFactory;
import com.rajan.shoppingmgmt.model.Brand;
import com.rajan.shoppingmgmt.model.Category;
import com.rajan.shoppingmgmt.model.Item;

public class Application {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println(
					"Input file name missing : Input file name should be given as first command line argument");
			return;
		}
		String inputFileName = args[0];

		// 1. Create & init brand service
		initBrands();

		// 2. Create & init category service
		initCategories();

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(new File(inputFileName));
			bufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			System.out.println(
					"Input file not found : Input file mention in command line argument is missing");
			return;
		}

		// 3. Read input, create item service, create items and add items to the
		// item service
		ItemFactory itemFactory = ItemFactory.getInstance();

		String s = null;
		while (true) {
			try {
				s = bufferedReader.readLine();
			} catch (IOException e) {
				System.out.println(
						"Unable to read the file for item inputs");
				return;
			}
			if (!s.trim().isEmpty()) {
				break;
			}
		}
		int itemCount = Integer.parseInt(s);
		while (itemCount > 0) {
			try {
				s = bufferedReader.readLine();
			} catch (IOException e) {
				System.out.println(
						"Unable to read the file for item inputs");
				return;
			}
			if (s.isEmpty()) {
				continue;
			}
			String[] sArray = s.split(",");
			if (sArray.length == 4) { // validation of item's entry
				Item item = new Item(sArray[0].trim(), BrandFactory.getInstance().getBrand(sArray[1].trim()),
						CategoryFactory.getInstance().getCategory(sArray[2].trim()), Double.parseDouble(sArray[3]));
				itemFactory.addItem(sArray[0], item);
			}
			itemCount--;
		}

		// 4. Read input for cutomer options, process the options and print the
		// result
		while (true) {
			try {
				s = bufferedReader.readLine();
			} catch (IOException e) {
				System.out.println(
						"Unable to read the file for customer inputs");
				return;
			}
			if (!s.trim().isEmpty()) {
				break;
			}
		}
		int customerCount = Integer.parseInt(s);
		BillCalculator billCalculator = new MaxDiscountBillCalculator();
		while (customerCount > 0) {
			try {
				s = bufferedReader.readLine();
			} catch (IOException e) {
				System.out.println(
						"Unable to read the file for customer inputs");
				return;
			}
			if (s.isEmpty()) {
				continue;
			}
			String[] sArray = s.split(",");
			Bill bill = new Bill(billCalculator);
			List<Item> items = new ArrayList<>();
			for (String ele : sArray) {
				Item item = itemFactory.getItem(ele.trim());
				if (item != null) { // validation of valid item id
					items.add(item);
				}
			}
			bill.billItems(items);
			customerCount--;
		}

		try {
			bufferedReader.close();
		} catch (IOException e) {
			System.out.println(
					"Unable to close the file operation");
			return;
		}
	}

	private static void initBrands() {
		BrandFactory brandFactory = BrandFactory.getInstance();
		brandFactory.addBrand("Wrangler", new Brand("Wrangler", 10));
		brandFactory.addBrand("Arrow", new Brand("Arrow", 20));
		brandFactory.addBrand("Vero Moda", new Brand("Vero Moda", 60));
		brandFactory.addBrand("UCB", new Brand("UCB", 0));
		brandFactory.addBrand("Adidas", new Brand("Adidas", 5));
		brandFactory.addBrand("Provogue", new Brand("Provogue", 20));
	}

	private static void initCategories() {
		CategoryFactory categoryFactory = CategoryFactory.getInstance();
		categoryFactory.addCategory("Men's Wear", new Category("Men's Wear", null, 0));
		categoryFactory.addCategory("Women's Wear", new Category("Women's Wear", null, 50));
		categoryFactory.addCategory("Shirts", new Category("Shirts", categoryFactory.getCategory("Men's Wear"), 0));
		categoryFactory.addCategory("Trousers", new Category("Trousers", categoryFactory.getCategory("Men's Wear"), 0));
		categoryFactory.addCategory("Casuals", new Category("Casuals", categoryFactory.getCategory("Trousers"), 30));
		categoryFactory.addCategory("Jeans", new Category("Jeans", categoryFactory.getCategory("Men's Wear"), 20));
		categoryFactory.addCategory("Dresses",
				new Category("Dresses", categoryFactory.getCategory("Women's Wear"), 20));
		categoryFactory.addCategory("Footwear",
				new Category("Footwear", categoryFactory.getCategory("Women's Wear"), 20));
	}

}
