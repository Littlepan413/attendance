<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>�û���֤</title>
</head>
<body>
<%!
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/attendance" ;
	public static final String DBUSER = "zyy";
	public static final String DBPASSWD = "kurimu";
%>
<%
	boolean flag = false;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String id = null;
	String name = null;
	String rank = null;
	String email = null;
%>
<%
	String username = request.getParameter("username");
	String passwd = request.getParameter("password");
	try{
		Class.forName(DBDRIVER);
		conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWD);
		stmt = conn.createStatement();
		String sql = "SELECT teacherid,name,rank,email FROM teacher_info WHERE account = '"+username+"' AND passwd = '"+passwd+"';";
		rs = stmt.executeQuery(sql);
		if(rs.next())
		{
			flag = true;
			id = rs.getString(1);
			name = rs.getString(2);
			rank = rs.getString(3);
			email = rs.getString(4);
			
		}
	}catch(Exception e){System.out.println(e);}
	finally{
		try{
			rs.close();
			stmt.close();
			conn.close();
		} catch(Exception e){}
	}
%>
<%
	if(flag){
		session.setAttribute("uname", name);
		session.setAttribute("uid", id);
		session.setAttribute("urank", rank);
%>
		<jsp:forward page="index.jsp"/>
<%
	}else{
%>
		<jsp:forward page="login_fail.jsp"/>
<%
	}
%>
</body>
</html>