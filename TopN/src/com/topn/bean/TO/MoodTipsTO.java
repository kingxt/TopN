package com.topn.bean.TO;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-16 下午08:11:50
 * 
 * 心情回复to
 * 
 * (1)心情提醒：心情发布者id，昵称，头像url，心情id，心情内容，发布时间，回复总数；回复id，回复者id，昵称，头像url，回复内容，时间；
 */
public class MoodTipsTO {	
	
	private int moodPersonId;
	
	private String name;
	
	private String url;
	
	private int moodId;
	
	private String message;
	
	private String pushDate;
	
	private int total;
	
	private int replyId;
	
	private int replyPersonId;
	
	private String replyPersonName;
	
	private String replyURL;
	
	private String replyMsg;
	
	private String replyDate;

	public int getMoodId() {
		return moodId;
	}

	public void setMoodId(int moodId) {
		this.moodId = moodId;
	}

	public int getMoodPersonId() {
		return moodPersonId;
	}

	public void setMoodPersonId(int moodPersonId) {
		this.moodPersonId = moodPersonId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPushDate() {
		return pushDate;
	}

	public void setPushDate(String pushDate) {
		this.pushDate = pushDate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getReplyPersonId() {
		return replyPersonId;
	}

	public void setReplyPersonId(int replyPersonId) {
		this.replyPersonId = replyPersonId;
	}

	public String getReplyPersonName() {
		return replyPersonName;
	}

	public void setReplyPersonName(String replyPersonName) {
		this.replyPersonName = replyPersonName;
	}

	public String getReplyURL() {
		return replyURL;
	}

	public void setReplyURL(String replyURL) {
		this.replyURL = replyURL;
	}

	public String getReplyMsg() {
		return replyMsg;
	}

	public void setReplyMsg(String replyMsg) {
		this.replyMsg = replyMsg;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	
	
}
