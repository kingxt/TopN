package com.topn.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.topn.collection.MyMapEntry;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-12 下午04:43:28
 * 
 * string的工具类
 */
public class StringUtil {
	
	public static final String MAIL_163 = "http://email.163.com/";
	public static final String MAIL_126 = "http://www.126.com/";
	public static final String MAIL_SINA = "http://mail.sina.com.cn/";
	public static final String MAIL_YAHOO = "http://mail.cn.yahoo.com/";
	public static final String MAIL_GMAIL = "http://gmail.google.com";
	public static final String MAIL_QQ = "https://mail.qq.com/cgi-bin/loginpage";
	
	public static final String MAIL_YEAH = "http://www.yeah.net";//网易邮箱
	public static final String MAIL_139 = "http://mail.10086.cn";
	public static final String MAIL_SOHU = "http://mail.sohu.com";
	public static final String MAIL_EYOU = "http://www.eyou.com/";
	public static final String MAIL_TOM = "http://mail.tom.com/";
	public static final String MAIL_HOTMAIL = "https://login.live.com/login.srf";
	public static final String MAIL_SOGOU = "http://mail.sogou.com/";
	/**
	 * string为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		return str == null || "".equals(str);
	}
	
	/**
	 * string不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str){
		return str != null && !str.equals("");
	}
	
	/**
	 * 验证是否是邮箱
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email)  
    {  
        // 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]  
        // 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn  
        // {1,3}表示可以出现一次或两次或者三次.  
        String reg = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";  
        Pattern pattern = Pattern.compile(reg);  
        boolean flag = false;  
        if (email != null)  
        {  
            Matcher matcher = pattern.matcher(email);  
            flag = matcher.matches();  
        }  
        return flag;  
    }  
	
	/**
	 * 判断字符串是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2){
		if(isBlank(str1) || isBlank(str2)){
			return false;
		}
		if(str1.equals(str2)){
			return true;
		}
		return false;
	}
	
	public static boolean isLessThen(String str, int len){
		if(isBlank(str))return true;
		return str.length() < len;
	}
	
	/**
	 * 获取邮箱的主页地址
	 * @param email
	 * @return
	 */
	public static MyMapEntry<String, String> getEmailHomeURL(String email){
		String temp = email.substring(email.indexOf('@'), email.length());
		String host = temp.substring(temp.indexOf('@') + 1, temp.indexOf('.'));
		MyMapEntry<String, String> mme = new MyMapEntry<String, String>("去" + host +"邮箱","");
		if(host.equals("163")){
			mme.setValue(MAIL_163);
		}else if(host.equals("126")){
			mme.setValue(MAIL_126);
		}else if(host.equals("sina")){
			mme.setValue(MAIL_SINA);
		}else if(host.equals("gmail")){
			mme.setValue(MAIL_GMAIL);
		}else if(host.equals("qq")){
			mme.setValue(MAIL_QQ);
		}else if(host.equals("yeah")){
			mme.setValue(MAIL_YEAH);
		}else if(host.equals("139")){
			mme.setValue(MAIL_139);
		}else if(host.equals("sohu")){
			mme.setValue(MAIL_SOHU);
		}else if(host.equals("eyou")){ 
			mme.setValue(MAIL_EYOU);
		}else if(host.equals("tom")){
			mme.setValue(MAIL_TOM);
		}else if(host.equals("hotmail")){
			mme.setValue(MAIL_HOTMAIL);
		}else if(host.equals("sogou")){
			mme.setValue(MAIL_SOGOU);
		}
		return mme;
	}
	
	/**
	 * 将字符解析成utf-8格式
	 * @param source
	 * @return
	 */
	public static String parseTOUTF_8(String source){
		try {
			return new String(source.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 判断是否是数字
	 * @return
	 */
	public static boolean isNumber(String str){ 
		Pattern pattern = Pattern.compile("[0-9]*"); 
		return pattern.matcher(str).matches();    
	}
	
	/**
	 * 判断是否是数字
	 * @return
	 */
	public static boolean isRightPath(String path){ 
		if(path.indexOf("page") > 0){
			Pattern pattern = Pattern.compile("/TopN/page/[a-zA-Z0-9]*.action"); 
			return pattern.matcher(path).matches();    
		}else{
			Pattern pattern = Pattern.compile("/TopN/[a-zA-Z0-9]*.action");
			return pattern.matcher(path).matches();    
		}
		
	}
	
	public static String dealAbsolutePath2Relative(String orinal){
		if(isBlank(orinal)){
			return "";
		}
		String t = orinal.substring(orinal.indexOf("user_picture"));
		return t;
	}


	
	public static void main(String[] args) {
		System.out.println(isRightPath("/TopN/loadPersonalAlbum.action"));
		
		System.out.println(dealAbsolutePath2Relative("../user_picture/2011-6/ordinary/small_150_115/a0ef07e9-4bc1-42c5-adfb-8b078d24d9c0.jpg"));
	}
}
