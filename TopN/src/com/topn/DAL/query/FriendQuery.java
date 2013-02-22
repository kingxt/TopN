package com.topn.DAL.query;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.topn.bean.TO.FriendChatTO;
import com.topn.bean.TO.FriendRecentMoodTO;
import com.topn.bean.TO.FriendTO;
import com.topn.bean.TO.LeaveMsgTO;
import com.topn.bean.TO.RecentVisitorTO;
import com.topn.bean.TO.RecommendFriendTO;
import com.topn.bean.TO.ReplyLeaveMsgTO;
import com.topn.bean.TO.ReplyTO;
import com.topn.util.DBReturnTemplate;
import com.topn.util.DBTemplate;
import com.topn.util.DateUitl;
import com.topn.util.FileUtil;
import com.topn.util.ProcedureExecutor;
import com.topn.util.ProcedureExecutorReturn;
import com.topn.util.SqlExecuteUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-11 下午04:26:35
 * 
 * 朋友信息查询类
 */
public class FriendQuery extends BaseQueryDAL{
	private static FriendQuery instance = new FriendQuery();
	private FriendQuery() {
	}

	public static FriendQuery getInstance() {
		return instance;
	}
	
	/**
	 * 根据条件查询注册人员
	 * @param sql
	 * @return
	 */
	public List<FriendTO> searchPerson(final String sql){
		final List<FriendTO> fts = new ArrayList<FriendTO>();
		SqlExecuteUtil.executeSql(sql, new DBTemplate() {
			
			@Override
			public void execute(Statement stat) throws SQLException {
				ResultSet rs = stat.executeQuery(sql);				
				while(rs.next()){
					FriendTO ft = new FriendTO();
					ft.setId(rs.getInt(1));
					ft.setName(rs.getString(2));
					ft.setCollege(rs.getString(3));
					ft.setHighSchool(rs.getString(4));
					ft.setUrl(rs.getString(5));
					ft.setHobby(rs.getString(6));
					ft.setPs(rs.getString(7));
					
					ft.setUrl(FileUtil.swithPersonPhototo50(ft.getUrl()));//处理图片
					fts.add(ft);
				}
			}
		});	
		return fts;
	}
	
	/**
	 * 搜索  总数接口
	 * @param sql
	 * @return
	 */
	public int getPersonCount(final String sql){
		Object obj = SqlExecuteUtil.executeSqlReturn(sql, new DBReturnTemplate() {
			@Override
			public Object executeReturn(Statement stat) throws SQLException {
				ResultSet rs =  null;
				int count = 0;
				rs = stat.executeQuery(sql);
				while(rs.next()){
					count += rs.getInt(1);
				}
				return count;
			}
		});
		return Integer.parseInt(obj.toString());
	}
	
	/**
	 * 查所有好友最近的心情
	 * @return
	 */
	public List<FriendRecentMoodTO> getFriendRecentMood(final int id, final Timestamp begin, final Timestamp end, final int pageNum){
		final List<FriendRecentMoodTO> frs = new ArrayList<FriendRecentMoodTO>();
		String procedure = "{call " + SP_MOOD_SELECT_ALL_RECENT_MOOD + "(?, ?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{id, begin, end, pageNum}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					FriendRecentMoodTO fr = new FriendRecentMoodTO();
					//心情的基本信息
					fr.setMoodPersonId(rs.getInt(1));
					fr.setMoodPersonName(rs.getString(2));
					fr.setMoodPersonURL(rs.getString(3));
					fr.setMoodId(rs.getInt(4));
					fr.setMoodMsg(rs.getString(5));
					fr.setMoodPublishTime(DateUitl.localDateShow(rs.getTimestamp(6)));
					fr.setTotalReply(rs.getInt(7));
					
					fr.setMoodPersonURL(FileUtil.swithPersonPhototo50(fr.getMoodPersonURL()));//处理图片
					
					//第一个回复者的信息
					fr.setFirstReplyId(rs.getInt(8));
					fr.setFirstReplyPersonName(rs.getString(9));
					fr.setFirstReplyPersonURL(rs.getString(10));
					fr.setReplyTopId(rs.getInt(11));
					fr.setFirstReplyPersonMsg(rs.getString(12));
					fr.setFirstReplyPersonTime(DateUitl.localDateShow(rs.getTimestamp(13)));
					
					fr.setFirstReplyPersonURL(FileUtil.swithPersonPhototo50(fr.getFirstReplyPersonURL()));//处理图片
					
					
					//最后一个回复者的信息
					fr.setLastReplyId(rs.getInt(14));
					fr.setLastReplyPersonName(rs.getString(15));
					fr.setLastReplyPersonURL(rs.getString(16));
					fr.setReplyLastId(rs.getInt(17));
					fr.setLastReplyPersonMsg(rs.getString(18));
					fr.setLastReplyPersonTime(DateUitl.localDateShow(rs.getTimestamp(19)));
					
					fr.setLastReplyPersonURL(FileUtil.swithPersonPhototo50(fr.getLastReplyPersonURL()));//处理图片
					
					frs.add(fr);
				}
			}
			
		});
		return frs;		
	}
	
	/**
	 * 获取一条回复的所有回复记录
	 * @param personId
	 * @param moodId
	 * @return
	 */
	public List<ReplyTO> getMoodAllReplies(final int personId, final int moodId){
		
		final List<ReplyTO> rts = new ArrayList<ReplyTO>();
		String procedure = "{call " + SP_MOOD_SELECT_ALL_REPLY + "(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{personId, moodId}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ReplyTO fr = new ReplyTO();
					//心情的基本信息
					fr.setReplyId(rs.getInt(1));
					fr.setReplyPersonId(rs.getInt(2));
					fr.setReplyPersonName(rs.getString(3));
					fr.setReplyPersonURL(rs.getString(4));
					fr.setReplyMsg(rs.getString(5));
					fr.setReplyDate(DateUitl.localDateShow(rs.getTimestamp(6)));
					fr.setReplyPersonURL(FileUtil.swithPersonPhototo50(fr.getReplyPersonURL()));//处理图片
					rts.add(fr);
				}
			}
			
		});
		return rts;		
	}
	
	/**
	 * 获取那类型的好友，例如好友，密友，按照类型查找
	 * @param pid 用户
	 * @param type 类型
	 * @param pageNum 第几页
	 * @return
	 */
	public List<FriendTO> getFriendsByType(final int pid, final int type, final int pageNum){
		final List<FriendTO> fts = new ArrayList<FriendTO>();
	
		String procedure = "{call " + SP_FRIENDS_SELECTBY_RELATE + "(?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid, type, pageNum}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					FriendTO ft = new FriendTO();
					ft.setId(rs.getInt(1));
					ft.setName(rs.getString(2));
					ft.setUrl(rs.getString(3));
					ft.setCollege(rs.getString(4));
					ft.setHighSchool(rs.getString(5));
					ft.setHobby(rs.getString(6));
					ft.setPs(rs.getString(7)); 
					ft.setUrl(FileUtil.swithPersonPhototo50(ft.getUrl()));//处理图片
					fts.add(ft);
				}
			}
		});
		return fts;
	}
	
	/**
	 * 获取好友的好友
	 * @param pid 好友的id
	 * @param pageNum 分页号
	 * @return
	 */
	public List<FriendTO> getFriendFriends(final int pid, final int pageNum){
		final List<FriendTO> fts = new ArrayList<FriendTO>();
	
		String procedure = "{call " + SP_FRIENDS_SELECT_FRIENDS_OF_OTHER + "(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid, pageNum}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					FriendTO ft = new FriendTO();
					ft.setId(rs.getInt(1));
					ft.setName(rs.getString(2));
					ft.setUrl(rs.getString(3));
					ft.setCollege(rs.getString(4));
					ft.setHighSchool(rs.getString(5));
					ft.setHobby(rs.getString(6));
					ft.setPs(rs.getString(7)); 
					ft.setUrl(FileUtil.swithPersonPhototo50(ft.getUrl()));//处理图片
					fts.add(ft);
				}
			}
			
		});
		return fts;
	}

	/**
	 * 查找自己所有的好友
	 * @param pid
	 * @return
	 */
	public List<FriendChatTO> getFriendChatTO(final int pid){
		final List<FriendChatTO> fto = new ArrayList<FriendChatTO>();
		String procedure = "{call " + SP_FRIENDS_SELECT_ALL_FRIENDS + "(?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					FriendChatTO ft = new FriendChatTO();
					ft.setId(rs.getInt(1));
					ft.setName(rs.getString(2));
					ft.setUrl(rs.getString(3));
					ft.setType(rs.getInt(4));
					
					ft.setUrl(FileUtil.swithPersonPhototo50(ft.getUrl()));//处理图片
					fto.add(ft);
				}			
			}
			
		});
		return fto;
	}
	
	/**
	 * 获取所有的留言
	 * @return
	 */
	public List<LeaveMsgTO> getLeaveMsgs(final int pid, final int pageNum){
		final List<LeaveMsgTO> fto = new ArrayList<LeaveMsgTO>();
		String procedure = "{call " + SP_NOTES_SELECT_NOTEBY_TARGET_ID + "(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure,new Object[]{pid, pageNum}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					LeaveMsgTO ft = new LeaveMsgTO();
					ft.setPublishId(rs.getInt(1));
					ft.setName(rs.getString(2));
					ft.setUrl(rs.getString(3));
					ft.setLeaveMsgId(rs.getInt(4));
					ft.setContent(rs.getString(5));
					ft.setPublishTime(DateUitl.localDateShow(rs.getTimestamp(6)));
					ft.setTotal(rs.getInt(7));
					
					ft.setUrl(FileUtil.swithPersonPhototo50(ft.getUrl()));//处理图片
					fto.add(ft);
				}			
			}
			
		});
		
		return fto;
	}
	
	/**
	 * 获取留言的所有回复
	 * @param leaveMsgId
	 * @return
	 */
	public List<ReplyTO> getReplyTO(final int leaveMsgId){
		
		final List<ReplyTO> rts = new ArrayList<ReplyTO>();
		
		String procedure = "{call " + SP_REPLY_NOTE_SELECTBY_NOTE_ID + "(?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{leaveMsgId}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ReplyTO fr = new ReplyTO();
					//心情的基本信息
					fr.setReplyId(rs.getInt(1));
					fr.setReplyPersonId(rs.getInt(2));
					fr.setReplyPersonName(rs.getString(3));
					fr.setReplyPersonURL(rs.getString(4));
					fr.setReplyMsg(rs.getString(5));
					fr.setReplyDate(DateUitl.localDateShow(rs.getTimestamp(6)));
					
					fr.setReplyPersonURL(FileUtil.swithPersonPhototo50(fr.getReplyPersonURL()));//处理图片
					rts.add(fr);
				}
			}
			
		});
		return rts;		
	}
	
	/**
	 * 留言
	 * @param publishId
	 * @param targetId
	 * @param msg
	 * @return
	 */
	public void leaveMsg(final ReplyLeaveMsgTO lmt){		
		String procedure = "{call " + SP_REPLY_NOTE_ADD_REPLY + "(?, ?, ?, ?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{lmt.getLeaveMsgId(), lmt.getParentId(),
				lmt.getPublishId(), lmt.getLeaveMsg(), lmt.getTotal()}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.registerOutParameter(6, Types.INTEGER);
				stmt.execute();
				int id = stmt.getInt(6);
				lmt.setReplyId(id);
			}
			
		});
	}
	
	/**
	 * 获取最近访客
	 * @param pid
	 * @return
	 */
	public List<RecentVisitorTO> getRecentVisitorTO(final int pid){
		String procedure = "{call " + SP_RECENT_VISIT_SELECTBY_ID + "(?)}";
		final List<RecentVisitorTO> rvts = new ArrayList<RecentVisitorTO>();
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid}, new ProcedureExecutor() {
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					RecentVisitorTO rt = new RecentVisitorTO();
					rt.setVisitorId(rs.getInt(1));
					rt.setName(rs.getString(2));
					rt.setUrl(rs.getString(3));
					rt.setUrl(FileUtil.swithPersonPhototo50(rt.getUrl()));
					rvts.add(rt);
				}
			}
		});
		return rvts;
	}
	
	/**
	 * @param pid
	 * @param relType
	 * @return
	 */
	public int getFriendCount(final int pid, final int relType){
		String procedure = "{call " + SP_FRIENDS_COUNT_FRIEND_NUM + "(?, ?, ?)}";
		Object obj = SqlExecuteUtil.executeReturnProcedure(procedure, new Object[]{pid, relType}, new ProcedureExecutorReturn() {
			@Override
			public Object executeReturn(CallableStatement stmt)
					throws SQLException {
				stmt.registerOutParameter(3, Types.INTEGER);
				stmt.execute();
				int sum = stmt.getInt(3);
				return sum;
			}
		});
		int sum = Integer.parseInt(obj.toString());
		return sum;
	}
	
	/**
	 * 
	 * @param pid 用户的id
	 * @param school 用户所在学校
	 * @param type 学校类型1 大学，2 高中
	 * @return
	 */
	public List<RecommendFriendTO> getRecommendFriendTO(final int pid, final String school, final int type){
		String procedure = "{call " + SP_FRIENDS_RECOMMEND_FRIEND + "(?, ?, ?)}";
		final List<RecommendFriendTO> rts = new ArrayList<RecommendFriendTO>();
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid, school, type}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					RecommendFriendTO rt = new RecommendFriendTO();
					rt.setId(rs.getInt(1));
					rt.setName(rs.getString(2));
					rt.setUrl(rs.getString(3));
					rt.setUrl(FileUtil.swithPersonPhototo50(rt.getUrl()));
					rts.add(rt);
				}
			}
		});
		return rts;
	}
	
	/**
	 * 自己给评论朋友流的言
	 * @return
	 */
	public List<FriendRecentMoodTO> getMyMsg2Friend(final int otherId,  final int pid){
		final List<FriendRecentMoodTO> frs = new ArrayList<FriendRecentMoodTO>();
		String procedure = "{call " + SP_NOTES_SELECT_WHEN_VISIT_OTHERS + "(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{otherId, pid}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					FriendRecentMoodTO fr = new FriendRecentMoodTO();
					//心情的基本信息
					fr.setMoodPersonId(rs.getInt(1));
					fr.setMoodPersonName(rs.getString(2));
					fr.setMoodPersonURL(rs.getString(3));
					fr.setMoodId(rs.getInt(4));
					fr.setMoodMsg(rs.getString(5));
					fr.setMoodPublishTime(DateUitl.localDateShow(rs.getTimestamp(6)));
					fr.setMoodDate(rs.getDate(6));
					fr.setTotalReply(rs.getInt(7));
					
					fr.setMoodPersonURL(FileUtil.swithPersonPhototo50(fr.getMoodPersonURL()));//处理图片
					
					//第一个回复者的信息
					fr.setFirstReplyId(rs.getInt(8));
					fr.setFirstReplyPersonName(rs.getString(9));
					fr.setFirstReplyPersonURL(rs.getString(10));
					fr.setReplyTopId(rs.getInt(11));
					fr.setFirstReplyPersonMsg(rs.getString(12));
					fr.setFirstReplyPersonTime(DateUitl.localDateShow(rs.getTimestamp(13)));
					
					fr.setFirstReplyPersonURL(FileUtil.swithPersonPhototo50(fr.getFirstReplyPersonURL()));//处理图片
					
					
					//最后一个回复者的信息
					fr.setLastReplyId(rs.getInt(14));
					fr.setLastReplyPersonName(rs.getString(15));
					fr.setLastReplyPersonURL(rs.getString(16));
					fr.setReplyLastId(rs.getInt(17));
					fr.setLastReplyPersonMsg(rs.getString(18));
					fr.setLastReplyPersonTime(DateUitl.localDateShow(rs.getTimestamp(19)));
					
					fr.setLastReplyPersonURL(FileUtil.swithPersonPhototo50(fr.getLastReplyPersonURL()));//处理图片
					
					frs.add(fr);
				}
			}
			
		});
		return frs;		
	}
	
}
