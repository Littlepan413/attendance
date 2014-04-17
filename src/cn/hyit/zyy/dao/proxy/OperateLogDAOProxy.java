package cn.hyit.zyy.dao.proxy;

import java.sql.Date;
import java.util.List;

import cn.hyit.zyy.dao.IOperateLogDAO;
import cn.hyit.zyy.dao.impl.OperateLogDAOimpl;
import cn.hyit.zyy.dbc.DatabaseConnection;
import cn.hyit.zyy.vo.OperateLog;

public class OperateLogDAOProxy implements IOperateLogDAO {

	private DatabaseConnection dbc = null;
	private IOperateLogDAO dao = null;
	public OperateLogDAOProxy()throws Exception{
		this.dbc = new DatabaseConnection();
		this.dao = new OperateLogDAOimpl(this.dbc.getConnection());
	}
	@Override
	public boolean doCreate(OperateLog log) throws Exception {
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
	public List<OperateLog> findAll() throws Exception {
		List<OperateLog> all = null;
		try {
			all = this.dao.findAll();
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

	@Override
	public OperateLog findByDate(Date date) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
