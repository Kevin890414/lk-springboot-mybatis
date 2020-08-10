package com.example.demo.threadtest;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

public class CallableDemo implements Callable<CallableDto> {
    public CallableDto call() throws Exception {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in callable demo");
        return new CallableDto(1);
    }
    
    
    public static void test() throws Exception {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        Future<CallableDto> future = executor.submit(new CallableDemo());
        CallableDto callableDto = future.get();
        System.out.println("in test");
        System.out.println("id from callable is " + callableDto.getId());
        future.cancel(true);
        executor.shutdown();
        System.out.println("------------------------------------------------------");

        // 使用方式2：实现Callable接口需要用FutureTask进行包装，因为FutureTask实现了Runable接口和Future接口
         CallableDemo callThread = new CallableDemo();
         FutureTask<CallableDto> futureTask = new FutureTask<>(callThread);
         new Thread(futureTask).start();
         CallableDto callableDto2 = futureTask.get();
         System.out.println("2:in test");
         System.out.println("2:id from callable is " + callableDto2.getId());
    }
    
    public static void main(String[] args) throws Exception {
    	test();
    	System.out.println("in main");
	}
    
}

class CallableDto {
    private int id;

    public CallableDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

