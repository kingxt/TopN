﻿﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ include file="/page/util/common.jsp"%>

    <script type="text/javascript" src="../scripts/topn.lightbox.js" ></script>	
 	<script type="text/javascript" src="../scripts/topn.jquery.pagination.js" ></script>
       <script type="text/javascript">
                    var isOut = true;
                  	var pageName="pic_manage";
                  	var ids;
                  	var moveId;
                  	var albumId='<s:property value="#parameters.albumId"/>';
                  	var pageNum;
                  	var isEntry=<s:property value="#parameters.isEntry"/>;
                  	var id;
                  	var cover='<s:property value="#parameters.url"/>';
               
					if(isEntry==2){
						$("#albumDetail").remove();
					}
                   
		           var opt = {		
						// 点击页码调用 函数 
						callback: pageselectCallback,
						// 默认显示 页数 
						current_page:0,
						// 每一页显示多少条纪录，最大值为总的记录条数
						items_per_page:9,
						// 一页显示 个数 
						num_display_entries:5,
						prev_text: "上页",
		        		next_text: "下页",
						// 是否永远显示下一页
						next_show_always: true,
						prev_show_always: true,
						// 省略号后显示 个数 
						num_edge_entries:2
				    } 
                    
                    
                   $(document).ready(function(){
                    	               	
			          	$("#group_div").click(function(event){
			          		 event.stopPropagation();          	});         	
						$(".pic_move_span>a").click(function (event) {
							movePic();
							$("#group_div").appendTo($(this).parents(".pic_move_span").first()).show();				
					       event.stopPropagation();     // 其它浏览器下阻止冒泡  
						});		
						$(document).click(function () {
			             	$("#group_div").hide();
			         	 });
			         	 $("#Pagination").pagination(<s:property value="#parameters.total"/>, opt);	
          			});
                    
                   	function setCheckBoxSelected(check)	{
                   		moveId=check.value;
	                   	$(".checkAlbum").each(function(){    
	                  		if($(this).val()!=check.value&&$(this).attr("checked")==true){
	                  			$(this).attr("checked",false) ;
	                  		}
	                    })
                   	}
                   
                    function managePic() {
                        if ($("#pic_operate_a").attr("class") == "operate_a"){
                            $(".pic_operates_div").show();                          
                            $(".manage_ul .del_chk").attr("checked", false).css("display","inline").show();                          
                            $(".select_all_li").find(".select_all_chk").attr("checked", false);
                            $(".select_all_li").show();
                            $("#pic_operate_a").attr("class","unoperate_a");                     
                            $("#Pagination").attr("style", "display:none");
                            var list="";                          
	                        for(var i=0;i<albums.length;i++){
	                         	if(albums[i][0]!=albumId && albums[i][1] != '个人参赛'){
	                         		var p="<p><input class='checkAlbum' onclick='setCheckBoxSelected(this)' type='checkbox' value='"+albums[i][0]+"'/>"+albums[i][1]+"</p>";
	                         		list+=p;
	                         	}                      	
	                         }
                         	 list+="<input type='button' onclick='moveSubmit()' class='group_submit_button'  value='确定'/>";
                             $("#group_div").html(list);
                        }
                        else {
                            $(".pic_operates_div").hide();
                            $(".manage_ul .del_chk").attr("checked", false).hide();
                            $("#pic_operate_a").attr("class","operate_a");
                            $("#Pagination").attr("style", "display:block");
                        }
                    }
                    function moveSubmit(){
	                    var isSelected=false;
	                    $(".checkAlbum").each(function(){
	                  	 	 if($(this).attr("checked")==true){
	                  	 	 	isSelected=true;	                  	 	 	
	                  	 	 }	 	  
	                  	})
	                  	if(isSelected==true){
	                  	 	var strIds="("+ids.toString()+")";
	 						$.ajax( {
								type : "post",
								url : 'movePhotos.action',
								cache : true,
								data:{'ids':strIds,'albumId':albumId,'moveAlbumId':moveId},
								dataType:'json',
								error : function() {
									alert('加载页面时出错！');
								},
								success : function(data) {								
									var json = eval('(' + data.resultTotal + ')');														 
									$("#Pagination").pagination(json.resultTotal, opt);
									$(".group_div").attr("style","display:none");
									$(".manage_ul .del_chk").attr("checked", false).css("display","inline").show();
								}
							});            
	                    }
	                    else{
	                   		$(document).trgger("click");            
	                    	showMessage("提示 ","请选择要移动至的相册 ");	      	                    	
	                    }
                    }
                    function selectAll(item) {
                        if ($(item).attr("checked") == true) {
                            $(".manage_ul .del_chk").attr("checked", true);
                        }
                        else {
                            $(".manage_ul .del_chk").attr("checked", false);
                        }
                    }
                    function movePic() {
	                   ids=new Array(); 
	                   var i=0;
	                   $(".manage_ul .del_chk").each(function()
	                   {      		
	                   		if($(this).attr("checked") == true)
	                   		{
	                   			ids[i]=$(this).val();
	                   			i++; 	
	                   		}
	                   })
	                   if(i==0)
	                   {            
	                   	showMessage("提示 ","请选中1张以上照片进行此操作 ");
	                   	return ;
	                   }
                    }
                    function delPic() {
                       ids=new Array(); 
	                    var i=0;
	                    $(".manage_ul .del_chk").each(function()
	                   {      		
	                   		if($(this).attr("checked") == true)
	                   		{
	                   			ids[i]=$(this).val();
	                   			i++; 	
	                   		}
	                   })
	                   if(i==0)
	                   {            
	                   	showMessage("提示 ","请选中1张以上照片进行此操作 ");
	                   	return ;
	                   }
	                 
                       showAskBox("删除图片", "确定删除?", sureDelete);
                    }
                    
                   function sureDelete(){
	                   	 var strIds="("+ids.toString()+")";
	                   	 $.ajax( {
									type : "post",
									url : 'deletePhotos.action',
									cache : true,
									data:{'ids':strIds,'albumId':albumId},
									dataType:'json',
									error : function() {
										alert('加载页面时出错！');
									},
									success : function(data) {
										var json = eval('(' + data.resultTotal + ')');		 
										$("#Pagination").pagination(json.resultTotal, opt);
										showMessage("提示 ","删除成功！ ");	
										$(".manage_ul .del_chk").attr("checked", false).css("display","inline").show();
									}
						});     
                   }
                    
                   function setCompetition() {
                    var i=0;
                    
                   $(".manage_ul .del_chk").each(function()
                   {      		
                   		if($(this).attr("checked") == true)
                   		{
                   			i++; 	
                   			id=$(this).val();
                   		}
                   		if(i>1)
                   		return ;
                   })
                  		if(i==1)
                   		{  	
                   		formSubmit($("#"+id+"Url").attr("href"));
                   		}
                   		else if(i<1)
                   		{
                   		showMessage("提示 ","请选中1张照片进行此操作 ");
                   		return ;
                   		} 
                   		else 
                   		{
                   			showMessage("提示 ","只能选中1张照片进行此操作 ");
                   		}
                    }
             
                    
			function loadAlbumPhotos(id,pageNum,isEntry,pageName){
				$.ajax({
					type : "post",
					url : 'loadAlbumPhotos.action',
					cache : false,
					async : false,
					data:{albumId:id,personalId:<s:property value="#parameters.personalInfoId"/>,pageNum:pageNum,isEntry:isEntry,pageName:pageName},
					success : function(msg) {					
						$("#pic_manage_div").empty().append(msg);					
					}
				});			
			}

           function pageselectCallback(page_index,opt){
               pageNum=page_index+1;
               loadAlbumPhotos(albumId,page_index+1,isEntry,pageName);
               return false;
           }
             function formSubmit(url){
           	 	opencenterwindow('loadCompetitionPictuer.action?photoUrl='+url, '513', '415');
             }
             
             function updateCover()
             {
             	  var i=0;
           
                   $(".manage_ul .del_chk").each(function(){      		
                   		if($(this).attr("checked") == true)
                   		{
                   			i++; 	
                   			id=$(this).val();
                   		}
                   		if(i>1)
                   		return ;
                   })
                  		if(i==1)
                   		{  	
                   		showAskBox("更换封面", "确定设置此照片为相册封面吗?", sureUpdateCover);
                   		}
                   		else if(i<1)
                   		{
                   		showMessage("提示 ","请选中1张照片进行此操作 ");
                   		return ;
                   		} 
                   		else 
                   		{
                   			showMessage("提示 ","只能选中1张照片进行此操作 ");
                   		}
             }
             
             function sureUpdateCover(){          	
             	var url=$("#"+id+"Img").attr("src");
            	url=url.substring(3);
            	var params = {
            		'albumId' : albumId,
            		'cover' : url,
            		'oldCover' : cover
            	}
            	new TopNAjax().ajax({
		    		url: 'updateAlbumCover.action',
		    		data: params,
		    		loading: false,
		    		success: function (data) {
		    			var json = eval('(' + data.result + ')');
		    			if(json.url == null || jQuery.trim(json.url) == ''){
		    				$("#cover").attr("src",dealImageURL(url));
		    			}else if(json.result==true)
						{
							$("#cover").attr("src",dealImageURL(json.url));
							cover=json.url;
						}			        	
		            }
		    	});
             }
 </script> 
		<div class="mainContent_left_div">
			<div class="radius_large_top_div"></div>

			   <div id="content_middle_div" class="content_middle_div">
				
                      <div id="pic_manage_head_div"  class="pic_manage_head_div" >
                             <div  class="pic_admin_div">
                                 <li>
                                        <a id="pic_operate_a" class="operate_a" onclick="managePic();"></a>
                                 </li>
                             </div>
                             <div class="pic_operates_div">
                                <ul>
                                     <li class="select_all_li">
                                            <input type="checkbox" class="select_all_chk"  onclick="selectAll(this);" />
                                            <span class="select_all_text">本页全选</span>
                                   </li>
                                   <li><a onclick="setCompetition();">设置为参赛照</a></li>
                                     <li><a onclick="updateCover();">设置相册封面</a></li>
                                   <li>
                                        <span class="pic_move_span">
                                                <a>移动</a>
                                                     <div id="group_div" class="group_div">
                                                        
                                                     </div>
                                        </span>
                                    </li>
                                    <li><a onclick="delPic();">删除</a></li>
                                </ul>
                           </div>
                       </div>
                    <div  id="pic_manage_div" class="pic_manage_div">
                        
                   </div>
                   <div id="Pagination" class="album_pagination"></div>
              </div>
			<div class="radius_large_bottom_div"></div>
		</div>
		<div class="mainContent_right_div">
			<div class="radius_small_top_div"></div>
			<div id="content_right_div" class="content_right_div">
	<div class="pic_des_title_div"><h5 class="title_h5">相册信息</h5></div>
	                  <div id="album_info_div" class="album_info_div">
	                  	<ul>
	                  		<li class="album_info_photo_li"><p class="album_info_p pic_show_p"><img id="cover" src='../<s:property value="#parameters.url"/>'/></p></li>
	                  		<li>名称:<s:property value="#parameters.albumName"/></li>
	                  		<li>张数:<s:property value="#parameters.total"/></li>
	
	                  		<li>创建时间:<s:property value="#parameters.createTime"/></li>
	                  		 	<li id="albumDetail">描述:<s:property value="#parameters.detail"/></li>
	                  		</ul>
	                  </div>
				
			</div>
			<div class="radius_small_bottom_div"></div>
		</div>