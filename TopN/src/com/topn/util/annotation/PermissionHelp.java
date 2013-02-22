package com.topn.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-26 上午10:32:10
 * 
 * 用户权限控制
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionHelp {

	/**
	 * 权限所在的位置
	 * @return
	 */
	public int index();
}
