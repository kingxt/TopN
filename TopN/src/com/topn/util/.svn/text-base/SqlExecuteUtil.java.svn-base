package com.topn.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-1 下午03:41:28
 * 
 * 语句执行模板，例如创建statement，resultset等等
 * 本类中的全部方法用静态方法
 */
public class SqlExecuteUtil {

	private static Logger logger = Logger.getLogger(SqlExecuteUtil.class);
	
	/**
	 * 创建一个statement
	 * @param conn
	 * @return
	 */
	public static Statement getStatement(Connection conn){
		Statement stat = null;
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， getStatement] 类中 创建Statement失败" + e.getMessage());
			e.printStackTrace();
		}
		return stat;
	}
	
	/**
	 * 创建一个PreparedStatement,但是这个结果集不能返回主键
	 * @param conn
	 * @param sql 
	 * @return
	 */
	public static PreparedStatement getPreparedStatement(Connection conn, String sql){
		
		PreparedStatement pstat = null;
		try {
			pstat = conn.prepareStatement(sql);
		} catch (SQLException e) {
			LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， getPreparedStatement] 类中 创建PreparedStatement失败" + e.getMessage());
			e.printStackTrace();
		}
		return pstat;
	}
	
	/**
	 * 创建能够添加数据后返回id的结果集
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getPreparedStatementWithReturnKey(Connection conn, String sql){
		
		PreparedStatement pstat = null;
		try {
			pstat = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， getPreparedStatement] 类中 创建PreparedStatement失败" + e.getMessage());
			e.printStackTrace();
		}
		return pstat;
	}
	
//	/**
//	 * 从Statment返回一个结果集
//	 * @param conn
//	 * @param sql
//	 * @return
//	 */
//	public static ResultSet createResultSetFromStatment(Connection conn, String sql){
//		Statement stat = SqlExecuteUtil.getStatement(conn);
//		ResultSet rs = null;
//		try {
//			rs = stat.executeQuery(sql);
//		} catch (SQLException e) {
//			LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， createResultSetFromStatment] 类中执行 " + sql +"失败" + e.getMessage());
//			e.printStackTrace();
//		}
//		return rs;
//	}
	
	/**
	 * 关闭结果集
	 * @param rs
	 */
	public static void closeResultSet(ResultSet rs){
		if(null != rs){
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， closeResultSet] 类中关闭ResultSet失败" + e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					if(rs != null){
						rs.close();
						rs = null;
					}					
				} catch (SQLException e) {
					LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， closeResultSet] 类中关闭ResultSet失败" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void closeCallableStatement(CallableStatement stat){
		if(stat != null){
			try{
				try {
					stat.close();
				} catch (SQLException e) {
					LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， closeCallableStatement] 类中关闭CallableStatement失败" + e.getMessage());
					e.printStackTrace();
				}
			}finally{
				try {
					if(stat != null){
						stat.close();
						stat = null;
					}
				} catch (SQLException e) {
					LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， closeCallableStatement] 类中关闭CallableStatement失败" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 关闭预编译结果集
	 * @param pstat
	 */
	public static void closePreparedStatement(PreparedStatement pstat){
		if(null != pstat){
			try {
				pstat.close();
				pstat = null;
			} catch (SQLException e) {
				LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， closePreparedStatement] 类中关闭PreparedStatement失败" + e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					if(pstat != null){
						pstat.close();
						pstat = null;
					}					
				} catch (SQLException e) {
					LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， closePreparedStatement] 类中关闭PreparedStatement失败" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 关闭Statement
	 * @param stat
	 */
	public static void closeStatement(Statement stat){
		if(null != stat){
			try {
				stat.close();
				stat = null;
			} catch (SQLException e) {
				LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， closeStatement] 类中关闭Statement失败" + e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					if(stat != null){
						stat.close();
						stat = null;
					}					
				} catch (SQLException e) {
					LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， closeStatement] 类中关闭Statement失败" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 关闭数据库链接
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		if(null != conn){
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， closeConnection] 类中关闭Connection失败" + e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					if(conn != null){
						conn.close();
						conn = null;
					}
				} catch (SQLException e) {
					LoggerUtil.loggerDebug(logger, "[SqlExecuteUtil， closeConnection] 类中关闭Connection失败" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 执行一个模板， 模板中的stat最好用addBatch(sql)方法执行，这样和
	 * 网络只链接一次
	 * @param conn
	 * @param dbTemplate
	 */
	public static void executeTemplate(Connection conn,DBTemplate dbTemplate){
		Statement stat = getStatement(conn);		
		try {
			dbTemplate.execute(stat);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行存储过程的模板，在这里控制了数据库连接的打开和关闭
	 * @param pe
	 * @param params 这里做一个修改是为了以后能够跟踪错误
	 */
	public static void executeProcedure(String procedureName, Object []params, ProcedureExecutor pe){
		Connection conn = ConnectionManager.getInstance().getConnection();
		CallableStatement stmt = null;
		try {
			stmt = conn.prepareCall(procedureName);
			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i+1, params[i]);
			}
			pe.execute(stmt);
		} catch (SQLException e) {
			String pa = "";
			for (int i = 0; i < params.length; i++) {
				pa += params[i] +", ";
			}
			LoggerUtil.loggerDebug(logger,
					"执行存储过程[" + procedureName
							+ "("+pa+")]失败" + e.getMessage());
			e.printStackTrace();
		}
		SqlExecuteUtil.closeStatement(stmt);
		SqlExecuteUtil.closeConnection(conn);
	}
	
	/**
	 * 执行存储过程的模板，在这里控制了数据库连接的打开和关闭
	 * 这个函数带返回值
	 * @param pe
	 */
	public static Object executeReturnProcedure(String procedureName, Object []params, ProcedureExecutorReturn pe){
		Connection conn = ConnectionManager.getInstance().getConnection();
		CallableStatement stmt = null;
		Object o = null;
		try {
			stmt = conn.prepareCall(procedureName);
			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i+1, params[i]);
			}
			o = pe.executeReturn(stmt);
		} catch (SQLException e) {
			String pa = "";
			for (int i = 0; i < params.length; i++) {
				pa += params[i] +", ";
			}
			LoggerUtil.loggerDebug(logger,
					"执行存储过程[" + procedureName
							+ "("+pa+")]失败" + e.getMessage());
			e.printStackTrace();
		}
		
		SqlExecuteUtil.closeStatement(stmt);
		SqlExecuteUtil.closeConnection(conn);
		return o;
	}
	
	/**
	 * 执行没有返回结果的sql
	 * @param sql
	 */
	public static void executeNoResultSql(String sql){
		Connection conn = ConnectionManager.getInstance().getConnection();
		Statement stmt = getStatement(conn);
		try {
			stmt.execute(sql);
		} catch (SQLException e) {
			LoggerUtil.loggerDebug(logger, "执行sql [" + sql + "]失败");
			e.printStackTrace();
		}
		SqlExecuteUtil.closeStatement(stmt);
		SqlExecuteUtil.closeConnection(conn);
	}
	
	/**
	 * 执行sql语句
	 * @param sql
	 * @param params
	 * @param dbTemplate
	 */
	public static void executeSql(String sql, DBTemplate dbTemplate){
		Connection conn = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			dbTemplate.execute(stmt);
		} catch (SQLException e) {
			LoggerUtil.loggerDebug(logger,
					"执行存储过程[" + sql
							+ "失败" + e.getMessage());
			e.printStackTrace();
		}
		SqlExecuteUtil.closeStatement(stmt);
		SqlExecuteUtil.closeConnection(conn);
	}
	
	/**
	 * 执行sql语句
	 * @param sql
	 * @param params
	 * @param dbTemplate
	 */
	public static Object executeSqlReturn(String sql, DBReturnTemplate dbTemplate){
		Connection conn = ConnectionManager.getInstance().getConnection();
		Statement stmt = null;
		Object o = null;
		try {
			stmt = conn.createStatement();
			o = dbTemplate.executeReturn(stmt);
		} catch (SQLException e) {
			LoggerUtil.loggerDebug(logger,
					"执行存储过程[" + sql
							+ "失败" + e.getMessage());
			e.printStackTrace();
		}
		SqlExecuteUtil.closeStatement(stmt);
		SqlExecuteUtil.closeConnection(conn);
		return o;
	}
}
