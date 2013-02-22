package com.topn.cache;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;
import com.topn.exception.CacheException;
import com.topn.util.LoggerUtil;

public class MemCache {

	private static Logger logger = Logger.getLogger(MemCache.class);

	public static final int SIXTY_THOUSAND_MS = 60000; // 1分钟的，或称1分钟包含的毫秒值

	private MemCachedClient mc;
	private String regionName; // 缓存区域标识
	private int expireTimeInSecond;// 该存储区域对缓存对象过期时间值
	private String keyPrefix; // 通过对象的key前缀区别不同缓存区域的对象

	@SuppressWarnings("deprecation")
	public MemCache(String poolName, String regionName, int expireTimeInSecond,
			Boolean compressEnable, Integer compressThreshold) {
		mc = new MemCachedClient(poolName);
		if (compressEnable != null) {
			mc.setCompressEnable(compressEnable.booleanValue());
		}
		if (compressThreshold != null) {
			mc.setCompressThreshold(compressThreshold.intValue());
		}
		this.expireTimeInSecond = expireTimeInSecond;
		this.regionName = regionName;
		this.keyPrefix = regionName + '-';
	}

	private String rebuildKey(Object key) {
		//return keyPrefix + key.toString().hashCode();
		return keyPrefix + key.toString();
	}

	public Object get(Object key) throws CacheException {
		LoggerUtil.loggerDebug(logger, "get Element by key: " + key);

		if (key == null) {
			return null;
		} else {
			Object rt = mc.get(rebuildKey(key));
			if (rt == null) {
				LoggerUtil.loggerDebug(logger, "Element for " + key
						+ " is null");
				return null;
			} else {
				return rt;
			}
		}

	}

	// public Object read(Object key) throws CacheException {
	// return get(rebuildKey(key));
	// }

	public void update(Object key, Object value) throws CacheException {
		if (expireTimeInSecond <= 0)
			mc.replace(rebuildKey(key), value);
		else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, expireTimeInSecond);
			mc.replace(rebuildKey(key), value, cal.getTime());
		}
	}

	public void put(Object key, Object value) throws CacheException {
		if (expireTimeInSecond <= 0)
			mc.set(rebuildKey(key), value);
		else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, expireTimeInSecond);
			mc.set(rebuildKey(key), value, cal.getTime());
		}
	}

	public void remove(Object key) throws CacheException {
		mc.delete(rebuildKey(key));
	}

	public void clear() throws CacheException {
		mc.flushAll();
	}

	public String getRegionName() {
		return regionName;
	}

	public String toString() {
		return "MemCached(" + getRegionName() + ')';
	}
}
