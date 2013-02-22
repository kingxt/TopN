package com.topn.action;

import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.topn.action.help.CookieHelp;
import com.topn.bean.City;
import com.topn.bean.District;
import com.topn.bean.HighSchool;
import com.topn.bean.PersonalInfo;
import com.topn.bean.Province;
import com.topn.bean.University;
import com.topn.bean.TO.CacheEducationTO;
import com.topn.bean.TO.PermissionsTO;
import com.topn.collection.UserInRegister;
import com.topn.controller.PersonalController;
import com.topn.util.DateUitl;
import com.topn.util.LoggerUtil;
import com.topn.util.StringUtil;

/**
 * 
 * 创建人 KingXt 创建时间：2011-4-13 下午04:41:33
 * 
 * 教育信息维护类
 */
public class EducationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(EducationAction.class);

	private int perSchool = 1;
	private String college;
	private String colLevel;
	private String highSchool;
	private String highLevel;

	// 修改教育信息的时候要用到的
	private int provinceId;
	private int cityId;
	private int districtId;

	/**
	 * 这里的策略是加载缓存中的信息，但是缓存的不是所有教育信息，都是一级栏目，也就是用户修改信息的时候开始 看到的
	 * 
	 * @return
	 */
	public String loadEducation() {
		CacheEducationTO cto = PersonalController.getInstance()
				.getCacheEducationTO();
		List<Province> pc = cto.getPc();
		List<University> us = cto.getUs();
		List<City> cs = cto.getCs();
		List<District> ds = cto.getDs();
		List<HighSchool> hs = cto.getHs();
		ActionContext.getContext().put("pc", pc);
		ActionContext.getContext().put("us", us);
		ActionContext.getContext().put("cs", cs);
		ActionContext.getContext().put("ds", ds);
		ActionContext.getContext().put("hs", hs);
		int pId = CookieHelp.getPersonalId(ServletActionContext.getRequest());
		PersonalInfo pi = PersonalController.getInstance().getPersonById(pId,
				pId, true);
		ActionContext.getContext().put("per",
				PermissionsTO.string2PermissionsTO(pi.getPermission()));
		// ActionContext.getContext().put("school",pi.getSchool());

		ActionContext.getContext().put("college", pi.getCollege());
		ActionContext.getContext().put("colLevel", pi.getColLevel());
		ActionContext.getContext().put("highSchool", pi.getHighSchool());
		ActionContext.getContext().put("highLevel", pi.getHighLevel());

		ActionContext.getContext().put("endYear", DateUitl.getThisYear());
		return SUCCESS;
	}

	/**
	 * 根据省份id加载大学
	 * 
	 * @return
	 */
	public String loadUniversityByProvinceId() {
		if (0 == provinceId) {
			LoggerUtil.loggerDebug(logger, "加载大学失败，省份的id是0");
			return SUCCESS;
		}
		List<University> us = PersonalController.getInstance()
				.getUniversitiesByProvinceId(provinceId);
		ActionContext.getContext().put("us", us);
		return SUCCESS;
	}

	/**
	 * 加载市 这里要做一个修改是，如果修改省份，所有的下级单位都必须修改
	 * 
	 * @return
	 */
	public String loadCityByProvince() {
		if (0 == provinceId) {
			LoggerUtil.loggerDebug(logger, "加载城市失败，省份的id是0");
			return SUCCESS;
		}
		List<City> cs = PersonalController.getInstance().loadCityByProvince(
				provinceId);
		ActionContext.getContext().put("cs", cs);
		return SUCCESS;
	}

	/**
	 * 加载区
	 * 
	 * @return
	 */
	public String loadDistrictByCity() {
		if (0 == cityId) {
			LoggerUtil.loggerDebug(logger, "加载区失败，市的id是0");
			return SUCCESS;
		}
		List<District> ds = PersonalController.getInstance()
				.loadDistrictByCity(cityId);
		ActionContext.getContext().put("ds", ds);
		return SUCCESS;
	}

	/**
	 * 加载高中
	 * 
	 * @return
	 */
	public String loadHighSchoolByDistrict() {
		if (0 == districtId) {
			LoggerUtil.loggerDebug(logger, "加载高中失败，区的id是0");
			return SUCCESS;
		}
		List<HighSchool> hs = PersonalController.getInstance()
				.loadHighSchoolByDistrict(districtId);
		ActionContext.getContext().put("hs", hs);
		return SUCCESS;
	}

	/**
	 * 修改学校
	 * 
	 * @return
	 */
	public String updateSchool() {

		PersonalInfo pi = new PersonalInfo();

		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("id")) {
				int pId = Integer.parseInt(c.getValue());
				pi.setPersonalInfoId(pId);
			} else if (c.getName().equals("username")) {
				pi.setTableName(UserInRegister.getTableName(c.getValue()));
			}
		}
		pi.setCollege(college);
		pi.setHighSchool(highSchool);
		pi.setColLevel(colLevel);
		pi.setHighLevel(highLevel);
		PersonalInfo cachePi = PersonalController.getInstance().getPersonById(
				pi.getPersonalInfoId(), pi.getPersonalInfoId(), true);
		PermissionsTO pto = PermissionsTO.string2PermissionsTO(cachePi
				.getPermission());
		pto.setPerSchool(perSchool);
		String per = PermissionsTO.permission2String(pto);
		pi.setPermission(per);
		pi.setSex(cachePi.getSex());

		cachePi.setCollege(college);
		cachePi.setHighSchool(highSchool);
		cachePi.setColLevel(colLevel);
		cachePi.setHighLevel(highLevel);

		cachePi.setPermission(per);
		// 更新缓存
		PersonalController.getInstance().updateCache(cachePi);
		PersonalController.getInstance().updatePersonalInfo(pi);

		return null;
	}
	
	/**
	 * 搜索大学
	 * @return
	 */
	public String searchUniversities(){
		if(StringUtil.isBlank(college)){
			return SUCCESS;
		}
		List<University> us = PersonalController.getInstance().searchUniversities(college);
		ActionContext.getContext().put("us", us);
		return SUCCESS;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public void setPerSchool(int perSchool) {
		this.perSchool = perSchool;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public void setColLevel(String colLevel) {
		this.colLevel = colLevel;
	}

	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}

	public void setHighLevel(String highLevel) {
		this.highLevel = highLevel;
	}

}
