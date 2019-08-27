<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>

<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/roleinfo.js"></script>
<script type="text/javascript" src="../js/base.js"></script>
<script type="text/javascript">
function Exrepot(){
	var fileName=$("input[name='file']").val();
	//alert("fileName="+fileName);
	if(fileName.lastIndexOf(".xlsx")>0||fileName.lastIndexOf(".xls")>0){
		 alert("上传成功")
		return true;
		
	}else{
		alert("你上传的文件不符合标准");
		return false;
	}
	
}
</script>
<link rel="stylesheet" href="../css/Roleinfo.css">
</head>
<body>
<h2>角色界面</h2>

<%--查询视图 --%>
角色名称:<input type="text" class="rolename">
级别:<input type="text" class="levl">
<div><input type="button" value="查询" class="serch"></div>
<div><input type="button" value="报表下载" class="repotDown"></div>
<div><input type="button" value="报表上传" class="reportUp"></div>

 
<%--内容视图 --%>
<div>
<table cellpadding="30" cellspacing="1" border="1" >
<tr>
<td><input type="checkbox" class="chekAlls"></td>
<td>id</td>
<td>角色名</td>
<td>级别</td>
</tr>

<%--内容视图 --%>
<tbody class="cotent">

</tbody>
</table>
<span class="noFind">未查询到结果!</span>
</div>

<!-- 页码视图 -->
<div class="pageViewn">

</div>


<!-- 角色操作按钮 -->
<div class="crudView">
<input type="button" value="添加" class="add">
<input type="button" value="删除" class="delt">
<input type="button" value="删除所有" class="deltAll">
<input type="button" value="修改" class="update">
</div>

<%--幕布 --%>
<div class="mubu"></div>

<%--添加视图 --%>
<div class="addView"> 
<div class="addcont">
角色名称:<input type="text" name="rolname"><br/>
级别:&nbsp;&nbsp;&nbsp;  <input type="text" name="lvle"><br/>
<input type="button" value="保存" class="ad_save">
<input type="button" value="取消" class="ad_del">
</div>
</div>

<%--删除视图 --%>
<div class="deletView">
<div class="delet">  
<span>你确定删除吗？</span><br/>
<input type="button" value="确定" class="del_sure"/>
<input type="button" value="取消" class="del_canl"/>
</div>
</div>

<%--删除所有视图--%>
<div class="delallView">
<div class="delall">
<span>你确定删除所有吗？</span> 
<input type="button" value="确定" class="deall_sure">
<input type="button" value="取消" class="deall_canl">
</div>
</div>

<%--修改视图 --%>
<div class="updaView">
<div class="updaCont">
角色名称:<input type="text" name="up_rolnas"><br/>
级别:&nbsp;&nbsp;&nbsp; <input type="text" name="up_levl"><br/>
<input type="button" value="确定" class="up_sure"/>
<input type="button" value="取消" class="up_canl"/>
</div>
</div> 

<!-- 报表上传视图 -->
<div id="repotUpView">
<div class="reprotCont">
<form action="reportUp" method="post" enctype="multipart/form-data" onsubmit="return Exrepot()">
desc:<input type="text" name="desc"><br/>
file:<input type="file" name="file" style="border: 1px solid #c0c0c0;">
<input type="submit" value="提交" class="sub">
</form>
</div>
</div>

</body>
</html>