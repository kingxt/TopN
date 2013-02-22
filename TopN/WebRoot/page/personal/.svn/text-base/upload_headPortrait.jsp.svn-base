
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/page/util/common.jsp"%>
<script type="text/javascript"
	src="../scripts/uploadPortrait/jquery.form.js"></script>
<script type="text/javascript"
	src="../scripts/uploadPortrait/jquery.Jcrop.min.js"></script>
<script type="text/javascript"
	src="../scripts/uploadPortrait/jQuery.UtrialAvatarCutter.js"></script>
<link rel="stylesheet" type="text/css" href="../styles/uploadPortrait/jquery.Jcrop.css"/>
<script type="text/javascript">

    var cutter = new jQuery.UtrialAvatarCutter(
    {
    content: "Step2Container",

    //缩略图配置,ID:所在容器ID;width,height:缩略图大小

    purviews: [{ id: "ImageIcon", width: 150, height:  180}],

    //选择器默认大小

    selector: { width: 150, height: 180 },

    Fixed: { fixedWidth: 50, fixedHeight: 50 }
    });

    $(document).ready(function () {
   
	     $('#form1').ajaxForm({
	
	    url: 'uploadTempPhoto.action',
	    type: 'post',
	    dataType: 'json',
	    'success': function (data) {
	
	    var json = eval('(' + data.imgInfo + ')');
	    //$("#Image").attr("src",json.imgUrl);
	    if(json.imgUrl=="error")
		 {
			alert("文件过大");
		 }
		 else
		 {
			 cutter.reload("../temp/" + json.imgUrl, json.imgWidth, json.imgHeight,0.83);
	  		$("#imgUrl").val(json.imgUrl);  
	  		Step2();
		 }
	    }
	    });
	    
	     cutter.init();
    });
    

    function Step2() {
    $("#upload_photo_div").attr("style", "display:none");
    $("#photo_container_div").attr("style", "display:inline-block");
    }
    function Step3() {
    $("#upload_photo_div").attr("style", "display:block");
    $("#photo_container_div").attr("style", "display:none");
    }

    function head_portrait_submit() {
    var data = cutter.submit();

    $.ajax({
    type: "post",
    url: 'cutHeadPortrait.action',
    cache: true,
    dataType: 'json',
    'data': { x: data.x, y: data.y,
    imgUrl: $("#imgUrl").val(), zoomWidth: data.zoomWidth, zoomHeight: data.zoomHeight
    },
    error: function () {
    alert('加载页面时出错！');
    },
    success: function (data) {

    //	$("#upload_headPortrait_div").html(msg)

    var json = eval('(' + data.newPhotoChar + ')');
	   $("#personal_photo_img").attr("src", json.newPhotoChar);
    Step3();
    }
    });
    }
    
     function checkurl()
		{
		var passfix= new Array("jpg","gif","png","jpeg");
		var strs=document.getElementById("photo");
		
		var maxsize=4;
		with(strs)     //用js 的 with
		{
		    var filename = value.substring(value.lastIndexOf('\\')+1,value.lastIndexOf('.'));
		   
		    var sign = false;
		    if(filename==null||filename=="")
		    {
		     alert("请选择您要上传的文件！");
		     return false;
		    }
		   
		    fix=value.substring(value.lastIndexOf('.')+1,value.length).toLowerCase();
		    for(var i=0;i<passfix.length;i++)
		    {
		     if(passfix[i]==fix)
		     {
		      sign=true;
		      break;
		     }
		    }
		   if(!sign)
		   {
		    alert(fix+"为不允许上传的类型！正确类型包括（jpg,gif,png,jpeg）");
		    return false;
		   }
		
		}
		return true;
		}
  </script>
<!--当前照片-->
<div id="upload_photo_div" class="upload_photo_div">
	<!--Step 1-->
	<form id="form1" name="form1" enctype="multipart/form-data"
		onsubmit="return checkurl();">
		<div class="uploadtool_tip_div">
			<p>
				请上传头像，图片文件需小于2.5MB
			</p>
		</div>
		<div>
			<span> <input type="file" name="photo" id="photo" title="选择照片" />
			</span>
			<span> <input type="submit" name="btnUpload" value="上 传"
					id="btnUpload" /> </span>
		</div>
	</form>
</div>
<!--Step 2-->
<div class="photo_container_div" id="photo_container_div">
	<div class="Step2_Container_div">
		<fieldset>
			<legend>
				上传照片
			</legend>
			<ol>
				<li>
					<div id="Step2Container">

					</div>
				</li>
			</ol>
		</fieldset>

	</div>
	<div id="Step3Container" class="Step3_Container_div">
		<fieldset>
			<legend>
				效果图
			</legend>
			<ol>
				<li>
					<div id="ImageIcon">
					</div>
				</li>
			
			</ol>
		</fieldset>
	</div>
</div>
<input id="imgUrl" type="hidden">
