package com.example.model;

import java.util.Comparator;

public class SellingPriceComparator implements Comparator<StockOrder> {
	@Override
	public int compare(StockOrder o1, StockOrder o2) {
		if (o1.getPrice() > o2.getPrice()){
			return 1;
		}
		else if (o1.getPrice() < o2.getPrice())
			return -1;
		return 0;
	}
}
