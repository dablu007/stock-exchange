package com.example;

import com.example.service.DataReader;
import com.example.service.WorkerThread;

public class Main {
	private static String filepath = "src/main/resources/input.txt";
    public static void main(String[] args) {
    	
	    DataReader dataReader = DataReader.getInstance();
	    dataReader.readFile(filepath);
		Thread workerThread = new WorkerThread();
		workerThread.start();
	    dataReader.createAndExecuteStockOrders();
	    
		
    }
}
