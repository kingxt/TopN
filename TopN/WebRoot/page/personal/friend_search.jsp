<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<script type="text/javascript"
	src="../scripts/topn.jquery.pagination.js"></script>

<script type="text/javascript" src="../scripts/topn.search.js"></script>
<div class="mainContent_left_div">
	<div class="radius_large_top_div"></div>

	<div id="content_middle_div" class="content_middle_div">
		<div id="search_div" class="search_div">
			<div class="welcome_div">
				<h5>
					好友搜索
					</h5>
				
			</div>
			<ul class="search_ul">
				<li>
					<a>按姓名搜索朋友</a>
					<ul>
						<li>
							请输入朋友的的姓名,比：如张三
						</li>
						<li>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 姓名:
								<input type="text" name="nickname" id="nickname" class="search_input"/>
								<input type="submit" value="确定" onclick="searchFriend()" class="search_btn"/>
							</p>
						</li>
					</ul>
				</li>
				<li>
					<a>按兴趣搜索朋友</a>
					<ul>
						<li>
							请输入你的兴趣,比：唱歌
						</li>
						<li>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 兴趣:
								<input type="text" name="hobby" id="hobby" class="search_input"/>
								<input type="submit" value="确定" onclick="searchCountWithHobby()" class="search_btn"/>
							</p>
						</li>
					</ul>
				</li>
				<li>
					<a>按账号搜索朋友</a>
					<ul>
						<li>
							请输入好友的邮箱账号,比：helloWorld@163.com
						</li>
						<li>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 账号:
								<input type="text" name="email" id="email" class="search_input"/>
								<input type="submit" value="确定" onclick="searchCountWithEmail()" class="search_btn"/>
							</p>
						</li>
					</ul>
				</li>
				<li>
					<a>按学校搜索朋友</a>
					<ul>
						<li>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 姓名:
								<input type="text" id="schoolName" name="schoolName" class="search_input"/>
							</p>
						</li>
						<li>
							<p>
								&nbsp;学校名称:
								<input type="text" id="school" name="school" class="search_input"/>
							</p>
						</li>
						<li>
							<p class="school_search_button">
								<input type="submit" value="确定"
									onclick="searchCountWithSchool()" class="search_btn"/>
							</p>
						</li>
					</ul>
				</li>
				<li>
					<a>按TopN排行榜搜索</a>
					<ul>
						<li>
							如果要搜索10到20名，则填写 10 &nbsp;&nbsp;20
							<br />
							如果要搜索确定名次，例如10， 则填写10
						</li>
						<li>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 性别:
								<select name="sex" id="sex">
											<option value="1">
												男
											</option>
											<option value="2">												
												女
											</option>
								</select>
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 范围:
								<input type="text" id="topn" name="topn" class="search_input"/>
								<input type="submit" value="确定" onclick="searchForTopN()" class="search_btn"/>
							</p>
						</li>
					</ul>
				</li>
				<!-- 
				<li>
					<a>按地区搜索同伴</a>
					<ul>
						<li>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 账号:
								<input type="text" />
							</p>
						</li>
						<li>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 地区:
								<select>
									<option>
										上海
									</option>
								</select>
								<select>
									<option>
										闵行
									</option>
								</select>
							</p>
						</li>
						<li>
							<p class="school_search_button">
								<input type="submit" value="确定" />
							</p>
						</li>
					</ul>
				</li>
				 -->
			</ul>
		</div>
		<div id="Pagination" class="pagination_div">
		</div>
	</div>

	<div class="radius_large_bottom_div"></div>
</div>

<div class="mainContent_right_div">
	<jsp:include page="../page_right/right.jsp"></jsp:include>
</div>
