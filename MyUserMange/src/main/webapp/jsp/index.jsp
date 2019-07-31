<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>
</head>
<body>
<%-- <form action="${pageContext.request.contextPath}/j_spring_security_check">
 用户名：<input type="text" name="j_username"> 
 密码：    <input type="text" name="j_password">
 <input type="submit" value="提交"> 
</form>  --%>


<form action="logMain" method="POST">
 用户名：<input type="text" name="user"> 
 密码：    <input type="text" name="password">
 <input type="submit" value="提交"> 
</form>   


</body>
</html>