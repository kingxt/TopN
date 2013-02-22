<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<s:iterator value="stt" var="st">
	                                                         <li id="sttips" class="tips_show_li">
	                                                                <div class="tips_chk_div"><input  type="checkbox" value="<s:property value="#st.tipId"/>" class="content_tips_chk"/></div>
	                                                                <div class="tips_content_div"><s:property value="#st.message"/></div>
	                                                                <div class="tips_accept_div"><a id="<s:property value="#st.tipId"/>" onclick="delSystemTips(this)">查收</a></div>
	                                                         </li>
</s:iterator>