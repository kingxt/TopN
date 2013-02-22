package com.topn.test;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.topn.bean.City;
import com.topn.bean.District;
import com.topn.bean.HighSchool;
import com.topn.util.ConnectionManager;
import com.topn.util.GenerateSql;
import com.topn.util.SqlExecuteUtil;

public class SchoolInitSystemDatas {

	public void importModules(List uls, String parent) throws Exception {
		int i = 1;
		Connection conn = ConnectionManager.getInstance().getConnection();
		Statement s = conn.createStatement();
		for (Iterator iterator = uls.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			System.out.println();
			//int pid = Integer.parseInt(element.attributeValue("id").trim());
			List lis = element.selectNodes("li");
			for (Iterator iterator2 = lis.iterator(); iterator2.hasNext();) {
				Element element1 = (Element) iterator2.next();
				HighSchool u = new HighSchool();
				u.setDistrictId(i);
				u.setName(((Element)(element1.selectNodes("a").get(0))).getText().trim());
				try {
					String sql = GenerateSql.generateCreateSql(u, false);
					System.out.println(sql);
					
					
					try {
						
						s.execute(sql);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
		}
		SqlExecuteUtil.closeStatement(s);
		SqlExecuteUtil.closeConnection(conn);
	}

	public static void main(String[] args) {
		String filePath = "D:\\high_school.txt";
		// DOM4J使用示例
		try {
			Document document = new SAXReader().read(new File(filePath));
			try {
				new SchoolInitSystemDatas().importModules(document
						.selectNodes("//ul"), null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
