<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<script>
	
	
</script>
<s:iterator value="fat" var="fa">
	                                                <li id="friends" class="tips_show_li">
	                                                	<!-- 
	                                                        <div class="tips_chk_div"><input  type="checkbox" value="<s:property value="#fa.friendId"/>" class="content_tips_chk" /></div>
	                                                         -->
	                                                        <div class="tip_photo_div"><img id="myphoto"  src="<s:property value="#fa.url"/>"></div>
	                                                        <div class="tips_apply_div"><s:property value="#fa.name"/>想加入你的圈子</div>
	                                                        <div class="tips_accept_div"><span id="add_friend_span"><a id="friendId" name="<s:property value="#fa.friendId"/>" onclick="showAdd(this, event);">接受</a></span></div>
	                                                        <div class="tips_refuse_div"><a id="friendName" name="<s:property value="#fa.name"/>" id="<s:property value="#fa.friendId"/>" onclick="reject(this);">拒绝</a></div>
	                                                 </li>
</s:iterator>