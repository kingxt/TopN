package com.topn.util;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-9 下午10:14:00
 * 
 * 所有的表接口,与存储过程名字
 */
public interface Table {
	public static final String USER_PHOTO_FILE = "user";
	public static final String USER_PHOTO_FILE_50_50_NAME = "user_small";
	//默认的头像url
	public static final String DEFAULT_PERSON_PHOTO = "pictures/default_comp_pic.png";
	
	//静态东西的根路径
	public static final String PROJECT_BASE_PATH = "D:/program/staticfile/TopN/";
	//参数照片上传时候的临时位置
	public static final String COM_TEMP_PATH = PROJECT_BASE_PATH+"temp";
	//用户头像路径
	public static final String USER_PHOTO_PATH = PROJECT_BASE_PATH+"user_picture/user/";
	//用户小头像路径
	public static final String USER_PHOTO_PATH_50_50 = PROJECT_BASE_PATH+"user_picture/user/user_small/";
	//相册封面
	public static final String ALBUM_COVER_PATH = "pictures/cover.jpg";
	
	//默认参赛照片
	public static final String DEFAULT_COMP_PHOTO = "pictures/default_topn_pic.png";
	public static final String PARENT_PATH ="../";
	public static final String FILE_DIVIDE ="/";
	//下面是五个用户表
	public static final String PERSON_INFO_ONE = "person_info_one";
	public static final String PERSON_INFO_TWO = "person_info_two";
	public static final String PERSON_INFO_THREE = "person_info_three";
	public static final String PERSON_INFO_FOUR = "person_info_four";
	public static final String PERSON_INFO_FIVE = "person_info_five";
	
	public static final int MOOD_PAGE_SIZE = 10; 
	public static final int VISITOR_MOOD_PAGE_NUM = 20; 
	
	
	public static final int USER_PHOTO_W = 165;
	public static final int USER_PHOTO_H = 180;
	public static final int USER_PHOTO_SMALL_SIZE = 50;
	
	public static final int COM_PHOTO_W = 380;
	public static final int COM_PHOTO_H = 450;
	public static final int COM_PHOTO_W_SMALL = 187;
	public static final int COM_PHOTO_H_SMALL = 211;
	
	//标识  用户注册多久时间内显示好友推荐列表
	public static final int USER_RECOMMEND_FRIEND_DAY = 20;
	
	//搜索好友时候一页显示多少个
	public static final int FRIEND_SEARCH_RESULT_PAGE_SIZE = 10;
	
	//------------------------------------------------------------
	//下面是类PersonalInfoQuery要用到的存储过程
	//--------------------------------------------------------------
	//注册
	public static final String SP_LOGIN = "sp_login";
	
	public static final String SP_VERIFY_USER_NAME = "sp_verify_user_name";
	
	// 存储过程，按照id查询
	public static final String SP_PERSON_INFO_SELECTBY_ONLYID = "sp_person_info_selectby_onlyid";

	// 根据用户id获取此人所有的参赛照片
	public static final String SP_PENTRY_PHOTO_SELECTBY_PID = "sp_pentry_photo_selectby_pid";

	// 返回用户所用相册的和封面
	public static final String SP_ALBUM_GETID_AND_COVER = "sp_album_getid_and_cover";
	
	//提醒信息分类总条数
	public static final String SP_ALL_TIPS_COUNT = "sp_all_tips_count";
	
	//好友申请提醒
	public static final String SP_FRIEND_TEMP_SELECT_RECORD_BYPID = "sp_friend_temp_select_record_bypid";
	
	//系统提醒
	public static final String SP_TIPS_SELECT_MESSAGE_BYPID = "sp_tips_select_message_bypid";

	//心情提醒
	public static final String SP_MOOD_TIPS_SELECT_INFO_ABOUT_TIPS = "sp_mood_tips_select_info_about_tips";
	
	//差自己的心情
	public static final String SP_MOOD_SELECT_MYMOOD_BY_PAGE_NUM = "sp_mood_select_mymood_by_page_num";
	
	//获取所有的表情
	public static final String SP_EXPRESSION_SELECT_ALL = "sp_expression_select_all";
	
	public static final String SP_ENTRY_FORM_SELECTBY_PID = "sp_entry_form_selectby_pid";
	
	public static final String SP_FRIENDS_VERIFY_ROLE_OF_VISITOR = "sp_friends_verify_role_of_visitor";
	
	//------------------------------------------------------------
	//下面是类FriendQuery要用到的存储过程
	//--------------------------------------------------------------
	
	//查找所有的心情
	public static final String SP_MOOD_SELECT_ALL_RECENT_MOOD = "sp_mood_select_all_recent_mood";
	//查找心情的所有回复
	public static final String SP_MOOD_SELECT_ALL_REPLY = "sp_mood_select_all_reply";
	//找好友,包括不同类型
	public static final String SP_FRIENDS_SELECTBY_RELATE = "sp_friends_selectby_relate";

	//找好友,所有的好友
	public static final String SP_FRIENDS_SELECT_ALL_FRIENDS = "sp_friends_select_all_friends";
	
	//留言
	public static final String SP_NOTES_LEAVE_A_MESSAGE = "sp_notes_leave_a_message";
	
	//获取某人的留言
	public static final String SP_NOTES_SELECT_NOTEBY_TARGET_ID = "sp_notes_select_noteby_target_id";
	
	//获得留言的所有回复
	public static final String SP_REPLY_NOTE_SELECTBY_NOTE_ID = "sp_reply_note_selectby_note_id";
	
	
	//留言回复
	public static final String SP_REPLY_NOTE_ADD_REPLY = "sp_reply_note_add_reply";
	
	//获取最近访客
	public static final String SP_RECENT_VISIT_SELECTBY_ID = "sp_recent_visit_selectby_id";
	
	//获取好友的好友
	public final String SP_FRIENDS_SELECT_FRIENDS_OF_OTHER = "sp_friends_select_friends_of_other";
	
	public final String SP_FRIENDS_COUNT_FRIEND_NUM = "sp_friends_count_friend_num";
	
	public final String SP_FRIENDS_RECOMMEND_FRIEND = "sp_friends_recommend_friend";
	
	public final String SP_NOTES_SELECT_WHEN_VISIT_OTHERS = "sp_notes_select_when_visit_others";
	
	
	
	//------------------------------------------------------------
	//下面是类EntryFormQuery要用到的存储过程
	//--------------------------------------------------------------
	
	//根据学校搜索排名
	public static final String SP_ENTRY_FORM_GET_RANKING_LIST_BY_SCHOOL = "sp_entry_form_get_ranking_list_by_school";
	
	public static final String RANKING_LIST = "sp_select_ranking_list";
	public static final String NEW_ENTRY_FORM = "sp_select_photos_from_entry";
	public static final String SP_ENTRY_FORM_SELECT_ONE = "sp_entry_form_select_one";
	public static final String SP_ENTRY_FORM_GETLIST_BY_AGE = "sp_entry_form_getlist_by_age";
	//获取topn排行
	public static final String SP_ENTRY_FORM_GET_PERSONAL_RANK = "sp_entry_form_get_personal_rank";
	//获取c值
	public static final String SP_ENTRY_FORM_GET_GLOBAL_AVERAGE = "sp_entry_form_get_global_average";
	
	//------------------------------------------------------------
	//下面是类AlbumInfoDAL要用到的存储过程
	//--------------------------------------------------------------
	// 存储过程，按照id查询该用户的相册
	public static final String SP_ALBUM_SELECTBY_PID="sp_album_selectby_pid";
	
	// 创建一个相册
	public static final String SP_ALBUM_CREATE_ALBUM="sp_album_create_album";	

	// 加载上传图片页面时获取用户所有相册名
	public static final String SP_ALBUM_GETLIST_WHEN_LOAD_PHOTO="sp_album_getlist_when_load_photo";
	
	public static final String SP_ALBUM_UPDATE_NAME_AND_DETAIL="sp_album_update_name_and_detail";
	
	public static final String SP_ALBUM_DELETE_BY_PID_AND_AID="sp_album_delete_by_pid_and_aid";

	public static final String SP_ALBUM_MODIFY_COVER="sp_album_modify_cover";
	
	//------------------------------------------------------------
	//下面是类FriendDAL要用到的存储过程
	//--------------------------------------------------------------
	//添加好友存储过程
	public static final String SP_FRIEND_TEMP_REPLY_ADD_FRIEND = "sp_friend_temp_reply_add_friend";
	
	//发表心情
	public static final String SP_MOOD_PUBLISH_MOOD = "sp_mood_publish_mood";
	
	//回复
	public static final String SP_REPLY_MOOD_ADD_REPLY = "sp_reply_mood_add_reply";
	
	//删除回复
	public static final String SP_REPLY_MOOD_DELETE_REPLY_BYID = "sp_reply_mood_delete_reply_byid";
	
	//添加心情回复提醒
	public static final String SP_MOOD_TIPS_ADD_TIPS = "sp_mood_tips_add_tips";
	
	//确认添加好友
	public static final String SP_FRIENDS_CONFIRM_FROM_TEMP = "sp_friends_confirm_from_temp";
	
	//删除心情
	public static final String SP_MOOD_DELETE_BYMID = "sp_mood_delete_bymid";
	
	//删除留言
	public static final String SP_NOTES_DELETE_BYNID = "sp_notes_delete_bynid";
	
	//删除留言回复
	public static final String SP_REPLY_NOTE_DELETE_REPLY_BYID = "sp_reply_note_delete_reply_byid";
	
	//------------------------------------------------------------
	//下面是类PersonalInfoDAL要用到的存储过程
	//--------------------------------------------------------------
	public static String SP_REGISTER = "sp_register";
	
	public static String SP_MOOD_TIPS_DELETE_BY_PID_AND_TYPE = "sp_mood_tips_delete_by_pid_and_type";
	
	public static String  SP_PERSON_INFO_MODIFY_PWD = "sp_person_info_modify_pwd";
	
	public static String SP_PERSON_INFO_FORGET_PWD = "sp_person_info_forget_pwd";
	
	//------------------------------------------------------------
	//下面是类EntryFormDAL要用到的存储过程
	//--------------------------------------------------------------
	public static final String SP_UPDATE_ENTRY_FORM = "sp_update_entry_form";
	public static final String SP_ENTRY_FORM_UPDATE_ONE = "sp_entry_form_update_one";
	
	//------------------------------------------------------------
	//下面是类PhotoInfoDAL要用到的存储过程
	//--------------------------------------------------------------
	public static final String SP_PHOTO_UPLOAD_PHOTO="sp_photo_upload_photo";
	
	//根据相册id查询该该相册内第page_num页内的照片
	public static final String SP_PHOTO_SELECTBY_AID="sp_photo_selectby_aid";
	
	// 更新头像
	public static final String SP_PERSON_INFO_UPLOAD_PHOTO_CHAR="sp_person_info_upload_photo_char";
	
	public static final String SP_PHOTO_SET_ENTRY_PHOTO="sp_photo_set_entry_photo";
	
	public static final String SP_PHOTO_MOVE_PHOTO="sp_photo_move_photo";
	
	public static final String SP_PHOTO_DELETE_PHOTO_BY_IDLIST="sp_photo_delete_photo_by_idlist";
	
}
