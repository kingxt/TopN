package com.topn.bean;

import java.io.Serializable;
import java.util.Date;

import com.topn.util.DateUitl;
import com.topn.util.StringUtil;
import com.topn.util.annotation.IgnoreProperty;
import com.topn.util.annotation.PropertyAlias;
import com.topn.util.annotation.TableName;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-2 下午04:31:31
 * 
 * 注册人实体
 */

public class PersonalInfo implements Serializable{


	@PropertyAlias(alias="person_info_id", isId=true)
	private int personalInfoId;

	@TableName(name="table")
	private String tableName;
	
	@PropertyAlias(alias="user_name")
	private String userName;
	
	private String password;
	
	/**
	 * 1: 男
	 * 2：女
	 */
	private int sex;
	
	private String name;
	
	@PropertyAlias(alias="nick_name")
	private String nickName;
	
	private String job;
	
	private String ps;
	
	private String photo;
	
	private String birthday;
	
	private String phone;
	
	private String address;
	
	private String msn;
	
	private String qq;
	
	private String college = "";
	
	@PropertyAlias(alias="col_level")
	private String colLevel = "";
	
	@PropertyAlias(alias="high_school")
	private String highSchool = "";
	
	@PropertyAlias(alias="high_level")
	private String highLevel = "";

	private String hobby = "";
	
	@PropertyAlias(alias="other_hobby")
	private String otherHobby;
	
	private String album;
	
	@IgnoreProperty
	private Date registerTime;
	
	@PropertyAlias(alias="update_time")
	private String updateTime;
	
	private String permission;

	

	public int getPersonalInfoId() {
		return personalInfoId;
	}

	public void setPersonalInfoId(int personalInfoId) {
		this.personalInfoId = personalInfoId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getBirthdayYear(){		
		return DateUitl.yearOfDate(birthday);
	}
	
	public String getBirthdayMonth(){
		return DateUitl.monthOfDate(birthday);
	}
	
	public String getBirthdayDay(){
		return DateUitl.dayOfDate(birthday);
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}


	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getColLevel() {
		return colLevel;
	}

	public void setColLevel(String colLevel) {
		this.colLevel = colLevel;
	}

	public String getHighSchool() {
		return highSchool;
	}

	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}

	public String getHighLevel() {
		return highLevel;
	}

	public void setHighLevel(String highLevel) {
		this.highLevel = highLevel;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getOtherHobby() {
		return otherHobby;
	}

	public void setOtherHobby(String otherHobby) {
		this.otherHobby = otherHobby;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getSchool(){
		String str = "";
		if(!StringUtil.isBlank(highSchool)){
			str += "高中: " + highSchool; 
		}
		if(!StringUtil.isBlank(college)){
			str += "   大学: " + college; 
		}
		
		return str;
	}

}
