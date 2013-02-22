package com.topn.flex;

import java.util.List;

import com.topn.bean.EntryForm;
import com.topn.bean.TO.LoginTO;
import com.topn.bean.TO.TipsCountTO;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-5 下午08:05:33
 * 
 * flex 服务接口
 */
public interface FlexService {

	/**
	 * 前段调用后端发送一个email
	 * @param emailAddress 传过来email地址
	 * @return 返回是否发送成功
	 */
	public boolean sendEmail(String emailAddress);
	
	/**
	 * 注册
	 * @param emailAddress email
	 * @param password 密码
	 * @param code 验证码
	 * @return 注册是否成功
	 */
	public boolean registe(String emailAddress, String password, String code);
	
	/**
	 * 登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 如果登录失败就返回null，否则返回LoginTO实例
	 */
	public LoginTO login(String username, String password);
	
	/**
	 * 打分保存到数据库
	 * @param entryId
	 * @param grade     打分的成绩
	 * @param thisAge	已经打分的年龄
	 * @param nextAge   下一个要打分年龄范围
	 * @param nextSex   下一个要打分的性别
	 * @return
	 */
	public EntryForm saveGrade(int entryId, float grade, int thisAge, String nextAge, int nextSex);
	
	/**
	 * 获取排名
	 * @param n  多少名开始的后续10名
	 * @return
	 */
	public List<EntryForm> getRankingList(int n, int sex, String type, int pid);
	
	/**
	 * 拿到建议
	 * @return
	 */
	public TipsCountTO getTipsCount(int pid);
	
	//public List<EntryForm> getRankingList
}
