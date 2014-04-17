package cn.hyit.zyy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.hyit.zyy.dao.IApplyInfoDAO;
import cn.hyit.zyy.vo.ApplyInfo;

public class ApplyInfoDAOimpl implements IApplyInfoDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public ApplyInfoDAOimpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(ApplyInfo apply) throws Exception {
		boolean flag = false;
		String sql = "INSERT INTO apply_info VALUES(?,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, apply.getTeacherid());
		this.pstmt.setString(2, apply.getName());
		this.pstmt.setString(3, apply.getAccpunt());
		this.pstmt.setString(4, apply.getEmail());
		this.pstmt.setInt(5, apply.getState());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public List<ApplyInfo> findAll(String keyWord) throws Exception {
		List<ApplyInfo> all = new ArrayList<ApplyInfo>();
		String sql = "SELECT teacherid,name,account,email FROM apply_info WHERE state = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = this.pstmt.executeQuery();
		ApplyInfo apply = null;
		while (rs.next()) {
			apply = new ApplyInfo();
			apply.setTeacherid(rs.getInt(1));
			apply.setName(rs.getString(2));
			apply.setAccpunt(rs.getString(3));
			apply.setEmail(rs.getString(4));
			all.add(apply);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public ApplyInfo findByid(int teacherid) throws Exception {
		ApplyInfo apply = null;
		String sql = "SELECT teacherid,name,account,email FROM apply_info WHERE state = ? AND teacherid = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, 0);
		this.pstmt.setInt(2, teacherid);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			apply = new ApplyInfo();
			apply.setTeacherid(rs.getInt(1));
			apply.setName(rs.getString(2));
			apply.setAccpunt(rs.getString(3));
			apply.setEmail(rs.getString(4));
		}
		this.pstmt.close();
		return apply;
	}

}
