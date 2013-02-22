	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ include file="/page/util/common.jsp"%>
		<script>
	    	$(document).ready(function(){
				$(".add_friend_span>a").click(function (event) {
					$("#group_div").appendTo($(this).parents(".add_friend_span").first()).show();	
			        event.stopPropagation();     // 其它浏览器下阻止冒泡  
				});		
	    	});
    	</script>
			<h5>
				TA的好友
			</h5>
			<s:iterator value="#ftos" var="ft">
				<ul class="search_detial_ul">
					<li>
						<div class="search_photo_div">
							<a
								href="visitor.action?personalInfoId=<s:property value="#ft.id"/>"
								target="_black"> <img src="<s:property value="#ft.url"/>" />
							</a>
						</div>
						<div class="search_info_div">
							<ul>
								<li>
									<span class="search_basic_span"><a
										href="visitor.action?personalInfoId=<s:property value="#ft.id"/>"
										target="_black"><s:property value="#ft.name" />
									</a> </span>
									<span class="add_friend_span"><a id="friendId"
										name="<s:property value="#ft.id"/>">加为好友</a> </span>
								</li>
								<li>
									简介:
									<s:property value="#ft.ps" />
								</li>
								<li>
									兴趣:
									<s:property value="#ft.hobby" />
								</li>
								<li>
									学校:
									<s:property value="#ft.school" />
								</li>
							</ul>
						</div>
					</li>
				</ul>
				</s:iterator>