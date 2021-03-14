package com.example;

public class Main {
	private static String filepath = "src/main/resources/input.txt";
    public static void main(String[] args) {
    	
	    DataReader dataReader = DataReader.getInstance();
	    dataReader.readFile(filepath);
	    dataReader.createAndExecuteStockOrders();
		IOrderExecutor orderExecutor = new OrderService();
	    while (true){
	    	orderExecutor.execute();
		}
    }
}
