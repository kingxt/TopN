// Copyright (c) 2000 Just Objects B.V. <just@justobjects.nl>
// Distributable under LGPL license. See terms of license at gnu.org.

package com.topn.nl.justobjects.pushlet.util;

import com.topn.nl.justobjects.pushlet.core.Config;
import com.topn.nl.justobjects.pushlet.core.ConfigDefs;

/**
 * Logging wrapper.
 * <p/>
 * Provides a hook to direct logging to your own logging library.  Override the DefaultLogger class by setting
 * "logger.class" in pushlet.properties to your own logger
 * to integrate your own logging library.
 *
 * @author Just van den Broecke
 * @version $Id: Log.java,v 1.5 2007/12/07 12:57:40 justb Exp $
 */
public class Log implements ConfigDefs {
	/**
	 * Init with default to have at least some logging.
	 */
	private static PushletLogger logger = new DefaultLogger();

	/**
	 * General purpose initialization.
	 */
	static public void init() {
		try {
			logger = (PushletLogger) Config.getClass(LOGGER_CLASS, "nl.justobjects.pushlet.util.DefaultLogger").newInstance();
		} catch (Throwable t) {
			// Hmmm cannot log this since we don't have a log...
			System.out.println("Cannot instantiate Logger from config ex=" + t);
			return;
		}

		logger.init();

		// Set log level
		logger.setLevel(Config.getIntProperty(Config.LOG_LEVEL));

		logger.info("Logging intialized logger class=" + logger.getClass());
	}

	/**
	 * Log message for trace level.
	 *
	 * @param aMessage the message to be logged
	 */
	static public void trace(String aMessage) {
		logger.debug(aMessage);
	}

	/**
	 * Log message for debug level.
	 *
	 * @param aMessage the message to be logged
	 */
	static public void debug(String aMessage) {
		logger.debug(aMessage);
	}

	/**
	 * Log message for info level.
	 *
	 * @param aMessage the message to be logged
	 */
	static public void info(String aMessage) {
		logger.info(aMessage);
	}

	/**
	 * Log message for warning level.
	 *
	 * @param aMessage the message to be logged
	 */
	static public void warn(String aMessage) {
		logger.warn(aMessage);
	}

	/**
	 * Log message for warning level with exception.
	 *
	 * @param aMessage   the message to be logged
	 * @param aThrowable the exception
	 */
	static public void warn(String aMessage, Throwable aThrowable) {
		logger.warn(aMessage, aThrowable);
	}

	/**
	 * Log message for error level.
	 *
	 * @param aMessage the message to be logged
	 */
	static public void error(String aMessage) {
		logger.error(aMessage);
	}

	/**
	 * Log message (error level with exception).
	 *
	 * @param aMessage   the message to be logged
	 * @param aThrowable the exception
	 */
	static public void error(String aMessage, Throwable aThrowable) {
		logger.error(aMessage, aThrowable);
	}

	/**
	 * Log message for fatal level.
	 *
	 * @param aMessage the message to be logged
	 */
	static public void fatal(String aMessage) {
		logger.fatal(aMessage);
	}

	/**
	 * Log message (fatal level with exception).
	 *
	 * @param aMessage   the message to be logged
	 * @param aThrowable the exception
	 */
	static public void fatal(String aMessage, Throwable aThrowable) {
		logger.fatal(aMessage, aThrowable);
	}

	/**
	 * Set log level
	 *
	 * @param aLevel the message to be logged
	 */
	static public void setLevel(int aLevel) {
		logger.setLevel(aLevel);
	}
}