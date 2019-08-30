<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理界面</title>
<link rel="stylesheet" href="css/userManage.css">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/userManage.js"></script>
<script type="text/javascript" src="js/base.js"></script>

<script type="text/javascript">
function exreport(){
	var fileName=$("input[type='file']").val();
	alert("file"+fileName);
	if(fileName.lastIndexOf(".xls")>0||fileName.lastIndexOf(".xlsx")>0){
		alert("上传成功");
		return true;
	}else{
		alert("请上传excel文件");
		return false;
	}
}
</script>
</head>
<body>
<h2 class="title">用户管理界面</h2>
用户名：<input type="text" name="username" >   
电话：<input type="text" name="teleps"><br/>
<div class="selen" onclick=serch()><input type="button" value="查询"></div>

<!-- 报表视图 -->
<div class="repor_view">
<span class="report_Dwn"> <input type="button" value="报表导出" id="reportDown"></span>
<span class="report_uplod"><input type="button" value="报表上传" id="reprortUp"></span>
</div>

<div class="cont">
<span class="sorry">抱歉，你未查找到信息!</span> 
<table id="body" border="1px" cellpadding="18px">
<tr>
<td><input type="checkbox" class="all_chek"> </td>
<td>id</td>
<td>用户名</td>
<td>电话</td>
<td>地址</td>
</tr>

<!-- 内容视图 -->
<tbody class="contens">
</tbody>

</table>
</div>


<!-- 页码视图 -->
<div class="page">
<div class="function">
<input type="button" value="添加" class="addn">&nbsp;
<input type="button" value="删除" class="delet">&nbsp;
<input type="button" value="修改" class="updat">&nbsp;
<input type="button" value="下载资源" class="downResource">&nbsp;
</div>
</div>

<!-- 幕布 -->
<div class="fubull"></div>

<!-- 添加界面 -->
<div class="addView">
<div class="addCont">
用户名： <input type="text" name="ad_urname"><br/>
密码：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="ad_passw"><br/>
电话：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="ad_telp"><br/>
地址：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="ad_addr"><br/>
<div class="Butn">
<input type="button" value="保存" class="add_save"> 
<input type="button" value="取消" class="add_cancl">
</div>
</div>
</div>

<!-- 删除提示框 -->
<div class="delView">
<div class="delCont">
 <span>确定是否删除?</span><br/>
 <div class="del_butn">
 <input type="button" name="de_sure" value="确定">
 <input type="button" name="de_no" value="取消">
 </div>
 </div>
</div>

<!-- 修改界面 -->
<div class="updatView">
<div class="updtCont">
用户名：<input type="text" name="name"/><br/>
密码：&nbsp;&nbsp;  <input type="password" name="pssword"/><br/>
电话：&nbsp;&nbsp;  <input type="text" name="telep"/><br/>
地址：&nbsp;&nbsp;  <input type="text" name="addr"/><br/>
<div class="Butn">
<input type="submit" value="保存" class="upt_save"> 
<input type="button" value="取消" class="upt_cancl">
</div>
</div>
 </div>
 
 
 <!--报表上传  -->
 <div class="reporUpView">
 <div class="reporCont">
<form action="reportUpload" method="post" enctype="multipart/form-data" onsubmit="return exreport()">
 描述:<input type="text" name="desc"><br/>
 文件:<input type="file" name="file" style="border: 1px solid #c0c0c0;">
 <input type="button" value="取消" class="templateCancel">
 <input type="submit" value="上传" class="repotSub">
 <input type="button" value="模板下载" class="templateDown">
 
 </form>
 </div>
 </div>
 
 

</body>
</html>