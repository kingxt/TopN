<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/page/util/common.jsp"%>

		<!--三层模式-->
		<!--中间内容显示开始-->
		<div class="mainContent_left_div">
			<div class="radius_large_top_div"></div>

			<div id="content_middle_div" class="content_middle_div">

				<div id="updateContent" class="show_info_div">
					<div id="info_div" class="update_div">
						<div class="basic_info_div">
						
						<a><h5 class="edit_info_h5" onclick="updateBaseInfoInput();">
											<img src="../pictures/edit_profile.gif" />
											编辑资料
										</h5> </a>
					<table class="basic_info_table">
						<tbody>
							
							<tr>
								<td colspan="2">
									<h5>
										基本信息
									</h5>
								</td>
							</tr>
							<tr>
								<td class="des_td">
									姓名：
								</td>
								<td>
									<s:property value="#pi.nickName" />
								</td>
							</tr>
							<tr>
								<td class="des_td">
									性别：
								</td>
								<td>
									<s:if test="#pi.sex == 1">男</s:if>
									<s:if test="#pi.sex == 2">女</s:if>
								</td>
							</tr>
							<tr>
								<td class="des_td">
									职业：
								</td>
								<td>
									<s:property value="#pi.job" />
								</td>
							</tr>
							<tr>
								<td class="des_td">
									个人简介：
								</td>
								<td>
									<s:property value="#pi.ps" />
								</td>
							</tr>
							
							<tr>
								<td class="des_td">
									生日：
								</td>
								<td>
									<s:property value="#pi.birthday" />
								</td>
							</tr>
							
							<tr>
								<td class="des_td">
									电话：
								</td>
								<td>
									<s:property value="#pi.phone" />
								</td>

							</tr>
							
							<tr>
								<td class="des_td">
									所在地：
								</td>
								<td>
									<s:property value="#pi.address" />
								</td>
							</tr>
							
							<tr>
								<td class="des_td">
									学校：
								</td>
								<td>
									<s:property value="#pi.school" />
								</td>
							</tr>
							
							<tr>
								<td class="des_td">
									MSN：
								</td>
								<td>
									<s:property value="#pi.msn" />
								</td>

							</tr>
							
							<tr>
								<td class="des_td">
									QQ：
								</td>
								<td>
									<s:property value="#pi.qq" />
								</td>
							</tr>
							
							<tr>
								<td class="des_td">
									兴趣：
								</td>
								<td>
									<s:property value="#pi.hobby" />
								</td>
							</tr>
							<tr>
								<td class="des_td">
									其他兴趣：
								</td>
								<td>
									<s:property value="#pi.otherHobby" />
								</td>
							</tr>
						</tbody>
					</table>


				</div>
				</div>

				<!-- 中间的内容 -->
			</div>
			
			</div>
			<div class="radius_large_bottom_div"></div>
		</div>
		<!--中间内容显示结束-->
		<div class="mainContent_right_div">
			<jsp:include page="../page_right/right.jsp"></jsp:include>
		</div>