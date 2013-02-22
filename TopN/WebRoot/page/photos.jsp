<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

 <%@ include file="/page/util/common.jsp"%>
<%@ page import="com.topn.bean.*" %>
<%@ page import="com.topn.bean.TO.*" %>


<div id="picShow_div" class="picShow_div" >
      <% 
      	AlbumPhotosTO apto=(AlbumPhotosTO)request.getAttribute("albumPhotos");
      if(apto.getPhotos()==null)
      {
      %>
      	<div>
      		<p>该相册下没有照片。</p>
      	</div>      	
      	<% 
      }
      	else
      	{
      		int isEntry=apto.getIsEntry();
      		int total=apto.getPhotos().size();
		      		for(int i=0;i<total;i++)
		      		{
			      			if(i==0)
		      			{
		      			%>
		      				 <ul class="picShow_ul">
		      			<% 
		      			}
			      			
			      			if(isEntry==1)
			      			{
			      			Photo photo=(Photo)apto.getPhotos().get(i);
			      				%>
			      				 <li>
			      				    <a href="<%=photo.getUrl()%> ">
			                            <img src="<%=photo.getUrl() %>" />
			                       </a>
			                      
			                        <%
			                        if(photo.getDetail().equals("null"))
			                        {		      
			                        	%>
			                        	 <p></p>  
			                        	  <%
			                        }else{

			                        %>
			                          <p><%=photo.getDetail() %><p> 
			                            <%
			                     	        }                
			                          %>
			                       
			      				   </li>
			      				<% 
			      			}
			      			else
			      			{
			      			PentryPhoto pp=(PentryPhoto)apto.getPhotos().get(i);
			      				%>
			      				 <li>
			      				    <a href="<%=pp.getUrl()%> ">
			                            <img src="<%=pp.getUrl() %>" />
			                       </a>
			                        <%
			                        if(pp.getDetail().equals("null"))
			                        {
			                        	%>
			                        	 <p></p>  
			                        	  <%
			                        }else{
			                        %>
			                          <p><%=pp.getDetail() %><p> 
			                            <%
			                     	        }                
			                          %>
			      				   </li>
			      				<% 
			      			}      			
			      			if((i+1)%5==0&&i!=total)
	      						{
	      						%>
	      						   </ul>
	      						    <ul class="picShow_ul">
	      					<% 
	      					}
	      					 else if(i==total)
	      					{
	      					%>
	      					 </ul>
	      					 <% 
	      					}
			      			
      		
      			}
      		
      	}
      	%>          
    </div>  
    <script>
    	 $(function () {
            $('.picShow_ul a').lightBox();
        });
    </script>