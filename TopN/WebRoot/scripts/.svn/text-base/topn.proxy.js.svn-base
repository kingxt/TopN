//用来实现ajax iframe代理
(function() {
	// 判断是否使用iframe
	var useIframe = (!!window.ActiveXObject && (!window.XMLHttpRequest || (!!window.XMLHttpRequest && (!document.documentMode || document.documentMode === 7))));
	
	// 这个history是widow的history，这儿adapter是构建的对象
	function HTML5History(history, adapter) {
		var self = this;
		this.history = history;
		this.blankHTML = "/blank.html";
		this._ignoreHashChange = false;
		history.pushState = function() {
			self.pushState.apply(self, arguments)
		};
		if (adapter) {
			self.fireEvent = adapter.fireEvent;// fireEvent是要触发的事件
			if (adapter.blankHTML) {
				self.blankHTML = adapter.blankHTML
			}
		}
		
		// 如果用不能使用iframe的话就使用轮询方式
		if (useIframe) {
			// 监听iframe改变
			history._oniframechange = function() {
				self.onIFrameChange.apply(self, arguments)
			};
			window.attachEvent("onload", function() {
				self.firePopState();
				self.makeIFrame(function() {
					self._isInit = true;// 这里说明初始化iframe成功呢
					self.makeIFrameHistory("")
				})
			})
		} else {// 基于锚点的监听
			if (window.addEventListener) {
				window.addEventListener("hashchange", function() {
					self.onHashChange.apply(self, arguments)
				}, false);
				window.addEventListener("load", function() {
					self.firePopState()
				}, false)
			} else {
				window.attachEvent("onhashchange", function() {
					self.onHashChange.apply(self, arguments)
				});
				window.attachEvent("onload", function() {
					self.firePopState()
				})
			}
		}
	}
	HTML5History.prototype = {
		makeIFrame : function(callback) {
			var iframeId = "html5history-iframe";
			var iframe = document.createElement("iframe");
			iframe.setAttribute("id", iframeId);
			if (document.domain != location.hostname) {
				iframe.setAttribute("src", "http://" + document.domain
						+ this.blankHTML)
			}
			iframe.style.display = "none";
			iframe.attachEvent("onload", function() {
				iframe.detachEvent("onload", arguments.callee); // 这里要修改
				setTimeout(function() {
					callback()
				}, 0)
			});
			document.body.appendChild(iframe);
			this.iframe = iframe;// 改变操纵iframe的窗口
		},
		makeIFrameHistory : function(url) {
			var doc = this.iframe.contentWindow.document;
			doc.open();
			doc
					.write( [
							"<html>",
							"<head>",
							'<meta http-equiv="Pragma" content="no-cache" />',
							'<meta http-equiv="Expires" content="-1" />',
							"<script>",
							(document.domain != location.hostname) ? '	document.domain="' + document.domain + '";'
									: "",
							"	function pageLoad() {",
							'		try { top.window.history._oniframechange("' + url + '") } catch(e) {}',
							"	}", "<\/script>", "</head>",
							'<body onload="pageLoad()">',
							'<div id="url">' + url + "</div>", "</body>",
							"</html>" ].join(""));
			doc.title = document.title;
			doc.close()
		},
		firePopState : function() {// 这里要注意的是fireEvent
			if (this.fireEvent) {
				this.fireEvent(window, "popstate")
			} else {// 下面是自定义事件，有可能浏览器不支持fireEvent
				if (document.createEvent) {
					var event = document.createEvent("UIEvents");
					event.initEvent("popstate", false, false);
					event.state = null;
					window.dispatchEvent(event)
				}
			}// html5 当窗口历史记录改变时运行脚本
			if (typeof window.onpopstate == "function") {
				window.onpopstate();  
			}
		},
		pushState : function(state, title, url) {
			if (url.substr(0, 1) != "#" || location.hash == url) {
				throw "";
			}
			this._pushState(state, title, url)
		},
		_pushState : useIframe ? function(state, title, url) {
			this._ignoreHashChange = true;
			this.makeIFrameHistory(url)
		} : function(state, title, url) {
			this._ignoreHashChange = true;
			window.location.hash = url
		},
		onIFrameChange : function(url) {
			if (this._isInit) {
				this._isInit = false
			} else {
				location.hash = url;
				if (!this._ignoreHashChange) {
					this.firePopState()
				}
				this._ignoreHashChange = false
			}
		},
		onHashChange : function(url) {
			if (!this._ignoreHashChange) {
				this.firePopState()
			}
			this._ignoreHashChange = false
		}
	};
	HTML5History.bind = function(history, adapter) {
		if (history.pushState) {// 这里保证浏览器只监听一次
			return
		}
		new HTML5History(history, adapter)
	};
	window.HTML5History = HTML5History
})();
// HTML5History 运行完毕

//定义异步管理器
AsyncHTMLManager = function(){
	var self = this;
	//事件绑定
	this.bind = function(self) {
		$(a).bind("click", function(){
				if (this.getAttribute("ui-async")) {
					e.preventDefault();
					self.open(jQuery.trim(this.href));
				}
		});
		this.initialize();
	}
	// 创建一个a链接，便于解析
	parseURL = function(self, url) {
		var ele = document.createElement("a");
		ele.href = url;
		return ele
	};
	// 初始化当前页面
	this.initialize = function() {
		self._buffer = null;
/*
 * self.__timer = { setTimeout : setTimeout, setInterval : setInterval };
 * self.__domModuleFn = object._loader.lib.dom.fn;
 */
		self.rendering = false;
		self.location = parseURL(location.href
					.replace(/#.*/ig, ""));
		self.hashname = "";
		self.prefetchLoad = false;
		self._lastHeadElements = [];
		self.head = document.getElementsByTagName("head")[0]
	};
	
	// 缓存
	this.putBuffer = function(self, url) {
		self._buffer = url
	};
	
	// hash链接
	this.parseHash = function(url) {
		url = self.parseURL(url);
		var protocol = "", hostname = "", port = "";
		if (url.protocol != location.protocol) {
			protocol = url.protocol
		}
		if (url.hostname != location.hostname) {
			hostname = url.hostname.replace("."
					+ document.domain, "")
		}
		if (url.port != "80" && url.port != "0"
				&& url.port != location.port) {
			port = url.port
		}
		var hash = "";
		if (protocol) {
			hash += protocol + "//"
		}
		if (hostname) {
			hash += (protocol ? "" : "//")
					+ hostname
		}
		if (port) {
			hash += ":" + port
		}
		if (url.pathname.slice(0, 1) != "/") {
			hash += "/"
		}
		hash += url.pathname + url.search;
		return hash
	};
	
	
	
	// 打开缓存中的链接
	this.runBuffer = function(self) {
		if (self._buffer) {
			self.open(self._buffer);
			self._buffer = null
		}
	};
	
	// 参数依次是， 请求的url，方法，参数，回调函数
	this.open = function(self, url) {
		if (!url) {
			return
		}
		if (this.rendering === true) {
			this.putBuffer(url);
			return
		}
		var hash = this.parseHash(url);
		var reload = false;
		if (url == self.location.href) {
			reload = true
		} else {
			self.location = self.parseURL(url);
			self.hashname = self.parseHash(url);
			window.history.pushState( {}, null, "#"
					+ self.hashname)
		}
		this.asyncHTML(url)
	};
	
	/**
	 * msg 返回值 callback 回调函数
	 */
	function parseHTML(msg){
		self.rendering = true;
		// 这里要做两个操作，第一个就是将css动态加载，第二就是将文本内容加入到指定节点中
		if('' == jQuery.trim(msg)){
			return;
		}
		var dh = $(msg);
		renderHTML(dh);// 渲染页面
		$("mainContent").html(msg);
		callback(msg);
		self.rendering = false;
	}
	
	// 动态加载css，这里没有做动态加载javascript
	function renderHTML(dhtml){
		$(dhtml).find("link").each(function(n, value){
			loadCSS(value.href);
		}); 
	}
	
	/*
	 * 动态加载样式表
	 */
	function loadCSS(url) {
		var t = true;
		var styles = document.getElementsByTagName("link");
		// 判断指定的文件是否已经包含，如果已包含则触发onsuccess事件并返回
		for ( var i = 0; i < styles.length; i = i + 1) {
			if (styles[i].href && styles[i].href.indexOf(url) != -1) {
				t = false;
				break;
			}
		}
		if (t) {
			var head = document.getElementsByTagName('HEAD').item(0);
			var style = document.createElement('link');
			// style.href = '../styles/update_info.css';
			style.href = url;
			style.rel = 'stylesheet'
			style.type = 'text/css';
			head.appendChild(style);
		}
	}
	
	// 异步加载html
	this.asyncHTML = function(self, url) {
		if (!method) {
			method = "get"
		}
		
		$.ajax( {
			type : method,
			url : url,
			cache : false,
			error : function() {
				alert('加载页面时出错！');
			},
			success : function(msg) {
				if (url != self.location.href) {
					return
				}
				parseHTML(msg);
				self.runBuffer();
			}
		});
	};
}



// 下面是一些绑定事件和初始化动作
(function(window){
	HTML5History.bind(window.history, {
		fireEvent : function(ele, type) {
			dom.Element.fireEvent(ele, type)
		},
		blankHTML : "/ajaxproxy.htm"
	});
	var asyncHTMLManager = new AsyncHTMLManager();
	$.ready(function() {
		asyncHTMLManager.bind(document);
	});
	
})(window)