package com.topn.controller;

import java.util.List;

import com.topn.DAL.PersonalInfoDAL;
import com.topn.DAL.query.HobbyAndHobbyTypeQuery;
import com.topn.bean.Hobby;
import com.topn.bean.HobbyType;
import com.topn.cache.MemCache;
import com.topn.cache.MemCacheProvider;
import com.topn.cache.UserCache;
import com.topn.util.Restrictions;
import com.topn.util.UpdateCriteria;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-26 下午02:09:17
 * 
 * 兴趣和兴趣类型 控制类
 */
public class HobbyAndHobbyTypeController {

	//缓存hobbytype
	private MemCache hobbyType = MemCacheProvider.getInstance().buildCache(MemCacheProvider.HOBBY_TYPE);
	
	private static HobbyAndHobbyTypeController instance = new HobbyAndHobbyTypeController();

	private HobbyAndHobbyTypeController() {
	}

	public static HobbyAndHobbyTypeController getInstance() {
		return instance;
	}

	/**
	 * 获取所有的兴趣类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HobbyType> loadHobbyTypse() {
		Object obj = hobbyType.get(MemCacheProvider.HOBBY_TYPE);
		List<HobbyType> hts = null;
		if(obj == null){
			//从数据库中找，然后缓存到memcache中去
			hts = HobbyAndHobbyTypeQuery.getInstance().getAllHobbyTypes();
			hobbyType.put(MemCacheProvider.HOBBY_TYPE, hts);
			return hts;
		}else{
			return (List<HobbyType>)obj;
		}	
		
	}
	
	/**
	 * 更新兴趣爱好
	 * @param hobby
	 */
	public void updateHobby(int pid, String hobby, String otherHobby){
		
		UpdateCriteria cr = new UpdateCriteria(getMoodTableName(pid));
		cr.addSet(Restrictions.updateWithSet("other_hobby", otherHobby));
		cr.addLastSet(Restrictions.updateWithSet("hobby", hobby));
		cr.addCondition(Restrictions.eq("person_info_id", pid));
		PersonalInfoDAL.getInstance().updatePerson(cr.toSqlString());
	}
	
	/**
	 * 根据用户的id 找到所在表名
	 * 
	 * @param pid
	 * @return
	 */
	public String getMoodTableName(int pid) {
		String userTable = UserCache.getInstance().getTableName(pid);
		return userTable;
	}
	
	/**
	 * 根据hobbytype加载hobby
	 * @param typeId
	 * @return
	 */
	public List<Hobby> loadHobbyByType(int typeId){
		return HobbyAndHobbyTypeQuery.getInstance().loadHobbyByType(typeId);
	}
}
