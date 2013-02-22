<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<script>
	//添加朋友
		function makeFriend(item){
			var type = 1;
			var url = 'addFriend.action';   
	        var params={   
	            'friendId' : $(item).attr("id"),
	            'friendType' : type
	        };  
	        new TopNAjax().ajax({
				cache: false,
				url: url,
				loading: false,			
				data : params,	
				success: function (msg) {
					$(item).html("已发出好友申请").css("color","#666").css("cursor","default");		
		        }
			});
		}
</script>
<div class="mainContent_left_div">
	<div class="radius_large_top_div"></div>
	<!--中间内容开始-->
	<div id="content_middle_div" class="content_middle_div">
		<s:if test="#coto.size() > 0">
			<h5 class="re_h5">
				可能是你的大学同学
			</h5>
			<div class="recommendation_div">
				<ul class="recommendation_ul">
					<s:iterator value="#coto" var="co">
						<li>
							<p>
								<img src="<s:property value="#co.url"/>" />
							</p>
							<p class="re_operation_p">
								<a href="tourist.action?personalInfoId=<s:property value="#co.id"/>" target="_blank"><s:property value="#co.name"/></a>
								<a id="<s:property value="#co.id"/>" onclick="makeFriend(this);" class="re_add_friend_a">加为好友</a>
							</p>
						</li>
					</s:iterator>
				</ul>
			</div>
		</s:if>
		<s:if test="#hito.size() > 0">
			<h5 class="re_highschool_h5 re_h5">
				可能是你的高中同学
			</h5>
			<div class="recommendation_div">
				<ul class="recommendation_ul">
					<s:iterator value="#hito" var="hi">
						<li>
							<p>
								<img src="<s:property value="#hi.url"/>" />
							</p>
							<p class="re_operation_p">
								<a href="#" target="_blank"><s:property value="#hi.name"/></a>
								<a id="<s:property value="#hi.id"/>" onclick="makeFriend(this);" class="re_add_friend_a">加为好友</a>
							</p>
						</li>
					</s:iterator>
				</ul>
			</div>
		</s:if>
		<s:if test="#hito.size() == 0 && #coto.size() == 0">
			
			<center>
				<s:if test="pi.highSchool == '' || pi.college == ''">
					<span style=" font-size: 12px; padding-top: 100px">请完善个人信息，例如：大学，高中</span>
				</s:if>
				<s:else>
					<span style=" font-size: 12px; padding-top: 100px">您的信息越完整,我们将为您找到更多好友</span>
				</s:else>
			</center>
			
		</s:if>
	</div>
	
	<div class="radius_large_bottom_div"></div>
</div>
<div class="mainContent_right_div">
<jsp:include page="../page_right/right.jsp"></jsp:include>
</div>