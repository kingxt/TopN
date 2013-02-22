package com.topn.bean.TO;


/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-6 下午03:58:31
 * 
 * 登录后返回的对象
 */
public class LoginTO {

	public static final String CHECK_FAIL = "您的参赛照片未审核通过，可能是由于和你本人的信息不合";
	public static final String CHECK_ING = "您的参赛照正在审核";

	/**
	 * 登录人的id标识
	 */
	private int personalInfoId;
	
	/**
	 * 评论次数
	 */
	private int times;
	
	/**
	 * 评论总分
	 */
	private float totalScore;
	
	/**
	 * 用户个人评价图片的url
	 */
	private String imageURL;
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 提醒用户的内容
	 */
	private String attention = "";
	
	/**
	 * 排行榜
	 */
	private String rankingList;
	
	/**
	 * 初始化相册的三张照片的url
	 */
	private String initPicURL = null;
	
	private int sex;

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

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	

	public String getRankingList() {
		return rankingList;
	}

	public void setRankingList(String rankingList) {
		this.rankingList = rankingList;
	}

	public String getInitPicURL() {
		return initPicURL;
	}

	public void setInitPicURL(String initPicURL) {
		this.initPicURL = initPicURL;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getPersonalInfoId() {
		return personalInfoId;
	}

	public void setPersonalInfoId(int personalInfoId) {
		this.personalInfoId = personalInfoId;
	}
	
	
}
