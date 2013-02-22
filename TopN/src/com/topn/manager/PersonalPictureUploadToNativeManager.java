package com.topn.manager;

import java.io.File;

import com.topn.strategy.IM4JavaPictureDealImpl;

/**
 * 
 * @author KingXt
 * 管理图片上传的类
 * 此类的作用是为了管理图片处理策略，默认图片处理策略是im4java
 */
public class PersonalPictureUploadToNativeManager {

	/*
	 *首先做一个单粒 
	 */
	private static final PersonalPictureUploadToNativeManager ppum = new PersonalPictureUploadToNativeManager();
	
	private PersonalPictureUploadToNativeManager(){}
	
	public static PersonalPictureUploadToNativeManager getInstance(){
		return ppum;
	}
	
	/**
	 * 图片处理策略，将来可以改成依赖注入的属性，默认图片处理策略
	 */
	private IM4JavaPictureDealImpl pds = IM4JavaPictureDealImpl.getInstance();
	

	/**
	 * 将上传的某个图片文件经过处理保存到指定路径
	 * @param file 上传的文件
	 * @param path 已经进过业务处理的路径
	 */
	public void dealPicture(File file, String path){
		
		pds.dealImageToForNative(file, path, IM4JavaPictureDealImpl.JPG);
		
	}
	
}
