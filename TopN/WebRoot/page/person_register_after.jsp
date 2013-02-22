<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ include file="/page/util/common.jsp"%>
	<head>
		<meta content="zh-cn" http-equiv="Content-Language" />
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<title><s:property value="#pi.nickName"/>个人主页</title>
		<link rel="shortcut icon" href="../pictures/222.ico"/>
		<link type="text/css" rel="Stylesheet"
			href="../styles/topn.css" />
		<script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js"></script>
		<script type="text/javascript" src="../scripts/topn.common.js" ></script>
		<script type="text/javascript" src="../scripts/topn.personal_page.js"></script>
		<script type="text/javascript" src="../scripts/ajax-pushlet-client.js"></script>
		<script type="text/javascript" src="../scripts/topn.im.js"></script>	
		<script type="text/javascript" src="../scripts/topn.expression.js"></script>	
		<script type="text/javascript" src="../scripts/topn.showbox.js"></script>
		<script type="text/javascript" src="../scripts/topn.status.js"></script>
		<script type="text/javascript">
			function modifyPhoto(item){
				$("#personal_photo_img").attr("src", item);
			}
			
			$(document).ready(function() {
				resolvePngInIe6();
				loadAllMyFrined();
			});
		</script>
		
</head>
<body>
<!--	<iframe id='iframe_hidden' width='0' height='0' frameborder='0'></iframe>-->
    <div class="show_background_div">
      <div  class="show_content_div">
      <div class="show_content_container_div">
 		<input type="hidden" id="register_after" value="register_after"/>
		<div id="header" class="header" >
	        <jsp:include page="header.jsp"></jsp:include>
   		 </div>


		<div id="content_left_div" class="content_left_div">
			<input type="hidden" value="<s:property value="#pi.personalInfoId"/>" id="personalInfoId"/>
			<div id="left_top_div" class="left_top_div">
				<div>	
					<a href="directModifyPhoto.action" ui-async="async" title="修改头像">
					<img id="personal_photo_img" id="<s:property value="#pi.personalInfoId"/>"
						src="<s:property value="#pi.photo"/>" />
					</a>
				</div>
			</div>

			<div id="left_middle_div" class="left_middle_div">
				<jsp:include page="navigation.jsp"></jsp:include>
			</div>
		</div>

		<!--右边内容开始-->
		<div id="mainContent" class="mainContent_div">
		<jsp:include page="personal/recommendation.jsp"></jsp:include>	
		
		</div>
		 
</div>
</div>
</div> 
       	<div id="footer_div"  class="footer_div">
                <jsp:include page="page_right/bottom.jsp"></jsp:include>
       	</div>  
       	<jsp:include page="page_right/real_time_chat.jsp"></jsp:include>
	</body>
	<script type="text/javascript" src="../scripts/topn.ajax.iframe.js"></script>
	
</html>
	
