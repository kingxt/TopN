package com.topn.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.bean.Expression;
import com.topn.controller.CDDController;

public class CDDAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String loadExpression(){
		List<Expression> es = CDDController.getInstance().getAllExpression();
		
		ActionContext.getContext().put("es", es);
		
		return SUCCESS;
	}
}
