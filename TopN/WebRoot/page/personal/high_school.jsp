<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<s:iterator value="#hs" var="h">
	<li>
		<a onclick="selectedThis(this)" id="<s:property value="#h.name"/>" name="2"><s:property
				value="#h.name" /> </a>
	</li>
</s:iterator>