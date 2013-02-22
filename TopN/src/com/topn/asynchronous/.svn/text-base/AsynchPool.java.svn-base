package com.topn.asynchronous;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-6 下午11:33:52
 * 
 * 异步线程执行池，这个池用来异步和数据库打交道，包括获取最新排行版
 */
public class AsynchPool {
	
	//异步的任务：在发出一个任务后，如果此任务没有完成，就不允许提交重复任务
	public static boolean pictureIsArrived = true;
	
	public static boolean rankingListIsArrived = true;

	/**
	 * 构造一个单线程线程池
	 */
	private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	private static final AsynchPool instance = new AsynchPool();
	
	private AsynchPool(){}
	
	public static AsynchPool getInstance(){
		return instance;
	}
	
	/**
	 * 添加一个需要异步执行的任务，此任务必须实现Runnable接口
	 * @param task 
	 */
	public void addTask(Runnable task){
		executorService.execute(task);
	}
}
