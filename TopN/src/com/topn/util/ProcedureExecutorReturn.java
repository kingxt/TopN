package com.topn.util;

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * 
 * 创建人 kingxt
 * 创建时间：2011-7-1 下午05:10:43
 */
public interface ProcedureExecutorReturn {

	public Object executeReturn(CallableStatement stmt) throws SQLException;
}
