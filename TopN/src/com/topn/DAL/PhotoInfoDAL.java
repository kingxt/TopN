package com.topn.DAL;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.topn.bean.PentryPhoto;
import com.topn.bean.Photo;
import com.topn.bean.TO.AlbumPhotosTO;
import com.topn.collection.MyMapEntry;
import com.topn.util.LoggerUtil;
import com.topn.util.ProcedureExecutorReturn;
import com.topn.util.SqlExecuteUtil;
import com.topn.util.Table;

/**
 * 创建人 youxishow 创建时间 2011-3-29 下午08:23:34 照片数据库处理
 */
public class PhotoInfoDAL extends BaseDAL {

	private static Logger logger = Logger.getLogger(BaseDAL.class);

	private static PhotoInfoDAL pid = new PhotoInfoDAL();

	private PhotoInfoDAL() {
	}

	public static PhotoInfoDAL getInstance() {
		return pid;
	}

	public boolean upload(List<Photo> photos, int pid, int albumId) {
		boolean result = false;
		StringBuffer procedure = new StringBuffer("call "
				+ SP_PHOTO_UPLOAD_PHOTO + "(" + pid + ",");
		StringBuffer sb = new StringBuffer("\"");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(new Date());

		for (int i = 0; i < photos.size(); i++) {
			Photo p = photos.get(i);
			if (i > 0)
				sb.append(",('" + p.getUrl() + "','" + p.getDetail() + "','"
						+ strDate + "'," + albumId + ")");

			else
				sb.append("('" + p.getUrl() + "','" + p.getDetail() + "','"
						+ strDate + "'," + albumId + ")");
		}
		sb.append("\");");
		procedure.append(sb);
		if (photos != null && photos.size() > 0) {
			result = (Boolean) SqlExecuteUtil.executeReturnProcedure(procedure
					.toString(), new Object[] {},
					new ProcedureExecutorReturn() {
						public Object executeReturn(CallableStatement stmt)
								throws SQLException {
							boolean result = false;

							result = stmt.execute();

							return result;
						}
					});
		}
		return result;

	}

	/**
	 * 根据页数返回相册里的图片
	 * 
	 * @param personalId
	 * @param albumId
	 * @param pageNum
	 * @return
	 */
	public AlbumPhotosTO loadAlbumPhotos(final int personalId,
			final int albumId, final int pageNum, final int isEntry,
			final int number) {
		String procedure = "{call " + SP_PHOTO_SELECTBY_AID + "(?,?,?,?,?)}";

		AlbumPhotosTO ap = (AlbumPhotosTO) SqlExecuteUtil
				.executeReturnProcedure(procedure, new Object[] { personalId,
						albumId, pageNum, isEntry, number },
						new ProcedureExecutorReturn() {
							public Object executeReturn(CallableStatement stmt)
									throws SQLException {
								AlbumPhotosTO ap = new AlbumPhotosTO();
								String url = "";
								String urlSmall = "";
								int total = 0;
								try {

									ResultSet rs = stmt.executeQuery();

									List<Object> photos = new ArrayList<Object>();
									while (rs.next()) {
										if (isEntry == 1) {
											Photo photo = new Photo();
											photo.setAlbumId(albumId);
											photo.setPhotoId(rs
													.getInt("photo_id"));
											url = rs.getString("url");
											if (url != null && url != "")
												urlSmall = url
														.replace("ordinary",
																"ordinary/small_150_115");
											photo.setUrl(Table.PARENT_PATH
													+ url);
											photo.setSmallUrl(Table.PARENT_PATH
													+ urlSmall);
											photo.setDetail(rs
													.getString("detail"));
											photos.add(photo);
										} else {
											PentryPhoto pp = new PentryPhoto();
											pp.setAlbumId(albumId);
											pp.setPentryId(rs
													.getInt("pentry_id"));
											pp.setUrl(Table.PARENT_PATH
													+ rs.getString("url"));
											if (urlSmall != null
													&& urlSmall != "")
												urlSmall = urlSmall
														.replace("competition",
																"competition/small_187_211");
											pp.setSamllUrl(urlSmall);
											pp
													.setDetail(rs
															.getString("detail"));
											pp.setScore(rs.getFloat("score"));
											pp.setIsEvaluating(rs
													.getInt("is_evaluating"));
											photos.add(pp);
										}
										if (total == 0) {
											total = rs.getInt("total_num");
										}
									}
									ap.setAlbumId(albumId);
									ap.setIsEntry(isEntry);
									ap.setPageNum(pageNum);
									ap.setPhotos(photos);
									ap.setTotal(total);
									ap.setPersonId(personalId);

								} catch (SQLException e) {
									LoggerUtil.loggerDebug(logger,
											"[PhotoInfoDAL， loadAlbumPhotos] 类中执行存储过程["
													+ SP_PHOTO_SELECTBY_AID
													+ "]失败, 获取信息失败"
													+ e.getMessage());
									e.printStackTrace();
								}

								return ap;
							}
						});
		return ap;
	}

	public String updatePersonHeadPortrait(final int personId, final String url) {
		String procedure = "{call " + SP_PERSON_INFO_UPLOAD_PHOTO_CHAR
				+ "(?,?,?)}";
		String oldUrl = (String) SqlExecuteUtil.executeReturnProcedure(
				procedure, new Object[] { personId, url },
				new ProcedureExecutorReturn() {
					public Object executeReturn(CallableStatement stmt)
							throws SQLException {
						String oldUrl = "";

						stmt.registerOutParameter(3, Types.VARCHAR);
						stmt.execute();
						oldUrl = stmt.getString(3);

						return oldUrl;
					}
				});
		return oldUrl;
	}

	/**
	 * 设置参赛照片
	 * 
	 * @param personId
	 * @param imgUrl
	 * @return 1 表示设置失败（可能姓名，年龄没有填写），2设置成功
	 */
	@SuppressWarnings("unchecked")
	public MyMapEntry<Integer, String> setCompetitionPictuer(final int personId, final String imgUrl) {
		String procedure = "{call " + SP_PHOTO_SET_ENTRY_PHOTO + "(?, ?, ?, ?)}";
		Object result = SqlExecuteUtil.executeReturnProcedure(procedure,
				new Object[] { personId, imgUrl },
				new ProcedureExecutorReturn() {
					public Object executeReturn(CallableStatement stmt)
							throws SQLException {

						stmt.registerOutParameter(3, Types.INTEGER);
						stmt.registerOutParameter(4, Types.VARCHAR);
						stmt.execute();
						int t = stmt.getInt(3);
						String old = stmt.getString(4);
						MyMapEntry<Integer, String> mme = new MyMapEntry<Integer, String>(t, old);
						return mme;
					}
				});
		return (MyMapEntry<Integer, String>) result;
	}

	public int movePhotos(final int personId, final String ids,
			final int albumId, final int moveAlbumId) {
		String procedure = "{call " + SP_PHOTO_MOVE_PHOTO + "(?,?,?,?,?)}";
		int result = (Integer) SqlExecuteUtil.executeReturnProcedure(procedure,
				new Object[] { personId, albumId, moveAlbumId, ids },
				new ProcedureExecutorReturn() {
					public Object executeReturn(CallableStatement stmt)
							throws SQLException {
						Integer result = new Integer(0);
						stmt.registerOutParameter(5, Types.INTEGER);
						stmt.executeUpdate();
						result = stmt.getInt(5);
						return result;
					}
				});

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<Object> deletePhotos(final int personId, final String ids,
			final int albumId) {
		String procedure = "{call " + SP_PHOTO_DELETE_PHOTO_BY_IDLIST
				+ "(?,?,?,?)}";
		List<Object> result = (List<Object>) SqlExecuteUtil
				.executeReturnProcedure(procedure, new Object[] { personId,
						albumId, ids }, new ProcedureExecutorReturn() {
					public Object executeReturn(CallableStatement stmt)
							throws SQLException {
						int total = 0;
						List<Object> result = new ArrayList<Object>();
						List<String> urls = new ArrayList<String>();

						stmt.registerOutParameter(4, Types.INTEGER);
						ResultSet rs = stmt.executeQuery();
						total = stmt.getInt(4);

						while (rs.next()) {
							urls.add(rs.getString("url"));
						}
						result.add(urls);
						result.add(total);

						return result;
					}
				});
		return result;
	}
}
