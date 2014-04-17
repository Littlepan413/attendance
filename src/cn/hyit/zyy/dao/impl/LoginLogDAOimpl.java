package cn.hyit.zyy.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.hyit.zyy.dao.ILoginLogDAO;
import cn.hyit.zyy.vo.LoginLog;

public class LoginLogDAOimpl implements ILoginLogDAO {

	private Connection conn=null;
	private PreparedStatement pstmt = null;
	public LoginLogDAOimpl(Connection conn){
		this.conn = conn;
	}
	@Override
	public boolean doCreate(LoginLog log) throws Exception {
		boolean flag = false;
		String sql = "insert into login_log values(null,?,?,?,?)";
		this.pstmt = conn.prepareStatement(sql);
		this.pstmt.setInt(1, log.getTeacherid());
		this.pstmt.setString(2, log.getLoginip());
		this.pstmt.setTime(3, log.getTime());
		this.pstmt.setDate(4, log.getDate());
		if(this.pstmt.executeUpdate()>0){
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public List<LoginLog> findAll(int teacherid) throws Exception {
		List<LoginLog> all = new ArrayList<LoginLog>();
		String sql = "select teacherid,login_ip,login_time,login_date from login_log where teacherid = ?" ;
		this.pstmt = conn.prepareStatement(sql);
		this.pstmt.setInt(1, teacherid);
		ResultSet rs = this.pstmt.executeQuery();
		LoginLog log = null;
		while(rs.next()){
			log = new LoginLog();
			log.setTeacherid(rs.getInt(1));
			log.setLoginip(rs.getString(2));
			log.setTime(rs.getTime(3));
			log.setDate(rs.getDate(4));
			all.add(log);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public LoginLog findByDate(Date date) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
