package com.topn.bean;

import java.io.Serializable;

import com.topn.util.annotation.PropertyAlias;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-26 下午02:04:29
 * 
 * 兴趣类型类
 */
public class HobbyType implements Serializable{

	@PropertyAlias(alias="type_id")
	private int typeId;
	
	private String name;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
