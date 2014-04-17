package cn.hyit.zyy.dao.proxy;

import java.util.List;

import cn.hyit.zyy.dao.ICourseInfoDAO;
import cn.hyit.zyy.dao.impl.CourseInfoDAOimpl;
import cn.hyit.zyy.dbc.DatabaseConnection;
import cn.hyit.zyy.vo.CourseInfo;

public class CourseInfoDAOProxy implements ICourseInfoDAO {

	private DatabaseConnection dbc = null;
	private ICourseInfoDAO dao = null;
	public CourseInfoDAOProxy()throws Exception{
		this.dbc = new DatabaseConnection();
		this.dao = new CourseInfoDAOimpl(this.dbc.getConnection());
	}
	@Override
	public boolean doCreate(CourseInfo course) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.doCreate(course)){
				flag=true;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public List<Integer> findById(int teacherid) throws Exception {
		List<Integer> all = null;
		try {
			all = this.dao.findById(teacherid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

	@Override
	public CourseInfo findGroup(int teacherid, int subjectid) throws Exception {
		CourseInfo course = null;
		try {
			course = this.dao.findGroup(teacherid, subjectid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return course;
	}
	@Override
	public boolean findByClass(int subjectid, int groupid) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.findByClass(subjectid, groupid);
		} catch (Exception e) {
			throw e;
		}finally{
			this.dbc.close();
		}
		return flag;
	}

}
