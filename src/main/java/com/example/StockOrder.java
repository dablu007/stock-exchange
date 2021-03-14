package com.example;

import lombok.Builder;

@Builder
public class StockOrder {
	private long timestamp;
	private int orderId;
	private String stock;
	private double price;
	private StockType type;
	
}
