package com.topn.factory;

import org.apache.log4j.Logger;

import com.topn.DAL.BaseDAL;
import com.topn.flex.FlexServiceImpl;

import flex.messaging.FactoryInstance;
import flex.messaging.FlexFactory;
import flex.messaging.config.ConfigMap;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-4 下午09:59:27
 * 
 * 这个类返回工厂实例供flex前段调用
 */
public class SpringFactoryInstance extends FactoryInstance {

	private static Logger logger = Logger.getLogger(BaseDAL.class);
	
	public SpringFactoryInstance(FlexFactory factory, String id,
			ConfigMap properties) {
		super(factory, id, properties);
	}

	@Override
	public Object lookup() {
		String beanName = getSource();
		return FlexServiceImpl.getInstance();
		//return super.lookup();
	}

}
