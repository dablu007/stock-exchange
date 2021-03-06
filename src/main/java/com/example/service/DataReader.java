package com.example.service;

import com.example.enums.StockType;
import com.example.model.BuyingPriceComparator;
import com.example.model.SellingPriceComparator;
import com.example.model.StockOrder;
import lombok.Getter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static com.example.constants.Constants.*;

@Getter
public class DataReader {
	private PriorityQueue<StockOrder> buyerOrders;
	private PriorityQueue<StockOrder> sellerOrders;
	private List<String> inputFileLines;
	private static DataReader instance = null;
	
	public static DataReader getInstance()
	{
		if (instance == null)
			instance = new DataReader();
		
		return instance;
	}
	private DataReader() {
		this.inputFileLines = new ArrayList<>();
		this.buyerOrders = new PriorityQueue(new BuyingPriceComparator());
		this.sellerOrders = new PriorityQueue(new SellingPriceComparator());
	}
	
	public void readFile(String inputFileName){
		List<String> lines = Collections.EMPTY_LIST;
		try {
			lines = Files.readAllLines(Paths.get(inputFileName), StandardCharsets.UTF_8);
			
		} catch (IOException e) {
			System.out.println("Unable to read file lines" +  e.getStackTrace());
		}
		this.inputFileLines = lines;
		
	}
	
	/* Storing the buyer and seller order in
	 * seperate queue to process and executing the order by buyer if seller exist
	 *  */
	public void createAndExecuteStockOrders(){
		IOrderExecutor orderExecutor = new OrderService();
		for (String data :this.inputFileLines) {
			String[] splittedData = data.split(SPACE);
			String orderId = splittedData[0];
			String timeData = splittedData[1];
			String name = splittedData[2];
			String stockType = splittedData[3].toUpperCase();
			double orderPrice;
			orderPrice = Double.parseDouble(splittedData[4]);
			int quantity;
			quantity = Integer.parseInt(splittedData[5]);
			String[] timeSplitted = timeData.split(COLON);
			int hour = Integer.parseInt(timeSplitted[0]) * MIN_CONSTANT;
			int min = Integer.parseInt(timeSplitted[1]);
			Date date = new Date(new Date().getTime() + hour + min);
			StockOrder order = StockOrder.builder().orderId(orderId).timestamp(date.getTime())
					.stock(name).type(StockType.valueOf(stockType)).price(orderPrice).quantity(quantity).build();
			if (stockType.equalsIgnoreCase(BUY)){
				buyerOrders.add(order);
			}
			else {
				sellerOrders.add(order);
			}
			orderExecutor.execute();
		}
	}
}
