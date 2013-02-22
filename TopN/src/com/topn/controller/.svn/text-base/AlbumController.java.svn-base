package com.topn.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import net.sf.json.JSONObject;

import com.topn.DAL.AlbumInfoDAL;
import com.topn.bean.Album;
import com.topn.bean.TO.AlbumsNameListTO;
import com.topn.util.Table;

/**
 * 创建人 youxishow
 * 创建时间 2011-3-31 下午07:29:37
 * 相册控制层
 */
public class AlbumController {

	private static AlbumController instance=new AlbumController();
	
	private static AlbumInfoDAL aid = AlbumInfoDAL.getInstance();
	
	public static AlbumController getInstance()
	{
		return instance;
	}
	
	/**
	 * 获取用户相册
	 * 
	 * @param personalId
	 * @return
	 */
	public List<Album> loadPersonalAlbum(int personalId) {
		
		aid = AlbumInfoDAL.getInstance();

		List<Album> albums = aid.loadPersonalAlbum(personalId);

		return albums;
	}
	
	/**
	 * 加载上传图片页面时获取用户所有相册名
	 * 
	 * @param personId
	 * @return
	 */
	public AlbumsNameListTO loadUploadPhoto(int personId) {
		AlbumInfoDAL aid = AlbumInfoDAL.getInstance();
		List<Album> albums = aid.getAlbumsNameList(personId);
		AlbumsNameListTO anl = new AlbumsNameListTO();
		anl.setPersonId(personId);
		if (albums != null && albums.size() > 0) {
			anl.setAlbums(albums);
		}

		return anl;
	}
	
	public Album createAlbum(int personId,String albumName,String detail)
	{
		Album a=new Album();
		a.setCover(Table.ALBUM_COVER_PATH);
		a.setName(albumName);
		a.setDetail(detail);
		a.setPersonalInfoId(personId);
		a.setIsEntry(1);
		a.setNeedPwd(1);
		int id=aid.createAlbum(a);
		if(id==0)
			return null;
		
		Album newAlbum=new Album();
		
		newAlbum.setAlbumId(id);
		newAlbum.setName(albumName);
		newAlbum.setDetail(detail);
		
		return newAlbum;
	}
	
	public String updateAlbum(int albumId,int personId,String albumName,String detail)
	{
		boolean result=aid.updateAlbum(albumId,personId, albumName, detail);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		String resultJson=jsonObject.toString();
		
		return resultJson;		
	}
	
	public String deleteAlbum(int personId,int albumId)
	{
		List<String> urls =aid.deleteAlbum(personId,albumId);
		if(urls==null)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", false);
			String resultJson=jsonObject.toString();
			
			return resultJson;			
		}
		if(urls.size()>0)
		{
			for(String url:urls)
			{
				File f=new File(Table.PROJECT_BASE_PATH+url);
				if(f.exists())
				f.delete();
			}		
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", true);
		String resultJson=jsonObject.toString();
		return resultJson;			
	}
	
	public String updateAlbumCover(int personId,int albumId,String cover,String oldCover)
	{
		boolean result=true;
		String resultJson="";
		String message="";
		String url="";
		
			File file=new File(Table.PROJECT_BASE_PATH+cover);
			if(file.exists())
			{
				if(!oldCover.startsWith("pictures"))
				{ 
					
				File oldFile=new File(Table.PROJECT_BASE_PATH+oldCover);
				if(oldFile.isFile())
					oldFile.delete();
				}
			
				int position =cover.lastIndexOf(".");
		
				String newPath=cover.substring(0,position-36)+java.util.UUID.randomUUID().toString()+'.'+cover.substring(position+1);
				
				File f = new File(Table.PROJECT_BASE_PATH+newPath);
				try {
					FileUtils.copyFile(file, f);
					result=aid.updateAlbumCover(personId,albumId,newPath);	
					if(result==true)
					{
						message="更新相册封面成功!";
						url=newPath;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else
			{
				result=false;
				message="图片不存在";
			}
		
		
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		jsonObject.put("message", message);
		jsonObject.put("url", url);
		resultJson=jsonObject.toString();
		
		return resultJson;		
	}
}
