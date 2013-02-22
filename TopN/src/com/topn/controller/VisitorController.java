package com.topn.controller;

import com.topn.DAL.query.PersonalInfoQuery;
import com.topn.bean.PersonalInfo;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-27 上午11:19:47
 * 
 * 别人访问时候的逻辑控制层
 */
public class VisitorController {

	private static VisitorController instance = new VisitorController();

	private VisitorController() {
	}

	public static VisitorController getInstance() {
		return instance;
	}
	
	/**
	 * 控制层，根据id查询
	 * @param id
	 * @return
	 */
	public PersonalInfo getPersonById(int id, int myId){
		if(id <= 0) return null;
		return PersonalInfoQuery.getInstance().getPersonById(id, myId);
	}
}
