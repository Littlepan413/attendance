<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="gbk"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>�ύ����</title>
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
	String result = "���뷢��ʧ�ܣ������³���";
%>
<%
	//����ͳһ����
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
			result = "���˺��ѱ�ע�ᣬ������˺Ż򹤺��ٴγ��ԣ�";
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
		
		<%out.print("alert(\"���뷢�ͳɹ����ȴ�����Ա��ˣ�ͨ���󽫻�ͨ���ʼ�֪ͨ����\");");%>
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