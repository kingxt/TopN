<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ include file="/page/util/common.jsp"%>
 <script>
	   		$(document).ready(function(){
			 function resolveMinibar()
			 {
			 	if($.browser.msie && $.browser.version=="6.0")
				{
					InitPosition($("#mini_bar_div"),1);
						$(window).resize(function(){			
							setTimeout(function(){InitPosition($("#mini_bar_div"),1);},400);
						});
						$(window).scroll(function(){
							setTimeout(function(){InitPosition($("#mini_bar_div"),1);},400);
						});	
				}	
				else
				{
					$("#mini_bar_div").css("position","fixed").css("bottom","0px").css("right","15px").css("display","block");
				}	
			 }
				resolveMinibar();
		  		resolvePngInIe6();
			
			});
	   </script>


<script>
	
</script>
<div id="mini_bar_div" class="mini_bar_div"> 
          <ul>
             <li class="mini_bar_li">
                  <a onclick="openChatingDialog()" class="communication_window_a"  href="#">聊天窗口</a>
             </li>
             <li class="mini_bar_li">                
                	<a id="friend_list_a" href="#" class="friend_list_link_a"> 好友列表</a>  
              </li>
          </ul>
</div>  
<div class="friend_div" id="friend_div">
	 <div>
				<table class="quick_search_tb">
					<tr>
						<td><img src="../pictures/quick_search.png" /></td>
						<td>
							<input type="text" class="friend_quick_search_i" id="friend_quick_search_i"/>
						</td>
					</tr>
				</table>
	</div>
	
	<div id="friend_list_div" class="friend_list_div">
		<ul class="friend_list_ul">
			
		</ul>
	</div>
</div>
<!--好友列表结束-->
<!--实时聊天开始-->
<div  id="communication_div"  class="communication_wrapOut_div"> 
                <div class="communication_wrapin_div" >
                <div  class="communication_bar_div" > 
                <div class="communication_title"><span id="title_span">对方即可</span></div>
                <font size="3" style="color: red"><a class="communication_wrap_close_div" id="communicationClose_a" onclick="displayComm()">x</a></font>
            </div>
                <div  class="communication_wrapBody_div">
                <div  class="communication_div">
						<div class="communication_chat_div">
							<div class="communication_to_photo_div">
								<img id="friendPhoto" src='<s:property value="#pi.photo"/>'>
							</div>
							<div class="communication_to_chat_div">
								   
								<div class="communication_to_content_div">
									<div class="communication_to_content_container_div" id="communication_to_content_container_div">
										<ul id="communication_to_content_ul">
						
										</ul>
									</div>	
								</div>
								
							</div>
						</div>
						<div class="communication_chat_div">
							<div class="communication_from_edit_div">
								
								<div class="edit_content_div">
									<font size="1"><textarea class="edit_content_area" id="edit_content_area"></textarea></font>
								</div>
								<div class="communication_operation_div">
									<div class="communication_sender_div">
										<font size="1"><input type="button" class="communication_sender_btn" value="发送" id="communication_sender_btn" name=""></font>
									</div>
								</div>
							</div>
							<div class="communication_from_photo_div">
								<font size="1"><img src='<s:property value="#pi.photo"/>'></font>
							</div>
						</div>
					</div>
			</div>
                </div>
               </div>

<div class="communication_right_div">

</div>
