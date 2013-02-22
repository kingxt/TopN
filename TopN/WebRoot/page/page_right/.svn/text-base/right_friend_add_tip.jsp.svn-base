<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<script type="text/javascript">
	function realAddFriend(obj) {
		showAskBox("提示", "您将添加" + obj.name + "为好友", todb(obj.id, 2), todb(
				obj.id, 1), "拒绝");
	}

	function todb(friendId, flag) {
		var params = {
			'friendId' : friendId,
			'flag' : flag
		};
		$.post('realAddFriend.action', params, function cbf(msg) {
		});
	}
	
	function toTips(obj){
		var params = {
			'tipSelectedItem' : obj
		}
		 new TopNAjax().ajax({
    		url: 'toTips.action',
    		data: params,
    		async : true,
    		loading : true,
    		loadingAreaId : 'content_middle_div',
    		success: function (msg) {
           	 	if(obj == 3){
					$("#mood_tips_div").html(" &nbsp;您有 0 个心情回复");
				}else if(obj == 4){
					$("#leave_msg_div").html(" &nbsp;您有 0 条留言");
				}
				$("#content_middle_div").empty().append(msg);
            } 
    	});
	}
	//-->
</script>
<div class="all_tips_div">
	<ul>
			<li><a  class="system_tips_a" href="javascript:toTips(1)">
				
						&nbsp;您有<s:if test="#tct.friendTip > 0">
						<SPAN  style="color: red;" id="friend_tips_id"><s:property value="#tct.friendTip"/></SPAN>
						</s:if>
						<s:else><s:property value="#tct.friendTip"/></s:else>
						个好友申请
				</a>
			</li>
			<li>
				<a  class="friend_tips_a" href="javascript:toTips(2)">
						&nbsp;您有
						<s:if test="#tct.systemTip > 0">
							<SPAN  style="color: red;" id="system_tips_id"><s:property value="#tct.systemTip"/></SPAN>
						</s:if>
						<s:else>
							<s:property value="#tct.systemTip"/>
						</s:else>
						个系统消息
				</a>				
			</li>
			<li>
				 <a  class="status_tips_a" href="javascript:toTips(3)" id="mood_tips_div">
				 		&nbsp;您有
				 		<s:if test="#tct.moodTip > 0">
							<SPAN  style="color: red;"><s:property value="#tct.moodTip"/></SPAN>
						</s:if>
						<s:else>
							<s:property value="#tct.moodTip"/>
						</s:else>	
				 		个心情回复
				 </a>				
            </li>
            <li> 
            	<a  class="status_tips_a" href="javascript:toTips(4)" id="leave_msg_div">
            		&nbsp;您有
            		<s:if test="#tct.leaveMsgTip > 0">
							<SPAN  style="color: red;"><s:property value="#tct.leaveMsgTip"/></SPAN>
						</s:if>
						<s:else>
							<s:property value="#tct.leaveMsgTip"/>
						</s:else>	
            		条留言
            	</a>            	
            </li>
	</ul>
</div>
