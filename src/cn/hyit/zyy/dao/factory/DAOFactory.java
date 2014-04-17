package cn.hyit.zyy.dao.factory;

import cn.hyit.zyy.dao.ICourseInfoDAO;
import cn.hyit.zyy.dao.IGroupInfoDAO;
import cn.hyit.zyy.dao.ILoginLogDAO;
import cn.hyit.zyy.dao.IOperateLogDAO;
import cn.hyit.zyy.dao.IStudentInfoDAO;
import cn.hyit.zyy.dao.ISubjectInfoDAO;
import cn.hyit.zyy.dao.ITeacherInfoDAO;
import cn.hyit.zyy.dao.IApplyInfoDAO;
import cn.hyit.zyy.dao.proxy.ApplyInfoDAOProxy;
import cn.hyit.zyy.dao.proxy.CourseInfoDAOProxy;
import cn.hyit.zyy.dao.proxy.GroupInfoDAOProxy;
import cn.hyit.zyy.dao.proxy.LoginLogDAOProxy;
import cn.hyit.zyy.dao.proxy.OperateLogDAOProxy;
import cn.hyit.zyy.dao.proxy.StudentInfoDAOProxy;
import cn.hyit.zyy.dao.proxy.SubjectInfoDAOProxy;
import cn.hyit.zyy.dao.proxy.TeacherInfoDAOProxy;

public class DAOFactory {
	public static IApplyInfoDAO getIApplyInfoDAOInstance() throws Exception {
		return new ApplyInfoDAOProxy();
	}

	public static ITeacherInfoDAO getITeacherInfoDAOInstance() throws Exception {
		return new TeacherInfoDAOProxy();
	}

	public static ILoginLogDAO getILoginLogDAOInstance() throws Exception {
		return new LoginLogDAOProxy();
	}

	public static IOperateLogDAO getIOperateLogDAOInstance() throws Exception {
		return new OperateLogDAOProxy();
	}

	public static ISubjectInfoDAO getISubjectInfoDAOInstance() throws Exception {
		return new SubjectInfoDAOProxy();
	}

	public static ICourseInfoDAO getICourseInfoDAOInstance() throws Exception {
		return new CourseInfoDAOProxy();
	}

	public static IGroupInfoDAO getIGroupInfoDAOInstance() throws Exception {
		return new GroupInfoDAOProxy();
	}

	public static IStudentInfoDAO getIStudentInfoDAOInstance() throws Exception {
		return new StudentInfoDAOProxy();
	}
}
