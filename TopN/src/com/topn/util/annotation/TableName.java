package com.topn.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-1 下午05:30:39
 * 
 * 自定义的注解用来在分表时候能够确定那个字段是表名
 */

@Target({ElementType.FIELD, ElementType.TYPE})

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {

	/**
	 * 表名称
	 * @return
	 */
	public String name();
}
