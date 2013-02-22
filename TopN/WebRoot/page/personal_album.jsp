<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<!--个人相册模块-->
<script>
            var hide_parent;
            var albums=new Array();
            var i=0;
             $(document).ready(function () {
             	if($.browser.version=="7.0"){
                    $("img").css("margin-top","4px");
                }
                $(".person_album_div > ul > li> .operation_p").each(function () {
                     $(this).find(".update_a").click(function () {
                     //获取这个结点 中隐藏域相册的ID并且将相册信息输入到修改框中
                     //alert($(this).parents(".operation_p").first().find("#hdPicID").val());
                         hide_parent = $(this).parents("li").first();
                         var albumName=$.trim(hide_parent.find(".name_p").first().html())       
                         $("#txtName").val(albumName);
                         $("#txtDes").val(hide_parent.find(".operation_p").find("#hdPicdetail").val());
                    //  $("#albumId").val($(this).parents(".operation_p").first().find("#hdPicID").val());
                         showBox("#update_album_div", "相册信息修改");
                     });
                     $(this).find(".delete_a").click(function () {
                         //获取这个结点 中相册的ID并且将相册名称息输入到确定框中
						hide_parent = $(this).parents("li").first();
                         showAskBox("删除相册", "确定删除"+hide_parent.find(".name_p").first().html()+"相册?",sureDelete);
                     });
                 });
                 
                 
             });
             
             function sureDelete(){
                var id= hide_parent.find("#hdPicID").val();
              	$.ajax({
	            	'url':'deleteAlbum.action',      	
	            	'type':'get',
	            	'data':{'albumId':id},
            	'success': function(data){	
	            	var json = eval('(' + data.result + ')');		 
					if(json.result==true)
					{
						$("#"+id).remove(); 
					}		
    		  	}});			
             }
             function updateAlbum()
			{	
			 	var id= hide_parent.find("#hdPicID").val();
			 	var alName = jQuery.trim($("#txtName").val());
			 	var alDetail = jQuery.trim($("#txtDes").val());
				$.ajax({
	            	'url':'updateAlbum.action',      	
	            	'type':'post',
	            	'data':{'albumId':id,'albumName':alName,'detail':alDetail},
	            	'dataType':'json',
	            	'success': function(data){
		            	var json = eval('(' + data.result + ')');			            	
						if(json.result==true)
						{
							 closeBox();							 
			                 var name_album = $(".name_album");
			                 var des_album = $(".des_album");
			                 hide_parent.find(".name_p").html(alName);
			                 hide_parent.find("#hdPicdetail").val(alDetail);
			                
			                 for(var i=0;i<albums.length;i++)
							{
								if(albums[i][0]==id)
								{
									albums[i][1]=alName;
									albums[i][2]=alDetail;					
								}
							}
							 name_album.val("");
			                 des_album.val("");
			                 }
	    		  }});
			}
			
			 function formSubmit(id,albumName,detail,url,total){   
			
			 document.form.albumId.value=id;  
			 document.form.albumName.value=albumName;
			 document.form.detail.value=detail;  
			 document.form.url.value=url;  
			 document.form.total.value=total;  
			        
             document.form.submit();   
         }   
         
       		 function loadPicManage(albumId,isGuest,personalInfoId)
       		 {
       		 	   for(var i=0;i<albums.length;i++)
					{
						if(albums[i][0]==albumId)
						{
							if(isGuest==false)
					        {
						$.post("../page/pic_manage.jsp", {
							'albumId' : albums[i][0],
							'albumName' : albums[i][1],
							'detail' : albums[i][2],
							'url' : albums[i][3],
							'total' : albums[i][4],					
							'createTime':albums[i][5],
							'isEntry' : albums[i][6],
							'personalInfoId':personalInfoId
						}, function(msg) {
							$("#mainContent").empty().append(msg);
						});
						}else
						{
							$.post("../page/pictures.jsp", {
							'albumId' : albums[i][0],
							'albumName' : albums[i][1],
							'detail' : albums[i][2],
							'url' : albums[i][3],
							'total' : albums[i][4],					
							'createTime':albums[i][5],
							'isEntry' : albums[i][6],
							'personalInfoId':personalInfoId
						}, function(msg) {
							$("#mainContent").empty().append(msg);
						});
						}	
						break;			
						}
					}	
       		 }
      </script>

<s:if test="#isGuest==false">
	<script>
       	 $(function () {
       		  $(".person_album_div > ul > li").each(function () {
                     $(this).mouseover(function () { $(this).find(".operation_p").show(); });
                     $(this).mouseleave(function () { $(this).find(".operation_p").hide(); });
                 });
              });
       	</script>
</s:if>
<form name="form" action="../page/album.jsp" method="post"
	target="_blank">
	<input name="albumId" type="hidden" value="" />
	<input name="albumName" type="hidden" value="" />
	<input name="detail" type="hidden" value="" />
	<input name="url" type="hidden" value="" />
	<input name="total" type="hidden" value="" />
</form>

<div class="mainContent_left_div">
	<div class="radius_large_top_div"></div>
	<div id="content_middle_div" class="content_middle_div">

		<div id="album_div" class="album_div">
			<h5 class="title_h5">
				个人相册
			</h5>
			<div id="person_album_div" class="person_album_div">
				<ul>
					<s:iterator value="albums" id="album" status="sta">
						<s:if test="#album.isEntry==1">

							<input id="<s:property value="#album.albumId"/>Name"
								type="hidden" value="<s:property value="#album.name"/>" />
							<li id="<s:property value="#album.albumId" />">
								<p class="img_p">
									<a href="#"
										onclick="loadPicManage(<s:property value="#album.albumId"/>,<s:property value="#isGuest" />,<s:property value="#parameters.personalInfoId"/>)">

										<img src="../<s:property  value="#album.cover"/>" /> </a>
								</p>

								<p>
									<a class="name_p" href="#"
										onclick="loadPicManage(<s:property value="#album.albumId"/>,<s:property value="#isGuest" />,<s:property value="#parameters.personalInfoId"/>)">
										<s:property value="#album.name" /> </a>
								</p>


								<p class="count_p">
									张数：
									<s:property value="#album.total" />
								</p>
								<p class="operation_p">
									<a class="update_a">修改</a>
									<a class="delete_a">删除</a>
									<input id="hdPicID" type="hidden"
										value=<s:property value="#album.albumId" />>
									<input id="hdPicdetail" type="hidden"
										value=<s:property value="#album.detail" />>
								</p>
							</li>
							<script type="text/javascript" charset="UTF-8">
										albums[i]=new Array(); 
										albums[i][0]=<s:property value="#album.albumId"/>;
										albums[i][1]=$("#"+"<s:property value="#album.albumId"/>Name").val();
										albums[i][2]=$("#hdPicdetail").val();
										albums[i][3]='<s:property  value="#album.cover"/>';
										albums[i][4]=<s:property  value="#album.total"/>;
										albums[i][5]='<s:date name="#album.createTime" format="yyyy-MM-dd"/>';
										albums[i][6]=<s:property  value="#album.isEntry"/>;
										i++;
								</script>
						</s:if>
						<s:else>
							<input type="hidden" id="competitionName"
								value='<s:property value="#album.name" />' />
							<input type="hidden" id="competitionId"
								value='<s:property value="#album.albumId" />' />

							<script type="text/javascript">
							var name=$("#competitionName").val();					
							$("#competitionImg").attr("src","../"+'<s:property  value="#album.cover"/>');						
							$("#pCompetitionName").text(name);
							$("#pCompetitionTotal").text("张数："+'<s:property  value="#album.total"/>');					
							
							albums[i]=new Array(); 
							albums[i][0]=<s:property value="#album.albumId"/>;
							albums[i][1]=name;
							albums[i][2]="";
							albums[i][3]='<s:property  value="#album.cover"/>';
							albums[i][4]=<s:property  value="#album.total"/>;
							albums[i][5]='<s:date name="#album.createTime" format="yyyy-MM-dd"/>';
							albums[i][6]=<s:property  value="#album.isEntry"/>;
							i++;
						</script>
						</s:else>
					</s:iterator>

				</ul>

			</div>
		</div>


		<div id="competition_album_div" class="competition_album_div">
			<h5 class="title_h5">
				参赛相册
			</h5>
			<ul>
				<li>
					<p class="img_p">
						<a href="#"
							onclick="loadPicManage($('#competitionId').val(),<s:property value="#isGuest" />,<s:property value="#parameters.personalInfoId"/>)">
							<img id="competitionImg" src="../pictures/cover.jpg" />
						 </a>
					</p>
					<p>
						<a id="pCompetitionName" class="name_p" href="#"
							onclick="loadPicManage($('#competitionId').val(),<s:property value="#isGuest" />,<s:property value="#parameters.personalInfoId"/>)">
						</a>
					</p>

					<p id="pCompetitionTotal" class="count_p"></p>
					<p>
						<input id="hiddenCompetitionId" type="hidden" value="相册ID" />
				</li>
			</ul>
		</div>
		<div id="update_album_div" class="update_album_div">
						 	<table>
								<tr class="des_tr">
									<td  class="des_td">相册名称：</td>
									<td><input id="txtName" type="text" class="name_album" /></td>
								</tr>
								<tr>
									<td  class="intr_td">描述：</td>
									<td><textarea id="txtDes" cols="20" rows="2" class="des_album"></textarea></td>
								</tr>
								<tr>
									<td colspan="2" class="opearation_td">
										<span><input id="Button3" type="button"
							onclick="updateAlbum();" class="submit_button" /></span>
										<span><input id="Button4" type="button"
							class="cancel_button" onclick="$.zxxbox.hide();" /></span>
									</td>
								</tr>
							</table>
		</div>

	</div>

	<!--双层模式-->

	<div class="radius_large_bottom_div"></div>
</div>
<div class="mainContent_right_div">
	<s:if test="#isGuest">
		<jsp:include page="page_right/visitor_right.jsp"></jsp:include>
	</s:if>
	<s:else>
		<jsp:include page="page_right/right.jsp"></jsp:include>
	</s:else>
</div>