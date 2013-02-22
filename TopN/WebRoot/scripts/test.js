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
})();
object
		.add(
				"xn.asyncHTML.manager",
				"events, dom, ua, net, string, xn.asyncHTML.frame",
				function(exports, events, dom, ua, net, string, xn) {
					var DEBUG = true;
					var log = function() {
						if (DEBUG && console) {
							console.log.apply(console, arguments)
						}
					};
					this.AsyncHTMLManager = new Class(
							function() {
								Class.mixin(this, events.Events);
								this.initialize = function(self) {
									self._buffer = null;
									self._doctype = [ "<!DOCTYPE html [",
											'<!ENTITY nbsp   "&#160;">',
											'<!ENTITY iexcl  "&#161;">',
											'<!ENTITY cent   "&#162;">',
											'<!ENTITY pound  "&#163;">',
											'<!ENTITY curren "&#164;">',
											'<!ENTITY yen    "&#165;">',
											'<!ENTITY brvbar "&#166;">',
											'<!ENTITY sect   "&#167;">',
											'<!ENTITY uml    "&#168;">',
											'<!ENTITY copy   "&#169;">',
											'<!ENTITY ordf   "&#170;">',
											'<!ENTITY laquo  "&#171;">',
											'<!ENTITY not    "&#172;">',
											'<!ENTITY shy    "&#173;">',
											'<!ENTITY reg    "&#174;">',
											'<!ENTITY macr   "&#175;">',
											'<!ENTITY deg    "&#176;">',
											'<!ENTITY plusmn "&#177;">',
											'<!ENTITY sup2   "&#178;">',
											'<!ENTITY sup3   "&#179;">',
											'<!ENTITY acute  "&#180;">',
											'<!ENTITY micro  "&#181;">',
											'<!ENTITY para   "&#182;">',
											'<!ENTITY middot "&#183;">',
											'<!ENTITY cedil  "&#184;">',
											'<!ENTITY sup1   "&#185;">',
											'<!ENTITY ordm   "&#186;">',
											'<!ENTITY raquo  "&#187;">',
											'<!ENTITY frac14 "&#188;">',
											'<!ENTITY frac12 "&#189;">',
											'<!ENTITY frac34 "&#190;">',
											'<!ENTITY iquest "&#191;">',
											'<!ENTITY Agrave "&#192;">',
											'<!ENTITY Aacute "&#193;">',
											'<!ENTITY Acirc  "&#194;">',
											'<!ENTITY Atilde "&#195;">',
											'<!ENTITY Auml   "&#196;">',
											'<!ENTITY Aring  "&#197;">',
											'<!ENTITY AElig  "&#198;">',
											'<!ENTITY Ccedil "&#199;">',
											'<!ENTITY Egrave "&#200;">',
											'<!ENTITY Eacute "&#201;">',
											'<!ENTITY Ecirc  "&#202;">',
											'<!ENTITY Euml   "&#203;">',
											'<!ENTITY Igrave "&#204;">',
											'<!ENTITY Iacute "&#205;">',
											'<!ENTITY Icirc  "&#206;">',
											'<!ENTITY Iuml   "&#207;">',
											'<!ENTITY ETH    "&#208;">',
											'<!ENTITY Ntilde "&#209;">',
											'<!ENTITY Ograve "&#210;">',
											'<!ENTITY Oacute "&#211;">',
											'<!ENTITY Ocirc  "&#212;">',
											'<!ENTITY Otilde "&#213;">',
											'<!ENTITY Ouml   "&#214;">',
											'<!ENTITY times  "&#215;">',
											'<!ENTITY Oslash "&#216;">',
											'<!ENTITY Ugrave "&#217;">',
											'<!ENTITY Uacute "&#218;">',
											'<!ENTITY Ucirc  "&#219;">',
											'<!ENTITY Uuml   "&#220;">',
											'<!ENTITY Yacute "&#221;">',
											'<!ENTITY THORN  "&#222;">',
											'<!ENTITY szlig  "&#223;">',
											'<!ENTITY agrave "&#224;">',
											'<!ENTITY aacute "&#225;">',
											'<!ENTITY acirc  "&#226;">',
											'<!ENTITY atilde "&#227;">',
											'<!ENTITY auml   "&#228;">',
											'<!ENTITY aring  "&#229;">',
											'<!ENTITY aelig  "&#230;">',
											'<!ENTITY ccedil "&#231;">',
											'<!ENTITY egrave "&#232;">',
											'<!ENTITY eacute "&#233;">',
											'<!ENTITY ecirc  "&#234;">',
											'<!ENTITY euml   "&#235;">',
											'<!ENTITY igrave "&#236;">',
											'<!ENTITY iacute "&#237;">',
											'<!ENTITY icirc  "&#238;">',
											'<!ENTITY iuml   "&#239;">',
											'<!ENTITY eth    "&#240;">',
											'<!ENTITY ntilde "&#241;">',
											'<!ENTITY ograve "&#242;">',
											'<!ENTITY oacute "&#243;">',
											'<!ENTITY ocirc  "&#244;">',
											'<!ENTITY otilde "&#245;">',
											'<!ENTITY ouml   "&#246;">',
											'<!ENTITY divide "&#247;">',
											'<!ENTITY oslash "&#248;">',
											'<!ENTITY ugrave "&#249;">',
											'<!ENTITY uacute "&#250;">',
											'<!ENTITY ucirc  "&#251;">',
											'<!ENTITY uuml   "&#252;">',
											'<!ENTITY yacute "&#253;">',
											'<!ENTITY thorn  "&#254;">',
											'<!ENTITY yuml   "&#255;">',
											'<!ENTITY fnof     "&#402;">',
											'<!ENTITY Alpha    "&#913;">',
											'<!ENTITY Beta     "&#914;">',
											'<!ENTITY Gamma    "&#915;">',
											'<!ENTITY Delta    "&#916;">',
											'<!ENTITY Epsilon  "&#917;">',
											'<!ENTITY Zeta     "&#918;">',
											'<!ENTITY Eta      "&#919;">',
											'<!ENTITY Theta    "&#920;">',
											'<!ENTITY Iota     "&#921;">',
											'<!ENTITY Kappa    "&#922;">',
											'<!ENTITY Lambda   "&#923;">',
											'<!ENTITY Mu       "&#924;">',
											'<!ENTITY Nu       "&#925;">',
											'<!ENTITY Xi       "&#926;">',
											'<!ENTITY Omicron  "&#927;">',
											'<!ENTITY Pi       "&#928;">',
											'<!ENTITY Rho      "&#929;">',
											'<!ENTITY Sigma    "&#931;">',
											'<!ENTITY Tau      "&#932;">',
											'<!ENTITY Upsilon  "&#933;">',
											'<!ENTITY Phi      "&#934;">',
											'<!ENTITY Chi      "&#935;">',
											'<!ENTITY Psi      "&#936;">',
											'<!ENTITY Omega    "&#937;">',
											'<!ENTITY alpha    "&#945;">',
											'<!ENTITY beta     "&#946;">',
											'<!ENTITY gamma    "&#947;">',
											'<!ENTITY delta    "&#948;">',
											'<!ENTITY epsilon  "&#949;">',
											'<!ENTITY zeta     "&#950;">',
											'<!ENTITY eta      "&#951;">',
											'<!ENTITY theta    "&#952;">',
											'<!ENTITY iota     "&#953;">',
											'<!ENTITY kappa    "&#954;">',
											'<!ENTITY lambda   "&#955;">',
											'<!ENTITY mu       "&#956;">',
											'<!ENTITY nu       "&#957;">',
											'<!ENTITY xi       "&#958;">',
											'<!ENTITY omicron  "&#959;">',
											'<!ENTITY pi       "&#960;">',
											'<!ENTITY rho      "&#961;">',
											'<!ENTITY sigmaf   "&#962;">',
											'<!ENTITY sigma    "&#963;">',
											'<!ENTITY tau      "&#964;">',
											'<!ENTITY upsilon  "&#965;">',
											'<!ENTITY phi      "&#966;">',
											'<!ENTITY chi      "&#967;">',
											'<!ENTITY psi      "&#968;">',
											'<!ENTITY omega    "&#969;">',
											'<!ENTITY thetasym "&#977;">',
											'<!ENTITY upsih    "&#978;">',
											'<!ENTITY piv      "&#982;">',
											'<!ENTITY bull     "&#8226;">',
											'<!ENTITY hellip   "&#8230;">',
											'<!ENTITY prime    "&#8242;">',
											'<!ENTITY Prime    "&#8243;">',
											'<!ENTITY oline    "&#8254;">',
											'<!ENTITY frasl    "&#8260;">',
											'<!ENTITY weierp   "&#8472;">',
											'<!ENTITY image    "&#8465;">',
											'<!ENTITY real     "&#8476;">',
											'<!ENTITY trade    "&#8482;">',
											'<!ENTITY alefsym  "&#8501;">',
											'<!ENTITY larr     "&#8592;">',
											'<!ENTITY uarr     "&#8593;">',
											'<!ENTITY rarr     "&#8594;">',
											'<!ENTITY darr     "&#8595;">',
											'<!ENTITY harr     "&#8596;">',
											'<!ENTITY crarr    "&#8629;">',
											'<!ENTITY lArr     "&#8656;">',
											'<!ENTITY uArr     "&#8657;">',
											'<!ENTITY rArr     "&#8658;">',
											'<!ENTITY dArr     "&#8659;">',
											'<!ENTITY hArr     "&#8660;">',
											'<!ENTITY forall   "&#8704;">',
											'<!ENTITY part     "&#8706;">',
											'<!ENTITY exist    "&#8707;">',
											'<!ENTITY empty    "&#8709;">',
											'<!ENTITY nabla    "&#8711;">',
											'<!ENTITY isin     "&#8712;">',
											'<!ENTITY notin    "&#8713;">',
											'<!ENTITY ni       "&#8715;">',
											'<!ENTITY prod     "&#8719;">',
											'<!ENTITY sum      "&#8721;">',
											'<!ENTITY minus    "&#8722;">',
											'<!ENTITY lowast   "&#8727;">',
											'<!ENTITY radic    "&#8730;">',
											'<!ENTITY prop     "&#8733;">',
											'<!ENTITY infin    "&#8734;">',
											'<!ENTITY ang      "&#8736;">',
											'<!ENTITY and      "&#8743;">',
											'<!ENTITY or       "&#8744;">',
											'<!ENTITY cap      "&#8745;">',
											'<!ENTITY cup      "&#8746;">',
											'<!ENTITY int      "&#8747;">',
											'<!ENTITY there4   "&#8756;">',
											'<!ENTITY sim      "&#8764;">',
											'<!ENTITY cong     "&#8773;">',
											'<!ENTITY asymp    "&#8776;">',
											'<!ENTITY ne       "&#8800;">',
											'<!ENTITY equiv    "&#8801;">',
											'<!ENTITY le       "&#8804;">',
											'<!ENTITY ge       "&#8805;">',
											'<!ENTITY sub      "&#8834;">',
											'<!ENTITY sup      "&#8835;">',
											'<!ENTITY nsub     "&#8836;">',
											'<!ENTITY sube     "&#8838;">',
											'<!ENTITY supe     "&#8839;">',
											'<!ENTITY oplus    "&#8853;">',
											'<!ENTITY otimes   "&#8855;">',
											'<!ENTITY perp     "&#8869;">',
											'<!ENTITY sdot     "&#8901;">',
											'<!ENTITY lceil    "&#8968;">',
											'<!ENTITY rceil    "&#8969;">',
											'<!ENTITY lfloor   "&#8970;">',
											'<!ENTITY rfloor   "&#8971;">',
											'<!ENTITY lang     "&#9001;">',
											'<!ENTITY rang     "&#9002;">',
											'<!ENTITY loz      "&#9674;">',
											'<!ENTITY spades   "&#9824;">',
											'<!ENTITY clubs    "&#9827;">',
											'<!ENTITY hearts   "&#9829;">',
											'<!ENTITY diams    "&#9830;">',
											'<!ENTITY quot    "&#34;">',
											'<!ENTITY OElig   "&#338;">',
											'<!ENTITY oelig   "&#339;">',
											'<!ENTITY Scaron  "&#352;">',
											'<!ENTITY scaron  "&#353;">',
											'<!ENTITY Yuml    "&#376;">',
											'<!ENTITY circ    "&#710;">',
											'<!ENTITY tilde   "&#732;">',
											'<!ENTITY ensp    "&#8194;">',
											'<!ENTITY emsp    "&#8195;">',
											'<!ENTITY thinsp  "&#8201;">',
											'<!ENTITY zwnj    "&#8204;">',
											'<!ENTITY zwj     "&#8205;">',
											'<!ENTITY lrm     "&#8206;">',
											'<!ENTITY rlm     "&#8207;">',
											'<!ENTITY ndash   "&#8211;">',
											'<!ENTITY mdash   "&#8212;">',
											'<!ENTITY lsquo   "&#8216;">',
											'<!ENTITY rsquo   "&#8217;">',
											'<!ENTITY sbquo   "&#8218;">',
											'<!ENTITY ldquo   "&#8220;">',
											'<!ENTITY rdquo   "&#8221;">',
											'<!ENTITY bdquo   "&#8222;">',
											'<!ENTITY dagger  "&#8224;">',
											'<!ENTITY Dagger  "&#8225;">',
											'<!ENTITY permil  "&#8240;">',
											'<!ENTITY lsaquo  "&#8249;">',
											'<!ENTITY rsaquo  "&#8250;">',
											'<!ENTITY euro   "&#8364;">', "]>" ]
											.join("");
									self._xslt = [
											'<xsl:stylesheet version="1.0"',
											'    exclude-result-prefixes=""',
											'   xmlns:xsl="http://www.w3.org/1999/XSL/Transform"',
											' xmlns="http://www.w3.org/1999/xhtml">',
											"",
											' <xsl:output method="html" encoding="utf-8" />',
											"",
											" <xsl:template match=\"*[local-name() = 'script']\">",
											'      <script type="text/javascript">',
											'          <xsl:value-of select="." disable-output-escaping="yes" />',
											"        <\/script>",
											"   </xsl:template>",
											"",
											'   <xsl:template match="node() | @*">',
											"       <xsl:copy>",
											'           <xsl:apply-templates select="node() | @*" />',
											"     </xsl:copy>",
											"  </xsl:template>",
											"",
											'   <xsl:template match="*[@ui-badformed]">',
											"      <xsl:copy>",
											'           <xsl:apply-templates select="@*" />',
											'          <xsl:value-of select="." disable-output-escaping="yes" />',
											"        </xsl:copy>",
											"  </xsl:template>", "",
											"</xsl:stylesheet>" ].join("");
									self.__timer = {
										setTimeout : setTimeout,
										setInterval : setInterval
									};
									self.__domModuleFn = object._loader.lib.dom.fn;
									self.rendering = false;
									self.location = self.parseURL(location.href
											.replace(/#.*/ig, ""));
									self.hashname = "";
									self.prefetchLoad = false;
									self._lastHeadElements = [];
									self.head = document
											.getElementsByTagName("head")[0]
								};
								this.parseURL = function(self, url) {
									var ele = document.createElement("a");
									ele.href = url;
									return ele
								};
								this.parseHash = function(self, url) {
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
								this.deparseHash = function(self, hash) {
									var url = self.parseURL(location.href);
									var pattern = /^(\w+?\:)?(\/\/([\w\-_.]*))?(:(\d+))?(\/.*?)?(\?(.*?))?$/i;
									var result = pattern.exec(hash);
									if (!result) {
										return ""
									}
									var protocol = result[1];
									var domain = result[3];
									var port = result[5];
									var pathname = result[6];
									var search = result[7];
									if (!protocol && !domain && !port
											&& !pathname && !search) {
										return ""
									}
									var href = "";
									href += protocol || url.protocol;
									href += "//";
									href += domain ? domain + "."
											+ document.domain : url.hostname;
									if (port) {
										href += ":" + port
									}
									href += pathname || "";
									href += search || "";
									return href
								};
								this.putBuffer = function(self, url) {
									self._buffer = url
								};
								this.runBuffer = function(self) {
									if (self._buffer) {
										self.open(self._buffer);
										self._buffer = null
									}
								};
								this.open = function(self, url, method) {
									if (!url) {
										return
									}
									if (self.rendering === true) {
										self.putBuffer(url);
										return
									}
									var hash = self.parseHash(url);
									var reload = false;
									if (url == self.location.href) {
										reload = true
									} else {
										self.location = self.parseURL(url);
										self.hashname = self.parseHash(url);
										window.history.pushState( {}, null, "#"
												+ self.hashname)
									}
									self.asyncHTML(url, reload, method)
								};
								var firstRun = true;
								this.listenHash = function(self, hash) {
									if (firstRun) {
										firstRun = false;
										return
									}
									var url = hash ? self.deparseHash(hash)
											: location.href.replace(/\#.*$/ig,
													"");
									if (!url) {
										return
									}
									if (self.rendering === true) {
										self.pubBuffer(url);
										return
									}
									self.location = self.parseURL(url);
									self.hashname = self.parseHash(url);
									self.asyncHTML(url)
								};
								this.initPage = function(self, prefetched) {
									var url = location.href
											.replace(/#.*$/i, "");
									if (location.href.indexOf("#") != -1) {
										var hash = location.href
												.substr(location.href
														.indexOf("#") + 1);
										var url2 = self.deparseHash(hash);
										if (url2) {
											url = url2
										}
									}
									self.location = self.parseURL(url);
									self.hashname = self.parseHash(url);
									dom.ready(function() {
										var match = document.body.className
												.match(/layout_(.+)/i);
										if (match) {
											self.defaultLayout = match[1]
										}
									});
									if (prefetched) {
										if (self.location.hostname != location.hostname
												|| self.location.pathname
														.replace(/^\//i, "") != location.pathname
														.replace(/^\//i, "")) {
											self.prefetchLoad = true
										}
										dom
												.ready(function() {
													self.fireReady();
													self._lastHeadElements = dom
															.getElements("head [ui-rel~=prefetchframe]")
												});
										self.fireEvent("beforeload");
										dom.ready(function() {
											self.fireEvent("load")
										});
										if (self.prefetchLoad) {
											if (ua.ua.ie) {
												var style = document
														.createStyleSheet();
												style.cssText = ".x-prefetchframecontent * {display:none}"
											} else {
												var node = document
														.createElement("style");
												node.setAttribute("type",
														"text/css");
												var css_text = document
														.createTextNode(".x-prefetchframecontent{display:none}");
												node.appendChild(css_text);
												self.head.appendChild(node)
											}
											dom.ready(function() {
												self.asyncHTML(url)
											})
										}
									} else {
										dom.ready(function() {
											self.asyncHTML(url)
										})
									}
								};
								this.startLoadFrame = function(self) {
									self.overwriteTimer();
									self.overwriteDomModule();
									dom.ready(function() {
										window.__logEvents = true
									});
									object
											.use(
													"dom",
													function(exports, dom) {
														if (window.XN) {
															window.XN.util.setTimeout = window.setTimeout;
															window.XN.util.setInterval = window.setInterval;
															window.XN.dom.ready = window.XN.dom.readyDo = dom.ready;
															window.XN.element.addEvent = window.XN.event.addEvent = function(
																	ele, type,
																	func, cap) {
																var addEvent = arguments.callee;
																if (Array
																		.isArray(ele)) {
																	ele
																			.forEach(function(
																					one) {
																				addEvent(
																						one,
																						type,
																						func,
																						cap)
																			})
																} else {
																	if (typeof ele == "string") {
																		ele = document
																				.getElementById(ele)
																	}
																	if (!ele._eventListeners) {
																		ele._eventListeners = {}
																	}
																	if (!ele.wrapEvent) {
																		ele.wrapEvent = function(
																				e) {
																			return dom.Element
																					.wrapEvent(e)
																		}
																	}
																	dom.Element.addEvent
																			.apply(
																					dom.Element,
																					arguments);
																	return ele
																}
															};
															window.XN.element.delEvent = dom.Element.removeEvent
																	.bind(dom.Element);
															window.XN.event.delEvent = dom.Element.removeEvent
																	.bind(dom.Element);
															window.XN
																	.clearFiles()
														}
													});
									self.fireLoading()
								};
								this.stopLoadFrame = function(self) {
									dom.ready(function() {
										window.__logEvents = false
									})
								};
								this.fireError = function(self, msg, code) {
									code = code || 10;
									var error = new Error(msg);
									error.number = code;
									self.fireEvent("error", error);
									self.rendering = false
								};
								this.loadRequestHTML = function(self, r) {
									self.rendering = true;
									var html = r.responseText;
									var head = html.substring(html
											.indexOf("<head") + 7, html
											.indexOf("</head>"));
									var body = html.substring(html
											.indexOf("<body") + 7, html
											.indexOf("</body>"));
									var bodyAttribute = html
											.match(/<body(.*)>/i);
									if (!head || !body) {
										self.fireError("网络返回html文档格式不正确");
										return
									}
									body = "<div " + bodyAttribute[1] + ">"
											+ body + "</div>";
									body = dom.Element.fromString(body);
									var xml, dochead;
									if (ua.ua.ie) {
										xml = new ActiveXObject(
												"Microsoft.XMLDOM");
										xml.async = false;
										xml.validateOnParse = false;
										xml
												.loadXML('<head xmlns="http://www.w3.org/1999/xhtml">'
														+ head + "</head>");
										dochead = xml.documentElement
									} else {
										var parser = new DOMParser();
										xml = parser.parseFromString(
												'<head xmlns="http://www.w3.org/1999/xhtml">'
														+ head + "</head>",
												"text/xml");
										dochead = xml.documentElement
									}
									self.renderHTML(dochead, body);
									window.scrollTo(0, 0);
									self.rendering = false
								};
								this.loadRequest = function(self, r) {
									self.rendering = true;
									var xml, errorCode, error;
									if (ua.ua.ie) {
										var text = r.responseText;
										text = text.replace(/<!DOCTYPE.+?>/mig,
												self._doctype);
										xml = new ActiveXObject(
												"Microsoft.XMLDOM");
										xml.async = false;
										xml.validateOnParse = false;
										xml.loadXML(text);
										if (!xml.xml) {
											self.fireError("网络返回XML文档格式不正确\n"
													+ xml.parseError.srcText
													+ xml.parseError.reason);
											return
										}
									} else {
										if (!r.responseXML
												|| !r.responseXML.documentElement
												|| r.responseXML
														.getElementsByTagName("parsererror").length) {
											self.fireError("网络返回XML文档格式不正确");
											return
										}
										xml = r.responseXML
									}
									if (xml.documentElement
											.getElementsByTagName("error").length) {
										errorCode = parseInt(xml.documentElement
												.getElementsByTagName("error")
												.item(0).getAttribute("code"));
										self.fireError("网络返回XML文档格式不正确",
												errorCode)
									}
									var head = xml.documentElement
											.getElementsByTagName("head")[0];
									var body = xml.documentElement
											.getElementsByTagName("body")[0];
									var bodyDiv = document.createElement("div");
									for ( var node, i = 0; i < body.childNodes.length; i++) {
										node = body.childNodes[i];
										if (node.nodeType === 1) {
											node = self.xml2html(node);
											bodyDiv.appendChild(node)
										}
									}
									self.renderHTML(head, bodyDiv);
									window.scrollTo(0, 0);
									self.rendering = false
								};
								this.asyncHTML = function(self, url, reload,
										method) {
									if (!method) {
										method = "get"
									}
									var viewMethod = reload ? "async-html-reload"
											: "async-html";
									self.fireEvent("beforeload");
									var request = new net.Request(
											{
												url : url
														+ (url.indexOf("?") != -1 ? "&"
																: "?")
														+ "__view="
														+ viewMethod,
												method : method,
												headers : {
													"Content-Type" : "application/x-www-form-urlencoded",
													"If-Modified-Since" : "0",
													"Cache-Control" : "no-cache"
												}
											});
									request.onsuccess = function(event) {
										if (url != self.location.href) {
											return
										}
										var xhr = event.request;
										dom.ready(function() {
											if (xhr.getResponseHeader(
													"Content-Type").indexOf(
													"text/xml") != -1) {
												self.loadRequest(xhr)
											}
											if (xhr.getResponseHeader(
													"Content-Type").indexOf(
													"text/html") != -1) {
												self.loadRequestHTML(xhr)
											}
										});
										self.runBuffer()
									};
									request.onerror = function(event) {
										self.fireError("网络问题")
									};
									request.send(null)
								};
								this.renderHTML = function(self, dochead,
										docbody) {
									xn.asyncHTML.frame.clearAllTimer();
									self.startLoadFrame();
									self.fixSelectInIE6();
									var headElements = [];
									var done = function() {
										xn.asyncHTML.frame.clearEvents();
										self.clearHeadElements();
										self._lastHeadElements = headElements;
										self.renderBody(docbody);
										self.fireEvent("unload");
										var _setTimeout = self.__timer.setTimeout;
										_setTimeout(function() {
											self.fireReady()
										}, 0);
										self.fireEvent("load")
									};
									var eles = [];
									for ( var i = 0; i < dochead.childNodes.length; i++) {
										var node = dochead.childNodes[i];
										if (node.nodeType === 1) {
											eles.push(node)
										}
									}
									var index = 0;
									var loadNext = function() {
										if (index === eles.length) {
											done();
											return
										}
										var ele = eles[index];
										index++;
										if (ele.tagName == "link"
												|| ele.tagName == "style") {
											ele = self.xml2html(ele);
											headElements.push(ele);
											self.head.appendChild(ele);
											loadNext()
										} else {
											if (ele.tagName == "script") {
												var src = ele
														.getAttribute("src");
												if (src) {
													Loader
															.loadScript(
																	src,
																	function(
																			ele) {
																		headElements
																				.push(ele);
																		loadNext()
																	})
												} else {
													ele = self.xml2html(ele);
													self.head.appendChild(ele);
													if (ua.ua.gecko) {
														eval.call(window,
																ele.innerHTML)
													}
													headElements.push(ele);
													loadNext()
												}
											} else {
												if (ele.tagName == "title") {
													document.title = ua.ua.ie ? ele.text
															: ele.textContent;
													loadNext()
												} else {
													loadNext()
												}
											}
										}
									};
									loadNext()
								};
								this.renderBody = function(self, bodyDiv) {
									var layout = bodyDiv
											.getAttribute("ui-layout");
									if (layout) {
										document.body.className = "layout_"
												+ layout
									} else {
										document.body.className = "layout_"
												+ self.defaultLayout
									}
									for ( var i = 0; i < bodyDiv.childNodes.length; i++) {
										var node = bodyDiv.childNodes[i];
										if (node.nodeType != 1) {
											continue
										}
										var id = node.getAttribute("id");
										if (!id) {
											continue
										}
										var placeholder = document
												.getElementById(id);
										if (!placeholder) {
											continue
										}
										placeholder.parentNode.replaceChild(
												node, placeholder);
										i--;
										if (!ua.ua.gecko || ua.ua.gecko === 2) {
											self.evalScripts(node)
										}
									}
									if (ua.ua.opera) {
										var reDrawNode = dom.id("main2");
										reDrawNode.innerHTML += ""
									}
								};
								this.clearHeadElements = function(self) {
									var elements = self._lastHeadElements;
									for ( var node, i = 0; i < elements.length; i++) {
										node = elements[i];
										node.parentNode.removeChild(node)
									}
									elements.length = 0
								};
								this.overwriteTimer = function(self) {
									window.__timeouts = [];
									window.__intervals = [];
									window.setTimeout = function(a1, a2) {
										var _setTimeout = self.__timer.setTimeout;
										var timer = _setTimeout(a1, a2);
										window.__timeouts.push(timer);
										return timer
									};
									window.setInterval = function(a1, a2) {
										var _setInterval = self.__timer.setInterval;
										var timer = _setInterval(a1, a2);
										window.__intervals.push(timer);
										return timer
									}
								};
								this.recoverTimer = function(self) {
									window.setTimeout = self.__timer.setTimeout;
									window.setInterval = self.__timer.setInterval
								};
								this.overwriteDomModule = function(self) {
									object._loader.lib.dom.fn = function() {
										self.__domModuleFn.apply(this,
												arguments);
										xn.asyncHTML.frame.wrap(this)
									}
								};
								this.recoverDomModule = function(self) {
									object._loader.lib.dom.fn = self.__domModuleFn
								};
								this.fireLoading = function() {
									window.__domLoading = true;
									window.__asyncDomloadHooks = [];
									window.__firer = dom.wrap(document
											.createElement("div"))
								};
								this.__runHooks = function() {
									window.__asyncDomloadHooks
											.forEach(function(f, i) {
												try {
													f()
												} catch (e) {
												}
											});
									window.__asyncDomloadHooks = []
								};
								this.fireReady = function(self) {
									window.__domLoading = false;
									if ((ua.ua.webkit && ua.ua.webkit < 525)
											|| !document.addEventListener) {
										self.__runHooks()
									}
									if (document.addEventListener) {
										window.__firer
												.fireEvent("AsyncDOMContentLoaded")
									}
								};
								this.fixSelectInIE6 = function(self) {
									if (ua.ua.ie !== 6) {
										return
									}
									var selectEles = document
											.getElementsByTagName("select");
									if (selectEles) {
										for ( var j = 0; j < selectEles.length; j++) {
											selectEles[j].style.visibility = "hidden"
										}
									}
								};
								this.evalScripts = function(self, node) {
									var scripts = node
											.getElementsByTagName("script");
									for ( var script, i = 0; i < scripts.length; i++) {
										script = scripts[i];
										try {
											if (ua.ua.ie) {
												window
														.execScript(script.innerHTML)
											} else {
												window.eval(script.innerHTML)
											}
										} catch (e) {
										}
									}
								};
								this.bind = function(self, ele) {
									dom
											.wrap(document.body)
											.delegate(
													"a",
													"click",
													function(e) {
														if (this
																.getAttribute("ui-async")
																|| (this.hostname == self.location.hostname
																		&& this.pathname == self.location.pathname && this.href
																		.indexOf("#") == -1)) {
															e.preventDefault();
															if (dom
																	.id("homeLoverTipBox")) {
																dom
																		.id(
																				"homeLoverTipBox")
																		.hide()
															}
															self
																	.open(string
																			.trim(this.href))
														}
													});
									dom
											.wrap(document.body)
											.delegate(
													"form[ui-async=async] *[type=submit]",
													"click",
													function(e) {
														e.preventDefault();
														if (this.form.onsubmit) {
															var r = this.form
																	.onsubmit();
															if (!r) {
																return
															}
														}
														var url = this.form.action;
														var queryString = "";
														for ( var element, i = 0; i < this.form.elements.length; i++) {
															element = this.form.elements[i];
															if (element.name) {
																queryString += (element.name
																		+ "="
																		+ encodeURIComponent(element.value) + "&")
															}
														}
														queryString = queryString
																.slice(0, -1);
														self
																.open(url
																		+ (url
																				.indexOf("?") != -1 ? "&"
																				: "?")
																		+ queryString)
													})
								};
								this.xml2html = function(self, node) {
									if (ua.ua.ie) {
										if (node.tagName == "link") {
											return document
													.createElement(node.xml)
										} else {
											if (node.tagName == "script") {
												var tmp = document
														.createElement("script");
												tmp.text = node.text;
												return tmp
											}
										}
										var xsl = new ActiveXObject(
												"Microsoft.XMLDOM");
										xsl.async = false;
										xsl.loadXML(self._xslt);
										var result = node.transformNode(xsl);
										node = dom.Element.fromString(result);
										return node
									} else {
										if (ua.ua.webkit || ua.ua.opera) {
											if (node.tagName == "script") {
												tmp = document
														.createElement("script");
												tmp.textContent = node.textContent;
												node = tmp
											} else {
												if (node.tagName == "link") {
													tmp = document
															.createElement("link");
													tmp.href = node.href;
													tmp.rel = node.rel;
													tmp.type = node.type;
													tmp.media = node.media;
													tmp.textContent = node.textContent;
													node = tmp
												} else {
													self.replaceBadFormed(node);
													if (ua.ua.webkit) {
														self.replaceCData(node)
													}
													node = document.importNode(
															node, true)
												}
											}
										} else {
											self.replaceBadFormed(node)
										}
									}
									return node
								};
								this.replaceCData = function(self, node) {
									var as = node.getElementsByTagName("*");
									for ( var i = 0, ilen = as.length; i < ilen; i++) {
										for ( var j = 0, jlen = as[i].childNodes.length; j < jlen; j++) {
											if (as[i].childNodes[j].nodeType == 4) {
												var t = document
														.createTextNode();
												t.nodeValue = as[i].childNodes[j].textContent;
												as[i].replaceChild(t,
														as[i].childNodes[j]);
												break
											}
										}
									}
								};
								this.replaceBadFormed = function(self, node) {
									var badformed = dom.getElements(
											"*[ui-badformed]", node);
									for ( var i = 0; i < badformed.length; i++) {
										var tmp = dom
												.getDom(badformed[i].textContent);
										badformed[i].innerHTML = "";
										while (tmp.firstChild) {
											badformed[i]
													.appendChild(tmp.firstChild)
										}
									}
								}
							})
				});
object.add("xn.asyncHTML.frame", "ua", function(exports, ua) {
	this.wrap = function(dom) {
		dom.ready = function(callback) {
			if (window.__domLoading) {
				if ((ua.ua.webkit && ua.ua.webkit < 525)
						|| !document.addEventListener) {
					window.__asyncDomloadHooks.push(callback)
				}
				if (document.addEventListener) {
					window.__firer.addEventListener("AsyncDOMContentLoaded",
							callback, false)
				}
			} else {
				callback()
			}
		};
		function addEvent(self, type, func, cap) {
			if (window.__logEvents) {
				if (!window.__events) {
					window.__events = []
				}
				window.__events.push( {
					node : self,
					type : type,
					func : func
				})
			}
			domAddEvent.apply(self, arguments)
		}
		var domAddEvent = dom.Element.prototype.addEvent.im_func;
		dom.Element.__mixin__("addEvent", addEvent);
		window.addEvent = document.addEvent = function(type, func, cap) {
			addEvent(this, type, func, cap)
		}
	};
	this.clearEvents = function() {
		if (window.__events) {
			window.__events.forEach(function(event) {
				dom.Element.removeEvent(event.node, event.type, event.func)
			});
			window.__events = []
		}
	};
	this.clearAllTimer = function() {
		var i;
		if (window.__timeouts) {
			for (i = 0; i < window.__timeouts.length; i++) {
				clearTimeout(window.__timeouts[i])
			}
		}
		if (window.__intervals) {
			for (i = 0; i < window.__intervals.length; i++) {
				clearInterval(window.__intervals[i])
			}
		}
		window.__timeouts = [];
		window.__intervals = []
	}
});
function switchSkin() {
	new Ajax.Request("http://i." + XN.env.domain + "/store/hometpl/list", {
		method : "get",
		onSuccess : function(o) {
			$("zidou_template").innerHTML = o.responseText;
			$("zidou_template").eval_inner_JS()
		}
	})
}
XN.dom.ready(function() {
	if (location.hash == "#switchSkin") {
		switchSkin()
	}
});
if (XN.browser.IE) {
	setInterval(function() {
		if (document.title.indexOf("#") != -1) {
			document.title = document.title.replace(/#.*$/i, "")
		}
	}, 200)
}
function TreeView(eles, options) {
	this.parent = null;
	this.parentEle = null;
	this.eles = eles;
	this.branches = [];
	this.options = {
		className : "selected",
		openedClassName : "opened",
		hideBranches : false,
		autoActive : true,
		checkEvent : function(e) {
			return true
		}
	};
	this.initialize(options)
}
(function TreeViewPrototype() {
	this.traversal = function(callback) {
		callback(this);
		for ( var i = 0; i < this.branches.length; i++) {
			this.branches[i].traversal(callback)
		}
	};
	this.addBranch = function(ele, branchEles, options) {
		var branchView = new TreeView(branchEles, options);
		branchView.parent = this;
		branchView.parentEle = ele;
		this.branches.push(branchView);
		return branchView
	};
	this.getBranch = function(ele) {
		for ( var i = 0; i < this.branches.length; i++) {
			if (this.branches[i].parentEle == ele) {
				return this.branches[i]
			}
		}
	};
	this.show = function() {
		for ( var i = 0; i < this.eles.length; i++) {
			this.eles[i].style.display = "block"
		}
	};
	this.hide = function() {
		for ( var i = 0; i < this.eles.length; i++) {
			this.eles[i].style.display = "none"
		}
	};
	this.change = function(ele) {
		for ( var i = 0; i < this.branches.length; i++) {
			if (this.branches[i].parentEle == ele) {
				this.branches[i].show()
			} else {
				this.branches[i].hide()
			}
			this.branches[i].deactiveAll()
		}
	};
	this.active = function(ele) {
		var root = this;
		while (root.parent) {
			root.change();
			root.show();
			root = root.parent
		}
		root.deactiveAll();
		ele.addClass(this.options.className);
		this.change(ele);
		if (this.parentEle) {
			$(this.parentEle).addClass(this.options.openedClassName)
		}
	};
	this.deactiveAll = function() {
		this.traversal(function(branch) {
			for ( var i = 0; i < branch.eles.length; i++) {
				$(branch.eles[i]).delClass(branch.options.className);
				$(branch.eles[i]).delClass(branch.options.openedClassName)
			}
		})
	};
	this.bind = function(ele) {
		ele = $(ele);
		ele.addEvent("click", function(e) {
			if (!this.options.checkEvent(e)) {
				return
			}
			this.active(ele)
		}.bind(this))
	};
	this.initialize = function(options) {
		this.__parseoptions(options);
		if (this.options.autoActive) {
			for ( var i = 0; i < this.eles.length; i++) {
				this.bind(this.eles[i])
			}
		}
	};
	this.__parseoptions = function(options) {
		if (!options) {
			return
		}
		for ( var i in options) {
			if (options.hasOwnProperty(i) && this.options[i] != undefined) {
				this.options[i] = options[i]
			}
		}
	}
}).call(TreeView.prototype);
object
		.use(
				"dom, xn.asyncHTML.manager",
				function(exports, dom, xn) {
					window.asyncHTMLManager = new xn.asyncHTML.manager.AsyncHTMLManager();
					HTML5History.bind(window.history, {
						fireEvent : function(ele, type) {
							dom.Element.fireEvent(ele, type)
						},
						blankHTML : "/ajaxproxy.htm"
					});
					dom.wrap(window);
					window.addEvent("popstate", function(event) {
						asyncHTMLManager.listenHash(location.hash.substring(1))
					});
					dom.ready(function() {
						window.asyncHTMLManager.bind(document)
					});
					dom
							.ready(function() {
								var $$ = Sizzle;
								var siteMenu = $$(
										".site-menu-nav .nav-item .item-title")
										.concat(
												$$(".site-menu-apps .with-menu .item-title"));
								var treeView = new TreeView(siteMenu, {
									hideBranches : true,
									autoActive : false
								});
								for ( var i = 0; i < siteMenu.length; i++) {
									(function(menu) {
										var branchEles = $$("ul li",
												menu.parentNode);
										if (!branchEles.length) {
											return
										}
										treeView.addBranch(menu, branchEles, {
											autoActive : false,
											eventCheck : function(e) {
												if (e.srcElement.tagName
														.toUpperCase() != "A") {
													return false
												}
												return true
											}
										})
									})(siteMenu[i])
								}
								var funcRuned = false;
								var func = function(e) {
									funcRuned = true;
									var currentHref = window.asyncHTMLManager.location;
									treeView
											.traversal(function(branch) {
												for ( var i = 0; i < branch.eles.length; i++) {
													var tabHref = $$("a",
															branch.eles[i])[0];
													if (currentHref.host
															.toLowerCase() == tabHref.host
															.toLowerCase()
															&& currentHref.pathname
																	.toLowerCase() == tabHref.pathname
																	.toLowerCase()) {
														if (treeView.activedEle) {
															$(
																	treeView.activedEle)
																	.delClass(
																			"loading")
														}
														$(branch.eles[i])
																.addClass(
																		"loading");
														treeView.activedBranch = branch;
														treeView.activedEle = branch.eles[i]
													}
												}
											});
									document.documentElement.style.cursor = "progress";
									XN.ui.clearDialog()
								};
								var delFunc = function() {
									if (treeView.activedEle) {
										var moreTrigger = $("site-menu-apps-more");
										var lessTrigger = $("site-menu-apps-less");
										var list = $("site-menu-apps-all");
										if (Sizzle(".loading", list).length > 0) {
											moreTrigger.hide();
											lessTrigger.show();
											list.show()
										}
										$(treeView.activedEle).delClass(
												"loading")
									}
									if (treeView.activedBranch) {
										treeView.activedBranch
												.active(treeView.activedEle)
									}
									document.documentElement.style.cursor = ""
								};
								var pvAdd = function() {
									COMSCORE.beacon( {
										c1 : 2,
										c2 : 6934070,
										c3 : "",
										c4 : "",
										c5 : "",
										c6 : "",
										c15 : ""
									})
								};
								window.asyncHTMLManager.addEvent("beforeload",
										func);
								window.asyncHTMLManager.addEvent("load",
										delFunc);
								window.asyncHTMLManager.addEvent("load", pvAdd);
								window.asyncHTMLManager.addEvent("error",
										delFunc);
								window.asyncHTMLManager
										.addEvent(
												"error",
												function(error) {
													if (!error
															|| error.number == 10) {
														$("content").innerHTML = '<h2>页面出错了 ^^，请尝试刷新页面(Ctrl+F5)，如果还不行，请<a href="http://support.renren.com/GetGuestbookList.do">联系客服人员</a>。</h2>'
													} else {
														if (error.number == 1) {
															location.reload()
														}
													}
												});
								var moreTrigger = $("site-menu-apps-more");
								var lessTrigger = $("site-menu-apps-less");
								var list = $("site-menu-apps-all");
								if (moreTrigger && lessTrigger) {
									moreTrigger.addEvent("click", function() {
										moreTrigger.hide();
										lessTrigger.show();
										list.show()
									});
									lessTrigger.addEvent("click", function() {
										lessTrigger.hide();
										moreTrigger.show();
										list.hide()
									})
								}
								var moreGroupTrigger = $("site-menu-minigroups-more");
								var lessGroupTrigger = $("site-menu-minigroups-less");
								var grouplist = $("site-menu-minigroups-all");
								var allGroupsLink = $("all-minigroups-link");
								if (moreGroupTrigger && lessGroupTrigger) {
									moreGroupTrigger.addEvent("click",
											function() {
												moreGroupTrigger.hide();
												lessGroupTrigger.show();
												grouplist.show();
												if (allGroupsLink) {
													allGroupsLink.show()
												}
											});
									lessGroupTrigger.addEvent("click",
											function() {
												lessGroupTrigger.hide();
												moreGroupTrigger.show();
												grouplist.hide();
												if (allGroupsLink) {
													allGroupsLink.hide()
												}
											})
								}
								if (!funcRuned) {
									func()
								}
							});
					if (XN.user.isGuide) {
						dom.ready(function() {
							window.asyncHTMLManager.initPage()
						})
					} else {
						window.asyncHTMLManager.initPage(true)
					}
				});
function popupMusicBox() {
	var lisFeat = "toolbar=no,location=no,directories=no,menubar=no,resizable=yes,status=yes,scrollbars=no,width=1000,height=640,left=100,top=10";
	var rrMCWin = window.open("http://music." + XN.env.domain + "/musicbox ",
			"rrMCWin", lisFeat);
	if (rrMCWin) {
		rrMCWin.focus()
	} else {
		alert("你的浏览器拦截了播放器窗口,请设置!");
		return false
	}
}
window.asyncHTMLManager.addEvent("load", function() {
	if (!$("attentionFeeds")) {
		return
	}
	var href = window.asyncHTMLManager.location.href;
	if (!/http:\/\/www.renren.com\/home/.test(href)
			&& !/http:\/\/guide.renren.com\/guide/.test(href)) {
		return
	}
	if (/http:\/\/www.renren.com\/homeAttention/
			.test(window.asyncHTMLManager.location.href)) {
		if ($("attentionCounts")) {
			$("attentionCounts").remove()
		}
		$("attentionFeeds").delClass("new");
		return
	}
	new XN.NET.xmlhttp( {
		url : "http://www.renren.com/feedfocuscount.do",
		method : "get",
		onSuccess : function(xmlHttp) {
			var count = parseInt(xmlHttp.responseText);
			if (typeof (count) != "number" || count.toString() == "NaN"
					|| count <= 0) {
				return
			}
			if (count > 99) {
				count = "99+"
			}
			if ($("attentionCounts")) {
				$("attentionCounts").remove()
			}
			var html = $element("span");
			html.id = "attentionCounts";
			html.className = "nav-item-count";
			html.innerHTML = count;
			html.title = count + "条新增特别关注内容";
			$("attentionFeeds").appendChild(html)
		}
	})
});
function delHomeApp(event, appId) {
	XN.event.stop(event);
	var id = appId;
	new XN.net.xmlhttp( {
		url : "http://apps." + XN.env.domain + "/menu/deleteRecent.do",
		data : "app_id=" + id,
		onSuccess : function(r) {
			$("appsItem_" + id).remove()
		},
		onError : function() {
			XN.DO.showError("网络错误，请稍后再试", "删除失败")
		}
	})
}
XN.dom
		.ready(function() {
			if (!$("allAppsInvite")) {
				return
			}
			if ($("showAppInviteTip")) {
				var tip = [
						'<div id="appsInviteTip">',
						'<div style="width:147px;height:34px;padding:8px;border:1px solid #F9B967;background:#FFFBC1;overflow:hidden;"><p style="float:left;width:130px;line-height:1.4em">你有',
						$("allAppsInvite").innerHTML,
						'条应用请求，点击数字处理请求</p><a href="javascript:;" style="float:right;" class="x-to-hide"></a></div>',
						'<div style="width:6px;height:9px;margin-left:-5px;margin-top:-32px;background:url(http://a.xnimg.cn/imgpro/arrow/tip-arrow-left-s.png) 0 0 no-repeat;position:relative;"></div>',
						"</div>" ];
				var tipe = $element("div");
				tipe.innerHTML = tip.join("");
				tipe.style.display = "none";
				$("dropmenuHolder").appendChild(tipe);
				var tipShow = new XN.ui.fixPositionElement( {
					id : "appsInviteTip",
					alignWith : "allAppsInvite",
					alignType : "2-1",
					offsetY : -17,
					offsetX : 8
				});
				new XN.net.xmlhttp( {
					url : "http://www." + XN.env.domain
							+ "/ajaxSetMemcache?memKey=appInvite" + XN.user.id
							+ "&memExpiry=21"
				});
				$(Sizzle("#appsInviteTip .x-to-hide")[0]).addEvent("click",
						function() {
							tipShow.remove()
						})
			}
			$("allAppsInvite").addEvent(
					"click",
					function(event) {
						XN.event.stop(event);
						window.open("http://app." + XN.env.domain
								+ "/app/appRequestList?origin=700045")
					})
		});
function openMusicBox() {
	var lisFeat = "toolbar=no,location=no,directories=no,menubar=no,resizable=yes,status=yes,scrollbars=no,width=1000,height=640,left=100,top=10", rrMCWin = window
			.open("http://music." + XN.env.domain + "/musicbox?from=homeleft",
					"rrMCWin", lisFeat);
	if (rrMCWin) {
		rrMCWin.focus()
	} else {
		alert("你的浏览器拦截了播放器窗口,请设置!");
		return false
	}
}
XN.dom.ready(function() {
	if (Sizzle(".minigroups .nav-item").length < 1) {
		return
	}
	var getMinigroupNews = function(ids) {
		new XN.net.xmlhttp( {
			url : "http://notify.renren.com/feed/group_unread",
			method : "get",
			data : "gl=" + ids + "&t=" + new Date(),
			onSuccess : function(r) {
				var j = XN.json.parse(r.responseText);
				var news = j.unread;
				for ( var key in news) {
					if (!news.hasOwnProperty(key) || news[key] < 1) {
						continue
					}
					var a = $("mg_" + key);
					if (Sizzle(".nav-item-count", a).length > 0) {
						Sizzle(".nav-item-count", a)[0].innerHTML = news[key]
					} else {
						var count = $element("span");
						count.className = "nav-item-count";
						count.innerHTML = news[key];
						a.appendChild(count)
					}
				}
			}
		})
	};
	var theId = function(id, ids) {
		if (ids.indexOf(id) != -1) {
			return id
		} else {
			return false
		}
	};
	var mga = Sizzle(".minigroups .with-count");
	var idList = [];
	for ( var i = 0; i < mga.length; i++) {
		idList.push(mga[i].id.slice(3))
	}
	var cb = function(c) {
		var mgId = c.toString();
		var id = theId(mgId, idList);
		if (id) {
			getMinigroupNews(id)
		}
	};
	var pagerCallback = function() {
		XN.webpager.addEvent("webpager_groupNumber_got", function(r) {
			var content = XN.json.parse(r.content), c = content.minigroup;
			if (content.actor == XN.user.id) {
				return false
			}
			cb(c)
		})
	};
	if (!XN.webpager) {
		if (!XN.webpagerUIReadyQ) {
			XN.webpagerUIReadyQ = []
		}
		XN.webpagerUIReadyQ.push(pagerCallback)
	} else {
		pagerCallback()
	}
});