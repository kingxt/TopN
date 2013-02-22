package com.topn.util;

import sun.misc.BASE64Decoder;

/**
 * 
 * 创建人 KingXt 创建时间：2011-4-26 下午01:39:21
 * 
 * 加密解密类
 */
public class Encrypt {
	
	/**
	 * 加密
	 * @param s
	 * @return
	 */
	public static String encode(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	// 将 BASE64 编码的字符串 s 进行解码
	/**
	 * 解密
	 */
	public static String decode(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
}
