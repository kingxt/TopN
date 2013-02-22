(function() {
	var useIframe = (!!window.ActiveXObject && (!window.XMLHttpRequest || (!!window.XMLHttpRequest && (!document.documentMode || document.documentMode === 7))));
	function HTML5History(history, adapter) {
		var self = this;
		this.history = history;
		this.blankHTML = "/blank.html";
		this._ignoreHashChange = false;
		history.pushState = function() {
			self.pushState.apply(self, arguments)
		};
		if (adapter) {
			self.fireEvent = adapter.fireEvent;
			if (adapter.blankHTML) {
				self.blankHTML = adapter.blankHTML
			}
		}
		if (useIframe) {
			history._oniframechange = function() {
				self.onIFrameChange.apply(self, arguments)
			};
			window.attachEvent("onload", function() {
				self.firePopState();
				self.makeIFrame(function() {
					self._isInit = true;
					self.makeIFrameHistory("")
				})
			})
		} else {
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
	
// 添加原形函数
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
				iframe.detachEvent("onload", arguments.callee);
				setTimeout(function() {
					callback()
				}, 0)
			});
			document.body.appendChild(iframe);
			this.iframe = iframe
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
							(document.domain != location.hostname) ? '  document.domain="' + document.domain + '";'
									: "",
							"    function pageLoad() {",
							'        try { top.window.history._oniframechange("' + url + '") } catch(e) {}',
							"    }", "<\/script>", "</head>",
							'<body onload="pageLoad()">',
							'<div id="url">' + url + "</div>", "</body>",
							"</html>" ].join(""));
			doc.title = document.title;
			doc.close()
		},
		firePopState : function() {
			if (this.fireEvent) {
				this.fireEvent(window, "popstate")
			} else {
				if (document.createEvent) {
					var event = document.createEvent("UIEvents");
					event.initEvent("popstate", false, false);
					event.state = null;
					window.dispatchEvent(event)
				}
			}
			if (typeof window.onpopstate == "function") {
				window.onpopstate()
			}
		},
		pushState : function(state, title, url) {
			if (url.substr(0, 1) != "#" || location.hash == url) {
				throw "由于浏览器限制，你必须传入一个与当前hash不同的hash值"
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
		if (history.pushState) {
			return
		}
		new HTML5History(history, adapter)
	};
	window.HTML5History = HTML5History
})
	


			function AsyncHTMLManager(){
				
					//创建一个a链接
					parseURL = function(url) {
						var ele = document.createElement("a");
						ele.href = url;
						return ele
					};
					//初始化当前页面
					this.initialize = function() {
						this._buffer = null;
				/*		
						self.__timer = {
								setTimeout : setTimeout,
								setInterval : setInterval
							};
							self.__domModuleFn = object._loader.lib.dom.fn;*/
						this.rendering = false;
						self.location = parseURL(location.href
									.replace(/#.*/ig, ""));
						this.hashname = "";
						this.prefetchLoad = false;
						this._lastHeadElements = [];
						this.head = document
									.getElementsByTagName("head")[0]
					};
					
					//缓存
					this.putBuffer = function(url) {
						self._buffer = url
					};
					
					//hash链接
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
					
					
					
					//打开缓存中的链接
					this.runBuffer = function() {
						if (this._buffer) {
							this.open(this._buffer);
							this._buffer = null
						}
					};
					
					// 参数依次是， 请求的url，方法，参数，回调函数
					this.open = function(self, url, method, params, callback) {
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
							self.location = this.parseURL(url);
							this.hashname = this.parseHash(url);
							window.history.pushState( {}, null, "#"
									+ this.hashname)
						}
						this.asyncHTML(url, params, method, callback)
					};
					
					/**
					 *  msg 返回值
					 *  callback 回调函数
					 */
					function parseHTML(msg, callback){
						self.rendering = true;
						//这里要做两个操作，第一个就是将css动态加载，第二就是将文本内容加入到指定节点中
						if('' == jQuery.trim(msg)){
							return;
						}
						var dh = $(msg);
						renderHTML(dh);//渲染页面
						callback(msg);
						self.rendering = false;
					}
					
					//动态加载css，这里没有做动态加载javascript
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
					
					//异步加载html
					this.asyncHTML = function(url, params,
							method, callback) {
						if (!method) {
							method = "get"
						}
						
						$.ajax( {
							type : method,
							url : url,
							data : params,
							cache : false,
							error : function() {
								alert('加载页面时出错！');
							},
							success : function(msg) {
								if (url != self.location.href) {
									return
								}
								parseHTML(msg, callback);
								self.runBuffer();
							}
						});
					};					
					
			
		}
	
