package cn.hyit.zyy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.hyit.zyy.dao.ISubjectInfoDAO;
import cn.hyit.zyy.vo.SubjectInfo;

public class SubjectInfoDAOimpl implements ISubjectInfoDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public SubjectInfoDAOimpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(SubjectInfo subject) throws Exception {
		boolean flag = false;
		String sql = "insert into subject_info values(null,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, subject.getSubjectname());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public SubjectInfo findById(int subjectid) throws Exception {
		SubjectInfo subject = null;
		String sql="select subjectname from subject_info where subjectid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, subjectid);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()){
			subject = new SubjectInfo();
			subject.setSubjectid(subjectid);
			subject.setSubjectname(rs.getString(1));
		}
		this.pstmt.close();
		return subject;
	}

	@Override
	public SubjectInfo findByName(String subjectname) throws Exception {
		SubjectInfo subject = null;
		String sql="select subjectid from subject_info where subjectname=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1,subjectname);
		ResultSet rs = this.pstmt.executeQuery();
		while(rs.next()){
			subject = new SubjectInfo();
			subject.setSubjectid(rs.getInt(1));
			subject.setSubjectname(subjectname);
		}
		this.pstmt.close();
		return subject;
	}

}
