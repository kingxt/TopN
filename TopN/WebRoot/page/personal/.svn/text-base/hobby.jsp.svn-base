<%@ include file="/page/util/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul class="interest_detial_ul" id="<s:property value="typeId"/>">
	 <h5 class="hobby_h5">详细内容</h5>	
	<s:iterator value="#hb" var="h" status="condi">
		<li>
			<input type="checkbox" onchange="changeHobby(this)"
				name="<s:property value="#h.name"/>" />
			<s:property value="#h.name" />
		</li>
	</s:iterator>
</ul>