package com.example.demo.synchronize;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Warehouse {

    private Queue<Integer> queue;
    private int capacity;

    public Warehouse(int capacity) {
        this.capacity = capacity;
        queue = new LinkedList<Integer>();
        
    }

    public synchronized void put(int num) {
        if (queue.size() >= capacity) {
            try {
                System.out.println(Thread.currentThread().getName() + " , start put full wait "+ System.currentTimeMillis());
                wait();
                System.out.println(Thread.currentThread().getName() + " , end put full wait " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(num);
        System.out.println(Thread.currentThread().getName() + " , put : " + num + "  , queue -> " + queue);
        notifyAll();
    }

    public synchronized int get() {
        if (queue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " , start get empty wait " +System.currentTimeMillis());
                wait();
                System.out.println(Thread.currentThread().getName() + " , end get empty wait " +System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int num = queue.poll();
        System.out.println(Thread.currentThread().getName() + " , get : " + num + "  , queue -> " + queue);
        notifyAll();
        return num;
    }
    
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(4);
        Random random = new Random();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    warehouse.put(random.nextInt(10));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "生产者-01").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    warehouse.get();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "消费者-01").start();
    }
}