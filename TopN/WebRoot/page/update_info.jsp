<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/page/util/common.jsp"%>



<script>
              $(document).ready(function () {	
              	 $(".info_select_div > ul >li").each(function (index) {
                      $(this).click(function () {
                          $("div.show_div").removeClass("show_div");
                          $("div.update_info_div").eq(index).addClass("show_div");
                      });
                  });
            });
          </script>          
<div id="update_div" class="update_div">
	 <div class="info_select_div">
                   <ul class="info_select_ul">
                     <li>
                              <a class="menu_a" onclick="baseInfoInput()">基本信息</a>
                     </li>
                       <li>
                            <a class="menu_a" onclick="loadHobbyType()">兴趣信息</a>
                     </li>
                      <li>
                            <a class="menu_a" onclick="loadEducation()">教育信息</a>
                     </li>
                     <li>
                            <a class="menu_a" onclick="loadUploadHeadPortrait()">个人头像</a>
                     </li>
                     <li>
                            <a class="menu_a" onclick="modifyPassword()">修改密码</a>
                     </li>
                   </ul>
              </div>

	<div id="info_div" class="show_div update_info_div">
		<jsp:include page="personal/base_info.jsp"></jsp:include>
	</div>

	<div class="update_info_div" id="hobby">
		<!-- 异步加载  兴趣类型 -->
	</div>
	<div class="update_info_div" id="education_div">
		<!-- 异步加载教育类型 -->
	</div>
	<div class="update_info_div">
		<div class="upload_headPortrait_div" id="upload_headPortrait_div">
			<!-- 异步加载上传头像 -->
		</div>
	</div>
	<div class="update_info_div" id="modify_pwd_div">
               
    </div>
	
	<div>
		<ul class="submit_div">
			<li>
				<input id="update" type="button" class="save_page_button"
					onclick="updatePersonalInfo()" />

			</li>
		</ul>
	</div>
	
	
</div>
<br />



