package com.topn.controller.help;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-6-15 下午01:23:17
 * 
 * weighted rank (WR) = (v ÷ (v+m)) × R + (m ÷ (v+m)) × C
	其中：
	R = average for the movie (mean) = (Rating)
	（是用普通的方法计算出的平均分）
	v = number of votes for the movie = (votes)
	（投票人数，需要注意的是，只有经常投票者才会被计算在内）
	m = minimum votes required to be listed in the top 250 (currently 1250)
	（出分结果需要的最小票数，只有三两个人投票，就算得满分也没用的）
	C = the mean vote across the whole report
	（目前所有得分的平均得分）


 */
public class RankStrategyHelp {

	/**
	 * 目前所有参赛记录的平均得分
	 */
	public static double C = 8;
	
	/**
	 * 出分结果需要的最小票数
	 */
	public static final int M = 20;
	
	private static long all_rank_times = 1;
	
	private static double all_score = 1;
	
	/**
	 * 更新一次打分记录
	 * @param s
	 */
	public static void updateAGrade(float s){
		RankStrategyHelp.all_rank_times = RankStrategyHelp.all_rank_times + 1;
		RankStrategyHelp.all_score = RankStrategyHelp.all_score + s;
		RankStrategyHelp.C = RankStrategyHelp.all_score / RankStrategyHelp.all_rank_times;
	}
	
	/**
	 * 更新c值
	 * @param num
	 * @param all
	 */
	public static void updateC(long num, double all){
		if(num == 0){
			num = 1;
		}
		if(all == 0){
			all = 1;
		}
		RankStrategyHelp.all_rank_times = RankStrategyHelp.all_rank_times + num;
		RankStrategyHelp.all_score = RankStrategyHelp.all_score + all;
		RankStrategyHelp.C = RankStrategyHelp.all_score / RankStrategyHelp.all_rank_times;		
	}
	
	/**
	 * 更新c值，并返回我的权重分数
	 * @param grade
	 * @param times
	 * @return (v ÷ (v+m)) × R + (m ÷ (v+m)) × C
	 */
	public static float updateAGradeAndReturnMyAverage(float grade, float myall, int times){
		updateAGrade(grade);
		return (float) ((times*1.0/(times + M)) * (myall/times) + (M*1.0/(times + M))*C);
	}
}
