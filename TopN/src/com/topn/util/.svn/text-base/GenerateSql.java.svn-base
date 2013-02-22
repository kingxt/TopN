package com.topn.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.topn.util.annotation.IgnoreProperty;
import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-1 下午05:24:55
 * 
 * 根据类生成执行的添加的sql语句
 */
public class GenerateSql {
	private static Logger logger = Logger.getLogger(GenerateSql.class);

	/**
	 * 注解说明：
	 * 如果有属性注解@TableName则此属性值为表名，如果没有属性注解@TableName但类前注解了@TableName则类注解的值为表面
	 * 如果都没有注解则有类名的小写字母为类名
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String generateCreateSql(Object obj, boolean insertId) throws Exception{
		Field []fields = obj.getClass().getDeclaredFields();
		int count = 0;
		String tableName = "";
		StringBuffer field = new StringBuffer();
		StringBuffer value = new StringBuffer();
		for(int i = 0; i < fields.length; i++){
			if(fields[i].isAnnotationPresent(TableName.class)){
				if(count == 1){
					LoggerUtil.loggerDebug(logger, "[GenerateSql, generateCreateSql]实体所指定的表名称重复了" + obj.getClass().getName());
					throw new Exception("实体所指定的表名称重复了" + obj.getClass().getName());
				}
				TableName tn = fields[i].getAnnotation(TableName.class);
				count ++;
				if(tn.name().equals("table")){
					String name = fields[i].getName();
					Method method = obj.getClass().getMethod("get"+ name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
					Object o = method.invoke(obj);
					tableName = o.toString();
				}
				
			}else
			if(fields[i].isAnnotationPresent(PropertyAlias.class)){
				PropertyAlias pa = fields[i].getAnnotation(PropertyAlias.class);
				if(pa.isId() && !insertId){
					continue;
				}
				String aliasName = pa.alias();
				String name = fields[i].getName();
				
				Method method = obj.getClass().getMethod("get"+ name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
				Object o = method.invoke(obj);
				if(o == null || o.equals("")){
					continue;
				}
				field.append(aliasName + ", ");
				value.append("\""+o.toString()+"\"" + ", ");
			}else if(fields[i].isAnnotationPresent(IgnoreProperty.class)){//忽略某个属性
				continue;
			}else{
				String name = fields[i].getName();
				Method method = obj.getClass().getMethod("get"+ name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
				Object o = method.invoke(obj);
				if(o == null || o.equals("")){
					continue;
				}
				field.append(name + ",");
				value.append("\""+o.toString()+"\"" + ", ");
			}
		}
		if(0 == field.length()){
			return null;
		}
				
		if("".equals(tableName)){
			boolean flag = obj.getClass().isAnnotationPresent(TableName.class);
			if(flag){
				TableName tn = obj.getClass().getAnnotation(TableName.class);
				if(null != tn && "".equals(tn.name().trim())){
					throw new Exception("实体没有指定表名 " + obj.getClass().getName());
				}else if(tn != null){
					tableName = tn.name();
				}
			}else{
				tableName = obj.getClass().getSimpleName().toLowerCase();
			}
		}
		
		
		String sql = "insert into " + tableName + "(" + field.substring(0, field.lastIndexOf(",")) + ")values("+value.substring(0, value.lastIndexOf(","))
					+")";
		return sql;
	}
	
	/**
	 * 根据传进来的参数生成sql语句
	 * @param queryProperty 要查的属性
	 * @param tableName 表名称
	 * @param whereProperty 参数属性
	 * @param whereValue 参数值
	 * @return 生成sql
	 * @throws Exception
	 */
	public static String generateQuerySql(String []queryProperty, String tableName, String []whereProperty, String []whereValue) throws Exception{
		if(whereProperty.length != whereValue.length){
			throw new Exception("生成sql语句时  输入的where条件  条件属性和值个数不一致");
		}
		StringBuffer sqlB = new StringBuffer("select ");
		for (int i = 0; i < queryProperty.length; i++) {
			sqlB.append(queryProperty[i] + ",");
		}
		String sql = sqlB.substring(0, sqlB.lastIndexOf(","));
		sql += " from " + tableName + " where 1 = 1 ";
		StringBuffer b2 = new StringBuffer();
		for (int i = 0; i < whereProperty.length; i++) {
			b2.append(" and");
			b2.append(queryProperty[i] + " = ");
			b2.append("'" + whereValue[i] + "'");
		}
		return sql + b2.toString();
	}
	
	public static String generateUpdateSql(Object obj) throws Exception{
		Field []fields = obj.getClass().getDeclaredFields();
		int count = 0;
		String tableName = "";
		String idString = " where ";
		StringBuffer value = new StringBuffer();
		for(int i = 0; i < fields.length; i++){
			
			//找表名称
			if(fields[i].isAnnotationPresent(TableName.class)){
				if(count == 1){
					LoggerUtil.loggerDebug(logger, "[GenerateSql, generateUpdateSql]实体所指定的表名称重复了" + obj.getClass().getName());
					throw new Exception("实体所指定的表名称重复了" + obj.getClass().getName());
				}
				TableName tn = fields[i].getAnnotation(TableName.class);
				count ++;
				if(tn.name().equals("table")){
					String name = fields[i].getName();
					Method method = obj.getClass().getMethod("get"+ name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
					Object o = method.invoke(obj);
					tableName = o.toString().trim();
				}
				
			}else
			if(fields[i].isAnnotationPresent(PropertyAlias.class)){
				PropertyAlias pa = fields[i].getAnnotation(PropertyAlias.class);
				String aliasName = pa.alias();
				boolean isId = pa.isId();
				
				String name = fields[i].getName();
				
				Method method = obj.getClass().getMethod("get"+ name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
				Object o = method.invoke(obj);
				if(o == null || o.equals("")){
					continue;
				}
				if(isId){
					idString = idString + aliasName +"=" + o.toString();
				}else{
					value.append(" " +aliasName+"='" + o.toString().trim()+"', ");
				}
				
			}else if(fields[i].isAnnotationPresent(IgnoreProperty.class)){//忽略某个属性
				continue;
			}else{
				String name = fields[i].getName();
				Method method = obj.getClass().getMethod("get"+ name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
				Object o = method.invoke(obj);
				if(o == null || o.equals("")){
					continue;
				}
				value.append(" " +name+"='" + o.toString().trim()+"', ");
			}
		}
		if(0 == value.length()){
			return null;
		}
				
		if("".equals(tableName)){
			boolean flag = obj.getClass().isAnnotationPresent(TableName.class);
			if(flag){
				TableName tn = obj.getClass().getAnnotation(TableName.class);
				if(null != tn && "".equals(tn.name().trim())){
					throw new Exception("实体没有指定表名 " + obj.getClass().getName());
				}else if(tn != null){
					tableName = tn.name();
				}
			}else{
				tableName = obj.getClass().getSimpleName().toLowerCase();
			}
		}
		
		
		String sql = "update " + tableName + " set " + value.substring(0, value.lastIndexOf(",")) + idString;
		return sql;
	}
}
