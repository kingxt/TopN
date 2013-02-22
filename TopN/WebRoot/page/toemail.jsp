<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/page/util/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" href="../styles/validationEngine.jquery.css" type="text/css" media="screen" />
<link rel="shortcut icon" href="../pictures/222.ico"/>
   <script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js" ></script>
    <title>导航到邮箱</title>
</head>
<body>
        <body class="forget_password_body">
       <div class="forget_password_div">
               <div class="login_head"> </div>
               <div class="forget_pwd_content_div">
                        <div class="title_div">找回密码</div>
                        <div class="getPwd_navigation_div">
                                <span >1.填写邮箱</span>
                                 <span>>></span>
                                 <span class="fill_email_span">2.登录邮箱获取链接</span>
                                  <span>>></span>
                                  <span >3.重置密码</span>
                        </div>
                        <div style="display:inline-block;">
                              <div >
                                    <p class="toemail_tip_p">邮件已发送到您的邮箱<s:property value="username"/>，请登录到您的邮箱去接受。</p>
                              </div>
                     </div>
                       <div class="gotoemail_div" >
                               <a href="<s:property value="#mme.value"/>"> <button type="button" id="goToMailbtn" class="goto_mail_btn"><s:property value="#mme.key"/></button></a>
                       </div>
       </div>
</body>
</body>
</html>
