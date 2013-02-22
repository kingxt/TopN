package com.topn.test;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.topn.bean.FeedBack;
import com.topn.util.DateUitl;
import com.topn.util.GenerateSql;


public class Test {

        /**
         * @param args
         */
        public static void main(String[] args) {
                
        		FeedBack fb = new FeedBack();
        		fb.setText("asdf");
        		fb.setTime(DateUitl.dateTOString(new Date()));
        		try {
					System.out.println(GenerateSql.generateCreateSql(fb, false));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				double o = 3.1415;
				float b = (float) o;
				System.out.println(b);
        }
        
}