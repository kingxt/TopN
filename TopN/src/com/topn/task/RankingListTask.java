package com.topn.task;

import java.util.List;

import com.topn.DAL.query.EntryFormQuery;
import com.topn.asynchronous.AsynchPool;
import com.topn.bean.EntryForm;
import com.topn.collection.PicturePartition;
import com.topn.test.TestTopN;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-6 下午11:28:56
 * 
 * 这个类是异步类，此类的作用是用来获取最新排名
 */
public class RankingListTask implements Runnable {

	private int sex;
	
	
	public RankingListTask(int sex) {
		super();
		this.sex = sex;
	}
	
	@Override
	public void run() {
		List<EntryForm> efs = EntryFormQuery.getInstance().getTopN(sex);
		List<EntryForm> rankingList = null;
		switch(sex){
			case 1:rankingList = PicturePartition.rankingListBoy;break;
			case 2:rankingList = PicturePartition.rankingListGirl;break;
			default: rankingList = PicturePartition.rankingListGirl;
		}
		synchronized (rankingList) {
			rankingList.clear();
			int len = efs.size();
			
TestTopN.rank.incrementAndGet();			
			
			for(int i = 0; i < len; i++){
				rankingList.add(efs.get(i));
			}
			AsynchPool.rankingListIsArrived = true;
		}
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}

}
