package com.topn.servlet.filter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.topn.action.help.CookieHelp;
import com.topn.collection.UserInRegister;
import com.topn.controller.PersonalController;

public class VerificationServlet extends HttpServlet {

	/*// 验证码图片的宽度。
	private int width = 120;

	// 验证码图片的高度。
	private int height = 33;

	// 验证码字符个数
	private int codeCount = 4;

	private int x = 0;

	// 字体高度
	private int fontHeight;

	private int codeY;

	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
*/
	/**
	 * 初始化验证图片属性
	 */
	public void init() throws ServletException {
		/*// 从web.xml中获取初始信息
		// 宽度
		String strWidth = this.getInitParameter("width");
		// 高度
		String strHeight = this.getInitParameter("height");
		// 字符个数
		String strCodeCount = this.getInitParameter("codeCount");

		// 将配置的信息转换成数值
		try {
			if (strWidth != null && strWidth.length() != 0) {
				width = Integer.parseInt(strWidth);
			}
			if (strHeight != null && strHeight.length() != 0) {
				height = Integer.parseInt(strHeight);
			}
			if (strCodeCount != null && strCodeCount.length() != 0) {
				codeCount = Integer.parseInt(strCodeCount);
			}
		} catch (NumberFormatException e) {
		}

		x = width / (codeCount + 1);
		fontHeight = height - 2;
		codeY = height - 4;
*/
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {

		String chose = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		char display[] = { '0', ' ', '0', ' ', '0', ' ', '0' }, ran[] = { '0',
				'0', '0', '0' }, temp;
		Random rand = new Random();
		for (int i = 0; i < 4; i++) {
			temp = chose.charAt(rand.nextInt(chose.length()));
			display[i * 2] = temp;
			ran[i] = temp;
		}
		String random = String.valueOf(display);
		
		
		CookieHelp.setVerification(resp, String
				.valueOf(ran)); 
		//PersonalController.getInstance().getPersonById(personalId, useCache);
		
		int width = 80, height = 30;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 以下填充背景颜色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 设置字体颜色
		g.setColor(Color.RED);
		Font font = new Font("Arial", Font.PLAIN, 20);
		g.setFont(font);
		// g.drawString(random,5,14);
		g.drawString(random, 5, 20);
		g.dispose();
		ServletOutputStream outStream;
		try {
			outStream = resp.getOutputStream();
			JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(outStream);
	        encoder.encode(image);
	        outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
