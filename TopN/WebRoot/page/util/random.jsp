<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.sun.image.codec.jpeg.JPEGCodec"%>
<%@page import="com.sun.image.codec.jpeg.JPEGImageEncoder"%>
<%@page import="java.awt.image.BufferedImage"%>


<%

        out.clear();
        response.setContentType("image/jpeg");
        response.addHeader("pragma","NO-cache");
        response.addHeader("Cache-Control","no-cache");
        response.addDateHeader("Expries",0);
        BufferedImage image = (BufferedImage)request.getAttribute("image");
        ServletOutputStream outStream = response.getOutputStream();
        JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(outStream);
        encoder.encode(image);
        outStream.close();
   %>