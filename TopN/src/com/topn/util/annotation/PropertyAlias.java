package com.topn.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-1 下午06:59:30
 * 
 * 通过属性别名能够实现实体bean中的一些属性和数据库字段不同
 */
@Target({ElementType.FIELD})

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PropertyAlias {

	public String alias();
	
	/**
	 * 属性是否是主键
	 * @return
	 */
	public boolean isId() default false;
}
