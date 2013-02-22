package com.topn.bean;

import java.io.Serializable;

import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

@TableName(name="high_school")
public class HighSchool implements Serializable{

	@PropertyAlias(alias="hs_id", isId=true)
	private int hsId;
	
	@PropertyAlias(alias="district_id")
	private int districtId;
	
	private String name;

	public int getHsId() {
		return hsId;
	}

	public void setHsId(int hsId) {
		this.hsId = hsId;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
