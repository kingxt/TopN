package com.topn.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.action.help.CookieHelp;
import com.topn.bean.PersonalInfo;
import com.topn.bean.TO.AlbumPhotosTO;
import com.topn.controller.PersonalController;
import com.topn.controller.PhotoController;
import com.topn.util.StringUtil;

/**
 * 创建人 youxishow
 * 创建时间 2011-3-31 下午04:35:18
 * 照片处理请求
 */
public class PhotoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int personalInfoId;
	// 上传照片文件
	private List<File> files;
	// 上传照片文件名
	private List<String> filesFileName;

	// 相册ID
	private int albumId;

	private int personalId;
	
	private int pageNum;
	
	private int isEntry;
	
	private String pageName;
	
	
	private File photo;  
	private String photoFileName; 
	private String photoContentType ;
    private String imgUrl;
    private String imgInfo;
    
    //private int x;
    //private int y;
    private int width;
    private int height;
    //private int zoomWidth;
    //private int zoomHeight;
    private String newPhotoChar;

    private int photoId;
    private String photoUrl;
    //private int isLocal;
    
    private String ids;
    private int moveAlbumId;
    
    private String resultTotal;
    private String result;
	/**
	 * 根据页数返回相册里的图片
	 * 
	 * @param personalId
	 * @param albumId
	 * @param pageNum
	 * @return
	 */
	public String loadAlbumPhotos() {
		
		AlbumPhotosTO ap;
		
		PhotoController pc = PhotoController.getInstance();
		
		ap=pc.loadAlbumPhotos(personalId,albumId,pageNum,isEntry,pageName);
			
		ActionContext.getContext().put("albumPhotos", ap);
		
		return pageName;
	}

	/**
	 * 上传照片
	 * 
	 * @return
	 */
	public String uploadPhoto() {
		PhotoController pc = PhotoController.getInstance();
		pc.uploadPhoto(files, filesFileName, personalInfoId,
				albumId);
		return SUCCESS;

	}
	
	/**
	 * 加载上传头像页面
	 * @return
	 */
	public String loadUploadHeadPortrait()
	{
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		/*MemCache mc = MemCacheProvider.getInstance().buildCache(
				MemCacheProvider.MEMCACHE_TYPE_USER);*/
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pId, -1, true);
		ActionContext.getContext().put("photo", pi.getPhoto());
		
		return SUCCESS;
	}
	
	/**
	 * 上传要裁剪的头像照片
	 * @return
	 */
	public String uploadTempPhoto()
	{
	
		PhotoController pc = PhotoController.getInstance();
		
		imgInfo=pc.uploadTempPhoto(photo, photoFileName);
		
		return SUCCESS;
	}
	
	/**
	 * 剪裁头像照片
	 * @return
	 */
	/*public String cutHeadPortrait()
	{
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		PhotoController pc = PhotoController.getInstance();
		newPhotoChar=pc.cutHeadPortrait(pId,x,y,zoomWidth,zoomHeight,imgUrl);	
		
		
		return SUCCESS;
	}*/
	
	
	public String loadCompetitionPictuer()
	{
		//PhotoController pc = PhotoController.getInstance();
		photoUrl = StringUtil.dealAbsolutePath2Relative(photoUrl);
		//PhotoTO pto= pc.loadCompetitionPictuer(photoUrl);
		
		//ActionContext.getContext().put("photo", pto);
		//ActionContext.getContext().put("isLocal", 1);
		
		ActionContext.getContext().put("photoUrl", photoUrl);
		return SUCCESS;
	}
	
	/*public String cutCompetitionPictuer()
	{
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		PhotoController pc = PhotoController.getInstance();
		imgUrl=pc.cutCompetitionPictuer(pId,imgUrl,x,y,zoomWidth,zoomHeight,isLocal);
		return SUCCESS;
	}*/
	
	/**
	 * 移動照片
	 * @return
	 */
	public String movePhotos(){
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		PhotoController pc = PhotoController.getInstance();
		if(StringUtil.equals("()", ids)){
			return SUCCESS;
		}
		resultTotal=pc.movePhotos(pId,ids, albumId,moveAlbumId);
		
		return SUCCESS;
	}
	
	public String deletePhotos()
	{
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		PhotoController pc = PhotoController.getInstance();
		resultTotal=pc.deletePhotos(pId,ids, albumId);
		
		return SUCCESS;
	}
	
	/**
	 * flex 剪裁头像
	 * @return
	 */
	public String cutHeadportrait(){
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		PhotoController pc = PhotoController.getInstance();
		
		try {
			result=pc.cutHeadportrait(pId, ServletActionContext.getRequest().getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * flex 剪裁参赛照片
	 * @return
	 */
	public String cutCompetition(){
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		PhotoController pc = PhotoController.getInstance();		
		try {
			result=pc.cutCompetition(pId, ServletActionContext.getRequest().getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public int getPersonalInfoId() {
		return personalInfoId;
	}

	public void setPersonalInfoId(int personalInfoId) {
		this.personalInfoId = personalInfoId;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<String> getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getPersonalId() {
		return personalId;
	}

	public void setPersonalId(int personalId) {
		this.personalId = personalId;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getIsEntry() {
		return isEntry;
	}

	public void setIsEntry(int isEntry) {
		this.isEntry = isEntry;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getImgInfo() {
		return imgInfo;
	}

	public void setImgInfo(String imgInfo) {
		this.imgInfo = imgInfo;
	}

	public String getNewPhotoChar() {
		return newPhotoChar;
	}

	public void setNewPhotoChar(String newPhotoChar) {
		this.newPhotoChar = newPhotoChar;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getMoveAlbumId() {
		return moveAlbumId;
	}

	public void setMoveAlbumId(int moveAlbumId) {
		this.moveAlbumId = moveAlbumId;
	}

	public String getResultTotal() {
		return resultTotal;
	}

	public void setResultTotal(String resultTotal) {
		this.resultTotal = resultTotal;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
