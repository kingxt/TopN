package com.topn.bean.TO;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-23 下午09:23:59
 * 
 * 留言回复to，对应存储过程sp_reply_note_add_reply的传入参数
 */
public class ReplyLeaveMsgTO {

	private int replyId;
	
	private int leaveMsgId;
	
	private int parentId;
	
	private int publishId;
	
	private String leaveMsg;
	
	private int total;

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getLeaveMsgId() {
		return leaveMsgId;
	}

	public void setLeaveMsgId(int leaveMsgId) {
		this.leaveMsgId = leaveMsgId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getPublishId() {
		return publishId;
	}

	public void setPublishId(int publishId) {
		this.publishId = publishId;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getLeaveMsg() {
		return leaveMsg;
	}

	public void setLeaveMsg(String leaveMsg) {
		this.leaveMsg = leaveMsg;
	}
	
	
}
