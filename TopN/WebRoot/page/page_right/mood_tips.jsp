<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<!--三层模式-->
<!--中间内容显示开始-->
<input type="hidden" id="personImageURL"
	value="<s:property value="#pi.photo"/>" />
<input type="hidden" id="personName"
	value="<s:property value="#pi.nickName"/>" />
<input type="hidden" id="personId"
	value="<s:property value="#pi.personalInfoId"/>" />

	
		<h5 class="title_h5">
			最近心情回复
		</h5>
		<div id="middle_status_show_div" class="middle_status_show_div">
			<s:iterator value="#mtpt" var="mt" status="st">
				<div id="Div1" class="status_div">
					<div class="status_show_div">
						<div class="photo_div">
							<a
								href="visitor.action?personalInfoId=<s:property value="#mt.mood.moodPersonId"/>"
								target="_black"> <img class="photo_img"
									src="<s:property value="#mt.mood.url"/>" /> </a>
						</div>
						<div class="status_content_div">
							<ul>
								<li>
									<a
										href="visitor.action?personalInfoId=<s:property value="#mt.mood.moodPersonId"/>"
										target="_black" target="_blank" class="replay_name"><s:property
											value="#mt.mood.name" />： </a>
									<span><s:property value="#mt.mood.message"
											escape="false" /> </span>
								</li>
								<li class="publish_date_li">
									<span>发布时间：</span>
									<span><s:property value="#mt.mood.pushDate" /> </span>
								</li>
							</ul>
						</div>
					</div>
					<div class="reply_detials_div">
						<div id="all_replies">
							<s:iterator value="#mt.mto" var="o">
								<div id="firstReplyId" class="status_reply_div">
									<div class="reply_info_div">
										<div class="reply_photo_div">
											<a
												href="visitor.action?personalInfoId=<s:property value="#o.replyPersonId"/>"
												target="_black"> <img class="reply_photo_img"
													src="<s:property value="#o.replyURL"/>" /> </a>
										</div>
										<div class="reply_infomation_div">
											<ul>
												<li class="reply_info_li">
													<a class="replay_name"
														href="visitor.action?personalInfoId=<s:property value="#o.replyPersonId"/>"
														target="_black"><s:property value="#o.replyPersonName" />
													</a>
													<span class="reply_datetime_span"><s:property
															value="#o.replyDate" /> </span>

													<span> <a class="del_reply_a"
														onclick="deleteRply(this);">x</a> <input
															id="hdreply_message_id" type="hidden"
															value="<s:property value="#o.replyId"/>" /> </span>

												</li>
												<li>
													<span><s:property value="#o.replyMsg" escape="false" />
													</span>
													<span class="operate_span"> <a
														id="<s:property value="#o.replyPersonId"/>"
														onclick="reply_click(this)"> 回复 </a> </span>
												</li>
											</ul>
										</div>
									</div>
									<div class="repay_seperate_div"></div>
								</div>
							</s:iterator>
						</div>
						<div class="reply_add_div" onclick="focusArea(this,event)">
							<p>
								<span class="reply_photo_span"> <a href="#"> <img
											class="reply_photo_img" src="<s:property value="#pi.photo"/>" />
								</a> </span>
								<span> <textarea id="TextArea2" cols="20" rows="2"
										class="reply_add_area" onkeyup="checkAddArea(this)"></textarea>
								</span>
							</p>
							<div class="reply_operate_div">
								<div class="reply_submit_div">
									<input type="hidden" id="parentId" name="parentId"
										value="<s:property value="#mt.mood.moodPersonId"/>">
									<input type="hidden" id="moodPersonId" name="moodPersonId"
										value="<s:property value="#mt.mood.moodPersonId"/>">
									<input type="hidden" name="orders" id="orders"
										value="<s:property value="#mt.mood.total"/>">
									<input type="hidden" id="moodId" name="moodId"
										value="<s:property value="#mt.mood.moodId"/>">
									<input type="button" id="reply_button<s:property value="#mt.mood.moodId"/>" class="reply_button" name="publishReplyMood" onclick="clickReplyButton(this)"
										/>
								</div>
								<div class="reply_char_count_div">
									<p class="reply_char_count_p">
										<span>您还能输入</span><span class="char_count_span">140</span><span>个字</span>
									</p>
								</div>
								<div id="other_operation_div" class="other_operation_div">
									<span id="expression_span" class="expression_span"> <a
										id="A5" class="expression_a"
										onclick="clickExpression(this, event);"><img
												src="../expression/smile.png" />
									</a> </span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:iterator>
			<s:if test="#mtpt.size() == 0">
			<div style="margin-top: 60; font-family: Arial">
				<center>
					<span style=" font-size: 12px">最近留言比较少，您可以多要请朋友加入你的圈子并及时更新动态</span>
				</center>
			</div>
		</s:if>
		</div>
		
		<s:if test="#sft > 0">
			<div id="ownerMoodPagination" class="pagination_div"
				style="display: none;">
			</div>
		</s:if>


