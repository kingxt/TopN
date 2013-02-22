package com.topn.DAL;

import com.topn.util.SqlExecuteUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-5-31 下午10:43:14
 * 
 * 用户反馈
 */
public class FeedBackDAL extends BaseDAL {

	private static FeedBackDAL instance = new FeedBackDAL();

	private FeedBackDAL() {
	}
	
	public static FeedBackDAL getInstance(){
		return instance;
	}
	
	public void addFeedBack(String sql){
		SqlExecuteUtil.executeNoResultSql(sql);
	}
}
