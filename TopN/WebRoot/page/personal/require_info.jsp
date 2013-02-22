<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>TopN基本信息填写</title> 
<link rel="shortcut icon" href="../pictures/222.ico"/>
<link type="text/css" rel="Stylesheet" href="../styles/topn.css" />
<script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js"></script>
<script type="text/javascript" src="../scripts/topn.showbox.js"></script>
<script type="text/javascript" src="../scripts/topn.common.js" ></script>

<script type="text/javascript" src="../scripts/topn.setday.js"></script>

<script>
             $(document).ready(function () {
             	resolvePngInIe6();
            });
            //保存基本信息
            function save(){
            	if(jQuery.trim($("#nickName").val()) == ''){
					$("#name_tip").html("此项必填");
					return;
				}
				if($("#birthday").val() == ''){
					$("#birthday_tip").html("此项必填");
					return;
				}
				if(jQuery.trim($("#highSchool").val()) == ''
				  || jQuery.trim($("#collSchool").val()) == ''){
				  alert("请填写完整教育信息");
				  return;
				}
				
				var params = $("#updateForm").serialize();
				new TopNAjax().ajax({
					url: 'updatePersonalInfo.action',
					data: params,
					cache : false,
					async : false,
					loading : false,
					disabledButtonId : 'save_next_a',
					success: function (msg) {
						return;
			        }
				});
				openwin();
            }
            
		function openwin() { 
			opencenterwindow('../page/competition_pictuer_nav.jsp', '501', '404');
		}
            
</script>   
</head>

<body>
	<div class="show_background_div">
      <div  class="show_content_div">
		<div id="header" class="header" >
        	<jsp:include page="../header2.jsp"></jsp:include>
         </div>
          <div class="required_info_div">
          	<div class="radius_large_top_div"></div>
          	<div id="content_middle_div" class="content_middle_div">
          		<form id="updateForm" action="updatePersonalInfo.action" method="post">
				 <div id="update_div" class="update_div">
				 	<ul>
						<li>
							<a><h5>基本信息(<span style="color: red;">*</span>必填)</h5></a>
							 <div class="basic_info_div">
								<table class="basic_information_table" >
									<tbody>
										<tr  class="des_tr">
											<td class="des_td"><span style="color: red;">*</span>姓名：</td>
											<td><input name="pi.nickName" type="text" id="nickName"/></td>
											<td><span style="color: red;" id="name_tip"></span></td>
										</tr>
										<tr class="des_tr">
			                                <td  class="des_td"><span style="color: red;">*</span>性别：</td>
			                                <td><select name="pi.sex" id="sex">
														<option value="1"
															"<s:if test="#pi.sex==1">selected</s:if>">
															男
														</option>
														<option value="2"
															"<s:if test="#pi.sex==2">selected</s:if>">
															女
														</option>
													</select></td>
			                            </tr>
										<tr  class="des_tr">
											<td class="des_td">职业：</td>
											<td><input name="pi.job" type="text"/></td>
										</tr>
										 <tr>
											<td  class="intr_td">个人简介：</td>
											<td><textarea name="pi.ps"  class="introduce_are" id="introduce_are"></textarea></td>
										</tr>
									</tbody>
								</table>
							</div>
						</li>
						<li>
							<a><h5>隐私信息(<span style="color: red;">*</span>必填)</h5></a>
							<div  class="basic_info_div">
								<table class="basic_info_table" >
									  <tbody>
									<tr>
										<td class="des_td"><span style="color: red;">*</span>生日：</td>
		                                <td><input name="pi.birthday" type="text" id="birthday" readonly="readonly" onclick="showcalendar(event, this);"/></td>
		                                <td>
		                                	<select name="per.perBirthday">
												<option value="1"
													<s:if test="#per.perBirthday == 1">selected</s:if>>
													公开
												</option>
												<option value="2"
													<s:if test="#per.perBirthday == 2">selected</s:if>>
													好友可见
												</option>
												<option value="3"
													<s:if test="#per.perBirthday == 3">selected</s:if>>
													保密
												</option>
											</select>	
		                               </td>
		                               <td><span style="color: red;">输入格式：年-月-日，例如 1989-7-7</span></td>
		                               <td><span style="color: red;" id="birthday_tip"></span></td>
									</tr>
									 <tr>
										<td  class="des_td">电话：</td>
		                                <td><input name="pi.phone" type="text"/></td>
		                                <td>
		                                	<select name="per.perPhone">
												<option value="1" <s:if test="#per.perPhone == 1">selected</s:if>>
													公开
												</option>
												<option value="2" <s:if test="#per.perPhone == 2">selected</s:if>>
													好友可见
												</option>
												<option value="3" <s:if test="#per.perPhone == 3">selected</s:if>>
													保密
												</option>
											</select>
		                               </td>
									</tr>
									 <tr>
										<td  class="des_td">所在地：</td>
		                                 <td><input name="pi.address" type="text"/></td>
		                                <td>
		                                	<select name="per.perAddress">
												<option value="1"
													<s:if test="#per.perAddress == 1">selected</s:if>>
													公开
												</option>
												<option value="2"
													<s:if test="#per.perAddress == 2">selected</s:if>>
													好友可见
												</option>
												<option value="3"
													<s:if test="#per.perAddress == 3">selected</s:if>>
													保密
												</option>
											</select>
		                               </td>
									</tr>
									 <tr>
										<td  class="des_td">MSN：</td>
		                                 <td><input name="pi.msn" type="text"/></td>
		                                <td>
		                                	<select name="per.perMsn">
										<option value="1" <s:if test="#per.perMsn == 1">selected</s:if>>
											公开
										</option>
										<option value="2" <s:if test="#per.perMsn == 2">selected</s:if>>
											好友可见
										</option>
										<option value="3" <s:if test="#per.perMsn == 3">selected</s:if>>
											保密
										</option>
										</select>
		                               </td>
									</tr>
									 <tr>
										<td  class="des_td">QQ：</td>
			                                 <td><input name="pi.qq" type="text"/></td>
			                                <td>
			                                	<select name="per.perQQ">
											<option value="1" <s:if test="#per.perQQ == 1">selected</s:if>>
												公开
											</option>
											<option value="2" <s:if test="#per.perQQ == 2">selected</s:if>>
												好友可见
											</option>
											<option value="3" <s:if test="#per.perQQ == 3">selected</s:if>>
												保密
											</option>
										</select>
			                               </td>
									</tr>
								</tbody>                		
								</table>
							 </div>
						</li>
						<li>
							<a><h5>教育信息(<span style="color: red;">*</span>必填)</h5></a>
							 <div class="basic_info_div">
								<jsp:include page="education.jsp"></jsp:include>
							</div>
						</li>
						<li>
							<div class="basic_info_div">
								<a href="#" class="save_next_a" onclick="save()" id="save_next_a"></a>
							</div>
						</li>
					</ul>
				 </div>
				 </form>
			</div>
			<div class="radius_large_bottom_div"></div>
		  </div>
	  </div>
   </div>
</body>
</html>
