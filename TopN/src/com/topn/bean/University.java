package com.topn.bean;

import java.io.Serializable;

import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

@TableName(name="university")
public class University implements Serializable{

	@PropertyAlias(alias="uni_id", isId=true) 
	private int universityId;
	
	@PropertyAlias(alias="prov_id")
	private int provinceId;
	
	private String name;



	public int getUniversityId() {
		return universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
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
