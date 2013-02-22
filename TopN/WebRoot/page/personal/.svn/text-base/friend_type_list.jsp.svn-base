<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/page/util/common.jsp"%>
    <script>
    	$(document).ready(function(){
          	$(".friend_grouping_span>a").click(function (event) {
				$("#group_div").appendTo($(this).parents(".friend_grouping_span").first()).show();
		        event.stopPropagation();     // 其它浏览器下阻止冒泡  
			});	
    	});
    </script>
<div class="close_friend_div">
				<h5>
					<s:if test="#friendType == 1">我的好友</s:if>
					<s:if test="#friendType == 2">我的密友</s:if>
				</h5>
				<ul class="close_friend_ul">
					<s:iterator value="#sft" var="ft">
					<ul class="search_detial_ul">
						<li id="friend_li">
						
							<div class="friend_img_div">
								<a href="visitor.action?personalInfoId=<s:property value="#ft.id"/>" target="_black"> <img
										src="<s:property value="#ft.url"/>" /> </a>
							</div>
							<div class="friend_manage_name_div">
								<a href="visitor.action?personalInfoId=<s:property value="#ft.id"/>" target="_black"><s:property value="#ft.name"/></a>
								<s:property value="#ft.school"/>
							</div>
							
							<div class="friend_grouping_div">
								<span class="friend_grouping_span"><a name="<s:property value="#ft.id"/>"
									 id="friendId">分组</a>
									<input type="hidden" id="friendId" value="<s:property value="#ft.id"/>">
								</span>
							</div>
							 <div  class="friend_delete_div">
	                             <span>
	                               <a class="del_friend_a" onclick="removeFriend(this);" name="<s:property value="#ft.name"/>" id="<s:property value="#ft.id"/>">x</a>
	                             </span>
	                        </div>
							
						</li>
					</ul>
					</s:iterator>
				</ul>
			</div>