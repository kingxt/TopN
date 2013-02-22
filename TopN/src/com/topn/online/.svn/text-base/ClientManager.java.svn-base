package com.topn.online;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-29 上午12:00:48
 * 
 * 管理所有的client客户端
 */
public class ClientManager {
	
	//保存所有的客户端
	private ConcurrentHashMap<Integer, Socket> clients = new ConcurrentHashMap<Integer, Socket>();

	private static ClientManager instance = new ClientManager();
	
	private ClientManager(){}
	
	public static ClientManager getInstance(){
		return instance;
	}
	
	/**
	 * 添加一个客户端
	 * @param id  客户端id
	 * @param so  客户端的socket
	 */
	public void addClient(int id, Socket so){
		clients.put(id, so);
	}
	
	/**
	 * 判断用户是否在线
	 * @param id
	 */
	public boolean isOnline(int id){
		if(null == clients.get(id)) return false;
		return true;
	}
	
	/**
	 * 如果在线就发送消息
	 * @param msg
	 */
	public void sendIfOnline(Message msg){
		Socket socket = clients.get(msg.getId());
		if(null == socket) return;
		DataOutputStream writer = null;  //套接字输出流
		try {
			writer = new DataOutputStream(socket.getOutputStream());
			writer.writeUTF(msg.getMessage());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
