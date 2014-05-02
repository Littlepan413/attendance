package cn.hyit.zyy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.hyit.zyy.dao.IStudentInfoDAO;
import cn.hyit.zyy.vo.StudentInfo;

public class StudentInfoDAOimpl implements IStudentInfoDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public StudentInfoDAOimpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(StudentInfo student) throws Exception {
		boolean flag = false;
		String sql = "insert into student_info values(?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, student.getStudentid());
		this.pstmt.setString(2, student.getName());
		this.pstmt.setString(3, student.getCardid());
		this.pstmt.setString(4, student.getClassname());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public StudentInfo findByName(String name) throws Exception {
		StudentInfo student = null;
		String sql = "select studentid,cardid,classname from student_info where studentname=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, name);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			student = new StudentInfo();
			student.setStudentid(rs.getInt(1));
			student.setName(name);
			student.setCardid(rs.getString(2));
			student.setClassname(rs.getString(3));
		}
		this.pstmt.close();
		return student;
	}

	@Override
	public StudentInfo findById(int studentid) throws Exception {
		StudentInfo student = null;
		String sql = "select studentname,cardid,classname from student_info where studentid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, studentid);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			student = new StudentInfo();
			student.setStudentid(studentid);
			student.setName(rs.getString(1));
			student.setCardid(rs.getString(2));
			student.setClassname(rs.getString(3));
		}
		this.pstmt.close();
		return student;
	}

	@Override
	public List<StudentInfo> findByClass(String classname) throws Exception {
		List<StudentInfo> all = new ArrayList<>();
		StudentInfo student = null;
		String sql = "select studentid,studentname,cardid from student_info where classname=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, classname);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			student = new StudentInfo();
			student.setStudentid(rs.getInt(1));
			student.setName(rs.getString(2));
			student.setCardid(rs.getString(3));
			student.setClassname(classname);
			all.add(student);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public List<String> findClassName() throws Exception {
		List<String> all = new ArrayList<>();
		String sql = "SELECT DISTINCT classname FROM student_info;";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()){
			all.add(rs.getString(1));
		}
		this.pstmt.close();
		return all;
	}

}
