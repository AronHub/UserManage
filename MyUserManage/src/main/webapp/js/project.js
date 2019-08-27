var base={
		domain:window.location.protocol + "//" + window.location.host
		+"/MyUserManage/"
}
var pageNow=1;
var pageCount=3;
$(document).ready(function(){
	$(document).ajaxStart(function(){
		//alert("正在加载中")
	});
	$(document).ajaxStop(function(){
		//alert("加载结束")
	})
	
	
	init(1,null,null);
	
	$(".reportDown").click(function(){
		
		 var projeName=$(".projName").val();
		 var projeNo=$(".projNo").val();
		 
		 projeName=encodeURI(encodeURI(projeName));
		 window.location.href=base.domain+"projeView/reportDown?projectName="+projeName +"&projectId="+projeNo;
	})
	
	$(".reportUp").click(function(){
		$("#mubu").css("display","inline-block");
		$(".reprotView").css("display","inline-block");
	})
	
	$(".serch").click(function(){
	   var projeName=$(".projName").val();
	   var projeNo=$(".projNo").val();
	   init(pageNow,projeName,projeNo);
	})
	
	
}) 

function init(pageNow,projeName,projeNo){
	$.ajax({
		async:true,
		url:base.domain+"projeView/getProjectInfo",
		data:{
			pageNow:pageNow,
			Rows:pageCount,
			projeName:projeName,
			projeNo:projeNo
		},
		success:function(data){
			var content=data.content;
			var total=data.rows;
			var size=content.length;
			var contView="";
			var pageView="";
//			$(".cont tr").remove();
			for(var i=0;i<size;i++){
				contView+="<tr>"+
						"<td><input type='checkbox' class='chek'></td>"+
						"<td>"+content[i].id+"</td>"+
						"<td>"+content[i].name+"</td>"+
						"<td>"+content[i].manager+"</td>"+
						"</tr>"	
				
			}
			$(".cont").append(contView);
			
			pageView+="<span>前一页</span>"
			
			for(var j=1;j<=total;j++){
				pageView+="<span>&lt;"+j+"&gt;</span>"
			}
				
			pageView+="<span>后一页</span>"+
				
					"<span class='toal'>共"+total+"页</span>"+
					"<input type='text' class='jumpCont'>"+
					"<input type='button' value='跳转' class='jump'>";
		$(".pageViw").append(pageView);
		
		},
		error:function(){
			alert("查询失败") 
		}
	
	})
}
