<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>正在跳转</title>
</head>
<body>
<%
	response.setHeader("refresh", "3;URL=login,html");
	session.invalidate();
%>
<center>
<h3>您已成功退出系统，三秒后跳转登陆页</h3>
<h3>如果没有跳转，请点击<a href="login.html">这里</a>手动跳转</h3>
</center>
</body>
</html>