<%@ page contentType="text/html;charset=GBK" language="java" pageEncoding="GBK"%>
<html>
<head>
<title>欢迎登陆</title>
</head>
<body>
<center>
<h1>登陆成功</h1>
<h2>欢迎<font color="red"><%=request.getParameter("uname")%></font>光临</h2><br>
<h2>您的用户ID为<font color="red"><%=request.getParameter("uid")%></font></h2><br>
<h2>您的账户权限为<font color="red"><%=request.getParameter("urank")%></font></h2><br>
<h2>您的邮箱为<font color="red"><%=request.getParameter("email")%></font></h2><br>
</center>
</body>
</html>