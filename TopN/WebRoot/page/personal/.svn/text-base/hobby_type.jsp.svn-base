<%@ include file="/page/util/common.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
	function hobby_submit(){
		updateHobby();
	}
	
	$(document).ready(function(){
		initHobby();
	});
//-->
</script>
<div>
	 <h5 class="hobby_h5">兴趣类别</h5>
	<ul class="interest_type_ul" id="interest_type_ul">
		<s:iterator value="#hts" var="ht">
			<li>
				<a onclick="loadHobby(this);" id="<s:property value="#ht.typeId"/>"><s:property
						value="#ht.name" /> </a>
			</li>
		</s:iterator>
	</ul>
	<div class="interest_seperate_div" ></div>    
	<div id="interest_detial_div" class="interest_detial_div">
		 <h5 class="hobby_h5">详细内容</h5>
		<ul class="interest_detial_ul" id="<s:property value="typeId"/>">
			<s:iterator value="#hb" var="h" status="condi">
				<li>
					<input type="checkbox" onchange="changeHobby(this)"
						name="<s:property value="#h.name"/>" />
					<s:property value="#h.name" />
				</li>
			</s:iterator>
		</ul>

	</div>
</div>
<div class="interest_seperate_div" ></div>  

<div ><input id="orinalHobby" type="hidden" value="<s:property value="#pi.hobby"/>">
                   	<table class="other_interest_table">
                   		<tr>
                   			<td> <h5 class="hobby_h5">其他兴趣:</h5></td>
                   			<td><textarea id="otherHobby" name="otherHobby"><s:property value="#pi.otherHobby"/></textarea></td>
                   		</tr>                   		
                   	</table>
</div>
