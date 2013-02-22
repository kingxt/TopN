<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <link rel="stylesheet" href="../styles/validationEngine.jquery.css" type="text/css" media="screen" />
 <link rel="shortcut icon" href="../pictures/222.ico"/>
   <script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js" ></script>
    <script type="text/javascript" src="../scripts/topn.validate.js"></script>
     <script type="text/javascript" src="../scripts/topn.validationEngine.js" ></script>
     <script type="text/javascript">
     	function resetPwd(){
     		//$("#usersign").val($.jquery.get('usersign'));
     		$("#resetForm").submit();
     	}
     </script>
    <title>重置密码</title>
</head>
<body class="forget_password_body">
       <div class="forget_password_div">
               <div class="login_head"> </div>
               <div class="forget_pwd_content_div">
                        <div class="title_div">找回密码</div>
                        <div class="getPwd_navigation_div">
                                <span >1.填写邮箱</span>
                                 <span>>></span>
                                 <span >2.登录邮箱获取链接</span>
                                  <span>>></span>
                                  <span class="fill_email_span">3.重置密码</span>
                        </div>
                        <input type="hidden" value="<s:property value=""/>"/>
                        <form action="/TopN/resetPassword.action" method="post" id="resetForm">
	                        <div style="display:inline-block;">
	                              <div  class="fill_space_div"></div>
	                              <div  class="reset_pwd_div">
	                                       <input type="password" id="password"
	                    	name="password" class="validate[required,length[4,20]]"
	                                    style="border:0px; background-color:Transparent;  width:124px; font-size:11px;height:14px;  margin:0px;left:0px;margin-top:8px;  margin-left:63px;  padding-left:2px; "/>
	                              </div>
	                        </div>
	                        <div style="display:inline-block;">
	                              <div  class="fill_space_div"></div>
	                              <div  class="reset_confirm_pwd_div">
	                                       <input type="password" id="confirmPassword"
	                    	name="confirmPassword"  class="validate[required,length[4,20],confirm[password]]"
	                                    style="border:0px; background-color:Transparent;  width:124px; font-size:11px;height:14px;  margin:0px;left:0px;margin-top:8px;  margin-left:63px;  padding-left:2px; "/>
	                              </div>
	                        </div>
	                        <div>
	                                <div  class="fill_space_div"></div>
	                               <div class="get_submit_div">
	                               		<input type="hidden" id="username" name="username" value="<s:property value="#username"/>"/>
	                                    <input type="button" class="get_submit_btn" onclick="resetPwd()"/>
	                               </div>
	                        </div>
                        </form>
               </div>
       </div>
</body>
</html>
