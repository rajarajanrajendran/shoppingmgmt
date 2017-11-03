package com.rajan.shoppingmgmt.billing;

import java.util.List;

import com.rajan.shoppingmgmt.model.Item;

public class Bill {

	private BillCalculator billCalculator;

	public Bill(BillCalculator billCalculator) {
		this.billCalculator = billCalculator;
	}

	public void billItems(List<Item> items) {
		// Display item properties
		
		// Calculate bill value
		System.out.println(billCalculator.calculate(items));
	}

}
