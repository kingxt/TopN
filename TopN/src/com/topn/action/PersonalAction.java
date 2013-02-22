package com.topn.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.action.help.CookieHelp;
import com.topn.bean.EntryForm;
import com.topn.bean.PersonalInfo;
import com.topn.bean.TO.FriendAddTipsTO;
import com.topn.bean.TO.FriendRecentMoodTO;
import com.topn.bean.TO.LeaveMsgTipsPageTO;
import com.topn.bean.TO.LoginTO;
import com.topn.bean.TO.MoodTipsPageTO;
import com.topn.bean.TO.PermissionsTO;
import com.topn.bean.TO.RankTO;
import com.topn.bean.TO.RecentVisitorTO;
import com.topn.bean.TO.RecommendFriendTO;
import com.topn.bean.TO.SystemTipsTO;
import com.topn.bean.TO.TipsCountTO;
import com.topn.cache.MemCache;
import com.topn.cache.MemCacheProvider;
import com.topn.collection.PicturePartition;
import com.topn.collection.UserInRegister;
import com.topn.controller.FriendController;
import com.topn.controller.GeneratePictureController;
import com.topn.controller.PersonalController;
import com.topn.util.DateUitl;
import com.topn.util.Object2XMLUtil;
import com.topn.util.StringUtil;
import com.topn.util.Table;

/**
 * 创建人 xutao 创建时间 2011-3-17 下午11:23:05 个人信息页面交互ACTION
 */
public class PersonalAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户基本信息 JSON类型
	private String personInfo;

	// 用户相册信息 JSON类型
	private String personAlbum;

	private PersonalInfo pi;
	private PermissionsTO per;

	private int personalInfoId;

	// 打开菜单时候
	private int tipSelectedItem;

	// 系统提示的id
	private String tipsIdStr;
	
	// 密码
	private String password;

	// 确认密码
	private String confirmPassword;
	
	// json
	private String msg;

	public String getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(String personInfo) {
		this.personInfo = personInfo;
	}

	public String getPersonAlbum() {
		return personAlbum;
	}

	public void setPersonAlbum(String personAlbum) {
		this.personAlbum = personAlbum;
	}

	/**
	 * 转到去个人主页
	 * 
	 * @return
	 */
	public String toHomePage() {
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		long currentTimestamp = System.currentTimeMillis();
		// 获取第一页的心情
		
		Calendar later = Calendar.getInstance(); 
		later.set(Calendar.DATE, later.get(Calendar.DATE) + 1);
		
		List<FriendRecentMoodTO> frs = FriendController.getInstance().getFriendRecentMood(pid, String.valueOf(later.getTimeInMillis()), 1);
	
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pid, -1,
				true);
		ActionContext.getContext().put("frs", frs);
		ActionContext.getContext().put("moodBeginTime", currentTimestamp);// 获取时间要的时间戳
		// 加载心情提醒
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(pid);

		ActionContext.getContext().put("tct", tct);
		// //加载好友申请提醒

		ActionContext.getContext().put("pi", pi);

		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(pid);
		ActionContext.getContext().put("rvt", rvt);
		
		RankTO rank = GeneratePictureController.getInstance().getMyRankPos(pid);
		ActionContext.getContext().put("rank", rank);
		int delta = DateUitl.deltaNumDay2Now(pi.getRegisterTime());
		//下面是获取好友推荐
		
		if(delta < Table.USER_RECOMMEND_FRIEND_DAY){
			if(frs.size() == 0){
				int total = 0;
				if(StringUtil.isNotBlank(pi.getCollege())){
					List<RecommendFriendTO> coto = FriendController.getInstance().getRecommendFriendTO(pid, pi.getCollege(), 1);
					total += coto.size();
					ActionContext.getContext().put("coto", coto);
				}
				if(StringUtil.isNotBlank(pi.getHighSchool())){
					List<RecommendFriendTO> hito = FriendController.getInstance().getRecommendFriendTO(pid, pi.getHighSchool(), 2);
					total += hito.size();
					ActionContext.getContext().put("hito", hito);
				}
				if(total == 0){
					return SUCCESS;
				}
					
				return "friendrecommend";
			}
		}		
		return SUCCESS;
	}

	/**
	 * 从缓存中获取用户的基本信息
	 * 
	 * @return json类型的用户信息
	 */
	public String getPersonalBasicInfo() {
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		String personId = "";
		for (Cookie c : cookies) {
			if (c.getName().equals("id")) {
				personId = c.getValue();
				break;
			}
		}
		MemCache mc = MemCacheProvider.getInstance().buildCache(
				MemCacheProvider.MEMCACHE_TYPE_USER);
		PersonalInfo pi = (PersonalInfo) mc.get(personId);
		JSONObject jo = JSONObject.fromObject(pi);
		personInfo = jo.toString();

		return SUCCESS;
	}

	/**
	 * 更新用户信息，这里首先应该是查出数据
	 * 
	 * @return
	 */
	public String showBaseInfo() {
		// 首先显示给用户基本信息
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		PersonalInfo pi = null;
		int accessPersonId = 0;
		if(this.personalInfoId != 0 && this.personalInfoId != pid){
			//访问好友的个人信息
			accessPersonId = personalInfoId;
			pi = PersonalController.getInstance().getPersonById(personalInfoId,-1,
					true);
		}else{
			accessPersonId = pid;
			pi = PersonalController.getInstance().getPersonById(pid,-1,
					true);
		}
		
		ActionContext.getContext().put("pi", pi);
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(accessPersonId);
		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(accessPersonId);
		ActionContext.getContext().put("rvt", rvt);
		ActionContext.getContext().put("tct", tct);
		ActionContext.getContext().put("per",
				PermissionsTO.string2PermissionsTO(pi.getPermission()));
		if(this.personalInfoId != 0 && this.personalInfoId != pid){
			return "othersBaseInfo";
		}else{
			return SUCCESS;
		}
		
	}

	/**
	 * 更新初始输入操作
	 * 
	 * @return
	 */
	public String updateBaseInfoInput() {
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pId,-1,
				true);
		ActionContext.getContext().put("pi", pi);
		ActionContext.getContext().put("per",
				PermissionsTO.string2PermissionsTO(pi.getPermission()));

		return SUCCESS;
	}
	
	/**
	 * 更新输入操作
	 * 
	 * @return
	 */
	public String baseInfoInput() {
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pId,-1,
				true);
		ActionContext.getContext().put("pi", pi);
		ActionContext.getContext().put("per",
				PermissionsTO.string2PermissionsTO(pi.getPermission()));

		return SUCCESS;
	}

	/**
	 * 更新基本信息至数据库
	 * 
	 * @return
	 */
	public String updatePersonalInfo() {
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		String username = CookieHelp.getUsername(ServletActionContext
				.getRequest());
		if (StringUtil.isBlank(username)) {
			return LOGIN;
		}
		pi.setPersonalInfoId(pId);
		pi.setTableName(UserInRegister.getTableName(username));
		PersonalInfo cachePi = PersonalController.getInstance().getPersonById(pId, -1,
				true);
		PermissionsTO pto = PermissionsTO.string2PermissionsTO(cachePi
				.getPermission());
		pto.setPerAddress(per.getPerAddress());
		pto.setPerBirthday(per.getPerBirthday());
		pto.setPerMsn(per.getPerMsn());
		pto.setPerPhone(per.getPerPhone());
		pto.setPerQQ(per.getPerQQ());
		
		pi.setPermission(PermissionsTO.permission2String(pto));
		PersonalController.getInstance().updatePersonalInfo(pi);
		//更新缓存
		PersonalController.getInstance().getPersonById(pId, -1, false);
		return null;
	}

	public String toTips() {
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		ActionContext.getContext().put("tipSelectedItem", tipSelectedItem);
		// 查找好友申请提示
		if (tipSelectedItem == 1) {
			List<FriendAddTipsTO> fat = PersonalController.getInstance()
					.getFriendAddTipsTO(pId);
			ActionContext.getContext().put("fat", fat);
		} else if (tipSelectedItem == 2) {
			List<SystemTipsTO> stt = PersonalController.getInstance()
					.getSystemTipsTO(pId);
			// 标识哪一个打开状态
			ActionContext.getContext().put("stt", stt);
		} else if (tipSelectedItem == 3) {
			// 标识哪一个打开状态
			List<MoodTipsPageTO> mtpt = PersonalController.getInstance()
					.getMoodTipsTO(pId);
			ActionContext.getContext().put("mtpt", mtpt);
			PersonalInfo pi = PersonalController.getInstance().getPersonById(
					pId, -1,true);
			ActionContext.getContext().put("pi", pi);
			return "moodTips";
		} else if (tipSelectedItem == 4) {
			List<LeaveMsgTipsPageTO> lmpt = PersonalController.getInstance()
					.getLeaveMsgTipsTO(pId);
			ActionContext.getContext().put("lmpt", lmpt);
			PersonalInfo pi = PersonalController.getInstance().getPersonById(
					pId,-1, true);
			ActionContext.getContext().put("pi", pi);
			return "leaveMsgTips";
		}
		return SUCCESS;
	}

	/**
	 * 加载提醒
	 * 
	 * @return
	 */
	public String loadTips() {
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		ActionContext.getContext().put("tipSelectedItem", tipSelectedItem);
		// 查找好友申请提示
		if (tipSelectedItem == 1) {
			List<FriendAddTipsTO> fat = PersonalController.getInstance()
					.getFriendAddTipsTO(pId);
			ActionContext.getContext().put("fat", fat);
			return "friendTip";
		} else if (tipSelectedItem == 2) {
			List<SystemTipsTO> stt = PersonalController.getInstance()
					.getSystemTipsTO(pId);
			// 标识哪一个打开状态
			ActionContext.getContext().put("stt", stt);
			return "systemTip";
		} else if (tipSelectedItem == 3) {
			// 标识哪一个打开状态
			List<MoodTipsPageTO> mtpt = PersonalController.getInstance()
					.getMoodTipsTO(pId);
			ActionContext.getContext().put("mtpt", mtpt);
			PersonalInfo pi = PersonalController.getInstance().getPersonById(
					pId, -1, true);
			ActionContext.getContext().put("pi", pi);
			return "moodTip";
		} else if (tipSelectedItem == 4) {
			List<LeaveMsgTipsPageTO> mtpt = PersonalController.getInstance()
					.getLeaveMsgTipsTO(pId);
			ActionContext.getContext().put("mtpt", mtpt);
			PersonalInfo pi = PersonalController.getInstance().getPersonById(
					pId, -1,true);
			ActionContext.getContext().put("pi", pi);
			return "leaveMsgTip";
		}
		return SUCCESS;
	}

	/**
	 * 删除操作,这里包括删除一条记录或者删除多条记录
	 * 
	 * @return
	 */
	public String delSystemTips() {
		if (StringUtil.isBlank(tipsIdStr)) {
			return SUCCESS;
		}
		String[] temp = tipsIdStr.split(" ");
		for (int i = 0; i < temp.length; i++) {
			PersonalController.getInstance().delSystemTips(
					Integer.parseInt(temp[i]));
		}
		return SUCCESS;
	}	
	
	/**
	 * 转到修改密码页面
	 * 
	 * @return
	 */
	public String modifyPwdInput() {
		return SUCCESS;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String modifyPassword() {
		if (!StringUtil.equals(password, confirmPassword)) {
			msg = "两次密码输入不一致";
			return SUCCESS;
		}
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		PersonalController.getInstance().modifyPassword(pId, password);
		msg = "修改成功";
		return SUCCESS;
	}
	
	/**
	 * 返回topn
	 * @return
	 */
	public String toTopN(){
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		LoginTO lto = PersonalController.getInstance().back2TopN(pId);
		
		PersonalInfo pi = PersonalController.getInstance().getPersonById(
				pId, -1,true);
		lto.setName(pi.getNickName());
		lto.setPersonalInfoId(pi.getPersonalInfoId());
		lto.setSex(pi.getSex());
		int sex = lto.getSex() == 1? 2:1;
		//查询排行榜
		List<EntryForm> efs = GeneratePictureController.getInstance().getTopN(0, 10, sex);
		
		if(efs != null && efs.size() > 0){
			String xmlList = Object2XMLUtil.list2XML(efs, EntryForm.class);
			lto.setRankingList(xmlList);
		}
		
		//随机获取三张照片
		EntryForm ef1 = GeneratePictureController.getInstance().getPictureBySexAndAge(sex, PicturePartition.P_16_25);
		if(ef1 != null){
			EntryForm ef2 = GeneratePictureController.getInstance().getPictureBySexAndAge(sex, PicturePartition.P_16_25);
			if(ef2 != null && ef1 != ef2){
				EntryForm ef3 = GeneratePictureController.getInstance().getPictureBySexAndAge(sex, PicturePartition.P_16_25);
				if(ef3 != null && ef3 != ef2){
					lto.setInitPicURL(Object2XMLUtil.array2XML(new EntryForm[]{ef1, ef2, ef3}, EntryForm.class));
				}else{//由于从数据库中只能够查出两条记录，这里就要从数据库中查出第三条记录		
					//从数据库中随机取一张照片
					List<EntryForm> efFromDB1 = GeneratePictureController.getInstance().getNewEntryForm(1, 16, 25);
					if(null == efFromDB1){
						efFromDB1 = new ArrayList<EntryForm>();
					}
					//然后将数据库从
					efFromDB1.add(ef1);efFromDB1.add(ef2);
					lto.setInitPicURL(Object2XMLUtil.list2XML(efFromDB1, EntryForm.class));
				}
			}else{
				// 由于从集合中只能够查出1条符合条件的记录，这里就从数据库中去查找
				List<EntryForm> efFromDB2 = GeneratePictureController.getInstance().getNewEntryForm(2, 16, 25);
				if(null == efFromDB2){
					efFromDB2 = new ArrayList<EntryForm>();
				}
				efFromDB2.add(ef1);
				lto.setInitPicURL(Object2XMLUtil.list2XML(efFromDB2, EntryForm.class));				
			}
		}else{
			//从缓存中娶不到数据，这里从数据库中直接找
			List<EntryForm> efFromDB3 = GeneratePictureController.getInstance().getNewEntryForm(3, 16, 25);
			if(null == efFromDB3){
				efFromDB3 = new ArrayList<EntryForm>();
			}
			lto.setInitPicURL(Object2XMLUtil.list2XML(efFromDB3, EntryForm.class));	
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("lto", lto);
		/*
		 * 下面设置传递给flex的一些信息
		 */
		return "success";
	}
	
	public String directModifyPhoto(){
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(pId);
		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(pId);
		ActionContext.getContext().put("rvt", rvt);
		ActionContext.getContext().put("tct", tct);
		return SUCCESS;
	}
	
	/**
	 * 刷新提醒
	 * @return
	 */
	public String refreshTips(){
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(pId);
		ActionContext.getContext().put("tct", tct);
		return SUCCESS;
	}

	public String delAllSystemTips() {
		return SUCCESS;
	}

	public void setPersonalInfoId(int personalInfoId) {
		this.personalInfoId = personalInfoId;
	}

	public void setPi(PersonalInfo pi) {
		this.pi = pi;
	}

	public PersonalInfo getPi() {
		return pi;
	}

	public PermissionsTO getPer() {
		return per;
	}

	public int getPersonalInfoId() {
		return personalInfoId;
	}

	public void setPer(PermissionsTO per) {
		this.per = per;
	}

	public void setTipSelectedItem(int tipSelectedItem) {
		this.tipSelectedItem = tipSelectedItem;
	}

	public void setTipsIdStr(String tipsIdStr) {
		this.tipsIdStr = tipsIdStr;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	

}
