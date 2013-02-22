package com.topn.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.topn.DAL.FriendDAL;
import com.topn.DAL.query.FriendQuery;
import com.topn.asynchronous.MoodPool;
import com.topn.bean.LeaveMsg;
import com.topn.bean.Mood;
import com.topn.bean.MoodTips;
import com.topn.bean.ReplyMood;
import com.topn.bean.TO.FriendChatTO;
import com.topn.bean.TO.FriendRecentMoodTO;
import com.topn.bean.TO.FriendTO;
import com.topn.bean.TO.LeaveMsgTO;
import com.topn.bean.TO.RecentVisitorTO;
import com.topn.bean.TO.RecommendFriendTO;
import com.topn.bean.TO.ReplyLeaveMsgTO;
import com.topn.bean.TO.ReplyTO;
import com.topn.cache.UserCache;
import com.topn.task.ReplyTask;
import com.topn.util.Criteria;
import com.topn.util.DeleteCriteria;
import com.topn.util.Restrictions;
import com.topn.util.StringUtil;
import com.topn.util.Table;
import com.topn.util.UpdateCriteria;

/**
 * 
 * 创建人 KingXt 创建时间：2011-4-7 下午10:34:08
 * 
 * 此类用来处理朋友，搜索，添加，删除
 */
public class FriendController {
	//private static Logger logger = Logger.getLogger(FriendController.class);

	private static FriendController instance = new FriendController();
	public static int N_DAY_MOOD = 3;
	
	

	private FriendController() {
	}

	public static FriendController getInstance() {
		return instance;
	}

	/**
	 * 根据条件查询注册人员
	 * 
	 * @param cri
	 * @return
	 */
	public List<FriendTO> searchPerson(Criteria[] cris, int pageNum) {

		String sql = "";
		for (int i = 0; i < cris.length; i++) {
			sql += cris[i].toSqlString();
			if (i != cris.length - 1) {
				sql += " union all ";
			}
		}
		if (StringUtil.isBlank(sql)) {
			return null;
		}
		sql += Restrictions.limit(pageNum, Table.FRIEND_SEARCH_RESULT_PAGE_SIZE);
		return FriendQuery.getInstance().searchPerson(sql);
	}

	/**
	 * 获取符合条件的好友总数
	 * @param cris
	 * @return
	 */
	public int getPersonCount(Criteria[] cris){
		String sql = "";
		for (int i = 0; i < cris.length; i++) {
			sql += cris[i].toSqlString();
			if (i != cris.length - 1) {
				sql += " union all ";
			}
		}
		if (StringUtil.isBlank(sql)) {
			return 0;
		}
		return FriendQuery.getInstance().getPersonCount(sql);
	}
	/**
	 * 发表心情
	 * 
	 * @param mood
	 */
	public String publishMood(Mood mood) {
		mood.setTableName(this.getMoodTableName(mood.getPersonalId()));

		FriendDAL.getInstance().publishMood(mood);
		if(0 == mood.getMoodId()){
			return "发表失败";
		}else{
			return "发表成功";
		}
	}

	/**
	 * 回复心情，这里等下要修改的是，如果好友在线就直接发送，否则就存入数据库等
	 * 用户下一次上线的时候提醒用户
	 * @return
	 */
	public String replyMood(ReplyMood replyMood) {
		String msg = ""; 
		FriendDAL.getInstance().replyMood(replyMood);
		if(0 == replyMood.getReplyId()){
			msg = "发表失败";
			return msg;
		}else{
			msg = "发表成功";
		}
		MoodTips moodTips = new MoodTips();
		moodTips.setMoodId(replyMood.getMoodId());
		moodTips.setMoodOwerId(replyMood.getMoodPersonId());
		moodTips.setRemindId(replyMood.getParentId());
		moodTips.setReplyId(replyMood.getReplyId());
		MoodPool.getInstance().addTask(new ReplyTask(moodTips));
		return msg;
	}
	
	/**
	 * 添加好友
	 * @param owerId 谁添加好友
	 * @param friendId 好友的id
	 * @param friendType 好友的类型
	 * @param msg
	 */
	public String addFriend(int owerId, int friendId, int friendType, String msg){
		return FriendDAL.getInstance().addFriend(owerId, friendId, friendType, msg);
	}

	/**
	 * 根据用户的id 找到心情应该放在的表中
	 * 
	 * @param pid
	 * @return
	 */
	public String getMoodTableName(int pid) {
		String userTable = UserCache.getInstance().getTableName(pid);
		return userTable.replaceFirst("person_info", "mood");
	}

	/**
	 *  根据用户的id 找到心情回复者的表中
	 * @param pid
	 * @return
	 */
	public String getReplyMoodTableName(int pid) {
		String userTable = UserCache.getInstance().getTableName(pid);
		return userTable.replaceFirst("personal_info", "reply_mood");
	}
	
	/**
	 * 找出一段时间的朋友的心情和回复（回复只包括第一条回复和最后一条回复）
	 * @param id
	 * @param begin
	 * @param end
	 * @return
	 */
	public List<FriendRecentMoodTO> getFriendRecentMood(int id, String startTime, int pageNum){
		Timestamp st = new Timestamp(Long.parseLong(startTime));
		//开始时间
//		Calendar now =Calendar.getInstance();
//		now.setTime(new java.util.Date());
//		now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);
		//结束时间
//		Calendar later =Calendar.getInstance();
//		later.setTime(new java.util.Date());
//		later.set(Calendar.DATE, later.get(Calendar.DATE) - N_DAY_MOOD);
		Calendar later = Calendar.getInstance(); 
		later.setTime(st); 
		later.set(Calendar.DATE, later.get(Calendar.DATE) - N_DAY_MOOD);
		Timestamp ts = new Timestamp(later.getTimeInMillis());
		//将java.util.Date  转换为  java.sql.Date
//		Date d = new Date(now.getTime().getTime());
//		Date e = new Date(later.getTime().getTime());
		return FriendQuery.getInstance().getFriendRecentMood(id, ts, st, pageNum);
	}
	
	/**
	 * 获取某段时间的心情
	 * @param id   用户的id
	 * @param begin  开始时间
	 * @param end  结束时间
	 * @return
	 */
//	public List<FriendRecentMoodTO> getFriendRecentMood(int id, int dlta){
//		//开始时间
//		Calendar now =Calendar.getInstance();
//		now.setTime(new java.util.Date());
//		now.set(Calendar.DATE, now.get(Calendar.DATE) - dlta);
//		//结束时间
//		Calendar later =Calendar.getInstance();
//		later.setTime(new java.util.Date());
//		later.set(Calendar.DATE, later.get(Calendar.DATE) - (dlta + N_DAY_MOOD));
//		//将java.util.Date  转换为  java.sql.Date
//		Date d = new Date(now.getTime().getTime());
//		Date e = new Date(later.getTime().getTime());
//		return FriendQuery.getInstance().getFriendRecentMood(id, e, d);
//	}
	
	/**
	 * 获取一条回复的所有回复记录
	 * @param personId 用户的id
	 * @param moodId
	 * @return
	 */
	public List<ReplyTO> getMoodAllReplies(int personId, int moodId){
		List<ReplyTO> rts = FriendQuery.getInstance().getMoodAllReplies(personId, moodId);
		/*if(rts.size() > 2){
			rts.remove(0);
			rts.remove(rts.size()-1);
		}*/
		return rts;
	}
	
	/**
	 * 删除心情
	 * @param pid  那个用户端
	 * @param replyId 那条回复
	 */
	public void deleteReply(int pid, int moodId, int replyId){
		FriendDAL.getInstance().deleteReply(pid, moodId, replyId);
	}
	
	/**
	 * 获取那类型的好友，例如好友，密友，按照类型查找
	 * @param pid 用户
	 * @param type 类型
	 * @return
	 */
	public List<FriendTO> getFriendsByType(int pid, int type, int pageNum){
		return FriendQuery.getInstance().getFriendsByType(pid, type, pageNum);
	}
	
	/**
	 * 删除好友
	 * @param pid 哪个好友的id
	 * @param personId 好友的id
	 */
	public void deleteFriend(int pid, int personId){
		DeleteCriteria dc = new DeleteCriteria("friends");
		if(pid < personId){
			dc.add(Restrictions.eq("person_one_id", pid));
			dc.add(Restrictions.eq("person_two_id", personId));
		}else if(pid > personId){
			dc.add(Restrictions.eq("person_one_id", personId));
			dc.add(Restrictions.eq("person_two_id", pid));
		}else{
			return;
		}		
		FriendDAL.getInstance().deleteFriend(dc.toSqlString());
	}
	
	/**
	 * 修改朋友类型
	 * @param pid
	 * @param friendId
	 * @param friendType
	 */
	public void modifyFriendType(int pid, int friendId, int friendType){
		UpdateCriteria uc = new UpdateCriteria("friends");
		if(pid < friendId){
			uc.addLastSet(Restrictions.updateWithSet("relate_one", friendType));
			uc.addCondition(Restrictions.eq("person_one_id", pid));
			uc.addCondition(Restrictions.eq("person_two_id", friendId));
		}else if(pid > friendId){
			uc.addLastSet(Restrictions.updateWithSet("relate_two", friendType));
			uc.addCondition(Restrictions.eq("person_one_id", friendId));
			uc.addCondition(Restrictions.eq("person_two_id", pid));
		}
		FriendDAL.getInstance().modifyFriendType(uc.toSqlString());
	}
	
	/**
	 * 添加好友	
	 * @param pid
	 * @param friendId
	 * @param flag
	 */
	public void realAddFriend(int pid, int friendId, String name, int friendType, int flag){
		FriendDAL.getInstance().realAddFriend(pid, friendId, name, friendType, flag);
	}
	
	/**
	 * 删除某个人的某条心情
	 * @param pid
	 * @param moodId
	 */
	public void deleteMood(int pid, int moodId){
		FriendDAL.getInstance().deleteMood(pid, moodId);
	}
	
	/**
	 * 查找自己所有的好友
	 * @param pid
	 * @return
	 */
	public List<FriendChatTO> getFriendChatTO(int pid){
		return FriendQuery.getInstance().getFriendChatTO(pid);
	}
	
	/**
	 * 获取所有的留言
	 * @return
	 */
	public List<LeaveMsgTO> getLeaveMsgs(int pid, int pageNum){
		return FriendQuery.getInstance().getLeaveMsgs(pid, pageNum);
	}
	
	/**
	 * 获取所有的留言回复
	 * @param leaveMsgId
	 * @return
	 */
	public List<ReplyTO> getReplyTO(int leaveMsgId){
		return FriendQuery.getInstance().getReplyTO(leaveMsgId);		
	}
	
	/**
	 * 留言
	 * @param publishId
	 * @param targetId
	 * @param msg
	 * @return
	 */
	public void leaveMsg(ReplyLeaveMsgTO lmt){
		FriendQuery.getInstance().leaveMsg(lmt);
	}
	
	/**
	 * 删除留言
	 * @param leaveMsgId
	 */
	public void deleteLeaveMsg(int leaveMsgId){
		FriendDAL.getInstance().deleteLeaveMsg(leaveMsgId);
	}
	
	/**
	 * 删除留言回复
	 * @param leaveMsgId
	 */
	public void deleteLeaveMsg(int leaveMsgId, int leaveReplyId){
		FriendDAL.getInstance().deleteLeaveMsg(leaveMsgId, leaveReplyId);
	}
	
	/**
	 * 获取最近访客
	 * @param pid
	 * @return
	 */
	public List<RecentVisitorTO> getRecentVisitorTO(final int pid){
		return FriendQuery.getInstance().getRecentVisitorTO(pid);
	}
	
	/**
	 * 留言
	 * @param from
	 * @param to
	 * @param msg
	 */
	public int leaveMsg(int from, int to, String msg){
		if(StringUtil.isBlank(msg)){
			return 0;
		}
		if(from == 0 || to == 0){
			return 0;
		}
		LeaveMsg lm = new LeaveMsg();
		lm.setPublisherId(from);
		lm.setTargetId(to);
		lm.setMessage(msg);
		FriendDAL.getInstance().leaveMsg(lm);
		return lm.getNoteId();
	}
	
	/**
	 * 获取好友的好友
	 * @param pid 好友的id
	 * @param pageNum 分页号
	 * @return
	 */
	public List<FriendTO> getFriendFriends(final int pid, final int pageNum){
		return FriendQuery.getInstance().getFriendFriends(pid, pageNum);
	}
	
	/**
	 * 
	 * @param pid
	 * @param relType 如果传入-1 表示所有好友
	 * @return
	 */
	public int getFriendCount(final int pid, final int relType){
		return FriendQuery.getInstance().getFriendCount(pid, relType);
	}
	
	/**
	 * 
	 * @param pid 用户的id
	 * @param school 用户所在学校
	 * @param type 用户类型
	 * @return
	 */
	public List<RecommendFriendTO> getRecommendFriendTO(final int pid, final String school, final int type){
		return FriendQuery.getInstance().getRecommendFriendTO(pid, school, type);
	}
	
	/**
	 * 自己给评论朋友流的言
	 * @return
	 */
	public List<FriendRecentMoodTO> getMyMsg2Friend(final int otherId,  final int pid){
		return FriendQuery.getInstance().getMyMsg2Friend(otherId, pid);
	}
}
