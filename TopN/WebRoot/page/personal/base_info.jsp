<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/util/common.jsp"%>
<script type="text/javascript" src="../scripts/topn.setday.js"></script>

<form id="updateForm" 
			method="post">
			<div>
	 			 <div class="basic_info_div">
                    <table class="basic_information_table">
                        <tbody>
                            <tr>
                                <td colspan="2"><h5>基本信息(<span style="color: red;">*</span>必填)</h5></td>
                            </tr>
                            <tr class="des_tr">
                                <td class="des_td"><span style="color: red;">*</span>姓名：</td>
                                <td><input name="pi.nickName" type="text" id="nickName"
											value="<s:property value="#pi.nickName"/>" /></td>
                            </tr>
                            <tr class="des_tr">
                                <td  class="des_td"><span style="color: red;">*</span>性别：</td>
                                <td><select name="pi.sex" id="sex">
											<option value="1"
												"<s:if test="#pi.sex==1">selected</s:if>">
												男
											</option>
											<option value="2"
												"<s:if test="#pi.sex==2">selected</s:if>">
												女
											</option>
										</select></td>
                            </tr>
                             <tr class="des_tr">
                                <td  class="des_td">职业：</td>
                                <td><input name="pi.job" type="text"
											value="<s:property value="#pi.job"/>" /></td>
                            </tr>
                             <tr>
                                <td class="intr_td">个人简介：</td>
                                <td><textarea name="pi.ps"  class="introduce_are" id="introduce_are"><s:property value="#pi.ps" /></textarea></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
         
				 <div  class="basic_info_div">
                		<table class="basic_info_table">
                			  <tbody>
                            <tr>
                                <td colspan="3"> <h5>隐私信息(<span style="color: red;">*</span>必填)</h5></td>
                            </tr>
                            <tr>
                                <td class="des_td"><span style="color: red;">*</span>生日：</td>
                                <td><input name="pi.birthday" type="text" id="birthday" value="<s:property value="#pi.birthday"/>"  readonly="readonly" onclick="showcalendar(event, this);"/></td>
                                <td>
                                	<select name="per.perBirthday">
										<option value="1"
											<s:if test="#per.perBirthday == 1">selected</s:if>>
											公开
										</option>
										<option value="2"
											<s:if test="#per.perBirthday == 2">selected</s:if>>
											好友可见
										</option>
										<option value="3"
											<s:if test="#per.perBirthday == 3">selected</s:if>>
											保密
										</option>
									</select>	
                               </td>
                             
                            </tr>
                             <tr>
                                <td  class="des_td">电话：</td>
                                <td><input name="pi.phone" type="text"
								value="<s:property value="#pi.phone"/>" /></td>
                                <td>
                                	<select name="per.perPhone">
										<option value="1" <s:if test="#per.perPhone == 1">selected</s:if>>
											公开
										</option>
										<option value="2" <s:if test="#per.perPhone == 2">selected</s:if>>
											好友可见
										</option>
										<option value="3" <s:if test="#per.perPhone == 3">selected</s:if>>
											保密
										</option>
									</select>
                               </td>
                            </tr>
                             <tr>
                                <td  class="des_td">所在地：</td>
                                 <td><input name="pi.address" type="text"
								value="<s:property value="#pi.address"/>" /></td>
                                <td>
                                	<select name="per.perAddress">
								<option value="1"
									<s:if test="#per.perAddress == 1">selected</s:if>>
									公开
								</option>
								<option value="2"
									<s:if test="#per.perAddress == 2">selected</s:if>>
									好友可见
								</option>
								<option value="3"
									<s:if test="#per.perAddress == 3">selected</s:if>>
									保密
								</option>
							</select>
                               </td>
                            </tr>
                             <tr>
                                <td  class="des_td">MSN：</td>
                                 <td><input name="pi.msn" type="text"
								value="<s:property value="#pi.msn"/>" /></td>
                                <td>
                                	<select name="per.perMsn">
								<option value="1" <s:if test="#per.perMsn == 1">selected</s:if>>
									公开
								</option>
								<option value="2" <s:if test="#per.perMsn == 2">selected</s:if>>
									好友可见
								</option>
								<option value="3" <s:if test="#per.perMsn == 3">selected</s:if>>
									保密
								</option>
							</select>
                               </td>
                            </tr>
							 <tr>
                                <td  class="des_td">QQ：</td>
                                 <td><input name="pi.qq" type="text"
								value="<s:property value="#pi.qq"/>" /></td>
                                <td>
                                	<select name="per.perQQ">
								<option value="1" <s:if test="#per.perQQ == 1">selected</s:if>>
									公开
								</option>
								<option value="2" <s:if test="#per.perQQ == 2">selected</s:if>>
									好友可见
								</option>
								<option value="3" <s:if test="#per.perQQ == 3">selected</s:if>>
									保密
								</option>
							</select>
                               </td>
                            </tr>

                        </tbody>                		
                		</table>
                     </div>
              </div>
		</form>