package com.topn.DAL.query;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.topn.bean.Expression;
import com.topn.util.ProcedureExecutor;
import com.topn.util.SqlExecuteUtil;

/**
 * 
 * 创建人 KingXt 创建时间：2011-5-11 下午12:54:09
 * 
 * 数据字典查询类，用此类来实现某些无联系信息查询，例如 表情
 */
public class CDDQuery extends BaseQueryDAL {

	private static CDDQuery instance = new CDDQuery();

	private CDDQuery() {
	}

	public static CDDQuery getInstance() {
		return instance;
	}

	/**
	 * 获取所有的表情
	 * @return
	 */
	public List<Expression> getAllExpression() {

		final List<Expression> rts = new ArrayList<Expression>();
		String procedure = "{call " + SP_EXPRESSION_SELECT_ALL + "()}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Expression e = new Expression();
					// 心情的基本信息
					e.setId(rs.getInt(1));
					e.setTitle(rs.getString(2));
					e.setUrl(rs.getString(3));
					rts.add(e);
				}
			}

		});
		return rts;
	}
}
