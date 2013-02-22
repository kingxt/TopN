<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<script type="text/javascript" src="../scripts/topn.jquery.pagination.js"></script>
<script><!--
		// 分页函数, 这里其实一个对象
	var optFriendFriends = {
			// 点击页码调用 函数
			callback : pageselectFriendFriends,
			// 默认显示 页数
			current_page : 0,
			// 每一页显示多少条纪录，最大值为总的记录条数
			items_per_page : 20,
			// 一页显示 个数
			num_display_entries : 5,
			prev_text : "上页",
			next_text : "下页",
			// 是否永远显示下一页
			next_show_always : true,
			prev_show_always : true,
			// 省略号后显示 个数
			num_edge_entries : 2
	};
		
	 $(document).ready(function(){
          	$("#group_div").click(function(event){
          		 event.stopPropagation();          
          	});         	
			
			$(document).click(function () {
             	$("#group_div").hide();
         	 });	
            optFriendFriends.callback = pageselectFriendFriends;
		    $("#Pagination").pagination(<s:property value="#total"/>, optFriendFriends);	 
     });
	
	function pageselectFriendFriends(page_index, optFriendFriends) {
		getFriendFriends(page_index + 1, 'visitorsFriendsPG.action');
		return false;
	}
	
	function getFriendFriends(pageNum, action) {
		var data = $.query.get("personalInfoId");
		var params = {
			'pageNum' : pageNum,
			'personalInfoId' : data
		};
		new TopNAjax().ajax({
			url: action,
			data: params,
			loading : false,
			success: function (msg) {
				$("#search_result_div").html(msg);
	        }
		});
	}
			
		//改变其他选项	
		function changeOther(item){	
			var styles = $(item).parents("#group_div").find(".friend_type");	
            styles.attr("checked", false);
			item.checked = 'checked';	
		}
--></script>
<div class="mainContent_left_div">
	<div class="radius_large_top_div"></div>

	<div id="content_middle_div" class="content_middle_div">
		<!--搜索结果开始-->
		<div id="search_result_div" class="search_result_div">
			<jsp:include page="visitor_friend.jsp"></jsp:include>
		</div>

		<div class="group_div" id="group_div">
			
			<p>
				<input type=checkbox value="好友" name="1" class="friend_type"
					onchange="changeOther(this)" />
				好友
			</p>
			<p>
				<input type=checkbox value="密友" name="2" class="friend_type"
					onchange="changeOther(this)" />
				密友
			</p>

			<input type="button" value="确定" class="group_submit_button"
				onclick="addFriend(this)" />
			
		</div>
		<div id="Pagination" class="pagination_div">
		</div>
	</div>

	<div class="radius_large_bottom_div"></div>
</div>

<div class="mainContent_right_div">
	<jsp:include page="../page_right/visitor_right.jsp"></jsp:include>
</div>
