<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <link rel="stylesheet" href="../styles/validationEngine.jquery.css" type="text/css" media="screen" />
   <script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js" ></script>
   <script type="text/javascript" src="../scripts/topn.validate.js"></script>
     <script type="text/javascript" src="../scripts/topn.validationEngine.js" ></script>
     <link rel="shortcut icon" href="../pictures/222.ico"/>
    <title>忘记密码</title>
    <script type="text/javascript">
    	function getNext(){
    		var imgSrc = $("#imgObj");   
    		var src = imgSrc.attr("src");   
    		imgSrc.attr("src",chgUrl(src));   
    	}
    	
    	function chgUrl(url){   
		    var timestamp = (new Date()).valueOf();   
		    url = url.substring(0,17);   
		    if((url.indexOf("&")>=0)){   
		        url = url + "×tamp=" + timestamp;   
		    }else{   
		        url = url + "?timestamp=" + timestamp;   
		    }   
		    return url;   
		}   
		
		$(document).ready(function() {
     		resolvePngInIe6();
     	});	
    </script>
</head>
<body class="forget_password_body">
       <div class="forget_password_div">
               <div class="login_head"> </div>
               <div class="forget_pwd_content_div">
                        <div class="title_div">找回密码</div>
                        <div class="getPwd_navigation_div">
                                <span class="fill_email_span">1.填写邮箱</span>
                                 <span>>></span>
                                   <span >2.登录邮箱获取链接</span>
                                  <span>>></span>
                                  <span >2.重置密码</span>
                        </div>
                     <form action="sendCertification.action" method="post">
                        <div style="display:inline-block;">
                              <div  class="fill_space_div"></div>
                              <div  class="old_email_div">
                                       <input type="text"
                    	name="username" class="validate[required,custom[email]] text-input"  
                                    style="border:0px; background-color:Transparent;  width:124px; font-size:11px;height:14px;  margin:0px;left:0px;margin-top:8px;  margin-left:63px;  padding-left:2px; "/>
                              </div>
                        </div>
                        <div style="display:inline-block;">
                              <div  class="fill_space_div"></div>
                              <div  class="old_validation_div">
                                       <input type="text"
                    	name="code" class="validate[required] text-input"  
                                    style="border:0px; background-color:Transparent;  width:124px; font-size:11px;height:14px;  margin:0px;left:0px;margin-top:8px;  margin-left:63px;  padding-left:2px; "/>
                              </div>
                              <div class="validation_img_div"><img id="imgObj" alt="" src="verifyCodeServlet" onclick="getNext()"/>
                              	 <span><font color="red"><s:property value="#remind"/></font></span>
                              </div>                            
                        </div>
                        <div style="display:inline-block;">
                                <div  class="fill_space_div"></div>
                               <div class="get_submit_div">
                                    <input type="submit" class="get_submit_btn" value=""/>
                               </div>
                        </div>
                     </form>      
               </div>
       </div>
</body>
</html>
