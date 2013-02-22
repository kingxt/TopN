package com.topn.asynchronous;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-6 下午11:33:52
 * 
 * 异步线程执行池，这个池的作用是想数据库中插入心情回复提醒
 */
public class MoodPool {

	/**
	 * 构造一个单线程线程池
	 */
	private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	private static final MoodPool instance = new MoodPool();
	
	private MoodPool(){}
	
	public static MoodPool getInstance(){
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
