package com.topn.bean;

import java.util.Date;

public class PentryPhoto {

	private int pentryId;
	private float score;
	private int isEvaluating;
	private Date selectedTime;
	private int albumId;
	private String url;
	private String samllUrl;
	private String detail;

	public int getPentryId() {
		return pentryId;
	}

	public void setPentryId(int pentryId) {
		this.pentryId = pentryId;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getIsEvaluating() {
		return isEvaluating;
	}

	public void setIsEvaluating(int isEvaluating) {
		this.isEvaluating = isEvaluating;
	}

	public Date getSelectedTime() {
		return selectedTime;
	}

	public void setSelectedTime(Date selectedTime) {
		this.selectedTime = selectedTime;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
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

	public String getSamllUrl() {
		return samllUrl;
	}

	public void setSamllUrl(String samllUrl) {
		this.samllUrl = samllUrl;
	}
	
}
