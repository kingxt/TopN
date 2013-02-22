<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<script type="text/javascript">
<!--
	$(document).ready(function () {
		$("#password").bind('keyup',function(event) {
		if(event.keyCode==13){
			relogin();
		}   
	});
		showBox("#relogin", "重新登录");
	});
	
	function relogin(){
		var username = jQuery.trim($("#username").val());
		var password = jQuery.trim($("#password").val());
		if(username == '' || password == ''){
			alert("用户名或密码为空 ");
		}
		var params = {
			'username' : username,
			'password' : password
		}
		new TopNAjax().ajax({
    		url: 'errorlogin.action',
    		data: params,
    		async : false,
    		loading: false,
    		success: function (msg) {
    			
    			if(msg == 'null' || msg == null){
    				window.location.reload();
    			}else{
    				$("#mainContent").html(msg);
    			}
            }
    	});
	}
//-->
</script>
<div id="relogin" class="update_album_div">
	<li>
		用户名:
		<input id="username" name="username" type="text" class="name_album" title="邮箱"/>
	</li>
	<li>
		
		密码：
		<input id="password"  name="password" type="password" class="name_album" title="密码"/>
	</li>
	<li>
		<span style="color: red"><s:property value="#remind"/></span>
	</li>
	<li>
		<div class="album_operations_div">
			<span> <input id="Button3" type="button"
					onclick="relogin();" class="submit_button" /> </span>
			<span> <input id="Button4" type="button" class="cancel_button"
					onclick="$.zxxbox.hide();" /> </span>
		</div>
	</li>
</div>