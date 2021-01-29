package com.example.demo.jvm;
class User{
	
	public static User user = null;

	@Override
	protected void finalize() throws Throwable {
		System.out.println("User-->finalize()");
		user = this;
	}
	
}

public class FinalizerTest {
	public static void main(String[] args) throws InterruptedException {
		User user = new User();
		user = null;
		System.gc();
		Thread.sleep(1000);
		user = User.user;
		System.out.println(user != null);//true
		
		user = null;
		System.gc();
//		Thread.sleep(1000);
		System.out.println(user != null);//false
	}
}
