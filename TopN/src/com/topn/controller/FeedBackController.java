package com.topn.controller;

import java.util.Date;

import org.apache.log4j.Logger;

import com.topn.DAL.FeedBackDAL;
import com.topn.bean.FeedBack;
import com.topn.util.DateUitl;
import com.topn.util.GenerateSql;
import com.topn.util.LoggerUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-5-31 下午10:29:50
 * 
 * 反馈控制值逻辑
 */
public class FeedBackController {
	
	private static Logger logger = Logger.getLogger(FeedBackController.class);
	
	private static FeedBackController instance = new FeedBackController();

	private FeedBackController() {
	}

	public static FeedBackController getInstance(){
		return instance;
	}
	
	/**
	 * 添加反馈信息
	 * @param text
	 */
	public void addFeedBack(String text){
		FeedBack fb = new FeedBack();
		fb.setText(text);
		fb.setTime(DateUitl.dateTOString(new Date()));
		String sql = "";
		try {
			sql = GenerateSql.generateCreateSql(fb, false);
		} catch (Exception e) {
			LoggerUtil.loggerDebug(logger, "反馈时生成sql出错:  " + e.getMessage());
			e.printStackTrace();
		}
		FeedBackDAL.getInstance().addFeedBack(sql);
	}
}
