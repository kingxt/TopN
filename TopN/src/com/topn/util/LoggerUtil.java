package com.topn.util;

import org.apache.log4j.Logger;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-1 下午03:02:59
 * 
 * 日志管理类
 */
public class LoggerUtil {

	public static void loggerDebug(Logger logger, String message){
		if(logger.isDebugEnabled()){
			logger.debug(message);
		}
	}
	
}
