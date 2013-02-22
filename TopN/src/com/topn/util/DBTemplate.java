package com.topn.util;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-1 下午05:16:55
 * 
 * 执行多条sql语句的接口，最好一次不要执行过多的sql语句
 */
public interface DBTemplate {

	/**
	 * 利用stat执行多条sql语句
	 * @param stat
	 */
	public void execute(Statement stmt) throws SQLException;
	
	
	
}
