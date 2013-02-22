<%@ page language="java" contentType="text/html; charset=UTF-8"
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
                  	var isEntry='<s:property value="#parameters.isEntry"/>';
                  	var id;
                  	var cover='<s:property value="#parameters.url"/>';
                 
					if(isEntry==2)
					{
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
                    	if($.browser.version=="7.0"){
                    		$("img").css("margin-top","4px");
                    	}                    	
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
                    
			 function loadAlbumPhotos(id,pageNum,isEntry,pageName)
			{
			
				$.ajax({
				type : "post",
				url : 'loadAlbumPhotos.action',
				cache : false,
			
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
            
 </script> 
		<div class="mainContent_left_div">
			<div class="radius_large_top_div"></div>

			   <div id="content_middle_div" class="content_middle_div">
				   
                    
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
	                  		<li><p class="album_info_p"><img id="cover" src='../<s:property value="#parameters.url"/>'/></p></li>
	                  		<li>名称:<s:property value="#parameters.albumName"/></li>
	                  		<li>张数:<s:property value="#parameters.total"/></li>
	
	                  		<li>创建时间:<s:property value="#parameters.createTime"/></li>
	                  			<li id="albumDetail">描述:<s:property value="#parameters.detail"/></li>
	                  		</ul>
	                  </div>
				
			</div>
			<div class="radius_small_bottom_div"></div>
		</div>