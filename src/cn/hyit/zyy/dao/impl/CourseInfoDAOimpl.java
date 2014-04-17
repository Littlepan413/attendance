package cn.hyit.zyy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.hyit.zyy.dao.ICourseInfoDAO;
import cn.hyit.zyy.vo.CourseInfo;

public class CourseInfoDAOimpl implements ICourseInfoDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public CourseInfoDAOimpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(CourseInfo course) throws Exception {
		boolean flag = false;
		String sql = "insert into course_info values(null,null,null,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, course.getSubjectid());
		this.pstmt.setInt(2, course.getGroupid());
		this.pstmt.setInt(3, course.getTeacherid());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public List<Integer> findById(int teacherid) throws Exception {
		List<Integer> all = new ArrayList<>();
		int subjectid = 0;
		String sql = "select subjectid from course_info where teacherid = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, teacherid);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			subjectid = rs.getInt(1);
			all.add(subjectid);
		}
		return all;
	}

	@Override
	public CourseInfo findGroup(int teacherid, int subjectid) throws Exception {
		CourseInfo course = null;
		String sql = "select groupid from course_info where subjectid = ? and teacherid = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, subjectid);
		this.pstmt.setInt(2, teacherid);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			course = new CourseInfo();
			course.setGroupid(rs.getInt(1));
		}
		return course;
	}

	@Override
	public boolean findByClass(int subjectid, int groupid) throws Exception {
		boolean flag = false;
		String sql = "select teacherid from course_info where subjectid=? and groupid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, subjectid);
		this.pstmt.setInt(2, groupid);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			flag = true;
		}
		return flag;
	}

}
