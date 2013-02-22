<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>

<script><!--
	
	
	 $(document).ready(function(){
          	$("#group_div").click(function(event){
          		 event.stopPropagation();          	
          	});         	
			$(".add_friend_span>a").click(function (event) {
				$("#group_div").appendTo($(this).parents(".add_friend_span").first()).show();	
		        event.stopPropagation();     // 其它浏览器下阻止冒泡  
			});		
			$(document).click(function () {
             	$("#group_div").hide();
         	});	
     });
	
			
		//改变其他选项	
		function changeOther(item){	
			var styles = $(item).parents("#group_div").find(".friend_type");	
            styles.attr("checked", false);
			item.checked = 'checked';	
		}
--></script>
<!--搜索结果开始-->
		<div id="search_result_div" class="search_result_div">
			<s:if test="#ftos == null || #ftos.size() == 0">
				<center style=" font-size: 12px;padding-top:160px">
					系统搜索不到和您输入匹配的数据，您可以修改搜索条件
				</center>
			</s:if>
			<s:iterator value="#ftos" var="ft">
				<ul class="search_detial_ul">
					<li>
						<div class="search_photo_div">
							<a href="visitor.action?personalInfoId=<s:property value="#ft.id"/>" target="_black"> <img src="<s:property value="#ft.url"/>" /> </a>
						</div>
						<div class="search_info_div">
							<ul>
								<li>
									<span  class="search_basic_span"><a href="visitor.action?personalInfoId=<s:property value="#ft.id"/>" target="_black"><s:property value="#ft.name" /></a>
									</span>
									<span class="add_friend_span"><a id="friendId" name="<s:property value="#ft.id"/>">加为好友</a> </span>
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
		</div>

		<div class="group_div" id="group_div">
			<p>
				<input type=checkbox value="好友" name="1" onclick="changeOther(this)" class="friend_type"/>
				好友
			</p>
			<p>
				<input type=checkbox value="密友" name="2" onclick="changeOther(this)" class="friend_type"/>
				密友
			</p>
			
			<input type="button" value="确定" class="group_submit_button" onclick="addFriend(this)"/>
		</div>