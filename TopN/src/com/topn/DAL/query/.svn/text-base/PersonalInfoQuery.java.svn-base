package com.topn.DAL.query;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import com.topn.bean.City;
import com.topn.bean.District;
import com.topn.bean.HighSchool;
import com.topn.bean.PersonalInfo;
import com.topn.bean.Province;
import com.topn.bean.University;
import com.topn.bean.TO.CompetitionPhotoTO;
import com.topn.bean.TO.FriendAddTipsTO;
import com.topn.bean.TO.FriendRecentMoodTO;
import com.topn.bean.TO.LeaveMsgTipsTO;
import com.topn.bean.TO.LoginTO;
import com.topn.bean.TO.MoodTipsTO;
import com.topn.bean.TO.OrdinaryAlbumTO;
import com.topn.bean.TO.SystemTipsTO;
import com.topn.bean.TO.TipsCountTO;
import com.topn.cache.UserCache;
import com.topn.util.Criteria;
import com.topn.util.DBReturnTemplate;
import com.topn.util.DBTemplate;
import com.topn.util.DateUitl;
import com.topn.util.FileUtil;
import com.topn.util.ProcedureExecutor;
import com.topn.util.ProcedureExecutorReturn;
import com.topn.util.Restrictions;
import com.topn.util.SqlExecuteUtil;
import com.topn.util.StringUtil;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-7 下午05:26:41
 * 
 * 个人信息查询
 */
public class PersonalInfoQuery extends BaseQueryDAL {

	private static PersonalInfoQuery piq = new PersonalInfoQuery();

	public PersonalInfoQuery() {
	}

	public static PersonalInfoQuery getInstance() {
		return piq;
	}

	/**
	 * 根据用户名查询
	 * 登录进入topn
	 * @param username
	 */
	public LoginTO queryWithUsername(final String tableName,
			final String username, final String password) {
		/*
		 * ？参数列表【是否成功的登录; 表名称， 用户名，密码; 照片是否审核成功， 总次数， 总分数， 姓名， 评论照片url， 用户id】
		 */
		String procedure = "{call " + SP_LOGIN
				+ "(?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?)}";
		final LoginTO lto = new LoginTO();
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{tableName, username, password}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.registerOutParameter(4, Types.TINYINT);
				stmt.registerOutParameter(5, Types.VARCHAR);
				stmt.registerOutParameter(6, Types.VARCHAR);
				stmt.registerOutParameter(7, Types.INTEGER);
				stmt.registerOutParameter(8, Types.FLOAT);
				stmt.registerOutParameter(9, Types.TINYINT);
				stmt.registerOutParameter(10, Types.INTEGER);
				stmt.registerOutParameter(11, Types.INTEGER);
				stmt.registerOutParameter(12, Types.INTEGER);
				stmt.execute();
				final int id = stmt.getInt(11);
				final int flag = stmt.getInt(4);
				if (flag != 1) {
					lto.setName(stmt.getString(5));

					lto.setImageURL(stmt.getString(6));

					lto.setTimes(stmt.getInt(7));

					lto.setTotalScore(stmt.getFloat(8));
					lto.setPersonalInfoId(id);
					
					int isChecked = stmt.getInt(9);
					switch (isChecked) {
					case 1:
						lto.setAttention(LoginTO.CHECK_ING);
						break;
					case 2:
						lto.setAttention(LoginTO.CHECK_FAIL);
						break;
					}
					lto.setSex(stmt.getInt(10));
					UserCache.getInstance().add(id, tableName);

				}
			}
		});
		if(StringUtil.isBlank(lto.getImageURL())){
			lto.setImageURL(DEFAULT_COMP_PHOTO);//设置为参赛照片
		}
		return lto;
	}

	/**
	 * 根据id查询用户
	 * 
	 * @param id
	 * @return
	 */
	public PersonalInfo getPersonById(final int id, final int myId) {
		final PersonalInfo pi = new PersonalInfo();
		String procedure = "{call " + SP_PERSON_INFO_SELECTBY_ONLYID + "(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{id, myId}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					pi.setSex(rs.getInt(1));
					pi.setNickName(rs.getString(2));
					pi.setJob(rs.getString(3));
					pi.setPs(rs.getString(4));
					pi.setPhoto(rs.getString(5));
					pi.setBirthday(DateUitl.dateTOString(rs.getDate(6)));
					pi.setPhone(rs.getString(7));
					pi.setAddress(rs.getString(8));
					pi.setMsn(rs.getString(9));
					pi.setQq(rs.getString(10));
					pi.setCollege(rs.getString(11));
					pi.setHobby(rs.getString(12));
					pi.setOtherHobby(rs.getString(13));
					pi.setPermission(rs.getString(14));
					pi.setColLevel(rs.getString(15));
					pi.setHighSchool(rs.getString(16));
					pi.setHighLevel(rs.getString(17));
					pi.setRegisterTime(rs.getDate(18));//获取注册时间
					pi.setPersonalInfoId(id);
					
					if(StringUtil.isBlank(pi.getPhoto())){
						pi.setPhoto(PARENT_PATH + DEFAULT_PERSON_PHOTO);
					}else{
						pi.setPhoto(PARENT_PATH + pi.getPhoto());
					}
				}else{
					//说明没有找到次用户
					pi.setPersonalInfoId(-1);
				}
			}
		});
		return pi;
	}

	/**
	 * 加载某个人所有参赛照片
	 * 
	 * @param personalId
	 * @return
	 */
	public List<CompetitionPhotoTO> loadAllCompetitionPhoto(final int personalId) {

		String procedure = "{call " + SP_PENTRY_PHOTO_SELECTBY_PID + "(?)}";
		
		final List<CompetitionPhotoTO> eps = new ArrayList<CompetitionPhotoTO>();
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{personalId}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					CompetitionPhotoTO e = new CompetitionPhotoTO();
					e.setUrl(rs.getString(1));
					e.setDetail(rs.getString(2));
					e.setScore(rs.getFloat(3));
					eps.add(e);
				}
			}
		});
		return eps;
	}

	/**
	 * 返回用户的所有相册信息
	 * 
	 * @param personalId
	 * @return
	 */
	public List<OrdinaryAlbumTO> getAllAlbums(final int personalId) {
	
		String procedure = "{call " + SP_ALBUM_GETID_AND_COVER + "(?)}";
		final List<OrdinaryAlbumTO> ats = new ArrayList<OrdinaryAlbumTO>();
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{personalId}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					OrdinaryAlbumTO e = new OrdinaryAlbumTO();
					e.setAlbumId(rs.getInt(1));
					e.setCoverURL(rs.getString(2));
					ats.add(e);
				}
			}
		});
		
		return ats;
	}

	public List<Province> getAllProvince() {
		final List<Province> ps = new ArrayList<Province>();
		final Criteria cri = new Criteria("province", new String[] { "prov_id",
				"name" });
		SqlExecuteUtil.executeSql(cri.toSqlString(), new DBTemplate() {
			
			@Override
			public void execute(Statement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery(cri.toSqlString());
				while (rs.next()) {
					Province p = new Province();
					p.setProvinceId(rs.getInt(1));
					p.setName(rs.getString(2));
					ps.add(p);
				}
			}
		});
		return ps;
	}

	/**
	 * 从数据库中选出指定省份的大学
	 * 
	 * @param provinceId
	 * @return
	 */
	public List<University> getUniversitiesByProvinceId(int provinceId) {
		final List<University> ps = new ArrayList<University>();
		final Criteria cri = new Criteria("university", new String[] { "uni_id",
				"name" });
		cri.add(Restrictions.eq("prov_id", provinceId));
		
		SqlExecuteUtil.executeSql(cri.toSqlString(), new DBTemplate() {
			
			@Override
			public void execute(Statement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery(cri.toSqlString());
				while (rs.next()) {
					University p = new University();
					p.setUniversityId(rs.getInt(1));
					p.setName(rs.getString(2));
					ps.add(p);
				}
			}
		});
		return ps;
	}

	/**
	 * 根据省份的id获取所有的市区
	 * 
	 * @param pid
	 *            省份id
	 * @return
	 */
	public List<City> loadCityByProvince(int pid) {
		final List<City> ps = new ArrayList<City>();
		final Criteria cri = new Criteria("city", new String[] { "city_id", "name" });
		cri.add(Restrictions.eq("prov_id", pid));
		
		SqlExecuteUtil.executeSql(cri.toSqlString(), new DBTemplate() {
			
			@Override
			public void execute(Statement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery(cri.toSqlString());
				while (rs.next()) {
					City p = new City();
					p.setCityId(rs.getInt(1));
					p.setName(rs.getString(2));
					ps.add(p);
				}
			}
		});
		return ps;
	}

	/**
	 * 获取一个市中的所有区
	 * 
	 * @param cid
	 * @return
	 */
	public List<District> loadDistrictByCity(int cid) {
		final List<District> ps = new ArrayList<District>();
		final Criteria cri = new Criteria("district", new String[] { "district_id",
				"name" });
		cri.add(Restrictions.eq("city_id", cid));
		SqlExecuteUtil.executeSql(cri.toSqlString(), new DBTemplate() {
			
			@Override
			public void execute(Statement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery(cri.toSqlString());
				while (rs.next()) {
					District p = new District();
					p.setDistrictId(rs.getInt(1));
					p.setName(rs.getString(2));
					ps.add(p);
				}
			}
		});
		return ps;
	}

	/**
	 * 获取一个区的所有 高中
	 * 
	 * @param cid
	 * @return
	 */
	public List<HighSchool> loadHighSchoolByDistrict(int did) {
		final List<HighSchool> ps = new ArrayList<HighSchool>();
		final Criteria cri = new Criteria("high_school", new String[] { "hs_id",
				"name" });
		
		cri.add(Restrictions.eq("district_id", did));
		SqlExecuteUtil.executeSql(cri.toSqlString(), new DBTemplate() {
			
			@Override
			public void execute(Statement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery(cri.toSqlString());
				while (rs.next()) {
					HighSchool p = new HighSchool();
					p.setHsId(rs.getInt(1));
					p.setName(rs.getString(2));
					ps.add(p);
				}
				
			}
		});
		return ps;
	}
	
	/**
	 * 搜索大学
	 * @return
	 */
	public List<University> searchUniversities(String uni){
		final List<University> ps = new ArrayList<University>();
		final Criteria cri = new Criteria("university", new String[] { "uni_id",
				"name" });
		cri.add(Restrictions.like("name", uni));
		SqlExecuteUtil.executeSql(cri.toSqlString(), new DBTemplate() {
			
			@Override
			public void execute(Statement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery(cri.toSqlString());
				while (rs.next()) {
					University p = new University();
					p.setUniversityId(rs.getInt(1));
					p.setName(rs.getString(2));
					ps.add(p);
				}
			}
		});

		return ps;
	}

	/**
	 * 获取提醒总数
	 * 
	 * @param pid
	 * @return
	 */
	public TipsCountTO getTipsCountTO(final int pid) {
		
		String procedure = "{call " + SP_ALL_TIPS_COUNT + "(?, ?, ?, ?, ?)}";
		final TipsCountTO tt = new TipsCountTO();
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.registerOutParameter(2, Types.INTEGER);
				stmt.registerOutParameter(3, Types.INTEGER);
				stmt.registerOutParameter(4, Types.INTEGER);
				stmt.registerOutParameter(5, Types.INTEGER);
				stmt.execute();
				tt.setSystemTip(stmt.getInt(2));
				tt.setFriendTip(stmt.getInt(3));
				tt.setMoodTip(stmt.getInt(4));
				tt.setLeaveMsgTip(stmt.getInt(5));
			}
		});
		return tt;
	}

	/**
	 * 获取好友申请
	 * 
	 * @param pid
	 * @return
	 */
	public List<FriendAddTipsTO> getFriendAddTipsTO(final int pid) {
		final List<FriendAddTipsTO> fts = new ArrayList<FriendAddTipsTO>();
		String procedure = "{call " + SP_FRIEND_TEMP_SELECT_RECORD_BYPID
				+ "(?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					FriendAddTipsTO ft = new FriendAddTipsTO();
					ft.setFriendId(rs.getInt(1));
					ft.setName(rs.getString(2));
					ft.setMessage(rs.getString(3));
					ft.setUrl(FileUtil.swithPersonPhototo50(rs.getString(4))); 
					fts.add(ft);
				}
				
			}
		});
		return fts;
	}

	/**
	 * 获取系统提醒
	 * 
	 * @param pid
	 * @return
	 */
	public List<SystemTipsTO> getSystemTipsTO(final int pid) {
		final List<SystemTipsTO> fts = new ArrayList<SystemTipsTO>();
		
		String procedure = "{call " + SP_TIPS_SELECT_MESSAGE_BYPID + "(?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					SystemTipsTO ft = new SystemTipsTO();
					ft.setTipId(rs.getInt(1));
					ft.setMessage(rs.getString(2));
					fts.add(ft);
				}
				
			}
		});
		return fts;
	}

	/**
	 * 获取心情回复提醒
	 * 
	 * @param pid
	 * @return
	 */
	public List<MoodTipsTO> getMoodTipsTO(final int pid) {
		final List<MoodTipsTO> fts = new ArrayList<MoodTipsTO>();
		
		String procedure = "{call " + SP_MOOD_TIPS_SELECT_INFO_ABOUT_TIPS
				+ "(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid, 1}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					MoodTipsTO ft = new MoodTipsTO();
					ft.setMoodPersonId(rs.getInt(1));
					ft.setName(rs.getString(2));
					ft.setUrl(rs.getString(3));
					
					ft.setUrl(FileUtil.swithPersonPhototo50(ft.getUrl()));//处理图片
					
					ft.setMoodId(rs.getInt(4));
					ft.setMessage(rs.getString(5));
					ft.setPushDate(DateUitl.localDateShow(rs.getTimestamp(6)));
					ft.setTotal(rs.getInt(7));
					ft.setReplyId(rs.getInt(8));
					ft.setReplyPersonId(rs.getInt(9));
					ft.setReplyPersonName(rs.getString(10));
					ft.setReplyURL(rs.getString(11));
					
					ft.setReplyURL(FileUtil.swithPersonPhototo50(ft.getReplyURL()));//处理图片
					
					ft.setReplyMsg(rs.getString(12));
					ft.setReplyDate(DateUitl.localDateShow(rs.getTimestamp(13)));
					fts.add(ft);
				}
			}
		});
		return fts;
	}

	/**
	 * 获取留言
	 * 
	 * @param pid
	 * @return
	 */
	public List<LeaveMsgTipsTO> getLeaveMsgTipsTO(final int pid) {
		final List<LeaveMsgTipsTO> fts = new ArrayList<LeaveMsgTipsTO>();
		String procedure = "{call " + SP_MOOD_TIPS_SELECT_INFO_ABOUT_TIPS
				+ "(?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{pid, 2}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					LeaveMsgTipsTO ft = new LeaveMsgTipsTO();
					ft.setPublisherId(rs.getInt(1));
					ft.setPublisherName(rs.getString(2));
					ft.setPublisherURL(rs.getString(3));
					
					ft.setPublisherURL(FileUtil.swithPersonPhototo50(ft.getPublisherURL()));//处理图片
					
					ft.setLeaveMsgId(rs.getInt(4));
					ft.setLeaveMsg(rs.getString(5));
					ft.setLeaveDate(DateUitl.dataTOMMString(rs.getTimestamp(6)));
					ft.setTotal(rs.getInt(7));
					ft.setReplyId(rs.getInt(8));
					ft.setReplyPersonId(rs.getInt(9));
					ft.setReplyPersonName(rs.getString(10));
					ft.setReplyPersonURL(rs.getString(11));
					
					ft.setReplyPersonURL(FileUtil.swithPersonPhototo50(ft.getReplyPersonURL()));//处理图片
					
					ft.setReplyMsg(rs.getString(12));
					ft.setReplyTime(DateUitl.dataTOMMString(rs.getTimestamp(13)));
					fts.add(ft);
				}
				
			}
		});
		return fts;
	}

	/**
	 * 查询是否已经注册了此邮箱
	 * 
	 * @param tableName
	 * @param username
	 * @return
	 */
	public int isExistUsername(final String tableName, final String username) {
		final String procedure = "{call " + SP_VERIFY_USER_NAME + "(?, ? ,?)}";
		Object o = SqlExecuteUtil.executeReturnProcedure(procedure, new Object[]{tableName, username}, new ProcedureExecutorReturn() {
			
			@Override
			public Object executeReturn(CallableStatement stmt)
					throws SQLException {
				stmt.registerOutParameter(3, Types.INTEGER);
				stmt.execute();
				int a = stmt.getInt(3);
				return a;
			}
		});
		if(o == null)return 0;
		return Integer.parseInt(o.toString());
	}

	/**
	 * 查所自己最近的心情
	 * 
	 * @return
	 */
	public List<FriendRecentMoodTO> getMyMood(final int id, final int pageNum, final int pageSize) {
		final List<FriendRecentMoodTO> frs = new ArrayList<FriendRecentMoodTO>();
		String procedure = "{call " + SP_MOOD_SELECT_MYMOOD_BY_PAGE_NUM
				+ "(?, ?, ?)}";
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{id, pageNum, pageSize}, new ProcedureExecutor() {

			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					FriendRecentMoodTO fr = new FriendRecentMoodTO();
					// 心情的基本信息
					fr.setMoodPersonId(rs.getInt(1));
					fr.setMoodPersonName(rs.getString(2));
					fr.setMoodPersonURL(rs.getString(3));
					fr.setMoodId(rs.getInt(4));
					fr.setMoodMsg(rs.getString(5));
					fr.setMoodPublishTime(DateUitl.localDateShow(rs
							.getTimestamp(6)));
					fr.setMoodDate(rs.getDate(6));
					fr.setTotalReply(rs.getInt(7));
					
					fr.setMoodPersonURL(FileUtil.swithPersonPhototo50(fr.getMoodPersonURL()));//处理图片
					

					// 第一个回复者的信息
					fr.setFirstReplyId(rs.getInt(8));
					fr.setFirstReplyPersonName(rs.getString(9));
					fr.setFirstReplyPersonURL(rs.getString(10));
					fr.setReplyTopId(rs.getInt(11));
					fr.setFirstReplyPersonMsg(rs.getString(12));
					fr.setFirstReplyPersonTime(DateUitl.localDateShow(rs
							.getTimestamp(13)));
					
					fr.setFirstReplyPersonURL(FileUtil.swithPersonPhototo50(fr.getFirstReplyPersonURL()));//处理图片
					
					
					// 最后一个回复者的信息
					fr.setLastReplyId(rs.getInt(14));
					fr.setLastReplyPersonName(rs.getString(15));
					fr.setLastReplyPersonURL(rs.getString(16));
					fr.setReplyLastId(rs.getInt(17));
					fr.setLastReplyPersonMsg(rs.getString(18));
					fr.setLastReplyPersonTime(DateUitl.localDateShow(rs
							.getTimestamp(19)));
					
					fr.setLastReplyPersonURL(FileUtil.swithPersonPhototo50(fr.getLastReplyPersonURL()));//处理图片
					
					frs.add(fr);
				}
			}
			
		});
		return frs;
	}

	/**
	 * 查自己的心情总数
	 */
	public int getTotalOfMine(final String sql) {
		Object obj = SqlExecuteUtil.executeSqlReturn(sql, new DBReturnTemplate() {
			
			@Override
			public Object executeReturn(Statement stmt) throws SQLException {
				ResultSet rs = null;
				int count = 0;
				
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					count = rs.getInt(1);
				}
				return count;
			}
		});
		return Integer.parseInt(obj.toString());
	}
	
	/**
	 * 返回值topn
	 */
	public LoginTO back2TopN(final int id) {
		
		String procedure = "{call " + SP_ENTRY_FORM_SELECTBY_PID
		+ "(?, ?, ?, ?, ?)}";
		final LoginTO lto = new LoginTO();
		SqlExecuteUtil.executeProcedure(procedure, new Object[]{id}, new ProcedureExecutor() {
			
			@Override
			public void execute(CallableStatement stmt) throws SQLException {
				stmt.registerOutParameter(2, Types.VARCHAR);
				stmt.registerOutParameter(3, Types.INTEGER);
				stmt.registerOutParameter(4, Types.FLOAT);
				stmt.registerOutParameter(5, Types.INTEGER);
				stmt.execute();
				lto.setImageURL(stmt.getString(2));
				lto.setTimes(stmt.getInt(3));
				lto.setTotalScore(stmt.getFloat(4));
				int isChecked = stmt.getInt(5);
				switch (isChecked) {
				case 1:
					lto.setAttention(LoginTO.CHECK_ING);
					break;
				case 2:
					lto.setAttention(LoginTO.CHECK_FAIL);
					break;
				}
			}
		});
		if(StringUtil.isBlank(lto.getImageURL())){
			lto.setImageURL(DEFAULT_COMP_PHOTO);//设置为参赛照片
		}
		return lto;
	}

	/**
	 * 判断是否是自己的好友
	 * @param me
	 * @param fri
	 * @return
	 */
	public int isMyFriend(final int me, final int fri){
		String procedure = "{call "+SP_FRIENDS_VERIFY_ROLE_OF_VISITOR+"(?, ?, ?)}";
		Object obj = SqlExecuteUtil.executeReturnProcedure(procedure, new Object[]{me, fri}, new ProcedureExecutorReturn() {

			@Override
			public Object executeReturn(CallableStatement stmt) throws SQLException {
				stmt.registerOutParameter(3, Types.INTEGER);
				stmt.execute();
				return stmt.getInt(3);
			}
			
		});
		if(obj == null){
			return 1;
		}
		return Integer.parseInt(obj.toString());
	}
}
