package com.topn.util;

import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-5 下午04:24:44
 * 
 * 我们公司的邮箱账号是 msg4topn@163.com 密码 top12345
 */
public class EmailSender {

	private static Logger logger = Logger.getLogger(EmailSender.class);

	private static EmailSender instance = new EmailSender();

	public static EmailSender getInstance() {
		return instance;
	}

	// 邮箱服务器
	private final String HOST = "smtp.163.com";

	// 这个是你的邮箱用户名
	private String username = "msg4topn";
	// 你的邮箱密码
	private String password = "top12345";

	// 注册账号时发送的标题
	public static final String HEAD_VERIFICATION_CODE = "topn请您查收注册验证码";

	public static final String SUBJECT_VERIFICATION_CODE = "topn请您查收注册验证码";

	public static final String SUBJECT_FORGET_PWD = "请点击下面的链接导航到修改密码页面，谢谢";

	private String mail_from = "msg4topn@163.com";

	Properties props = new Properties();
	

	/**
	 * 发送邮件以获得注册验证码
	 * 
	 * @param sendWho
	 *            发送给谁（用户名）
	 * @param sendTo
	 *            发送给的用户名
	 * @param subjcet
	 *            主题
	 * @param body
	 *            内容
	 * @return
	 * @throws Exception
	 */
	public boolean send(String sendWho, String sendTo, String subjcet,
			String body) {
		// 设置session,和邮件服务器进行通讯。
		SimpleEmail email = new SimpleEmail();          
        email.setTLS(true); //是否TLS校验，，某些邮箱需要TLS安全校验，同理有SSL校验       
       // email.setSSL(true);             
        email.setHostName(HOST);      
        email.setAuthenticator(new DefaultAuthenticator(username, password));     
        try        
        {       
         email.setFrom(mail_from); //发送方,这里可以写多个     
         email.addTo(sendTo); // 接收方     
         email.setCharset("GBK");     
         email.setSubject(subjcet); // 标题     
        
         email.setMsg(body);// 内容     
         email.send();     
        } catch (EmailException e) {       
            e.printStackTrace();       
            return false;
        }     
        return true;
	}

	/**
	 * 
	 * @param sendWho
	 * @param sendTo
	 *            接受人的emial
	 * @param subjcet
	 * @param body
	 * @return
	 */
	public boolean sendHTML(String sendWho, String sendTo, String subjcet,
			String body) {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(HOST);
		email.setTLS(true); // 是否TLS校验，，某些邮箱需要TLS安全校验，同理有SSL校验
		email.setAuthentication(username, password);
		try {
			email.setCharset("GBK");
			email.addTo(sendTo, sendWho);
			email.setFrom(mail_from, "topn小组");
			email.setSubject(subjcet);
			email.setHtmlMsg("<html>" + body + "</html>");
			// 假如图片失效时显示的文字
			email.setTextMsg("Your email client does not support HTML messages");
			// send the email
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
