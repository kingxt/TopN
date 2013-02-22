package com.topn.bean.TO;

import java.util.Date;

import com.topn.util.DateUitl;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-11 下午03:59:28
 * 
 * 朋友最近的心情  
 */
public class FriendRecentMoodTO implements Comparable<FriendRecentMoodTO>{

	//下面是心情---------------------------------
	private int moodPersonId;
	
	private String moodPersonName;
	
	private String moodPersonURL;
	
	private int moodId;
	
	private String moodMsg;
	
	private String moodPublishTime;
	
	//这个字段是容易字段,用来做时间排序的
	private Date moodDate;
	
	private int totalReply;
	
	//下面是第一条记录的--------------------------
	
	//这个id是personid
	private int firstReplyId;
	
	private String firstReplyPersonName;
	
	private String firstReplyPersonURL;
	
	private String firstReplyPersonMsg;
	
	private String firstReplyPersonTime;
	
	private int replyTopId;
	
	//下面是最后第一条记录的--------------------------
	
	//这个id是personid
	private int lastReplyId;
	
	private String lastReplyPersonName;
	
	private String lastReplyPersonURL;
	
	private String lastReplyPersonMsg;
	
	private String lastReplyPersonTime;
	
	private int replyLastId;

	public int getMoodPersonId() {
		return moodPersonId;
	}

	public void setMoodPersonId(int moodPersonId) {
		this.moodPersonId = moodPersonId;
	}

	public String getMoodPersonName() {
		return moodPersonName;
	}

	public void setMoodPersonName(String moodPersonName) {
		this.moodPersonName = moodPersonName;
	}

	public String getMoodPersonURL() {
		return moodPersonURL;
	}

	public void setMoodPersonURL(String moodPersonURL) {
		this.moodPersonURL = moodPersonURL;
	}

	public int getMoodId() {
		return moodId;
	}

	public void setMoodId(int moodId) {
		this.moodId = moodId;
	}

	public String getMoodMsg() {
		return moodMsg;
	}

	public void setMoodMsg(String moodMsg) {
		this.moodMsg = moodMsg;
	}

	public String getMoodPublishTime() {
		return moodPublishTime;
	}

	public void setMoodPublishTime(String moodPublishTime) {
		this.moodPublishTime = moodPublishTime;
	}

	public int getTotalReply() {
		return totalReply;
	}

	public void setTotalReply(int totalReply) {
		this.totalReply = totalReply;
	}

	public int getFirstReplyId() {
		return firstReplyId;
	}

	public void setFirstReplyId(int firstReplyId) {
		this.firstReplyId = firstReplyId;
	}

	public String getFirstReplyPersonName() {
		return firstReplyPersonName;
	}

	public void setFirstReplyPersonName(String firstReplyPersonName) {
		this.firstReplyPersonName = firstReplyPersonName;
	}

	public String getFirstReplyPersonURL() {
		return firstReplyPersonURL;
	}

	public void setFirstReplyPersonURL(String firstReplyPersonURL) {
		this.firstReplyPersonURL = firstReplyPersonURL;
	}

	public String getFirstReplyPersonMsg() {
		return firstReplyPersonMsg;
	}

	public void setFirstReplyPersonMsg(String firstReplyPersonMsg) {
		this.firstReplyPersonMsg = firstReplyPersonMsg;
	}

	public String getFirstReplyPersonTime() {
		return firstReplyPersonTime;
	}

	public void setFirstReplyPersonTime(String firstReplyPersonTime) {
		this.firstReplyPersonTime = firstReplyPersonTime;
	}

	
	public int getLastReplyId() {
		return lastReplyId;
	}

	public void setLastReplyId(int lastReplyId) {
		this.lastReplyId = lastReplyId;
	}

	public String getLastReplyPersonName() {
		return lastReplyPersonName;
	}

	public void setLastReplyPersonName(String lastReplyPersonName) {
		this.lastReplyPersonName = lastReplyPersonName;
	}

	public String getLastReplyPersonURL() {
		return lastReplyPersonURL;
	}

	public void setLastReplyPersonURL(String lastReplyPersonURL) {
		this.lastReplyPersonURL = lastReplyPersonURL;
	}

	public String getLastReplyPersonMsg() {
		return lastReplyPersonMsg;
	}

	public void setLastReplyPersonMsg(String lastReplyPersonMsg) {
		this.lastReplyPersonMsg = lastReplyPersonMsg;
	}

	public String getLastReplyPersonTime() {
		return lastReplyPersonTime;
	}

	public void setLastReplyPersonTime(String lastReplyPersonTime) {
		this.lastReplyPersonTime = lastReplyPersonTime;
	}

	public int getReplyTopId() {
		return replyTopId;
	}

	public void setReplyTopId(int replyTopId) {
		this.replyTopId = replyTopId;
	}

	public int getReplyLastId() {
		return replyLastId;
	}

	public void setReplyLastId(int replyLastId) {
		this.replyLastId = replyLastId;
	}

	@Override
	public int compareTo(FriendRecentMoodTO o) {
		if(this.moodDate.getTime() > o.getMoodDate().getTime()){
			return -1;
		}else{
			return 1;
		}
	}

	public Date getMoodDate() {
		return moodDate;
	}

	public void setMoodDate(Date moodDate) {
		this.moodDate = moodDate;
	}

	
}
