package com.example.demo.threadtest;

public class UseThread extends Thread {
	public void run() {
		super.run();
		// do my work;
		System.out.println("I am extends Thread");
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
	     Thread useThread = new UseThread();
	     useThread.start();
	     useThread.join();
	     System.out.println("in main");
	}
}