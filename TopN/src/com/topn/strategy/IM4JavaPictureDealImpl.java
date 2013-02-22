package com.topn.strategy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Operation;
import com.topn.manager.PersonalPictureUploadToNativeManager;
import com.topn.util.CompressPicture;
import com.topn.util.LoggerUtil;
import com.topn.util.PropertyDeal;

/**
 * 
 * @author KingXt 图片用 IM4Java方式实现处理
 */
public class IM4JavaPictureDealImpl {

	private static final Logger logger = Logger
			.getLogger(PersonalPictureUploadToNativeManager.class);

	public static final String JPG = "jpg";
	public static final String PNG = "png";
	public static final String GIF = "gif";

	private static final IM4JavaPictureDealImpl im4jpdl = new IM4JavaPictureDealImpl();

	private IM4JavaPictureDealImpl() {
	}

	public static IM4JavaPictureDealImpl getInstance() {
		return im4jpdl;
	}

	/**
	 * 传入此方法的file都应该经过处理, 直接本地处理
	 * 
	 * @param file
	 * @param pictureType
	 */
	public void dealImageToForNative(File file, String fos, String transferType) {
		long ti = System.currentTimeMillis();
		IMOperation localIMOperation = new IMOperation();
		BufferedImage localBufferedImage = null;
		try {
			localBufferedImage = ImageIO.read(file);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		localIMOperation.addImage();

		long ui = System.currentTimeMillis();
		System.out.println(ui - ti);
		try {

			CompressPicture.cropImage(localIMOperation, 4000, 3000,
					PropertyDeal.user_contraction_picture_original_wide,
					PropertyDeal.user_contraction_picture_original_height);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() - ui);
		localIMOperation.addImage();

		ConvertCmd localConvertCmd = new ConvertCmd(true);

		try {
			localConvertCmd.run(localIMOperation, new Object[] {
					localBufferedImage, fos });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 居中切割图片(不支持gif图片切割) 1、如果源图宽高都小于目标宽高，则只压缩图片，不做切割
	 * 2、如果源图宽高都大于目标宽度，则根据宽度等比压缩后再居中切割 3、其它条件下，则压缩图片（不做缩放）后再居中切割
	 * 
	 * 该方法在知道源图宽度（sw）和高度（sh）的情况下使用
	 * 
	 * @param srcPath
	 *            源图路径
	 * @param desPath
	 *            切割图片保存路径
	 * @param sw
	 *            源图宽度
	 * @param sh
	 *            源图高度
	 * @param dw
	 *            切割目标宽度
	 * @param dh
	 *            切割目标高度
	 * @throws Exception
	 */
	public void cropImage(String srcPath, String desPath, int sw, int sh,
			int dw, int dh){

		if (sw <= 0 || sh <= 0 || dw <= 0 || dh <= 0)
			return;

		IMOperation op = new IMOperation();
		op.addImage();

		if ((sw <= dw) && (sh <= dh)) // 如果源图宽度和高度都小于目标宽高，则仅仅压缩图片
		{
			op.resize(sw, sh);
		}

		if ((sw <= dw) && (sh > dh)) // 如果源图宽度小于目标宽度，并且源图高度大于目标高度
		{
			op.resize(sw, sh); // 压缩图片
			op.append().crop(sw, dh, 0, (sh - dh) / 2);// 切割图片
		}

		if ((sw > dw) && (sh <= dh)) // 如果源宽度大于目标宽度，并且源高度小于目标高度
		{
			op.resize(sw, sh);
			op.append().crop(dw, sh, (sw - dw) / 2, 0);
		}

		if (sw > dw && sh > dh) // 如果源图宽、高都大于目标宽高
		{
			float ratiow = (float) dw / sw; // 宽度压缩比
			float ratioh = (float) dh / sh; // 高度压缩比

			if (ratiow >= ratioh) // 宽度压缩比小（等）于高度压缩比（是宽小于高的图片）
			{
				int ch = (int) (ratiow * sh); // 压缩后的图片高度

				op.resize(dw, null); // 按目标宽度压缩图片
				op.append().crop(dw, dh, 0, (ch > dh) ? ((ch - dh) / 2) : 0); // 根据高度居中切割压缩后的图片
			} else // （宽大于高的图片）
			{
				int cw = (int) (ratioh * sw); // 压缩后的图片宽度

				op.resize(cw, null); // 按计算的宽度进行压缩
				op.append().crop(dw, dh, (cw > dw) ? ((cw - dw) / 2) : 0, 0); // 根据宽度居中切割压缩后的图片

			}
		}

		op.addImage();
		ConvertCmd convert = new ConvertCmd(true);
		try {
			convert.run(op, srcPath, desPath);
		} catch (Exception e) {
			LoggerUtil.loggerDebug(logger, "处理图片失败，源路径("+ srcPath+")目标路径(" +desPath+")" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 处理图片方法,此方法可以不用传过来图片大小
	 * @param srcImg  原图片路径
	 * @param desImg  目标图片路径
	 */
	public void cropImage(String srcImg, String desImg){
		ConvertCmd cvcmd = new ConvertCmd(true);   
		Operation op = new Operation();   
		op.addImage();   
		op.addRawArgs("-sample" ,  "200x220^" );   
		op.addRawArgs("-gravity" ,  "center" );   
		op.addRawArgs("-extent" ,  "200x220" );   
		op.addRawArgs("-quality" ,  "100" );   
		op.addImage();   
		   
		try {
			cvcmd.run(op, srcImg, desImg);
		} catch (Exception e) {
			LoggerUtil.loggerDebug(logger, "处理图片失败，源路径("+ srcImg+")目标路径(" +desImg+")" + e.getMessage());
		}   
	}
}
