var update_personalInfo_index = "base_info";
var base_info_loaded = new Object();


function initialize(parent, child1, child2) {

}

function initializePagination(parent, child, page) {

}


/*
 * 记载教育页面
 */
function loadEducation() {
	update_personalInfo_index = "education";
	$(".save_page_button").css("display", "inline-block");
	if($("#education_div").children().size() > 0){
		return;
	}
	new TopNAjax().ajax({
		type: "get",
		cache: true,
		url: 'loadEducation.action',
		loadingAreaId : 'education_div',
		success: function (msg) {
			$("#education_div").html(msg);
        }
	});
}

/*
 * 这里为了记录修改个人基本信息时候横向导航条的索引
 */
function changeIndex() {
	update_personalInfo_index = "base_info";
}

/*
 * @despachered 被废弃
 */
function loadHobbyType() {
	update_personalInfo_index = "hobby";
	$(".save_page_button").css("display", "inline-block");
	if($("#hobby").children().size() > 0){
		return;
	}
	new TopNAjax().ajax({
		type: "get",
		url: 'loadHobbyType.action',
		cache : true,
		loadingAreaId : 'hobby',
		success: function (msg) {
			$("#hobby").html(msg);
			
        }
	});
}

// 用来保存加载的兴趣列表
var object = new Object();
function loadHobby(obj) {
	if (null == object) {
		object = new Object();
	}
	// 判断是否是加载同一个兴趣类型，如果是同一种类型就直接返回并将选择结果保存
	if (obj.id == $(".interest_detial_ul").attr("id")) {
		object["'" + obj.id + "'"] = $(".interest_detial_ul");
		return;
	}

	// 如果点击别的兴趣，则先要保存此项
	if ($("#interest_detial_div ul").children().length > 0) {
		var id = $(".interest_detial_ul").attr("id");
		object["'" + id + "'"] = $(".interest_detial_ul");
	}

	// 看如果加载了一次就不要加载了，直接从对象中找
	if (object["'" + obj.id + "'"] != null) {
		$("#interest_detial_div").empty().append(object["'" + obj.id + "'"]);
		return;
	}
	
	var params = {
			'typeId' : obj.id	
	}
	new TopNAjax().ajax({
		url: 'loadHobby.action',
		data: params,
		cache : true,
		async : false,
		loading: false,
		success: function (msg) {
			object["'" + obj.id + "'"] = msg;
			$("#interest_detial_div").html(msg);
			var ori = $("#orinalHobby").val();
			$("#interest_detial_div").show();
			if(ori.length > 0){
				var op = ori.split(",");
				if(op.length > 0){
					for(var i = 0; i < op.length; i++){
						$("input[name='"+jQuery.trim(op[i])+"']").attr("checked", "true");
						//document.getElementsByName(jQuery.trim(op[i])).checked = true;
					}
				}
			}
        }
	});
}

function updateHobby(){
	var selectedHobby = readHobbyFromObject();
	var otherHobby = $("#otherHobby").val();
	if((selectedHobby == '' || null == selectedHobby)&& jQuery.trim(otherHobby) == ''){
		return;
	}
	var params = {
			"hobby" : selectedHobby,
			"otherHobby" : otherHobby
	}
	
	new TopNAjax().ajax({
		url: 'updateHobby.action',
		data: params,
		cache : false,
		async : false,
		loading: false,
		success: function (msg) {
			showUpdateSuccess();
        }
	});
}

function readHobbyFromObject() {
	var str = "";
	for ( var i = 0; i < hobbies.length; i = i + 1) {
		if (i > 0) {
			str += ",";
		}
		str += " " + hobbies[i];
	}
	//重新初始化
	hobbies = new Array();
	object = new Object();
	return str;
}

// 用来保存兴趣列表
var hobbies = new Array();
function changeHobby(obj) {
	var hb = obj.name;
	if (obj.checked) {
		hobbies.push(hb);
	} else {
		for ( var i = 0; i < hobbies.length; i = i + 1) {
			if (hobbies[i] == hb) {
				hobbies = hobbies.del(i);
			}
		}
	}
}

function initHobby(){
	var ori = $("#orinalHobby").val();
	if(ori.length > 0){
		var op = ori.split(",");
		if(op.length > 0){
			for(var i = 0; i < op.length; i++){
				hobbies.push(jQuery.trim(op[i]));
			}
		}
	}
}

/*
 * 更新基本信息是的输入框
 */
function updateBaseInfoInput() {
	update_personalInfo_index = "base_info";
	new TopNAjax().ajax({
		type: "get",
		url: 'updateBaseInfoInput.action',
		cache: false,
		success: function (msg) {
			$("#content_middle_div").empty().append(msg);
        }
	});
}

/*
 * 更新基本信息是的输入框
 */
function baseInfoInput() {
	update_personalInfo_index = "base_info";
	$(".save_page_button").css("display", "inline-block");
	if($("#info_div").children().size() > 0){
		return;
	}
	new TopNAjax().ajax({
		type: "get",
		url: 'baseInfoInput.action',
		loadingAreaId : 'info_div',
		success: function (msg) {
			$("#info_div").empty().append(msg);
			
        }
	});
}


function loadCSS(){
	
}

// 采用ajax方式修改用户信息
function updatePersonalInfo() {
	if (update_personalInfo_index == 'base_info') {
		if(jQuery.trim($("#nickName").val()) == '' || $("#birthday").val() == ''){
			showMessage("填写提示", "姓名，性别，生日必填");
			return;
		}
		var params = $("#updateForm").serialize();
		$.ajax( {
			type : "post",
			url : 'updatePersonalInfo.action',
			cache : false,
			data : params,
			error : function() {
				alert('加载页面时出错！');
			},
			success : function(msg) {
				showUpdateSuccess();
			}
		});
	} else if (update_personalInfo_index == 'education') {
		education_submit();
	} else if (update_personalInfo_index == 'hobby') {
		hobby_submit();
	}else if(update_personalInfo_index == 'head_portrait'){
		head_portrait_submit();
	}else if(update_personalInfo_index == 'modify_password'){
		modifyPassword_to();
	}
}

function showLoading(item){
/*	if($("#loading") != null){
		return;
	}
	var str = '<center id="loading"><img src="../pictures/loading_page.gif" width="500"/></center>';
	$(item).append(str);*/
}

/*
 * obj是好友的类型
 */
function manageFriend(obj){
	var params = {
		'friendType' : obj
	};
	new TopNAjax().ajax({
		type: "get",
		url: 'manageFriend.action',
		data: params,
		cache : false,
		success: function (msg) {
			$("#mainContent").empty().append(msg);
        }
	});
}

//个人信息中更改密码
function modifyPassword(){
	update_personalInfo_index = "modify_password";
	$(".save_page_button").css("display", "inline-block");
	if($("#modify_pwd_div").children().size() > 0){
		return;
	}
	new TopNAjax().ajax({
		type: "get",
		cache : true,
		url: 'modifyPwdInput.action',
		loadingAreaId : 'modify_pwd_div',
		success: function (msg) {
			$("#modify_pwd_div").empty().append(msg);
        }
	});
	
}

/**
 * 清楚由于控件问题带来的缓存
 * @return
 */
function resetUpdatePage(){
}

function exit(){
	$.cookie("password", null);
	$.cookie("id", null);
	window.location.href = "exit.action";
}

function go2topn(){
	window.location.href = "/TopN/totopn.action";
}

//添加朋友
function addFriend(item){
	var selectedType = $(item).parents("#group_div").find("[class='friend_type'][checked]");			
	if(selectedType == null)return;
	var type = selectedType.attr("name");
	var url = 'addFriend.action';   
    var params={   
        'friendId' : $(item).parents(".add_friend_span").find("#friendId").attr("name"),
        'friendType' : type
    };	     
    new TopNAjax().ajax({
		cache: false,
		url: url,
		loading: false,			
		data : params,	
		success: function (msg) {
			
			showMessage("好友申请提醒", msg);
        }
	});
}

////////////////////////////////
//下面是张顺的js，这里做合并
////////////////////////////////

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


