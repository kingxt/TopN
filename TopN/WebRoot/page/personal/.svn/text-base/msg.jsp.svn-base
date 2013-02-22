<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>

<s:iterator value="#frs" var="fr" status="st">
	<div id="Div1" class="status_div">
		<div class="status_show_div">
			<div class="photo_div">
				<a
					href="visitor.action?personalInfoId=<s:property value="#fr.publishId"/>"
					target="_black"> <img class="photo_img"
						src="<s:property value="#fr.url"/>" /> </a>
			</div>
			<div class="status_content_div">
				<ul>
					<li  class="publish_content_li">
						<div class="mood_content_div">
						<a
							href="visitor.action?personalInfoId=<s:property value="#fr.publishId"/>"
							target="_black" target="_blank" class="replay_name"><s:property
								value="#fr.name" />: </a>
						<span><s:property value="#fr.content" escape="false"/>
						</span>
						</div>
                                      <div class="mood_del_div">
                                             <a class="del_mood_a" onclick="deleteLeaveMsg(this);" id="<s:property value="#fr.leaveMsgId"/>">x</a>
                                     </div>
					</li>
					<li class="publish_date_li">
						<span>发布时间：</span>
						<s:if test="#fr.total > 0">
							<span> <a class="reply_operate_a" onclick="openLeaveMsgReplies(this)" name="<s:property value="#fr.leaveMsgId"/>">打开<s:property value="#fr.total"/>条回复记录</a></span>
						</s:if>	
						
						<span><s:property value="#fr.publishTime" />
						</span>
					</li>
				</ul>
			</div>
		</div>
		<div class="reply_detials_div">
			<div id="all_replies">
			</div>
			<div class="reply_add_div" onclick="focusArea(this,event)">
				<p>
					<span class="reply_photo_span"> <a href="#"> <img
								class="reply_photo_img"
								src="<s:property value="#pi.photo"/>" /> </a> </span>
					<span> <textarea id="TextArea2" cols="20" rows="2" class="reply_add_area" onkeyup="checkAddArea(this)"></textarea></span>
				</p>
				<div class="reply_operate_div">
					<div class="reply_submit_div">
						<input type="hidden" id="parentId" name="parentId"
							value="<s:property value="#fr.publishId"/>">
						<input type="hidden" id="leaveMsgId" name="leaveMsgId"
							value="<s:property value="#fr.leaveMsgId"/>">
						<input type="hidden" name="orders" id="orders"
							value="<s:property value="#fr.total"/>">
						<input type="button" class="reply_button" id="reply_button<s:property value="#fr.leaveMsgId"/>"
							onclick="clickLeaveMsgButton(this)" />
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
<s:if test="#frs.size() == 0">
		<center>
			<span style=" font-size: 12px">最近没有人留言</span>
		</center>
</s:if>
<script type="text/javascript" src="../scripts/topn.status.js"></script>