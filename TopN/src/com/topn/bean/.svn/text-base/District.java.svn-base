package com.topn.bean;

import java.io.Serializable;

import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-12 下午12:15:13
 * 
 * 区
 */
@TableName(name="district")
public class District implements Serializable{

	
	@PropertyAlias(alias="district_id", isId=true)
	private int districtId;
	
	@PropertyAlias(alias="city_id")
	private int cityId;
	
	private String name;

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
