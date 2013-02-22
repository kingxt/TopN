package com.topn.action;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.action.help.CookieHelp;
import com.topn.action.help.FriendsOnlineHelp;
import com.topn.bean.EntryForm;
import com.topn.bean.PersonalInfo;
import com.topn.bean.TO.FriendChatTO;
import com.topn.bean.TO.FriendTO;
import com.topn.bean.TO.RecentVisitorTO;
import com.topn.bean.TO.RecommendFriendTO;
import com.topn.bean.TO.TipsCountTO;
import com.topn.controller.FriendController;
import com.topn.controller.GeneratePictureController;
import com.topn.controller.PersonalController;
import com.topn.util.Criteria;
import com.topn.util.Restrictions;
import com.topn.util.StringUtil;
import com.topn.util.Table;

/**
 * 朋友管理类, 如果采用json方式返回字符串，那么最终返回的页面只能够是success
 * 如果采用的不是json，如果在cookie中找不到id就返回到登陆页面
 * 创建人 KingXt
 * 创建时间：2011-4-9 下午07:55:39
 */
public class FriendAction extends ActionSupport implements Table{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//下面一些属性是搜索条件
	private String nickname;
	private String hobby;
	private String email;
	private String school;
	private int sex;
	private String range;
	
	
	
	//jason返回字符串
	private String msg;
	
	//添加好友的id以及要添加的类型
	private int friendId;
	private int friendType = 2;
	//添加好友时候留言
	private String leaveMsg;
	//分页
	private int pageNum = 1;

	
	
	
	
	//标识是同意还是拒绝添加为好友
	private int flag;
	
	//一串id标识删除的用户
	private String delIds;
	
	public String friendSearch(){
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(pid);
		ActionContext.getContext().put("rvt", rvt);
		
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(pid);
		ActionContext.getContext().put("tct", tct);
		return SUCCESS;
	}
	
	/**
	 * 根据姓名搜索
	 * @return
	 */
	public String searchFriend(){
		if(StringUtil.isBlank(nickname)){
			return SUCCESS;
		}
		Criteria []cris = this.getCriterias();
		for (int i = 0; i < cris.length; i++) {
			cris[i].add(Restrictions.like("nick_name", nickname.trim()));
		}
		List<FriendTO> ftos = FriendController.getInstance().searchPerson(cris, pageNum);
		ActionContext.getContext().put("ftos", ftos);
		return SUCCESS;
	}
	
	/**
	 * 按照好友搜索总数
	 * @return
	 */
	public String searchCount(){
		if(StringUtil.isBlank(nickname)){
			return SUCCESS;
		}
		Criteria []cris = this.getCountCriteria("nick_name", nickname);
		msg = String.valueOf(FriendController.getInstance().getPersonCount(cris));
		return SUCCESS;
	}
	
	/**
	 * 按照email搜索好友的总数
	 * @return
	 */
	public String searchCountEmail(){
		if(StringUtil.isBlank(email) || !StringUtil.isValidEmail(email)){
			msg = "0";
			return SUCCESS;
		}
		Criteria []cris = this.getCountCriteria("user_name", email);
		msg = String.valueOf(FriendController.getInstance().getPersonCount(cris));
		return SUCCESS;
	}
	
	/**
	 * 按照school搜索总数
	 * @return
	 */
	public String searchCountSchool(){
		if(StringUtil.isBlank(school)){
			return SUCCESS;
		}
		Criteria []cris = null;
		if(StringUtil.isBlank(nickname)){
			cris = this.getCountCriteria(new String[]{"college", "high_school"}, new String[]{school, school});
		}else{
			this.getCountCriteria(new String[]{"college", "high_school", "nick_name"}, new String[]{school, school, nickname});
		}
		
		msg = String.valueOf(FriendController.getInstance().getPersonCount(cris));
		return SUCCESS;
	}
	
	/**
	 * 按照hobby搜索总数
	 * @return
	 */
	public String searchCountHobby(){
		if(StringUtil.isBlank(hobby)){
			return SUCCESS;
		}
		Criteria []cris = this.getCountCriteria("hobby", hobby);
		msg = String.valueOf(FriendController.getInstance().getPersonCount(cris));
		return SUCCESS;
	}
	
	/**
	 * 获得模糊查询的条件
	 * @param property
	 * @param con
	 * @return
	 */
	public Criteria [] getCountCriteria(String property, String con){
		Criteria []cris = this.getCountCriterias();
		for (int i = 0; i < cris.length; i++) {
			cris[i].add(Restrictions.like(property, con.trim()));
		}
		return cris;
	}
	
	/**
	 * 获得模糊查询的条件
	 * @param property
	 * @param con
	 * @return
	 */
	public Criteria [] getCountCriteria(String property[], String con[]){
		Criteria []cris = this.getCountCriterias();
		for (int i = 0; i < cris.length; i++) {
			for (int j = 0; j < property.length; j++) {
				if(j == 0){
					cris[i].add(Restrictions.like(property[j], con[j].trim()));
				}else{
					cris[i].add(Restrictions.likeOR(property[j], con[j].trim()));
				}				
			}			
		}
		return cris;
	}
	
	/**
	 * 按照兴趣搜索
	 * @return
	 */
	public String searchWithHobby(){
		if(StringUtil.isBlank(hobby)){
			return SUCCESS;
		}
		Criteria []cris = this.getCriterias();
		for (int i = 0; i < cris.length; i++) {
			cris[i].add(Restrictions.like("hobby", hobby.trim()));
		}
		List<FriendTO> ftos = FriendController.getInstance().searchPerson(cris, pageNum);
		ActionContext.getContext().put("ftos", ftos);
		return SUCCESS;
	}
	
	/**
	 * 按照邮箱搜索
	 * @return
	 */
	public String searchWithEmail(){
		if(StringUtil.isBlank(email) || !StringUtil.isValidEmail(email)){
			return SUCCESS;
		}
		Criteria []cris = this.getCriterias();
		for (int i = 0; i < cris.length; i++) {
			cris[i].add(Restrictions.like("user_name", email.trim()));
		}
		List<FriendTO> ftos = FriendController.getInstance().searchPerson(cris, pageNum);
		ActionContext.getContext().put("ftos", ftos);
		return SUCCESS;
	}
	

	/**
	 * 按照学校搜索
	 * @return
	 */
	public String searchWithSchool(){
		if(StringUtil.isBlank(school)){
			return SUCCESS;
		}
		Criteria []cris = this.getCriterias();
		for (int i = 0; i < cris.length; i++) {
			cris[i].add(Restrictions.like("college", school.trim()));
			cris[i].add(Restrictions.likeOR("high_school", school.trim()));
			if(StringUtil.isNotBlank(nickname)){
				cris[i].add(Restrictions.likeOR("nick_name", nickname.trim()));
			}			
		}
		List<FriendTO> ftos = FriendController.getInstance().searchPerson(cris, pageNum);
		ActionContext.getContext().put("ftos", ftos);
		return SUCCESS;
	}
	
	/**
	 * 拿到count模板
	 * @return
	 */
	private Criteria [] getCountCriterias(){
		Criteria []cris = new Criteria[5];
		String args[] = new String[]{"count(*)"};
		cris[0] = new Criteria(PERSON_INFO_ONE, args);
		cris[1] = new Criteria(PERSON_INFO_TWO, args);
		cris[2] = new Criteria(PERSON_INFO_THREE, args);
		cris[3] = new Criteria(PERSON_INFO_FOUR, args);
		cris[4] = new Criteria(PERSON_INFO_FIVE, args);
		return cris;
	}
	
	
	private Criteria [] getCriterias(){
		Criteria []cris = new Criteria[5];
		String args[] = new String[]{"person_info_id","nick_name", "college", "high_school", "photo_char", "hobby", "ps"};
		cris[0] = new Criteria(PERSON_INFO_ONE, args);
		cris[1] = new Criteria(PERSON_INFO_TWO, args);
		cris[2] = new Criteria(PERSON_INFO_THREE, args);
		cris[3] = new Criteria(PERSON_INFO_FOUR, args);
		cris[4] = new Criteria(PERSON_INFO_FIVE, args);
		return cris;
	}
	
	/**
	 * 添加好友 json
	 * @return
	 */
	public String addFriend(){
		if(0 == friendId){
			return SUCCESS;
		}
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(0 == pid){
			msg = "重新登录";
			return SUCCESS;
		}
		if(pid == friendId){
			msg = "添加自己为好友了!!!";
			return SUCCESS;
		}
		String re = FriendController.getInstance().addFriend(pid, friendId, friendType, leaveMsg);
		if(StringUtil.isBlank(re)){
			msg = "您的申请已发送给对方，等待好友的验证";
			
		}
		msg = re;
		return SUCCESS;
	}
	
	
	
	/**
	 * 管理好友
	 * @return
	 */
	public String manageFriend(){
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(0 == pid){
			msg = "重新登录";
			return LOGIN;
		}
		int total = FriendController.getInstance().getFriendCount(pid, friendType);
		List<FriendTO> sft = FriendController.getInstance().getFriendsByType(pid, friendType, pageNum);
		ActionContext.getContext().put("sft", sft);
		ActionContext.getContext().put("total", total);
		if(friendType == 1){
			ActionContext.getContext().put("friendType", "1");
		}else if(friendType == 2){
			ActionContext.getContext().put("friendType", "2");
		}
		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(pid);
		ActionContext.getContext().put("rvt", rvt);
		
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(pid);
		ActionContext.getContext().put("tct", tct);
		return SUCCESS;
	}
	
	/**
	 * 分页加载
	 * @return
	 */
	public String loadFriendByType(){
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(0 == pid){
			msg = "重新登录";
			return LOGIN;
		}
		List<FriendTO> sft = FriendController.getInstance().getFriendsByType(pid, friendType, pageNum);
		ActionContext.getContext().put("sft", sft);
		if(friendType == 1){
			ActionContext.getContext().put("friendType", "1");
		}else if(friendType == 2){
			ActionContext.getContext().put("friendType", "2");
		}
		return SUCCESS;
	}
	
	/**
	 * 删除好友
	 * @return
	 */
	public String deleteFriend(){
		if(0 == friendId){
			return SUCCESS;
		}
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(0 == pid){
			msg = "重新登录";
			return LOGIN;
		}
		FriendController.getInstance().deleteFriend(pid, friendId);
		return SUCCESS;
	}
	
	/**
	 * 修改好友类型
	 */
	public String modifyFriendType(){
		if(0 == friendId){
			return SUCCESS;
		}
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(0 == pid){
			msg = "重新登录";
			return LOGIN;
		}
		FriendController.getInstance().modifyFriendType(pid, friendId, friendType);
		return SUCCESS;
	}
	
	/**
	 * 添加好友（有两种情况）
	 * @return
	 */
	public String realAddFriend(){
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(0 == pid){
			msg = "重新登录";
			return LOGIN;
		}
		if(friendId == 0 || flag == 0){
			msg = "重新登录";
			return SUCCESS;
		}
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid,-1,
				true);
		FriendController.getInstance().realAddFriend(pid, friendId, pi.getNickName(), friendType, flag);
		return SUCCESS;
	}
	
	/**
	 * 删除若干过好友申请
	 * 
	 * @return
	 */
	public String deleteAllSelectedFriend(){
		if(StringUtil.isBlank(delIds))return SUCCESS;
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid,-1,
				true);
		if(0 == pid){
			msg = "重新登录";
			return LOGIN;
		}
		String []ids = delIds.split(" ");
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			FriendController.getInstance().realAddFriend(pid, Integer.parseInt(id), pi.getNickName(), friendType, flag);
		}
		return SUCCESS;
	}
	
	
	
	/**
	 * 及时通讯用到的
	 * 加载自己的所有好友,这里 应该采用json方式返回
	 * @return
	 */
	public String getAllMyFriend(){
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(0 == pid){
			msg = "重新登录";
			return LOGIN;
		}
		List<FriendChatTO> fto = FriendController.getInstance().getFriendChatTO(pid);
		FriendsOnlineHelp.dealFriendsOnline(fto);
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid, -1, true);
		FriendChatTO fct = new FriendChatTO();
		fct.setId(pi.getPersonalInfoId());
		fct.setName(pi.getNickName());
		fct.setType(3);
		fct.setUrl(pi.getPhoto());
		fto.add(fct);
		fct.setIsOnline(1);
		JSONArray ja = JSONArray.fromObject(fto);
		this.msg = ja.toString();
		return SUCCESS;
	}
	
	/**
	 * 按照topn排名搜索
	 * @return
	 */
	public String searchForTopN(){
		if(StringUtil.isBlank(range)){
			
			return SUCCESS;
		}
		this.sex = (this.sex == 0 ? 2:this.sex);
		String rangeS[] = range.split(" ");
		if(rangeS.length < 0){
			return SUCCESS;
		}
		
		
		List<EntryForm> efs = null;
		if(rangeS.length == 1 && StringUtil.isNumber(rangeS[0].trim())){	
			int temp = Integer.parseInt(rangeS[0]);
			if(temp <= 1){
				temp = 1;
			}
			efs = GeneratePictureController.getInstance().getTopN(temp-1, temp, sex);
		}else if(rangeS.length == 2 && StringUtil.isNumber(rangeS[0].trim()) && StringUtil.isNumber(rangeS[1].trim())){
			int temp = Integer.parseInt(rangeS[0].trim());
			if(temp < 1){
				temp = 1;
			}
			efs = GeneratePictureController.getInstance().getTopN(temp -1, Integer.parseInt(rangeS[1].trim()), sex); 
		}
		if(null == efs){
			return SUCCESS;
		}
		int len = efs.size();
		List<FriendTO> ftos = new ArrayList<FriendTO>();
		for (int i = 0; i < len; i++) {
			FriendTO fr = new FriendTO();
			EntryForm ef = efs.get(i);
			PersonalInfo pi = PersonalController.getInstance().getPersonById(ef.getPersonInfoId(), ef.getPersonInfoId(), true);
			fr.setCollege(pi.getCollege());
			fr.setHighSchool(pi.getHighSchool());
			fr.setHobby(pi.getHobby());
			fr.setId(ef.getPersonInfoId());
			fr.setName(pi.getNickName());
			fr.setPs(pi.getPs());
			fr.setUrl(pi.getPhoto());
			ftos.add(fr);
		}
		ActionContext.getContext().put("ftos", ftos);
		return SUCCESS;
	}
	
	/**
	 * 获取好友推荐
	 * @return
	 */
	public String friendRecommendation(){
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if (0 == pid) {
			return LOGIN;
		}
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid, -1,
				true);
		if(StringUtil.isNotBlank(pi.getCollege())){
			List<RecommendFriendTO> coto = FriendController.getInstance().getRecommendFriendTO(pid, pi.getCollege(), 1);
			ActionContext.getContext().put("coto", coto);
		}
		if(StringUtil.isNotBlank(pi.getHighSchool())){
			List<RecommendFriendTO> hito = FriendController.getInstance().getRecommendFriendTO(pid, pi.getHighSchool(), 2);
			ActionContext.getContext().put("hito", hito);
		}
		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(pid);
		ActionContext.getContext().put("rvt", rvt);
		
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(pid);
		ActionContext.getContext().put("tct", tct);
		return SUCCESS;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	

	public void setFriendType(int friendType) {
		this.friendType = friendType;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setRange(String range) {
		this.range = range;
	}

	
	
	
}
