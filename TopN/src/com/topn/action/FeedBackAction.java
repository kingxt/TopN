package com.topn.action;

import com.opensymphony.xwork2.ActionSupport;
import com.topn.controller.FeedBackController;
import com.topn.util.StringUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-5-31 下午10:29:04
 * 
 * 反馈
 */
public class FeedBackAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String text;
	
	private String msg;
	
	/**
	 * 用户反馈
	 * @return
	 */
	public String feedback(){
		if(StringUtil.isBlank(text)){
			return SUCCESS;
		}
		FeedBackController.getInstance().addFeedBack(text);
		return SUCCESS;
	}
	

	public void setText(String text) {
		this.text = text;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
