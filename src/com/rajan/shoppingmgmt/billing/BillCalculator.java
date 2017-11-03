package com.rajan.shoppingmgmt.billing;

import java.util.List;

import com.rajan.shoppingmgmt.model.Item;

public interface BillCalculator {

	double calculate(List<Item> items);

}
