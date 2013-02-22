package com.topn.controller;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletInputStream;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.topn.DAL.PhotoInfoDAL;
import com.topn.bean.PersonalInfo;
import com.topn.bean.Photo;
import com.topn.bean.TO.AlbumPhotosTO;
import com.topn.bean.TO.PhotoTO;
import com.topn.collection.MyMapEntry;
import com.topn.util.LoggerUtil;
import com.topn.util.StringUtil;
import com.topn.util.Table;

/**
 * 创建人 youxishow
 * 创建时间 2011-3-31 下午07:27:31
 * 照片处理控制层
 */
public class PhotoController {
	
	private static PhotoController instance=new PhotoController();
	private static Logger logger = Logger.getLogger(PhotoController.class);
	
	private static String path="D:/program/staticfile/TopN/";
	
	public static PhotoController getInstance()
	{
		System.setProperty("jmagick.systemclassloader","no");
		return instance;
	}


	/**
	 * 上传照片
	 * 
	 * @param files
	 * @param filesFileName
	 * @param personId
	 * @param albumId
	 * @return
	 */
	public boolean uploadPhoto(List<File> files, List<String> filesFileName,
			int personId, int albumId) {
		
		boolean result=false;
		MagickImage image=null;
		ImageInfo info =null;
		Dimension dimension=null;
		
		Calendar c=Calendar.getInstance();	
		int mouth=c.get(Calendar.MONTH)+1;
		String strDate= c.get(Calendar.YEAR)+"-"+mouth;
		List<Photo> photos=new ArrayList<Photo>();
		//String folderRealPath= "E:/program/staticfile/TopN/user_picture/"+strDate+"/ordinary";
		String folderRealPath= Table.PROJECT_BASE_PATH + "user_picture/"+strDate+"/ordinary";
		String folderName = "user_picture/"+strDate+"/ordinary";
		PhotoInfoDAL pid=PhotoInfoDAL.getInstance();
		for (int i = 0; i < files.size(); i++) {
			File photo = files.get(i);
			String fileName = filesFileName.get(i);

			int position = fileName.lastIndexOf(".");
			String extension = fileName.substring(position);
			String name= fileName.substring(0,position-1);
			String newName = java.util.UUID.randomUUID().toString()
					+ extension;
			newName=newName.toLowerCase();
			File f = new File(folderRealPath, newName);
			File smallf=new File(folderRealPath+"/small_150_115",newName);
			try {
				FileUtils.copyFile(photo, f);
				FileUtils.copyFile(photo, smallf);
				
				info=new ImageInfo(folderRealPath+"/small_150_115/"+newName);
				image=new MagickImage(info);
				dimension = image.getDimension();
				if(dimension.width>150&&dimension.height>115)
				{
					if(dimension.width>dimension.height)
					{
						 image=image.scaleImage(150, 115);
					}
					else if (dimension.width<dimension.height||dimension.width==dimension.height)
					{
						double scale=(115.0/dimension.height)*dimension.width;
					 int s=(int) scale;
						image=image.scaleImage(s, 115);
					}
				}				
				image.writeImage(info);
				
				if(dimension.width>700||dimension.height>500)
				{
					info=new ImageInfo(folderRealPath+"/"+newName);
					image=new MagickImage(info);
					dimension = image.getDimension();
			
					if(dimension.width>dimension.height)
					{
						if(dimension.width>700&&dimension.height>500||dimension.width>700)
						{
							double scale=(700.0/dimension.width)*dimension.height;
							image=image.scaleImage(700, (int) scale);
						}
					}
					else 
					{		
						double scale=(500.0/dimension.height)*dimension.width;		
						image=image.scaleImage((int) scale, 500);				
					}
					
					image.writeImage(info);
				}
						
				Photo p=new Photo();
				p.setUrl(folderName+"/"+newName);
				p.setAlbumId(albumId);
				p.setDetail(name);
				photos.add(p);
				
			} catch (IOException e) {
				pid.upload(photos,personId,albumId);
				LoggerUtil.loggerDebug(logger,
				"上传图片遇到错误");
				e.printStackTrace();	
			} catch (MagickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally 
			{
				if(image!=null)
				image.destroyImages();
			}
		}
		result=pid.upload(photos,personId,albumId);

		return result;
	}
	
	public AlbumPhotosTO loadAlbumPhotos(int personalId, int albumId, int pageNum,int isEntry,String pageName)
	{
		int number=0;
		if(pageName.equals("pic_manage"))
		number=9;
		else if (pageName.equals("album"))
			number=20;
		
		PhotoInfoDAL pid=PhotoInfoDAL.getInstance();
		
		AlbumPhotosTO ap=pid.loadAlbumPhotos(personalId, albumId, pageNum, isEntry,number);
		
		return ap;
	}
	
	/**
	 * 上传要裁剪的头像照片
	 * @return
	 */
	public String uploadTempPhoto(File photo,String photoFileName)
	{
		String imgInfo="";
		if(photo!=null)
		{	
			int position = photoFileName.lastIndexOf(".");
		    String extension = photoFileName.substring(position);
			
			String newName=java.util.UUID.randomUUID().toString()+extension;
			newName=newName.toLowerCase();
			File f=new File(Table.COM_TEMP_PATH,newName);
			try {
				FileUtils.copyFile(photo, f);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String imgUrl=newName;
			int imgWidth=0;
			int imgHeight=0;
			MagickImage image=null;
			try {
				ImageInfo info=new ImageInfo(Table.COM_TEMP_PATH+"/"+imgUrl);
				image=new MagickImage(info);
				Dimension dimension=image.getDimension();
				imgWidth=dimension.width;
				imgHeight=dimension.height;
			} catch (MagickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally
			{
				if(image!=null)
				image.destroyImages();
			}
		
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("imgUrl", imgUrl);	
			jsonObject.put("imgWidth", imgWidth);	
			jsonObject.put("imgHeight", imgHeight);	
			
			imgInfo=jsonObject.toString();
		}
		return imgInfo;
	}
	
	/**
	 * 剪裁头像照片
	 * @return
	 */
	public String cutHeadPortrait(int personId,int x,int y,int zoomWidth,int zoomHeight,String imgUrl)
	{
		 ImageInfo info = null;  
		  // 源图片
		  MagickImage image = null; 
		  // 正常尺寸头像
		  MagickImage imageHead = null;
		  // 小尺寸头像
		  MagickImage imageSmall = null;  
		  // 截取正方形
		  Rectangle rect = null;
		  // 尺寸
		  Dimension dimension = null;
		  
		  String path=Table.COM_TEMP_PATH+"/"+imgUrl;
		  String url=Table.USER_PHOTO_PATH+imgUrl;
		  String urlSmall=Table.USER_PHOTO_PATH_50_50+imgUrl;
		  String photoChar="";
		  String result="";
		  
		  boolean writeSuccess=false;
		  try {
			info=new ImageInfo(path);
			image=new MagickImage(info);
			dimension=image.getDimension();  
			rect = new Rectangle(x, y, 150, 180);  
			
			image=image.scaleImage(zoomWidth, zoomHeight);
				
			image=image.cropImage(rect);		
		
			imageHead=image;

			imageHead.setFileName(url);
			writeSuccess=imageHead.writeImage(info); 
			if(writeSuccess==false)
			{
				return result;
			}			
			imageSmall=image.scaleImage(50, 50);
			imageSmall.setFileName(urlSmall);
			writeSuccess=imageSmall.writeImage(info);     
			if(writeSuccess==false)
			{
				File f=new File(url);
				f.delete();
				return result;
			}
			
			PhotoInfoDAL pid=PhotoInfoDAL.getInstance();
			String oldUrl=pid.updatePersonHeadPortrait(personId, "user_picture/user/"+imgUrl);
			
			File temp=new File(path);
			
			if(oldUrl=="")
			{
				return result;
			}
			
			if(temp.exists())
			{
				temp.delete();
			}
			
			if(oldUrl!="")
			{
				int position = oldUrl.lastIndexOf("/");
				String fileName = oldUrl.substring(position+1,oldUrl.length());
				
				File oldFile=new File(Table.USER_PHOTO_PATH+fileName);
				if(oldFile.exists())
				oldFile.delete();
				File oldFileSmall=new File(Table.USER_PHOTO_PATH_50_50+fileName);
				if(oldFileSmall.exists())
					oldFileSmall.delete();
			}
			photoChar="../user_picture/user/"+imgUrl;
			
			/*MemCache mc = MemCacheProvider.getInstance().buildCache(
					MemCacheProvider.MEMCACHE_TYPE_USER);
			PersonalInfo pi = (PersonalInfo) mc.get(personId);		
			pi.setPhoto(photoChar);	
			mc.put(pi.getPersonalInfoId(), pi);
			*/
			PersonalInfo pi = PersonalController.getInstance().getPersonById(personId, -1, true);
			pi.setPhoto(photoChar);	
			PersonalController.getInstance().updateCache(pi);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("newPhotoChar", photoChar);
			result=jsonObject.toString();
			
		} catch (MagickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			if(image!=null)
				image.destroyImages();
			if(imageHead!=null)
			imageHead.destroyImages();
			if(imageSmall!=null)
			imageSmall.destroyImages();
		}
		  
		return result;
	}
	
	public PhotoTO loadCompetitionPictuer(String photoUrl)
	{
		ImageInfo info = null;  
		MagickImage image = null; 
		Dimension dimension = null;
		PhotoTO pto = null;
		try {
			info=new ImageInfo(path+photoUrl);
			image=new MagickImage(info);
			dimension=image.getDimension(); 
			pto=new PhotoTO();
		
			pto.setUrl(photoUrl);
			pto.setWidth(dimension.width);
			pto.setHeight(dimension.height);
		
			
		} catch (MagickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(image!=null)
			image.destroyImages();
		}
		return pto;
	}
	
	/**
	 * @deprecated
	 * @param personId
	 * @param imgUrl
	 * @param x
	 * @param y
	 * @param zoomWidth
	 * @param zoomHeight
	 * @param isLocal
	 * @return
	 */
	public String cutCompetitionPictuer(int personId,String imgUrl,int x,int y,int zoomWidth,int zoomHeight,int isLocal)
	{
		 ImageInfo info = null;  
		 MagickImage image = null; 
		  Rectangle rect = null;
		 MagickImage imageCompetition = null;
		 MagickImage imageSamllCompetition = null;
		 boolean writeSuccess=false;
		 String result="";
		 String url="";
	
			try {
				if(isLocal==1)
				{				
					url=path+"/"+imgUrl;
				}
				else
				{
					url=Table.COM_TEMP_PATH+"/"+imgUrl;
				}	
				info=new ImageInfo(url);
				image=new MagickImage(info);
				rect = new Rectangle(x, y, 380, 450);  
				image=image.scaleImage(zoomWidth, zoomHeight);
				imageCompetition=image.cropImage(rect);	
			
				Calendar c=Calendar.getInstance();	
				int mouth=c.get(Calendar.MONTH)+1;
				String strDate= c.get(Calendar.YEAR)+"-"+mouth;
				//String savePath= "E:/program/staticfile/TopN/user_picture/"+strDate+"/competition/";
				String savePath= Table.PROJECT_BASE_PATH + "user_picture/"+strDate+"/competition/";
				
				if(isLocal==1)
				{
					imgUrl=imgUrl.substring(imgUrl.lastIndexOf('/')+1,imgUrl.length());
				}	
				String sqlPath="user_picture/"+strDate+"/competition/"+imgUrl;
				imageCompetition.setFileName(savePath+imgUrl);
				writeSuccess=imageCompetition.writeImage(info);  
				if(writeSuccess==false)
				{
					return result;
				}				
				imageSamllCompetition=imageCompetition.scaleImage(187,211);						
				imageSamllCompetition.setFileName(Table.PROJECT_BASE_PATH + "user_picture/"+strDate+"/competition/small_187_211/"+imgUrl);
				writeSuccess=imageSamllCompetition.writeImage(info);  
				if(writeSuccess==false)
				{
					File f=new File(savePath+imgUrl);
					if(f.exists())
						f.delete();
					return result;
				}
				writeSuccess=imageCompetition.writeImage(info);  
				
				/*PhotoInfoDAL pid=PhotoInfoDAL.getInstance();
				int b=pid.setCompetitionPictuer(personId,sqlPath);
				if(b==1)
				{
					File f=new File(savePath+imgUrl);
					if(f.exists())
						f.delete();
					File fSamll=new File(Table.PROJECT_BASE_PATH + "user_picture/"+strDate+"/competition/small_187_211/"+imgUrl);
					if(fSamll.exists())
						fSamll.delete();
					return result;
				}*/
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("imgUrl", sqlPath);
				result=jsonObject.toString();
				
			} catch (MagickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				if(image!=null)
				image.destroyImages();
				if(imageCompetition!=null)
				imageCompetition.destroyImages();			
			}
			
		return result;
	}
	
	public String movePhotos(int personId,String ids,int albumId,int moveAlbumId)
	{
		PhotoInfoDAL pid=PhotoInfoDAL.getInstance();
		int resultTotal=pid.movePhotos(personId,ids, albumId, moveAlbumId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultTotal", resultTotal);
		String result=jsonObject.toString();
		return result;
	}
	
	public String deletePhotos(int personId,String ids,int albumId)
	{
		PhotoInfoDAL pid=PhotoInfoDAL.getInstance();
		List<Object> result=pid.deletePhotos(personId, ids, albumId);
		
		List<String> urls=(List<String>) result.get(0);
		for(String url:urls)
		{
			File f=new File(path+url);
			if(f.exists())
				f.delete();
			url=url.replace("ordinary", "ordinary/small_150_115");
			File f1=new File(path+url);
			if(f1.exists())
				f1.delete();
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("resultTotal", result.get(1));
		String resultJson=jsonObject.toString();
		
		return resultJson;
	}
	
	/**
	 * 剪裁个人头像
	 * @param personId
	 * @param is
	 * @return
	 */
	public String cutHeadportrait(int personId, InputStream is)
	{
		String result="";
		int i;
		
		ImageInfo info = null;  
		MagickImage image = null; 
		MagickImage imageSmall = null;  
		boolean writeSuccess=false;
		try {
			BufferedInputStream inputStream = new BufferedInputStream(is);
			String fileName=personId+".jpg";
			File file=new File(Table.USER_PHOTO_PATH+fileName);
			if(file.exists())
			{
				file.delete();
			}
			boolean b=file.createNewFile();
			if(b==false)
			{
				return result;
			}
			FileOutputStream outputStream = new FileOutputStream(file);
			byte [] bytes = new byte[1024];    
			while((i=inputStream.read(bytes))>0){    
			    outputStream.write(bytes,0,i);    
			}    
			outputStream.close();    
			inputStream.close(); 
			
			info=new ImageInfo(Table.USER_PHOTO_PATH+fileName);
			image=new MagickImage(info);
			image=image.scaleImage(Table.USER_PHOTO_W, Table.USER_PHOTO_H);
			image.setFileName(Table.USER_PHOTO_PATH+fileName);

			writeSuccess=image.writeImage(info); 
			if(writeSuccess==false)
			{
				return result;
			}			
			Rectangle rect = new Rectangle (0, 0, Table.USER_PHOTO_W, Table.USER_PHOTO_W);
			imageSmall=image.cropImage(rect);//先剪裁
			imageSmall=imageSmall.scaleImage(Table.USER_PHOTO_SMALL_SIZE, Table.USER_PHOTO_SMALL_SIZE);//再压缩
			imageSmall.setFileName(Table.USER_PHOTO_PATH_50_50+fileName);
			writeSuccess=imageSmall.writeImage(info);     
			if(writeSuccess==false)
			{
				File f=new File(Table.USER_PHOTO_PATH+fileName);
				f.delete();
				return result;
			}
			PhotoInfoDAL pid=PhotoInfoDAL.getInstance();
			String oldUrl=pid.updatePersonHeadPortrait(personId, "user_picture/user/"+fileName);

			String photoChar="../user_picture/user/"+fileName;
			PersonalInfo pi = PersonalController.getInstance().getPersonById(personId, -1, true);
			pi.setPhoto(photoChar);	
			PersonalController.getInstance().updateCache(pi);
		
			result=photoChar;
			
			
		} catch (IOException e) {		
			e.printStackTrace();
		} catch (MagickException e) {
			e.printStackTrace();
		} finally
		{
			if(image!=null)
				image.destroyImages();
			if(imageSmall!=null)
			imageSmall.destroyImages();
		}
		return result;
		
	}

	/**
	 * 参赛照片flex剪裁  徐涛
	 * 
	 * 修改了存储过程，防止重复设置参赛照片而使得以前的参赛照片没有被删掉，这样占用硬盘
	 * @param personId
	 * @param is
	 * @return
	 */
	public String cutCompetition(int personId,ServletInputStream is) {
		String result="";
		int i;
		
		ImageInfo info = null;  
		MagickImage image = null; 
		MagickImage imageSmall = null;  
		boolean writeSuccess=false;
		try {
			BufferedInputStream inputStream = new BufferedInputStream(is);
			String fileName=java.util.UUID.randomUUID().toString()+".jpg";
			Calendar c=Calendar.getInstance();	
			int mouth=c.get(Calendar.MONTH)+1;
			String strDate= c.get(Calendar.YEAR)+"-"+mouth;

			//设置路径
			String savePath= Table.PROJECT_BASE_PATH + "user_picture/"+strDate+"/competition/";
			File file=new File(savePath+fileName);
			
			FileOutputStream outputStream = new FileOutputStream(file);
			byte [] bytes = new byte[1024];    
			while((i=inputStream.read(bytes))>0){    
			    outputStream.write(bytes,0,i);    
			}    
			outputStream.close();    
			inputStream.close(); 
			//剪裁原照片
			info=new ImageInfo(savePath+fileName);
			image=new MagickImage(info);
			image=image.scaleImage(Table.COM_PHOTO_W, Table.COM_PHOTO_H);
			image.setFileName(savePath+fileName);

			writeSuccess=image.writeImage(info); 
			if(writeSuccess==false){
				return result;
			}			
			//Rectangle rect = new Rectangle (0, 0, Table.COM_PHOTO_W_SMALL, Table.COM_PHOTO_H_SMALL);
			//imageSmall=image.cropImage(rect);//先剪裁
			imageSmall=image.scaleImage(Table.COM_PHOTO_W_SMALL, Table.COM_PHOTO_H_SMALL);//再压缩
			imageSmall.setFileName(Table.PROJECT_BASE_PATH + "user_picture/"+strDate+"/competition/small_187_211/"+fileName);
			writeSuccess=imageSmall.writeImage(info);     
			if(writeSuccess==false)
			{
				File f=new File(savePath+fileName);
				f.delete();
				return result;
			}
			String sqlPath="user_picture/"+strDate+"/competition/"+fileName;
			PhotoInfoDAL pid=PhotoInfoDAL.getInstance();
			MyMapEntry<Integer, String> mme = pid.setCompetitionPictuer(personId,sqlPath);
			
			if(mme.getKey() == 1)
			{
				File f=new File(savePath+fileName);
				if(f.exists())
					f.delete();
				File fSamll=new File(Table.PROJECT_BASE_PATH + "user_picture/"+strDate+"/competition/small_187_211/"+fileName);
				if(fSamll.exists())
					fSamll.delete();
				return result;
			}
			if(mme.getKey() == 2 && StringUtil.isNotBlank(mme.getValue())){
				//这里说明是重新上传参赛照片
				File f=new File(Table.PROJECT_BASE_PATH+mme.getValue());
				if(f.exists())
					f.delete();
				File fSamll=new File(Table.PROJECT_BASE_PATH + mme.getValue().replace("competition", "competition/small_187_211"));
				if(fSamll.exists())
					fSamll.delete();
				return result;
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("imgUrl", sqlPath);
			result=jsonObject.toString();	
	
		} catch (IOException e) {		
			LoggerUtil.loggerDebug(logger, personId +" 剪裁参赛照片失败");
		} catch (MagickException e) {
			LoggerUtil.loggerDebug(logger, personId +" 剪裁参赛照片失败");
		} finally
		{
			if(image!=null)
				image.destroyImages();
			if(imageSmall!=null)
			imageSmall.destroyImages();
		}
		return result;
	}
}
