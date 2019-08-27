//设置页码变量
var pageNow=1;
var pageCount=3;
var junit=3;

$(document).ready(function(){
	
	inint(1);
	//报表下载
	$(".repotDown").click(function(){
		var rolename=$(".rolename").val(); 
		var levl=$(".levl").val();
		//这边需要使用两次encodeURI，但vue框架就只要使用一次encodeURI就可以
		rolename=encodeURI(encodeURI(rolename));
	    window.location.href=base.domain+"role/reportDownLoad?rolename="+rolename+"&levl="+levl
	})
	
	//报表上传
	$(".reportUp").click(function(){
		$("#repotUpView").css("display","inline-block");
		
	})
	//查询
	$(".serch").click(function(){
		var dis=$(".noFind").css("display");
		if(dis=="inline-block"){
			location.reload([true]);
		}else{
			var rolename=$(".rolename").val(); 
			var levl=$(".levl").val();
			//alert("role="+rolename+" lev="+levl);
			inint(pageNow,rolename,levl);
		}
		
		
	});
	//删除
	$(".delt").click(function(){
		var id="";
	    var check=$(".check:checked");
	
         if(check.length==0){
        	 alert("请选择一条。");
         }else{
        	 $(".deletView").css("display","inline-block");
    		 $(".mubu").css("display","inline-block");
    		if(check.length==1){
    			var id=check.val(); 
    		}else{
    			$(".check:checked").each(function(){
    			    id+=$(this).val();
    				id+=",";
    				//alert("id="+id);					 
    			})
    		}
    	}		
		
       //删除_取消
     	$(".del_canl").click(function(){
     		 $(".deletView").css("display","none");
     		 $(".mubu").css("display","none");
     	});
     	//删除_确定
     	$(".del_sure").click(function(){
     		
     		$.ajax({
     			url:base.domain+"role/deleteRole",
     			data:{
     				id:id
     			},
     			success:function(){
     				 alert("删除成功");
     				 $(".deletView").css("display","none");
     	    		 $(".mubu").css("display","none");
     	    		 inint(1); 
     	    		 
     			},
     			err:function(){
     				alert("删除失败！");
     			}
     		})
     	})
     	
		
	});
	
	//删除所有
	$(".deltAll").click(function(){
		$(".mubu").css("display","inline-block");
		$(".delallView").css("display","inline-block");
		
		//删除所有_确定
		$(".deall_sure").click(function(){
			$.ajax({
				url:base.domain+"role/delAll",
				type:"post",
				success:function(){
					alert("删除成功");
					$(".mubu").css("display","none");
					$(".delallView").css("display","none");
					inint(1,null, null); 
				},
				error:function(){
					alert("失败");
				}
			})
		})
		//删除所有_取消
		$(".deall_canl").click(function(){
			$(".mubu").css("display","none");
			$(".delallView").css("display","none");
		})
	})
	
	//修改(回显)
	$(".update").click(function(){
		var length=$(".check:checked").length;
		if(length!=1){
			alert("请选择一条记录")
		}else{
			var id=$(".check:checked").val();
			$.ajax({
				url:base.domain+"role/getRoleByID",
				data:{
					id:id
				},
				success:function(data){
					var cont=data.content;
					//alert("dat="+data.content);
					$("input[name='up_rolnas']").val(cont.name);
					$("input[name='up_levl']").val(cont.levl);
					$(".mubu").css("display","inline-block");
					$(".updaView").css("display","inline-block");
				},
				error:function(){
					alert("失败")
				}
			})
		}
		
		//修改-确定
		$(".up_sure").click(function(){
			var rolename=$("input[name='up_rolnas']").val();
			//var levl=$("input[name='up_levl']").val();
			var id=$(".check:checked").val();
			$.ajax({
				url:base.domain+"role/updatRole",
				data:{
					id:id,
					name:rolename,
					//levl:levl
				},
				success:function(){
					alert("修改成功")
					$(".mubu").css("display","none");
					$(".updaView").css("display","none");
					location.reload([true]);
				}
			    
			})
			
			
			
		})
		
		
		//修改-取消
		$(".up_canl").click(function(){
			$(".mubu").css("display","none");
			$(".updaView").css("display","none");
			location.reload([true]);
		})
		
		
	})
	
	//全选操作
	$(".chekAlls").click(function(){
		var check=$(".chekAlls")[0].checked;
		//alert("cjefb="+check)
		if(check){
			$(".check").each(function(){
				$(this)[0].checked=true;
			})
			
		}else{

			$(".check").each(function(){
				$(this)[0].checked=false;
			})
		}
		
	}		
	)
	
	
	//添加 
	$(".add").click(function(){ 
		clearAdd();
		$(".mubu").css("display","inline-block");
		$(".addView").css("display","inline-block");
	})
	
	//添加_取消
	$(".ad_del").click(function(){
		$(".mubu").css("display","none");
		$(".addView").css("display","none");
	})
	
	//添加_保存
	$(".ad_save").click(function(){
	  var name=	$("input[name='rolname']").val();
	  var levl=$("input[name='lvle']").val();
	  $.ajax({
		  type:"post",
		  url:base.domain+"role/addRole",
		  data:{
			  name:name,
			  levl:levl
		  },
		  success:function(){
			  alert("添加成功");
			  $(".mubu").css("display","none");
			  $(".addView").css("display","none");
			  inint(1);
		  },
		  err:function(){
			  alert("err");
		  }
	     
	  })
		
	})
	
})
//跳转框
function junmp(){
  var pageNow=	$(".jump").val();
  inint(pageNow);
}

//清除添加框
function clearAdd(){
	 $("input[name='rolname']").val("");
	 $("input[name='lvle']").val("");
}

//页码点击跳转
function chagePage(obj){
	  //alert("obj="+obj);
	   pageNow=obj;
	   inint(pageNow);
} 
//清空内容
function clerltext(){
	$(".cotent tr").remove();
	$(".pageView").remove();
	$(".rolename").val("");
	$(".levl").val("");
}
//前一页
function prepage(pagew){
	//alert("pagew="+pagew); 
	if(pagew<1){
		alert("不好意思，你越线了")
	}else{
		if(pagew==junit-3){
			//alert("====")
			junit=junit-3;
		}
		pageNow=pagew;
		inint(pagew);
	}
	
}
//后一页
function afterpage(pagew,pagecount){
	//alert("afterpa="+pagew);
	/*alert("pagecount="+pagecount);*/
	if(pagew>pagecount){
		alert("不好意思，你越线了");
	}else{
		if(pagew==junit+1){
			//alert("==");
			junit=junit+3;
		}
		pageNow=pagew;
		inint(pagew);	
	}

}


//初始化
function inint(pageNow,rolename,levl){
	clerltext();
	$("table").css("display","inline-block");
	$(".noFind").css("display","none");
	$.ajax({
		data:{
			levl:levl,
			rolename:rolename,
			pageNow:pageNow,
			Rows:pageCount
		}, 
		url:base.domain+"role/findRoleInfo",
		success:function(data){
			  var cont=data.content;
			  //获取总页数
              var pagecount=data.pagecount;
	          //alert("cont="+cont);
			  var conView="";
			  var pagView="<div class='pageView'>";
			  if(cont!=null&&cont.length!=0){
				  for(var i=0;i<cont.length;i++){
					  conView+="<tr><td><input type='checkbox' value='"+cont[i].id+"' class='check'/></td>"
							+"<td>"+cont[i].id+"</td>"
							+"<td>"+cont[i].name+"</td>"
							+"<td>"+cont[i].levl+"</td></tr>"
							
				  } 
				  $(".cotent").append(conView);
			  }else{
				  $("table").css("display","none");
				  $(".noFind").css("display","inline-block");
			  }
			 
			  /*页码视图*/
			  pagView+="<span class='prepage' onclick=prepage("+(pageNow-1)+")>前一页</span>";
			 
			  var j=junit-2;
			  for(j;j<=pagecount;j++){
				  if(j<=junit){
					  pagView+="<span class='pagema' onclick=chagePage("+j+")>"+"<"+j+">"+"</span>";
					   
				  }  
				 
			  }
			  
			  pagView+="<span class='aferpage' onclick=afterpage("+(pageNow+1)+","+pagecount+")>后一页</span>";
			  pagView+="<span class='jumpt'>共"+pagecount+"页</span>";
			  pagView+="<input type='text' class='jump'>";
			  pagView+="<input type='button' value='跳转' onclick=junmp()></div>";
			 
		      $(".pageViewn").prepend(pagView);
              
			  
		},
	    error:function(){
	    	alert("初始化失败");
	    }
	})
}