<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<script type="text/javascript">
<!--
	function modifyPassword_to(){
		var p1 = jQuery.trim($("#password").val());
		var p2 = jQuery.trim($("#confirmPassword").val());
		if(p1 == '' ||p2  == ''){
			return;
		}
		if(p1.length < 4){
			$("#pwd_error_span").html("密码长度大于4个字");
			return;
		}
		if(p1 != p2){
			$("#pwd_error_span").html("两次密码输入不一致");
			return;
		}
		var params = {
			'password':p1,
			'confirmPassword':p2
		}; 
		$.post('modifyPwd.action', params, function cbf(msg) {
			if(jQuery.trim(msg) != ''){
				showUpdateSuccess();
			}
		});
	}
//-->
</script>
<div class="update_pwd_div">
 	 <div class="update_pwd_div">  
                    <table class="basic_info_table">
                        <tbody>
                            <tr>
                                <td colspan="2"><h5>修改密码(<span style="color: red">密码长度大于4位</span>)</h5></td>
                            </tr>
                            <tr>
                                <td class="des_td">新密码：</td>
                                <td><input type="password" name="password" id="password"/>
								</td>
                            </tr>
                             <tr>
                                <td  class="des_td">确认密码：</td>
                                <td><input type="password" name="confirmPassword" id="confirmPassword"/></td>
                                <td><span id="pwd_error_span"  class="pwd_error_span"></span></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
</div>