/**
 * jquery IframeAjax
 * 
 * @徐涛 2010.5.5
 * 
 */

(function($) {
	window.iframeAjax = {
			
		userIframe:false,

		
		debug : false,

		
		defaultUrl : null,

		
		currHash : null,

		
		intervalID : null,

		
		selector : "",

		
		base : "",

		filter : null,

		
		currUrl : "",

	
		ifSame : true,

		
		attr : "",

	
		script : "",

		
		autoStart : true,
		
		rendering: false,
		
		_buffer : null,
		
		isLoading:false,

		
		ini : function(options) {
			var opt = $.extend( {
				debug : false, 
				defaultUrl : null, 
				selector : ".ajax",
				base : "#base", 
				before : this.before, 
				success : this.success, 
				after : this.after, 
				ifSame : true, 
				attr : "href", 
				script : "include", 
				autoStart : true, 
				filter : {}
			// 杩囨护鍣ㄦ暟缁�
					}, options);

			this.debug = opt.debug;
			this.defaultUrl = opt.defaultUrl;
			this.selector = opt.selector;
			this.base = opt.base;
			this.before = opt.before;
			this.success = opt.success;
			this.after = opt.after;
			this.ifSame = opt.ifSame;
			this.attr = opt.attr;
			this.script = opt.script;
			this.autoStart = opt.autoStart;
			this.filter = $.extend( {}, opt.filter, {
				iframeAjaxBefore : {
					reg : /^.*$/,
					success : iframeAjax.before
				},
				iframeAjaxSuccess : {
					reg : /^.*$/,
					success : iframeAjax.success
				},
				iframeAjaxAfter : {
					reg : /^.*$/,
					success : iframeAjax.after
				}
			});

			if (this.debug) {
				$("body").append("<button id=\"clearLog\">clear logs</button>");
				$("body")
						.append(
								"<div id=\"iframeAjax_log\" style=\"height: 300px;overflow: auto;\"></div>");

				$("#clearLog").click(function() {
					$("#iframeAjax_log").empty();

					return false;
				});
			}
			
			$("a").click(
					function(event) {
						if($(this).attr(iframeAjax.selector) == 'async'){
							var url = $(this).attr(iframeAjax.attr)|| $(this).attr("href");
							iframeAjax.get(url);
							return false;
					}
			});
			
			if ($.browser.msie && parseInt($.browser.version) < 8
					&& $("#iframe_hidden").size() <= 0) {
				// add hidden iframe
				//$("body").append("<iframe id='iframe_hidden' width='0' height='0' frameborder='0'></iframe>");
				var iframeId = "iframe_hidden";
				var iframe = document.createElement("iframe");
				iframe.setAttribute("id", iframeId);
				iframe.setAttribute("width", 0);
				iframe.setAttribute("height", 0);
				iframe.setAttribute("frameborder", 0);
				iframe.style.display = "none";
				document.body.appendChild(iframe);
			}

				
			if (this.autoStart) {
				this.start();
			} else {
			}

		},

	
		start : function() {

			var href = window.location.href;
			var url = this.getAfter(href); 

			if (url !== "") {
				this.get(url);
			} else {
				this.get(this.defaultUrl);
			}
		},

		get : function(url) {
			if (url === null || url === "") {
				return;
			}

			if (this.currUrl === url && !this.ifSame) {
				return;
			}
			
			if ($.browser.msie && parseInt($.browser.version) < 8) {
				var str = "topnIframeAjax/proxy.html?" + Math.random()
						+ "#" + url;
				$("#iframe_hidden").attr("src", str);
				this.userIframe = true;
			} else {
				window.clearInterval(this.intervalID);
			
				if (window.location.hash != "#" + url) {
					window.location.hash = url;
				}
				
				this.ready(url);
				this.listenHash();
				this.userIframe = false;
			}
		},

		ready : function(url) {
			if (url) {
				this.success(url);
			}
			this.currUrl = url;
		},

		before : function(url) {
		},

	
		after : function(url) {
		},
		
		runBuffer : function(){
			if (this._buffer) {
				success(this._buffer);
				this._buffer = null;
			}
		},
		
		
		renderHTML : function(dhtml){
			var vitual = document.createElement('div'); 
			vitual.innerHTML = dhtml;
			$(vitual).find("link").each(function(n, value){
				iframeAjax.loadCSS(value.href);
			});
		},
		
		
		loadCSS : function(url) {
			var t = true;
			var styles = document.getElementsByTagName("link");
			
			for ( var i = 0; i < styles.length; i = i + 1) {
				if (styles[i].href && styles[i].href.indexOf(url) != -1) {
					t = false;
					break;
				}
			}
			if (t) {
				new TopNAjax().ajax({
		    		url: url,
		    		async : false,
		    		cache: true,
		    		success: function (msg) {
						iframeAjax.css_loaded(msg, url);
		            }
		    	});
			}
		},
		
		css_loaded : function(msg, url){
			var el = document.createElement("link");
			el.setAttribute("type", "text/css");
			el.setAttribute("rel", "stylesheet");
			el.setAttribute("href", url);
			el.styleSheet.cssText=msg;
			
			var head = document.getElementsByTagName('HEAD').item(0);
			head.appendChild(el);
		
		},
		
		
		parseHTML : function(msg){
			this.rendering = true;
			if(msg.indexOf("<head>") > -1){
				window.location.href = 'login.jsp';
				return;
			}
			if('' == jQuery.trim(msg)){
				return;
			}
			$(iframeAjax.base).html(msg);
			this.rendering = false;
		},

		
		
		success : function(url) {
			if (this.rendering === true) {
				_buffer = url;
				return;
			}
			
			if (url == null || url == "") {
				return;
			} else if (url.match("^.+\\?.*$")) {
				url += "&_r" + Math.random() + "=" + Math.random();
			} else {
				url += "?_r" + Math.random() + "=" + Math.random();
			}
			
			var data = $.query.get("personalInfoId");
			if(data == null || data == undefined || data == ''){
				data = $("#personalInfoId").val();
			}
			//$("#logo_loading").attr("src", "../pictures/logo_loading.swf");
			//swfobject.embedSWF("../pictures/logo_loading.swf", "logo_img_div", "128", "80", "9.0.0", "");
			
			
			new TopNAjax().ajax({
	    		url: encodeURI(url),
	    		async : true,
	    		data : "personalInfoId="+data+"&personId="+data,
	    		cache : false,
	    		success: function (msg) {
					iframeAjax.parseHTML(msg);
					if(iframeAjax.userIframe){
						var sub = $(iframeAjax.base, msg);
							var inc = sub.attr(iframeAjax.script) || "";
							if (inc !== "") {
								$.each(inc.split(","), function(k, v) {
									$.ajax( {
										url : v,
										type : 'GET',
										async : false,
										dataType : 'script'
									});
								});
							}
					}				
					iframeAjax.runBuffer();
	            }
	    	});
		},
		
		
		
		getBefore : function(url) {
			if (url.match("^.*#.*$")) {
				return url.substring(0, url.indexOf("#"));
			} else {
				return url;
			}
		},

	
		getAfter : function(url) {
			if (url.match("^.*#.+$")) {
				return url.substring(url.indexOf("#") + 1, url.length);
			} else {
				return "";
			}
		},

		getBase : function(url) {
			url = this.getBefore(url);
			var base = url.substring(0, url.lastIndexOf("/") + 1);
			return base;
		},

		listenHash : function() {

			this.currHash = window.location.hash;
			this.intervalID = window.setInterval(function() {
				iframeAjax.handleHash();
			}, 10);
		},

		handleHash : function() {
			if (window.location.hash != this.currHash) {
				this.currHash = window.location.hash;
				
				this.start();
			}
		},

		// log
		log : function(str) {
			if (this.debug) {
				$("#iframeAjax_log").append(str + "<br/>");
				$("#iframeAjax_log").scrollTop(99999999);
			}
		},

		refresh : function() {

			iframeAjax.ready(iframeAjax.getAfter(window.location.href));
		}
	};
})(jQuery);


(function(window){
	iframeAjax.ini({
		debug: false,
		base: "#mainContent",
		selector: "ui-async"
	});
})(window)
