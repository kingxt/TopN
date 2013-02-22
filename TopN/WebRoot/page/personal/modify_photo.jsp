<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/page/util/common.jsp"%>
<script>
              $(document).ready(function () {
              		$('#upload_headPortrait_div').html("<center>加载中</center>");
              		$('#upload_headPortrait_div').flash( {
						swf : '../swf/TopN/PicCut.swf',
						height : 404,
						width : 501		
					});
              });
</script>  
		<!--三层模式-->
		<!--中间内容显示开始-->
		<div class="mainContent_left_div">
			<div class="radius_large_top_div"></div>

			<div id="content_middle_div" class="content_middle_div">
				<div id="update_div" class="update_div">
					<h5 class="title_h5">
					修改头像
					</h5>
					<div class="direct_photo_div">
						<div class="upload_headPortrait_div" id="upload_headPortrait_div">
							<!-- 异步加载上传头像 -->
						</div>
					</div>
				</div>
				<!-- 中间的内容 -->
			</div>
			<div class="radius_large_bottom_div"></div>
	 </div>
		

		<!--中间内容显示结束-->
		<div class="mainContent_right_div">
			<jsp:include page="../page_right/right.jsp"></jsp:include>
		</div>