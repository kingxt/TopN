package com.topn.bean.TO;

import java.util.List;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-24 下午10:25:24
 * 
 * 封装返回到页面的内容
 */
public class LeaveMsgTipsPageTO {

	private LeaveMsgTipsTO leaveMsg;

	private List<LeaveMsgTipsTO> mto;

	

	public LeaveMsgTipsTO getLeaveMsg() {
		return leaveMsg;
	}

	public void setLeaveMsg(LeaveMsgTipsTO leaveMsg) {
		this.leaveMsg = leaveMsg;
	}

	public List<LeaveMsgTipsTO> getMto() {
		return mto;
	}

	public void setMto(List<LeaveMsgTipsTO> mto) {
		this.mto = mto;
	}
	
	
}
