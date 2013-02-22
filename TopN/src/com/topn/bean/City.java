package com.topn.bean;

import java.io.Serializable;

import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

@TableName(name="city")
public class City implements Serializable{

	@PropertyAlias(alias="city_id", isId=true)
	private int cityId;
	
	@PropertyAlias(alias="prov_id")
	private int provinceId;
	
	private String name;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
