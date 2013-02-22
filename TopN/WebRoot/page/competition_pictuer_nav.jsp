﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<html>
	<head>
		<title>上传参赛照片</title>
		<script type="text/javascript" src="../scripts/topn.jquery-1.4.1.js"></script>
		<script type="text/javascript" src="../scripts/topn.common.js" ></script>
		<script type="text/javascript">
	      $(document).ready(function () {
	      	 $('#flexCom').flash( {
				swf : '../swf/TopN/ComCut.swf',
				height : 404,
				width : 501		
			});
	      });
	      
	      function go2topn(){								
				if(window.opener){
						window.opener.location="/TopN/totopn.action";
						window.close();
				}else{
						var parentWindow = window.dialogArguments;
						window.close();
						parentWindow.location="/TopN/totopn.action";
									
				}
		}
    </script>
	</head>
	<body>
		<div id="Step2Container" >
			<div align="left" id="flexCom">
			
			</div>
		</div>
	</body>
</html>
