<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
     <script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js" ></script>
     <script type="text/javascript" src="../scripts/topn.validate.js"></script>
     <script type="text/javascript" src="../scripts/topn.validationEngine.js" ></script>
     <link rel="stylesheet" href="../styles/validationEngine.jquery.css" type="text/css" media="screen" />
      <script type="text/javascript">
          
          function getValidator() {
              $("#get_validator_btn").removeClass("get_validator_div").addClass("get_validator_click_div");
              setTimeout(function () {
                  $("#get_validator_btn").removeClass("get_validator_click_div").addClass("get_validator_div");
              }, 200);
              getCode();
          }
          function registUser() {
              $("#regist_btn_div").removeClass("regist_btn_div").addClass("regist_btn_click_div");
             setTimeout(function () {
                  $("#regist_btn_div").removeClass("regist_btn_click_div").addClass("regist_btn_div");
              }, 200);
              $("#reg_form").submit();
          }
          function reSet() {
              $("#reset_btn_div").removeClass("reset_btn_div").addClass("reset_btn_click_div");
              setTimeout(function () {
                 $("#reset_btn_div").removeClass("reset_btn_click_div").addClass("reset_btn_div");
              }, 200);
          }
          //
          function getCode(){
          		if(jQuery.trim($("#username").val()) == ''){
          			alert("请在用户名处输入您的邮箱地址");
          			return;
          		}
     			var url = 'TopN/getCode.action';   
	            var params={   
	                username:$("#username").val()  
	            }  	      
	            $.post(   
	                url,        
	                params,    
	                function cbf(data){ 
	                	if(data == null || jQuery.trim(data) == ''){
	                		return;
	                	}
	                    var member = eval("("+data+")");   
	                  
	                    if(member == null || jQuery.trim(member.value) == ''){
	                    	return;
	                    }
	                  	alert(member.value);
	                },    
	               'json'  
            	);
     	}
	</script>
</head>
<body class="regist_body">
    <div class="regist_div" >
                <div class="login_head"> </div>
        <div class="regist_content">
       		<form action="register.action" method="post" id="reg_form" style="position:absolute;">
                   <div class="mail_input_div" >
                    <input type="text"  style="border:0px; background-color:Transparent;  width:124px; font-size:11px;height:14px;"
                           id="username" name="username" class="validate[required,custom[email]] text-input"/>
                </div>
                <div  class="password_input_div">
                 <input type="text"  style="border:0px; background-color:Transparent;  width:124px; font-size:11px; height:14px;"
                 			name="password" id="password" class="validate[required,length[4,20]]"
                 />
                </div>
                 <div class="confirm_pwd_input_div" >
                    <input type="text"  style="border:0px; background-color:Transparent;  width:124px; font-size:11px;height:14px;"
                    		name="confirmPassword" class="validate[required,length[4,20],confirm[password]]"
                    />
                </div>
                <div  class="validator_input_div">
                 <input type="text"  style="border:0px; background-color:Transparent;  width:124px; font-size:11px; height:14px;"
                 			name="code" class="validate[required,length[4,4], onlyNumber]"
                 />
                </div>
             <div class="get_validator_div"  id="get_validator_btn" onclick="getValidator();"></div>
             <div class="regist_btn_div"  id="regist_btn_div" onclick="registUser();"></div>
             <div class="reset_btn_div"  id="reset_btn_div" onclick="reSet();"></div>
           </form> 
    </div>
    </div>
</body>
</html>
