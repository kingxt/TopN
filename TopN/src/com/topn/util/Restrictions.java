package com.topn.util;

/**
 * 
 * 创建人 KingXt 创建时间：2011-4-8 上午12:11:22
 * 
 * 约束
 */
public class Restrictions {
	
	/**
	 * 相等
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static String eq(String propertyName, String value) {
		return " and" + propertyName + "='" + value+ "'";
	}
	
	/**
	 * 相等
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static String eq(String propertyName, int value){
		return " and " + propertyName + "=" + value;
	}
	
	public static String like(String propertyName, String value){
		return " and " + propertyName + " like '%" + value +"%'";
	}
	
	public static String likeOR(String propertyName, String value){
		return " or " + propertyName + " like '%" + value +"%'";
	}
	
	/**
	 * 分页可以用
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static String limit(int pageNum, int pageSize){
		if(0 == pageNum){
			pageNum = 1;
		}
		return " limit " + (pageNum -1 )* pageSize + " ," + pageSize;
	}
	
	/**
	 * 修改时候用来填充set字段
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static String updateWithSet(String propertyName, String value){
		return " " + propertyName + " = '" + value + "' ";
	}
	

	/**
	 * 修改时候用来填充set字段
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static String updateWithSet(String propertyName, int value){
		return " " + propertyName + " = " + value;
	}
}
