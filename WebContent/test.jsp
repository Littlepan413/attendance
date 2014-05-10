<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%	
	request.setCharacterEncoding("utf-8");
	String []studentid;
	String []studentname;
	String studentclass;
    studentid=request.getParameterValues("studentid");
    studentname=request.getParameterValues("studentname");
    studentclass=request.getParameterValues("studentclass")[0];
    for(String sid:studentid){
    	out.println(sid);
    }
    for(String sname:studentname){
    	out.println(sname);
    }
    out.println(studentclass);
     %>
</body>
</html>