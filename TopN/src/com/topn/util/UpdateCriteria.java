package com.topn.util;

/**
 * 
 * 创建人 KingXt 创建时间：2011-4-11 下午11:33:31
 */
public class UpdateCriteria {

	private StringBuffer sb = new StringBuffer();

	public UpdateCriteria(String tableName) {
		sb.append("update ").append(tableName).append(" set ");
	}

	/**
	 * 
	 * @param clazz
	 *            类名
	 * @param args
	 *            要查的字段
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public UpdateCriteria createUpdateCriteria(Class clazz) {
		return new UpdateCriteria(clazz.getName());
	}

	/**
	 * 建议add方法添加一个 Restrictions的静态方法的调用，Restrictions类的静态方法都返回一个 字符串
	 * 
	 * @param re
	 */
	public void addLastSet(String re) {
		sb.append(re).append(" where 1=1 ");
	}
	
	public void addCondition(String re){
		sb.append(re);
	}
	
	public void addSet(String re) {
		sb.append(re).append(",");
	}

	public String toSqlString() {
		return sb.toString();
	}
}
