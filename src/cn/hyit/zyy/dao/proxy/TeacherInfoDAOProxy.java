package cn.hyit.zyy.dao.proxy;

import java.util.List;

import cn.hyit.zyy.dao.ITeacherInfoDAO;
import cn.hyit.zyy.dao.impl.TeacherInfoDAOimpl;
import cn.hyit.zyy.dbc.DatabaseConnection;
import cn.hyit.zyy.vo.TeacherInfo;

public class TeacherInfoDAOProxy implements ITeacherInfoDAO {

	private DatabaseConnection dbc = null;
	private ITeacherInfoDAO dao = null;

	public TeacherInfoDAOProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new TeacherInfoDAOimpl(this.dbc.getConnection());
	}

	@Override
	public boolean doCreate(TeacherInfo teacher) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doCreate(teacher);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
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
		try {
			teacher = this.dao.findByUser(account, password);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return teacher;
	}

	@Override
	public TeacherInfo findByid(String cardid) throws Exception {
		TeacherInfo teacher = null;
		try {
			teacher = this.findByid(cardid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return teacher;
	}

}
