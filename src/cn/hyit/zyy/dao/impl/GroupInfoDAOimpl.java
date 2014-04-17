package cn.hyit.zyy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.hyit.zyy.dao.IGroupInfoDAO;
import cn.hyit.zyy.vo.GroupInfo;

public class GroupInfoDAOimpl implements IGroupInfoDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public GroupInfoDAOimpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(GroupInfo group) throws Exception {
		boolean flag = false;
		String sql = "insert into group_info values(null,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, group.getGroupid());
		this.pstmt.setInt(2, group.getStudentid());
		this.pstmt.setString(3, group.getGroupname());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public List<GroupInfo> findAll(int groupid) throws Exception {
		List<GroupInfo> all = new ArrayList<>();
		GroupInfo group = null;
		String sql = "select studentid,groupname from group_info where groupid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, groupid);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			group = new GroupInfo();
			group.setGroupid(groupid);
			group.setStudentid(rs.getInt(1));
			group.setGroupname(rs.getString(2));
			all.add(group);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public boolean removeStudent(GroupInfo group) throws Exception {
		boolean flag = false;
		String sql = "delete from group_info where groupid=? and studentid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, group.getGroupid());
		this.pstmt.setInt(2, group.getStudentid());
		if (this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public GroupInfo findById(int groupid, int studentid) throws Exception {
		GroupInfo group = null;
		String sql = "select groupname from group_info where groupid=? and studentid=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, groupid);
		this.pstmt.setInt(2, studentid);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			group = new GroupInfo();
			group.setGroupid(groupid);
			group.setStudentid(studentid);
			group.setGroupname(rs.getString(1));
		}
		this.pstmt.close();
		return group;
	}

	@Override
	public List<GroupInfo> findByName(String groupname) throws Exception {
		List<GroupInfo> all = new ArrayList<>();
		GroupInfo group = null;
		String sql = "SELECT DISTINCT groupid from group_info WHERE groupname=?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, groupname);
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			group = new GroupInfo();
			group.setGroupid(rs.getInt(1));
			all.add(group);
		}
		this.pstmt.close();
		return all;
	}

}
