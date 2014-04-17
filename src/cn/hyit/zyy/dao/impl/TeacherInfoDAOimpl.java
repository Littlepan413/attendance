package cn.hyit.zyy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import cn.hyit.zyy.dao.ITeacherInfoDAO;
import cn.hyit.zyy.vo.TeacherInfo;

public class TeacherInfoDAOimpl implements ITeacherInfoDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public TeacherInfoDAOimpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(TeacherInfo teacher) throws Exception {
		boolean flag = false;
		String sql = "insert into values(?,?,?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, teacher.getTeacherid());
		this.pstmt.setString(2, teacher.getName());
		this.pstmt.setString(3, teacher.getCardid());
		this.pstmt.setString(4, teacher.getAccount());
		this.pstmt.setString(5, teacher.getPassword());
		this.pstmt.setInt(6, teacher.getRank());
		this.pstmt.setString(7, teacher.getEmail());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public List<TeacherInfo> findAll(String keyWrod) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeacherInfo findByUser(String account, String password)
			throws Exception {
		TeacherInfo teacher = null;
		String sql = "select teacherid,name,rank,email from teacher_info where account = ? and passwd = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, account);
		this.pstmt.setString(2, password);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			teacher = new TeacherInfo();
			teacher.setTeacherid(rs.getInt(1));
			teacher.setName(rs.getString(2));
			teacher.setRank(rs.getInt(3));
			teacher.setEmail(rs.getString(4));
		}
		this.pstmt.close();
		return teacher;
	}

	public TeacherInfo findByid(String cardid) throws Exception {
		TeacherInfo teacher = null;
		String sql = "select teacherid,name,rank,email from teacher_info where cardid = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, cardid);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			teacher = new TeacherInfo();
			teacher.setTeacherid(rs.getInt(1));
			teacher.setName(rs.getString(2));
			teacher.setRank(rs.getInt(3));
			teacher.setEmail(rs.getString(4));
		}
		this.pstmt.close();
		return teacher;
	}
}
