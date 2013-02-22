<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>


<div class="mainContent_left_div">
	<div class="radius_large_top_div"></div>

	<div id="content_middle_div" class="content_middle_div">
		<div class="middle_top_div" id="middle_top_div">
			<div class="mood_div">
				<div style="float:left; width:68px; height:100px;"></div>
				<div class="status_submit_div">
					<div class="char_count_div"><p class="char_count_p"><span>您还能输入</span><span  class="char_count_span">140</span><span>个字</span></p></div>
					<div class="status_edit_div">
						<textarea id="moodText" name="moodText" cols="20" rows="2" class="status_area" onkeyup="checkMoodArea(this)"></textarea>
					</div>
				</div>
				<div class="operation_container_div">
					<div class="message_newoperation_div">
						<span id="expression_span">
                                 
                                    <a id="expression_a"  class="status_expression_a" onclick="$.expression.statusExpressionInit(this,'expression_span', event);"><img  src="../expression/smile.png"/>表情</a>                                   
                                </span>
					</div>
					<div class="message_submit_div">
						<input type="button" id="leave_message_button" class="leave_message_button" onclick="$.expression.leaveMsg();" name="leaveMsg"/>
					</div>
				</div>
			</div>
		</div>
		
		<div id="middle_status_show_div" class="middle_status_show_div">
			<jsp:include page="visitor_status.jsp"></jsp:include>
		</div>
	</div>

	<div class="radius_large_bottom_div"></div>
</div>
<!--中间内容显示结束-->
<div class="mainContent_right_div">
	<jsp:include page="../page_right/visitor_right.jsp"></jsp:include>
</div>

<script type="text/javascript" src="../scripts/topn.status.js"></script>