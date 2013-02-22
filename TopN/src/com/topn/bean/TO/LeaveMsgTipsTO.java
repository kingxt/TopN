package com.topn.bean.TO;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-24 下午10:15:05
 * 
 * 留言提醒
 * 
 * (2)留言提醒：留言发布者id，昵称，头像url，留言id，留言内容，发布时间，回复总数；回复id，回复者id，昵称，头像url，回复内容，时间；
 */
public class LeaveMsgTipsTO {

	private int publisherId;
	
	private String  publisherName;
	
	private String publisherURL;
	
	private int leaveMsgId;
	
	private String leaveMsg;
	
	private String leaveDate;
	
	private int total;
	
	private int replyId;
	
	private int replyPersonId;
	
	private String replyPersonName;
	
	private String replyPersonURL;
	
	private String replyMsg;
	
	private String replyTime;

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherURL() {
		return publisherURL;
	}

	public void setPublisherURL(String publisherURL) {
		this.publisherURL = publisherURL;
	}

	public int getLeaveMsgId() {
		return leaveMsgId;
	}

	public void setLeaveMsgId(int leaveMsgId) {
		this.leaveMsgId = leaveMsgId;
	}

	public String getLeaveMsg() {
		return leaveMsg;
	}

	public void setLeaveMsg(String leaveMsg) {
		this.leaveMsg = leaveMsg;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
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

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	
	
}
