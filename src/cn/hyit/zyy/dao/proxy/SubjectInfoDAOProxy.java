package cn.hyit.zyy.dao.proxy;

import cn.hyit.zyy.dao.ISubjectInfoDAO;
import cn.hyit.zyy.dao.impl.SubjectInfoDAOimpl;
import cn.hyit.zyy.dbc.DatabaseConnection;
import cn.hyit.zyy.vo.SubjectInfo;

public class SubjectInfoDAOProxy implements ISubjectInfoDAO {

	private DatabaseConnection dbc = null;
	private ISubjectInfoDAO dao = null;

	public SubjectInfoDAOProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new SubjectInfoDAOimpl(this.dbc.getConnection());
	}

	@Override
	public boolean doCreate(SubjectInfo subject) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findByName(subject.getSubjectname()) == null) {
				flag = this.dao.doCreate(subject);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public SubjectInfo findById(int subjectid) throws Exception {
		SubjectInfo subject = null;
		try {
			subject = this.dao.findById(subjectid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return subject;
	}

	@Override
	public SubjectInfo findByName(String subjectname) throws Exception {
		SubjectInfo subject = null;
		try {
			subject = this.dao.findByName(subjectname);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return subject;
	}

}
