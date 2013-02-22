
//保存自己的所有好友,这里要求不要删除好友,知道用户下线
var friends = new Map();
//var historyDialog = new Map();
var openedDialog = null;
var dialogIsOpened = false;
var connected = false;

var flashArray = new Map();//闪
var flashFlagArrary = new Map();//闪的位置
var friendCall = null;

$(document).ready(function() {
	function checkFriendDiv() {
        var item = $("#friend_div");
        if (item == null || item == undefined) {
            return false;
        }
        return true;
    }

	function friendDivSildeIn() {
        if (!checkFriendDiv()) {
            fillFriendDiv();
        }
        var item = $("#friend_div");
        item.show();
        item.animate({ bottom: 35 }, 200, null, null);
    }
    function friendDivSildeOut() {
        var item = $("#friend_div");
        item.animate({ bottom: -280 }, 200, null, function () { item.hide(); });
    }
    
    $("#friend_list_a").toggle(function () {
		
		if($.browser.msie && $.browser.version=="6.0"){
			InitPosition($("#friend_div"),2);
			$(window).resize(function(){	
				if($("#friend_div").css("display")!="none")
							{	
							setTimeout(function(){								
								InitPosition($("#friend_div"),2);
								},400);
							}
					});
					$(window).scroll(function(){
						if($("#friend_div").css("display")!="none")
							{	
							setTimeout(function(){								
								InitPosition($("#friend_div"),2);
								},400);
							}
			});
		}
		else{
		//	friendDivSildeIn();
		//	loadAllMyFrined();
	        friendDivSildeIn();
	        clearInterval(friendCall);
	        stopRemind();
		}}, 
		function () {
			if($.browser.msie && $.browser.version=="6.0")
			{
				$("#friend_div").css("display", "none");
			}else{
				friendDivSildeOut();
			}			
		}
	);

	 $("#friend_list_a").toggle(function () {
	 	//loadAllMyFrined();
        friendDivSildeIn();
        stopRemind();
    }, function () {
        friendDivSildeOut();
    });
	 
	resolveMinibar();
});

function resolveMinibar()
{
	if($.browser.msie && $.browser.version=="6.0")
	{
		InitPosition($("#mini_bar_div"),1);
			$(window).resize(function(){			
				setTimeout(function(){InitPosition($("#mini_bar_div"),1);},400);
			});
			$(window).scroll(function(){
				setTimeout(function(){InitPosition($("#mini_bar_div"),1);},400);
			});	
	}	
	else
	{
		$("#mini_bar_div").css("position","fixed").css("bottom","0px").css("right","15px").css("display","block");
	}	
}
/**
 * 加载自己的所有好友
 * @return
 */
function loadAllMyFrined(){
	if(friends.size() > 0){
		return;
	}
	$.post(   
	        'getAllMyFriend.action',        
	        function cbf(data){ //返回的是一个心情id
	        	if(jQuery.trim(data) == ''){
	        		return;
	        	}
	        	initFriends(data)
	        }
	);
}

/**
 * 添加好友
 * @param id
 * @param img
 * @param friendType
 * @return
 */
function addNewFriend(id, name, friendType, img){
	var p = new Person();
	p.id = id;
	p.isOnline = 2;
	p.name = name;
	p.type = friendType;
	p.url = img;
	friends.put(id, p);
	initFriendsListFrame();
}

/**
 * 打开的历史记录
 * @return
 */
function displayComm(){
	dialogIsOpened = false;
	$("#communication_div").css("bottom","-375px").hide();
}

function openChatingDialog(){
	if(openedDialog && !dialogIsOpened){
		//$("#communication_div").appendTo($("#communication_minibar_div")).animate({ bottom: 35 }, 500, null, null).show();
		if($.browser.msie && $.browser.version=="6.0")
		{
			InitPosition($("#communication_div"),4);
		}else{
			$("#communication_div").animate({ bottom: 35 }, 500, null, null).show();
		}
		dialogIsOpened = true;
	}else if(openedDialog && dialogIsOpened){
		displayComm();
	}
}

/**
 * 初始化好友
 */
function initFriends(jsonStr){
	//将json字符串转换为对象
	var json = eval('(' + jsonStr + ')');  
	$.each(json, function(i){
		var id = json[i].id;
		var name = json[i].name;
		var url = json[i].url;
		var type = json[i].type;
		var isOnline = json[i].isOnline;
        var p = new Person(id, name, url, type, isOnline);
        friends.put(id, p);
	});
	if(friends.size() > 0){
		var id = $.cookie("id");
		connectTOServer(id);
	}
	initFriendsListFrame();
}

/**
 * 注册事件
 * @return
 */
function register(){
	
	$(".friend_type_a").click(function () {
        $(this).parents("li").first().find(".friend_detial_ul").toggle();
    });
	
	$(".friend_list_div").find("li .friend_photo_div a").dblclick(
			function() {
				var id=$(this).attr("id");
	        	flashFlagArrary.remove(id);
	        	clearInterval(flashArray.get(id));
	        	flashArray.remove(id);        	
	        	$(this).find("img").css("width","25px").css("height", "25px");        	
	        	
	        	stopRemind();
				openChatFrame($(this).parents("#aFriend").first());
				dialogIsOpened = true;
			});
	
	$("#communication_sender_btn").click(function(){
		sendMsg($(this));
	});
	
	$("#edit_content_area").bind('keyup',function(event) {
		if(event.keyCode==13){
			//commitMe();
			$("#communication_sender_btn").trigger("click");
		}
	});
	
	$("#friend_quick_search_i").bind('keyup',function(event) {
		if(event.keyCode==13){
			quickSearch();
		}
		var name = $("#friend_quick_search_i").val();
		name = jQuery.trim(name);
		
		if('' == name){
			initFriendsListFrame();
			return;
		}
	});
}

function getFlash(id){
		if(id == undefined || id == ''){
			return;
		}
		flashFlagArrary.put(id, 1);
		var flash = setInterval(function(){
		var flashFriend=$("#friend_list_div").find("#"+id).find("img");  
		if(flashFlagArrary.get(id) == 1)    			
		{					   
			  flashFriend.css("width",20);
			   flashFriend.css("height",20);
		   flashFlagArrary.put(id, 2);
		 }
		 else
		 {
			   flashFriend.css("width",25);
			   flashFriend.css("height",25);
		   flashFlagArrary.put(id, 1);
		 }					
	},350);
	flashArray.put(id,flash);
} 
//停止闪
function stopRemind(){
	clearInterval(friendCall);
	$("#friend_list_a").attr("class","friend_list_link_a");
	friendCall = null;
}
//闪
function friendMsgRemind(){
	friendCall = setInterval(function(){
		var minibar=$("#friend_list_a");
			if(minibar.attr("class")== "friend_list_link_a")
			{
				minibar.attr("class","friend_list_link_flash_a");
			}
			else
			{
				minibar.attr("class","friend_list_link_a");
			}
	},200);     
} 


/**
 * 初始化窗口
 * @return
 */
function initFriendsListFrame(){
	//首先选出自己的在线好友
	
	var strFriend = "";
	var closeFriend = "";
	var onlineStrFriend = "";
	var onlineCloseFriend = "";
	friends.each(function(key,value,index){
		if(value.type == 1 && (value.isOnline == 2 || value.isOnline == '2')){
			strFriend += myFriendHMTL(value);
		}else if(value.type == 2&& (value.isOnline == 2 || value.isOnline == '2')){
			closeFriend += myFriendHMTL(value);
		}else if(value.type == 1 && (value.isOnline == 1 || value.isOnline == '1')){
			onlineStrFriend+= myFriendHMTL(value);
		}else if(value.type == 2 && (value.isOnline == 1 || value.isOnline == '1')){
			onlineCloseFriend+= myFriendHMTL(value);
		}		
		
	});	
	strFriend = getFriendListLi("好友", onlineStrFriend+strFriend);
	closeFriend = getFriendListLi("密友", onlineCloseFriend+closeFriend);
	//将生成的html添加到容器中去
	$(".friend_list_ul").html(closeFriend).append(strFriend);
	register();
}

/**
 * 打开聊天窗口
 * @return
 */
function openChatFrame(item){
	var personId = item.attr("name");
	if(personId == 0 || personId == '')return;
	var person = friends.get(personId);
	if($.browser.msie && $.browser.version=="6.0")
	{
		InitPosition($("#communication_div"),4);
		$(window).resize(function(){
			if($("#communication_div").css("display")!="none"){
				setTimeout(function(){InitPosition($("#communication_div"),4);},400);
			}
		});
		$(window).scroll(function(){
		if($("#communication_div").css("display")!="none"){
			setTimeout(function(){InitPosition($("#communication_div"),4);},400);
			}
		});	
	}else{
		$("#communication_div").animate({ bottom: 35 }, 500, null, null).show();
	}
	$("#title_span").html("和【"+person.name+"】聊天中");
	
	var p = person.url;
	$("#friendPhoto").attr("src", p.replace("user_small/", ""));
	
	refreshFrame(person);
	if(person.content != undefined && person.content != null){
		$("#communication_to_content_ul").html(person.content);
	}else{
		$("#communication_to_content_ul").html("");
	}
	$("#communication_sender_btn").attr("name", person.id);
	openedDialog = personId;
	//register();
	resertForm();	
}
/**
 * 重置form
 * @return
 */
function resertForm(){
	$("#edit_content_area").val("");
	$("#edit_content_area").focus();
}




/**
 * 添加一个在线好友
 * @param id
 * @param name
 * @param url
 * @return
 */
function addOnlineFriend(id, name, url, type){
	if(friends.get(id) != null){//如果有了此人就不用再修改
		return;
	}else{
		var per = new Person(id, name, url, type);
		friends.put(id, per);
	}	
}

/**
 * 离开操作，好友离线的操作应该是将其头像致灰
 * @param id
 * @return
 */
function friendLeave(id){
	var person = friends.get(id);
	if(person){
		person.isOnline = 2;
		initFriendsListFrame();//刷新窗口
	}	
}

function friendOn(id){
	var person = friends.get(id);
	if(person){
		person.isOnline = 1;
		initFriendsListFrame();//刷新窗口
	}	
}

/**
 * 每个人有个对象
 * @id 好友的id
 * @type 好友的类型
 * @name 好友的名字
 * @type 朋友类型
 */
function Person(id, name, url, type, isOnline) {
	this.id = id;
	this.name = name;
	this.url = url;
	this.type = type;
	this.isOnline = isOnline;
	
	this.isMeyou = function(){
		return type == 2;
	};
	this.isHaoyou = function(){
		return type == 1;
	}
}

/**
 * 加入到服务器做到实时监听 id 用户的id标识
 */
function connectTOServer(id) {
	if(connected)return;
	p_join_listen('/chat');
	p_publish('/chat', 'action', 'enter', 'id', id);
	connected = true;
}

/**
 * 监听到服务器消息
 * @param event
 * @return
 */
function onData(event) {
	//p_debug(false, "pushlet-app", 'event received event=' + event.getEvent());
	var action = event.get('action');//拿到服务器的动作，看是有人发消息，还是有人上线，还是有人回复自己的留言
	var content = 'none action=' + action;
	if (action == 'send') {//有人发消息来了,或是有心情回复
		
		var ty = event.get('p_type');
		
		if(ty != undefined && (ty == 1 || ty == '1')){
			reveiveNewReplyMood();
		}else if(ty != undefined && (ty == 2 || ty == '2')){
			friendLeave(event.get('p_id'));
		}else if(ty != undefined && (ty == 3 || ty == '3')){
			friendOn(event.get('p_id'));
		}else{
			msgTOFrame(event.get('p_id'), event.get('p_send_time'), event.get('msg'));
			if(dialogIsOpened && openedDialog == event.get('p_id')){
				return;//说明对话窗口已经打开
			}else{
				//如果正在闪，就不能再添加事件了
				var temp = flashArray.get(event.get('p_id'));
				if(!temp || temp == null || temp == undefined){
					getFlash(event.get('p_id'));
				}
				if(friendCall == null){
					friendMsgRemind();
				}				
			}
		}		
	} else if (action == 'enter') {//有人加入
		addOnlineFriend(event.get('id'), event.get('time'), event.get('url'));
	} else if (action == 'exit') {
		friendLeave(event.get('id'));
	}//alert(action);
	//appendMessage(content);
}

/**
 * 发送消息:这里应该注意的是没有必要将用户的id传过去，因为pushlet会帮我们做这个操作
 * @param msg 要发的消息
 * @return
 */
function sendMsg(item){
	var msg = jQuery.trim($("#edit_content_area").val());
	if(msg == ''){
		return;
	}
	
	resertForm();
	var myId = $.cookie("id");
	var toId = item.attr("name");
	var person = friends.get(toId);
	var me = friends.get(myId);
	if(person.content == undefined || person.content == null)person.content =  "";
	var addTime = getTimeString();
	person.content = person.content + generateMessage(me.name, addTime, msg);
	var msg2 = encodeURIComponent(msg);
	p_publish('/chat', 'action', 'send', 'msg', msg2, 'p_to', toId, 'p_send_time', addTime);
	refreshFrame(person);//refresh the frame
}

function leaveApply(){
	p_publish('/chat', 'action', 'leave');
}

/**
 * 
 * @param id
 * @param time
 * @param msg
 * @return
 */
function msgTOFrame(id, time, msg){
	
	//先找到那个人
	var person = friends.get(id);
	var str = generateMessage(person.name, time, msg);
	if(person.content == undefined || person.content == null)person.content =  "";
	person.content = person.content + str;//更新要显示的字符串
	
	if(id == openedDialog){
		refreshFrame(person);
	}
}

/**
 * 快速搜索
 * @return
 */
function quickSearch(){
	var name = $("#friend_quick_search_i").val();
	name = jQuery.trim(name);
	
	if('' == name){
		initFriendsListFrame();
		return;
	}
	var se = "";
	friends.each(function(key,value,index){
		if(value.name == name){
			se+= myFriendHMTL(value);			
		}				
	});	
	se = getFriendListLi("搜索列表", se);
	
	//将生成的html添加到容器中去
	$(".friend_list_ul").html(se);
	register();
	$(".friend_type_a").trigger("click");
}

/**
 * 发布新回复
 * @return
 */
function publishNewReplyMood(){
	
}

/**
 * 提醒用户有新的提醒了
 * @return
 */
function reveiveNewReplyMood(){
	if(null == $("#mood_tips_div")){
		return;
	}
	$("#mood_tips_div").html("<span style='color: red;'>您的心情有新回复,看看吧!!!</span>");
}

/**
 * 刷新聊天窗口
 * @return
 */
function refreshFrame(person){
	$("#communication_to_content_ul").html(person.content);
	var contentDIV = $("#communication_to_content_container_div");
    var contentUL = $("#communication_to_content_ul");
    if (contentDIV.height() < contentUL.height()) {
        contentDIV.scrollTop(contentUL.height());
    }
}

/**
 * 获取好友列表时候最外层应该有一个套层
 * @param type
 * @param fri
 * @return
 */
function getFriendListLi(type, fri){
		
	var str	= '<li class="friend_list_li">'
	    	+'	<a class="friend_type_a">'+type+'</a>'
	    	+'		<div  class="friend_detial_ul">'
	    	+'			<ul >'	
	    	+fri
	        +'			</ul>'
		    +'		</div>'
		    +'</li>';
	return str;
}

/**
 * 生成格式化的聊天记录 name 发信人 time 发送时间 content 发送内容
 */
function generateMessage(name, time, content) {
	var str = '<li>'
			  +'	<p>'
			  +'		<span class="communication_writer_span">'+name+'</span>'
			  +'		<span class="communication_time_span">'+time+'</span>'
			  +'	</p>'
			  +'	<p>'
			  +		content
			  +'	</p>'
			  +'</li>';
	return str;
}

/**
 * 生成一个friend格式列表
 * @param person
 * @return
 */
function myFriendHMTL(person){
	var str = "";
	if(person.isOnline == 2){
		str = '<li class="offline_li" id="aFriend" name="'+person.id+'">'
			+'  <div class="friend_photo_div">'
			+'		<a id="'+person.id+'">'
			+'			<img  style=" width:25px;height:25px;" src="'+person.url+'"/>'
			+'		</a>'
			+' </div>'
			+'   <div class="friend_name_div">'
	        +'     <p><a href=visitor.action?personalInfoId='+person.id+' target="_black">'+person.name+'</a></p>'
	        +' </div>'
	        +'</li>';
	}else{
		str = '<li id="aFriend" name="'+person.id+'">'
			+'  <div class="friend_photo_div">'
			+'		<a id="'+person.id+'">'
			+'			<img  style=" width:25px;height:25px;" src="'+person.url+'"/>'
			+'		</a>'
			+' </div>'
			+'   <div class="friend_name_div">'
	        +'     <p><a href=visitor.action?personalInfoId='+person.id+' target="_black">'+person.name+'</a></p>'
	        +' </div>'
	        +'</li>';
	}
	return str;
}

/**
 * 获取时间
 * @return
 */
function getTimeString(){
	var da =new Date(); 
	return da.getHours() + ":" + da.getMinutes();
}


var tryingToReload = false;
window.onbeforeunload = function(e) //on before unload
{
	if (!e) //Firefox and Safari gets argument directly.
	{
	e = window.event; // this is for IE
	}

	if (e.clientY != undefined && e.clientY < 0) // clicked on the close button for IE
	{
		tryingToReload = false;
	}

	if (e.clientY != undefined && (e.clientY > 100 && e.clientY < 140)) //select close from context menu from the right click on title bar on IE
	{
		tryingToReload = false;
	}

	if (!tryingToReload) //user hasn't clicked on X close button or hasn't selected close from context menu
	{
		leaveApply();
	}
}

document.onkeydown = function(e) //attach to key down event to detect the F5 key
{
	tryingToReload = false;

	if (!e) //Firefox and Safari gets argument directly.
	{
	e = window.event;
	}

	var key = e.keyCode ? e.keyCode : e.which;

	try //try
	{
		if (key == 116) //F5 Key detected
		{
		tryingToReload = true;
		}
	}
	catch (ex) { }
}

document.oncontextmenu = function(e) //check for the right click
{
//cannot blindly say tryingToReload = true as the context menu doesn't have refresh/reload options on all the elments.
//reload options doesn't appear on image, anchor tag, it does appear on body, td and div tag in this case

	var srcElement = getEventSrc(e);
	
	var tagName = '';
	if (srcElement.tagName != undefined) //Get the name of the tag
	{
	tagName = srcElement.tagName;
	}

	switch (tagName)
	{
		case "BODY":
		case "TD":
		case "DIV":
		{
			tryingToReload = true;
			break;
		}
		default:
		break;
	}
}

function getEventSrc(e)
{
	if (this.Event)
	{
		var targ = e.target;
		//nodeType of 1 means ELEMENT_NODE
		return targ.nodeType == 1 ? targ : targ.parentNode;
	}
	else //this is for IE
	return event.srcElement;
}

document.onclick = function(e) //clicked anywhere else, you are not trying to reload
{
tryingToReload = false;
}


