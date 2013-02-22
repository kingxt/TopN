package com.topn.DAL;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.topn.bean.Album;
import com.topn.util.ProcedureExecutor;
import com.topn.util.ProcedureExecutorReturn;
import com.topn.util.SqlExecuteUtil;

/**
 * 创建人 youxishow 创建时间 2011-3-31 下午07:30:30 相册数据库处理
 */
public class AlbumInfoDAL extends BaseDAL {

	private static AlbumInfoDAL instance = new AlbumInfoDAL();

	public static AlbumInfoDAL getInstance() {
		return instance;
	}

	public List<Album> loadPersonalAlbum(final int personalId) {

		String procedure = "{call " + SP_ALBUM_SELECTBY_PID + "(?,?)}";
		final List<Album> albums = new ArrayList<Album>();
		SqlExecuteUtil.executeProcedure(procedure,
				new Object[] { personalId, 1 }, new ProcedureExecutor() {
					@Override
					public void execute(CallableStatement stmt)
							throws SQLException {

						ResultSet rs = stmt.executeQuery();
						while (rs.next()) {
							Album a = new Album();
							a.setAlbumId(rs.getInt("aid"));
							// a.setOwner(rs.getString("owner"));
							a.setTotal(rs.getInt("photo_num"));
							a.setName(rs.getString("name"));
							a.setPassword(rs.getString("password"));
							a.setNeedPwd(rs.getInt("need_pwd"));
							a.setDetail(rs.getString("detail"));
							a.setCover(rs.getString("cover"));
							a.setIsEntry(rs.getInt("is_entry"));
							a.setCreateTime(rs.getDate("create_time"));
							albums.add(a);
						}
					}
				});

		return albums;
	}

	/**
	 * 获取用户所有相册名
	 * 
	 * @param personId
	 * @return
	 */
	public List<Album> getAlbumsNameList(final int personalId) {

		String procedure = "{call " + SP_ALBUM_GETLIST_WHEN_LOAD_PHOTO + "(?)}";
		final List<Album> albums = new ArrayList<Album>();
		SqlExecuteUtil.executeProcedure(procedure, new Object[] { personalId },
				new ProcedureExecutor() {

					@Override
					public void execute(CallableStatement stmt)
							throws SQLException {

						ResultSet rs = stmt.executeQuery();
						while (rs.next()) {
							Album a = new Album();
							a.setAlbumId(rs.getInt("album_id"));
							a.setName(rs.getString("name"));
							albums.add(a);
						}
					}
				});
		return albums;
	}

	public int createAlbum(final Album a) {
		String procedure = "{call " + SP_ALBUM_CREATE_ALBUM
				+ "(?,?,?,?,?,?,?,?)}";
		String detail = "";
		if (a.getDetail() == null && a.getDetail() == "") {
			detail = "";
		} else
			detail = a.getDetail();
		int result = (Integer) SqlExecuteUtil.executeReturnProcedure(procedure,
				new Object[] { a.getPersonalInfoId(), a.getName(), "",
						a.getNeedPwd(), detail, a.getCover(), a.getIsEntry() },
				new ProcedureExecutorReturn() {
					@Override
					public Object executeReturn(CallableStatement stmt)
							throws SQLException {
						stmt.registerOutParameter(8, Types.INTEGER);
						stmt.execute();
						return stmt.getInt(8);
					}
				});

		return result;
	}

	public boolean updateAlbum(final int albumId, final int personId,
			final String albumName, final String detail) {
		String procedure = "{call " + SP_ALBUM_UPDATE_NAME_AND_DETAIL
				+ "(?,?,?,?)}";
		boolean result = (Boolean) SqlExecuteUtil.executeReturnProcedure(
				procedure, new Object[]{albumId, personId, albumName, detail}, new ProcedureExecutorReturn() {
					@Override
					public Object executeReturn(CallableStatement stmt)
							throws SQLException {
						stmt.execute();
						return true;
					}

				});

		return result;
	}

	/**
	 * 
	 * @param personId
	 * @param albumId
	 * 
	 * 这里检查有一个问题,为什么要传一个cover过去
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> deleteAlbum(final int personId, final int albumId) {
		String procedure = "{call " + SP_ALBUM_DELETE_BY_PID_AND_AID
				+ "(?,?,?)}";
		List<String> result = (List<String>) SqlExecuteUtil
				.executeReturnProcedure(procedure, new Object[]{personId, albumId}, new ProcedureExecutorReturn() {
					public Object executeReturn(CallableStatement stmt)
							throws SQLException {
						List<String> result = new ArrayList<String>();
							stmt.registerOutParameter(3, Types.VARCHAR);
							ResultSet rs = stmt.executeQuery();
							while (rs.next()) {
								result.add(rs.getString("url"));
							}
							if (!stmt.getString(3).startsWith("pictures"))
								result.add(stmt.getString(3));
						
						return result;
					}
				});

		return result;
	}

	public boolean updateAlbumCover(final int personId, final int albumId,
			final String cover) {
		String procedure = "{call " + SP_ALBUM_MODIFY_COVER + "(?,?,?)}";
		boolean result = (Boolean) SqlExecuteUtil.executeReturnProcedure(
				procedure, new Object[]{personId, albumId, cover}, new ProcedureExecutorReturn() {
					@Override
					public Object executeReturn(CallableStatement stmt)
							throws SQLException {
						return stmt.execute();		
					}
				});
		return result;
	}
}
