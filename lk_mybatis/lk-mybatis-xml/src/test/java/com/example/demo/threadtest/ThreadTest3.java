package com.example.demo.threadtest;

import java.util.concurrent.TimeUnit;

/**
* 类说明
* @author likui 
* @since  2020年7月16日 下午8:19:04
* @version 1.0
*
*/
public class ThreadTest3 {
	
	public static void test1() {
		while(true) {
			System.out.println("test1: ------------");
			sleep(1);
		}
	}
	
	public static void test2() {
		while(true) {
			System.out.println("test2: +++++++++++++");
			sleep(1);
		}
	}
	
	public static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Thread(ThreadTest3 :: test1).start();
		test2();
	}
	
	
	
}
