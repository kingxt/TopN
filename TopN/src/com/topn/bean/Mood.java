package com.topn.bean;


import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-9 上午11:19:56
 * 
 * 心情
 */
@TableName(name="mood")
public class Mood {
	
	@PropertyAlias(alias="mood_id", isId=true)
	private int moodId;
	
	@PropertyAlias(alias="person_id")
	private int personalId;
	
	@PropertyAlias(alias="send_time")
	private String sendTime;
	
	private String message;
	
	@TableName(name="table")
	private String tableName;
	
	private int total;
	
	/**
	 * 1:所有人可见，2：紧好友可见，3：所有人不可见
	 * 默认所有人可见
	 */
	private int priv = 1;

	public int getMoodId() {
		return moodId;
	}

	public void setMoodId(int moodId) {
		this.moodId = moodId;
	}

	public int getPersonalId() {
		return personalId;
	}

	public void setPersonalId(int personalId) {
		this.personalId = personalId;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPriv() {
		return priv;
	}

	public void setPriv(int priv) {
		this.priv = priv;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}
	
	
}
