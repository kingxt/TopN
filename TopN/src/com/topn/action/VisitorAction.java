package com.topn.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.action.help.CookieHelp;
import com.topn.bean.PersonalInfo;
import com.topn.bean.TO.FriendRecentMoodTO;
import com.topn.bean.TO.FriendTO;
import com.topn.bean.TO.RecentVisitorTO;
import com.topn.controller.FriendController;
import com.topn.controller.PersonalController;
import com.topn.util.StringUtil;
import com.topn.util.Table;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-23 下午02:26:35
 * 
 * 别人访问自己的action
 */
public class VisitorAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//jason返回字符串
	private String msg;
	
	private int personalInfoId;
	private int inRanking;
	
	private int pageNum = 1;
	
	//游客留言
	private String leaveMsg;
	private int leaveTo;

	/**
	 * 别人访问时候加载信息
	 * 这里要做一个处理就是，如果那个人在排行榜上就要缓存起来
	 * @return
	 */
	public String visitOther(){
		if(0 == personalInfoId){
			return null;
		}
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(pid == personalInfoId){
			return "tomyhome";
		}
		
		if(0 == inRanking){
			PersonalInfo otherInfo = PersonalController.getInstance().getPersonById(personalInfoId, pid, false);
			ActionContext.getContext().put("pi", PersonalController.getInstance().getPersonById(pid, -1, true));
			ActionContext.getContext().put("otherInfo", otherInfo);
			List<FriendRecentMoodTO> frs2 = PersonalController.getInstance().getMyMood(personalInfoId, 1, Table.VISITOR_MOOD_PAGE_NUM);
			List<FriendRecentMoodTO> frs = FriendController.getInstance().getMyMsg2Friend(personalInfoId, pid);
			frs.addAll(frs2);
			Collections.sort(frs); 
			ActionContext.getContext().put("frs", frs);
			ActionContext.getContext().put("loginId", pid);
			
			//获取最近访客
			List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(personalInfoId);
			ActionContext.getContext().put("rvt", rvt);
			return SUCCESS;
		}
		//CacheRankPersonTO ct = PersonalController.getInstance().getRankPerson(personalInfoId);
		//ActionContext.getContext().put("ct", ct);
		return SUCCESS;
	}
	
	public String visitorsFriends(){
		if(0 == personalInfoId){
			return null;
		}
		int total = FriendController.getInstance().getFriendCount(personalInfoId, -1);
		if(total > 0){
			List<FriendTO> sft = FriendController.getInstance().getFriendFriends(personalInfoId, pageNum);
			ActionContext.getContext().put("total", total);
			ActionContext.getContext().put("ftos", sft);
		}
		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(personalInfoId);
		ActionContext.getContext().put("rvt", rvt);
		return SUCCESS;
		
	}

	public String visitorsFriendsPG(){
		if(0 == personalInfoId){
			return null;
		}
		List<FriendTO> sft = FriendController.getInstance().getFriendFriends(personalInfoId, pageNum);
		ActionContext.getContext().put("ftos", sft);
		
		return "pg";
	
	}
	/**
	 * 获取最近访客
	 * @return
	 */
	public String getMyRecentVisitor(){
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(0 == pid){
			return LOGIN;
		}
		
		return SUCCESS;
	}
	
	public String visitorLeaveMsg(){
		if(this.leaveTo == 0 || StringUtil.isBlank(leaveMsg)){
			msg = "留言失败,请重新登录";
			return SUCCESS; 
		}

		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pId, -1, true);
		int lmgId = FriendController.getInstance().leaveMsg(pId, leaveTo, leaveMsg);
		List<FriendRecentMoodTO> frs = new ArrayList<FriendRecentMoodTO>();
		FriendRecentMoodTO fr = new FriendRecentMoodTO();
		fr.setMoodId(lmgId);
		fr.setMoodMsg(leaveMsg);
		fr.setMoodPersonId(pId);
		fr.setMoodPersonName(pi.getNickName());
		fr.setMoodPersonURL(pi.getPhoto());
		fr.setMoodPublishTime("刚刚");
		fr.setTotalReply(0);
		frs.add(fr);
		ActionContext.getContext().put("frs", frs);
		ActionContext.getContext().put("pi", pi);
		ActionContext.getContext().put("loginId", pId);
		return SUCCESS;
	}

	public void setPersonalInfoId(int personalInfoId) {
		this.personalInfoId = personalInfoId;
	}

	public void setInRanking(int inRanking) {
		this.inRanking = inRanking;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setLeaveMsg(String leaveMsg) {
		this.leaveMsg = leaveMsg;
	}

	public void setLeaveTo(int leaveTo) {
		this.leaveTo = leaveTo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getPersonalInfoId() {
		return personalInfoId;
	}

	
}
