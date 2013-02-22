package com.topn.bean;


import com.topn.util.annotation.IgnoreProperty;
import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-2 下午07:41:01
 * 
 * 参赛图片
 */
@TableName(name="entry_form")
public class EntryForm {

	@PropertyAlias(alias="entry_id", isId=true)
	private int entryId;
	
	@PropertyAlias(alias="person_info_id")
	private int personInfoId;
	
	private String url;
	
	private int times = 1;
	
	@PropertyAlias(alias="total_score")
	private float totalScore;
	
	@PropertyAlias(alias="average_score")
	private float averageScore;
	
	//性别  1：男        2： 女
	private int sex;
	
	//年龄
	private int age;
	
	@IgnoreProperty
	private String type;

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public int getPersonInfoId() {
		return personInfoId;
	}

	public void setPersonInfoId(int personInfoId) {
		this.personInfoId = personInfoId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}

	public float getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
