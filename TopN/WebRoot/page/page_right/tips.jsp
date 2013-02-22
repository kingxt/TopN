  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/page/util/common.jsp"%>
  <!--中间内容显示开始-->
         
                <!--各种提示-->
                    <script>
                    	var acceptFreind = new Object(); 
                    	var selectedItem;
 
                        function reSizeContent(item){
                        	
                        }
                        
                        function selectAll(id) {
                            var item = $(id);
                            var itemText = item.find(".tips_allselected_span");
                            var itemchk = item.find(".tips_selectedall_chk");
                            if (itemchk.attr("checked") == true) {
                                $(id).find(".content_tips_chk").attr("checked", true);
                                itemText.html("取消选中");
                            }
                            else {
                                $(id).find(".content_tips_chk").attr("checked", false);
                                itemText.html("全部选中");
                            }
                        }
                      
                        $(document).ready(function(){
				          	$("#group_div").click(function(event){
				          		 event.stopPropagation();          	
				            });
							$(document).click(function () {
				             	$("#group_div").hide();
				         	});	
				         	 
				         	 $(".tips_type_a").click(function () {
                                $(this).parents("li").first().find(".tips_show_div").toggle();
                                var selectedItem = $(this).name;
                            });
                          	openTree();
     					});
     					
     					function showAdd(item, event){
							$("#group_div").appendTo($(item).parents("#add_friend_span").first()).show();		
							stopPropagation(event);    // 其它浏览器下阻止冒泡  
						}
                       
                        function refuse(item) {
                        
                        }
                        
                        function openTree(){
                        	 if($("#tipSelectedItem").val() == 1){
                           		$("#friend_tip").parents("li").first().find(".tips_show_div").toggle();
                           	 }else if($("#tipSelectedItem").val() == 2){
                           		$("#system_tip").parents("li").first().find(".tips_show_div").toggle();
                           	 }
                        }
                     
						//改变其他选项	
						function changeOther(item){	
							var styles = $(item).parents("#group_div").find(".friend_type");	
				            styles.attr("checked", false);
							item.checked = 'checked';	
						}
						
						//接受朋友
						function acceptFriend(item){
							var selectedType = $(item).parents("#group_div").first().find("[class='friend_type'][checked]");	
							if(selectedType == null)return;
							var type = selectedType.attr("name");		
							var fid = $(item).parents("#add_friend_span").first().find("#friendId").attr("name");
							var imgsrc = $(item).parents("#friends").first().find("#myphoto").attr("src");
							var friendName = $(item).parents("#friends").first().find("#friendName").attr("name");
							
							var params = {
								'friendId' : fid,
								'friendType' : type,
								'flag' : 2
							};
						
							new TopNAjax().ajax({
								cache : false,
								loading : false,
								data : params,
								url: 'realAddFriend.action',
								disabledButtonId : 'acceptButton',
								success: function (msg) {
									showMessage("好友","添加好友成功");
									$(item).parents("#friends").first().hide();
									refreshTips();
									addNewFriend(fid, friendName, type, imgsrc);
						        }
							});
						}
						
						//拒绝朋友
						function reject(obj){
							acceptFreind.id = obj.id;
							selectedItem = $(obj).parents("#friends").first();
							showAskBox("提示", "您要拒绝添加" + obj.name + "为好友吗？", re, null, "取消");
						}
						
						function re(){
							var params = {
								'friendId' : acceptFreind.id,
								'flag' : 1 //拒绝标识
							};

							$.post('realAddFriend.action', params, function cbf(msg) {
								selectedItem.remove();
								refreshTips();
							});
						}
						//删除多条记录
						function deletAllFriend(item){
							var str = "";
							$(item).find(".content_tips_chk").each(function(){
								if($(this).attr("checked") == true) {
      								str = str + $(this).val() + " ";
   								}
							});
							if(jQuery.trim(str) == ''){
								return;
							}
							showAskBox("提示", "您确定要删除选中的全部吗？", function(){
								params={delIds : str};
								$.post('deleteAllSelectedFriend.action', params, function cbf(msg) {
									$(item).find("#friends").each(function(){
										if($(this).attr("checked") == true) {
		      								$(this).parent().parent().remove();
		   								}
									});
									refreshTips();
								});							
							}, null, "取消");
						}
						
						//删除一条系统记录
						function delSystemTips(item){
							params={tipsIdStr : item.id};
							$.post('delSystemTips.action', params, function cbf(msg) {
								$(item).parent().parent().remove();
								refreshTips();	
							});
						}
						///删除多条系统记录
						function delAllSystemTips(item){
							var str = "";
							$(item).find(".content_tips_chk").each(function(){
								if($(this).attr("checked") == true) {
      								str = str + $(this).val() + " ";
   								}
							});
							if(jQuery.trim(str) == ''){
								return;
							}
							params={tipsIdStr : str};
							$.post('delSystemTips.action', params, function cbf(msg) {
								$(item).find(".content_tips_chk").each(function(){
									if($(this).attr("checked") == true) {
	      								$(this).parent().parent().remove();
	   								}
								});
								refreshTips();
							});	
						}
						//加载tip
						function loadTips(obj){
							if(obj == null || obj == undefined){
								return;
							}
							var str = "#tip" + obj;
							var se = $(str).val();
							if(Number(se) == Number(obj)){
								return;
							}
							
							var data = "tipSelectedItem="+obj;
							$.ajax( {
								type : "post",
								url : 'loadTips.action',
								cache : false,
								data:data,
								error : function() {
									alert('加载页面时出错！');
								},
								success : function(msg) {
									if(obj == 1){
										$("#tip1").val("1");
										$("#friend_apply_ul").append(msg);		
									}else	
									if(obj == 2){
										$("#tip2").val("2");
										$("#system_apply_ul").append(msg);	
									}	
								}
							});
						}
						/**
						 * 刷新提示
						 * @return
						 */
						function refreshTips(){
							new TopNAjax().ajax({
								type: "get",
								cache : false,
								loading : false,
								url: 'refreshTips.action',
								success: function (msg) {
									$("#tip_friend").html(msg);
						        }
							});
						}
                    </script>
                   <input type="hidden" id="tipSelectedItem" value="<s:property value="#tipSelectedItem"/>"/>
                   <input type="hidden" id="tip1" value="<s:property value="#tipSelectedItem"/>"/>
                   <input type="hidden" id="tip2" value="<s:property value="#tipSelectedItem"/>"/>
                   <input type="hidden" id="tip3" value="<s:property value="#tipSelectedItem"/>"/>
                   <input type="hidden" id="tip4" value="<s:property value="#tipSelectedItem"/>"/>
                   <div class="tips_div" id="tips_div">
                        <div  class="tips_contraint_div">
                               <ul>
                                    <li>
                                        <a class="tips_type_a" id="system_tip" onclick="loadTips(2)">系统提示</a>
										<div class="tips_show_div">                                       
                                        <ul id="system_apply_ul">
                                                <li class="tips_show_li">
                                                    <div class="tips_operations_div">
                                                                <div class="tips_allselected_div"><input  type="checkbox" class="tips_selectedall_chk"  onclick="selectAll('#system_apply_ul')" /></div>
                                                                <span  class="tips_allselected_span">全部选中 </span>       
                                                                <span><a onclick="delAllSystemTips('#system_apply_ul')">查收</a></span>
                                                     </div>
                                                </li>    
                                             	<jsp:include page="system_tips.jsp"></jsp:include>
                                        </ul>
                                        </div>
                                     </li>
                               </ul>
                        </div>
                        <div class="tips_contraint_div">
                             <ul>
                                    <li>
                                         <a class="tips_type_a"  id="friend_tip" onclick="loadTips(1)">好友申请</a>
                                        <div class="tips_show_div">  
                                        <ul id="friend_apply_ul">
                                        		<!-- 
                                                <li class="tips_show_li">
                                                     <div class="tips_operations_div">
                                                                <div class="tips_allselected_div"><input  type="checkbox" class="tips_selectedall_chk"  onclick="selectAll('#friend_apply_ul')"/></div>
                                                                <span  class="tips_allselected_span">全部选中 </span>       
                                                                 <span><a onclick="deletAllFriend('#friend_apply_ul')">删除选中</a></span>
                                                                 
                                                     </div>
                                               </li>      
												 -->
                                               <jsp:include page="friend_tips.jsp"></jsp:include>
                                        </ul>
                                        </div>
                                     </li>
                               </ul>
                        </div>    
                       
                         <div class="group_div" id="group_div">
                           <p>
							<input type=checkbox value="好友" name="1" onclick="changeOther(this)" class="friend_type"/>好友
						  </p>
							<p>
							<input type=checkbox value="密友" name="2" onclick="changeOther(this)" class="friend_type"/>密友
							</p>
							<input id="acceptButton" type="button" value="确定" class="group_submit_button" onclick="acceptFriend(this)"/>
                         </div>
                   </div>
                 <!--各种提示-->
 
         