<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
     <%@ include file="/page/util/common.jsp"%>
     <%
String path = "";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>登录topn</title>
       <base href="<%=basePath%>">
     <script type="text/javascript" src="TopN/scripts/topn.jquery-1.4.1.js" ></script>
     <script type="text/javascript" src="TopN/scripts/topn.validate.js" ></script>
     <script type="text/javascript" src="TopN/scripts/topn.validationEngine.js" ></script>
     <script type="text/javascript" src="TopN/scripts/topn.common.js"></script>	
     <link rel="stylesheet" href="TopN/styles/validationEngine.jquery.css" type="text/css" media="screen" />
     <link rel="shortcut icon" href="TopN/pictures/222.ico"/>
     <script type="text/javascript">

        
     	function login() {
     	    $("#login_btn").removeClass("login_button_div").addClass("login_button_click_div");
     	   setTimeout(function () {
     	       $("#login_btn").removeClass("login_button_click_div").addClass("login_button_div");
     	    }, 200);
     	    commitMe();
     	}
     	function regist() {
     	    location.href = "/TopN/page/regist.jsp";
     	}
        function getPwd() {
       		location.href = "/TopN/page/forget_pwd.jsp";
            $("#foget_pwd_btn").removeClass("forget_pwd_div").addClass("forget_pwd_click_div");
            setTimeout(function () {
                $("#foget_pwd_btn").removeClass("forget_pwd_click_div").addClass("forget_pwd_div");
            }, 200);
        }
        
        function commitMe(){
     		$("#login_form").submit();
     	}
     	
     	$(document).ready(function() {
     		resolvePngInIe6();
     		$("#password").bind('keyup',function(event) {
				if(event.keyCode==13){
					commitMe();
				}   
			}); 
		});
	</script>
	
	
</head>
<body class="login_body">

<div id="login_div" class="login_div">
	<form id="login_form" action="TopN/login.action" method="post">
		<div class="login_head">
			
		</div>
		<div class="login_content">
			<div class="top_container_div">				
				<div id="regist_btn" class="regist_link_div" >
					<a href="javascript:regist();"></a>
				</div>			
				<div class="login_mail_input_div">
					<input class="validate[required,custom[email]] text-input" name="username" id="username" 
					style="border: 0px; background-color: Transparent; width: 124px; font-size: 13px; height: 14px; margin: 0px; left: 0px; margin-top: 9px; margin-left: 63px; padding-left: 2px;" type="text" />
				</div>
				<div id="mail_error_div" class="mail_error_div"><p id="mail_error_p"><s:property value="#remind"/></p></div>
			</div>
			<div id="password_input_div" class="login_password_input_div">
					<div class="login_password_content_div">
						<input id="password" class="validate[required,length[4,20]]" name="password" style="border: 0px; background-color: Transparent; width: 124px; font-size: 13px; height: 14px; padding-left: 2px;margin-top: 8px; margin-left: 63px;" type="password" />
					</div>
					<div id="login_btn" class="login_button_div" >
						<a href="javascript:login();"></a>
					</div>
			</div>
			<div id="" class="other_operation_div">
				<div class="remberPwd_div">
					<input id="rememberPwd" name="rememberPwd" type="checkbox" value="1" />
					<input id="encrypt" name="encrypt" type="hidden" value="1" />
				</div>
				<div id="foget_pwd_btn" class="forget_pwd_div" >
					<a href="javascript:getPwd();"></a>
				</div>
			</div>
		</div>
	</form>
</div>

</body>

</html>
