<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>	
<div class="recently_visitor_div" id="recently_visitor_div">
                    <div class="recently_title_div">
                        <p>最近访客</p>
                    </div>
                        <ul  id="recently_visitor_ul">
                        	<s:iterator value="#rvt" var="rv">
	                             <li>
	                                 <div class="visitor_detial_div">
	                                        <div class="visitor_photo_div"><a href="visitor.action?personalInfoId=<s:property value="#rv.visitorId"/>"
								target="_black"><img  src="<s:property value="#rv.url"/>"/></a></div>
	                                        <div class="visitor_remark_div">
	                                             <a href="visitor.action?personalInfoId=<s:property value="#rv.visitorId"/>"
								target="_black"> <p><s:property value="#rv.name"/></p></a>            
	                                        </div>
	                                 </div>
	                             </li>
                             </s:iterator>
                        </ul>
</div>