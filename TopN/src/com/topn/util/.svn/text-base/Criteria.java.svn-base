package com.topn.util;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-7 下午11:26:52
 * 
 * 用来创建条件语句
 */
public class Criteria {
	
	private StringBuffer sb = new StringBuffer();
	
	public Criteria(String tableName, String []args){
		sb.append("select ");
		for (int i = 0; i < args.length; i++) {
			sb.append(args[i]);
			if(i != args.length -1){
				sb.append(",");
			}
		}
		sb.append(" from ").append(tableName).append(" where 1=1 ");
	}

	/**
	 * 
	 * @param clazz 类名
	 * @param args  要查的字段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Criteria createCriteria(Class clazz, String []args){
		return new Criteria(clazz.getName(), args);
	}
	
	/**
	 * 
	 * @param tableName 表名称
	 * @param args   要查的字段
	 * @return
	 */
	public Criteria createCriteria(String tableName, String []args){
		return new Criteria(tableName, args);
	}
	
	/**
	 * 建议add方法添加一个 Restrictions的静态方法的调用，Restrictions类的静态方法都返回一个
	 * 字符串
	 * @param re
	 */
	public void add(String re){
		sb.append(re);
	}
	
	public String toSqlString(){
		return sb.toString();
	}
}
