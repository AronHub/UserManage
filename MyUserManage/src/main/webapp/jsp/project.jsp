<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="../js/project.js"></script>
<script type="text/javascript" src="../js/base.js"></script>
<script type="text/javascript">
function checkFile(){
	var fileName=$("input[name='file']").val();
	if(fileName.lastIndexOf(".xls")>0||fileName.lastIndexOf(".xlsx")>0){
		alert("上传成功");
		return true;
	}else{
		alert("文件格式不正确");
		return false;
	}
}

</script>
<link rel="stylesheet" href="../css/proManage.css" />
<title>Insert title here</title> 
</head> 
<body>
<h2>项目管理</h2> 
项目名称：<input type="text" class="projName">
项目编号：<input type="text" class="projNo"><br/>
<input type="button" value="查询" class="serch"><br/>
<input type="button" value="报表上传" class="reportUp"> 
<input type="button" value="报表下载" class="reportDown">

<table class="content" cellpadding="20px" border="2px" >
<tr>
<td><input type="checkbox" class="checkAll">全选</td>
<td>id</td>
<td>项目名称</td>
<td>经理</td>
</tr>

<tbody class="cont">

</tbody>
</table>

<div class="pageViw">
</div>

<div id="mubu">  </div>

<div class=reprotView>
<div class="reportCont">
<form action="fileName" method="post" enctype="multipart/form-data" onsubmit="return checkFile()">
描述<input type="text" name="desc" ><br/>
文件<input type="file" name="file" style="border: 1px solid #c0c0c0; "><br/>
<input type="submit" value="提交" id="xj">
<input type="button" value="取消">
</form>
</div>
</div>

</body>
</html>