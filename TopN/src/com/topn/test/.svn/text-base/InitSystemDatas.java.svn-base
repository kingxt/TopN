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
import com.topn.util.ConnectionManager;
import com.topn.util.GenerateSql;
import com.topn.util.SqlExecuteUtil;

public class InitSystemDatas {

	public void importModules(List uls, String parent) {
		int i = 6;
		for (Iterator iterator = uls.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			System.out.println();
			int pid = Integer.parseInt(element.attributeValue("id").trim());
			List lis = element.selectNodes("li");
			for (Iterator iterator2 = lis.iterator(); iterator2.hasNext();) {
				Element element1 = (Element) iterator2.next();
				City u = new City();
				u.setProvinceId(pid);
				u.setName(((Element)(element1.selectNodes("a").get(0))).getText().trim());
				try {
					String sql = GenerateSql.generateCreateSql(u, false);
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
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		String filePath = "D:\\city.txt";
		// DOM4J使用示例
		try {
			Document document = new SAXReader().read(new File(filePath));
			new InitSystemDatas().importModules(document
					.selectNodes("//ul"), null);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
