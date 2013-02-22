package com.topn.DAL;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;


import com.topn.bean.LeaveMsg;
import com.topn.bean.Mood;
import com.topn.bean.MoodTips;
import com.topn.bean.ReplyMood;
import com.topn.util.DBTemplate;
import com.topn.util.ProcedureExecutor;
import com.topn.util.ProcedureExecutorReturn;
import com.topn.util.SqlExecuteUtil;

/**
 * 
 * 创建人 KingXt 创建时间：2011-4-10 上午10:35:24
 * 
 * 朋友管理类，此类不包括 查询操作
 */
public class FriendDAL extends BaseDAL {
	private static FriendDAL instance = new FriendDAL();

	private FriendDAL() {
	}

	public static FriendDAL getInstance() {
		return instance;
	}

	/**
	 * 发表心情
	 * 
	 * @param mood
	 */
	public void publishMood(Mood mood) {
		String procedure = "{call " + SP_MOOD_PUBLISH_MOOD + "(?, ?, ?, ?, ?)}";
		Object obj = SqlExecuteUtil.executeReturnProcedure(procedure,
				new Object[] { mood.getTableName(), mood.getPersonalId(),
						mood.getMessage(), mood.getPriv() },
				new ProcedureExecutorReturn() {
					@Override
					public Object executeReturn(CallableStatement stmt)
							throws SQLException {
						stmt.registerOutParameter(5, Types.INTEGER);
						stmt.execute();
						return stmt.getInt(5);
					}
				});
		int id = Integer.parseInt(obj.toString());
		mood.setMoodId(id);
	}

	/**
	 * 回复心情
	 * 
	 * @param replyMood
	 * @return
	 */
	public void replyMood(final ReplyMood replyMood) {
		String procedure = "{call " + SP_REPLY_MOOD_ADD_REPLY
				+ "(?, ?, ?, ?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[] {
				replyMood.getMoodPersonId(), replyMood.getMoodId(),
				replyMood.getParentId(), replyMood.getPublisherId(),
				replyMood.getMessage() }, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.registerOutParameter(6, Types.INTEGER);
				stmt.execute();
				int id = stmt.getInt(6);
				replyMood.setReplyId(id);
			}
		});
	}

	/**
	 * 添加好友
	 * 
	 * @param owerId
	 *            主人
	 * @param friendId
	 *            好友id
	 * @param friendType
	 *            好友类型
	 * @param msg
	 *            留给好友的信息
	 */
	public String addFriend(final int owerId, final int friendId,
			final int friendType, final String msg) {
		String procedure = "{call " + SP_FRIEND_TEMP_REPLY_ADD_FRIEND
				+ "(?, ?, ?, ?, ?)}";
		String re = (String) SqlExecuteUtil.executeReturnProcedure(procedure,
				new Object[] { owerId, friendId, friendType, msg },
				new ProcedureExecutorReturn() {

					@Override
					public Object executeReturn(CallableStatement stmt)
							throws SQLException {
						stmt.registerOutParameter(5, Types.VARCHAR);
						stmt.execute();
						return stmt.getString(5);
					}

				});
		return re;
	}

	/**
	 * 删除心情
	 * 
	 * @param pid
	 *            那个用户端
	 * @param replyId
	 *            那条回复
	 */
	public void deleteReply(int pid, int moodId, int replyId) {
		String procedure = "{call " + SP_REPLY_MOOD_DELETE_REPLY_BYID
				+ "(?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[] { pid, moodId,
				replyId }, new ProcedureExecutor() {
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();
			}
		});
	}

	/**
	 * 修改朋友类型
	 * 
	 * @param pid
	 * @param friendId
	 * @param friendType
	 */
	public void modifyFriendType(final String sql) {
		SqlExecuteUtil.executeSql(sql, new DBTemplate() {

			@Override
			public void execute(Statement stat) throws SQLException {
				stat.execute(sql);
			}
		});
	}

	/**
	 * 添加心情回复提醒
	 * 
	 * @param mt
	 */
	public void addMoodTips(MoodTips mt) {
		String procedure = "{call " + SP_MOOD_TIPS_ADD_TIPS + "(?, ?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[] {
				mt.getReplyId(), mt.getRemindId(), mt.getMoodId(),
				mt.getMoodOwerId() }, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();

			}
		});

	}

	/**
	 * 添加好友
	 * 
	 * @param pid
	 * @param friendId
	 * @param flag
	 *            是否同意申请 (flag=1表示拒绝，2表示同意)
	 */
	public void realAddFriend(int pid, int friendId, String name,
			int friendType, int flag) {

		String procedure = "{call " + SP_FRIENDS_CONFIRM_FROM_TEMP
				+ "(?, ?, ?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[] { friendId,
				pid, name, friendType, flag }, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();

			}
		});
	}

	/**
	 * 删除某个人的某条心情
	 * 
	 * @param pid
	 * @param moodId
	 */
	public void deleteMood(int pid, int moodId) {
		String procedure = "{call " + SP_MOOD_DELETE_BYMID + "(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid, moodId}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();
			}
		});
	}

	/**
	 * 删除好友
	 * 
	 * @param sql
	 */
	public void deleteFriend(final String sql) {
		SqlExecuteUtil.executeSql(sql, new DBTemplate() {
			
			@Override
			public void execute(Statement stmt) throws SQLException {
				stmt.execute(sql);
			}
		});
	}

	/**
	 * 删除留言
	 * 
	 * @param leaveMsgId
	 */
	public void deleteLeaveMsg(int leaveMsgId) {
		String procedure = "{call " + SP_NOTES_DELETE_BYNID + "(?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{leaveMsgId}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();
			}
		});
	}

	/**
	 * 删除留言回复
	 * 
	 * @param leaveMsgId
	 */
	public void deleteLeaveMsg(int leaveMsgId, int leaveReplyId) {
		String procedure = "{call " + SP_REPLY_NOTE_DELETE_REPLY_BYID
				+ "(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{leaveMsgId, leaveReplyId}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.execute();
				
			}
		});
	}

	/**
	 * 留言
	 * 
	 * @param lm
	 */
	public void leaveMsg(final LeaveMsg lm) {
		String procedure = "{call " + SP_NOTES_LEAVE_A_MESSAGE
				+ "(?, ?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{lm.getPublisherId(), lm.getTargetId(), lm.getMessage()}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.registerOutParameter(4, Types.INTEGER);
				stmt.execute();
				lm.setNoteId(stmt.getInt(4));
			}
		});
	}
}
