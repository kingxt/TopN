
jQuery.UtrialAvatarCutter = function(config){

	var h,w,x,y;

	var os,oh,ow;

	var api = null;

	var sel = this;

	var img_content_id = config.content;

	var img_id = "img_"+(Math.random()+"").substr(3,8);
	var purviews = new Array();

	var select_width = null;
	var select_height = null;

	var zoomWidth,zoomHeight;
	var left,top;
	var fixedWidth=config.Fixed.fixedWidth;
	var fixedHeight=config.Fixed.fixedHeight;
	var aspectRatio=1;

	if(config.purviews){
		for(i=0,c=config.purviews.length;i<c;++i){
			purviews[purviews.length] = config.purviews[i];
		}
	}
	
	check_thums_img = function(){
		if(config.purviews){
			for(i=0,c=config.purviews.length;i<c;++i){
				if($('#'+config.purviews[i].id+" img").length==0){
					$('#'+config.purviews[i].id).html("<img src='"+os+"'/>");
				}else{
					$('#'+config.purviews[i].id+" img").attr("src",os);
				}
			}
		}
	}

	/*
	 *	���¼���ͼƬ
	 */
	this.reload = function(img_url,width,height,aspect){alert(height);
			if(aspect!=null)
			{	
			aspectRatio=aspect;
			}			

			os = img_url+"?"+Math.random();
			$("#"+img_content_id).html("<img id='"+img_id+"' src='"+os+"'/>");
			if(img_url!=null && img_url != ""){
			if(width>300&&width>height)
			{
			  var scale=300/width;
			  scale=(Math.floor(scale*10))/10;
			  
			 $('#'+img_id).css("height", height*scale+"px").css("width", width*scale+"px");	
			
			}
			else if(width>300&&width<height||height>300)
			{
			var scale=300/height;
			  scale=(Math.floor(scale*10))/10;
			  	
				
			  $('#'+img_id).css("height", height*scale+"px").css("width", width*scale+"px");	
			
			}
			else
			{
	
			 $('#'+img_id).css("height", height+"px").css("width", width+"px");	
			
			}
            		
			
			$("#"+img_id).bind("load",
				function(){
					check_thums_img();
					sel.init();
				}
			);
		}
	}
	$("#"+img_content_id+" img").attr("id",img_id);

	var preview = function(c) {
		if ( c.w == 0 || c.h == 0 ) {
			//api.setSelect([ x, y, x+w, y+h ]);
			//api.animateTo([ x, y, x+w, y+h ]);
			return;
		}
		x = c.x;
		y = c.y;
		w = c.w;
		h = c.h;
		for(i=0,c=purviews.length;i<c;++i){
			var purview = purviews[i];
			var rx = purview.width / w;
			var ry = purview.height / h;
			$('#'+purview.id+" img").css({
				width: Math.round(rx * ow) +"px",
				height: Math.round(ry * oh) +"px",
				marginLeft: '-' + Math.round(rx * x) +"px",
				marginTop: '-' + Math.round(ry * y) +"px" 
			});
		}
		
	}

	this.init = function(){
		if(api!=null){
			api.destroy();
		}
		os = $("#"+img_content_id+" img").attr("src");
		if(os=="")
			return;
		check_thums_img();
		for(i=0,c=purviews.length;i<c;++i){
			var purview = purviews[i];
			var purview_content = $("#"+purview.id);
			purview_content.css({position: "relative", overflow:"hidden", height:purview.height+"px", width:purview.width+"px"});
		}

		oh = $('#'+img_id).height();
		ow = $('#'+img_id).width();
		
		select_width = config.selector.width;
		select_height = config.selector.height;	

		select_width = Math.min(ow,select_width);
		select_height = Math.min(oh,select_height);
		
		x = ((ow - select_width) / 2);
		y = ((oh - select_height) / 2);

		
			
			
		
		//����ԭJcrop����,�޸Ĵ˴����޸�Jcrop��������ֹ���
		api = $.Jcrop('#'+img_id,{ 
			aspectRatio: aspectRatio,
			onChange: preview,
			onSelect: preview,
			minSize:[fixedWidth,fixedHeight],
			setSelect: [ x, y, x+select_width, y+select_height ]	

		});
		//����ѡ���Ĭ��λ��
		//api.animateTo([ x, y, x+select_width, y+select_height ]);
		
	}

	this.submit = function(){
		
		var purview = purviews[0];

		var strWidth=$('#'+purview.id+" img").css("width");
		strWidth=strWidth.split("px");
		zoomWidth=strWidth[0];

		var strHeight=$('#'+purview.id+" img").css("height");
		strHeight=strHeight.split("px");
		zoomHeight=strHeight[0];	
		
		var strX=$('#'+purview.id+" img").css("marginLeft");
		strX=strX.split("px");
		left=strX[0].substring(1);
	
		var strY=$('#'+purview.id+" img").css("marginTop");
		strY=strY.split("px");
		top=strY[0].substring(1);
		
		
		return {x:left,y:top,s:os,zoomWidth:zoomWidth,zoomHeight:zoomHeight};
	}
}