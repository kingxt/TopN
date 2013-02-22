package com.topn.action;

import java.util.List;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.action.help.CookieHelp;
import com.topn.bean.Album;
import com.topn.bean.TO.AlbumsNameListTO;
import com.topn.bean.TO.RecentVisitorTO;
import com.topn.bean.TO.TipsCountTO;
import com.topn.controller.AlbumController;
import com.topn.controller.FriendController;
import com.topn.controller.PersonalController;
import com.topn.util.StringUtil;

/**
 * 创建人 youxishow
 * 创建时间 2011-3-31 下午04:34:17
 * 接收相册处理请求
 */
public class AlbumAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 相册ID
	private int albumId;
	// 相册名字
	private String albumName;
	// 相册备注
	private String detail;

	private String newAlbum;
	
	private String cover;
	private String oldCover;
	private String result;

	private int personalInfoId;
	
	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getNewAlbum() {
		return newAlbum;
	}

	public void setNewAlbum(String newAlbum) {
		this.newAlbum = newAlbum;
	}
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public String getOldCover() {
		return oldCover;
	}

	public void setOldCover(String oldCover) {
		this.oldCover = oldCover;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getPersonalInfoId() {
		return personalInfoId;
	}

	public void setPersonalInfoId(int personalInfoId) {
		this.personalInfoId = personalInfoId;
	}

	/**
	 * 获取用户的所有相册
	 * 
	 * @return
	 */
	public String loadPersonalAlbum() {
		List<Album> albums;
		//获取最近访客
		List<RecentVisitorTO> rvt;
		boolean isGuest;
		AlbumController ac = AlbumController.getInstance();
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		
		if(personalInfoId == 0){
			personalInfoId = pId;
		}
		
		albums = ac.loadPersonalAlbum(personalInfoId);
		rvt= FriendController.getInstance().getRecentVisitorTO(personalInfoId);
			
		if(pId!=personalInfoId)
			 isGuest=true;
		else
			isGuest=false;
		if(!isGuest){
			TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(personalInfoId);
			ActionContext.getContext().put("tct", tct);
		}
		ActionContext.getContext().put("albums", albums);
		ActionContext.getContext().put("isGuest", isGuest);
		ActionContext.getContext().put("rvt", rvt);
		return SUCCESS;
	}

	/**
	 * 加载上传照片页面
	 * 
	 * @return
	 */
	public String loadUploadPhoto() {
		int pid = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if (0 == pid) {
			return LOGIN;
		}
		AlbumController ac = AlbumController.getInstance();
		AlbumsNameListTO anl = ac.loadUploadPhoto(pid);
		ActionContext.getContext().put("albumsNameList", anl);
		TipsCountTO tct = PersonalController.getInstance().getTipsCountTO(pid);
		//获取最近访客
		List<RecentVisitorTO> rvt = FriendController.getInstance().getRecentVisitorTO(pid);
		ActionContext.getContext().put("rvt", rvt);
		ActionContext.getContext().put("tct", tct);
		return SUCCESS;
	}

	public String createAlbum() {

		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if (0 == pId) {
			return LOGIN;
		}
		AlbumController ac = AlbumController.getInstance();
		Album a = ac.createAlbum(pId, albumName, detail);
		if (a == null)
			return ERROR;
		JSONObject jo = JSONObject.fromObject(a);
		newAlbum = jo.toString();
		
		return SUCCESS;
	}
	
	public String updateAlbum()
	{
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if (0 == pId) {
			return LOGIN;
		}
		AlbumController ac = AlbumController.getInstance();
		result=ac.updateAlbum(albumId,pId,albumName,detail);
		
		return SUCCESS;
	}
	
	public String deleteAlbum()
	{
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if (0 == pId) {
			return LOGIN;
		}
		
		AlbumController ac = AlbumController.getInstance();
		result=ac.deleteAlbum(pId, albumId);
		
		return SUCCESS;
	}
	
	/**
	 * 更新相册封面
	 * @return
	 */
	public String updateAlbumCover(){
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		if(0 == albumId || StringUtil.isBlank(cover)){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			jsonObject.put("message", "修改失败");
			result = jsonObject.toString();
			return SUCCESS;
		}
		//由于360问题,图片都采用觉得路径了，这里要做一个统一处理
		cover = StringUtil.dealAbsolutePath2Relative(cover);
		AlbumController ac = AlbumController.getInstance();
		result=ac.updateAlbumCover(pId,albumId,cover,oldCover);
		
		return SUCCESS;
	}
}
