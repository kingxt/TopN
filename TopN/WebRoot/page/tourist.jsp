<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>游客</title>
<script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js"></script>
<script type="text/javascript" src="../scripts/topn.showbox.js"></script>
<link type="text/css" rel="Stylesheet" href="../styles/topn.css" />
<link rel="shortcut icon" href="../pictures/222.ico"/>   
<script type="text/javascript">
	//添加朋友
		function addFriend(){
				var url = 'addFriend.action';   
	            var params={   
	                'friendId':$("#personal_photo_img").attr("name"),
	                'friendType':1,
	            };  
	            $.post(   
	                url,        
	                params,    
	                function cbf(data){ 
	                   showMessage("好友申请提醒","您的申请已发送给对方，等待好友的验证");
	                },    
	               'json'   //数据传递的类型  json   
            	);
		}
</script>
</head>

<body>
	<div id="show_background_div" class="show_background_div" >
	    <div  class="show_content_div">
		    <div class="show_content_container_div">
				<div id="header" class="header" >
			        <jsp:include page="header2.jsp"></jsp:include>
			     </div>
			      <div id="content_left_div" class="content_left_div">
			      	 <div id="left_top__div" class="left_top__div">
		                <div >
		                    <img  id="personal_photo_img"    src="<s:property value="#pi.photo"/>" name="<s:property value="#pi.personalInfoId"/>"/>
		                </div>
		             </div>
		             <div>
		             	<ul>
		             		<li>目前有:<s:property value="#rank.rankTimes"/>人对其打分</li>
		             		<s:if test="#rank.rankNum == -1">
		             			<li>尚未纳入排行榜</li>
		             		</s:if>
		             		<s:elseif test="#rank.rankNum > 1000">
		             			<li>TopN排名:...</li>
		             		</s:elseif>		 
		             		<s:else>
		             			<s:if test="#rankURL">
		             				TopN 目前排名 <span style="font-size: 16px; font-weight: bold; color: red"><s:property value="#rankURL"/></span>
		             			</s:if>
		             			<s:else>
		             				TopN 目前排名<span style="font-size: 16px; font-weight: bold; color: red"><s:property value="#rank.rankNum"/></span>
		             			</s:else>		             			
		             		</s:else>            		
		             	</ul>
		             </div>
			     </div>
			     <div id="mainContent" class="mainContent_div" >
			          <div class="mainContent_left_div">
				          <div class="radius_large_top_div"></div>
				          	<div id="content_middle_div" class="content_middle_div">
					          	 <div id="stranger_div" class="stranger_div">
					          		<div>
					          			<span><s:property value="#pi.nickName"/>&nbsp;</span><span></span>
					          		</div>
		          					<div>	
		          						<a class="make_friend_a" onclick="addFriend()"></a>
		          				    </div>
		          			     </div>	
		          <div class="basic_info_div">			     
		          			     <!--基本信息--> 
		          			     <table class="basic_info_table">
						<tbody>
							<tr>
								<td class="des_td">
									性别：
								</td>
								<td>
									<s:if test="#pi.sex == 1">男</s:if>
									<s:if test="#pi.sex == 2">女</s:if>
								</td>
							</tr>
							
							<s:if test="#pi.job != null">
								<tr>
									<td class="des_td">
										职业：
									</td>
									<td>
										<s:property value="#pi.job" />
									</td>
								</tr>
							</s:if>
							<s:if test="#pi.ps != null">
								<tr>
									<td class="des_td">
										个人简介：
									</td>
									<td>
										<s:property value="#pi.ps" />
									</td>
								</tr>
							</s:if>
							<s:if test="#pi.birthday != null && #per.perBirthday == 1">
								<tr>
									<td class="des_td">
										生日：
									</td>
									<td>
										<s:property value="#pi.birthday" />
									</td>
								</tr>
							</s:if>
							<s:if test="#pi.phone != null && #per.perPhone == 1" >
								<tr>
									<td class="des_td">
										电话：
									</td>
									<td>
										<s:property value="#pi.phone" />
									</td>
	
								</tr>
							</s:if>
							<s:if test="#pi.address != null && #per.perAddress == 1">
								<tr>
									<td class="des_td">
										所在地：
									</td>
									<td>
										<s:property value="#pi.address" />
									</td>
								</tr>
							</s:if>
							<s:if test="#pi.school != null && #per.perSchool == 1">
								<tr>
									<td class="des_td">
										学校：
									</td>
									<td>
										<s:property value="#pi.school" />
									</td>
								</tr>
							</s:if>
							<s:if test="#pi.msn != null && #per.perMsn == 1">
								<tr>
									<td class="des_td">
										MSN：
									</td>
									<td>
										<s:property value="#pi.msn" />
									</td>
								</tr>
							</s:if>
							<s:if test="#pi.qq != null && #per.perQQ == 1">
								<tr>
									<td class="des_td">
										QQ：
									</td>
									<td>
										<s:property value="#pi.qq" />
									</td>
								</tr>
							</s:if>
							<s:if test="#pi.hobby != null && #pi.hobby != ''">
								<tr>
									<td class="des_td">
										兴趣：
									</td>
									<td>
										<s:property value="#pi.hobby" />
									</td>
								</tr>
							</s:if>
							<s:if test="#pi.otherHobby != null && #pi.otherHobby != ''">
								<tr>
									<td class="des_td">
										其他兴趣：
									</td>
									<td><s:property value="#pi.otherHobby" /></td>
								</tr>
							</s:if>
						</tbody>
					</table>
					</div>
							</div>
						  <div class="radius_large_bottom_div"></div>
					  </div>
				</div>
		  </div>
 	 </div>
 	</div> 
 	<div id="footer_div"  class="footer_div">
                <jsp:include page="page_right/bottom.jsp"></jsp:include>
    </div>   
</body>
</html>
