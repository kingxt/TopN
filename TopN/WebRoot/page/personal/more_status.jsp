<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>

<s:iterator value="#frs" var="fr" status="st">
				<div id="Div1" class="status_div">
					<div class="status_show_div">
						<div class="photo_div">
							<a
								href="visitor.action?personalInfoId=<s:property value="#fr.moodPersonId"/>"
								target="_black"> <img class="photo_img"
									src="<s:property value="#fr.moodPersonURL"/>" /> </a>
						</div>
						<div class="status_content_div">
							<ul>
								<li>
									<a
										href="visitor.action?personalInfoId=<s:property value="#fr.moodPersonId"/>"
										target="_black" target="_blank" class="replay_name"><s:property
											value="#fr.moodPersonName" />: </a>
									<span><s:property value="#fr.moodMsg" escape="false"/>
									</span>
								</li>
								<li class="publish_date_li">
									<span>发布时间：</span>
									<span><s:property value="#fr.moodPublishTime" />
									</span>
									<span> <a class="reply_operate_a" onclick="packUp(this);">收起回复</a></span>
								</li>
							</ul>
						</div>
					</div>
					<div class="reply_detials_div">
						<div id="all_replies">
							<s:if test="#fr.firstReplyId > 0">
								<div id="status_reply_id" class="status_reply_div">
									<div class="reply_info_div">
										<div class="reply_photo_div">
											<a
												href="visitor.action?personalInfoId=<s:property value="#fr.firstReplyId"/>"
												target="_black"> <img class="reply_photo_img"
													src="<s:property value="#fr.firstReplyPersonURL"/>" /> </a>
										</div>
										<div class="reply_infomation_div">
											<ul>
												<li class="reply_info_li">
													<a class="replay_name"
														href="visitor.action?personalInfoId=<s:property value="#fr.firstReplyId"/>"
														target="_black"><s:property
															value="#fr.firstReplyPersonName" />
													</a>
													<span class="reply_datetime_span"><s:property
															value="#fr.firstReplyPersonTime" />
													</span>
													<s:if test="#fr.firstReplyId == #pi.personalInfoId">
														<span> <a class="del_reply_a"
															onclick=
		deleteRply(this);
	>x</a> <input
																id="hdreply_message_id" type="hidden"
																value="<s:property value="#fr.replyTopId"/>" /> </span>
													</s:if>
												</li>
												<li>
													<span><s:property value="#fr.firstReplyPersonMsg" escape="false" />
													</span>
													<span class="operate_span"> <a
														onclick="reply_click(this);" id="<s:property value="#fr.firstReplyId"/>"> 回复 </a> </span>
												</li>
											</ul>
										</div>
									</div>
									<div class="repay_seperate_div"></div>
								</div>
							</s:if>
							<s:if test="#fr.totalReply > 2">
								<div class="show_more_div">
									<a id="showAll" class="showAll" onclick=
		showAllReplies(this);
	>全部显示<s:property
											value="#fr.totalReply" />条</a>
	
								</div>
							</s:if>
							<s:if test="#fr.lastReplyId > 0 && #fr.totalReply > 1">
								<div id="status_reply_id" class="status_reply_div">
									<div class="reply_info_div">
										<div class="reply_photo_div">
											<a
												href="visitor.action?personalInfoId=<s:property value="#fr.lastReplyId"/>"
												target="_black"> <img class="reply_photo_img"
													src="<s:property value="#fr.lastReplyPersonURL"/>" /> </a>
										</div>
										<div class="reply_infomation_div">
											<ul>
												<li class="reply_info_li">
													<a class="replay_name"
														href="visitor.action?personalInfoId=<s:property value="#fr.lastReplyId"/>"
														target="_black"><s:property
															value="#fr.lastReplyPersonName" />
													</a>
													<span class="reply_datetime_span"><s:property
															value="#fr.lastReplyPersonTime" />
													</span>
													<s:if test="#fr.lastReplyId == #pi.personalInfoId">
														<span> <a class="del_reply_a"
															onclick="deleteRply(this);">x</a> <input
																id="hdreply_message_id" type="hidden"
																value="<s:property value="#fr.replyLastId"/>" /> </span>
													</s:if>
												</li>
												<li>
													<span><s:property value="#fr.lastReplyPersonMsg" escape="false"/>
													</span>
													<span class="operate_span"> <a
														onclick="reply_click(this);" id="<s:property value="#fr.lastReplyId"/>"> 回复 </a> </span>
												</li>
											</ul>
										</div>
									</div>
									<div class="repay_seperate_div"></div>
								</div>
							</s:if>
						</div>
						<div class="reply_add_div" onclick="focusArea(this,event)">
							<p>
								<span class="reply_photo_span"> <a href="#"> <img
											class="reply_photo_img"
											src="<s:property value="#pi.photo"/>" /> </a> </span>
								<span> <textarea id="TextArea2" cols="20" rows="2"
										class="reply_add_area" onkeyup="checkAddArea(this)"
										></textarea>
								</span>
							</p>
							<div class="reply_operate_div">
								<div class="reply_submit_div">
									<input type="hidden" id="parentId" name="parentId"
										value="<s:property value="#fr.moodPersonId"/>">
										<input type="hidden" id="moodPersonId" name="moodPersonId"
										value="<s:property value="#fr.moodPersonId"/>">
									<input type="hidden" id="orders" name="orders" id="orders"
										value="<s:property value="#fr.totalReply"/>">
									<input type="hidden" id="moodId" name="moodId"
										value="<s:property value="#fr.moodId"/>">
									<input type="button" id="reply_button<s:property value="#fr.moodId"/>" class="reply_button" name="publishReplyMood" onclick="clickReplyButton(this)"
										/>
								</div>
								<div class="reply_char_count_div"><p class="reply_char_count_p"><span>您还能输入</span><span  class="char_count_span">140</span><span>个字</span></p></div>
								
								<div id="other_operation_div" class="other_operation_div">
									<span id="expression_span"  class="expression_span">
                                    	<a id="A5"  class="expression_a" onclick="clickExpression(this, event)"><img  src="../expression/smile.png"/></a>  
                                	</span> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:iterator>
