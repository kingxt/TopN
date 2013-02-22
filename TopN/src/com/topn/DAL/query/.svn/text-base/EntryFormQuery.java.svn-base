package com.topn.DAL.query;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.topn.bean.EntryForm;
import com.topn.bean.TO.RankTO;
import com.topn.controller.help.RankStrategyHelp;
import com.topn.util.ProcedureExecutor;
import com.topn.util.ProcedureExecutorReturn;
import com.topn.util.SqlExecuteUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-3 下午11:18:57
 * 
 * 在线评价相片的所有查询类，单粒模式
 */
public class EntryFormQuery extends BaseQueryDAL{
	
	private static EntryFormQuery instance = new EntryFormQuery();
	
	private EntryFormQuery(){}
	
	public static EntryFormQuery getInstance(){
		return instance;
	}

	/**
	 * 获取排行榜
	 * @return
	 */
	public List<EntryForm> getTopN(final int sex){
		final List<EntryForm> ranks = new ArrayList<EntryForm>();
		String procedure = "{call "+RANKING_LIST+"(?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{sex}, new ProcedureExecutor() {
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();	
				while(rs.next()){
					EntryForm ef = new EntryForm();
					ef.setPersonInfoId(rs.getInt(1));
					ef.setUrl(rs.getString(2));
					ef.setType(sex +"a");
					ranks.add(ef);
				}
			}
			
		});
		return ranks;
	}
	
	/**
	 * 由于制定的年龄段没有找到照片，这里只能够从数据库中找
	 * @param age1   年龄下限
	 * @param age2 年龄上限
	 * @param sex  性别
	 * @return
	 */
	public EntryForm selectOne(final int age1, final int age2, final int sex){	
		String procedure = "{call "+SP_ENTRY_FORM_SELECT_ONE+"(?, ?, ?)}";
		return (EntryForm) SqlExecuteUtil.executeReturnProcedure(procedure, new Object[]{age1, age2, sex}, new ProcedureExecutorReturn() {

			@Override
			public Object executeReturn(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				if(rs.next()){ 
					EntryForm ef = new EntryForm();
					ef.setEntryId(rs.getInt(1));
					ef.setPersonInfoId(rs.getInt(2));
					ef.setUrl(rs.getString(3));
					ef.setTimes(rs.getInt(4));
					ef.setTotalScore(rs.getFloat(5));
					return ef;
				}
				return null;
			}
			
		});
	}
	
	/**
	 *  更新缓存评论照片后获取新的照片
	 * @return
	 */
	public List<EntryForm> getNewEntryForm(final int count, final int a1, final int a2){
		if(count <= 0){
			return null;
		}
		
		final List<EntryForm> newPic = new ArrayList<EntryForm>();
		String procedure = "{call "+SP_ENTRY_FORM_GETLIST_BY_AGE+"(?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{count, a1, a2}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();	
				while(rs.next()){
					EntryForm ef = new EntryForm();
					ef.setEntryId(rs.getInt(1));
					ef.setPersonInfoId(rs.getInt(2));
					ef.setUrl(rs.getString(3));
					ef.setTimes(rs.getInt(4));
					ef.setTotalScore(rs.getFloat(5));
					ef.setSex(rs.getInt(6));
					ef.setAge(rs.getInt(7));
					ef.setAverageScore(rs.getFloat(8));
					newPic.add(ef);
				}
			}
			
		});
		return newPic;
	}
	
	/**
	 * 
	 * @param type 1a 2a
	 * @param school 学校
	 * @param sex 性别
	 * @param level 入学年份
	 * @param num 分页号
	 * @param sn 这个冗余字段标识1：大学，2：高中
	 * @return
	 */
	public List<EntryForm> getRankListByType(final String type, final String school, final int sex, final int level, final int num, final int sn){
		String procedure = "{call "+SP_ENTRY_FORM_GET_RANKING_LIST_BY_SCHOOL+"(?, ?, ?, ?, ?)}";
		final List<EntryForm> newPic = new ArrayList<EntryForm>();
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{school, level, sex, sn, num}, 
				new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();	
				while(rs.next()){
					EntryForm ef = new EntryForm();
					ef.setPersonInfoId(rs.getInt(1));
					ef.setUrl(rs.getString(2));
					ef.setType(type);
					newPic.add(ef);
				}
			}
			
		});
		return newPic;
	}
	
	/**
	 * 获取名次
	 * @return
	 */
	public RankTO getMyRankPos(final int pid){
		String procedure = "{call "+SP_ENTRY_FORM_GET_PERSONAL_RANK+"(?, ?, ?)}";
		RankTO obj = (RankTO) SqlExecuteUtil.executeReturnProcedure(procedure, new Object[]{pid}, new ProcedureExecutorReturn() {

			@Override
			public Object executeReturn(CallableStatement stmt) throws SQLException {
				stmt.registerOutParameter(2, Types.INTEGER);
				stmt.registerOutParameter(3, Types.INTEGER);
				stmt.execute();
				int rak = stmt.getInt(2);
				int times = stmt.getInt(3);
				RankTO tt = new RankTO();
				tt.setRankNum(rak);
				tt.setRankTimes(times);
				return tt;
			}			
		});
		return obj;
	}
	
	/**
	 * 获取所有评论的平均分
	 * @return
	 */
	public void getStaticC(){
		String procedure = "{call "+SP_ENTRY_FORM_GET_GLOBAL_AVERAGE+"(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{}, new ProcedureExecutor() {
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.registerOutParameter(1, Types.LONGNVARCHAR);
				stmt.registerOutParameter(2, Types.DOUBLE);
				stmt.execute();
				RankStrategyHelp.updateC(stmt.getLong(1), stmt.getDouble(2));
			}			
		});
	}
}
