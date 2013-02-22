$(document).ready(function() {
	$(".search_ul > li > a").click(function() {
		$(this).parents("li").first().find("ul").toggle();
		var search_div = $(".search_div");
		var content_middle_div = $(".content_middle_div");
		if (search_div.height() < 500) {
			content_middle_div.css("height", "500px");
		} else {
			content_middle_div.height(search_div.height());
		}
	});
	
	$("#nickname").bind('keyup',function(event) {
		if(event.keyCode==13){
			searchFriend();
		}   
	});
	
	$("#hobby").bind('keyup',function(event) {
		if(event.keyCode==13){
			searchCountWithHobby();
		}   
	}); 
	
	$("#email").bind('keyup',function(event) {
		if(event.keyCode==13){
			searchCountWithEmail();
		}   
	}); 
	
	$(".search_input").bind('keyup',function(event) {
		if(event.keyCode==13){
			searchCountWithSchool();
		}   
	}); 
	
	$("#topn").bind('keyup',function(event) {
		if(event.keyCode==13){
			searchForTopN();
		}   
	}); 
});

//分页函数, 这里其实一个对象
var opt = {
	// 点击页码调用 函数
	callback : pageselectCallback,
	// 默认显示 页数
	current_page : 0,
	// 每一页显示多少条纪录，最大值为总的记录条数
	items_per_page : 10,
	// 一页显示 个数
	num_display_entries : 5,
	prev_text : "上页",
	next_text : "下页",
	// 是否永远显示下一页
	next_show_always : true,
	prev_show_always : true,
	// 省略号后显示 个数
	num_edge_entries : 2
}

var parm;

// 搜索名字匹配总数
function searchFriend() {
	// 首先加载总量
	if (jQuery.trim($("#nickname").val()) == '') {
		return;
	}
	
	var params = {
		'nickname' : $("#nickname").val()
	};
	parm = "nickname="+$("#nickname").val();
	$.post('searchCount.action', params, function cbf(data) {
		opt.callback = pageselectCallback;
		$("#Pagination").pagination(data, opt);
	});
}

// 搜索email匹配总数
function searchCountWithEmail() {
	if (jQuery.trim($("#email").val()) == '') {
		return;
	}
	
	var params = {
		'email' : $("#email").val()
	};
	parm="email="+$("#email").val();
	$.post('searchEmailCount.action', params, function cbf(data) {
		opt.callback = pageselectCallbackEmail;
		$("#Pagination").pagination(data, opt);
	});
}

/*
 * 搜索兴趣爱好总数
 */
function searchCountWithHobby() {
	if (jQuery.trim($("#hobby").val()) == '') {
		return;
	}
	var params = {
		'hobby' : $("#hobby").val()
	};
	parm="hobby="+$("#hobby").val();
	$.post('searchHobbyCount.action', params, function cbf(data) {
		opt.callback = pageselectCallbackHobby;
		$("#Pagination").pagination(data, opt);
	});
}

/*
 * 按照搜索总数
 */
function searchCountWithSchool() {
	if (jQuery.trim($("#school").val()) == '') {
		return;
	}
	var params = {
		'school' : $("#school").val(),
		'nickname' : $("#schoolName").val()
	};
	parm="school="+$("#school").val()+"&nickname=" + $("#schoolName").val();
	new TopNAjax().ajax({
		url: 'searchSchoolCount.action',
		data: params,
		loading: true,
		loadingAreaId : 'loadingAreaId',
		success: function (data) {
			opt.callback = pageselectCallbackSchool;
			$("#Pagination").pagination(data, opt);
        }
	});
}



// 加载朋友
function loadFriends(pageNum, action) {
	$.ajax( {
		type : "post",
		url : action,
		cache : false,
		data : parm + "&pageNum=" + pageNum,
		error : function() {
			alert('加载页面时出错！');
		},
		success : function(msg) {
			$("#search_div").html(msg);
		}
	});
}

//按照昵称搜索的回调函数
function pageselectCallback(page_index, opt) {
	loadFriends(page_index + 1, 'searchFriend.action');
	return false;
}

//按照兴趣搜索回调函数
function pageselectCallbackHobby(page_index, opt) {
	loadFriends(page_index + 1, 'searchHobbyFriend.action');
	return false;
}

//按照email搜索回调函数
function pageselectCallbackEmail(page_index, opt) {
	loadFriends(page_index + 1, 'searchEmailFriend.action');
	return false;
}

//按照school搜索回调函数
function pageselectCallbackSchool(page_index, opt) {
	loadFriends(page_index + 1, 'searchSchoolFriend.action');
	return false;
}

function searchForTopN(){
	if (jQuery.trim($("#topn").val()) == '') {
		return;
	}
	var params = {
		'range' : $("#topn").val(),
		'sex' : $("#sex").val()
	};
	new TopNAjax().ajax({
		url: 'searchForTopN.action',
		data: params,
		loading : true,
		loadingAreaId : "search_div",
		success: function (msg) {
			$("#search_div").replaceWith(msg);
        }
	});
}
