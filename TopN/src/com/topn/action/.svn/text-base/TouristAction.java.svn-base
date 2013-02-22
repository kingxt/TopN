package com.topn.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.action.help.CookieHelp;
import com.topn.bean.PersonalInfo;
import com.topn.bean.TO.PermissionsTO;
import com.topn.bean.TO.RankTO;
import com.topn.controller.GeneratePictureController;
import com.topn.controller.PersonalController;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-6-1 下午04:48:08
 * 
 * 游客访问
 */
public class TouristAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int personalInfoId;
	
	private int rank;
	
	/**
	 * 访问刚打完分的人的页面
	 * @return
	 */
	public String tourist(){
		if(0 == personalInfoId){
			return LOGIN;
		}
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(personalInfoId == pId){
			return "tomyhome";
		}
		int temp = PersonalController.getInstance().isMyFriend(pId, personalInfoId);
		if(temp == 2){
			return "direct2friends";
		}
		PersonalInfo pi = PersonalController.getInstance().getPersonById(personalInfoId, personalInfoId, true, false);
		ActionContext.getContext().put("pi", pi);
		ActionContext.getContext().put("per",
				PermissionsTO.string2PermissionsTO(pi.getPermission()));
		RankTO rt = GeneratePictureController.getInstance().getMyRankPos(personalInfoId);
		ActionContext.getContext().put("rank", rt);
		return SUCCESS;
	}
	
	/**
	 * 排行榜上的人物做特许处理，要缓存
	 * @return
	 */
	public String touristRank(){
		if(0 == personalInfoId){
			return LOGIN;
		}
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(personalInfoId == pId){
			return "tomyhome";
		}
		int temp = PersonalController.getInstance().isMyFriend(pId, personalInfoId);
		if(temp == 2){
			return "direct2friends";
		}
		if(0 == rank){
			return this.tourist();
		}
		PersonalInfo pi = PersonalController.getInstance().getPersonById(personalInfoId, personalInfoId, true, true);
		ActionContext.getContext().put("pi", pi);
		ActionContext.getContext().put("per",
				PermissionsTO.string2PermissionsTO(pi.getPermission()));
		RankTO rt = GeneratePictureController.getInstance().getMyRankPos(personalInfoId);
		ActionContext.getContext().put("rank", rt);
		ActionContext.getContext().put("rankURL", rank);
		return SUCCESS;
	}

	public void setPersonalInfoId(int personalInfoId) {
		this.personalInfoId = personalInfoId;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPersonalInfoId() {
		return personalInfoId;
	}
	
}
