<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.topn.bean.*" %>
<%@ page import="com.topn.bean.TO.*" %>
 <%@ include file="/page/util/common.jsp"%>

<html> 
  <head>  
    <link  rel="stylesheet" type="text/css" href="../styles/pagination.css" />
    <link rel="stylesheet" type="text/css" href="../styles/jquery.lightbox.css" media="screen" />
    <link  rel="stylesheet" type="text/css" href="../styles/album.css" />
    <script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js" ></script>
    <script type="text/javascript" src="../scripts/topn.lightbox.js" ></script>
     <script type="text/javascript" src="../scripts/topn.jquery.pagination.js" ></script>
     
   
   
</head>
<body>
 <script type="text/javascript">
    
      var opt = {		
				// 点击页码调用 函数 
				callback: pageselectCallback,
				// 默认显示 页数 
				current_page:0,
				// 每一页显示多少条纪录，最大值为总的记录条数
				items_per_page:20,
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
		
			 function loadAlbumPhotos(id,pageNum,isEntry)
			{
				$.ajax({
				type : "post",
				url : 'loadAlbumPhotos.action',
				cache : false,
			
				data:{albumId:id,pageNum:pageNum,isEntry:isEntry,pageName:"album"},
				success : function(msg) {
					
					$("#picShow_div").empty().append(msg);
					$("#picShow_div").removeClass("loader");
				}
			});
			
			}

           function pageselectCallback(page_index,opt){
               
               loadAlbumPhotos(<%=request.getParameter("albumId")%>,page_index+1,1);
               
              
                return false;
            }
       
          $(document).ready(function(){
          
        //  $("#Pagination").pagination(<s:property  value="#albumPhotos.total"/>, opt);
     	$("#Pagination").pagination(<%=request.getParameter("total")%>, opt);
          });
    </script>
    <div id="header" class="header"  >
       
    </div>
    <div class="upload_pic_div">
      <div class="radius_top_div"> 
      </div> 
      <div id="album_introduce_div" class="album_introduce_div">
           <div id="img_div" class="img_div">
                <img  id="photo_img" src='<%=request.getParameter("url")%>' /> 
           </div>            
           <div id="img_intorduce_div" class="img_intorduce_div">
                <ul>
                    <li style="margin-bottom:10px;">相册名称:<%=request.getParameter("albumName") %></li>
                    <li>相册描述:</li>
                    <li class="album_introduce_li">
                        <p>
                         <%=request.getParameter("detail")%>
                        </p>
                    </li>
                </ul>
           </div>                   
     </div>	
      <div class="radius_bottom_div"> 
      </div> 
      <div class="radius_top_div"> 
      </div>  
      <div id="picShow_div" class="picShow_div">
     
    </div>  
      <div class="radius_bottom_div"> 
      </div> 
     <div id="Pagination">
        </div>
	</body>
</html>