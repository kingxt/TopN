<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
		<script type="text/javascript" src="../scripts/topn.jquery.pagination.js"></script>
	
		<script type="text/javascript">
			
	<!--
		// 分页函数, 这里其实一个对象
		var optMoodPagination = {
			// 点击页码调用 函数
			callback : pageselectMyMood,
			// 默认显示 页数
			current_page : 0,
			// 每一页显示多少条纪录，最大值为总的记录条数
			items_per_page : 10,
			// 一页显示 个数
			num_display_entries : 5,
			prev_text : "上页",
			next_text : "下页",
			// 是否永远显示下一页
			next_show_always : true,
			prev_show_always : true,
			// 省略号后显示 个数
			num_edge_entries : 2
		}
		
		$(document).ready(function(){
	     	$("#ownerMoodPagination").pagination(<s:property  value="#sft"/>, optMoodPagination);
	    });
	    
	    
	    function pageselectMyMood(page_index,optMoodPagination){
	        getMyMoodPage(page_index+1);
	        return false;
	    }
	    
	    
	    
	    function getMyMoodPage(pagenum){
	    	var parmt ="pageNum="+pagenum;
	    	$.ajax({
					type : "post",
					url : 'getMyMoodPage.action',
					cache : false,			
					data:parmt,
					success : function(msg) {
						$("#middle_status_show_div").empty().append(msg);
						
	     				$("#ownerMoodPagination").css("display", "inline-block");
	     				
					}
			});
	    }
	//-->
	</script>


		<div class="mainContent_left_div">
			<div class="radius_large_top_div"></div>

			<div id="content_middle_div" class="content_middle_div">
				<h5 class="title_h5">
					我的心情
				</h5>
				<div id="middle_status_show_div" class="middle_status_show_div">
					<center id="loading">
						<img src="../pictures/loading_page.gif" width="500" />
					</center>
					<!--						<jsp:include page="my_mood_page_status.jsp"></jsp:include>-->

				</div>
				<s:if test="#sft > 0">
					<div id="ownerMoodPagination" class="pagination_div"
						style="display: none;">
					</div>
				</s:if>
			</div>

			<div class="radius_large_bottom_div"></div>
		</div>
		<!--中间内容显示结束-->
		<div class="mainContent_right_div">
			<jsp:include page="../page_right/right.jsp"></jsp:include>
		</div>
	
