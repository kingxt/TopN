package com.topn.util;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-5-1 下午05:15:03
 */
public class FileUtil implements Table{

	/**
	 * 将大头像更改成小头像
	 * @param large ../user_picture/user/e5ca64ef-deaa-4d6b-9176-301035c87e5a.jpg
	 * @return
	 */
	public static String swithPersonPhototo50(String large){
		if(StringUtil.isBlank(large)){
			return PARENT_PATH + DEFAULT_PERSON_PHOTO;
		}
		String pc[] = large.split("/");
		if(pc.length != 3){
			return PARENT_PATH + DEFAULT_PERSON_PHOTO;
		}
		return PARENT_PATH + pc[0] + FILE_DIVIDE + pc[1]  + FILE_DIVIDE+ USER_PHOTO_FILE_50_50_NAME + FILE_DIVIDE + pc[2];
	}
	
	public static void main(String[] args) {
		System.out.println(swithPersonPhototo50("user_picture/user/e5ca64ef-deaa-4d6b-9176-301035c87e5a.jpg"));
	}
}
