package com.topn.bean.TO;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-12 下午06:46:07
 * 
 * 用来取所有的回复记录
 */
public class ReplyTO {
	
	private int replyId;

	private int replyPersonId;
	
	private String replyPersonName;
	
	private String replyPersonURL;
	
	private String replyMsg;
	
	private String replyDate;
	

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

	public String getReplyPersonURL() {
		return replyPersonURL;
	}

	public void setReplyPersonURL(String replyPersonURL) {
		this.replyPersonURL = replyPersonURL;
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

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	
	
}
