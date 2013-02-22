package com.topn.controller;

import java.util.List;

import com.topn.DAL.query.CDDQuery;
import com.topn.bean.Expression;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-5-11 下午12:58:56
 * 
 * 
 */
public class CDDController {

	private static CDDController instance = new CDDController();

	private CDDController() {
	}

	public static CDDController getInstance() {
		return instance;
	}
	
	/**
	 * 获取所有的表情
	 * @return
	 */
	public List<Expression> getAllExpression() {
		
		return CDDQuery.getInstance().getAllExpression();
	}
	
}
