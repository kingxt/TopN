package com.topn.task;

import com.topn.DAL.FriendDAL;
import com.topn.bean.MoodTips;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-17 下午02:13:42
 * 
 * 这个类的作用是将提醒回复异步到数据库
 */
public class ReplyTask implements Runnable {

	private MoodTips moodTips;
	
	@Override
	public void run() {
		FriendDAL.getInstance().addMoodTips(moodTips);		
	}
	
	public void setMoodTips(MoodTips moodTips) {
		this.moodTips = moodTips;
	}
	public MoodTips getMoodTips() {
		return moodTips;
	}

	public ReplyTask(MoodTips moodTips) {
		super();
		this.moodTips = moodTips;
	}

}
