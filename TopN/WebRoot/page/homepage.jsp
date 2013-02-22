<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/page/util/common.jsp"%>
	<head>
		<meta content="zh-cn" http-equiv="Content-Language" />
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<title><s:property value="#otherInfo.nickName"/>个人主页</title>
		<link rel="shortcut icon" href="../pictures/222.ico"/>
		<link type="text/css" rel="Stylesheet"
			href="../styles/topn.css" />
		<script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js"></script>
		<script type="text/javascript" src="../scripts/topn.common.js" ></script>
		<script type="text/javascript" src="../scripts/topn.personal_page.js"></script>
		<script type="text/javascript" src="../scripts/topn.expression.js"></script>	
		<script type="text/javascript" src="../scripts/topn.showbox.js"></script>
		<script type="text/javascript">
			function modifyPhoto(item){
				$("#personal_photo_img").attr("src", item);
			}
			
			$(document).ready(function() {
				resolvePngInIe6();
			});
		</script>
	
</head>
<body>
<!--	<iframe id='iframe_hidden' width='0' height='0' frameborder='0'></iframe>-->
    <div class="show_background_div">
      <div  class="show_content_div">
      <div class="show_content_container_div">
		<div id="header" class="header" >
	        <jsp:include page="header2.jsp"></jsp:include>
   		 </div>
		<div id="content_left_div" class="content_left_div">
			<div id="left_top_div" class="left_top_div">
				<!--<div class="radius_small_top_div"></div>-->
				<div>
					<img id="personal_photo_img"
						src="<s:property value="#otherInfo.photo"/>" />
				</div>
			</div>

			<div id="left_middle_div" class="left_middle_div">
				<ul class="left_menu_ul">
                    <li>
                        <a class="menu_common menu_li_1" href="getVisitorMood.action" ui-async="async"></a>
                        <div class="sub_menu_div" id="submood">             
                        </div> 
                    </li>
                   
                  <li>
                  			<a class="menu_common menu_li_3" href="loadPersonalAlbum.action" ui-async="async"></a>
                             <div class="sub_menu_div">
                             </div>  
                  </li>
                  <li>
                    	<a class="menu_common menu_li_7" href="visitorsFriends.action" ui-async="async"></a>
                        <div class="sub_menu_div">             
                               
                        </div>
                  </li>
                   <li>
                   			<a  class="menu_common menu_li_5" href="showBaseInfo.action" ui-async="async"></a>
                             <div class="sub_menu_div">
                             </div>  
                  </li>
                </ul>
			</div>
		</div>

		<!--右边内容开始-->
		<div id="mainContent" class="mainContent_div">
			<jsp:include page="personal/visitor_mood.jsp"></jsp:include>
		</div>
		</div>
</div>
</div>
			<div id="footer_div"  class="footer_div">
                <jsp:include page="page_right/bottom.jsp"></jsp:include>
       		</div>     
     
	</body>
	<script type="text/javascript" src="../scripts/topn.ajax.iframe.js"></script>
</html>
	
