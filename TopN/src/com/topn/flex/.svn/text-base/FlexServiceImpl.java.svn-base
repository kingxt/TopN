package com.topn.flex;

import java.util.List;

import com.topn.DAL.PersonalInfoDAL;
import com.topn.DAL.query.PersonalInfoQuery;
import com.topn.bean.EntryForm;
import com.topn.bean.PersonalInfo;
import com.topn.bean.TO.LoginTO;
import com.topn.bean.TO.TipsCountTO;
import com.topn.collection.UserInRegister;
import com.topn.controller.GeneratePictureController;
import com.topn.controller.PersonalController;
import com.topn.util.EmailSender;
import com.topn.util.StringUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-5 下午08:05:28
 * 
 * flex接口实现
 * 
 * Detected duplicate HTTP-based FlexSessions, 
 * generally due to the remote host disabling session cookies. 
 * Session cookies must be enabled to manage the client connection correctly.
 */
public class FlexServiceImpl implements FlexService{

	private static FlexServiceImpl instance = new FlexServiceImpl();
	
	private FlexServiceImpl(){}
	
	public static FlexService getInstance(){ 
		return instance;
	}

	
	@Override
	public boolean sendEmail(String emailAddress) {
		String code = UserInRegister.generateCode();
		EmailSender es = EmailSender.getInstance();
		boolean isSend = es.send(emailAddress, emailAddress, EmailSender.SUBJECT_VERIFICATION_CODE, "您的注册码是：   "+code);
		if(isSend){
			UserInRegister.getInstance().add(emailAddress, code);
		}
		return isSend;
	}

	@Override
	public boolean registe(String emailAddress, String password, String code) {
		boolean isExist = true;//UserInRegister.getInstance().find(emailAddress) != null;
		if(isExist){
			int id = PersonalInfoDAL.getInstance().regiser(UserInRegister.getTableName(emailAddress), emailAddress, password);
			if(id == 0)return false;
		}
		return true;
	}

	@Override
	public LoginTO login(String username, String password) {
		LoginTO lto = PersonalInfoQuery.getInstance().queryWithUsername(UserInRegister.getTableName(username), username, password);
		//获取排行榜
		//lto.setRankingList(GeneratePictureController.getInstance().getTopN(0, 10, lto.getSex()));
		
		return lto;
	}

	@Override
	public EntryForm saveGrade(int entryId, float grade, int thisAge, String nextAge, int nextSex) {
		return GeneratePictureController.getInstance().saveGrade(entryId, grade, thisAge, nextAge, nextSex);
	}
	
	private int getSex(String type){
		if(StringUtil.isBlank(type)){
			return 2;
		}else if(type.length() == 2){
			String t = type.substring(0, 1);
			return Integer.parseInt(t);
		}else {
			return 2;
		}
	}
	
	private String getScope(String type){
		if(StringUtil.isBlank(type)){
			return "a";
		}else if(type.length() == 2){
			return type.substring(1, 2);
		}else {
			return "a";
		}
	}

	@Override
	public List<EntryForm> getRankingList(int n, int sex, String type, int pid) {
		if(StringUtil.equals(type, "1a") || StringUtil.equals(type, "2a")){
			List<EntryForm> efs = GeneratePictureController.getInstance().getTopN(n, n+10, getSex(type));
			return efs;
		}else{
			PersonalInfo pi = PersonalController.getInstance().getPersonById(pid, pid, true);
			if(getScope(type).equals("b")){
				if(StringUtil.isBlank(pi.getCollege()) || StringUtil.isBlank(pi.getColLevel())){
					return null;
				}
				//下面就根据条件去获取信息 todo
				return GeneratePictureController.getInstance().getRankListByType(type, pi.getCollege(), getSex(type), Integer.parseInt(pi.getColLevel()), n);
			}else if(getScope(type).equals("c")){
				if(StringUtil.isBlank(pi.getHighSchool()) || StringUtil.isBlank(pi.getHighLevel())){
					return null;
				}
				//下面就根据条件去获取信息 todo
				return GeneratePictureController.getInstance().getRankListByType(type, pi.getHighSchool(), getSex(type), Integer.parseInt(pi.getHighLevel()), n);
			}
		}
		return null;
	}

	@Override
	public TipsCountTO getTipsCount(int pid) { 
		return PersonalController.getInstance().getTipsCountTO(pid);
	}

}
