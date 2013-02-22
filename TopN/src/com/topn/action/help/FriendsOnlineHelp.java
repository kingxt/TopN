package com.topn.action.help;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.topn.bean.TO.FriendChatTO;
import com.topn.nl.justobjects.pushlet.core.Dispatcher;
import com.topn.nl.justobjects.pushlet.core.Event;
import com.topn.nl.justobjects.pushlet.core.Protocol;
import com.topn.nl.justobjects.pushlet.core.Session;
import com.topn.nl.justobjects.pushlet.core.SessionManager;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-23 下午02:55:11
 * 
 * 这个类用来与pushlets打交道
 */
public class FriendsOnlineHelp {
	
	public static final String LEAVEMSG_REALTIME_BACK = "1";
	public static final String FRIEND_OFF_LINE = "2";
	public static final String FRIEND_ON_LINE = "3";

	/**
	 * 看好友是否在线
	 * @param pid 好友 的id
	 */
	public static boolean isOnline(int pid){
		Session session = SessionManager.getInstance().getSession(String.valueOf(pid));
		if(null == session){
			return false;
		}
		return true;
	}
	
	/**
	 * 提醒给在线用户
	 * p_event=publish, p_subject=/chat, action=send, p_time=1303543308, p_send_time=15:21, p_id=1, msg=nihao, p_to=4
	 * 
	 * {p_event=data, p_from=4, p_subject=/chat, action=send, p_time=1303713089, p_send_time=14:31, p_id=4, msg=gg, p_to=1}
	 * @param pid 提醒谁
	 * @param parentId 给谁留言
	 * 
	 * 1: 标识是留言回复
	 */
	public static void sendToOnlineFriend(int pid, int parentId, HttpServletRequest request, HttpServletResponse response){
		Session toSession = SessionManager.getInstance().getSession(String.valueOf(parentId));
		if(null == toSession){
			return;
		}
		Session session = SessionManager.getInstance().getSession(String.valueOf(pid));
		Event event = new Event(Protocol.E_DATA);
		event.setField("p_event", "data");
		event.setField("p_subject", "/chat");
		event.setField("action", "send");
		event.setField("p_from", pid);
		event.setField("p_to", parentId);
		event.setField("p_type", LEAVEMSG_REALTIME_BACK);
	//	Command command = Command.create(session, event, request, response);
		session.kick();
		session.setAddress(request.getRemoteAddr());
		Dispatcher.getInstance().unicast(event, toSession);
	}
	
	/**
	 * 处理好友,如果在线就将标识设置为1 否则设置为2
	 * @param fto
	 */
	public static void dealFriendsOnline(List<FriendChatTO> fto){
		for (Iterator<FriendChatTO> iterator = fto.iterator(); iterator.hasNext();) {
			FriendChatTO friendChatTO = (FriendChatTO) iterator.next();
			if(isOnline(friendChatTO.getId())){ 
				friendChatTO.setIsOnline(1);
			}else{
				friendChatTO.setIsOnline(2);
			}
		}
	}
	
	/**
	 * 朋友下线
	 * @param friendId
	 * 
	 */
	public static void myFriendOffLine(int friendId){
		Event event = new Event(Protocol.E_DATA);
		event.setField("p_event", "data");
		event.setField("p_subject", "/chat");
		event.setField("action", "send");
		//event.setField("p_from", friendId);
		event.setField("p_id", friendId);
		event.setField("p_type", FRIEND_OFF_LINE);
		Dispatcher.getInstance().broadcast(event);
	}
	
	/**
	 * 朋友下线
	 * @param friendId
	 * 
	 */
	public static void myFriendOnLine(int friendId){
		Event event = new Event(Protocol.E_DATA);
		event.setField("p_event", "data");
		event.setField("p_subject", "/chat");
		event.setField("action", "send");
		//event.setField("p_from", friendId);
		event.setField("p_id", friendId);
		event.setField("p_type", FRIEND_ON_LINE);
		Dispatcher.getInstance().broadcast(event);
	}
}









