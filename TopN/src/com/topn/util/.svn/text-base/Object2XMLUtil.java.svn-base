package com.topn.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-16 下午01:03:30
 * 
 * 将对象
 */
public class Object2XMLUtil {

	@SuppressWarnings("unchecked")
	public static String object2XML(Object object, Class clazz){
		XStream xstream = new XStream();
		xstream.alias(clazz.getSimpleName(), clazz); 
		return xstream.toXML(object); 
	}
	
	@SuppressWarnings("unchecked")
	public static String list2XML(List list, Class clazz){
		XStream xstream = new XStream();
		xstream.alias(clazz.getSimpleName(), clazz);
		String str = xstream.toXML(list);
		str = trimEnter(str);
		return str;
	}
	
	@SuppressWarnings("unchecked")
	public static String array2XML(Object []obj, Class clazz){
		XStream xstream = new XStream();
		xstream.alias(clazz.getSimpleName(), clazz);
		String str = xstream.toXML(obj); 
		str = trimEnter(str);
		return str;
	}
	
	public static String trimEnter(String str){
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		str = m.replaceAll("");
		return str;
	}
}
