<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<!--三层模式-->
<!--中间内容显示开始-->
<input type="hidden" id="personImageURL"
	value="<s:property value="#pi.photo"/>" />
<input type="hidden" id="personName"
	value="<s:property value="#pi.nickName"/>" />
<input type="hidden" id="personId"
	value="<s:property value="#pi.personalInfoId"/>" />
<input type="hidden" id="pageNum" value="2" />
<input type="hidden" id="moodBeginTime"
	value="<s:property value="#moodBeginTime"/>" />
	
<div class="mainContent_left_div">
	<div class="radius_large_top_div"></div>
	<div id="content_middle_div" class="content_middle_div">
		<div class="middle_top_div" id="middle_top_div">
			<div class="mood_div">
				<div style="float:left; width:68px; height:100px;"></div>
				
				<div class="status_submit_div">
					<div class="char_count_div"><p class="char_count_p"><span>您还能输入</span><span  class="char_count_span">140</span><span>个字</span></p></div>
					<div class="status_edit_div">
					<textarea id="moodText" name="moodText" cols="20" rows="2" class="status_area" onkeyup="checkMoodArea(this);"></textarea>
					</div>
				</div>
				<div class="operation_container_div">
					<div class="status_newoperation_div">
						<span id="expression_span">
                                 
                                    <a id="expression_a"  class="status_expression_a" onclick="$.expression.statusExpressionInit(this,'expression_span', event);"><img  src="../expression/smile.png"/>表情</a>                                   
                                </span>
					</div>
					<div class="operation_submit_div">
						<input type="button" class="publish_button" id="publish_button" onclick="$.expression.publish_lastedStatus();"/>
					</div>
				</div>
			</div>
			
		</div>
		<h5 class="title_h5">
			最新状态
		</h5>
		<div id="middle_status_show_div" class="middle_status_show_div">
			
		<!-- 这里包含的好处是代码复用 -->
			<jsp:include page="personal/more_status.jsp"></jsp:include>
		</div>
		<s:if test="#frs.size() >= 30">
			<div class="more_status_div">
				<a id="more_status_a" onclick=
	getMoreStatus();
>显示更多</a>
			</div>
		</s:if>
	</div>

	<!--双层模式-->

	<div class="radius_large_bottom_div"></div>
</div>
<div class="mainContent_right_div">
<jsp:include page="page_right/right.jsp"></jsp:include>
</div>

