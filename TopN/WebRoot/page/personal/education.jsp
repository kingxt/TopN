<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>

<script type="text/javascript">
<!--
	$(document).ready(function(){
		 $("#pro_hi_ul li:last-child").remove();
		 $("#pro_hi_ul li:last-child").remove();
	});
	
	function sea(event){
		if(event.keyCode==13){
			searchUniversities();
		}  
	}
	
	function searchUniversities(){
		var con = jQuery.trim($("#universitySearch").val());
		if(null == con || con == ''){
			return;
		}
		$("#univercity_ul").html("<center style='padding-top:60px'>正在搜索，请稍后。。。</center>");
		var params = {
			'college' : con 
		}
		new TopNAjax().ajax({
			cache: false,
			url: 'searchUniversities.action',
			loading : false,
			data : params,
			success: function (msg) {
				if(jQuery.trim(msg) == ''){
					$("#univercity_ul").html("<center style='padding-top:60px'>没有搜索到和您输入匹配的大学</center>");
				}else{
					$("#univercity_ul").html(msg);
				}				
				
	        }
		});
	}
	
	function selectSchool(){
		if($("#schoolType").val()=='大学'){
			showBox("#university", "选择大学", true);
		}else if($("#schoolType").val()=='高中'){
			showBox("#high_school", "选择高中", true);
		}
	}
	
	function selectHighSchool(){
		showBox("#high_school", "选择高中", true);
	}
	
	function selectCollSchool(){
		showBox("#university", "选择大学", true);
	}
	
	function getUniversitiesByProvinceId(obj){
		
		$.ajax({
			type : "post",
			url : 'loadUniversity.action',
			data: 'provinceId='+obj.id,
			cache : true,
			error : function() {
				alert('加载页面时出错！');
			},
			success : function(msg) {
				$("#univercity_ul").html(msg);
			}
		});
	}
	
	function loadCityByProvince(obj){
		if(obj.id == 1){
			var temp = new Object(); temp.id = 1;
			$("#city").hide();
			loadDistrictByCity(temp);
			return;
		}else if(obj.id == 2){
			var temp = new Object(); temp.id = 2;
			$("#city").hide();
			loadDistrictByCity(temp);
			return;
		}else if(obj.id == 6){
			var temp = new Object(); temp.id = 3;
			$("#city").hide();
			loadDistrictByCity(temp);
			return;
		}else if(obj.id == 19){
			var temp = new Object(); temp.id = 4;
			$("#city").hide();
			loadDistrictByCity(temp);
			return;
		}else if(obj.id == 32){
			var temp = new Object(); temp.id = 5;
			$("#city").hide();
			loadDistrictByCity(temp);
			return;
		}
		$("#city").show();
		$.ajax({
			type : "post",
			url : 'loadCityByProvince.action',
			data: 'provinceId='+obj.id,
			cache : true,
			error : function() {
				alert('加载页面时出错！');
			},
			success : function(msg) {
				$("#city").html(msg);
				var first = $("#city").find("li").first();
				if(null != first){
					var fiId = first.find("a").first().attr("id");
					if(Number(fiId) != 0){
						var obj = new Object(); obj.id = fiId;
						loadDistrictByCity(obj);
					}
				}
			}
		});
	}
	
	function loadDistrictByCity(obj){
		//下面做越级处理
		var temp = new Object();
		if(Number(obj.id) >= 122 && Number(obj.id) <= 125){
			temp.id = 1082+Number(obj.id);
			rt(temp);return;
		}else if(Number(obj.id) >= 142 && Number(obj.id) <= 157){
			temp.id = 1194 + Number(obj.id);
			rt(temp);return;
		}else if(Number(obj.id) >= 324 && Number(obj.id) <= 326){
			temp.id = 2301 + Number(obj.id);
			rt(temp);return;
		}
		
		$("#district").show();
		$.ajax({
			type : "post",
			url : 'loadDistrictByCity.action',
			data: 'cityId='+obj.id,
			cache : true,
			error : function() {
				alert('加载页面时出错！');
			},
			success : function(msg) {
				$("#district").html(msg);
				var first = $("#district").find("li").first();
				if(null != first){
					var fiId = first.find("a").first().attr("id");
					if(Number(fiId) != 0){
						var obj = new Object(); obj.id = fiId;
						loadHighSchoolByDistrict(obj);
					}
				}
			}
		});
	}
	
	function rt(obj){
		$("#district").hide();
		loadHighSchoolByDistrict(obj);
		return;
	}
	
	function loadHighSchoolByDistrict(obj){
		$.ajax({
			type : "post",
			url : 'loadHighSchoolByDistrict.action',
			data: 'districtId='+obj.id,
			cache : true,
			error : function() {
				alert('加载页面时出错！');
			},
			success : function(msg) {
				$("#high_school_dd").html(msg);
			}
		});
	}
	
	function selectedThis(obj2){
		if(obj2.id == '暂时没有此区域的学校信息'){
			return;
		}
		if(obj2.name == '1'){
			$("#collSchool").val(obj2.id);
		}else if(obj2.name == '2'){
			$("#highSchool").val(obj2.id);
		}
		
		closeBox();
	}
	
	function education_submit(){
		//var params = "school=" + $("#school").val();
		var params = {
            'college': $("#collSchool").val(),
            'colLevel': $("#enrollCollegeTime").val(),
            'highSchool': $("#highSchool").val(),
            'highLevel': $("#enrollHighSchoolTime").val(),
            'perSchool' : $("#perSchool").val()
        };
		new TopNAjax().ajax({
    		url: 'updateEducation.action',
    		data: params,
    		async : false,
    		cache : false,
    		loading : false,
    		success: function (msg) { 
    			showUpdateSuccess();
    		}
        });
	}
//-->
</script>
 <table class="basic_info_table" >
                        <tbody>
                           <tr>
                                <td class="des_td">教育保密：</td>
                                <td>
                                <select name="per.school" id="perSchool">
									<option value="1" <s:if test="#per.perSchool == 1">selected</s:if>>
										公开
									</option>
									<option value="2" <s:if test="#per.perSchool == 2">selected</s:if>>
										好友可见
									</option>
									<option value="3" <s:if test="#per.perSchool == 3">selected</s:if>>
										保密
									</option>
								</select>
								</td>
                            </tr>
                             <tr>
                                <td  class="des_td"><span style="color: red;">*</span>高中：</td>
                                <td>
                                	<input name="pi.highSchool" id="highSchool" onclick="selectHighSchool()"
				value="<s:property value="#highSchool"/>" />
								</td>
                            </tr>
                             <tr>
                                <td  class="des_td"><span style="color: red;">*</span>入学时间：</td>
                                <td>
		                          <select id="enrollHighSchoolTime" name="pi.highLevel">
									<s:bean name="com.topn.util.Counter" id="i">
										<s:param name="first" value="#endYear"/> <s:param name="last" value="1970"/>
										<s:param name="interval" value="-1"/>
										<s:iterator>
										<option value="<s:property value="#i.current + 1"/>" <s:if test="#highLevel == #i.current + 1">selected</s:if>>
										<s:property value="#i.current + 1"/>
									  </option>
									</s:iterator> 
									</s:bean> 
								</select>
                                </td>
                            </tr>
                           
                             <tr>
                                <td  class="des_td"><span style="color: red;">*</span>大学：</td>
                                <td>
                                	<input name="pi.college" id="collSchool" onclick="selectCollSchool()"
				value="<s:property value="#college"/>" />
								</td>
                            </tr>
                             <tr>
                                <td  class="des_td"><span style="color: red;">*</span>入学时间：</td>
                                <td>
		                           <select id="enrollCollegeTime" name="pi.colLevel">
									<s:bean name="com.topn.util.Counter" id="i">
										<s:param name="first" value="#endYear"/> <s:param name="last" value="1970"/>
										<s:param name="interval" value="-1"/>
										<s:iterator>
											<option value="<s:property value="#i.current + 1"/>" <s:if test="#colLevel == #i.current + 1">selected</s:if>>
												<s:property value="#i.current + 1"/>
											</option>
										</s:iterator> 
									</s:bean> 
								</select>
                                </td>
                            </tr>

                        </tbody>
                    </table>
 
 
<!--下面是大学 -->
<div class="education_div" id="university">
	<div>
		<table>
					<tr>
						<td>快速定位：</td>
						<td><input type="text" name="universitySearch" id="universitySearch" onkeyup="sea(event)"/></td>
						<td>输入后请按enter键</td>
					</tr>
		</table>
	</div>
	<div>
		<ul class="province_ul">
			<s:iterator value="#pc" var="p">
				<li>
					<a onclick="getUniversitiesByProvinceId(this)"
						id="<s:property value="#p.provinceId"/>"><s:property
							value="#p.name" /> </a>
				</li>
			</s:iterator>
		</ul>
	</div>
	<div>
		<ul class="univercity_ul" id="univercity_ul">
			<s:iterator value="#us" var="u">
				<li>
					<a onclick="selectedThis(this)" id="<s:property value="#u.name"/>" name="1"><s:property
							value="#u.name" /> </a>
				</li>
			</s:iterator>
		</ul>
	</div>
</div>


<!--下面是中学-->
<div class="education_div" id="high_school">
	<ul class="province_ul" id="pro_hi_ul">
		<s:iterator value="#pc" var="p">
			<li>
				<a onclick="loadCityByProvince(this)"
					id="<s:property value="#p.provinceId"/>"><s:property
						value="#p.name" /> </a>
			</li>
		</s:iterator>
	</ul>
	<ul class="district_ul" id="city" style="display: none">
		<s:iterator value="#cs" var="c">
			<li>
				<a onclick="loadDistrictByCity(this)" id="<s:property value="#c.cityId"/>"><s:property
						value="#c.name" /> </a>
			</li>
		</s:iterator>
	</ul>
	<ul class="district_ul" id="district">
		<s:iterator value="#ds" var="d">
			<li>
				<a onclick="loadHighSchoolByDistrict(this)" id="<s:property value="#d.districtId"/>"><s:property
						value="#d.name" /> </a>
			</li>
		</s:iterator>
	</ul>
	<ul class="univercity_ul" id="high_school_dd">
		<s:iterator value="#hs" var="h">
			<li>
				<a onclick="selectedThis(this)" id="<s:property value="#h.name"/>" name="2"><s:property
						value="#h.name" /> </a>
			</li>
		</s:iterator>
	</ul>
</div>
