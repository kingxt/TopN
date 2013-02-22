package com.topn.test;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;


public class Test3 {

	public static void main(String[] args)
    {
		long ll = System.currentTimeMillis();
        if(compressPic("E:\\1.jpg", "E:\\ssss.jpg"))
        {
            System.out.println("ѹ���ɹ���"); 
        }
        else
        {
            System.out.println("ѹ��ʧ�ܣ�"); 
        }
        System.out.println(System.currentTimeMillis() - ll);
    }
    

    public static boolean compressPic(String srcFilePath, String descFilePath)
    {
        File file = null;
        BufferedImage src = null;
        FileOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;

        // ָ��дͼƬ�ķ�ʽΪ jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
        // Ҫʹ��ѹ��������ָ��ѹ����ʽΪMODE_EXPLICIT
        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
        // ����ָ��ѹ���ĳ̶ȣ�����qality��ȡֵ0~1��Χ�ڣ�
        imgWriteParams.setCompressionQuality((float)0.01);
        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
        ColorModel colorModel = ColorModel.getRGBdefault();
        // ָ��ѹ��ʱʹ�õ�ɫ��ģʽ
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel
                .createCompatibleSampleModel(16, 16)));

        try
        {
            if(srcFilePath.equals(""))
            {
                return false;
            }
            else
            {
                file = new File(srcFilePath);
                src = ImageIO.read(file);
                out = new FileOutputStream(descFilePath);

                imgWrier.reset();
                // ������ָ�� outֵ�����ܵ���write����, ImageOutputStream����ͨ���κ� OutputStream����
                imgWrier.setOutput(ImageIO.createImageOutputStream(out));
                // ����write�������Ϳ�����������дͼƬ
                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
                out.flush();
                out.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
