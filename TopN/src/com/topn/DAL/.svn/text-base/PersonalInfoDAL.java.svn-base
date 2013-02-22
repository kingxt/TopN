package com.topn.DAL;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.topn.util.DBTemplate;
import com.topn.util.ProcedureExecutor;
import com.topn.util.ProcedureExecutorReturn;
import com.topn.util.SqlExecuteUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-2 下午12:19:10
 * 
 * 个人信息处理类，类似这样的类用单粒模式
 */
public class PersonalInfoDAL extends BaseDAL {
	private static PersonalInfoDAL pid = new PersonalInfoDAL();
	
	private PersonalInfoDAL(){}
	
	public static PersonalInfoDAL getInstance(){
		return pid;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param username
	 * @param password
	 * @return 返回id为-1表示注册失败
	 */
	public int regiser(String tableName, String username, String password){
		
		String procedure = "{call " +SP_REGISTER +"(?, ?, ?, ?)}";
		Object obj = SqlExecuteUtil.executeReturnProcedure(procedure, new Object[]{tableName, username, password}, new ProcedureExecutorReturn(
				) {
			
			@Override
			public Object executeReturn(CallableStatement stmt) throws SQLException {
				stmt.registerOutParameter(4, Types.INTEGER);
				stmt.execute();
				return stmt.getInt(4);
			}
		});
		return Integer.parseInt(obj.toString());
	}
	
	/**
	 * 执行更新
	 * @param sql
	 */
	public void updatePerson(final String sql){
		SqlExecuteUtil.executeSql(sql, new DBTemplate() {
			@Override
			public void execute(Statement stmt) throws SQLException {
				stmt.executeUpdate(sql);
			}
		});
	}
	
	/**
	 * 执行删除
	 * @param sql
	 */
	public void delSystemTips(final String sql){
		SqlExecuteUtil.executeSql(sql, new DBTemplate() {
			
			@Override
			public void execute(Statement stmt) throws SQLException {
				stmt.executeUpdate(sql);
			}
		});
	}
	
	/**
	 * 删除心情提醒
	 */
	public void deleteMoodTips(int pid){
		String procedure = "{call " +SP_MOOD_TIPS_DELETE_BY_PID_AND_TYPE +"(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid, 1}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();
			}
		});
	}
	
	/**
	 * 删除留言提醒
	 */
	public void deleteleaveMsgTips(int pid){
		String procedure = "{call " +SP_MOOD_TIPS_DELETE_BY_PID_AND_TYPE +"(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid, 2}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();
			}
		});
	}
	
	/**
	 * 修改密码
	 * @param pid
	 * @param pwd 密码
	 */
	public void modifyPassword(final int pid, final String pwd){
		String procedure ="{call " +SP_PERSON_INFO_MODIFY_PWD +"(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid, pwd}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();
			}
		});
	}
	
	/**
	 * 修改密码
	 * @param tableName
	 * @param username
	 * @param password
	 */
	public void fogetPwd(final String tableName, final String username, final String password){
		String procedure ="{call " +SP_PERSON_INFO_FORGET_PWD +"(?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{tableName, username, password}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();
			}
		});
	}
}
