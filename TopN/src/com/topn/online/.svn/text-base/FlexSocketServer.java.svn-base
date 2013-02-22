package com.topn.online;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;

import com.topn.util.LoggerUtil;


/**
 * 
 * 创建人 KingXt 创建时间：2011-3-28 下午11:18:14
 * 
 * 这个用来做及时通知用的
 */
public class FlexSocketServer {

	private static Logger logger = Logger.getLogger(FlexSocketServer.class);
	
	// 服务器段端口，开这个端口的意义是为了让flex获得策略文件
	private static final int SERVER_PORT = 843;
	private static final int CLIENT_SERVER_PORT = 2299;
	private ServerSocket server;
	private ServerSocket clientServer;

	/**
	 * 启动server函数，之所有为什么要关闭socked是因为当flex链接是多次链接，第一次链接是
	 * 获得策略文件，如果获得成功，就会再次请求主socked
	 */
	public void startServer() {

		//发送的策略文件的字符串
		String policy = "";
		try {
			server = new ServerSocket(SERVER_PORT);
			//读取策略文件
			String path = FlexSocketServer.class.getResource("crossdomain.xml")
					.getPath();
			policy = readFileAsString(path);
		} catch (IOException e2) {
			e2.printStackTrace();
			LoggerUtil.loggerDebug(logger, "[FlexSocketServer, startServer]服务启动失败");
		}
		Socket client = null;
		while (true) {
			try {
				client = server.accept();
				InputStreamReader input = new InputStreamReader(client
						.getInputStream(), "UTF-8");
				BufferedReader reader = new BufferedReader(input);
				OutputStreamWriter output = new OutputStreamWriter(client
						.getOutputStream(), "UTF-8");
				BufferedWriter writer = new BufferedWriter(output);
				// 读取客户端发送的数据
				StringBuilder data = new StringBuilder();
				int c = 0;
				while ((c = reader.read()) != -1) {
					if (c != '\0')
						data.append((char) c);
					else
						break;
				}
				String info = data.toString();
				//System.out.println("输入的请求: " + info);
				// 接收到客户端的请求之后，将策略文件发送出去
				if (info.indexOf("<policy-file-request/>") >= 0) {
					writer.write(policy + "\0");
					writer.flush();
					//System.out.println("将安全策略文件发送至: " + client.getInetAddress());
				} else {
					writer.write("请求无法识别\0");
					writer.flush();
					System.out.println("请求无法识别: " + client.getInetAddress());
				}
				client.close();
			} catch (IOException e) {
				// 发现异常关闭连接
				if (client != null) {
					try {
						client.close();
					} catch (IOException e1) {
						LoggerUtil.loggerDebug(logger, "[FlexSocketServer, startServer]关闭socket是失败");
					}
					client = null;
				}
				e.printStackTrace();
			}finally{
				if (client != null) {
					try {
						client.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					client = null;
				}
			}
		}
	}
	
	/**
	 * 
	 * 创建人 KingXt
	 * 创建时间：2011-3-30 下午07:13:24
	 * 
	 * 此类事用来和客户端通信的类
	 */
	class MainThread implements Runnable{
		
		MainThread(){
			try {
				clientServer = new ServerSocket(CLIENT_SERVER_PORT);
			} catch (IOException e) {
				LoggerUtil.loggerDebug(logger, "[FlexSocketServer, MainThread,MainThread]服务启动失败");
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(true){
				try {
					Socket client = clientServer.accept();
					DataInputStream dis = new DataInputStream(client
							.getInputStream());
					int personalId = dis.readInt();
					//如果读到了用户的id为0，就直接关掉socked
					if(0 >= personalId){
						try{
							client.close();
						}finally{
							if(null != client){
								client.close();
							}
						}						
					}
					ClientManager.getInstance().addClient(personalId, client);
				} catch (IOException e) {
					LoggerUtil.loggerDebug(logger, "[FlexSocketServer, MainThread]" + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		FlexSocketServer fs = new FlexSocketServer();
		new Thread(fs.new MainThread()).start();
		fs.startServer();
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws java.io.IOException
	 * 读取策略文件
	 */
	public static String readFileAsString(String url)
			throws java.io.IOException {
		byte[] buffer = new byte[(int) new File(url).length()];
		BufferedInputStream f = new BufferedInputStream(
				new FileInputStream(url));
		f.read(buffer);
		f.close();
		return new String(buffer);
	}
}
