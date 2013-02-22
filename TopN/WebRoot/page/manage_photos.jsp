<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

 <%@ include file="/page/util/common.jsp"%>
<%@ page import="com.topn.bean.*" %>
<%@ page import="com.topn.bean.TO.*" %>

<script>
	 $(function () {
                        $('.manage_ul  a').lightBox();
                    });
</script>
<div>
<ul class="manage_ul">
<s:if test="null==#albumPhotos.photos||#albumPhotos.photos.size()==0">
<div class="manager_photo_null_div"><p>该相册下没有照片。</p></div>
</s:if>
<s:else>

<s:if test="#albumPhotos.isEntry==1">

<s:iterator value="#albumPhotos.photos" id="photo" >
	
		 <li id="<s:property value="#photo.photoId"/>">
		  	<p class="pic_show_p">
			   <a id="<s:property value="#photo.photoId"/>Url" href="<s:property value="#photo.url"/>">
					<img id="<s:property value="#photo.photoId"/>Img" src="<s:property value="#photo.smallUrl"/>" />
			   </a>
			</p> 
			<p>
			 <span ><input  type="checkbox" value="<s:property value="#photo.photoId"/>" class="del_chk"/></span>
			                       
			 <span class="pic_name_span"><s:property value="#photo.detail"/></span>
			</p>  		        		                       
	    </li>
</s:iterator>
	</s:if>
	<s:else>
	<s:iterator value="#albumPhotos.photos" id="photo" >
	<li>
	  <a href="<s:property value="#photo.url"/>">
	   <img src="<s:property value="#photo.smallUrl"/>" />
	    <p><s:property value="#photo.detail"/><p> 
	  </a>
	</li>
	</s:iterator>
	</s:else>
</s:else>
    
      	  </ul>
 </div>