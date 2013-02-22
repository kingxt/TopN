package com.topn.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.topn.DAL.EntryFormDAL;
import com.topn.asynchronous.AsynchPool;
import com.topn.collection.PicturePartition;
import com.topn.controller.GeneratePictureController;
import com.topn.task.RankingListTask;
import com.topn.task.UpdatePicturePartitionTask;
import com.topn.test.TestTopN;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-6-15 下午12:49:26
 * 
 * 这个类的作用是：初始化服务器要的参数
 */
public class ServerInitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		StringBuilder sb = new StringBuilder();
		PicturePartition.clearAllOriginal(sb, PicturePartition.pp_original_0_15);
		PicturePartition.clearAllOriginal(sb, PicturePartition.pp_original_16_25);
		PicturePartition.clearAllOriginal(sb, PicturePartition.pp_original_26_35);
		PicturePartition.clearAllOriginal(sb, PicturePartition.pp_original_36_45);
		PicturePartition.clearAllOriginal(sb, PicturePartition.pp_original_46_over);
		PicturePartition.clearAllAlreadyAndReady(sb, PicturePartition.P_0_15);
		PicturePartition.clearAllAlreadyAndReady(sb, PicturePartition.P_16_25);
		PicturePartition.clearAllAlreadyAndReady(sb, PicturePartition.P_26_35);
		PicturePartition.clearAllAlreadyAndReady(sb, PicturePartition.P_36_45);
		PicturePartition.clearAllAlreadyAndReady(sb, PicturePartition.P_46_over);
		String sql = sb.toString();
		if (sql.lastIndexOf(",") - 1 > 0) {

			TestTopN.toDB.incrementAndGet();
			EntryFormDAL.getInstance().asynGradeTODB(
					sql.substring(0, sql.lastIndexOf(",")));
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		//获取c值
		GeneratePictureController.getInstance().getStaticC();
		//加载排行榜
		AsynchPool.getInstance().addTask(new RankingListTask(1));
		AsynchPool.getInstance().addTask(new RankingListTask(2));
		AsynchPool.getInstance().addTask(new UpdatePicturePartitionTask(2, "p_16_25"));
		AsynchPool.getInstance().addTask(new UpdatePicturePartitionTask(1, "p_16_25"));
	}

}
