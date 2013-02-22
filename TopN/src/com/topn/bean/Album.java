package com.topn.bean;

import java.util.Date;

/**
 * 创建人 youxishow
 * 创建时间: 2011-3-26 下午03:45:32
 * 相册
 */
public class Album {

	private int albumId;
	private String owner;
	private String name;
	private String password;
	private int needPwd;
	private String detail;
	private String cover;
	private int personalInfoId;
	private Date createTime;
	private int isEntry;
	
	private int total;
	
	
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNeedPwd() {
		return needPwd;
	}
	public void setNeedPwd(int needPwd) {
		this.needPwd = needPwd;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getPersonalInfoId() {
		return personalInfoId;
	}
	public void setPersonalInfoId(int personalInfoId) {
		this.personalInfoId = personalInfoId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getIsEntry() {
		return isEntry;
	}
	public void setIsEntry(int isEntry) {
		this.isEntry = isEntry;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}
