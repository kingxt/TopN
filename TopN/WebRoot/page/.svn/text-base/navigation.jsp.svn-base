<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>

    $(document).ready(function () {
    	
         $("#left_menu_ul  li a").click(function () {
            $(this).parents("li").first().find(".sub_menu_ul").toggle();
        });
    });
</script>	    
<ul class="left_menu_ul" id="left_menu_ul">
                    <li>
                        <a class="menu_common menu_li_1" href="loadMainMood.action" ui-async="async"></a>
                        <div>             
                               <ul class="sub_menu_ul">
                                    <li><a href="getMyMood.action" ui-async="async">我的状态</a></li>
                                    <li><a href="toLeaveMsgs.action" ui-async="async">留言板</a></li>
                               </ul>
                        </div> 
                    </li>
                    <li>
                    		<a   class="menu_common menu_li_2" href="loadUploadPhoto.action" ui-async="async"></a>
                             <div class="sub_menu_div">
                             </div> 
                  </li>
                  <li>
                  			<a class="menu_common menu_li_3" href="loadPersonalAlbum.action" ui-async="async"></a>
                             <div class="sub_menu_div">
                             </div>  
                  </li>
                  <li>
                    	<a class="menu_common menu_li_4" href="manageFriend.action" ui-async="async"></a>
                        <div>             
                               <ul class="sub_menu_ul">
                                    <li><a href="friendSearch.action" ui-async="async">搜索好友</a></li>
                                    <li><a href="friendRecommendation.action" ui-async="async">好友推荐</a></li>
                               </ul>
                        </div>
                  </li>
                   <li>
                   			<a   class="menu_common menu_li_5" href="showBaseInfo.action" ui-async="async"></a>
                             <div class="sub_menu_div">
                             </div>  
                  </li>
                  <li>
                       <a   class="menu_common menu_li_6" href="/TopN/totopn.action"></a>
                             <div class="sub_menu_div">
                       </div>  
                  </li>
                   
                </ul>