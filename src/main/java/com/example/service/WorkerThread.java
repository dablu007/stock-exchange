package com.example.service;

public class WorkerThread extends Thread{
	@Override
	public void run() {
		while (true) {
			IOrderExecutor orderExecutor = new OrderService();
			synchronized(this) {
				orderExecutor.execute();
			}
			try {
				/* Added sleep to check output on some interval */
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
