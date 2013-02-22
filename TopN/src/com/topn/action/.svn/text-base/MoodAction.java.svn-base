package com.topn.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.action.help.CookieHelp;
import com.topn.action.help.FriendsOnlineHelp;
import com.topn.bean.Mood;
import com.topn.bean.PersonalInfo;
import com.topn.bean.ReplyMood;
import com.topn.bean.TO.FriendRecentMoodTO;
import com.topn.bean.TO.LeaveMsgTO;
import com.topn.bean.TO.RecentVisitorTO;
import com.topn.bean.TO.ReplyLeaveMsgTO;
import com.topn.bean.TO.ReplyTO;
import com.topn.bean.TO.TipsCountTO;
import com.topn.controller.FriendController;
import com.topn.controller.PersonalController;
import com.topn.util.StringUtil;
import com.topn.util.Table;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-29 下午02:02:55
 * 
 * 这里将所有涉及到心情的业务转移到此类
 */
public class MoodAction extends ActionSupport {

	// 自己心情分页用的
	private int pageNum = 1;
	
	// 留言的id
	//private ReplyLeaveMsgTO replyLeaveMsgTO;
	private int leaveMsgId;
	private int total;
	private int parentId;
	private String leaveMsg;
	
	private int replyLeaveMsgId;
	
	//发表心情
	private Mood mood;
	private ReplyMood replyMood;
	private int personId;
	private int moodId;
	
	//jason返回字符串
	private String msg;
	
	//查一个时间段的心情
	private String moodBeginTime;
	
	//回复id
	private int replyId;
	
	//删除某条回复时候用到的
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查询自己的心情
	 * 
	 * @return
	 */
	public String getMyMood() {
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(this.personId != pId && this.personId != 0){
			pId = personId;
		}
		int total = PersonalController.getInstance().getTotalOfMyMood(pId);
		ActionContext.getContext().put("sft", total);
		// 加载心情提醒
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(pid);

		ActionContext.getContext().put("tct", tct);
		// //加载好友申请提醒

		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(pid);
		ActionContext.getContext().put("rvt", rvt);
		return SUCCESS;
	}
	
	/**
	 * 分页查询的
	 * 
	 * @return
	 */
	public String getMyMoodPage() {
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(this.personId != pId && this.personId != 0){
			pId = personId;
		}
		List<FriendRecentMoodTO> frs = PersonalController.getInstance()
				.getMyMood(pId, pageNum, Table.MOOD_PAGE_SIZE);
		ActionContext.getContext().put("frs", frs);
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid,
				-1,true);
		ActionContext.getContext().put("pi", pi);
		return SUCCESS;
	}
	
	/**
	 * 转移到留言页面
	 * 
	 * @return
	 */
	public String toLeaveMsgs() {
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		int total = PersonalController.getInstance().getTotalOfLeaveMsg(pId);
		ActionContext.getContext().put("sft", total);
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(pid);

		ActionContext.getContext().put("tct", tct);
		// //加载好友申请提醒

		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(pid);
		ActionContext.getContext().put("rvt", rvt);
		return SUCCESS;
	}
	
	/**
	 * 获取留言
	 */
	public String getLeaveMsgs() {
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		List<LeaveMsgTO> frs = FriendController.getInstance().getLeaveMsgs(pId,
				pageNum);
		ActionContext.getContext().put("frs", frs);
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pId,-1,
				true);
		ActionContext.getContext().put("pi", pi);
		return SUCCESS;
	}
	
	/**
	 * 获取好友留言的所有回复
	 * 
	 * @return
	 */
	public String openLeaveMsgReplies() {
		if (0 == leaveMsgId) {
			return SUCCESS;
		}
		List<ReplyTO> rts = FriendController.getInstance().getReplyTO(
				leaveMsgId);
		ActionContext.getContext().put("rts", rts);
		return SUCCESS;
	}
	
	/**
	 * 发表留言
	 * 
	 * @return
	 */
	public String leaveMsg() {
		ReplyLeaveMsgTO replyLeaveMsgTO = new ReplyLeaveMsgTO();
		replyLeaveMsgTO.setLeaveMsg(leaveMsg);
		replyLeaveMsgTO.setParentId(parentId);
		replyLeaveMsgTO.setTotal(total);
		replyLeaveMsgTO.setLeaveMsgId(leaveMsgId);
		if (StringUtil.isBlank(replyLeaveMsgTO.getLeaveMsg())) {
			return SUCCESS;
		}
		if (replyLeaveMsgTO.getParentId() == 0)
			return SUCCESS;
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		replyLeaveMsgTO.setPublishId(pId);
		FriendController.getInstance().leaveMsg(replyLeaveMsgTO);
		ReplyTO rt = new ReplyTO();
		rt.setReplyDate("刚刚");
		rt.setReplyId(replyLeaveMsgTO.getReplyId());
		rt.setReplyMsg(replyLeaveMsgTO.getLeaveMsg());
		rt.setReplyPersonId(pId);
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pId,-1,
				true);
		rt.setReplyPersonName(pi.getNickName());
		rt.setReplyPersonURL(pi.getPhoto());
		List<ReplyTO> rts = new ArrayList<ReplyTO>();
		rts.add(rt);
		ActionContext.getContext().put("rts", rts);
		return SUCCESS;
	}
	
	/*
	 * 发表心情 json
	 */
	public String publishMood(){
		if(StringUtil.isBlank(mood.getMessage())){
			msg = "发表心情为空";
			return SUCCESS;
		}
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		mood.setPersonalId(pid);
		msg = "发表成功";
		FriendController.getInstance().publishMood(mood);
		if(mood.getMoodId() == 0){
			msg = "发表失败";
		}else{
			msg = String.valueOf(mood.getMoodId());
		}
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid, -1, true);
		List<FriendRecentMoodTO> frs = new ArrayList<FriendRecentMoodTO>();
		FriendRecentMoodTO fr = new FriendRecentMoodTO();
		fr.setMoodId(mood.getMoodId());
		fr.setMoodMsg(mood.getMessage());
		fr.setMoodPersonId(pid);
		fr.setMoodPersonName(pi.getNickName());
		fr.setMoodPersonURL(pi.getPhoto());
		fr.setMoodPublishTime("刚刚");
		fr.setTotalReply(0);
		frs.add(fr);
		ActionContext.getContext().put("frs", frs);
		ActionContext.getContext().put("pi", pi);
		return SUCCESS;
	}
	
	/**
	 * 加载心情
	 * @return
	 */
	public String loadMainMood(){
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		long currentTimestamp = System.currentTimeMillis();
		
		Calendar later = Calendar.getInstance(); 
		later.set(Calendar.DATE, later.get(Calendar.DATE) + 1);
		
		List<FriendRecentMoodTO> frs = FriendController.getInstance().getFriendRecentMood(pid, String.valueOf(later.getTimeInMillis()), 1);
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid, -1, true);
		ActionContext.getContext().put("pi", pi);
		ActionContext.getContext().put("moodBeginTime", currentTimestamp);
		ActionContext.getContext().put("frs", frs);
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(pid);
		ActionContext.getContext().put("tct", tct);
		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(pid);
		ActionContext.getContext().put("rvt", rvt);
		return SUCCESS;
	}


	/**
	 * 获取心情下面的所有回复
	 * @return
	 */
	public String getMoodAllReplies(){
		if(personId == 0 || moodId == 0){
			return LOGIN;
		}
		List<ReplyTO> rts = FriendController.getInstance().getMoodAllReplies(personId, moodId);
		ActionContext.getContext().put("rts", rts);
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid, -1, true);
		//ActionContext.getContext().put("rts", rts);
		ActionContext.getContext().put("pi", pi);
		return SUCCESS;
	}
	
	/**
	 * 获取我的心情下面的所有回复
	 * @return
	 */
	public String getMyMoodAllReplies(){
		if(personId == 0 || moodId == 0){
			return LOGIN;
		}
		List<ReplyTO> rts = FriendController.getInstance().getMoodAllReplies(personId, moodId);
		ActionContext.getContext().put("rts", rts);
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid, -1, true);
		ActionContext.getContext().put("pi", pi);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String reply(){
		if(StringUtil.isBlank(replyMood.getMessage())){
			msg = "回复为空";
			return SUCCESS;
		}
		if(replyMood.getMoodId() == 0
			|| replyMood.getMoodPersonId() == 0
			|| replyMood.getParentId() == 0){
			msg = "请刷新页面";
			return SUCCESS;
		}
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		replyMood.setPublisherId(pid);
		msg = "发表成功";
		FriendController.getInstance().replyMood(replyMood);
		FriendsOnlineHelp.sendToOnlineFriend(pid, replyMood.getParentId(), ServletActionContext.getRequest()
				, ServletActionContext.getResponse());
		msg = String.valueOf(replyMood.getReplyId());
		List<ReplyTO> rts = new ArrayList<ReplyTO>();
		ReplyTO rt = new ReplyTO();
		rt.setReplyDate("刚刚");
		rt.setReplyId(replyMood.getReplyId());
		rt.setReplyMsg(replyMood.getMessage());
		rt.setReplyPersonId(replyMood.getPublisherId());
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid, -1, true);
		rt.setReplyPersonName(pi.getNickName());
		rt.setReplyPersonURL(pi.getPhoto());
		rts.add(rt);
		ActionContext.getContext().put("rts", rts);
		ActionContext.getContext().put("pi", pi);
		return SUCCESS;
	}
	
	/**
	 * 回复心情 json
	 * 改了实现方式，这里采用统一页面处理
	 * @return
	 */
	public String replyMood(){
		
		return reply();
	}
	
	/**
	 * 
	 * @return
	 */
	public String replyMyMood(){
		return reply();
	}
	
	/**
	 * 加载n天后的心情
	 * @return
	 */
	public String loadNDayLaterMood(){
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		List<FriendRecentMoodTO> frs = FriendController.getInstance().getFriendRecentMood(pid, moodBeginTime, pageNum);
		ActionContext.getContext().put("frs", frs);
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid, -1, true);
		ActionContext.getContext().put("pi", pi);
		return SUCCESS;
	}
	
	/**
	 * 删除心情
	 * @return
	 */
	public String deleteReply(){
		if(0 == replyId || 0 == personId || 0 == moodId){
			msg = "删除失败";
			return LOGIN;
		}
		FriendController.getInstance().deleteReply(personId, moodId, replyId);
		msg = "删除成功";
		return SUCCESS;
	}
	
	public String deleteLeaveMsg() {
		if (0 == leaveMsgId) {
			return SUCCESS;
		}
		FriendController.getInstance().deleteLeaveMsg(leaveMsgId);
		return SUCCESS;
	}

	public String deleteReplyLeaveMsg() {
		if (0 == leaveMsgId || 0 == replyLeaveMsgId) {
			return SUCCESS;
		}
		FriendController.getInstance().deleteLeaveMsg(leaveMsgId,
				replyLeaveMsgId);
		return SUCCESS;
	}
	
	/**
	 * 删除心情
	 * @return
	 */
	public String deleteMood(){
		if(0 == moodId)return SUCCESS;
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		FriendController.getInstance().deleteMood(pid, moodId);
		return SUCCESS;
	}
	
	/**
	 * 获取游客进去之后心情
	 * @return
	 */
	public String getVisitorMood(){
		if(this.personId == 0){
			return "";
		}
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		PersonalInfo otherInfo = PersonalController.getInstance().getPersonById(personId, pid, false);
		ActionContext.getContext().put("pi", PersonalController.getInstance().getPersonById(pid, -1, true));
		ActionContext.getContext().put("otherInfo", otherInfo);
		List<FriendRecentMoodTO> frs2 = PersonalController.getInstance().getMyMood(personId, 1, Table.VISITOR_MOOD_PAGE_NUM);
		List<FriendRecentMoodTO> frs = FriendController.getInstance().getMyMsg2Friend(personId, pid);
		frs.addAll(frs2);
		Collections.sort(frs); 
		ActionContext.getContext().put("frs", frs);
		ActionContext.getContext().put("loginId", pid);
		
		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(personId);
		ActionContext.getContext().put("rvt", rvt);
		return SUCCESS;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setLeaveMsgId(int leaveMsgId) {
		this.leaveMsgId = leaveMsgId;
	}

	public void setReplyLeaveMsgId(int replyLeaveMsgId) {
		this.replyLeaveMsgId = replyLeaveMsgId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setMood(Mood mood) {
		this.mood = mood;
	}

	public void setReplyMood(ReplyMood replyMood) {
		this.replyMood = replyMood;
	}

	public void setMoodBeginTime(String moodBeginTime) {
		this.moodBeginTime = moodBeginTime;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public void setMoodId(int moodId) {
		this.moodId = moodId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getReplyLeaveMsgId() {
		return replyLeaveMsgId;
	}

	public ReplyMood getReplyMood() {
		return replyMood;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public void setLeaveMsg(String leaveMsg) {
		this.leaveMsg = leaveMsg;
	}
	
	
}
