<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<s:iterator value="#cs" var="c">
	<li>
		<a onclick="loadDistrictByCity(this)"
			id="<s:property value="#c.cityId"/>"><s:property value="#c.name" />
		</a>
	</li>
</s:iterator>