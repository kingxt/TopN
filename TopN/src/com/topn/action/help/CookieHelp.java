package com.topn.action.help;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.topn.util.Encrypt;
import com.topn.util.StringUtil;


/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-14 下午07:33:18
 * 
 * 与cookie打交道的都是此类
 */
public class CookieHelp {
	
	public static final String ID = "id";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String VERIFICATION = "verification";
	public static final String BASE_COOKIE_DOMAIN = "/TopN/page";
	

	/**
	 * 从cookie中获取用户的id
	 * @param request
	 * @return
	 */
	public static int getPersonalId(HttpServletRequest request){		
		Cookie[] cookies = request.getCookies();
		int pId = 0;
		for (Cookie c : cookies) {
			if (c.getName().equals(ID)) {
				if(StringUtil.isBlank(c.getValue())){
					return 0;
				}
				pId = Integer.parseInt(c.getValue());
				break;
			}
		}
		return pId;
	}
	
	/**
	 * 将id添加到cookie中
	 * @param response
	 * @param id
	 */
	public static void setPersonalId(HttpServletResponse response, int id){
		
		Cookie cook = new Cookie(ID, String.valueOf(id));
		cook.setPath("/");
		cook.setMaxAge(-1);
		response.addCookie(cook);
	}
	
	/**
	 * 从cookie中获取用户用户名
	 * @param request
	 * @return
	 */
	public static String getUsername(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		String username = "";
		for (Cookie c : cookies) {
			if (c.getName().equals(USERNAME)) {
				username = c.getValue();
				break; 
			}
		}
		return username;
	}
	
	/**
	 * 将用户名保存到cookie中
	 * @param response
	 * @param username
	 */
	public static void rememberUsername(HttpServletResponse response, String username){
		Cookie cook = new Cookie(USERNAME, username);
		cook.setMaxAge(7*24*60*60);
		cook.setPath("/");
		response.addCookie(cook);
	}
	
	/**
	 * 将用户名保存到cookie中
	 * @param response
	 * @param username
	 */
	public static void setUsername(HttpServletResponse response, String username){
		Cookie cook = new Cookie(USERNAME, username);
		cook.setMaxAge(-1);
		cook.setPath("/");
		response.addCookie(cook);
	}
	
	/**
	 * 记住密码
	 */
	public static void rememberPassword(HttpServletResponse response, String password){
		//这里的密码要经过加密
		password = Encrypt.encode(password);
		Cookie cook = new Cookie(PASSWORD, password);
		cook.setMaxAge(7*24*60*60);
		cook.setPath("/");
		response.addCookie(cook);
	}
	
	/**
	 * 记住密码
	 */
	public static void removePassword(HttpServletResponse response){
		//这里的密码要经过加密
		Cookie cook = new Cookie(PASSWORD, "");
		cook.setMaxAge(-1);
		cook.setPath("/");
		response.addCookie(cook);
	}
	
	public static void removePersonId(HttpServletResponse response){
		//这里的密码要经过加密
		Cookie cook = new Cookie(ID, "");
		cook.setMaxAge(-1);
		cook.setPath("/");
		response.addCookie(cook);
	}
	
	
	/**
	 * 获取密码
	 * @param request
	 * @return
	 */
	public static String getPassword(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		String password = "";
		for (Cookie c : cookies) {
			if (c.getName().equals(PASSWORD)) {
				password = c.getValue();
				break;
			}
		}
		if(StringUtil.isBlank(password)){
			return "";
		}
		return Encrypt.decode(password);
	}
	
	
	/**
	 * @deprecated
	 * 密码失效
	 */
	public static void expirePassword(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies == null){
			return;
		}
		for (Cookie c : cookies) {
			if (c.getName().equals(PASSWORD)) {
				c.setMaxAge(-1);   
				break;
			}
		}
	}
	
	/**
	 * 密码失效
	 */
	public static void kickPassword(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies == null){
			return;
		}
		for (Cookie c : cookies) {
			if (c.getName().equals(PASSWORD)) {
				c.setMaxAge(-1);   
				c.setValue("");
				break;
			}
		}
	}
	
	/**
	 * 添加验证码
	 */
	public static void setVerification(HttpServletResponse response, String code){
		code = Encrypt.encode(code);
		Cookie cook = new Cookie(VERIFICATION, code);
		cook.setMaxAge(-1);
		cook.setPath("/");
		response.addCookie(cook);
	}
	

	/**
	 * 获取密码
	 * @param request
	 * @return
	 */
	public static String getVerification(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		String verification = "";
		for (Cookie c : cookies) {
			if (c.getName().equals(VERIFICATION)) {
				verification = c.getValue();
				break;
			}
		}
		if(StringUtil.isBlank(verification)){
			return "";
		}
		return Encrypt.decode(verification);
	}
	
	/**
	 * 
	 */
	public static void setIsRelogin(){
		
	}
	
	public static void main(String[] args) {
	
	}
}
