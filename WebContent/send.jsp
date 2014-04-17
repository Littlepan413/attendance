<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="gbk"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>提交申请</title>
</head>
<body>
<%!
	public static final String DBDRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/attendance";
	public static final String DBUSER = "zyy";
	public static final String DBPASSWD = "kurimu";
%>
<%
	boolean flag = false;
	Connection conn = null;
	Statement stmt = null;
	ResultSet res =null;
	int rs = 0;
	String name = null;
	String teacherid = null;
	String account = null;
	String email = null;
	String result = "申请发送失败，请重新尝试";
%>
<%
	//设置统一编码
	request.setCharacterEncoding("utf-8");
	name = request.getParameter("username");
	//System.out.println(name);
	teacherid = request.getParameter("TeacherId");
	account = request.getParameter("Account");
	email = request.getParameter("email");
	try{
		Class.forName(DBDRIVER);
		conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWD);
		stmt = conn.createStatement();
		String querySql = "SELECT name FROM teacher_info WHERE teacherid ='"+teacherid+"' OR account = '"+account+"';";
		res = stmt.executeQuery(querySql);
		if(res.next()){
			result = "该账号已被注册，请更换账号或工号再次尝试！";
		}else{
			String sql = "INSERT INTO apply_info VALUES('"+teacherid+"','"+name+"','"+account+"','"+email+"',"+0+");";
			rs = stmt.executeUpdate(sql);
		}
		stmt.close();
		conn.close();
	}catch(Exception e) {System.out.println(e);  }
%>
<%out.print("<script type=\"text/javascript\">");%>
<%
	if(rs==1){
%>
		
		<%out.print("alert(\"申请发送成功，等待管理员审核，通过后将会通过邮件通知您！\");");%>
		<%out.print("top.location='login.html';");%>
<%		
	}else{
		out.print("alert(\""+result+"\");");
		out.print("top.location='Registration.html';");
%>
<%	
	}
%>
<%out.print("</script>"); %>	
</body>
</html>