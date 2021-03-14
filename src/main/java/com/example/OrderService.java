package com.example;

import java.util.PriorityQueue;

public class OrderService implements OrderExecutor{
	
	@Override
	public void execute() {
		DataReader dataReader = DataReader.getInstance();
		PriorityQueue<StockOrder> buyerOrders = dataReader.getBuyerOrders();
		PriorityQueue<StockOrder> sellerOrders = dataReader.getSellerOrders();
		if (sellerOrders.size() > 1){
			StockOrder sellerOrder = sellerOrders.poll();
		}
	}
}
