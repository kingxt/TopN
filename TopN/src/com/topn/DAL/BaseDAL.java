package com.topn.DAL;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.topn.util.ConnectionManager;
import com.topn.util.GenerateSql;
import com.topn.util.LoggerUtil;
import com.topn.util.SqlExecuteUtil;
import com.topn.util.Table;
import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-1 下午09:00:27
 * 
 * 最底层访问数据库的layer, 每个业务模块可以重写此类方法来实现自己的业务逻辑
 * 继承这个类的每个模块的dal都要用单粒实现
 */
public class BaseDAL implements Table{
	private static Logger logger = Logger.getLogger(BaseDAL.class);

	public void save(Object obj){
		String sql = "";
		Connection conn = ConnectionManager.getInstance().getConnection();
		Statement stat = SqlExecuteUtil.getStatement(conn);
		try {
			sql = GenerateSql.generateCreateSql(obj, false);
			stat.execute(sql);			
		} catch (Exception e) {
			LoggerUtil.loggerDebug(logger, "[BaseDAL， save] 类中生成" + sql +"失败或者sql执行失败" + e.getMessage());
			e.printStackTrace();
		}
		SqlExecuteUtil.closeStatement(stat);
		SqlExecuteUtil.closeConnection(conn);
	}
	
	public void update(String sql){
		Connection conn = ConnectionManager.getInstance().getConnection();
		Statement stat = SqlExecuteUtil.getStatement(conn);
		try {
			stat.execute(sql);
		} catch (SQLException e) {
			LoggerUtil.loggerDebug(logger, "[BaseDAL， update] 类中生成" + sql +"失败" + e.getMessage());
			e.printStackTrace();
		}
		SqlExecuteUtil.closeStatement(stat);
		SqlExecuteUtil.closeConnection(conn);
	}
	
	/**
	 * 执行删除操作，这里主要是要慎用，最后重写
	 * id查找依据是指定@PropertyAlias中isId为true的字段
	 * @param id
	 * @param clazz
	 */
	@SuppressWarnings("unchecked")
	public void delete(int id, Class clazz){
		Connection conn = ConnectionManager.getInstance().getConnection();
		Statement stat = SqlExecuteUtil.getStatement(conn);
		String sql = null;
		try {
			TableName tn = (TableName)clazz.getAnnotation(TableName.class);
			if(tn == null){		
				sql = "delete from " + clazz.getSimpleName().toLowerCase();
			}else{
				sql = "delete from " + tn.name();
			}
			String idName = "";
			Field []fields = clazz.getDeclaredFields();
			//找到id的别名
			for(int i = 0; i < fields.length; i++){
				if(fields[i].isAnnotationPresent(PropertyAlias.class)){
					PropertyAlias pa = fields[i].getAnnotation(PropertyAlias.class);
					if(pa.isId()){
						idName = pa.alias();
						break;
					}
				}
			}
			sql = sql + " where "+ idName +" = " +id;
			stat.execute(sql);
		} catch (SQLException e) {
			LoggerUtil.loggerDebug(logger, "[BaseDAL， update] 类中删除id[" + id +"]类" +clazz.getName()+" 失败" + e.getMessage());
			e.printStackTrace();
		}
		SqlExecuteUtil.closeStatement(stat);
		SqlExecuteUtil.closeConnection(conn);
	}
	
	/**
	 * 执行带参数但是不带返回值的存储过程
	 * @param procedureName 存储过程名
	 * @param param 输入参数
	 */
	public void executeProcedure(String procedureName, String param[]){
		if("".equals(procedureName)){
			return;
		}
		StringBuilder procedure = new StringBuilder("{call " + procedureName + "(");
		if(param.length > 0){
			for (int i = 0; i < param.length; i++) {
				procedure.append(param[i] + ",");
			}			
		}
		procedure.substring(0, procedure.lastIndexOf(","));
		procedure.append(")}");
		Connection conn = ConnectionManager.getInstance().getConnection();
		CallableStatement stmt = null;   
		try {
			stmt = conn.prepareCall(procedure.toString());
			stmt.execute();
		} catch (SQLException e1) {
			LoggerUtil.loggerDebug(logger, "[BaseDAL， executeProcedure] 类中执行存储过程[" + procedureName +"]失败" + e1.getMessage());
			e1.printStackTrace();
		}
		SqlExecuteUtil.closeStatement(stmt);
		SqlExecuteUtil.closeConnection(conn);
	}
}
