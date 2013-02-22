package com.topn.test;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.topn.action.TestLoginAction;
import com.topn.bean.EntryForm;
import com.topn.collection.PicturePartition;
import com.topn.controller.GeneratePictureController;
import com.topn.flex.FlexService;
import com.topn.flex.FlexServiceImpl;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-19 下午04:25:30
 * 
 * 此测试用来模拟高并发测试用户点击图片
 */
public class TestTopN {
	private AtomicInteger ai = new AtomicInteger(0);
	
	//记录从数据库中获取多少次
	public static AtomicInteger fromDB = new AtomicInteger(0);
	//更新至数据库
	public static AtomicInteger toDB = new AtomicInteger(0);
	//记录从缓存中成功获取多少次
	public static AtomicInteger fromCache = new AtomicInteger(0);
	//记录排行榜获取多少次
	public static AtomicInteger rank = new AtomicInteger(0);
	//记录从数据库直接获取次数
	public static AtomicInteger direckSelect = new AtomicInteger(0);
	//记录直接更新数据库
	public static AtomicInteger direckUpdate = new AtomicInteger(0);
	
	public static AtomicInteger direckFromCacheAlready = new AtomicInteger(0);
	
	public static AtomicInteger direckFromCacheReady = new AtomicInteger(0);
	
	public static AtomicInteger updateLength = new AtomicInteger(0);
	
	public static AtomicInteger all = new AtomicInteger(0);
	
	public static AtomicInteger mergerGet = new AtomicInteger(0);
	
	public static AtomicInteger updateCache = new AtomicInteger(0);
	
	public static AtomicInteger count = new AtomicInteger(0);
	
	public static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();
	Random rd = new Random();
	
	public static void main(String[] args) {
		TestTopN ttn = new TestTopN();
		for (int i = 0; i < 10; i++) {
			new Thread(ttn.new VisualUser()).start();
		}
	}

	/**
	 * 模拟的用户
	 */
	class VisualUser implements Runnable{
		TestLoginAction la = new TestLoginAction();
		FlexService fsi =FlexServiceImpl.getInstance();
		//LoginTO lto = null;
		EntryForm init = GeneratePictureController.getInstance().getPictureBySexAndAge(rd.nextInt(2) + 1, PicturePartition.getStringAge(rd.nextInt(80)));
		
		public VisualUser() {
			la.setUsername("test" + ai.getAndIncrement() + "@163.com");
			la.setPassword("11");
			//LoginTO lto = la.login(); // 模拟登录
			//this.lto = lto;
		}
		
		@Override
		public void run() {
			while(true){
				if(count.get() == 100){
					int j = 0;
					j++;
				}
				try {
					Thread.sleep(100);
					//这里随便获取一张照片
					all.incrementAndGet();
					EntryForm ef = fsi.saveGrade(init.getEntryId(), rd.nextInt(11), init.getAge(), PicturePartition.getStringAge(rd.nextInt(40)), rd.nextInt(2) + 1);
					init = ef;
					count.incrementAndGet();
				
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
		}
		
	}
	
	public static void print(String age){
		System.out.println("----------------------------------");
		System.out.println("获取   age " + age);
		System.out.println("从数据库查询次数 ：" +  fromDB.get());
		System.out.println("同步到数据库次数 ：" +  toDB.get());
		System.out.println("从缓存总查询的次数 ：" +  fromCache.get());
		System.out.println("从already集合中查询次数：" + direckFromCacheAlready.get());
		System.out.println("从ready集合中查询次数：" + direckFromCacheReady.get());
		System.out.println("获取排行榜次数 ：" +  rank.get());
		System.out.println("直接查询次数 ：" +  direckSelect.get());
		System.out.println("直接更新次数 ：" +  direckUpdate.get());
		System.out.println("更新字符串中次数"+updateLength.get());
		System.out.println("总次数"+all.get());
		System.out.println("合并获取"+mergerGet.get());
		System.out.println("更新缓存次数"+updateCache.get());
		System.out.println("----------------------------------");
	}
}
