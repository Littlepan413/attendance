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
import cn.hyit.zyy.vo.GroupInfo;
import cn.hyit.zyy.vo.StudentInfo;
import cn.hyit.zyy.vo.SubjectInfo;

/**
 * Servlet implementation class CheckRepeatServlet
 */
@WebServlet("/CheckRepeatServlet")
public class CheckRepeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckRepeatServlet() {
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
		SubjectInfo subject = new SubjectInfo();
		GroupInfo group = new GroupInfo();
		RequestDispatcher rd = null;
		boolean flag = true;
		/**
		 * 开始检测是否有重复班级的创建
		 */
		try {
			List<GroupInfo> allgroup = DAOFactory.getIGroupInfoDAOInstance()
					.findByName(classname);
			subject = DAOFactory.getISubjectInfoDAOInstance().findByName(
					subjectname);
			Iterator<GroupInfo> iter = allgroup.iterator();
			while (iter.hasNext()) {
				if (subject == null) {
					break;
				}
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
		 * 检测班级重复结束
		 */
		if (flag) {
			/**
			 * 从数据库中获取班级学生名单
			 */
			List<StudentInfo> allStudent = null;
			try {
				allStudent = DAOFactory.getIStudentInfoDAOInstance()
						.findByClass(classname);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("allStudent", allStudent);
			request.setAttribute("subject", subjectname);
			rd = request.getRequestDispatcher("select.jsp");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("error.html");
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
