package cn.hyit.zyy.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.hyit.zyy.dao.IOperateLogDAO;
import cn.hyit.zyy.vo.OperateLog;

public class OperateLogDAOimpl implements IOperateLogDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public OperateLogDAOimpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean doCreate(OperateLog log) throws Exception {
		boolean flag = false;
		String sql = "insert into operate_log values(null,?,?,?,?)";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, log.getTeacherid());
		this.pstmt.setString(2, log.getOperate());
		this.pstmt.setTime(3, log.getTime());
		this.pstmt.setDate(4, log.getDate());
		if(this.pstmt.executeUpdate()>0){
			flag = true;
		}
		this.pstmt.close();
		return flag;
	}

	@Override
	public List<OperateLog> findAll() throws Exception {
		List<OperateLog> all = new ArrayList<OperateLog>();
		String sql = "SELECT teacherid,operate,op_time,op_date FROM operate_log";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		OperateLog log = null;
		while(rs.next()){
			log = new OperateLog();
			log.setTeacherid(rs.getInt(1));
			log.setOperate(rs.getString(2));
			log.setTime(rs.getTime(3));
			log.setDate(rs.getDate(4));
			all.add(log);
		}
		this.pstmt.close();
		return all;
	}

	@Override
	public OperateLog findByDate(Date date) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
