package com.topn.bean.TO;

import java.util.List;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-24 下午07:41:19
 * 
 * 返回到页面的心情
 */
public class MoodTipsPageTO {

	private MoodTipsTO mood;
	
	private List<MoodTipsTO> mto;

	public MoodTipsTO getMood() {
		return mood;
	}

	public void setMood(MoodTipsTO mood) {
		this.mood = mood;
	}

	public List<MoodTipsTO> getMto() {
		return mto;
	}

	public void setMto(List<MoodTipsTO> mto) {
		this.mto = mto;
	}
	
	
}
