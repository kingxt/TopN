<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<jsp:include page="/page/util/common.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>注册topn</title>
		<script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js"></script>
		<script type="text/javascript" src="../scripts/topn.validate.js"></script>
		<script type="text/javascript" src="../scripts/topn.showbox.js"></script>
		<script type="text/javascript" src="../scripts/topn.common.js" ></script>
		<script type="text/javascript"
			src="../scripts/topn.validationEngine.js"></script>
		<link rel="stylesheet" href="../styles/validationEngine.jquery.css"
			type="text/css" media="screen" />
		
		<link rel="shortcut icon" href="../pictures/222.ico"/>
		<script type="text/javascript">
		
	function getValidator() {
		getCode();
	}
	function registUser() {
		$("#reg_form").submit();
	}
	function reSet() {
		$("#username").val("");
		$("#password").val("");
		$("#confirmPassword").val("");
		$("#code").val("");
	}
	//获取验证码
	function getCode() {
		if (jQuery.trim($("#username").val()) == '' || jQuery.trim($("#username").val()) == '用户邮箱') {
			alert("请输入您的邮箱以获取验证码");
			return;
		}
		var url = 'TopN/getCode.action';
		var params = {
			'username' : jQuery.trim($("#username").val())
		};
		showMessage("提示", "系统正在给您发送邮件，请稍后...");
		waitForTransfer(false);
		$.post(url, params, function cbf(data) {
			waitForTransfer(true);
			if (data == null || jQuery.trim(data) == '') {
				showMessage("提示", "服务器发送失败,请确认你的邮箱是否正确");
				return;
			}
			var member = eval("(" + data + ")");
			if (member == null) {
				showMessage("提示", "服务器发送失败,请确认你的邮箱是否正确");
				return;
			}
			var temp1, temp2;
			$.each(member, function(i) {
				if (i == 0)
					temp1 = member[i];
				if (i == 1)
					temp2 = member[i];
			});
			
			if (null != temp1 && null != temp2) {
				$("#toEmail").attr("href", temp2.value);
				$("#showRemind").html(temp1.value);
				$("#goToMailbtn").val(temp2.key);
				showBox("#gotoemail_div", "导航到邮箱");
				return;
			}else if(temp1 == null && temp2 == null){
				if(member != null){
					showMessage("提示", member.value);
					return;
				}
			}else if (null != temp1 && null == temp2) {
				showMessage("提示", temp1.value);
				return;
			}
		}, 'json');
	}
	
	function waitForTransfer(flag)
	{
		if(!flag){
			$("#wrapClose").hide();
			$("#zxxSubmitBtn").hide();
		}else{
			$("#wrapClose").show();
			$("#zxxSubmitBtn").show();
		}
	}

	$(document).ready(function() {
		resolvePngInIe6();
		var tip = '用户邮箱';
		var email = $('#username');
		if (email.val() == '' || email.val() == tip) {
			$('#username').val(tip);
			$('#username').click(
				function() {
					if ($('#username').val() == tip) {
						$('#username').val('');
						$('#username').css("color", '#000000');
					}
				}
			);
			$('#username').blur(
				 function() {
					if ($('#username').val() == tip) {
						$('#username').css("color", '#ff0000');
					}
					if($('#username').val() == ''){
						$('#username').val(tip);
						$('#username').css("color", '#ff0000');
					}
				 }
			);
			$('#username').css("color", '#ff0000');
			setTimeout(function() {
				$('#username').value = tip;
			}, 0);
		}
	});
</script>
	</head>
<body class="regist_body">
		<div class="regist_div">
			<form action="register.action" method="post" id="reg_form">
				<div class="login_head">
				</div>
				<div class="regist_content">
					<div class="regist_navigation_div"></div>
					<div class="regist_content_div">
						<div class="container_div">
							<div class="mail_input_div">
								<input type="text" id="username" name="username" title="邮箱"
									class="validate[required,custom[email]] text-input"
									style="border: 0px; background-color: Transparent; width: 124px; font-size: 13px; height: 14px; margin-left: 63px; margin-top: 9px; padding-left: 2px; color: rgb(136, 136, 136);" />

							</div>
							<div class="remark_div">
								<span style="float: left"><s:property value="#remind" />
								</span>
							</div>

						</div>
						<div class="container_div">
							<div class="password_input_div">
								<input type="password"
									style="border: 0px; background-color: Transparent; width: 124px; font-size: 11px; height: 14px; margin-left: 63px; margin-top: 9px; padding-left: 2px;"
									name="password" id="password"
									class="validate[required,length[4,20]]" />
							</div>
						</div>
						<div class="container_div">
							<div class="confirm_pwd_input_div">
								<input type="password"
									style="border: 0px; background-color: Transparent; width: 124px; font-size: 11px; height: 14px; margin-left: 63px; margin-top: 9px; padding-left: 2px;"
									name="confirmPassword" id="confirmPassword"
									class="validate[required,length[4,20],confirm[password]]" />
							</div>
						</div>
						<div class="container_div">
							<div class="validator_input_div">
								<div class="validator_content_div">
									<input type="text"
										style="border: 0px; background-color: Transparent; width: 124px; font-size: 11px; height: 14px; margin-left: 63px; margin-top: 9px; padding-left: 2px;"
										name="code" id="code" class="validate[required, onlyNumber]" />
								</div>
								<div class="get_validator_div" id="get_validator_btn">
									<a href="javascript:getValidator();"></a>
								</div>
							</div>
						</div>
						<div class="container_div">
							<div class="regist_operation_div">
								<div class="regist_btn_div" id="regist_btn_div">
									<a href="javascript:registUser();"></a>
								</div>
								<div class="reset_btn_div" id="reset_btn_div">
									<a href="javascript:reSet();"></a>
								</div>
							</div>
						</div>
					</div>
				</div>
		
		</form>
		</div>
		<div id="gotoemail_div"
			style="display: none; width: 300px; height: 60px; text-align: center;">
			<script>
	  function go() {
				window.open($("#toEmail").attr('href'));
				closeBox();
			}
		</script>
			<span id="showRemind" style="font-family:Arial; font-size: 13px"></span>
			<a id="toEmail" target="_blank"></a>
			<input type="button" id="goToMailbtn" class="goto_mail_btn"
				style="margin-top: 10px;" onclick=
	go();
>
			</input>

		</div>
	</body>
</html>
