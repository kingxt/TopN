package com.topn.bean.TO;

import java.util.List;

/**
 * 搜索的某类型的好友
 * 创建人 KingXt
 * 创建时间：2011-4-16 下午03:27:13
 */
public class SearchFriendsTO {

	private List<FriendTO> fts;
	
	private int total;

	public List<FriendTO> getFts() {
		return fts;
	}

	public void setFts(List<FriendTO> fts) {
		this.fts = fts;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
