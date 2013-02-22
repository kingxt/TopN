package com.topn.util;

import java.util.Properties;

public class PropertiesUtil {

	public static boolean getBoolean(Properties prop, String name){
		return Boolean.parseBoolean( prop.getProperty(name).trim());
	}
	
	public static int getInt(Properties prop, String name){
		return Integer.parseInt(prop.getProperty(name).trim());
	}
	
	public static String getString(Properties prop, String name){
		return prop.getProperty(name).trim();
	}
}
