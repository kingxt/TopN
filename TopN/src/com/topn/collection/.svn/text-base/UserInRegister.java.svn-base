package com.topn.collection;

import java.util.Random;

import com.topn.bean.TO.ForgetPasswordTO;
import com.topn.cache.MemCache;
import com.topn.cache.MemCacheProvider;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-5 下午07:21:33
 * 
 * 这个类用来处理所正在注册的用户
 */
public class UserInRegister {
	
	public static final String DOMAIN = "http://58.194.70.47/TopN/";
	
	private static UserInRegister uir = new UserInRegister();
	private static Random random = new Random();
	private UserInRegister(){}
	
	private MemCache mc = MemCacheProvider.getInstance().buildCache("registecode");//注册
	private MemCache forgetpwd = MemCacheProvider.getInstance().buildCache(MemCacheProvider.FORGETPWD);//忘记密码
	//private ConcurrentHashMap<String, String> user_register_code = new ConcurrentHashMap<String, String>();
	
	public static UserInRegister getInstance(){
		return uir;
	}
	
	/**
	 * 添加一个用户，但是这个用户还没有真正注册，等验证码确认后就可以发送到数据库中
	 * @param username
	 * @param code
	 */
	public void add(String username, String code){
		mc.put(username, code);
	}
	
	/**
	 * 将忘记密码的一些信息放到缓存中去
	 * @param username
	 * @param fpt
	 */
	public void addForgetUser(String username, String sign){
		forgetpwd.put(username, sign);
	}
	
	/**
	 * 获取忘记密码的人
	 * @param username
	 * @return
	 */
	public String getForgetPasswordTO(String username){
		return (String) forgetpwd.get(username);
	}
	
	public String find(String username){
		return (String)mc.get(username);
	}
	/**
	 * 根据用户名计算hash，这里采用jdk默认方式，将来可以改进
	 * @param username
	 * @return
	 */
	public static int hash(String username){
		return username.hashCode();
	}
	
	/**
	 * 这里随机生成四位数
	 * @return
	 */
	public static String generateCode(){
		int a = random.nextInt(10000) + 1000;
		if(a > 10000) a -= 1000;
		return String.valueOf(a);
	}
	
	/**
	 * 根据email地址的前四位散列到不同的表
	 * @param emailAddress
	 * @return
	 */
	public static String getTableName(String emailAddress){
		String hashString = emailAddress.substring(0, 3);
		int tableIndex = hash(hashString);
		switch(tableIndex%5){
			case 0: return "person_info_one"; 
			case 1: return "person_info_two";
			case 2: return "person_info_three";
			case 3: return "person_info_four";
			case 4: return "person_info_five";
			default : return "person_info_one";
		}
	}
}
