package com.topn.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author KingXt
 *此类用来加载配置文件， 类中所有属性应该和properties配置文件中属性一致
 */
public class PropertyDeal {
	
	/**
	 * 这个参数是设置用户形象照片基路径，注意斜线结束
	 */
	public static String user_contraction_picture_relative_path = "";
	public static int user_contraction_picture_original_wide;
	public static int user_contraction_picture_original_height;
	public static int user_contraction_picture_small_wide;
	public static int user_contraction_picture_small_height;
	private static Logger logger = Logger.getLogger(PropertyDeal.class);

	static{
		PropertyDeal.class.getClassLoader();
		InputStream is = ClassLoader.getSystemResourceAsStream("app.properties");
		Properties p= new Properties(); 
	    try {
			p.load(is);
		} catch (IOException e) {
			if(logger.isDebugEnabled()){
				logger.debug("[PropertyDeal] 类中读配置文件失败\n"+e.getMessage());
			}
		} 
		
		
		PropertyDeal.user_contraction_picture_relative_path = p.getProperty("user_contraction_picture_relative_path");
		if(user_contraction_picture_relative_path == null){
			if(logger.isDebugEnabled()){
				logger.debug("[PropertyDeal] 类中参数user_contraction_picture_relative_path读取失败");
			}
		} 
		
		try{
			PropertyDeal.user_contraction_picture_original_wide = Integer.parseInt(p.getProperty("user_contraction_picture_original_wide"));
			PropertyDeal.user_contraction_picture_original_height = Integer.parseInt(p.getProperty("user_contraction_picture_original_height"));
			PropertyDeal.user_contraction_picture_small_height = Integer.parseInt(p.getProperty("user_contraction_picture_small_height"));
			PropertyDeal.user_contraction_picture_small_wide = Integer.parseInt(p.getProperty("user_contraction_picture_small_wide"));
		}catch(Exception e){
			if(logger.isDebugEnabled()){
				logger.debug("[PropertyDeal] 类中参数读取或者转换失败");
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		System.out.println(PropertyDeal.user_contraction_picture_small_height);
	}
}
