package com.topn.bean.TO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.topn.exception.ServiceException;
import com.topn.util.StringUtil;
import com.topn.util.annotation.PermissionHelp;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-25 下午07:34:48
 * 
 * 权限控制类
 * 
 * 1 公开
 * 2 好友可见
 * 3 保密
 */
public class PermissionsTO {

	/**
	 * 排列第一
	 */
	@PermissionHelp(index = 1)
	private int perBirthday = 1;
	
	/**
	 * 排列第二
	 */
	@PermissionHelp(index = 2)
	private int perPhone = 1;
	
	/**
	 * 排列第三
	 */
	@PermissionHelp(index = 3)
	private int perAddress = 1;
	
	/**
	 * 排列第四
	 */
	@PermissionHelp(index = 4)
	private int perMsn = 1;
	
	/**
	 * 排列第五
	 */
	@PermissionHelp(index = 5)
	private int perQQ = 1;
	
	/**
	 * 排列第六
	 */
	@PermissionHelp(index = 6)
	private int perSchool = 1;

	public int getPerBirthday() {
		return perBirthday;
	}

	public void setPerBirthday(int perBirthday) {
		this.perBirthday = perBirthday;
	}

	public int getPerPhone() {
		return perPhone;
	}

	public void setPerPhone(int perPhone) {
		this.perPhone = perPhone;
	}

	public int getPerAddress() {
		return perAddress;
	}

	public void setPerAddress(int perAddress) {
		this.perAddress = perAddress;
	}

	public int getPerMsn() {
		return perMsn;
	}

	public void setPerMsn(int perMsn) {
		this.perMsn = perMsn;
	}

	public int getPerQQ() {
		return perQQ;
	}

	public void setPerQQ(int perQQ) {
		this.perQQ = perQQ;
	}

	public int getPerSchool() {
		return perSchool;
	}

	public void setPerSchool(int perSchool) {
		this.perSchool = perSchool;
	}
	
	/**
	 * 将对象变成字符串
	 * @param to
	 * @return
	 */
	public static String permission2String(PermissionsTO to){
		String per = "";
		Field []fields = to.getClass().getDeclaredFields();
		char []a = new char[fields.length];
		for (int i = 0; i < fields.length; i++) {
			PermissionHelp tn = fields[i].getAnnotation(PermissionHelp.class);
			int index = tn.index();
			String name = fields[i].getName();
			Method method;Object o = null;
			try {
				method = to.getClass().getMethod("get"+ name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
				o = method.invoke(to);
			} catch (Exception e) {
				throw new ServiceException("[PermissionsTO, permission2String] 转换失败");
			}
			
			if(o == null || o.equals("")){
				continue;
			}
			a[index - 1] = o.toString().charAt(0);
		}
		for(int i = 0; i < a.length; i++){
			//if(a[i] == '1' || a[i] == '2'){
				per = per + a[i];
			//}			
		}
		return per;
	}
	
	public static PermissionsTO string2PermissionsTO(String str){
		PermissionsTO pt = new PermissionsTO();
		Field []fields = pt.getClass().getDeclaredFields();
		if(StringUtil.isBlank(str) || str.length() < fields.length){
			return pt;
		}
		for (int i = 0; i < fields.length; i++) {
			PermissionHelp tn = fields[i].getAnnotation(PermissionHelp.class);
			int index = tn.index();
			String name = fields[i].getName();
			Method method;
			try {
				method = pt.getClass().getMethod("set"+ name.substring(0, 1).toUpperCase() + name.substring(1, name.length()), int.class);
				method.invoke(pt, Integer.valueOf(str.charAt(index - 1)) - 48);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServiceException("[PermissionsTO, string2PermissionsTO] 转换失败");				
			}
		}
		return pt;
	}
}
