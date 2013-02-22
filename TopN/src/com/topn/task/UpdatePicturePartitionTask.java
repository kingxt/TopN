package com.topn.task;

import java.util.Iterator;
import java.util.List;

import com.topn.DAL.EntryFormDAL;
import com.topn.DAL.query.EntryFormQuery;
import com.topn.asynchronous.AsynchPool;
import com.topn.bean.EntryForm;
import com.topn.collection.MyConcurrentHashMap;
import com.topn.collection.PicturePartition;
import com.topn.test.TestTopN;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-7 下午09:20:07
 * 
 * 更新缓存中的图片
 */
public class UpdatePicturePartitionTask implements Runnable {

	private int sex = 2;
	private String age;

	@Override
	public void run() {
		MyConcurrentHashMap<Integer, EntryForm> cacheOriginal = null;

		// 首先判断要处理的缓存
		if (age.equals(PicturePartition.P_0_15)) {
			cacheOriginal = PicturePartition.pp_original_0_15;
		} else if (age.equals(PicturePartition.P_16_25)) {
			cacheOriginal = PicturePartition.pp_original_16_25;
		} else if (age.equals(PicturePartition.P_26_35)) {
			cacheOriginal = PicturePartition.pp_original_26_35;
		} else if (age.equals(PicturePartition.P_36_45)) {
			cacheOriginal = PicturePartition.pp_original_36_45;
		} else if (age.equals(PicturePartition.P_46_over)) {
			cacheOriginal = PicturePartition.pp_original_46_over;
		}
		// 获取年龄段
		String[] ages = age.split("_");
		if (ages.length < 3)
			return;
		int a1 = Integer.parseInt(ages[1]);
		int a2 = 100;
		if (!ages[2].equals("over")) {
			a2 = Integer.parseInt(ages[2]);
		}
		
		TestTopN.fromDB.incrementAndGet();
		if (age.equals(PicturePartition.P_36_45)) {
			int a = 0;
			a++;
		}
		//将缓存中的记录用字符串方式体现,然后更新至数据库
		StringBuilder sb = new StringBuilder();
		PicturePartition.clearAllOriginal(sb, cacheOriginal);
		PicturePartition.clearAllAlreadyAndReady(sb, age);
		String sql = sb.toString();
		if (sql.lastIndexOf(",") - 1 > 0) {

			TestTopN.toDB.incrementAndGet();
			if (age.equals(PicturePartition.P_16_25)) {
				int a = 0;
				a += 6;
			}
			System.out.println("sql = " + sql);
			EntryFormDAL.getInstance().asynGradeTODB(
					sql.substring(0, sql.lastIndexOf(",")));
		}
		/*
		 * 如果设计的好的话,可以在不用清空集合的前提下到数据库中取数据,但是这里没有更好的办法
		 * 按照已经实现的此种方案的话,在清空集合到取到数据这段时间内,集合内事空的,用户的数据只能够从数据库中直接去了,这段数据交换时间可能比较长点
		 * 将来看有什么更好的办法
		 *  获取指定年龄段指定数字的人
		 */
		List<EntryForm> efs = EntryFormQuery.getInstance().getNewEntryForm(
				PicturePartition.getAgeSize(age), a1, a2);
		TestTopN.print(age + "||  " + efs.size());
		//synchronized (cacheOriginal) {
			for (Iterator<EntryForm> iterator = efs.iterator(); iterator
					.hasNext();) {
				EntryForm entryForm = (EntryForm) iterator.next();
				cacheOriginal.put(entryForm.getEntryId(), entryForm);
			}
			AsynchPool.pictureIsArrived = true;
		//}
		PicturePartition.loop.set(0);
		//标识获取照片已经到达了
		

		/**
		 * 这里有一个问题就是：按照性别更新排行榜可能导致某一个性别排行榜更新过慢
		 */
		if (AsynchPool.rankingListIsArrived) {
			AsynchPool.getInstance().addTask(new RankingListTask(sex));
			AsynchPool.rankingListIsArrived = false;
		}
	}

	public UpdatePicturePartitionTask(int sex, String age) {
		super();
		this.sex = sex;
		this.age = age;
	}

	/**
	 * 
	 * @param ef         已经在缓存中的数据
	 * @param loaded	  刚加进来的数据
	 * @param sb         要拼写的字符串
	 *//*
	public void object2DB(MyConcurrentHashMap<Integer, EntryForm> ef, MyConcurrentHashMap<Integer, EntryForm> loaded,
			StringBuilder sb) {
		
		Set<Entry<Integer, EntryForm>> keys = ef.entrySet();
		if (keys == null)
			return;
		for (Iterator<Entry<Integer, EntryForm>> iterator = keys.iterator(); iterator
				.hasNext();) {
			Entry<Integer, EntryForm> entry = (Entry<Integer, EntryForm>) iterator
					.next();
			EntryForm temp = ef.remove(entry.getKey());
			if (temp == null)
				continue;
			
			 * 为什么这么做是因为：如果没有更新进来的人在缓存中，必须要保证缓存中是最新的
			 
			if(null != loaded.remove(entry.getKey())){
				ef.put(entry.getKey(), entry.getValue());
			}
			sb.append("(" + temp.getEntryId() + "," + temp.getTimes() + ","
					+ temp.getTotalScore() + "),");
		}
	}*/
}
