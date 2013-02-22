package com.topn.util;

import org.im4java.core.IMOperation;

/**
 * 
 * @author KingXt
 * 此类用来压缩处理图片，主要是因为用户上传上来的图片可能太大，
 * 或者格式原因都不能够很好的在网络传输
 */
public class CompressPicture {

	public static IMOperation cropImage(IMOperation op, int sw, int sh,
			int dw, int dh) throws Exception {

		if (sw <= 0 || sh <= 0 || dw <= 0 || dh <= 0)
			return null;
		

		if ((sw <= dw) && (sh <= dh)) // 如果源图宽度和高度都小于目标宽高，则仅仅压缩图片
		{
			op.resize(sw, sh);
		}

		if ((sw <= dw) && (sh > dh))// 如果源图宽度小于目标宽度，并且源图高度大于目标高度
		{
			op.resize(sw, sh); // 压缩图片
			op.append().crop(sw, dh, 0, (sh - dh) / 2);// 切割图片
		}

		if ((sw > dw) && (sh <= dh))// 如果源宽度大于目标宽度，并且源高度小于目标高度
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
		return op;
	}
}
