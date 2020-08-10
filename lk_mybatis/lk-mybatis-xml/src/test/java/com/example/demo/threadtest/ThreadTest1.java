package com.example.demo.threadtest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
* 类说明
* @author likui 
* @since  2020年7月16日 下午8:19:04
* @version 1.0
*
*/
public class ThreadTest1 {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		
		Random random = new Random();
		
		System.out.println("开始时间："+ LocalDateTime.now());
		long currentTimeMillis = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					list.add(random.nextInt());
				}
			});
			thread.start();
		}
		
		System.out.println("耗时："+(System.currentTimeMillis()-currentTimeMillis));
		
	}
}
