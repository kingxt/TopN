function loadPersonlAlbum() {

	$.ajax( {
		type : "get",
		url : 'loadPersonalAlbum.action',
		cache : false,
		error : function() {
			alert('加载页面时出错！');
		},
		success : function(msg) {
			$("#mainContent").empty().append(msg);

		}
	});

}

function loadUploadPhoto() {

	$.ajax( {
		type : "get",
		url : 'loadUploadPhoto.action',
		cache : false,
		error : function() {
			alert('加载页面时出错！');
		},
		success : function(msg) {
			$("#content_middle_div").empty().append(msg);
			$("#content_middle_div").removeClass("loader");
		}
	});

}

function loadUploadHeadPortrait() {
	update_personalInfo_index = "head_portrait";
	$(".save_page_button").css("display", "none");
	if($("#upload_headPortrait_div").children().size() > 0){
		return;
	}
	$('#upload_headPortrait_div').flash( {
		swf : '../swf/TopN/PicCut.swf',
		height : 404,
		width : 501		
	});
}

function loadPicManage(id, albumName, detail, url, total, albums, createTime,isEntry,isGuest,personalInfoId) {

	$.post("../page/pic_manage.jsp", {
		'albumId' : id,
		'albumName' : albumName,
		'detail' : detail,
		'url' : url,
		'total' : total,
		'isEntry' : isEntry,
		'createTime':createTime,
		'isGuest':isGuest,
		'personalInfoId':personalInfoId
	}, function(msg) {
		$("#mainContent").empty().append(msg);
	});
}

