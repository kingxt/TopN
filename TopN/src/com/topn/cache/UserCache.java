package com.topn.cache;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-9 下午06:41:01
 * 
 * 由于前台是用flex登录的，所以这里
 */
public class UserCache {
	
	private static UserCache instance = new UserCache();
	
	private UserCache(){}
	
	public static UserCache getInstance(){
		return instance;
	}
	
	//在线用户
	private MemCache onlineUser = MemCacheProvider.getInstance().buildCache(
			MemCacheProvider.ONLINE_USER);

	//private ConcurrentHashMap<Integer, String> userInWeb  = new ConcurrentHashMap<Integer, String>();

	/**
	 * 
	 * @param id 用户的主键
	 * @param sign 标识，这里采用对应表名称
	 */
	public void add(int id, String sign){
		//userInWeb.put(id, sign);
		onlineUser.put(id, sign);
	}
	
	/**
	 * 用户id是否存在缓存 中
	 * @param id
	 * @return
	 */
	public boolean isExist(int id){
		//return userInWeb.get(id) != null;
		Object obj = onlineUser.get(id);
		return obj != null;
	}
	
	/**
	 * 根据id返回id所在的表明
	 * @param id
	 * @return
	 */
	public String getTableName(int id){
		//return userInWeb.get(id);
		Object obj = onlineUser.get(id);
		if(obj == null){
			return null; 
		}
		return obj.toString();
	}
	
	public void remove(int id){
		//userInWeb.remove(id);
		onlineUser.remove(id);
	}
}
