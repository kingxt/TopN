package com.topn.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import com.mortennobel.imagescaling.ResampleOp;
import org.apache.log4j.Logger;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-2 下午09:07:15
 * 
 * 图片处理类，此类效率比较好，在项目前期推荐用此类实现图片剪裁
 * 建议图片剪裁功能另外建立一个线程来完成图片剪裁功能
 * 此类的方法都是静态方法
 */
public class TopNImageScale {
	
	private static final Logger logger = Logger.getLogger(TopNImageScale.class);
	
	/**
	 * 接收输入流输生成图片
	 * @param input        输入流
	 * @param writePath    输出路径
	 * @param width		       输出宽度
	 * @param height       输出高度
	 * @param format       输出格式 jpg等
	 * @return
	 */
	public static boolean resizeImage(InputStream input, String writePath,
			Integer width, Integer height, String format) {
		try {
			BufferedImage inputBufImage = ImageIO.read(input);
			LoggerUtil.loggerDebug(logger, "转前图片高度和宽度：" + inputBufImage.getHeight() + ":"+ inputBufImage.getWidth());
			ResampleOp resampleOp = new ResampleOp(width, height);// 转换
			BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,
					null);
			ImageIO.write(rescaledTomato, format, new File(writePath));
			LoggerUtil.loggerDebug(logger, "转后图片高度和宽度：" + rescaledTomato.getHeight() + ":"+ rescaledTomato.getWidth());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 接收File输出图片
	 * @param file         要处理的图片文件
	 * @param writePath    输出路径
	 * @param width        输出宽度
	 * @param height       输出高度
	 * @param format       输出格式  jgp
	 * @return
	 */
	public static boolean resizeImage(File file, String writePath, Integer width,
			Integer height, String format) {
		try {
			BufferedImage inputBufImage = ImageIO.read(file);
			inputBufImage.getType();
			LoggerUtil.loggerDebug(logger, "转前图片高度和宽度：" + inputBufImage.getHeight() + ":"+ inputBufImage.getWidth());
			ResampleOp resampleOp = new ResampleOp(width, height);// 转换
			BufferedImage rescaledTomato = resampleOp.filter(inputBufImage,
					null);
			ImageIO.write(rescaledTomato, format, new File(writePath));
			LoggerUtil.loggerDebug(logger, "转后图片高度和宽度：" + rescaledTomato.getHeight() + ":"+ rescaledTomato.getWidth());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @param RGBS			接收字节数组生成图片
	 * @param writePath     输出路径
	 * @param width         输出宽度
	 * @param height        输出高度
	 * @param format        输出格式  jgp
	 * @return
	 */
	public static boolean resizeImage(byte[] RGBS, String writePath, Integer width,
			Integer height, String format) {
		InputStream input = new ByteArrayInputStream(RGBS);
		return resizeImage(input, writePath, width, height, format);
	}

	/**
	 * 从输入流中读取字节
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static byte[] readBytesFromIS(InputStream is) throws IOException {
		int total = is.available();
		byte[] bs = new byte[total];
		is.read(bs);
		return bs;
	}
	
	//测试
	public static void main(String[] args) throws IOException {
		
//		int width = 160;
//		int height = 120;
//		File inputFile = new File("E:\\3.jpg");TopNImageScale myImage = new TopNImageScale();
//		long ll = System.currentTimeMillis();
////		File outFile = new File("E:\\4.jpg");
////		String outPath = outFile.getAbsolutePath();
////		MyImage myImage = new MyImage();
////		InputStream input = new FileInputStream(inputFile);
////		byte[] byteArrayImage=myImage.readBytesFromIS(input);
////		input.read(byteArrayImage);
////		myImage.resizeImage(byteArrayImage, outPath, width, height, "jpg");
//		myImage.resizeImage(inputFile, "E:\\4.jpg", width, height, "jpg");
//		System.out.println(System.currentTimeMillis() - ll);
		File inputFile = new File("E:\\3.jpg");
		long ll = System.currentTimeMillis();
		TopNImageScale.resizeImage(inputFile, "E:\\4.jpg", 200, 200, "jpg");
		System.out.println(System.currentTimeMillis() - ll);
	}
}