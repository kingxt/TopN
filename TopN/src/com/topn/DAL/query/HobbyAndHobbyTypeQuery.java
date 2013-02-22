package com.topn.DAL.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.topn.bean.Hobby;
import com.topn.bean.HobbyType;
import com.topn.util.ConnectionManager;
import com.topn.util.LoggerUtil;
import com.topn.util.SqlExecuteUtil;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-26 下午02:16:34
 * 
 * 查询兴趣类和兴趣类型类
 */
public class HobbyAndHobbyTypeQuery extends BaseQueryDAL {
	private static Logger logger = Logger
			.getLogger(HobbyAndHobbyTypeQuery.class);
	private static HobbyAndHobbyTypeQuery instance = new HobbyAndHobbyTypeQuery();

	private HobbyAndHobbyTypeQuery() {
	}

	public static HobbyAndHobbyTypeQuery getInstance() {
		return instance;
	}

	public List<HobbyType> getAllHobbyTypes() {
		Connection conn = ConnectionManager.getInstance().getConnection();

		ResultSet rs = null;
		List<HobbyType> hts = new ArrayList<HobbyType>();
		Statement stat = null;
		try {
			String sql = "select * from hobby_type";
			stat = SqlExecuteUtil.getStatement(conn);
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				HobbyType ht = new HobbyType();
				ht.setTypeId(rs.getInt(1));
				ht.setName(rs.getString(2));
				hts.add(ht);
			}
		} catch (SQLException e1) {
			LoggerUtil.loggerDebug(logger,
					"[HobbyAndHobbyTypeQuery， getAllHobbyTypes] 获取兴趣类型失败"
							+ e1.getMessage());
			e1.printStackTrace();
		}
		SqlExecuteUtil.closeResultSet(rs);
		SqlExecuteUtil.closeStatement(stat);
		SqlExecuteUtil.closeConnection(conn);
		return hts;
	}

	/**
	 * 根据hobbytype加载hobby
	 * @param typeId
	 * @return
	 */
	public List<Hobby> loadHobbyByType(int typeId) {
		if(0 == typeId) return null;
		Connection conn = ConnectionManager.getInstance().getConnection();

		ResultSet rs = null;
		List<Hobby> hbs = new ArrayList<Hobby>();
		Statement stat = null;
		try {
			String sql = "select hobby_id, name from hobby where type_id = " + typeId;
			stat = SqlExecuteUtil.getStatement(conn);
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Hobby ht = new Hobby();
				ht.setHobbyId(rs.getInt(1));
				ht.setName(rs.getString(2));
				hbs.add(ht);
			}
		} catch (SQLException e1) {
			LoggerUtil.loggerDebug(logger,
					"[HobbyAndHobbyTypeQuery， loadHobbyByType] 获取兴趣类型失败 ，兴趣类型id是" + typeId
							+ "   "+e1.getMessage());
			e1.printStackTrace();
		}
		SqlExecuteUtil.closeResultSet(rs);
		SqlExecuteUtil.closeStatement(stat);
		SqlExecuteUtil.closeConnection(conn);
		return hbs;
	}
}
