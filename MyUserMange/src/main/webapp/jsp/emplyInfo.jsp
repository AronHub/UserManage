<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/emplyeInfo.js"></script>
<script type="text/javascript">
function isXlm(){
   var fileName=$("input[name='file']").val();
   //alert("fileName="+fileName);
   if(fileName.lastIndexOf(".xlsx")>0||fileName.lastIndexOf(".xls")>0){
	   return true;
	   alert("上传成功")
   }else{
	   alert("请选择Excle文件")
	   return false;
   }
}

</script>
<link rel="stylesheet" href="../css/emplyee.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<h2>雇员管理界面</h2>
雇员名: <input type="text" class="empName">
email:<input type="text" class="email"><br/>
<input type="button" value="查询" class="serch"><br/>



<input type="button" value="报表上传" id="exeup">
<input type="button" value="报表下载" id="exedown">

<!--内容 -->
<div class="contenView">
<table cellpadding="18px" border="2px solid green">
<tr>
<td><input type="checkbox" class="checkAll"> </td>
<td>id</td>
<td>雇员名</td>
<td>email</td>   
<td>角色</td>
<td>项目</td>
</tr>


<tbody class="cont">

</tbody>
</table>
<div class="tis" style="display:none;margin-left: 180px;">抱歉，未搜索到内容</div>
</div>

<!-- 页码视图 -->
<div id="pageView">
</div>

<!-- 操作视图 -->
<input type="button" value="添加" id="add">
<input type="button" value="删除" id="delete">
<input type="button" value="删除所有" id="deletAll">
<input type="button" value="修改" id="update">
<input type="button" value="跑批" id="runpi">

<%--幕布 --%>
<div class="mubu"> </div> 
<!-- 加载提示 -->
<div class="loading">加载视图，请稍等。</div>

<!-- 添加视图 -->
<div class="addView">
<div class="addCont">
雇员名:<input type="text" name="empnams">
email:&nbsp;<input type="text" name="empemail"><br/>
角色:&nbsp; <input type="text" name="roleId">
<input type="button" value="确定" class="add_sure">
<input type="button" value="取消" class="add_canl">
</div>
</div>

<!-- 删除视图 -->
<div class="deltView">
<div class="deltCont">
<span> 确定删除吗？</span><br/> 
<input type="button" value="确定" class="del_sure">
<input type="button" value="取消" class="del_canl">
</div>
</div>

<!--删除所有视图-->
<div class="deleAllView">
<div class="delteAl">
<span>确定删除所有信息吗？</span><br/>	
<input type="button" value="确定" class="delAll_sure">
<input type="button" value="取消" class="delAll_canl">
</div>
</div>

<!-- 修改视图 -->
<div class="updatView">
<div class="updatCont">
雇员名:<input type="text" name="empnam"><br/>
email:&nbsp;<input type="text" name="empemails"><br/>
角色:&nbsp; <input type="text" name="roleIdn"><br/>
<input type="button" value="确定" class="up_sure">
<input type="button" value="取消" class="up_canl">
</div>
</div>

<%--报表上传视图--%>
<div class="upexport" style="display: none">
<div class="upexCont">
<form method="post" action="exportUp" enctype="multipart/form-data" onsubmit="return isXlm()">
Desc:<input type="text" name="desc"><br/>
File:<input type="file" name="file" style="border: 1px solid #c0c0c0;"><br/>
<input type="submit" value="提交" class="subm">
</form>
</div>
</div>


</body>
</html>