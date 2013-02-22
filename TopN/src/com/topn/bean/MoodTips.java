package com.topn.bean;

import com.topn.util.annotation.IgnoreProperty;
import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-17 下午01:47:58
 * 
 * 添加好友提醒
 */
@TableName(name="mood_tips")
public class MoodTips {

	@PropertyAlias(alias="mt_id")
	private int moodTipsId;
	
	//那条心情
	@PropertyAlias(alias="mood_id")
	private int moodId;
	
	//提醒谁
	@PropertyAlias(alias="remind_id")
	private int remindId;
	
	//那个人的心情
	@PropertyAlias(alias="mood_owner_id")
	private int moodOwerId;
	
	@PropertyAlias(alias="add_time")
	private int addTime;
	
	@IgnoreProperty
	private int replyId;

	public int getMoodTipsId() {
		return moodTipsId;
	}

	public void setMoodTipsId(int moodTipsId) {
		this.moodTipsId = moodTipsId;
	}

	public int getMoodId() {
		return moodId;
	}

	public void setMoodId(int moodId) {
		this.moodId = moodId;
	}

	public int getRemindId() {
		return remindId;
	}

	public void setRemindId(int remindId) {
		this.remindId = remindId;
	}

	public int getMoodOwerId() {
		return moodOwerId;
	}

	public void setMoodOwerId(int moodOwerId) {
		this.moodOwerId = moodOwerId;
	}

	public int getAddTime() {
		return addTime;
	}

	public void setAddTime(int addTime) {
		this.addTime = addTime;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
}
