package com.topn.util;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-1 下午02:37:11
 * 
 * 数据库链接管理类， 此类采用单粒模式
 */
public class ConnectionManager {

	private static ConnectionManager instance = new ConnectionManager();
	private static ComboPooledDataSource ds = null;
	private static Logger logger = Logger.getLogger(ConnectionManager.class);

	static {
		InputStream is = null;
		System.out.println(12312313);
		is = ConnectionManager.class.getResourceAsStream("/c3p0.properties");

		Properties p = new Properties();
		try {
			p.load(is);
		} catch (IOException e) {
			LoggerUtil.loggerDebug(logger, "[ConnectionManager] 类中读配置文件失败\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass(PropertiesUtil.getString(p, "jdbc.driverClass"));
			ds.setJdbcUrl(PropertiesUtil.getString(p, "jdbc.jdbcUrl"));
			ds.setUser(PropertiesUtil.getString(p, "jdbc.user"));
			ds.setPassword(PropertiesUtil.getString(p, "jdbc.password"));
			try {
				is.close();
			} catch (IOException e) {
				LoggerUtil.loggerDebug(logger, "[ConnectionManager] 类中关闭文件输入流失败\n"+ e.getMessage());
				e.printStackTrace();
			}
		} catch (PropertyVetoException e) {
			LoggerUtil.loggerDebug(logger, "[ConnectionManager] 类中属性注册失败\n"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 根据参数初始化连接池
	 */
	private ConnectionManager() {// instance.getClass().getResourceAsStream("/c3p0.properties");

	}

	/**
	 * @return 返回单粒
	 */
	public static ConnectionManager getInstance() {
		return instance;
	}

	/**
	 * @return 返回从连接池取得的链接
	 */
	public synchronized final Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			LoggerUtil.loggerDebug(logger, "[ConnectionManager] 类中获取链接失败\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 在垃圾收集器收集此类的时候要数据源释放掉
	 */
	protected void finalize() throws Throwable {
		DataSources.destroy(ds); // 关闭datasource
		super.finalize();
	}

}
