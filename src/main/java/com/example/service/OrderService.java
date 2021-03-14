package com.example.service;

import com.example.model.StockOrder;

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
				/* Buyer Order information */
				String buyerOrderId = buyerOrder.getOrderId();
				int buyerQuantity =  buyerOrder.getQuantity();
				double buyerPrice = buyerOrder.getPrice();
				/* Seller order Information */
				String sellerOrderId = sellerOrder.getOrderId();
				int sellerQuantity =  sellerOrder.getQuantity();
				double sellerPrice = sellerOrder.getPrice();
				
				if (buyerPrice >= sellerPrice) {
					/* Check for the stock quantity if buyer has more stock to buy
					* */
					if (buyerQuantity > sellerQuantity) {
						System.out.println(buyerOrderId + " " + sellerPrice + " " + sellerQuantity + " " + sellerOrderId);
						buyerOrder.setQuantity(buyerQuantity - sellerQuantity);
						sellerOrders.poll();
						buyerOrders.poll();
						buyerOrders.add(buyerOrder);
					}
					/* Check for the stock quantity if seller has more stock to sell
					 * */
					else {
						System.out.println(buyerOrderId + " " + sellerPrice + " "+ buyerQuantity + " " + sellerOrderId);
						sellerOrder.setQuantity(sellerQuantity - buyerQuantity);
						sellerOrders.poll();
						buyerOrders.poll();
						sellerOrders.add(sellerOrder);
					}
				}
			}
		}
	}
}
