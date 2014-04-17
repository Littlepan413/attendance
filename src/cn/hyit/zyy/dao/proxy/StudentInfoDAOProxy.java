package cn.hyit.zyy.dao.proxy;

import java.util.List;

import cn.hyit.zyy.dao.IStudentInfoDAO;
import cn.hyit.zyy.dao.impl.StudentInfoDAOimpl;
import cn.hyit.zyy.dbc.DatabaseConnection;
import cn.hyit.zyy.vo.StudentInfo;

public class StudentInfoDAOProxy implements IStudentInfoDAO {

	private DatabaseConnection dbc = null;
	private IStudentInfoDAO dao = null;

	public StudentInfoDAOProxy() throws Exception {
		this.dbc = new DatabaseConnection();
		this.dao = new StudentInfoDAOimpl(this.dbc.getConnection());
	}

	@Override
	public boolean doCreate(StudentInfo student) throws Exception {
		boolean flag = false;
		try {
			if (this.dao.findById(student.getStudentid()) == null) {
				flag = this.dao.doCreate(student);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public StudentInfo findByName(String name) throws Exception {
		StudentInfo student = null;
		try {
			student = this.dao.findByName(name);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return student;
	}

	@Override
	public StudentInfo findById(int studentid) throws Exception {
		StudentInfo student = null;
		try {
			student = this.dao.findById(studentid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return student;
	}

	@Override
	public List<StudentInfo> findByClass(String classname) throws Exception {
		List<StudentInfo> all = null;
		try {
			all = this.dao.findByClass(classname);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

}
