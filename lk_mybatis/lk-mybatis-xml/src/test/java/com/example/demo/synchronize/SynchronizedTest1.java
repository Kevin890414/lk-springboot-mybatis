package com.example.demo.synchronize;
/**
* 类说明
* @author likui 
* @since  2020年5月13日 下午5:57:31
* @version 1.0
*
*/
public class SynchronizedTest1 implements Runnable {
	//共享资源
    static int i =0;
    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase(){
        i++;
    }
    
    @Override
    public void run(){
        for (int j =0 ; j<10000;j++){
            increase();
        }
    }
    
    public synchronized void method1() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public synchronized void method2() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static void main(String[] args) throws InterruptedException {
//        SynchronizedTest1 test = new SynchronizedTest1();
//        Thread t1 = new Thread(test);
//        Thread t2 = new Thread(test);
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(i);
    	
    	final SynchronizedTest1 test = new SynchronizedTest1();

        new Thread(test::method1).start();

        new Thread(test::method2).start();
    }
}
