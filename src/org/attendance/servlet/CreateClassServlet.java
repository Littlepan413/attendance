package org.attendance.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
		String[] studentid = request.getParameterValues("studentid");
		String classname = request.getParameterValues("classname")[0];
		String subjectname = request.getParameterValues("subject")[0];
		int teacherid = (int) request.getSession().getAttribute("uid");
		/**
		 * 开始班级的创建
		 */
		SubjectInfo subject = new SubjectInfo();
		subject.setSubjectname(subjectname);
		try {
			DAOFactory.getISubjectInfoDAOInstance().doCreate(subject);
			subject = DAOFactory.getISubjectInfoDAOInstance().findByName(
					subject.getSubjectname());
		} catch (Exception e) {
			e.printStackTrace();
		}
		CourseInfo course = new CourseInfo();
		GroupInfo group = new GroupInfo();
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
		for (String id : studentid) {
			group.setStudentid(Integer.parseInt(id));
			try {
				DAOFactory.getIGroupInfoDAOInstance().doCreate(group);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * 班级创建结束 重新获取教师的课程列表
		 */
		List<Integer> allSubjectid = null;
		Iterator<Integer> iterSubid = null;
		List<String> allSubjectName = new ArrayList<>();
		try {
			allSubjectid = DAOFactory.getICourseInfoDAOInstance().findById(
					teacherid);
			iterSubid = allSubjectid.iterator();
			while (iterSubid.hasNext()) {
				subject = DAOFactory.getISubjectInfoDAOInstance().findById(
						iterSubid.next().intValue());
				allSubjectName.add(subject.getSubjectname());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("allSubjectid", allSubjectid);
		request.getSession().setAttribute("allSubjectName", allSubjectName);
		RequestDispatcher rd = request.getRequestDispatcher("queryClassList");
		rd.forward(request, response);
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
