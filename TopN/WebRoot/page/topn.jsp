<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.topn.bean.TO.LoginTO"%>
<%@ include file="/page/util/common.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	String parm = "";
	LoginTO lto = (LoginTO)request.getAttribute("lto");
	parm = lto.getPersonalInfoId() + "&" + lto.getTimes() + "&" + lto.getTotalScore() + "& "+ lto.getImageURL() +
			"&" + lto.getName() + "&"+ lto.getSex() + "&" + lto.getAttention();
	String rankintList = "";
	if(null != lto.getRankingList()){
		rankintList = lto.getRankingList();
	}
	
	String initPic = lto.getInitPicURL();
	int pid = lto.getPersonalInfoId();
%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TopN</title>
<link rel="stylesheet" type="text/css" href="swf/TopN/history/history.css" />
<link rel="shortcut icon" href="pictures/222.ico"/>

<script type="text/javascript">
	function getRankingList(){
		var rl = "<%=rankintList %>";
		return rl;
	}
	
	function getPara(){
		return "<%=parm%>";
	}
	
	function getInit(){
		return "<%=initPic%>";
	}
	
	function navigateTOHomePage(){
		
		window.open("page/personalPage.action?personalInfoId=" + <%=pid %>, "homepage"); 
	}
	
	function tourist(id){
		window.open("page/tourist.action?personalInfoId=" + id, "tourist"); 
	}
	
	function touristRank(id, ii){
		window.open("page/touristRank.action?personalInfoId=" + id+"&rank="+ii, "tourist"); 
	}
	
	function toLogin(){
		window.location.href = '<%=basePath%>'+'<%=path%>'+"/page/login.jsp";
	}
	
	function contactUs(){
		window.open("page/contact_us.html", "contactus"); 
	}
	
	function exit(){
		window.close();
	}
	
	function changeTitle(){
		document.title = 'TopN';
	}
	//拿到当前页面的域
	function getDomain(){
		return '<%=basePath%>'+'<%=path%>/';
	}
</script>
<script src="swf/TopN/AC_OETags.js" language="javascript"></script>
 
<!--  BEGIN Browser History required section -->
<script src="swf/TopN/history/history.js" language="javascript"></script>
<!--  END Browser History required section -->

<style> 
body { margin: 0px; overflow:hidden }
</style>
<script language="JavaScript" type="text/javascript"> 
<!--
// -----------------------------------------------------------------------------
// Globals
// Major version of Flash required
var requiredMajorVersion = 9;
// Minor version of Flash required
var requiredMinorVersion = 0;
// Minor version of Flash required
var requiredRevision = 124;
// -----------------------------------------------------------------------------
// -->

</script>

</head>
<body scroll="no">
<script language="JavaScript" type="text/javascript"> 
<!--
// Version check for the Flash Player that has the ability to start Player Product Install (6.0r65)
var hasProductInstall = DetectFlashVer(6, 0, 65);
 
// Version check based upon the values defined in globals
var hasRequestedVersion = DetectFlashVer(requiredMajorVersion, requiredMinorVersion, requiredRevision);
 
if ( hasProductInstall && !hasRequestedVersion ) {
	// DO NOT MODIFY THE FOLLOWING FOUR LINES
	// Location visited after installation is complete if installation is required
	var MMPlayerType = (isIE == true) ? "ActiveX" : "PlugIn";
	var MMredirectURL = window.location;
    document.title = document.title.slice(0, 47) + " - Flash Player Installation";
    var MMdoctitle = document.title;
 
	AC_FL_RunContent(
		"src", "playerProductInstall",
		"FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle,
		"width", "100%",
		"height", "100%",
		"align", "middle",
		"id", "TopN",
		"wmode", "transparent",
		"quality", "high",
		"bgcolor", "#869ca7",
		"name", "TopN",
		"allowFullScreen","true",
		"allowScriptAccess", "sameDomain",
		"type", "application/x-shockwave-flash",
		"pluginspage", "http://www.adobe.com/go/getflashplayer"
	);
} else if (hasRequestedVersion) {
	// if we've detected an acceptable version
	// embed the Flash Content SWF when all tests are passed
	AC_FL_RunContent(
			"src", "swf/TopN/TopN",
			"width", "100%",
			"height", "100%",
			"align", "middle",
			"id", "TopN",
			"wmode", "transparent",
			"quality", "high",
			"bgcolor", "#ffffff",
			"name", "TopN",
			"allowFullScreen","true",
			"allowScriptAccess", "sameDomain",
			"type", "application/x-shockwave-flash",
			"pluginspage", "http://www.adobe.com/go/getflashplayer"
	);
  } else {  // flash is too old or we can't detect the plugin
    var alternateContent = 'Alternate HTML content should be placed here. '
  	+ 'This content requires the Adobe Flash Player. '
   	+ '<a href=http://www.adobe.com/go/getflash/>Get Flash</a>';
    document.write(alternateContent);  // insert non-flash content
  }
// -->
</script>
<noscript>
  	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="swf/TopN/TopN" width="100%" height="100%"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="swf/TopN/TopN.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#ffffff" />
			<param name="allowFullScreen" value="true" />		
			<param name="allowScriptAccess" value="sameDomain">
			<param name="wmode" value="transparent">
			<embed src="swf/TopN/TopN.swf" quality="high" bgcolor="#ffffff" id="topnkingxt"
				width="100%" height="100%" name="TopN" align="middle"
				play="true"
				loop="false"
				quality="high"
			    wmode="transparent"
				allowFullScreen="true"
			    allowScriptAccess = "sameDomain"
				type="application/x-shockwave-flash"
				pluginspage="http://www.adobe.com/go/getflashplayer"
				>
			</embed>
	</object>
</noscript>

<br></body>
</html>