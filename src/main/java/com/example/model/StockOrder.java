package com.example.model;

import com.example.enums.StockType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class StockOrder {
	private String orderId;
	private long timestamp;
	private String stock;
	private StockType type;
	private double price;
	private int quantity;
	
}
