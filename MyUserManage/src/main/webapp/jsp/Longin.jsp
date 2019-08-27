<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>
</head>
<body>
<%
String  userName="";
Cookie[] cookieArray = request.getCookies();
for (Cookie ck : cookieArray) {
	if ("userName".equals(ck.getName())) {
		userName=ck.getValue();
	}
}
%>
<form action="logMain" method="POST">
 用户名：<input type="text" name="user" value="<%=userName %>"> 
 密码：    <input type="text" name="password"><br/>
<input type="checkbox" value="keep" name="keepName" id="isKeep">保存用户名    &nbsp;&nbsp;&nbsp;
<input type="submit" value="登录"> 
</form>   
<font color="red"> ${err} </font> 

</body>
</html>