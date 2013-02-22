package com.topn.factory;

import org.apache.log4j.Logger;

import com.topn.DAL.BaseDAL;

import flex.messaging.FactoryInstance;
import flex.messaging.FlexFactory;
import flex.messaging.config.ConfigMap;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-4 下午09:58:15
 * 
 * 这个类负责创建实例工厂
 */
public class FlexFactoryImpl implements FlexFactory{

	private static Logger logger = Logger.getLogger(BaseDAL.class);
	@Override
	public FactoryInstance createFactoryInstance(String id, ConfigMap properties) {
		logger.info("Create FactoryInstance."); 
		SpringFactoryInstance instance = new SpringFactoryInstance(this, id, properties);
		instance.setSource(properties.getPropertyAsString(SOURCE, instance.getId())); 
		return instance;
	}

	@Override
	public Object lookup(FactoryInstance instanceInfo) {
		return instanceInfo.lookup();   
	}

	@Override
	public void initialize(String arg0, ConfigMap arg1) {
		// TODO Auto-generated method stub
		
	}

}
