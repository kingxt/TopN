package com.topn.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.topn.DAL.PersonalInfoDAL;
import com.topn.DAL.query.PersonalInfoQuery;
import com.topn.bean.City;
import com.topn.bean.District;
import com.topn.bean.HighSchool;
import com.topn.bean.PersonalInfo;
import com.topn.bean.Province;
import com.topn.bean.University;
import com.topn.bean.TO.CacheEducationTO;
import com.topn.bean.TO.CachePersonTO;
import com.topn.bean.TO.CacheRankPersonTO;
import com.topn.bean.TO.FriendAddTipsTO;
import com.topn.bean.TO.FriendRecentMoodTO;
import com.topn.bean.TO.LeaveMsgTipsPageTO;
import com.topn.bean.TO.LeaveMsgTipsTO;
import com.topn.bean.TO.LoginTO;
import com.topn.bean.TO.MoodTipsPageTO;
import com.topn.bean.TO.MoodTipsTO;
import com.topn.bean.TO.PermissionsTO;
import com.topn.bean.TO.SystemTipsTO;
import com.topn.bean.TO.TipsCountTO;
import com.topn.cache.MemCache;
import com.topn.cache.MemCacheProvider;
import com.topn.cache.UserCache;
import com.topn.collection.UserInRegister;
import com.topn.exception.ServiceException;
import com.topn.util.Criteria;
import com.topn.util.DeleteCriteria;
import com.topn.util.GenerateSql;
import com.topn.util.LoggerUtil;
import com.topn.util.Restrictions;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-23 下午01:57:01
 * 
 * 个人主页和别人的主页控制类
 */
public class PersonalController {

	private static Logger logger = Logger.getLogger(PersonalController.class);

	private MemCache userMC = MemCacheProvider.getInstance().buildCache(
			MemCacheProvider.MEMCACHE_TYPE_USER);

	// 缓存排行版的一些记录
	private MemCache rankMC = MemCacheProvider.getInstance().buildCache(
			MemCacheProvider.MEMCACHE_TYPE_RANK);
	// 缓存初始化教育信息
	private MemCache educationMC = MemCacheProvider.getInstance().buildCache(
			MemCacheProvider.MEMCACHE_EDUCATION);

	private static PersonalController pc = new PersonalController();

	private PersonalController() {
	}

	public static PersonalController getInstance() {
		return pc;
	}

	/**
	 * 获取排名记录 第一步应该是从缓存中取的，如果取不到就到数据库中取，然后将他放到缓存中去
	 * 
	 * @return
	 */
	public CacheRankPersonTO getRankPerson(int personalId) {
		// 第一步获取用户基本信息
		Object obj = rankMC.get(personalId);
		if (null != obj) {
			return (CacheRankPersonTO) obj;
		} else {
			CacheRankPersonTO crpt = new CacheRankPersonTO();
			/*
			 * 这里为什么会直接从数据库中找是因为在缓存中查找到命中率笔记哦啊低，这里也没有必要放到缓存中去避免多次缓存同一条记录
			 */
			PersonalInfo pi = PersonalInfoQuery.getInstance().getPersonById(
					personalId, personalId);

			crpt.setPer(PermissionsTO.string2PermissionsTO(pi.getPermission()));
			crpt.setPi(pi);
			crpt.setCompetitionAlbum(PersonalInfoQuery.getInstance()
					.loadAllCompetitionPhoto(personalId));
			crpt.setOrdinaryAlbum(PersonalInfoQuery.getInstance().getAllAlbums(
					personalId));
			rankMC.put(personalId, crpt);
			return crpt;
		}
	}

	/**
	 * 控制层，根据id查询，如果在缓存中能够找到就直接返回，否则就从数据库里面查然后同步到缓存
	 * 修改了缓存人的实现方式，因为将来可能缓存人的信息不只是数据库一个表的字段，也或许不只是数据库表的字段
	 * 调用这个函数取得的人一定会被存到缓存中去
	 * @param id
	 * @return
	 */
	public PersonalInfo getPersonById(int personalId, int myId, boolean useCache) {
		if(-1 == myId){
			myId = personalId;
		}
		if (personalId <= 0)
			return null;
		Object obj = null;

		if (useCache) {
			obj = userMC.get(personalId);
		}

		if (null != obj) {
			return ((CachePersonTO) obj).getPi();
		} else {
			PersonalInfo pi = PersonalInfoQuery.getInstance().getPersonById(
					personalId, myId);
			if(-1 == pi.getPersonalInfoId()){//数据库中没有找到就返回null
				return null;
			}
			// 要跟新表名称，这里表名称可以从jvm缓存集合中找到
			pi.setTableName(UserCache.getInstance().getTableName(personalId));
			CachePersonTO cpt = new CachePersonTO();
			cpt.setPi(pi);
			userMC.put(personalId, cpt);
			return pi;
		}
	}
	
	/**
	 * 
	 * @param personalId
	 * @param myId
	 * @param useCache
	 * @param save2Cache 是否要存到缓存中
	 * @return
	 */
	public PersonalInfo getPersonById(int personalId, int myId, boolean useCache, boolean save2Cache) {
		if(-1 == myId){
			myId = personalId;
		}
		if (personalId <= 0)
			return null;
		Object obj = null;

		if (useCache) {
			obj = userMC.get(personalId);
		}

		if (null != obj) {
			return ((CachePersonTO) obj).getPi();
		} else {
			PersonalInfo pi = PersonalInfoQuery.getInstance().getPersonById(
					personalId, myId);
			// 要更新表名称，这里表名称可以从jvm缓存集合中找到
			if(save2Cache){
				pi.setTableName(UserCache.getInstance().getTableName(personalId));
				CachePersonTO cpt = new CachePersonTO();
				cpt.setPi(pi);
				userMC.put(personalId, cpt);
			}
			return pi;
		}
	}

	/**
	 * 更新人物信息
	 * 
	 * @param pi
	 */
	public void updatePersonalInfo(PersonalInfo pi) {
		if (null == pi)
			return;

		String sql = null;
		try {
			sql = GenerateSql.generateUpdateSql(pi);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.loggerDebug(logger,
					"[PersonalAction, updatePersonalInfo]更新异常");
			throw new ServiceException("更新异常");
		}
		PersonalInfoDAL.getInstance().updatePerson(sql);
		//this.getPersonById(pi.getPersonalInfoId(), pi.getPersonalInfoId(), false);
	}

	/**
	 * 从缓存中获取修改教育信息的选择菜单
	 * @return
	 */
	public CacheEducationTO getCacheEducationTO() {
		Object obj = educationMC.get(MemCacheProvider.MEMCACHE_EDUCATION);
		if (null != obj) {
			return (CacheEducationTO) obj;
		} else {
			CacheEducationTO cto = new CacheEducationTO();
			List<Province> pc = this.getAllProvince();
			List<University> us = this.getUniversitiesByProvinceId(1);
			List<City> cs = this.loadCityByProvince(1);
			List<District> ds = this.loadDistrictByCity(1);
			List<HighSchool> hs = this.loadHighSchoolByDistrict(1);
			cto.setPc(pc);cto.setUs(us);cto.setCs(cs);cto.setDs(ds);cto.setHs(hs);
			educationMC.put(MemCacheProvider.MEMCACHE_EDUCATION, cto);
			return cto;
		}
	}

	/**
	 * 加载所有省份，这里用了memcache
	 * 
	 * @return
	 */
	public List<Province> getAllProvince() {
		// 第一步获取用户基本信息
		List<Province> cc = PersonalInfoQuery.getInstance().getAllProvince();
		return cc;
	}

	public List<University> getUniversitiesByProvinceId(int provinceId) {
		return PersonalInfoQuery.getInstance().getUniversitiesByProvinceId(
				provinceId);
	}

	/**
	 * 更新缓存
	 * 
	 * @param pi
	 */
	public void updateCache(PersonalInfo pi) {
		if (pi.getPersonalInfoId() != 0) {
			Object obj = userMC.get(pi.getPersonalInfoId());
			if(null != obj){
				CachePersonTO cpt = (CachePersonTO)(obj);
				cpt.setPi(pi);
				userMC.put(pi.getPersonalInfoId(), cpt);
			}else{
				CachePersonTO cpt = new CachePersonTO();
				cpt.setPi(pi);
				userMC.put(pi.getPersonalInfoId(), cpt);
			}
		}
	}

	/**
	 * 根据省份的id获取所有的市区
	 * 
	 * @param pid
	 *            省份id
	 * @return
	 */
	public List<City> loadCityByProvince(int pid) {
		return PersonalInfoQuery.getInstance().loadCityByProvince(pid);
	}

	/**
	 * 获取一个市中的所有区
	 * 
	 * @param cid
	 * @return
	 */
	public List<District> loadDistrictByCity(int cid) {
		return PersonalInfoQuery.getInstance().loadDistrictByCity(cid);
	}
	
	/**
	 * 搜索大学
	 * @return
	 */
	public List<University> searchUniversities(String uni){
		return PersonalInfoQuery.getInstance().searchUniversities(uni);
	}

	/**
	 * 获取一个区的所有 高中
	 * 
	 * @param cid
	 * @return
	 */
	public List<HighSchool> loadHighSchoolByDistrict(int did) {
		return PersonalInfoQuery.getInstance().loadHighSchoolByDistrict(did);
	}
	
	/**
	 * 获取提醒总数
	 * @param pid
	 * @return
	 */
	public TipsCountTO getTipsCountTO(int pid){
		return PersonalInfoQuery.getInstance().getTipsCountTO(pid);
	}
	
	/**
	 * 获取心情回复提醒, 获取后就马上删除
	 * @param pid
	 * @return
	 */
	public List<MoodTipsPageTO> getMoodTipsTO(int pid){
		List<MoodTipsPageTO> mtpt = new ArrayList<MoodTipsPageTO>();
		List<MoodTipsTO> mto = PersonalInfoQuery.getInstance().getMoodTipsTO(pid);
		MoodTipsPageTO mt = null;
		List<MoodTipsTO> mtt = null;
		for (Iterator<MoodTipsTO> iterator = mto.iterator(); iterator.hasNext();) {
			MoodTipsTO moodTipsTO = (MoodTipsTO) iterator.next();
			if(moodTipsTO.getMoodPersonId() != 0){
				mtt = new ArrayList<MoodTipsTO>();
				mt = new MoodTipsPageTO();
				
				mtpt.add(mt);
				mt.setMood(moodTipsTO);
				mt.setMto(mtt);
			}else{
				mtt.add(moodTipsTO);
			}
		}
		PersonalInfoDAL.getInstance().deleteMoodTips(pid);
		return mtpt;		
	}
	
	/**
	 * 获取留言
	 * @param pid
	 * @return
	 */
	public List<LeaveMsgTipsPageTO> getLeaveMsgTipsTO(int pid){
		List<LeaveMsgTipsPageTO> mtpt = new ArrayList<LeaveMsgTipsPageTO>();
		List<LeaveMsgTipsTO> mto = PersonalInfoQuery.getInstance().getLeaveMsgTipsTO(pid);
		LeaveMsgTipsPageTO mt = null;
		List<LeaveMsgTipsTO> mtt = null;
		for (Iterator<LeaveMsgTipsTO> iterator = mto.iterator(); iterator.hasNext();) {
			LeaveMsgTipsTO moodTipsTO = (LeaveMsgTipsTO) iterator.next();
			if(moodTipsTO.getPublisherId() != 0){
				mtt = new ArrayList<LeaveMsgTipsTO>();
				mt = new LeaveMsgTipsPageTO();
				
				mtpt.add(mt);
				mt.setLeaveMsg(moodTipsTO);
				mt.setMto(mtt);
			}else{
				mtt.add(moodTipsTO);
			}
		}
		PersonalInfoDAL.getInstance().deleteleaveMsgTips(pid);
		return mtpt;		
	}
	
	/**
	 * 获取好友申请
	 * @param pid
	 * @return
	 */
	public List<FriendAddTipsTO> getFriendAddTipsTO(int pid){
		return PersonalInfoQuery.getInstance().getFriendAddTipsTO(pid);
	}
	
	/**
	 * 获取系统提醒
	 * @param pid
	 * @return
	 */
	public List<SystemTipsTO> getSystemTipsTO(int pid){
		return PersonalInfoQuery.getInstance().getSystemTipsTO(pid);
	}
	
	/**
	 * 查询是否已经注册了此邮箱
	 * @param tableName
	 * @param username
	 * @return 
	 */
	public boolean isExistUsername(String username){
		
		int sign = PersonalInfoQuery.getInstance().isExistUsername(UserInRegister.getTableName(username), username);
		if(sign == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除系统提示消息
	 * @param tipId
	 */
	public void delSystemTips(int tipId){
		DeleteCriteria dc = new DeleteCriteria("tips");
		dc.add(Restrictions.eq("id", tipId));
		PersonalInfoDAL.getInstance().delSystemTips(dc.toSqlString());
	}
	
	
	/**
	 * 查所自己最近的心情
	 * @return
	 */
	public List<FriendRecentMoodTO> getMyMood(int id, int pageNum, int pageSize){
		return PersonalInfoQuery.getInstance().getMyMood(id, pageNum, pageSize);
	}
	
	/**
	 * 查询自己的心情总数
	 * @param pid  自己的id
	 * @param username  自己的用户名
	 * @return
	 */
	public int getTotalOfMyMood(int pid){
		
		Criteria c = new Criteria(getMoodTable(pid), new String[]{"count(1)"});
		c.add(Restrictions.eq("person_id", pid));
		return PersonalInfoQuery.getInstance().getTotalOfMine(c.toSqlString());
	}
	
	/**
	 * 获取留言总数
	 * @param pid
	 * @return
	 */
	public int getTotalOfLeaveMsg(int pid){
		Criteria c = new Criteria("notes", new String[]{"count(1)"});
		c.add(Restrictions.eq("target_id", pid));
		return PersonalInfoQuery.getInstance().getTotalOfMine(c.toSqlString());
	}
	
	public String getMoodTable(int pid){
		String userTable = UserCache.getInstance().getTableName(pid);
		return userTable.replace("person_info", "mood"); 
	}
	
	/**
	 * 修改密码
	 * @param pid
	 * @param pwd 密码
	 */
	public void modifyPassword(int pid, String pwd){
		PersonalInfoDAL.getInstance().modifyPassword(pid, pwd);
	}
	
	/**
	 * 修改密码
	 * @param tableName
	 * @param username
	 * @param password
	 */
	public void fogetPwd(final String tableName, final String username, final String password){
		PersonalInfoDAL.getInstance().fogetPwd(tableName, username, password);
	}
	
	public LoginTO back2TopN(int id){
		return PersonalInfoQuery.getInstance().back2TopN(id);
	}
	
	/**
	 * 由于权限设置,这里要判断好友的个人信息是否能够看到
	 */
	public void friendPersonalInfo(PersonalInfo pi){
		PermissionsTO pto = PermissionsTO.string2PermissionsTO(pi.getPermission());
		if(pto.getPerAddress() == 3){
			pi.setAddress("");
		}
		if(pto.getPerBirthday() == 3){
			pi.setBirthday("");
		}
		if(pto.getPerMsn() == 3){
			pi.setMsn("");
		}
		if(pto.getPerPhone() == 3){
			pi.setPhone("");
		}
		if(pto.getPerQQ() == 3){
			pi.setQq("");
		}
		if(pto.getPerSchool() == 3){
			pi.setHighSchool("");
			pi.setCollege("");
		}
	}
	
	/**
	 * 判断是否是自己的好友
	 * @param me
	 * @param fri
	 * @return
	 */
	public int isMyFriend(final int me, final int fri){
		return PersonalInfoQuery.getInstance().isMyFriend(me, fri);
	}
}
