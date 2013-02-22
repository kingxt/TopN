package com.topn.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.danga.MemCached.SockIOPool;
import com.topn.exception.CacheException;
import com.topn.util.LoggerUtil;
import com.topn.util.PropertiesUtil;
import com.topn.util.StringUtil;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-19 下午07:20:07
 */
public class MemCacheProvider {

	private static MemCacheProvider instance = new MemCacheProvider();

	private static Logger logger = Logger.getLogger(MemCacheProvider.class);

	private static Hashtable<String, MemCache> caches;

	private static Properties props = null;

	public final static String DEFAULT_REGION_NAME = "default";

	//默认的缓存失效时间
	public static final int DEFAULT_EXPIRE_TIME_IN_SEC = 60 * 60;
	
	// 缓存类型 用户类型
	public final static String MEMCACHE_TYPE_USER = "user";
	
	public final static String HOBBY_TYPE = "hobby_type";
	
	//缓存排行榜的姓名
	public final static String MEMCACHE_TYPE_RANK = "topn_user";
	
	public final static String MEMCACHE_EDUCATION = "education";

	public final static String FORGETPWD = "forgetpwd";
	
	public final static String ONLINE_USER = "online_user";

	static {
		props = getProperties();

		caches = new Hashtable<String, MemCache>();
		// 
		SockIOPool pool = SockIOPool.getInstance(props
				.getProperty("memcached.poolName"));
		if (pool.isInitialized()) {
			logger.info("MemcachedCacheProvider.SockIOPool has been Started!");
		} else {
			String servers = props.getProperty("memcached.servers");
			if (StringUtil.isBlank(servers)) {
				throw new CacheException(
						"configuration 'servers' get a empty value");
			}
			pool.setServers(servers.split(","));
			// 
			String weights = props.getProperty("memcached.weights");
			if (weights != null) {
				String[] ws = weights.split(",");
				Integer[] iws = new Integer[ws.length];
				for (int i = 0; i < iws.length; i++) {
					iws[i] = Integer.parseInt(ws[i]);
				}
				pool.setWeights(iws);
			}
			// 
			pool.setFailover(PropertiesUtil.getBoolean(props,
					"memcached.failover"));
			pool.setFailback(PropertiesUtil.getBoolean(props,
					"memcached.failback"));
			pool
					.setInitConn(PropertiesUtil.getInt(props,
							"memcached.initConn"));
			pool.setMinConn(PropertiesUtil.getInt(props, "memcached.minConn"));
			pool.setMaxConn(PropertiesUtil.getInt(props, "memcached.maxConn"));
			pool.setMaintSleep(PropertiesUtil.getInt(props,
					"memcached.maintSleep"));
			pool.setNagle(PropertiesUtil.getBoolean(props, "memcached.nagle"));
			pool
					.setSocketTO(PropertiesUtil.getInt(props,
							"memcached.socketTO"));
			pool.setAliveCheck(PropertiesUtil.getBoolean(props,
					"memcached.aliveCheck"));
			pool.initialize();
		}

		logger.info("MemcachedCacheProvider Started!");
	}

	private MemCacheProvider() {
	}

	public static MemCacheProvider getInstance() {
		return instance;
	}

	private static Properties getProperties() throws CacheException {
		Properties props = new Properties();
		String properties = props.getProperty("memcached.properties",
				"memcached.properties");
		props.setProperty("memcached.properties", properties);
		InputStream is = MemCacheProvider.class
				.getResourceAsStream("/memcached.properties");

		try {
			props.load(is);
		} catch (IOException e) {
			LoggerUtil.loggerDebug(logger, "[MemCacheProvider] 类中读配置文件失败\n"
					+ e.getMessage());
			e.printStackTrace();
		}

		// 如果没有设置poolName，则以属性文件地址名自动设置之
		if (props.getProperty("memcached.poolName") == null) {
			props.setProperty("memcached.poolName", properties.substring(0,
					properties.lastIndexOf('.')));
		}
		try {
			is.close();
		} catch (IOException e) {
			LoggerUtil.loggerDebug(logger, "[MemCacheProvider] 类中关闭输入流失败\n"
					+ e.getMessage());
			e.printStackTrace();
		}
		return props;
	}

	public void start() {

	}

	private static int getSeconds(String str) {
		try {
			switch (str.charAt(str.length() - 1)) {
			case 's':
				return Integer.parseInt(str.substring(0, str.length() - 1));
			case 'm':
				return Integer.parseInt(str.substring(0, str.length() - 1)) * 60;
			case 'h':
				return Integer.parseInt(str.substring(0, str.length() - 1)) * 3600;
			case 'd':
				return Integer.parseInt(str.substring(0, str.length() - 1)) * 86400;
			default:
				return Integer.parseInt(str);
			}
		} catch (NumberFormatException e) {
			logger.warn("Illegal configuration value : " + str, e);
		}
		return -1;
	}

	public MemCache buildCache(String regionName) throws CacheException {
		if (caches == null) {
			throw new IllegalStateException("Please start the provider first!");
		}
		// Properties props = this.getProperties();
		String poolName = props.getProperty("memcached.poolName");
		if (StringUtil.isBlank(regionName)) {
			regionName = DEFAULT_REGION_NAME;
		}
		MemCache mCache = caches.get(regionName);
		if (mCache == null) {
			String expireTimeExpression = props.getProperty("memcached.region."
					+ regionName + ".expireTime");
			if (StringUtil.isBlank(expireTimeExpression)) {
				expireTimeExpression = props
						.getProperty("memcached.expireTime");
			}
			int expireTimeInSecond = -1;
			if (StringUtil.isNotBlank(expireTimeExpression)) {
				expireTimeExpression = expireTimeExpression.toLowerCase()
						.trim();
				expireTimeInSecond = getSeconds(expireTimeExpression);
			} else {
				expireTimeInSecond = DEFAULT_EXPIRE_TIME_IN_SEC;
			}
			// 
			String pcompressEnable = props.getProperty("memcached.region."
					+ regionName + ".compressEnable");
			if (pcompressEnable == null) {
				pcompressEnable = props.getProperty("memcached.compressEnable");
			}
			Boolean compressEnable = null;
			if (pcompressEnable != null) {
				compressEnable = Boolean.parseBoolean(pcompressEnable);
			}
			// 

			String pcompressThreshold = props.getProperty("memcached.region."
					+ regionName + ".compressThreshold");
			if (pcompressThreshold == null) {
				pcompressThreshold = props
						.getProperty("memcached.compressThreshold");
			}
			Integer compressThreshold = null;
			if (pcompressThreshold != null) {
				compressThreshold = Integer.parseInt(pcompressThreshold);
			}
			logger.info("Building cache named " + regionName
					+ " using expireTimeInSecond is " + expireTimeInSecond);
			mCache = new MemCache(poolName, regionName, expireTimeInSecond,
					compressEnable, compressThreshold);
			caches.put(regionName, mCache);
		}
		return mCache;
	}

}
