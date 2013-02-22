<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<s:iterator value="#rts" var="rt">
	<div id="status_reply_id" class="status_reply_div">
		<div class="reply_info_div">
			<div class="reply_photo_div">
				<a href="visitor.action?personalInfoId=<s:property value="#rt.replyPersonId"/>" target="_black"> <img class="reply_photo_img"
						src="<s:property value="#rt.replyPersonURL"/>" /> </a>
			</div>
			<div class="reply_infomation_div">
				<ul>
					<li class="reply_info_li">
						<a class="replay_name" href="visitor.action?personalInfoId=<s:property value="#rt.replyPersonId"/>" target="_black"><s:property value="#rt.replyPersonName" /></a>
						<span class="reply_datetime_span"><s:property
								value="#rt.replyDate" /> </span>
						<s:if test="#rt.replyPersonId == #pi.personalInfoId">						
							<span>
	                             <a class="del_reply_a" onclick="deleteRply(this);">x</a>
	                             <input id="hdreply_message_id" type="hidden"  value="<s:property value="#rt.replyId"/>"/>
	                        </span>
                        </s:if>
					</li>
					<li>
						<span><s:property value="#rt.replyMsg" escape="false"/> </span>
						<span class="operate_span"> <a onclick="reply_click(this)" id="<s:property value="#rt.replyPersonId"/>"> 回复 </a> </span>
					</li>
				</ul>
			</div>
		</div>
		<div class="repay_seperate_div"></div>
		
		</div>
</s:iterator>