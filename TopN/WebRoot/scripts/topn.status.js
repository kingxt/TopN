
    var statusOptions = {
        reply_area: null
    }
    
    $(document).ready(function () {
        $(document).click(function () {
            Restore();
            var p = $("#expression_div");
            if(p.length > 0){
            	p.hide();//隐藏表情
            }
        }); 
  });
     
  function checkMoodArea(item){   
	  var tt = calculateCount($(item));
	  if(tt == 0){
		  var str = $(item).val();
		  str = str.substring(0, 140);
		  $(item).val(str);
	  }
	 $(item).parents(".mood_div").first().find(".char_count_span").html(tt);			
  }
  
  //检查字数
  function checkAddArea(item){
	  var tt = calculateCount($(item));
	  if(tt == 0){
		  var str = $(item).val();
		  str = str.substring(0, 140);
		  $(item).val(str);
	  }
	  $(item).parents(".reply_add_div").first().find(".char_count_span").html(tt);					
  }
  function clickExpression(item, event) {
	    	$.expression.replyExpressionInit($(item), $(item).parents("#expression_span").first());
    	}
    	function reply_click(item,event) {
	        var status_div = $(item).parents(".status_div").first();
	        var replay_div= status_div.find(".reply_add_div");
	        var replay_area = replay_div.find(".reply_add_area");	      
	        replay_area.focus();
	       
	        replay_area.val("回复"+ jQuery.trim($(item).parents(".reply_infomation_div").find(".replay_name").html()) + ":");
	        //parentId = $(item).attr("id");
	        status_div.find(".reply_add_div").find("#parentId").val($(item).attr("id"));
	        replay_div.trigger("click");
    	}
 		function focusArea(item,event) { 		
 		 	var obj=$(item).find(".reply_add_area");
 			if(statusOptions.reply_area!=null&&statusOptions.reply_area!=undefined)
 			{
 				if(obj!=statusOptions.reply_area)
 				{
 					Restore();	  
 				}      	
	        }	      
	        statusOptions.reply_area = obj;
	        if (obj.val() == "添加回复") {
	            obj.val("");
	        }
	        obj.css("width", "445px").css("height", "47px").css("margin-left",
				"27px");
	        var parent = obj.parents(".status_div");
	        var photo = parent.find(".reply_photo_span");
	        if (!photo.is(":visible")) {
	            var area = parent.find(".reply_add_area");
	            var operate = parent.find(".reply_operate_div");
	            photo.show();
	            operate.show();
	        }
	       stopPropagation(event);
	    }

    function clickReplyButton(item) {
        $.expression.reply_status($(item).parents(".status_div").first().find(".reply_add_area"), $(item));
    }

    function replyMood(item) {
        replyMoodTo(item, "publishReplyMood.action");
    }

    function replyMyMood(item) {
        replyMoodTo(item, "publishReplyMyMood.action");
    }

    //回复心情
    function replyMoodTo(item, str) {
        var url = $(item).attr("name") + ".action";
        //parentId = $(item).parent().find("input[name='parentId']").val();
        var parentId = $(item).parents(".reply_submit_div").first().find("#parentId").val();

        var msg = str;
        msg = jQuery.trim(msg);
        if (msg == '' || msg == null || msg == undefined) {
            return;
        }
        
        //var url = 'publishReplyMood.action';
        var or = Number($(item).parents(".reply_submit_div").first().find("#orders").val());
        var moodId = $(item).parents(".reply_submit_div").first().find("#moodId").val();
        var params = {
            'replyMood.message': msg,
            'replyMood.parentId': parentId,
            'replyMood.moodPersonId': $(item).parents(".reply_submit_div").first().find(
				"#moodPersonId").val(),
            'replyMood.moodId': moodId,
            'replyMood.orders': or
        };
        
        new TopNAjax().ajax({
    		url: url,
    		data: params,
    		async : false,
    		loading: false,
    		disabledButtonId : "reply_button"+moodId,
    		success: function (msg) {
	        	$(item).parents(".status_div").first().find("#all_replies").append(msg);
	            $(item).parents(".reply_submit_div").first().find("#orders").val(or + 1);
            }
    	});
    }

    /*
    * 显示所有的回复
    */
    function showAllReplies(item) {
        var moodID = $(item).parents(".reply_detials_div").first().find("#moodId")
			.val();
        var moodPersonId = $(item).parents(".reply_detials_div").first().find(
			"#parentId").val();
        // var str = 'personId='+
        // $(item).parent().find("input[name='moodPersonId']").val()+
        // '&moodId='+$(item).parent().find("input[name='moodId']").val();
        var str = 'personId=' + moodPersonId + '&moodId=' + moodID;
        var obj = $(item);
        var temp_add = $(item).parent().parent().find(".reply_add_div");
        $.ajax({
            type: "post",
            url: 'getMoodAllReplies.action',
            data: str,
            cache: false,
            error: function () {
                alert('加载页面时出错！');
            },
            success: function (msg) {
            	var dd = obj.parents("#all_replies").first();
            	dd.html(msg);
            }
        });
    }

    /*
    * 显示我的心情的所有的回复
    */
    function showMeAllReplies(item) {
        var moodID = $(item).parents(".reply_detials_div").first().find("#moodId")
			.val();
        var moodPersonId = $(item).parents(".reply_detials_div").first().find(
			"#moodPersonId").val();
        var str = 'personId=' + moodPersonId + '&moodId=' + moodID;
        var obj = $(item);
        var temp_add = $(item).parent().parent().find(".reply_add_div");
        $.ajax({
            type: "post",
            url: 'getMyMoodAllReplies.action',
            data: str,
            cache: false,
            error: function () {
                alert('加载页面时出错！');
            },
            success: function (msg) {
            	var dd = obj.parents("#all_replies").first();
            	dd.html(msg);
            }
        });
    }
   

    function Restore() {
    	if(statusOptions.reply_area==null || statusOptions.reply_area==undefined)
    	{
    		return;
    	}
        if (jQuery.trim($(statusOptions.reply_area).val()) != '') {
            return;
        }
           statusOptions.reply_area.css("width", "475px").css("height", "20px").css(
				"margin-left", "0px");
            var parent = statusOptions.reply_area.parents(".status_div");
            var photo = parent.find(".reply_photo_span");
            var area = parent.find(".reply_add_area");
            area.val("添加回复");
            var operate = parent.find(".reply_operate_div");
            photo.hide();
            operate.hide();
    }

    function dymiclength() {

    }

    function packUp(item) {
        var status_div = $(item).parents(".status_div").first();
        if ($(item).html() == "收起回复") {
            $(item).html("回复(" + status_div.find("#orders").val() + ")");
            status_div.find(".reply_detials_div").css("display","none");
        } else {
            $(item).html("收起回复");
            status_div.find(".reply_detials_div").css("display","block");
        }       
    }

    /*
    * 发表心情
    */
    function publishMood(moodText) {
        moodText = jQuery.trim(moodText);
        if (moodText == '' || moodText == null || moodText == undefined) {
            return;
        }
        var url = 'publishMood.action';
        var params = {
            'mood.message': moodText
        };
        new TopNAjax().ajax({
    		url: url,
    		data: params,
    		async : false,
    		loading: false,
    		disabledButtonId : 'publish_button',
    		success: function (data) {
           	 	$("#middle_status_show_div").prepend(data);
                $("#moodText").val("");
            },
            error : function(){
            	showMessage("友情提示","由于网络原因发送失败，请稍后重试");
            }
    	});
    }

    /*
    * 获取更多心情
    */
    var loadingStateFlag = false; // 标识是否现在在后台加载数据
    function getMoreStatus() {
        if (loadingStateFlag) {
            return;
        }
        loadingStateFlag = true;
        var str = "moodBeginTime=" + $("#moodBeginTime").val() + "&pageNum="
			+ $("#pageNum").val();
        $.ajax({
            type: "post",
            url: 'loadNDayLaterMood.action',
            data: str,
            cache: false,
            error: function () {
                loadingStateFlag = false;
            },
            success: function (msg) {
                if (jQuery.trim(msg) == '') {
                    $("#more_status_a").html("没有跟多显示了");
                    return;
                }
                loadingStateFlag = false;
                $("#middle_status_show_div").append(msg);
                $("#pageNum").val(Number($("#pageNum").val()) + 1);
                
            }
        });
    }

    /*
    * 删除回复
    */
    function deleteRply(item) {
        var replyId = $(item).parents("span").first().find("#hdreply_message_id")
			.val();
        var moodPersonId = $(item).parents(".reply_detials_div").first().find(
			"#moodPersonId").val();
        var moodId = $(item).parents(".reply_detials_div").first().find("#moodId")
			.val();

        var params = {
            'personId': moodPersonId,
            'replyId': replyId,
            'moodId': moodId
        };
        showAskBox("提示", "确定删除此条回复吗？", delReply);

        function delReply() {
            $.post('deleteReply.action', params, function cbf(msg) {
                var temp = $(item).parents(".reply_detials_div").first().find("#orders");
                temp.val(Number(temp.val()) - 1);
                $(item).parents(".status_reply_div").first().remove();
               
            });
        }
    }

    /*
    * 加载自己的心情
    */
    function loadMyStatus() {
        $.ajax({
            type: "post",
            url: 'loadMyStatus.action',
            cache: false,
            error: function () {
                alert('加载页面时出错！');
            },
            success: function (msg) {
            }
        });
    }

    /*
    * 删除心情
    */
    function deleteMood(item) {
        var moodId = item.id;

        var params = {
            'moodId': moodId
        };
        showAskBox("提示", "确定删除此条心情吗？", delThisMood);

        function delThisMood() {
            $.post('deleteMood.action', params, function cbf(msg) {
                $(item).parents(".status_div").first().remove();
            });
        }
    }

    // -------------------下面是留言模块

    function openLeaveMsgReplies(item) {
        $.ajax({
            type: "post",
            url: 'openLeaveMsgReplies.action',
            cache: false,
            data: "leaveMsgId=" + item.name,
            error: function () {
                alert('加载页面时出错！');
            },
            success: function (msg) {
            	$(item).parents(".status_div").first().find("#all_replies").append(msg);
                $(item).remove();
            }
        });
    }
    
    function clickLeaveMsgButton(item) {
        $.expression.reply_leave_status($(item).parents(".status_div").first().find(".reply_add_area"), $(item));
    }
    
    /**
    * 回复留言
    * 
    * @return
    */
    function replyFriendsLeave(item, str) {
        //if (parentId == null || parentId == undefined) {
        var parentId = $(item).parent().find("input[name='parentId']").val();
        //}
        //var msg = $(item).parent().parent().parent().find(".reply_add_area").val();
        str = jQuery.trim(str);
        if (str == '' || str == null || str == undefined) {
            return;
        }
       
        var or = Number($(item).parents(".reply_submit_div").first().find("#orders").val());
        var leaveMsgId = $(item).parents(".reply_submit_div").first().find("#leaveMsgId").val();
        var url = 'replyLeaveMsg.action';
        var params = {
            'leaveMsg': str,
            'parentId': parentId,
            'total': or,
            'leaveMsgId': leaveMsgId
        };
        new TopNAjax().ajax({
    		url: url,
    		data: params,
    		async : true,
    		loading: false,
    		disabledButtonId : "reply_button"+leaveMsgId,
    		success: function (data) {
	        	$(item).parents(".status_div").first().find("#all_replies").append(data);
	        	$(item).parents(".reply_submit_div").first().find("#orders").val(or + 1);
	            $(item).parent().parent().parent().find(".reply_add_area").val("");
            }
    	});
    }

    function deleteLeaveMsg(item) {
        showAskBox("提示", "确定删除此条留言吗？", dlm);
        function dlm() {
            var params = {
                leaveMsgId: item.id
            };
            $.post('deleteLeaveMsg.action', params, function cbf(msg) {
                $(item).parents(".status_div").first().remove();
            });
        }
    }

    function deleteReplyLeaveMsg(item) {
        showAskBox("提示", "确定删除此条留言回复吗？", drlm);
        function drlm() {
            var replyId = $(item).find("#hdreply_leave_id").val();
            var leavaMsgId = $(item).parents(".status_div").first().find(".reply_add_div").find("#leaveMsgId").val();
            var params = {
                leaveMsgId: leavaMsgId,
                replyLeaveMsgId: replyId
            };
            $.post('deleteReplyLeaveMsg.action', params, function cbf(msg) {
                $(item).parents(".status_reply_div").first().remove();
            });
        }
    }
    
    function leaveMsgDB(str, id){
    	if(0 == id || jQuery.trim(str) == ''){
    		return;
    	}
    	var params = {
    			leaveMsg: str,
    			leaveTo: id
         };
    	new TopNAjax().ajax({
    		url: 'visitorLeaveMsg.action',
    		data: params,
    		async : true,
    		loading: false,
    		disabledButtonId : "leave_message_button",
    		success: function (data) {
	    		$("#middle_status_show_div").prepend(data);
	            $("#moodText").val("");
            }
    	});
    }