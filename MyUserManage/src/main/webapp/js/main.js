var base = {
	domain : window.location.protocol + "//" + window.location.host
			+"/MyUserManage/"
	
};
$(document).ready(function(){
	//访问用户管理界面 
	$(".usermanage").click(function(){
		window.location.href=base.domain+"goUserView";
	});
	//访问角色管理界面
	$(".rolemanage").click(function(){
		
		window.location.href=base.domain+"role/goRoleView";	
	})
	//访问雇员管理界面
	$(".emplyManage").click(function(){
		window.location.href=base.domain+"emply/retrnEmply";
	})
	//访问项目管理界面
	$(".projMange").click(function(){
		window.location.href=base.domain+"projeView/GoProjctView";
		
	})
	

});