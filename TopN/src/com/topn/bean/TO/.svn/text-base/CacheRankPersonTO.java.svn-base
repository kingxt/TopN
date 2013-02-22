package com.topn.bean.TO;

import java.io.Serializable;
import java.util.List;

import com.topn.bean.PersonalInfo;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-23 下午03:18:04
 * 
 * 此类用来缓存排行榜的人，必须实现序列化接口
 */
public class CacheRankPersonTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PersonalInfo pi;
		
	
	
	//------------------------基本信息配置结束
	
	//参赛照片
	private List<CompetitionPhotoTO>  competitionAlbum;
	
	//普通相册
	private List<OrdinaryAlbumTO> ordinaryAlbum;
	
	//基本信息的权限
	private PermissionsTO per;

	public List<CompetitionPhotoTO> getCompetitionAlbum() {
		return competitionAlbum;
	}

	public void setCompetitionAlbum(List<CompetitionPhotoTO> competitionAlbum) {
		this.competitionAlbum = competitionAlbum;
	}

	public List<OrdinaryAlbumTO> getOrdinaryAlbum() {
		return ordinaryAlbum;
	}

	public void setOrdinaryAlbum(List<OrdinaryAlbumTO> ordinaryAlbum) {
		this.ordinaryAlbum = ordinaryAlbum;
	}

	public PersonalInfo getPi() {
		return pi;
	}

	public void setPi(PersonalInfo pi) {
		this.pi = pi;
	}

	public PermissionsTO getPer() {
		return per;
	}

	public void setPer(PermissionsTO per) {
		this.per = per;
	}

	
}
