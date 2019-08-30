var pageRows=3;
var pageNow=1;
//页码控制的变量
var mind=3;


$(document).ready(function(){
	//初始化操作
	InitUser(pageNow);
	

	//报表导出
	$("#reportDown").click(function(){
		var usernames=$("input[name=username]").val();
		var telep=$("input[name=teleps]").val();
		//这边需要使用两次encodeURI，但vue框架就只要使用一次encodeURI就可以
		usernames=encodeURI(encodeURI(usernames));
		
		window.location.href=base.domain+"reportDownLoad?usernames="+usernames+"&telep="+telep;
	})
	
	//报表上传
	$("#reprortUp").click(function(){
		$(".reporUpView").css("display","inline-block")
	})
	
////////////////添加///////////////////////	
	//添加功能
	$(".addn").click(function(){
		//清空文本框数据
		clearAdd();
		$(".fubull").css("display","block");
		$(".addView").css("display","block");
	});
	//添加-保存
	$(".add_save").click(function(){
		//文本框内容只能用val()方法获取
		var username=$("input[name='ad_urname']").val();
	    var passw= $("input[name='ad_passw']").val(); 
		var telep=$("input[name='ad_telp']").val();
		var addr=$("input[name='ad_addr']").val();
		
		var addcotxt={
			name:username,
			pssword:passw,
			telep:telep,
			addr:addr
			
		}
		$.ajax({
		   data:addcotxt,
		   url:base.domain+"adduser",
		   success:function(data){
			   if(data=="success"){
				   alert("添加用户成功");
				   $(".fubull").css("display","none");
				   $(".addView").css("display","none");
				   InitUser(1); 
			   }else{
				   alert("添加失败!");
			   }
			  
		   },
		   err:function(){
			   alert("抱歉，添加失败");
		   }
		  
		})
	}); 
	//添加-取消
	$(".add_cancl").click(function(){
		$(".fubull").css("display","none");
		$(".addView").css("display","none");
		
	})
//////////////////删除///////////////////////
	//点击删除按钮
	$(".delet").click(function(){
	  //获取到已选框的数量
	  var length=$(".chekbox:checked").length;
	  if(length==0){
		  alert("请选一条记录");
	  }else{
		  //只选择了一条,或多条
		 $(".delView").css("display","inline-block");
		 $(".fubull").css("display","inline-block");
		  
	  }
		
	})
	//删除--确定
	$("input[name='de_sure']").click(function(){
		//获取到用户id 
		  var id='';
		  var lengt=$(".chekbox:checked").length;
		  if(lengt==1){
			  id+=$(".chekbox:checked").attr("value");
		  }else{
			  $(".chekbox:checked").each(function(){
				  //注意：这边一定要用$(this).
					 id+=$(this).attr("value")+',';
				})
			//去除最后一个字符
//			  id=id.substring(0, id.length-1);
//			  alert("id---"+id);
		  }
		 
		  
		  $.ajax({
			  type:"post",
			  url:base.domain+"delete",
			  data:{"id":id},
			  success:function(data){
				  if(data=="success"){
					 alert("删除成功");
					 $(".delView").css("display","none");
					 $(".fubull").css("display","none");
					 InitUser(1);
				  }
			
				 
			  },
			  err:function(){
				  alert("删除失败");
			  }
			  
		  })
		  
	});
	
	//删除——取消
	$("input[name='de_no']").click(function(){
		$(".delView").css("display","none");
		$(".fubull").css("display","none");
	    $(".chekbox:checked").each(function(){
	    	$(this)[0].checked=false;
	    });
	});
	
///////////////////////修改/////////////////////////////////////////
	$(".updat").click(function(){
		var length=$(".chekbox:checked").length;
		if(length==1){
			//获取id
			var upt_id=$(".chekbox:checked").attr("value")
			//alert("upt_id="+upt_id)
			$.ajax({
				url:base.domain+"updateReturn",
				data:{"id":upt_id},
				success:function(data){
					//让数据回显
					var cont=data.user;
					$("input[name='name']").val(cont.name);
					$("input[name='pssword']").val(cont.pssword);
					$("input[name='telep']").val(cont.telep);
					$("input[name='addr']").val(cont.addr);
					
				},
				err:function(){
					alert("获取失败")
				}
			});
			
			
			$(".fubull").css("display","inline-block");
			$(".updatView").css("display","inline-block");
		
		//修改-保存
			$(".upt_save").click(function(){
			var name=$("input[name='name']").val();
			var pssword=$("input[name='pssword']").val();
			var telep=$("input[name='telep']").val();
			var addr=$("input[name='addr']").val();
			var id=$(".chekbox:checked").attr("value");
			var date={
					id:id,
					name:name,
					pssword:pssword,
					telep:telep,
					addr:addr
			}
			
			$.ajax({
					type:"post",
					url:base.domain+"updat",
				    data:date,
				    success:function(data){
				    	//alert("更改成功");
				    	$(".fubull").css("display","none");
						$(".updatView").css("display","none");
						var uptcheck=$(".chekbox:checked");
						uptcheck[0].checked=false;
						//需要刷新，不然会出现问题。
						location.reload([true]);
				    	InitUser(1);
				    },
				    err:function(){
				    	alert("修改失败");
				    }
				    
				});
			
			
			});
			
			
		//修改-取消
			$(".upt_cancl").click(function(){
				$(".fubull").css("display","none");
				$(".updatView").css("display","none");
				location.reload([true]);
			    var uptcheck=$(".chekbox:checked");
			    uptcheck[0].checked=false;
			    
			  
			});	
		
		
		}else{
			alert("请选择一条记录")
		}
		

  
		
	})
	
	//全选实现
	$(".all_chek").click(function(){
		var all_chek=$(".all_chek:checked").length;
		if(all_chek==1){
			
			$(".chekbox").each(function(){
		    	$(this)[0].checked=true;
		    }); 
		}else{	
			$(".chekbox:checked").each(function(){
		    	$(this)[0].checked=false;
		    }); 
		}
	})
	
	///////////////////////导出资源//////////////////////////
		$(".downResource").click(function(){
			window.location.href=base.domain+"downResource";
		})
	
})


//清空添加界面的内容
function clearAdd(){
	$("input[name='ad_username']").val("");
	$("input[name='ad_passw']").val("");
	$("input[name='ad_telp']").val("");
	$("input[name='ad_addr']").val("");
}


//查询功能
function serch(){
	
	var disp= $(".cont span").css("display");
	//如果抱歉提示框是显示状态，则刷新当前页
	if(disp=="block"){
		location.reload([true]);
	}
	
	InitUser(1);
}

//清空内容
function cleantext(){
	  $(".contens tr").remove();
	  $(".previewn").remove();
}


//换页码事件
function exchagePage(obj){
	var pagesdfd=$(".tznr").val();
	pageNow=obj;
	cleantext();
    InitUser(pageNow);
    //更改页码的颜色
    $("#pagem"+obj).css("color","blue");
   
	  
} 

//回城实现
function remove(){
	//页码控制，大于第一页的才能操作。
	if(pageNow>1){
		
		if(pageNow==(mind-2)){
			mind-=3;
		}
		
		pageNow-=1;
		InitUser(pageNow);
	}else{
		alert("不好意思，你已超标！");
	}
	 //更改页码的颜色
    $("#pagem"+pageNow).css("color","blue");
}
//前进实现
function premove(){
	//获取总页码
	var max=parseInt($("#sum_Page").text());
	//页码控制，小于总页码的才能操作。
	if(pageNow<max){
		if(pageNow==mind){
			mind+=3; 	
		}
		pageNow+=1;
		InitUser(pageNow);
	}else{
		alert("不好意思，你已超标！");
	}
	 //更改页码的颜色
    $("#pagem"+pageNow).css("color","blue");
}
//跳转实现
function jump(){
	//输入的页码
	var pagenowx=parseInt($(".tznr").val());
	//parseInt方法把字符串转换成整数
	var max=parseInt($("#sum_Page").text());
	var value=pagenowx%3;
	//parseInt方法可以丢弃小数部分,保留整数部分
	var move=parseInt(pagenowx/3);
	if(pagenowx>pageNow){
		if(value==0){
			//整除情况
			mind+=(move-1)*3;
			pageNow=pagenowx;
		}else{
			//有余数情况
			if(value>=1&&move!=0){
				mind+=move*3;
			}
			pageNow=pagenowx;
		}
	}else{
		if(value==0){
			mind=move*3;
			pageNow=pagenowx;
		}else{
			//有余数情况
			move!=0?mind=move*3+1:mind=3;
			pageNow=pagenowx;
		}
	}
	
	
	if(pagenowx<1||pagenowx>max){
		alert("不好意思，超标了！");
	}
	
	InitUser(pagenowx);
	 //更改页码的颜色
    $("#pagem"+pagenowx).css("color","blue");
	
}
//获取user数据
function InitUser(pageNow){
	
	cleantext();
	var usernames=$("input[name=username]").val();
	var telep=$("input[name=teleps]").val();
	
	$.ajax({
		async:false,
		type:"post", 
	    //以下面的data方式传送参数，不会出现乱码问题。
	    data:{
	    	usernames:usernames,
	    	telep:telep,
	    	Rows:pageRows,
	    	pageNow:pageNow
	    },
	    //url:base.domain+"serchInfo?usernames="+usernames+"&telep="+telep,  //如果是这样传中文参数，则会出现中文乱码。
	    url:base.domain+"getUserInfo",
	    success:function(data){
	    	//内容
	    	var content=data.content;
	    	//总记录数
	    	var total=data.total;
	    	//总页数
	        var pageCount;
	    	var continfo='';
	    	var pageView='<div class="previewn">';
	    	//alert("输出第二个对象的name:"+content[1].name);
	    	if(total%pageRows==0)
    		{ 
	    		pageCount=total/pageRows;
	    		var pageCou=parseInt(pageCount);
    		}else{
    			pageCount=total/pageRows+1;
    			//parseInt方法 ：去小数，取整数
    			var pageCou= parseInt(pageCount);
    			
    		}
	    	
            if(content!=null&&content.length!=0)
	    	{
            	$(".cont span").css("display","none");
            	$("#body").show();
	    		for(var i=0;i<content.length;i++){
	    		   continfo+='<tr><td><input type="checkbox" class="chekbox" value='+content[i].id+'></td>'+
		    			   '<td>'+content[i].id+'</td>'+
		    			   '<td>'+content[i].name+'</td>'+
		    			   '<td>'+content[i].telep+'</td>'+
		    			   '<td>'+content[i].addr+'</td>'+
		    			   '</tr>'
	    		}
	    		
	    		$(".contens").append(continfo); 

	    		//页码控制
	    		var j=mind-2;
	    		pageView+='<span onclick=remove()><回城></span>'
	    		for(j;j<=pageCount;j++)
    			{   
	    			if(j<=mind){
	    				pageView+='<span id="pagem'+j+'" value="10" onclick=exchagePage('+j+')>'+'<'+j+'>'+'</span>'
	    			}
	    			
    			}  
	    		pageView+='<span onclick=premove()><前进></span>'
	    		//显示总页码		
	    		pageView+='<span id="sum_Page">'+pageCou+'</span>页' 
	    		//显示跳转框
	    		pageView+='<input type="text" class="tznr"><input  type="button" onclick=jump()  value="跳转"></div>'
	            $(".page").prepend(pageView);   
	
	    	}else{
	    		$("#body").hide();
	    	    $(".cont span").css("display","block")
	    	
	    	}	 
	    	 
	    },
	    err:function(){ 
	    	alert("err!");
	    }
		
	});
	

}



