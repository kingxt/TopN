<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>

		<link rel="stylesheet" type="text/css"
			href="../styles/uploadify/uploadify.css" />
		<script type="text/javascript" src="../scripts/uploadify/topn.uploadify.v2.1.4.js"></script>
		<script type="text/javascript" src="../scripts/uploadify/topn.swfobject.js"></script>
		<script type="text/javascript" src="../scripts/topn.personal_page_zs.js"></script>
	
			<script type="text/javascript">
		$(document).ready(function() {
			$("#uploadify").uploadify( {
				'uploader' : '../scripts/uploadify/uploadify.swf',
				'script' : 'uploadPhoto.action',
				'cancelImg' : '../pictures/uploadify/cancel.png',
				'buttonImg':'../pictures/uploadify/uploadify.swf',
				'type' : 'get',
				//'scriptData':{'albumId':$('#albumId').val(),'personalInfoId':$.query.get('personalInfoId')},
				'multi' : true,
				'auto' : false,
				 'sizeLimit': 2621440,
				'fileDataName' : 'files',
				'fileExt' : '*.jpg;*.gif;*.png',
				'fileDesc' : '请选择图片文件*.jpg,*.gif,*.png类型',
				'queueID' : 'custom-queue',
				'fileExt' : '*.jpg;*.gif;*.png',
				'removeCompleted' : true
			});
			
				
		});
	
		function popCreateAlbum() {
			$("#Text1").val("");
			$("#TextArea2").val("");	
			showBox("#new_album_div", "新建相册", true);
			
		}
		function hiddenMe() {
			$("#albumname").val("");
			$("#albumDesc").val("");
			closeBox();
		}
	
		function upload() {
			// alert();
			$("#uploadify").uploadifySettings('scriptData', {
				'albumId' : $('#albumId').val(),
				'personalInfoId' : <s:property value="#parameters.personalInfoId"/>
			});
			javascript: $('#uploadify').uploadifyUpload();
		}
		
		function openwin() { 
			opencenterwindow('../page/competition_pictuer.jsp', '513', '415');
		}
		
		function createAlbum() {
			if(jQuery.trim($("#albumname").val())=='')
			{
			  	 showMessage("提示", "相册名不能为空");
		         return ;
			}
			$.ajax( {
				'url' : 'createAlbum.action',
				'dataType' : 'json',
				'type' : 'post',
				'data' : {
					'albumName' : jQuery.trim($("#albumname").val()),
					'detail' : jQuery.trim($("#albumDesc").val())
				},
				'success' : function(data) {
					var json = eval('(' + data.newAlbum + ')');
					if(json)
					{
						$("#albumId").append(
								"<option value='" + json.albumId + "' selected='selected'>"
										+ json.name + "</option>");
						hiddenMe();
					}
				}
			});
		}
		</script>
		<div class="mainContent_left_div">
			<div class="radius_large_top_div"></div>

			<div id="content_middle_div" class="content_middle_div">
				<div class="upload_pic_div">
					<div class="album_select_div">
						<li>
							选择相册：
						</li>
						<li>
							<select id="albumId" name="albumId" class="album_list_select">
								<s:iterator value="#albumsNameList.albums" var="album">
									<option value=<s:property value="#album.albumId"/>>
										<s:property value="#album.name" />
									</option>

								</s:iterator>
							</select>
						</li>
						<li>
							<a onclick="popCreateAlbum()">新建相册</a>
						</li>
						<li>
							<a onclick="openwin()">上传参赛照</a>
						</li>
					</div>
					<div class="album_upload_div">
						<div id="upload">

							<input id="uploadify" type="file" name="files" />

							<div id="custom-queue"></div>
							<div class="album_operation_div">
								<input id="Button1" onclick="upload()" type="button"
									class="upload_button" />
								
							</div>
						</div>
					</div>
					
					
					<div id="new_album_div" class="update_album_div">
						 	<table>
								<tr class="des_tr">
									<td  class="des_td">相册名称：</td>
									<td><input id="albumname" type="text" class="name_album" /></td>
								</tr>
								<tr>
									<td  class="intr_td">描述：</td>
									<td><textarea id="albumDesc" cols="20" rows="2" class="des_album"></textarea></td>
								</tr>
								<tr>
									<td colspan="2" class="opearation_td">
										<span><input id="Button3" type="button"
										class="submit_button" onclick="createAlbum();" /></span>
										<span><input id="Button4" type="button"
										class="cancel_button" onclick="hiddenMe()" /></span>
									</td>
								</tr>
							</table>
					</div>
					
					
				</div>

			</div>
			<div class="radius_large_bottom_div"></div>
		</div>
		<!--中间内容显示结束-->
		<div class="mainContent_right_div">
			<jsp:include page="page_right/right.jsp"></jsp:include>
		</div>
