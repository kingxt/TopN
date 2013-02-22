package com.topn.DAL;

import java.sql.CallableStatement;
import java.sql.SQLException;

import com.topn.controller.help.RankStrategyHelp;
import com.topn.util.ProcedureExecutor;
import com.topn.util.SqlExecuteUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-27 下午12:24:46
 * 
 * 参赛照片更新和删除访问层
 */
public class EntryFormDAL extends BaseDAL{
	
	private static EntryFormDAL instance = new EntryFormDAL();

	private EntryFormDAL() {
	}
	

	public static EntryFormDAL getInstance() {
		return instance;
	}
	
	/**
	 * 将讨论拼写成的字符串插入数据库中去
	 * @param sql
	 */
	public void asynGradeTODB(String sql){
		String procedureName = "{call "+EntryFormDAL.SP_UPDATE_ENTRY_FORM+"(?)}";
		SqlExecuteUtil.executeProcedure(procedureName, new Object[]{sql}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();
			}
		});
	}
	
	/**
	 * 更新一条记录到数据库
	 * @param id
	 * @param grade
	 */
	public void updateAnEntryForm(final int id, final float grade){
		String procedure = "{call "+EntryFormDAL.SP_ENTRY_FORM_UPDATE_ONE+"(?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{id, grade, RankStrategyHelp.C}, new ProcedureExecutor() {
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();
			}
			
		});
	}
}
