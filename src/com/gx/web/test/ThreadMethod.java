package com.gx.web.test;



/*	class ThreadMethodTest implements Runnable{
	private String name;
 
	public ThreadMethodTest(String name) {
		this.name=name;
	}
 
	@Override
	public void run() {
		  for (int i = 0; i < 5; i++) {
	            System.out.println(name + "运行  :  " + i);
	            try {
	            	Thread.sleep((int) Math.random() * 10);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
		
	}
	
}*/
public class ThreadMethod {
 
	public static void main(String[] args) {
		new Thread(new ThreadMethodTest("C")).start();
		new Thread(new ThreadMethodTest("D")).start();
	}
 
}


