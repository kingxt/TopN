package com.topn.bean.TO;

import java.io.Serializable;

import com.topn.util.StringUtil;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-23 下午10:38:04
 * 
 * 参赛照片
 */
public class CompetitionPhotoTO implements Serializable{

	private int pEntryId;
	
	private String url;
	
	private String smallURL;
	
	private String detail;
	
	private float score;

	public int getpEntryId() {
		return pEntryId;
	}

	public void setpEntryId(int pEntryId) {
		this.pEntryId = pEntryId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getSmallURL() {
		
		return smallURL;
	}
	
	
}
