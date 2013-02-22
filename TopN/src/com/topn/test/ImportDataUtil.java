package com.topn.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.topn.util.ConnectionManager;
import com.topn.util.SqlExecuteUtil;

public class ImportDataUtil {

	private StringBuffer sb = new StringBuffer();

	public ImportDataUtil(String tableName, String[] a) {
		sb.append("insert into ").append(tableName).append("(");
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]);
			if (i != a.length - 1) {
				sb.append(",");
			}
		}
		sb.append(")values");
	}

	public void readProvinceFile(String path) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String b = null; 
		while ((b = br.readLine()) != null) {
			System.out.println(b);
			sb.append("('").append(b).append("')").append(",");
		}
		String sql  = sb.substring(0, sb.lastIndexOf(","));
		insert2DB(sql);
	}
	
	public void insert2DB(String sql){
		System.out.println(sql);
		Connection conn = ConnectionManager.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			s.execute(sql);
			SqlExecuteUtil.closeStatement(s);
			SqlExecuteUtil.closeConnection(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public static void main(String[] args) throws Exception {
		new ImportDataUtil("province", new String[]{"name"}).readProvinceFile("D:\\data.txt");
	}

}
