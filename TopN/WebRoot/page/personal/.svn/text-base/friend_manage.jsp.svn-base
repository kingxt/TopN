<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<script type="text/javascript" src="../scripts/topn.jquery.pagination.js"></script>
<div class="mainContent_left_div">
	<div class="radius_large_top_div"></div>
	<!--中间内容开始-->
	<div id="content_middle_div" class="content_middle_div">
		<script>
	var optFriendsManager = {
			// 点击页码调用 函数
			callback : pageselectMyFriend,
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
         	
         	 optFriendsManager.callback = pageselectMyFriend;
     		 $("#FriendsPagination").pagination(<s:property  value="#total"/>, optFriendsManager);
     });

	function pageselectMyFriend(page_index,optFriendsManager){
        loadFriendByType(page_index+1, $("#friendTypeHidden").val());
        return false;
    } 
    
    function loadFriendByType(pagenum, type){
    	var params = {
    		'pageNum' : pagenum,
    		'friendType' : type
    	};
    	new TopNAjax().ajax({
				cache: false,
				url: 'loadFriendByType.action',
				loading: true,			
				data : params,	
				loadingAreaId : 'friend_manage_div',
				success: function (msg) {
					$("#friend_manage_div").empty().append(msg);
		        }
		});
    }
	    //删除好友
	    function removeFriend(obj){
	    	var params={   
		        'friendId':obj.id
	    	};  
	    	showAskBox("提示", "确定删除朋友"+obj.name+"吗？", function(){
		    	$.post( 
		    		'removeFriend.action',
		    		params,
		    		function cbf(msg) {
		    			$(obj).parents("#friend_li").hide();
		    		}
		    	);
	   		 })
	    }
    
    	//改变其他选项	
		function changeOther(item){
			var styles = $(item).parents("#group_div").find(".friend_type");	
            styles.attr("checked", false);
			item.checked = 'checked';
		}
		
		//修改朋友类型
		function modifyFriendType(item){
			//如果是想修改类型，必须与本身的类型不一致
			var selectedType = $(item).parents("#group_div").find("[class='friend_type'][checked]");			
			if(selectedType == null)return;
			var type = selectedType.attr("name");
			if($("#friendTypeHidden").val() == type){
				return;
			}
			
			var url = 'modifyFriendType.action';   
			
	        var params={   
	            'friendId' : $(item).parents(".friend_grouping_span").find("#friendId").attr("name"),
	            'friendType' : type
	        };  
	      
	        new TopNAjax().ajax({
				cache: false,
				url: url,
				loading: false,			
				data : params,	
				success: function (msg) {
					$(item).parents("#friend_li").hide();
		        }
			});
		}
</script>
		<!--好友管理开始-->
		<div class="kind_select_div">
			<ul>
				<li>
					<a onclick="manageFriend(2)">密友</a>
				</li>
				<li>
					|
				</li>
				<li>
					<a onclick="manageFriend(1)">好友</a>
				</li>
			</ul>
		</div>
		<input id="friendTypeHidden" type="hidden"
			value="<s:property value="friendType"/>">
		<div class="friend_manage_div" id="friend_manage_div">
			
		</div>
		<div class="group_div" id="group_div">
			<p>
				<input type="checkbox" value="密友" name="2" id="miyou" class="friend_type"
					onclick="changeOther(this)" />
				密友
			</p>
			<p>
				<input type="checkbox" value="好友" name="1" id="haoyou" class="friend_type"
					onclick="changeOther(this)" />
				好友
			</p>
			<input type="button" class="group_submit_button" value="确定"
				onclick="modifyFriendType(this)" />
		</div>
		<!--好友管理结束-->
		<s:if test="#sft.size > 0">
			<div id="FriendsPagination" class="pagination_div"></div>
		</s:if>


	</div>

	<div class="radius_large_bottom_div"></div>
</div>
<!--中间内容显示结束-->

<!--右边内容开始-->
<div class="mainContent_right_div">
	<jsp:include page="../page_right/right.jsp"></jsp:include>
</div>
