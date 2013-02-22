//各种定位
function InitPosition(obj,flag){
					var scrollWidth=$(window).scrollLeft();
					var width=$(window).width();
					var height=$(window).height();
					var scrollTop=$(window).scrollTop();
					var objWidth=obj.width();
					var objHeight=obj.height();
					var left; 
					var top;
					// MiniBar的定位
					if(flag==1)
					{
						left = scrollWidth + width - objWidth-15;
						top=height-objHeight+scrollTop;
						
					}
					// 好友列表的的定位
					else if(flag==2)
					{
						left= scrollWidth + width - objWidth-25;
						top=height-objHeight+scrollTop-35;
						
					}
					// 居中的
					else if(flag == 3){
							left = scrollWidth+ (width- objWidth) / 2;
							top=scrollTop+(height-objHeight)/2;
					}
					// 聊天框的定位
					else if(flag==4)
					{
						left = scrollWidth + width - objWidth-185;
						top=height-objHeight+scrollTop-35;						
					}
					// 绝对贴上居中
					else if(flag==5)
					{
						left = scrollWidth+ (width- objWidth) / 2;
						top=scrollTop;				
					}
					obj.css("position","absolute").css("left",left+"px").css("top",top+"px").css("display","inline");
}

/**
 * ../user_picture/2011-6/ordinary/small_150_115/a0ef07e9-4bc1-42c5-adfb-8b078d24d9c0.jpg
 * 
 * @param orinal
 * @return
 */
function dealImageURL(orinal){
	var t = orinal.substring(orinal.indexOf("user_picture"));
	return "../" + t;
}
// 设置遮罩层的大小
function setBg(obj)
{
	var width=$(window).width();
	var height=$(window).height();
	var bgheight=$("body").height();
	var bgwidth=$("body").width();
	var finalWidth=width>bgwidth?width:bgwidth;
	var finalHeight=height>bgheight?height:bgheight;
	obj.height(finalHeight).width(finalWidth).show();					
}
// 删除关于IE6浏览器提示
function removeTipForIe6()
	{
		$("#tip_for_ie_div").remove();
	}	
// 检查IE6内核的浏览器
function checkIeVersion()
	{
					
		if($.browser.msie && $.browser.version=="6.0")
			{
				 var tip =" <div id='tip_for_ie_div' style='display:none;width:900px;border:2px solid #666;border-top:0px; height:40px;background-color:white;font-size:13px;text-align:center;'>	<p>您当前所使用的IE6或者(IE6)内核的浏览器，为了增强你的网站体验，请尽快更新至IE8或者以上版本!多谢配合。</p><p style='text-align:right;line-height:15px;'><a href='javascript:void(0);' onclick='removeTipForIe6();'>关闭</a></p></div>";
				  $("body").append(tip);
				  InitPosition($("#tip_for_ie_div"),5);  
			}					
				 
	}
// 获取flash对象
function getFlashMovieObject(movieName){
  if (window.document[movieName]){
    return window.document[movieName];
  }else if (navigator.appName.indexOf("Microsoft")==-1){
    if (document.embeds && document.embeds[movieName])
    return document.embeds[movieName];
  }else{
    return document.getElementById(movieName);
  }
}
// 检查textarea 的字数
function checkCount(item){
    var length ;
	if(item!=undefined){ 
       	length = $(item).val().length;
    }
    return length > 140?false:true;
}

// 统计textarea的字数
function calculateCount(item){
	// var item =$("#"+id);
	if(item!=undefined){ 
		var reCount=140-item.val().length;
		 return reCount>=0?reCount:0;
	}
	return 0;
}

function showUpdateSuccess(){
	showTip("提示","修改成功");
}

function showAddSuccess(msg){
	if(msg == null || msg == ''){
		msg = "修改成功";
	}
	showTip("提示", msg);
}

function stopPropagation(event)
{
	 var e =event;
	 if(e==undefined)
	 {
	 	return;
	 }
	if (e && e.stopPropagation)
		        e.stopPropagation();
		    else
		        window.event.cancelBubble=true; 
}

function opencenterwindow(url, width, height) {
	if ($.browser.msie) {
		window.showModalDialog(url, window, 'dialogWidth=' + width
				+ 'px;dialogHeight=' + height + 'px');
	} else {
		window
				.open(
						url,
						"",
						"toolbar=no,height="
								+ height
								+ ",width="
								+ width
								+ ",left="
								+ (window.screen.width - width)/2
								+ ","
								+ "top="
								+ (window.screen.height - height)/2
								+ ",location=no,directories=no,menubar=no,scrollbars=no,resizable=no,status=no");
	}
}

Array.prototype.remove = function(s) {
	for ( var i = 0; i < this.length; i++) {
		if (s == this[i])
			this.splice(i, 1);
	}
}

// 添加一个原形函数
Array.prototype.del = function(n) { // n表示第几项，从0开始算起。
	// prototype为对象原型，注意这里为对象增加自定义方法的方法。
	if (n < 0) // 如果n<0，则不进行任何操作。
		return this;
	else
		return this.slice(0, n).concat(this.slice(n + 1, this.length));
	/*
	 * concat方法：返回一个新数组，这个新数组是由两个或更多数组组合而成的。
	 * 这里就是返回this.slice(0,n)/this.slice(n+1,this.length) 组成的新数组，这中间，刚好少了第n项。
	 * slice方法： 返回一个数组的一段，两个参数，分别指定开始和结束的位置。
	 */
}

/**
 * Simple Map
 * 
 * 
 * var m = new Map(); m.put('key','value'); ... var s = "";
 * m.each(function(key,value,index){ s += index+":"+ key+"="+value+"\n"; });
 * alert(s);
 * 
 * @author 徐涛
 */
function Map() {
	/** 存放键的数组(遍历用到) */
	this.keys = new Array();
	/** 存放数据 */
	this.data = new Object();

	/**
	 * 放入一个键值对
	 * 
	 * @param {String}
	 *            key
	 * @param {Object}
	 *            value
	 */
	this.put = function(key, value) {
		if (this.data[key] == null) {
			this.keys.push(key);
		}
		this.data[key] = value;
	};

	/**
	 * 获取某键对应的值
	 * 
	 * @param {String}
	 *            key
	 * @return {Object} value
	 */
	this.get = function(key) {
		return this.data[key];
	};

	/**
	 * 删除一个键值对
	 * 
	 * @param {String}
	 *            key
	 */
	this.remove = function(key) {
		this.keys.remove(key);
		this.data[key] = null;
	};

	/**
	 * 遍历Map,执行处理函数
	 * 
	 * @param {Function}
	 *            回调函数 function(key,value,index){..}
	 */
	this.each = function(fn) {
		if (typeof fn != 'function') {
			return;
		}
		var len = this.keys.length;
		for ( var i = 0; i < len; i++) {
			var k = this.keys[i];
			fn(k, this.data[k], i);
		}
	};

	/**
	 * 获取键值数组(类似Java的entrySet())
	 * 
	 * @return 键值对象{key,value}的数组
	 */
	this.entrys = function() {
		var len = this.keys.length;
		var entrys = new Array(len);
		for ( var i = 0; i < len; i++) {
			entrys[i] = {
				key : this.keys[i],
				value : this.data[i]
			};
		}
		return entrys;
	};

	/**
	 * 判断Map是否为空
	 */
	this.isEmpty = function() {
		return this.keys.length == 0;
	};

	/**
	 * 获取键值对数量
	 */
	this.size = function() {
		return this.keys.length;
	};

	/**
	 * 重写toString
	 */
	this.toString = function() {
		var s = "{";
		for ( var i = 0; i < this.keys.length; i++, s += ',') {
			var k = this.keys[i];
			s += k + "=" + this.data[k];
		}
		s += "}";
		return s;
	};
}

// 封装jquery 的ajax实现
TopNAjax = function() {

	var async = true; // 调试模式开关
	var url = null; // 
	var type = 'post';
	var timeout = 60 * 1000; // 请求超时
	var cache = true;
	var sendType = 'json'; // 发送数据的方式,如果采用json方式就传入json，否则就采用prarm方式
	var data = null;
	var loading = true;
	var loadingAreaId = 'content_middle_div';
	var disabledButtonId = '';

	// ajax请求
	this.ajax = function(options) {
		var opt = $.extend( {
			async : true, // 调试模式开关
			url : null, // 
			type : 'post',
			timeout : 60 * 1000, // 请求超时
			beforeSend : this.beforeSend,
			complete : this.complete,
			error : this.error,
			cache : true,
			sendType : 'json', // 发送数据的方式
			data : null,
			success : this.success,
			loading : true,
			loadingAreaId : 'content_middle_div',
			disabledButtonId : ''
		}, options);

		async = opt.async;
		url = opt.url;
		type = opt.type;
		timeout = opt.timeout;
		cache = opt.cache;
		sendType = opt.sendType;
		beforeSend = opt.beforeSend;
		error = opt.error;
		data = opt.data;
		success = opt.success;
		complete = opt.complete;
		loading = opt.loading;
		loadingAreaId = opt.loadingAreaId;
		disabledButtonId = opt.disabledButtonId;
		// 在这个之前可以做一些操作
		$.ajax( {
			type : type,
			url : url,
			async : async,
			data : data,
			cache : cache,
			timeout : timeout,
			error : error,
			beforeSend : beforeSend,
			success : success,
			complete : complete
		});

	};

	this.error = function() {
		if(disabledButtonId != ''){
			var disabledButton = $("#disabledButtonId");
			if(disabledButton.length > 0){
				disabledButton.removeAttr("disabled");
			}
		}
	};
	
	this.beforeSend = function() {
		if(disabledButtonId != ''){
			var disabledButton = $("#"+disabledButtonId);
			if(disabledButton.length > 0){
				disabledButton.attr("disabled", "disabled");
			}
		}
		if(loading && loadingAreaId != ''){
			var loadingStr = '<center id="loading" style="padding-top: 100px">'
				 +'	<img src="../pictures/loading_page.gif" width="500" />'
				 +'</center>';
			$("#"+loadingAreaId).html(loadingStr);			
		}
	};
	// 完成ajax链接
	this.complete = function() {
		if(disabledButtonId != ''){
			var disabledButton = $("#"+disabledButtonId);
			if(disabledButton.length > 0){
				disabledButton.removeAttr("disabled");
			}
		}
	};
	// override
	this.success = function() {

	};

	// 将json字符串解析成 & 链接方式
	this.parseJson = function(jsonStr) {
		var json = eval('(' + jsonStr + ')');
		$.each(json, function(i) {
			var id = json[i].id;
			var name = json[i].name;
			var url = json[i].url;
			var type = json[i].type;
			var p = new Person(id, name, url, type);
			friends.put(id, p);
		});
	};

};

/**
 * jQuery.query - Query String Modification and Creation for jQuery Written by
 * Blair Mitchelmore (blair DOT mitchelmore AT gmail DOT com) Licensed under the
 * WTFPL (http://sam.zoy.org/wtfpl/). Date: 2009/8/13
 * 
 * @author Blair Mitchelmore
 * @version 2.1.7
 * 
 */
new function(settings) {
	// Various Settings
	var $separator = settings.separator || '&';
	var $spaces = settings.spaces === false ? false : true;
	var $suffix = settings.suffix === false ? '' : '[]';
	var $prefix = settings.prefix === false ? false : true;
	var $hash = $prefix ? settings.hash === true ? "#" : "?" : "";
	var $numbers = settings.numbers === false ? false : true;

	jQuery.query = new function() {
		var is = function(o, t) {
			return o != undefined && o !== null
					&& (!!t ? o.constructor == t : true);
		};
		var parse = function(path) {
			var m, rx = /\[([^[]*)\]/g, match = /^([^[]+)(\[.*\])?$/.exec(path), base = match[1], tokens = [];
			while (m = rx.exec(match[2]))
				tokens.push(m[1]);
			return [ base, tokens ];
		};
		var set = function(target, tokens, value) {
			var o, token = tokens.shift();
			if (typeof target != 'object')
				target = null;
			if (token === "") {
				if (!target)
					target = [];
				if (is(target, Array)) {
					target.push(tokens.length == 0 ? value : set(null, tokens
							.slice(0), value));
				} else if (is(target, Object)) {
					var i = 0;
					while (target[i++] != null)
						;
					target[--i] = tokens.length == 0 ? value : set(target[i],
							tokens.slice(0), value);
				} else {
					target = [];
					target.push(tokens.length == 0 ? value : set(null, tokens
							.slice(0), value));
				}
			} else if (token && token.match(/^\s*[0-9]+\s*$/)) {
				var index = parseInt(token, 10);
				if (!target)
					target = [];
				target[index] = tokens.length == 0 ? value : set(target[index],
						tokens.slice(0), value);
			} else if (token) {
				var index = token.replace(/^\s*|\s*$/g, "");
				if (!target)
					target = {};
				if (is(target, Array)) {
					var temp = {};
					for ( var i = 0; i < target.length; ++i) {
						temp[i] = target[i];
					}
					target = temp;
				}
				target[index] = tokens.length == 0 ? value : set(target[index],
						tokens.slice(0), value);
			} else {
				return value;
			}
			return target;
		};

		var queryObject = function(a) {
			var self = this;
			self.keys = {};

			if (a.queryObject) {
				jQuery.each(a.get(), function(key, val) {
					self.SET(key, val);
				});
			} else {
				jQuery.each(arguments, function() {
					var q = "" + this;
					q = q.replace(/^[?#]/, ''); // remove any leading ? || #
						q = q.replace(/[;&]$/, ''); // remove any trailing & ||
													// ;
						if ($spaces)
							q = q.replace(/[+]/g, ' '); // replace +'s with
														// spaces

						jQuery.each(q.split(/[&;]/), function() {
							var key = decodeURIComponent(this.split('=')[0]
									|| "");
							var val = decodeURIComponent(this.split('=')[1]
									|| "");

							if (!key)
								return;

							if ($numbers) {
								if (/^[+-]?[0-9]+\.[0-9]*$/.test(val)) // simple
																		// float
																		// regex
									val = parseFloat(val);
								else if (/^[+-]?[0-9]+$/.test(val)) // simple
																	// int regex
									val = parseInt(val, 10);
							}

							val = (!val && val !== 0) ? true : val;

							if (val !== false && val !== true
									&& typeof val != 'number')
								val = val;

							self.SET(key, val);
						});
					});
			}
			return self;
		};

		queryObject.prototype = {
			queryObject : true,
			has : function(key, type) {
				var value = this.get(key);
				return is(value, type);
			},
			GET : function(key) {
				if (!is(key))
					return this.keys;
				var parsed = parse(key), base = parsed[0], tokens = parsed[1];
				var target = this.keys[base];
				while (target != null && tokens.length != 0) {
					target = target[tokens.shift()];
				}
				return typeof target == 'number' ? target : target || "";
			},
			get : function(key) {
				var target = this.GET(key);
				if (is(target, Object))
					return jQuery.extend(true, {}, target);
				else if (is(target, Array))
					return target.slice(0);
				return target;
			},
			SET : function(key, val) {
				var value = !is(val) ? null : val;
				var parsed = parse(key), base = parsed[0], tokens = parsed[1];
				var target = this.keys[base];
				this.keys[base] = set(target, tokens.slice(0), value);
				return this;
			},
			set : function(key, val) {
				return this.copy().SET(key, val);
			},
			REMOVE : function(key) {
				return this.SET(key, null).COMPACT();
			},
			remove : function(key) {
				return this.copy().REMOVE(key);
			},
			EMPTY : function() {
				var self = this;
				jQuery.each(self.keys, function(key, value) {
					delete self.keys[key];
				});
				return self;
			},
			load : function(url) {
				var hash = url.replace(/^.*?[#](.+?)(?:\?.+)?$/, "$1");
				var search = url.replace(/^.*?[?](.+?)(?:#.+)?$/, "$1");
				return new queryObject(url.length == search.length ? ''
						: search, url.length == hash.length ? '' : hash);
			},
			empty : function() {
				return this.copy().EMPTY();
			},
			copy : function() {
				return new queryObject(this);
			},
			COMPACT : function() {
				function build(orig) {
					var obj = typeof orig == "object" ? is(orig, Array) ? []
							: {} : orig;
					if (typeof orig == 'object') {
						function add(o, key, value) {
							if (is(o, Array))
								o.push(value);
							else
								o[key] = value;
						}
						jQuery.each(orig, function(key, value) {
							if (!is(value))
								return true;
							add(obj, key, build(value));
						});
					}
					return obj;
				}
				this.keys = build(this.keys);
				return this;
			},
			compact : function() {
				return this.copy().COMPACT();
			},
			toString : function() {
				var i = 0, queryString = [], chunks = [], self = this;
				var encode = function(str) {
					str = str + "";
					if ($spaces)
						str = str.replace(/ /g, "+");
					return encodeURIComponent(str);
				};
				var addFields = function(arr, key, value) {
					if (!is(value) || value === false)
						return;
					var o = [ encode(key) ];
					if (value !== true) {
						o.push("=");
						o.push(encode(value));
					}
					arr.push(o.join(""));
				};
				var build = function(obj, base) {
					var newKey = function(key) {
						return !base || base == "" ? [ key ].join("") : [ base,
								"[", key, "]" ].join("");
					};
					jQuery.each(obj, function(key, value) {
						if (typeof value == 'object')
							build(value, newKey(key));
						else
							addFields(chunks, newKey(key), value);
					});
				};

				build(this.keys);

				if (chunks.length > 0)
					queryString.push($hash);
				queryString.push(chunks.join($separator));

				return queryString.join("");
			}
		};

		return new queryObject(location.search, location.hash);
	};
}(jQuery.query || {}); // Pass in jQuery.query as settings object

// jQuery SWFObject v1.1.1 MIT/GPL @jon_neal
// http://jquery.thewikies.com/swfobject
(function(f, h, i) {
	function k(a, c) {
		var b = (a[0] || 0) - (c[0] || 0);
		return b > 0 || !b && a.length > 0 && k(a.slice(1), c.slice(1))
	}
	function l(a) {
		if (typeof a != g)
			return a;
		var c = [], b = "";
		for ( var d in a) {
			b = typeof a[d] == g ? l(a[d]) : [ d, m ? encodeURI(a[d]) : a[d] ]
					.join("=");
			c.push(b)
		}
		return c.join("&")
	}
	function n(a) {
		var c = [];
		for ( var b in a)
			a[b] && c.push( [ b, '="', a[b], '"' ].join(""));
		return c.join(" ")
	}
	function o(a) {
		var c = [];
		for ( var b in a)
			c.push( [ '<param name="', b, '" value="', l(a[b]), '" />' ]
					.join(""));
		return c.join("")
	}
	var g = "object", m = true;
	try {
		var j = i.description
				|| function() {
					return (new i("ShockwaveFlash.ShockwaveFlash"))
							.GetVariable("$version")
				}()
	} catch (p) {
		j = "Unavailable"
	}
	var e = j.match(/\d+/g) || [ 0 ];
	f[h] = {
		available : e[0] > 0,
		activeX : i && !i.name,
		version : {
			original : j,
			array : e,
			string : e.join("."),
			major : parseInt(e[0], 10) || 0,
			minor : parseInt(e[1], 10) || 0,
			release : parseInt(e[2], 10) || 0
		},
		hasVersion : function(a) {
			a = /string|number/.test(typeof a) ? a.toString().split(".")
					: /object/.test(typeof a) ? [ a.major, a.minor ] : a
							|| [ 0, 0 ];
			return k(e, a)
		},
		encodeParams : true,
		expressInstall : "expressInstall.swf",
		expressInstallIsActive : false,
		create : function(a) {
			if (!a.swf || this.expressInstallIsActive || !this.available
					&& !a.hasVersionFail)
				return false;
			if (!this.hasVersion(a.hasVersion || 1)) {
				this.expressInstallIsActive = true;
				if (typeof a.hasVersionFail == "function")
					if (!a.hasVersionFail.apply(a))
						return false;
				a = {
					swf : a.expressInstall || this.expressInstall,
					height : 137,
					width : 214,
					flashvars : {
						MMredirectURL : location.href,
						MMplayerType : this.activeX ? "ActiveX" : "PlugIn",
						MMdoctitle : document.title.slice(0, 47)
								+ " - Flash Player Installation"
					}
				}
			}
			attrs = {
				data : a.swf,
				type : "application/x-shockwave-flash",
				id : a.id || "flash_" + Math.floor(Math.random() * 999999999),
				width : a.width || 320,
				height : a.height || 180,
				style : a.style || ""
			};
			m = typeof a.useEncode !== "undefined" ? a.useEncode
					: this.encodeParams;
			a.movie = a.swf;
			a.wmode = a.wmode || "opaque";
			delete a.fallback;
			delete a.hasVersion;
			delete a.hasVersionFail;
			delete a.height;
			delete a.id;
			delete a.swf;
			delete a.useEncode;
			delete a.width;
			var c = document.createElement("div");
			c.innerHTML = [ "<object ", n(attrs), ">", o(a), "</object>" ]
					.join("");
			return c.firstChild
		}
	};
	f.fn[h] = function(a) {
		var c = this.find(g).andSelf().filter(g);
		/string|object/.test(typeof a) && this.each(function() {
			var b = f(this), d;
			a = typeof a == g ? a : {
				swf : a
			};
			a.fallback = this;
			if (d = f[h].create(a)) {
				b.children().remove();
				b.html(d)
			}
		});
		typeof a == "function"
				&& c.each(function() {
					var b = this;
					b.jsInteractionTimeoutMs = b.jsInteractionTimeoutMs || 0;
					if (b.jsInteractionTimeoutMs < 660)
						b.clientWidth || b.clientHeight ? a.call(b)
								: setTimeout(function() {
									f(b)[h](a)
								}, b.jsInteractionTimeoutMs + 66)
				});
		return c
	}
})(jQuery, "flash", navigator.plugins["Shockwave Flash"]
		|| window.ActiveXObject);

jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires
															// attribute,
															// max-age is not
															// supported by IE
        }
        // CAUTION: Needed to parenthesize options.path and options.domain
        // in the following expressions, otherwise they evaluate to undefined
        // in the packed version for some reason...
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};


function resolvePngInIe6()
{
if($.browser.msie && $.browser.version=="6.0"){
			   $("*").each(function(){
			   		var s=$(this).css('background-image');
						if(s.indexOf(".png")!=-1){
							DD_belatedPNG.fix("."+$(this).attr("class"));
						}			   	
			   });}

}

// 下面是处理为题6 png图片问题
var DD_belatedPNG={ns:"DD_belatedPNG",imgSize:{},delay:10,nodesFixed:0,createVmlNameSpace:function(){if(document.namespaces&&!document.namespaces[this.ns]){document.namespaces.add(this.ns,"urn:schemas-microsoft-com:vml")}},createVmlStyleSheet:function(){var b,a;b=document.createElement("style");b.setAttribute("media","screen");document.documentElement.firstChild.insertBefore(b,document.documentElement.firstChild.firstChild);if(b.styleSheet){b=b.styleSheet;b.addRule(this.ns+"\\:*","{behavior:url(#default#VML)}");b.addRule(this.ns+"\\:shape","position:absolute;");b.addRule("img."+this.ns+"_sizeFinder","behavior:none; border:none; position:absolute; z-index:-1; top:-10000px; visibility:hidden;");this.screenStyleSheet=b;a=document.createElement("style");a.setAttribute("media","print");document.documentElement.firstChild.insertBefore(a,document.documentElement.firstChild.firstChild);a=a.styleSheet;a.addRule(this.ns+"\\:*","{display: none !important;}");a.addRule("img."+this.ns+"_sizeFinder","{display: none !important;}")}},readPropertyChange:function(){var b,c,a;b=event.srcElement;if(!b.vmlInitiated){return}if(event.propertyName.search("background")!=-1||event.propertyName.search("border")!=-1){DD_belatedPNG.applyVML(b)}if(event.propertyName=="style.display"){c=(b.currentStyle.display=="none")?"none":"block";for(a in b.vml){if(b.vml.hasOwnProperty(a)){b.vml[a].shape.style.display=c}}}if(event.propertyName.search("filter")!=-1){DD_belatedPNG.vmlOpacity(b)}},vmlOpacity:function(b){if(b.currentStyle.filter.search("lpha")!=-1){var a=b.currentStyle.filter;a=parseInt(a.substring(a.lastIndexOf("=")+1,a.lastIndexOf(")")),10)/100;b.vml.color.shape.style.filter=b.currentStyle.filter;b.vml.image.fill.opacity=a}},handlePseudoHover:function(a){setTimeout(function(){DD_belatedPNG.applyVML(a)},1)},fix:function(a){if(this.screenStyleSheet){var c,b;c=a.split(",");for(b=0;b<c.length;b++){this.screenStyleSheet.addRule(c[b],"behavior:expression(DD_belatedPNG.fixPng(this))")}}},applyVML:function(a){a.runtimeStyle.cssText="";this.vmlFill(a);this.vmlOffsets(a);this.vmlOpacity(a);if(a.isImg){this.copyImageBorders(a)}},attachHandlers:function(i){var d,c,g,e,b,f;d=this;c={resize:"vmlOffsets",move:"vmlOffsets"};if(i.nodeName=="A"){e={mouseleave:"handlePseudoHover",mouseenter:"handlePseudoHover",focus:"handlePseudoHover",blur:"handlePseudoHover"};for(b in e){if(e.hasOwnProperty(b)){c[b]=e[b]}}}for(f in c){if(c.hasOwnProperty(f)){g=function(){d[c[f]](i)};i.attachEvent("on"+f,g)}}i.attachEvent("onpropertychange",this.readPropertyChange)},giveLayout:function(a){a.style.zoom=1;if(a.currentStyle.position=="static"){a.style.position="relative"}},copyImageBorders:function(b){var c,a;c={borderStyle:true,borderWidth:true,borderColor:true};for(a in c){if(c.hasOwnProperty(a)){b.vml.color.shape.style[a]=b.currentStyle[a]}}},vmlFill:function(e){if(!e.currentStyle){return}else{var d,f,g,b,a,c;d=e.currentStyle}for(b in e.vml){if(e.vml.hasOwnProperty(b)){e.vml[b].shape.style.zIndex=d.zIndex}}e.runtimeStyle.backgroundColor="";e.runtimeStyle.backgroundImage="";f=true;if(d.backgroundImage!="none"||e.isImg){if(!e.isImg){e.vmlBg=d.backgroundImage;e.vmlBg=e.vmlBg.substr(5,e.vmlBg.lastIndexOf('")')-5)}else{e.vmlBg=e.src}g=this;if(!g.imgSize[e.vmlBg]){a=document.createElement("img");g.imgSize[e.vmlBg]=a;a.className=g.ns+"_sizeFinder";a.runtimeStyle.cssText="behavior:none; position:absolute; left:-10000px; top:-10000px; border:none; margin:0; padding:0;";c=function(){this.width=this.offsetWidth;this.height=this.offsetHeight;g.vmlOffsets(e)};a.attachEvent("onload",c);a.src=e.vmlBg;a.removeAttribute("width");a.removeAttribute("height");document.body.insertBefore(a,document.body.firstChild)}e.vml.image.fill.src=e.vmlBg;f=false}e.vml.image.fill.on=!f;e.vml.image.fill.color="none";e.vml.color.shape.style.backgroundColor=d.backgroundColor;e.runtimeStyle.backgroundImage="none";e.runtimeStyle.backgroundColor="transparent"},vmlOffsets:function(d){var h,n,a,e,g,m,f,l,j,i,k;h=d.currentStyle;n={W:d.clientWidth+1,H:d.clientHeight+1,w:this.imgSize[d.vmlBg].width,h:this.imgSize[d.vmlBg].height,L:d.offsetLeft,T:d.offsetTop,bLW:d.clientLeft,bTW:d.clientTop};a=(n.L+n.bLW==1)?1:0;e=function(b,p,q,c,s,u){b.coordsize=c+","+s;b.coordorigin=u+","+u;b.path="m0,0l"+c+",0l"+c+","+s+"l0,"+s+" xe";b.style.width=c+"px";b.style.height=s+"px";b.style.left=p+"px";b.style.top=q+"px"};e(d.vml.color.shape,(n.L+(d.isImg?0:n.bLW)),(n.T+(d.isImg?0:n.bTW)),(n.W-1),(n.H-1),0);e(d.vml.image.shape,(n.L+n.bLW),(n.T+n.bTW),(n.W),(n.H),1);g={X:0,Y:0};if(d.isImg){g.X=parseInt(h.paddingLeft,10)+1;g.Y=parseInt(h.paddingTop,10)+1}else{for(j in g){if(g.hasOwnProperty(j)){this.figurePercentage(g,n,j,h["backgroundPosition"+j])}}}d.vml.image.fill.position=(g.X/n.W)+","+(g.Y/n.H);m=h.backgroundRepeat;f={T:1,R:n.W+a,B:n.H,L:1+a};l={X:{b1:"L",b2:"R",d:"W"},Y:{b1:"T",b2:"B",d:"H"}};if(m!="repeat"||d.isImg){i={T:(g.Y),R:(g.X+n.w),B:(g.Y+n.h),L:(g.X)};if(m.search("repeat-")!=-1){k=m.split("repeat-")[1].toUpperCase();i[l[k].b1]=1;i[l[k].b2]=n[l[k].d]}if(i.B>n.H){i.B=n.H}d.vml.image.shape.style.clip="rect("+i.T+"px "+(i.R+a)+"px "+i.B+"px "+(i.L+a)+"px)"}else{d.vml.image.shape.style.clip="rect("+f.T+"px "+f.R+"px "+f.B+"px "+f.L+"px)"}},figurePercentage:function(d,c,f,a){var b,e;e=true;b=(f=="X");switch(a){case"left":case"top":d[f]=0;break;case"center":d[f]=0.5;break;case"right":case"bottom":d[f]=1;break;default:if(a.search("%")!=-1){d[f]=parseInt(a,10)/100}else{e=false}}d[f]=Math.ceil(e?((c[b?"W":"H"]*d[f])-(c[b?"w":"h"]*d[f])):parseInt(a,10));if(d[f]%2===0){d[f]++}return d[f]},fixPng:function(c){c.style.behavior="none";var g,b,f,a,d;if(c.nodeName=="BODY"||c.nodeName=="TD"||c.nodeName=="TR"){return}c.isImg=false;if(c.nodeName=="IMG"){if(c.src.toLowerCase().search(/\.png$/)!=-1){c.isImg=true;c.style.visibility="hidden"}else{return}}else{if(c.currentStyle.backgroundImage.toLowerCase().search(".png")==-1){return}}g=DD_belatedPNG;c.vml={color:{},image:{}};b={shape:{},fill:{}};for(a in c.vml){if(c.vml.hasOwnProperty(a)){for(d in b){if(b.hasOwnProperty(d)){f=g.ns+":"+d;c.vml[a][d]=document.createElement(f)}}c.vml[a].shape.stroked=false;c.vml[a].shape.appendChild(c.vml[a].fill);c.parentNode.insertBefore(c.vml[a].shape,c)}}c.vml.image.shape.fillcolor="none";c.vml.image.fill.type="tile";c.vml.color.fill.on=false;g.attachHandlers(c);g.giveLayout(c);g.giveLayout(c.offsetParent);c.vmlInitiated=true;g.applyVML(c)}};try{document.execCommand("BackgroundImageCache",false,true)}catch(r){}DD_belatedPNG.createVmlNameSpace();DD_belatedPNG.createVmlStyleSheet();
