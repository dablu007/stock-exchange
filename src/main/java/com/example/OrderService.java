package com.example;

import java.util.PriorityQueue;

public class OrderService implements IOrderExecutor {
	
	@Override
	public void execute() {
		DataReader dataReader = DataReader.getInstance();
		PriorityQueue<StockOrder> buyerOrders = dataReader.getBuyerOrders();
		PriorityQueue<StockOrder> sellerOrders = dataReader.getSellerOrders();
		if (sellerOrders.size() > 1){
			StockOrder sellerOrder = sellerOrders.peek();
			StockOrder buyerOrder = buyerOrders.peek();
			/* Check  for the buyer price first */
			if (buyerOrder != null) {
				if (buyerOrder.getPrice() >= sellerOrder.getPrice()) {
					/* Check for the number of quantity */
					if (buyerOrder.getQuantity() > sellerOrder.getQuantity()) {
						System.out.println(buyerOrder.getOrderId() + " " + sellerOrder.getPrice() + " "+ sellerOrder.getQuantity() + " " + sellerOrder.getOrderId());
						buyerOrder.setQuantity(buyerOrder.getQuantity() - sellerOrder.getQuantity());
						sellerOrders.poll();
						buyerOrders.poll();
						buyerOrders.add(buyerOrder);
					}
					else {
						System.out.println(buyerOrder.getOrderId() + " " + sellerOrder.getPrice() + " "+ buyerOrder.getQuantity() + " " + sellerOrder.getOrderId());

						sellerOrder.setQuantity(sellerOrder.getQuantity() - buyerOrder.getQuantity());
						sellerOrders.poll();
						buyerOrders.poll();
						sellerOrders.add(sellerOrder);
					}
				}
			}
		}
	}
}
