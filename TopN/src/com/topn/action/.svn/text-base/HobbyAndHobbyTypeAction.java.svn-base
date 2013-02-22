package com.topn.action;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.action.help.CookieHelp;
import com.topn.bean.PersonalInfo;
import com.topn.controller.HobbyAndHobbyTypeController;
import com.topn.controller.PersonalController;

public class HobbyAndHobbyTypeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int typeId;
	private String hobby;
	private String otherHobby;
	
	public String loadHobbyType(){
		ActionContext.getContext().put("hts", HobbyAndHobbyTypeController.getInstance().loadHobbyTypse());
		
		return SUCCESS;
	}
	
	public String loadHobby(){
		ActionContext.getContext().put("hb", HobbyAndHobbyTypeController.getInstance().loadHobbyByType(typeId));
		return SUCCESS;
	}
	
	/**
	 * 初始化界面
	 * @return
	 */
	public String initFace(){
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if (0 == pId) {
			return LOGIN;
		}
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pId,-1,
				true);
		ActionContext.getContext().put("pi", pi);
		ActionContext.getContext().put("hts", HobbyAndHobbyTypeController.getInstance().loadHobbyTypse());
		ActionContext.getContext().put("hb", HobbyAndHobbyTypeController.getInstance().loadHobbyByType(1));
		return SUCCESS;
	}
	
	/**
	 * 更新兴趣
	 * @return
	 */
	public String updateHobby(){
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(0 == pid){
			return LOGIN;
		}
		//拿到缓存并更新缓存
		PersonalInfo cachePi = PersonalController.getInstance().getPersonById(pid, -1, true);
		cachePi.setHobby(hobby);
		cachePi.setOtherHobby(otherHobby);
		PersonalController.getInstance().updateCache(cachePi);
		HobbyAndHobbyTypeController.getInstance().updateHobby(pid, hobby, otherHobby);
		return null;
	}
	

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public void setOtherHobby(String otherHobby) {
		this.otherHobby = otherHobby;
	}
	
	
}
