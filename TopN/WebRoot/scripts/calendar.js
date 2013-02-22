var controlid = null;
var currdate = null;
var startdate = null;
var enddate  = null;
var yy = null;
var mm = null;
var hh = null;
var ii = null;
var currday = null;
var addtime = false;
var nowtoday = null;
var lastcheckedyear = false;
var lastcheckedmonth = false;
var ie =navigator.appName=="Microsoft Internet Explorer"?true:false;
function _cancelBubble(event) {
	e = event ? event : window.event ;
	if(ie) {
		e.cancelBubble = true;
	} else {
		e.stopPropagation();
	}
}

function getposition(obj) {
	var r = new Array();
	r['x'] = obj.offsetLeft;
	r['y'] = obj.offsetTop;
	while(obj = obj.offsetParent) {
		r['x'] += obj.offsetLeft;
		r['y'] += obj.offsetTop;
	}
	return r;
}
function loadcalendar() {
	s = '';
	s += '<div id="calendar" style="display:none; position:absolute; z-index:9;font: 11px Arial, Tahoma;font-size=:12px" onclick="_cancelBubble(event)">';
	if (ie)
	{
		s += '<iframe width="180" height="160" src="about:blank" style="position: absolute;z-index:-1;"></iframe>';
	}
	s += '<div style="width:180px;"><table style="background: white; border: 1px solid #86B9D6;" cellspacing="0" cellpadding="0" width="100%" style="text-align: center">';
	s += '<tr align="center" style="font: 12px Arial, Tahoma !important;font-weight: bold !important;font: 11px Arial, Tahoma;font-weight: bold;color: #154BA0;background: #C2DEED;height: 25px;padding-left: 10px;">';
	s += '<td style="padding-left: 10px;">'
	s += '<a style="color:#154BA0;text-decoration: none;" href="#" onclick="refreshcalendar(yy, mm-1);return false" title="pre month">&lt;&lt;</a></td>';
	s += '<td colspan="5" style="text-align: center" style="padding-left: 10px;">';
	s += '<a href="#" onclick="showdiv(\'year\');_cancelBubble(event);return false" title="choose year" id="year" style="padding-right: 10px;background: url(onbottom.gif) no-repeat center right;text-decoration: none;color:blue">';
	s += '</a>&nbsp; - &nbsp;<a id="month" style="color:blue;text-decoration: none;padding-right: 10px;background: url(onbottom.gif) no-repeat center right;" title="choose month" href="#" onclick="showdiv(\'month\');_cancelBubble(event);return false"></a></td>';
	s += '<td style="padding-left: 10px;"><A style="color: #154BA0;text-decoration: none;" href="#" onclick="refreshcalendar(yy, mm+1);return false" title="next month">&gt;&gt;</A></td></tr>';
	s += '<tr align="center" style="font:12px Arial, Tahoma !important;font:11px Arial, Tahoma;color: #92A05A;height: 20px;background-color: #FFFFD9;">';
	s += '<td width="26px" style="border-bottom: 1px solid #DEDEB8;" align="center">周日</td>';
	s += '<td width="26px" style="border-bottom: 1px solid #DEDEB8;" align="center">周一</td>';
	s += '<td width="26px" style="border-bottom: 1px solid #DEDEB8;" align="center">周二</td>';
	s += '<td width="26px" style="border-bottom: 1px solid #DEDEB8;" align="center">周三</td>';
	s += '<td width="26px" style="border-bottom: 1px solid #DEDEB8;" align="center">周四</td>';
	s += '<td width="26px" style="border-bottom: 1px solid #DEDEB8;" align="center">周五</td>';
	s += '<td width="26px" style="border-bottom: 1px solid #DEDEB8;" align="center">周六</td></tr>';
	for(var i = 0; i < 6; i++) {
		s += '<tr style="font-size:12px;" align="center">';
		for(var j = 1; j <= 7; j++)
			s += "<td id=d" + (i * 7 + j) + " height=\"19\" style=\"font-size:12px\">0</td>";
		s += "</tr>";
	}
	s += '<tr style="font-size:12px"><td height=\"19\" colspan="7" align="center" style="margin:auto"><a href="#" style="color:#86B9D6;text-decoration:none" onclick="getNow()" title="select today">today</a>&nbsp;&nbsp;';
	s += '<a href="#" onclick="clearDay()" style="color:#86B9D6;text-decoration:none" title="clear">clear</a>';
	s += '&nbsp;&nbsp;<a href="#" onclick="closeDay()" style="color:#86B9D6;text-decoration:none" title="close">close</a></td></tr>';
	s += '<tr id="hourminute"><td colspan="7" align="center"><input type="text" size="1" value="" id="hour" onKeyUp=\'this.value=this.value > 23 ? 23 : zerofill(this.value);controlid.value=controlid.value.replace(/\\d+(\:\\d+)/ig, this.value+"$1")\'> hour <input type="text" size="1" value="" id="minute" onKeyUp=\'this.value=this.value > 59 ? 59 : zerofill(this.value);controlid.value=controlid.value.replace(/(\\d+\:)\\d+/ig, "$1"+this.value)\'> minute</td></tr>';
	s += '</table></div></div>';
	s += '<div id="calendar_year" style="display: none;line-height: 130%;background: #FFFFFF;position: absolute;z-index: 10;" onclick="_cancelBubble(event)"><div style="float: left;background: #FFFFFF;margin-left: 1px;border: 1px solid #86B9D6;padding: 4px; font-size:12px;color:blue">';
	for(var k = 1973; k <= 2012; k++) {
		s += k != 2000 && k % 10 == 0 ? '</div><div style="float: left;background: #FFFFFF;margin-left: 1px;border: 1px solid #86B9D6;padding: 4px; font-size:12px;color:blue;">' : '';
		s += '<a style="color:blue;text-decoration: none;" href="#" onclick="refreshcalendar(' + k + ', mm);document.getElementById(\'calendar_year\').style.display=\'none\';document.getElementById(\'calendar_month\').style.display=\'none\';return false">';
		s += '<span' + (nowtoday.getFullYear() == k ? ' style="color:#00BB00;"' : '') + ' id="calendar_year_' + k + '">' + k + '</span></a><br />';
	}
	s += '</div></div>';
	s += '<div id="calendar_month" style="display: none;background: #FFFFFF;line-height: 130%;border: 1px solid #86B9D6;padding: 4px;position: absolute;z-index: 11; font-size:12px;color:blue" onclick="_cancelBubble(event)">';
	for(var k = 1; k <= 12; k++) {
		s += '<a style="color:blue;text-decoration: none;" href="#" style="font-size=12px; color:blue" onclick="refreshcalendar(yy, ' + (k - 1) + ');document.getElementById(\'calendar_month\').style.display=\'none\';document.getElementById(\'calendar_year\').style.display=\'none\';return false">';
		s += '<span' + (nowtoday.getMonth()+1 == k ? ' style="color:#00BB00;"' : '') + ' id="calendar_month_' + k + '">' + k + ( k < 10 ? '&nbsp;' : '') + '</span></a><br />';
	}
	s += '</div>';

	var nElement = document.createElement("div");
	nElement.innerHTML=s;
	document.getElementsByTagName("body")[0].appendChild(nElement);

//	document.write(s);
	document.onclick = function(event) {
		document.getElementById('calendar').style.display = 'none';
		document.getElementById('calendar_year').style.display = 'none';
		document.getElementById('calendar_month').style.display = 'none';
	}
	document.getElementById('calendar').onclick = function(event) {
		_cancelBubble(event);
		document.getElementById('calendar_year').style.display = 'none';
		document.getElementById('calendar_month').style.display = 'none';
	}
}

function parsedate(s) {
	/(\d+)\-(\d+)\-(\d+)\s*(\d*):?(\d*)/.exec(s);
	var m1 = (RegExp.$1 && RegExp.$1 > 1899 && RegExp.$1 < 2101) ? parseFloat(RegExp.$1) : nowtoday.getFullYear();
	var m2 = (RegExp.$2 && (RegExp.$2 > 0 && RegExp.$2 < 13)) ? parseFloat(RegExp.$2) : nowtoday.getMonth() + 1;
	var m3 = (RegExp.$3 && (RegExp.$3 > 0 && RegExp.$3 < 32)) ? parseFloat(RegExp.$3) : nowtoday.getDate();
	var m4 = (RegExp.$4 && (RegExp.$4 > -1 && RegExp.$4 < 24)) ? parseFloat(RegExp.$4) : 0;
	var m5 = (RegExp.$5 && (RegExp.$5 > -1 && RegExp.$5 < 60)) ? parseFloat(RegExp.$5) : 0;
	/(\d+)\-(\d+)\-(\d+)\s*(\d*):?(\d*)/.exec("0000-00-00 00\:00");
	return new Date(m1, m2 - 1, m3, m4, m5);
}

function settime(d) {
	document.getElementById('calendar').style.display = 'none';
	controlid.value = yy + "-" + zerofill(mm + 1) + "-" + zerofill(d) + (addtime ? ' ' + zerofill(document.getElementById('hour').value) + ':' + zerofill(document.getElementById('minute').value) : '');
}

function showcalendar(event, controlid1, addtime1, startdate1, enddate1) {
	nowtoday = new Date();
	loadcalendar();
	controlid = controlid1;
	addtime = addtime1;
	startdate = startdate1 ? parsedate(startdate1) : false;
	enddate = enddate1 ? parsedate(enddate1) : false;
	currday = controlid.value ? parsedate(controlid.value) : nowtoday;
	hh = currday.getHours();
	ii = currday.getMinutes();
	var p = getposition(controlid);
	document.getElementById('calendar').style.display = 'block';
	document.getElementById('calendar').style.left = p['x']+'px';
	document.getElementById('calendar').style.top	= (p['y'] + 20)+'px';
	_cancelBubble(event);
	refreshcalendar(currday.getFullYear(), currday.getMonth());
	if(lastcheckedyear != false) {
		document.getElementById('calendar_year_' + lastcheckedyear).style.backgroundColor = '#ffffff';
		document.getElementById('calendar_year_' + nowtoday.getFullYear()).style.backgroundColor = '#00BB00';
	}
	if(lastcheckedmonth != false) {
		document.getElementById('calendar_month_' + lastcheckedmonth).style.backgroundColor = '#ffffff';
		document.getElementById('calendar_month_' + (nowtoday.getMonth() + 1)).style.backgroundColor = '#00BB00';
	}
	document.getElementById('calendar_year_' + currday.getFullYear()).style.backgroundColor = '#FF0000';
	document.getElementById('calendar_month_' + (currday.getMonth() + 1)).style.backgroundColor = '#FF0000';
	document.getElementById('hourminute').style.display = addtime ? '' : 'none';
	lastcheckedyear = currday.getFullYear();
	lastcheckedmonth = currday.getMonth() + 1;
}

function refreshcalendar(y, m) {
	var x = new Date(y, m, 1);
	var mv = x.getDay();
	var d = x.getDate();
	var dd = null;
	yy = x.getFullYear();
	mm = x.getMonth();
	document.getElementById("year").innerHTML = yy;
	document.getElementById("month").innerHTML = mm + 1 > 9  ? (mm + 1) : '0' + (mm + 1);

	for(var i = 1; i <= mv; i++) {
		dd = document.getElementById("d" + i);
		dd.innerHTML = "&nbsp;";
		dd.style.backgroundColor = '#ffffff';
		dd.setAttribute("onmouseover","");
		dd.setAttribute("onmouseout","");
	}

	while(x.getMonth() == mm) {
		dd = document.getElementById("d" + (d + mv));
		dd.innerHTML = '<a href="###" style="text-decoration: none;color:#000;" onclick="settime(' + d + ');return false">' + d + '</a>';
		if(x.getTime() < nowtoday.getTime() || (enddate && x.getTime() > enddate.getTime()) || (startdate && x.getTime() < startdate.getTime())) {
			dd.style.backgroundColor = '#FFFAFA';
			dd.setAttribute("onmouseover","style.backgroundColor=\'#FFEBCD\'");
			dd.setAttribute("onmouseout","style.backgroundColor=\'#FFFAFA\'");
		} else {
			dd.style.backgroundColor = '#F0F8FF';
			dd.setAttribute("onmouseover","style.backgroundColor=\'#FFEBCD\'");
			dd.setAttribute("onmouseout","style.backgroundColor=\'#F0F8FF\'");
		}
		if(x.getFullYear() == nowtoday.getFullYear() && x.getMonth() == nowtoday.getMonth() && x.getDate() == nowtoday.getDate()) {
			dd.style.backgroundColor = "#00BB00";
			dd.firstChild.title = 'today';
		}
		if(x.getFullYear() == currday.getFullYear() && x.getMonth() == currday.getMonth() && x.getDate() == currday.getDate()) {
			dd.style.backgroundColor = '#FAEBD7';
		}
		x.setDate(++d);
	}

	while(d + mv <= 42) {
		dd = document.getElementById("d" + (d + mv));
		dd.innerHTML = "&nbsp;";
		dd.style.backgroundColor = '#ffffff';
		dd.setAttribute("onmouseover","");
		dd.setAttribute("onmouseout","");
		d++;
	}

	if(addtime) {
		document.getElementById('hour').value = zerofill(hh);
		document.getElementById('minute').value = zerofill(ii);
	}
}

function showdiv(id) {

	var p = getposition(document.getElementById(id));
	document.getElementById('calendar_' + id).style.left = p['x']+'px';
	document.getElementById('calendar_' + id).style.top = (p['y'] + 16)+'px';
	document.getElementById('calendar_' + id).style.display = 'block';
}

function zerofill(s) {
	var s = parseFloat(s.toString().replace(/(^[\s0]+)|(\s+$)/g, ''));
	s = isNaN(s) ? 0 : s;
	return (s < 10 ? '0' : '') + s.toString();
}

function getNow() {
	var nowDate = new Date();
	document.getElementById('calendar').style.display = 'none';
	controlid.value = nowDate.getFullYear() + "-" + (nowDate.getMonth()+1>9?nowDate.getMonth()+1:"0"+nowDate.getMonth()+1) + "-" + nowDate.getDate(); 
}
function clearDay() {
	document.getElementById('calendar').style.display = 'none';
	controlid.value="";
}
function closeDay() {
	document.getElementById('calendar').style.display = 'none';
}
