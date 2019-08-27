<%@ page language="java" import="java.net.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</head>

<body>
<h2>欢迎来到主界面！</h2>    
<%
String lastime=(String)request.getAttribute("lastTime");
 lastime=URLDecoder.decode(lastime,"utf-8"); %>
 
<div>用户：${user.name}  &nbsp;&nbsp; &nbsp;<%=lastime%><span></span></div><br/>
<div class="usermanage">用户管理</div><br/>
<div class="rolemanage">角色管理</div><br/>
<div class="projMange">项目管理</div><br/>
<div class="emplyManage">雇员管理</div><br/>
<div class="##">任务管理</div><br/>
<div class="##">修改密码</div><br/> 
<a href="retnLongin">安全退出</a><br/>
</body>
</html>