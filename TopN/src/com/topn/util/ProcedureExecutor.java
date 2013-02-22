package com.topn.util;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-25 下午12:21:55
 * 
 * 存储过程执行器
 */
public interface ProcedureExecutor {
	/**
	 * 存储过程执行器
	 * @param stat
	 * @return 
	 * @throws SQLException 
	 */
	
	
	public void execute(CallableStatement stmt) throws SQLException;
}
