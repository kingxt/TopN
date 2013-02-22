package com.topn.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.DAL.query.PersonalInfoQuery;
import com.topn.action.help.CookieHelp;
import com.topn.action.help.FriendsOnlineHelp;
import com.topn.bean.EntryForm;
import com.topn.bean.TO.LoginTO;
import com.topn.cache.UserCache;
import com.topn.collection.PicturePartition;
import com.topn.collection.UserInRegister;
import com.topn.controller.GeneratePictureController;
import com.topn.util.Object2XMLUtil;
import com.topn.util.StringUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-10 下午05:43:37
 * 登录验证的action
 */
public class LoginAction extends ActionSupport{
	
	private String username;
	
	private String password;
	
	private int rememberPwd;
	
	private int encrypt;
	
	private String msg;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 登录
	 * @return 逻辑视图
	 */
	@SuppressWarnings("deprecation")
	public String login(){
		
		if(this.encrypt == 2){//说明密码是经过加密的
			password = CookieHelp.getPassword(ServletActionContext.getRequest());
		}
		if(StringUtil.isBlank(username) || StringUtil.isBlank(password)){
			ActionContext.getContext().put("remind", "用户名和密码不能为空");
			return "loginerror";
		}
		username = username.toLowerCase();//邮箱大小写可以忽略
		LoginTO lto = PersonalInfoQuery.getInstance().queryWithUsername(UserInRegister.getTableName(username), username, password);
		
		if(lto == null || lto.getPersonalInfoId() == 0){
			ActionContext.getContext().put("remind", "用户名或密码不正确");
			return "loginerror";
		}
		
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
//					EntryForm efdb1 = GeneratePictureController.getInstance().selectOneFromDB(PicturePartition.P_16_25, sex);
//					if(null != efdb1){
//						lto.setInitPicURL(Object2XMLUtil.array2XML(new EntryForm[]{ef1, ef2, efdb1}, EntryForm.class));
//					}else{
//						lto.setInitPicURL(Object2XMLUtil.array2XML(new EntryForm[]{ef1, ef2}, EntryForm.class));
//					}		
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
		
		//添加一个用户id的cook
		/*Cookie cook = new Cookie("id", String.valueOf(lto.getPersonalInfoId()));
		Cookie cookUsername = new Cookie("username", username);
		ServletActionContext.getResponse().addCookie(cook);
		ServletActionContext.getResponse().addCookie(cookUsername);*/
		CookieHelp.setPersonalId(ServletActionContext.getResponse(), lto.getPersonalInfoId());
		
		if(this.rememberPwd == 1){//记住密码
			CookieHelp.rememberPassword(ServletActionContext.getResponse(), password);
			CookieHelp.rememberUsername(ServletActionContext.getResponse(), username);
		}else if(this.rememberPwd == 0){//失效密码
			CookieHelp.expirePassword(ServletActionContext.getRequest());
			CookieHelp.setUsername(ServletActionContext.getResponse(), username);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("lto", lto);
		/*
		 * 下面设置传递给flex的一些信息
		 */
		return "success";
	}
	
	/**
	 * 退出
	 * @return
	 */
	public String exit(){
		CookieHelp.kickPassword(ServletActionContext.getRequest());
		CookieHelp.removePassword(ServletActionContext.getResponse());
		CookieHelp.removePersonId(ServletActionContext.getResponse());
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		UserCache.getInstance().remove(pid);
		//通知用户,我已经下线了
		FriendsOnlineHelp.myFriendOffLine(pid);
		return LOGIN;
	}
	
	/**
	 * 重新登录
	 * @return
	 */
	public String relogin(){
		if(StringUtil.isBlank(username) || StringUtil.isBlank(password)){
			ActionContext.getContext().put("remind", "用户名和密码不能为空");
			return "loginerror";
		}
		username = username.toLowerCase();//邮箱大小写可以忽略
		LoginTO lto = PersonalInfoQuery.getInstance().queryWithUsername(UserInRegister.getTableName(username), username, password);
		if(lto == null || lto.getPersonalInfoId() == 0){
			ActionContext.getContext().put("remind", "用户名或密码不正确");
			return "loginerror";
		}
		CookieHelp.setPersonalId(ServletActionContext.getResponse(), lto.getPersonalInfoId());
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setRememberPwd(int rememberPwd) {
		this.rememberPwd = rememberPwd;
	}

	public void setEncrypt(int encrypt) {
		this.encrypt = encrypt;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
}
