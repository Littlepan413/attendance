package org.attendance.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hyit.zyy.dao.factory.DAOFactory;
import cn.hyit.zyy.vo.CourseInfo;
import cn.hyit.zyy.vo.GroupInfo;
import cn.hyit.zyy.vo.StudentInfo;
import cn.hyit.zyy.vo.SubjectInfo;

/**
 * Servlet implementation class CreateClass
 */
@WebServlet("/CreateClass")
public class CreateClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateClassServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String classname = request.getParameter("classname");
		String subjectname = request.getParameter("subject");
		String member = request.getParameter("member");
		int teacherid = (int) request.getSession().getAttribute("uid");
		/**
		 * ���п��ܻ����һЩ������ַ����ڴ���ǰ���ȹ��ˣ��ȴ����
		 */
		String[] student = member.split("	|\n");
		SubjectInfo subject = new SubjectInfo();
		CourseInfo course = new CourseInfo();
		GroupInfo group = new GroupInfo();
		StudentInfo stuinfo = new StudentInfo();
		boolean flag = true;
		try {
			List<GroupInfo> allgroup = DAOFactory.getIGroupInfoDAOInstance()
					.findByName(classname);
			subject = DAOFactory.getISubjectInfoDAOInstance().findByName(
					subjectname);
			Iterator<GroupInfo> iter = allgroup.iterator();
			while (iter.hasNext()) {
				group = iter.next();
				if (DAOFactory.getICourseInfoDAOInstance().findByClass(
						subject.getSubjectid(), group.getGroupid())) {
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * �ڲ���༶ǰ��Ҫ����Ƿ����ظ���������ϣ����ϼ���Ƿ����ظ��༶����
		 */
		if (flag) {
			subject = new SubjectInfo();
			subject.setSubjectname(subjectname);
			try {
				DAOFactory.getISubjectInfoDAOInstance().doCreate(subject);
				subject = DAOFactory.getISubjectInfoDAOInstance().findByName(
						subject.getSubjectname());
			} catch (Exception e) {
				e.printStackTrace();
			}
			course.setSubjectid(subject.getSubjectid());
			course.setTeacherid(teacherid);
			try {
				DAOFactory.getICourseInfoDAOInstance().doCreate(course);
				course = DAOFactory.getICourseInfoDAOInstance().findGroup(
						course.getTeacherid(), course.getSubjectid());
			} catch (Exception e) {
				e.printStackTrace();
			}
			group.setGroupid(course.getGroupid());
			group.setGroupname(classname);
			for (int i = 0; i < student.length; i += 2) {
				group.setStudentid(Integer.parseInt(student[i]));
				try {
					DAOFactory.getIGroupInfoDAOInstance().doCreate(group);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			/**
			 * ���¿�ʼ�����ݿ���д��ѧ����������ѧ��������Ϊ��������
			 */
			for (int i = 0; i < student.length; i += 2) {
				stuinfo.setStudentid(Integer.parseInt(student[i]));
				stuinfo.setName(student[i + 1]);
				stuinfo.setCardid("00:00:00:00");
				stuinfo.setClassname("δʹ��");
				try {
					DAOFactory.getIStudentInfoDAOInstance().doCreate(stuinfo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("addclass.jsp");
			rd.forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("error.html");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
