package com.rajan.shoppingmgmt.billing;

import java.util.List;

import com.rajan.shoppingmgmt.model.Category;
import com.rajan.shoppingmgmt.model.Item;

public class MaxDiscountBillCalculator implements BillCalculator {

	@Override
	public double calculate(List<Item> items) {
		double billAmount = 0;
		for (Item item : items) {
			double maxDiscount = 0;

			// 1. Get brand discount
			maxDiscount = item.getBrand() == null ? maxDiscount : item.getBrand().getDiscount();

			// 2.Get category discount
			Category category = item.getCategory();
			double maxCategoryDiscount = category == null ? maxDiscount : category.getDiscount();
			Category parentCategory = category.getParent();
			while (parentCategory != null) {
				maxCategoryDiscount = maxCategoryDiscount > parentCategory.getDiscount() ? maxCategoryDiscount
						: parentCategory.getDiscount();
				parentCategory = parentCategory.getParent();
			}

			// maxCategoryDiscount = category.getMaxDiscount();

			// 3. Calculate max discount
			maxDiscount = maxDiscount > maxCategoryDiscount ? maxDiscount : maxCategoryDiscount;

			// 4. Calculate bill amount
			billAmount += item.getPrice() - (item.getPrice() * (maxDiscount / 100));
		}
		return billAmount;
	}

}
