package com.example.demo.synchronize;

public class SynchronizedTest {
	private Object lock = new Object();

	public void synchronizedBlockOnObject(long executeTime) {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + " -> start synchronizedBlockOnObject");
			doSomething(executeTime);
			System.out.println(Thread.currentThread().getName() + " -> end synchronizedBlockOnObject");
		}
	}

	public void synchronizedBlockOnThis(long executeTime) {
		synchronized (this) {
			System.out.println(Thread.currentThread().getName() + " -> start synchronizedBlockOnThis");
			doSomething(executeTime);
			System.out.println(Thread.currentThread().getName() + " -> end synchronizedBlockOnThis");
		}
	}

	public void synchronizedBlockOnClass(long executeTime) {
		synchronized (SynchronizedTest.class) {
			System.out.println(Thread.currentThread().getName() + " -> start synchronizedBlockOnClass");
			doSomething(executeTime);
			System.out.println(Thread.currentThread().getName() + " -> end synchronizedBlockOnClass");
		}
	}

	public synchronized void synchronizedMethodOnThis(long executeTime) {
		System.out.println(Thread.currentThread().getName() + " -> start synchronizedMethodOnThis");
		doSomething(executeTime);
		System.out.println(Thread.currentThread().getName() + " -> end synchronizedMethodOnThis");
	}

	public static synchronized void synchronizedMethodOnClass(long executeTime) {
		System.out.println(Thread.currentThread().getName() + " -> start synchronizedMethodOnClass");
		doSomething(executeTime);
		System.out.println(Thread.currentThread().getName() + " -> end synchronizedMethodOnClass");
	}

	private static void doSomething(long executeTime) {
		try {
			Thread.sleep(executeTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SynchronizedTest synchronizedTest = new SynchronizedTest();
		Long executeTime = 1000L;
		// synchronizedTest.synchronizedBlockOnObject(executeTime);
		// synchronizedTest.synchronizedBlockOnThis(executeTime);
		// synchronizedTest.synchronizedBlockOnClass(executeTime);
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronizedTest.synchronizedBlockOnObject(executeTime);
			}
		}).start();
		

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronizedTest.synchronizedBlockOnThis(executeTime);
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronizedTest.synchronizedBlockOnClass(executeTime);
			}
		}).start();

//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				synchronizedTest.synchronizedMethodOnThis(executeTime);
//			}
//		}).start();
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				SynchronizedTest.synchronizedMethodOnClass(0);
//
//			}
//		}).start();

	}
}