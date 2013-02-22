package com.topn.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import com.topn.collection.UserInRegister;

public class Test2 {
	
	private static AtomicInteger ai = new AtomicInteger(0);
	
	private Test2(){
		
	//	System.out.println(123);
	}
	
	
	
	public static void main(String[] args) {
		Test2 rr =new Test2();
		
		Thread r = new Thread(rr.new T1());r.start(); 
		Thread r1 = new Thread(rr.new T2());r1.start(); 
	}
	
	public class T1 implements Runnable{

		@Override
		public void run() {
			while(true){
				synchronized (ai) {
					try {System.out.println("----------------");
						Thread.sleep(5000);
						System.out.println("----------------");
						ai.incrementAndGet();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
	
	public class T2 implements Runnable{

		@Override
		public void run() {
			while(true){
				System.out.println(ai.incrementAndGet());
			}
				
			
		}
		
	}
}
