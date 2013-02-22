package com.topn.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import com.topn.bean.EntryForm;
import com.topn.controller.help.RankStrategyHelp;
import com.topn.test.TestTopN;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-2 下午07:34:45
 * 
 * 图片分区，更具年龄段分成几块,但是每块都包含三个集合 jmap –dump:file=test.bin {pid}
 */
public class PicturePartition {
	
	/**
	 * 这里是代表循环的次数，loopCount代表照片轮番评论了两次就同步数据库
	 */
	public static int loopCount = 1;
	
	
	
	/**
	 * 从0 开始的计数器，此计数器是线程安全的，用来记录集合循环的次数
	 */
	public static AtomicInteger loop = new AtomicInteger(0);
	/**
	 * 排名取最大缓存前100个
	 */
	public static final int RANKING_SIZE = 100;
	
	/**
	 * 容器大小设计
	 */
	public static final int size_0_15 = 50;
	public static final int size_16_25 = 2000;
	public static final int size_26_35 = 150;
	public static final int size_36_45 = 150;
	public static final int size_46_over = 50;
	/**
	 * 这是上面各类集合的总和
	 */
	public static final int CACHE_SIZE_TOTAL = 2400;
	
	public static final int MALE = 1;
	public static final int FEMAIL = 2;
	
	public static final String P_0_15 = "p_0_15";
	public static final String P_16_25 = "p_16_25";
	public static final String P_26_35 = "p_26_35";
	public static final String P_36_45 = "p_36_45";
	public static final String P_46_over = "p_46_over";
	
	/**
	 * 年龄段的划分
	 */
	public static final int P1 = 15;
	public static final int P2 = 25;
	public static final int P3 = 35;
	public static final int P4 = 45;
	/*
	 *1-16    一个年龄段三个集合 , 初始时候这个集合缓存500
	 */
	public static MyConcurrentHashMap<Integer, EntryForm> pp_original_0_15 = new MyConcurrentHashMap<Integer, EntryForm>(size_0_15, 1, 8);
	public static MyConcurrentHashMap<Integer, EntryForm> pp_ready_0_15 = new MyConcurrentHashMap<Integer, EntryForm>(size_0_15, 1, 8);
	public static MyConcurrentHashMap<Integer, EntryForm> pp_already_0_15 = new MyConcurrentHashMap<Integer, EntryForm>(size_0_15, 1, 8);
	
	/*
	 *16_25  一个年龄段三个集合 ， 这个集合年龄段为7000, 运行100个线程并发
	 */
	public static MyConcurrentHashMap<Integer, EntryForm> pp_original_16_25 = new MyConcurrentHashMap<Integer, EntryForm>(size_16_25, 1, 128);
	public static MyConcurrentHashMap<Integer, EntryForm> pp_ready_16_25 = new MyConcurrentHashMap<Integer, EntryForm>(size_16_25, 1, 128);
	public static MyConcurrentHashMap<Integer, EntryForm> pp_already_16_25 = new MyConcurrentHashMap<Integer, EntryForm>(size_16_25, 1, 128);
	
	/*
	 *26_35  一个年龄段三个集合  这个集合年龄段为1000
	 */
	public static MyConcurrentHashMap<Integer, EntryForm> pp_original_26_35 = new MyConcurrentHashMap<Integer, EntryForm>(size_26_35, 1, 16);
	public static MyConcurrentHashMap<Integer, EntryForm> pp_ready_26_35 = new MyConcurrentHashMap<Integer, EntryForm>(size_26_35, 1, 16);
	public static MyConcurrentHashMap<Integer, EntryForm> pp_already_26_35 = new MyConcurrentHashMap<Integer, EntryForm>(size_26_35, 1, 16);
	
	/*
	 *36_45  一个年龄段三个集合  这个集合年龄段为1000
	 */
	public static MyConcurrentHashMap<Integer, EntryForm> pp_original_36_45 = new MyConcurrentHashMap<Integer, EntryForm>(size_36_45, 1, 16);
	public static MyConcurrentHashMap<Integer, EntryForm> pp_ready_36_45 = new MyConcurrentHashMap<Integer, EntryForm>(size_36_45, 1, 16);
	public static MyConcurrentHashMap<Integer, EntryForm> pp_already_36_45 = new MyConcurrentHashMap<Integer, EntryForm>(size_36_45, 1, 16);
	
	/*
	 *46_over  一个年龄段三个集合 这个集合年龄段为500
	 */
	public static MyConcurrentHashMap<Integer, EntryForm> pp_original_46_over = new MyConcurrentHashMap<Integer, EntryForm>(size_46_over, 1, 8);
	public static MyConcurrentHashMap<Integer, EntryForm> pp_ready_46_over = new MyConcurrentHashMap<Integer, EntryForm>(size_46_over, 1, 8);
	public static MyConcurrentHashMap<Integer, EntryForm> pp_already_46_over = new MyConcurrentHashMap<Integer, EntryForm>(size_46_over, 1, 8);
	
	/*
	 * 这个集合用来存排行榜, 这里要注意的是如果往集合中添加数据一定要加锁，否则就会造成
	 * 多线程访问异常
	 */
	public static List<EntryForm> rankingListGirl = new ArrayList<EntryForm>();
	public static List<EntryForm> rankingListBoy = new ArrayList<EntryForm>();
	
	/**
	 * 拿到前十名
	 * @return
	 */
	public static List<EntryForm> getTop10(){
		return null;
	}
	
	/**
	 * 合并两个集合, 这里可以不用同步，因为MyConcurrentHashMap  的remove操作已经实现了锁操作
	 * @param from 数据取自
	 * @param to 合并到那个集合中去
	 */
	public static void merger(MyConcurrentHashMap<Integer, EntryForm> from, MyConcurrentHashMap<Integer, EntryForm> to){
		to.putAll(from);
		from.clear();
	}
	
	/**
	 * 清空所有原始集合
	 */
	public static void clearAllOriginal(StringBuilder sb, MyConcurrentHashMap<Integer, EntryForm> cacheOriginal){		
		object2DB(cacheOriginal, sb);
	}
	
	/**
	 * 清空所有除原始集合的其他评价照片集合
	 */
	public static void clearAllAlreadyAndReady(StringBuilder sb, String age){
		MyConcurrentHashMap<Integer, EntryForm> cacheReady = null;
		MyConcurrentHashMap<Integer, EntryForm> cacheAlready = null;
		if(age.equals(PicturePartition.P_0_15)){		
			cacheReady = PicturePartition.pp_ready_0_15;
			cacheAlready = PicturePartition.pp_already_0_15;
		}else if(age.equals(PicturePartition.P_16_25)){		
			cacheReady = PicturePartition.pp_ready_16_25;
			cacheAlready = PicturePartition.pp_already_16_25;
		}else if(age.equals(PicturePartition.P_26_35)){		
			cacheReady = PicturePartition.pp_ready_26_35;
			cacheAlready = PicturePartition.pp_already_26_35;
		}else if(age.equals(PicturePartition.P_36_45)){	
			cacheReady = PicturePartition.pp_ready_36_45;
			cacheAlready = PicturePartition.pp_already_36_45;
		}else if(age.equals(PicturePartition.P_46_over)){		
			cacheReady = PicturePartition.pp_ready_46_over;
			cacheAlready = PicturePartition.pp_already_46_over;
		}
		
		object2DB(cacheReady, sb);
		object2DB(cacheAlready, sb);
	}
	
	/**
	 * 将对象生成字符串去更新数据库
	 * @param ef
	 * @param sb
	 */
	public static void object2DB(MyConcurrentHashMap<Integer, EntryForm> ef, StringBuilder sb){
TestTopN.updateLength.addAndGet(ef.size());
	
		Set<Entry<Integer, EntryForm>> keys = ef.entrySet();
		if(keys == null)return;
		for (Iterator<Entry<Integer, EntryForm>> iterator = keys.iterator(); iterator.hasNext();) {
			Entry<Integer, EntryForm> entry = (Entry<Integer, EntryForm>) iterator
					.next();
			EntryForm temp = ef.remove(entry.getKey());
			if(temp == null)continue;
			sb.append("(").append(temp.getEntryId()).append(",").append(temp.getTimes()).append(",").append(temp.getTotalScore()).append(",").append(temp.getAverageScore()).append("),");
		}
	}
	
	/**
	 * 根据年龄返回这个年龄所属段
	 * @param age
	 * @return
	 */
	public static String getStringAge(int age){
		String ageS = "";
		if(age <= PicturePartition.P1){
			ageS = PicturePartition.P_0_15;
		}else if(age <= PicturePartition.P2){
			ageS = PicturePartition.P_16_25;
		}else if(age <= PicturePartition.P3){
			ageS = PicturePartition.P_26_35;
		}else if(age <= PicturePartition.P4){
			ageS = PicturePartition.P_36_45;
		}else{
			ageS = PicturePartition.P_46_over;
		}
		return ageS;
	}
	
	/**
	 * 获取指定年龄段的人数
	 * @param age
	 * @return
	 */
	public static int getAgeSize(String age){
		if(age.equals(PicturePartition.P_0_15)){
			return size_0_15;
		}else if(age.equals(PicturePartition.P_16_25)){
			return size_16_25;
		}else if(age.equals(PicturePartition.P_26_35)){
			return size_26_35;
		}else if(age.equals(PicturePartition.P_36_45)){
			return size_36_45;
		}else if(age.equals(PicturePartition.P_46_over)){
			return size_46_over;
		}
		return 0;
	}
}
