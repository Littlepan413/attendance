package cn.hyit.zyy.dao.proxy;

import java.sql.Date;
import java.util.List;

import cn.hyit.zyy.dao.ILoginLogDAO;
import cn.hyit.zyy.dao.impl.LoginLogDAOimpl;
import cn.hyit.zyy.dbc.DatabaseConnection;
import cn.hyit.zyy.vo.LoginLog;

public class LoginLogDAOProxy implements ILoginLogDAO {

	private DatabaseConnection dbc = null;
	private ILoginLogDAO dao = null;
	public LoginLogDAOProxy()throws Exception{
		this.dbc = new DatabaseConnection();
		this.dao = new LoginLogDAOimpl(this.dbc.getConnection());
	}
	@Override
	public boolean doCreate(LoginLog log) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doCreate(log);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<LoginLog> findAll(int teacherid) throws Exception {
		List<LoginLog> all = null;
		try {
			all = this.dao.findAll(teacherid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

	@Override
	public LoginLog findByDate(Date date) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
