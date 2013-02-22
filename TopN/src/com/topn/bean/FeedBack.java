package com.topn.bean;

import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-5-31 下午10:24:42
 * 
 * 用户反馈
 */
@TableName(name="feedback")
public class FeedBack {

	@PropertyAlias(isId=true, alias="feedback_id")
	private int fbId;
	
	private String text;
	
	private String time;

	public int getFbId() {
		return fbId;
	}

	public void setFbId(int fbId) {
		this.fbId = fbId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	

}
