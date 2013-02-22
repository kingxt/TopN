package com.topn.task;

import com.topn.controller.GeneratePictureController;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-10 下午05:08:10
 * 异步任务，将成绩保存到集合中或者异步到数据库中
 */
public class GradeTask implements Runnable{

	private int entryId;
	private float grade;
	private String age;
	@Override
	public void run() {
		if(entryId != 0 && grade >= 0){
			GeneratePictureController.getInstance().grade2DB(entryId, grade, age);
		}
	}
	
	public GradeTask(int entryId, float grade, String age) {
		super();
		this.entryId = entryId;
		this.grade = grade;
		this.age = age;
	}

}
