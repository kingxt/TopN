package com.topn.util;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-7 下午11:26:52
 * 
 * 用来创建删除条件
 */
public class DeleteCriteria {
	
	private StringBuffer sb = new StringBuffer();
	
	public DeleteCriteria(String tableName){
		sb.append("delete ");		
		sb.append(" from ").append(tableName).append(" where 1=1 ");
	}

	/**
	 * 
	 * @param clazz 类名
	 * @param args  要查的字段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public DeleteCriteria createCriteria(Class clazz){
		return new DeleteCriteria(clazz.getName());
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
