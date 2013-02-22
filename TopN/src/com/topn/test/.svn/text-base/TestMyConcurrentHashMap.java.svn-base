package com.topn.test;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.topn.bean.EntryForm;
import com.topn.collection.MyConcurrentHashMap;
import com.topn.collection.MyMapEntry;

public class TestMyConcurrentHashMap {
	MyConcurrentHashMap<String, EntryForm> tt = new MyConcurrentHashMap<String, EntryForm>();

	AtomicInteger ai = new AtomicInteger(0);
	Random rd = new Random();
	public void test() {
		
		
		for (int i = 0; i < 10000; i++) {
			EntryForm ef = new EntryForm();
			ef.setAge(rd.nextInt(1) + 1);
			tt.put("kingxt "+ i, ef);
		}
		long ll = System.currentTimeMillis();
		
		MyMapEntry<String, EntryForm> sa = tt.getFirstAndRemove(rd.nextInt(1) + 1);
		
		System.out.println(System.currentTimeMillis() -ll);
		
//		for(int j = 0; j < 100; j++){
//			
//			Thread a  = new Thread(new TestThread(), "thread" + j);
//			a.start();
//		}
	}
	
	class TestThread implements Runnable{
		
		@Override
		public void run() {
			
			while(ai.get() < 10000){
				MyMapEntry<String, EntryForm> sa = tt.getFirstAndRemove(rd.nextInt(1) + 1);
				ai.incrementAndGet();
				
				System.out.println(sa.getKey());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		TestMyConcurrentHashMap t = new TestMyConcurrentHashMap();
		t.test();
	}
}
