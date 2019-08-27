var base={
		domain:window.location.protocol + "//" + window.location.host
		+"/MyUserManage/"
}
var PageNow=1;
var pageCount=3;
var unit=3;
$(document).ready(function(){
     //开始加载触发
	$(document).ajaxStart(function(){
		
		$(".loading").css("display","inline-block");
		
	})
	 //加载结束触发
	$(document).ajaxStop(function(){
		$(".loading").css("display","none");
	})
	
	init(1);

	//报表上传
	$("#exeup").click(function(){
		$(".upexport").css("display","inline-block");
		
	})
	//报表下载
	$("#exedown").click(function(){
		window.location.href=base.domain+"emply/exportDown";
	})
	
	//查询
	$(".serch").click(function(){
		var empName=$(".empName").val();
		var email=$(".email").val();
		init(PageNow,empName,email); 
		$(".empName").val("");
		$(".email").val("");
	
		 
	});
	//更新
	$("#update").click(function(){
		var id=$(".check:checked").attr("name");
		var length=$(".check:checked").length;
		
		if(length!=1){
			alert("抱歉，请选择一个")
		}else{
			$(".updatView").css("display","inline");
			$(".mubu").css("display","inline");
			$.ajax({
				async:false,
				url:base.domain+"emply/serchByid",
				data:{
					id:id
				},
				success:function(data){
					//var content=JSON.parse(data);
					var content=data.content;
					var roid=data.roleid;
					//alert("content="+data)
					$("input[name='empnam']").val(content.name);
					$("input[name='empemails']").val(content.email);
					$("input[name='roleIdn']").val(roid);
					
				},
				error:function(){
					alert("回归数据失败");
				}
			})
		}
		var sour_roid=$("input[name='roleIdn']").val();
		
		//修改-取消
		$(".up_canl").click(function(){ 
			$(".updatView").css("display","none");
			$(".mubu").css("display","none");
			window.location.reload([true]);
		})
		
		//修改-确定
		$(".up_sure").click(function(){
			var ind=$(".check:checked").attr("name");
			var epname=$("input[name='empnam']").val();
			var email=$("input[name='empemails']").val();
			var roid=$("input[name='roleIdn']").val();
			$.ajax({
				url:base.domain+"emply/updat",
				async:true,
				data:{
					ind:ind,
					epname:epname,
					email:email,
					roleId:roid,
					sour_roid:sour_roid
				},
				success:function(data){
					alert("修改成功");
					window.location.reload([true]);
				},
				error:function(){
					alert("修改失败");
				}
				
			})
				
			
		})
		
	})
	
	
	//删除所有
	$("#deletAll").click(function(){
		$(".deleAllView").css("display","inline-block");
		$(".mubu").css("display","inline-block");
		//取消
		$(".delAll_canl").click(function(){
			$(".deleAllView").css("display","none");
			$(".mubu").css("display","none");
			 
		})
		//确定
		$(".delAll_sure").click(function(){
			$.ajax({
				url:base.domain+"emply/deltAll",
				async:true,
				success:function(data){
					alert("删除所有成功");
				},
				error:function(){
					alert("失败")
				}
			})
		})
	
		
	})
	
	//删除
	$("#delete").click(function(){
		 var leng=$(".check:checked").length;
		 var id="";
		 if(leng==0){
			 alert("请选择一条进行删除");
		 }else{ 
			 $(".mubu").css("display","inline-block");
			 $(".deltView").css("display","inline-block");
			//删除-确定
				$(".del_sure").click(function(){
					if(leng==1){
						var idn=$(".check:checked").attr("name")
						id=idn;
						//alert("id="+id)
					}else{
						$(".check:checked").each(function(){
							var ids=$(this).attr("name");
							id+=ids+",";
							
						})
						//alert("id2="+id)
					}
				   $.ajax({
					   type:'get',
					   async:true,
					   url:base.domain+"emply/delet",
					   data:{
						   id:id
					   },
					   success:function(data){
						   alert("删除成功")
						   $(".mubu").css("display","none");
						   $(".deltView").css("display","none");
						   init(1);
						   location.reload([true]);
					   },
					   error:function(){
						   alert("删除失败") 
					   }
				   })
					
				})
				//删除-取消
				$(".del_canl").click(function(){
					$(".mubu").css("display","none");
					$(".deltView").css("display","none");
					location.reload([true]);
				})
		 }
		
		
		
	})
     
	
	//添加
	$("#add").click(function(){
		$("input[name='empnams']").val("");
		$("input[name='empemail']").val("");
		
		$(".mubu").css("display","inline-block");
		$(".addView").css("display","inline-block");
	
		//添加-确定
		$(".add_sure").click(function(){
			var name=$("input[name='empnams']").val();
			var email=$("input[name='empemail']").val();
			var roleId=$("input[name='roleId']").val();
			var dats={
					name:name,
		    		email:email,
		    		roleId:roleId
			}
			var dat=JSON.stringify(dats);
		    $.ajax({
		    	async:true,
		    	url:base.domain+"emply/addEmp",
		    	data:{
		    		dat:dat
		    	},
		    	success:function(data){
		    		alert("添加成功");
		    		$(".mubu").css("display","none");
					$(".addView").css("display","none");
		    		init(1); 
		    	},
		    
		    })
			
			
		})
		//添加-取消
		$(".add_canl").click(function(){
			$(".mubu").css("display","none");
			$(".addView").css("display","none");
			
		})
	})
})


function clean(){
	$(".cont tr").remove();
	$(".pageCont").remove();
}
//跳转框
function jumk(){
	var page=$(".jumtext").val();
	init(page);
}
//前一页
function prepage(pageNon){
	if(pageNon<=0){
		alert("抱歉，你越界了");
	}else{
		if(pageNon==(unit-3)){ 
			unit-=3;
		}
		init(pageNon)
	}
	
}
//下一页
function afterPage(pageNon,tatal){
	
	if(pageNon>tatal){
		alert("抱歉，你越界了")
	}else{
		if(pageNon==(unit+1)){
			unit+=3;
		}
		init(pageNon)
	}
	
}
function pageChange(pageNon){
	init(pageNon)
}

function init(PageNow,empName,email){
	clean(); 
	$.ajax({
		url:base.domain+"emply/serch",
		data:{
			"empName":empName,
			"email":email,
			"PageNow":PageNow,
			"pageCount":pageCount
		},
		success:function(data){
			//获取内容
			var cont=data.content;
			var tatal=data.total;
			
			var contnt='';
			var pageCont='<div class="pageCont">';
			//alert("cont="+cont)
			if(cont!=""){
				$("table").css("display","inline-block")
				$(".tis").css("display","none")
				//内容视图
				var length=cont.length;
				for(var i=0;i<length;i++){
					contnt+='<tr>'+
					'<td><input type="checkbox" class="check" name='+cont[i].id+'></td>'+
					'<td>'+cont[i].id+'</td>'+
					'<td>'+cont[i].name+'</td>'+
					'<td>'+cont[i].email+'</td>'+
					'<td></td>'+
					'<td></td>'+
					'</tr>'
					
				}
				$(".cont").append(contnt);
				
			}else{
				$("table").css("display","none")
				$(".tis").css("display","inline-block")
			}
			
			//页码视图
			pageCont+='<span onclick=prepage('+(PageNow-1)+')>前一页</span>'
			var j=unit-2;
			for(j;j<=unit;j++){
				
				if(j<=tatal){
					pageCont+='<span onclick=pageChange('+j+')><'+j+'></span>';
				}
				
			}
			pageCont+='<span onclick=afterPage('+(PageNow+1)+','+tatal+')>后一页</span>'+
                      '<span class="toal">共'+tatal+'页</span>'+
					  '<input type="text" class="jumtext">'+
					  '<input type="button" value="跳转" onclick=jumk() class="jump"><div>'
				
			$("#pageView").append(pageCont);
			
		},
		error:function(){
			alert("查询失败");
		}
	})
}