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
import cn.hyit.zyy.vo.StudentInfo;
import cn.hyit.zyy.vo.SubjectInfo;

/**
 * Servlet implementation class CreateClass
 */
@WebServlet("/CreateClass")
public class CreateClassServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateClassServlet2() {
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
		int teacherid = (int) request.getSession().getAttribute("uid");
		/**
		 * 从数据库中获取班级学生名单 
		 */
		List<StudentInfo> allStudent = null;
		try {
			allStudent = DAOFactory.getIStudentInfoDAOInstance().findByClass(classname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SubjectInfo subject = new SubjectInfo();
		CourseInfo course = new CourseInfo();
		GroupInfo group = new GroupInfo();
		boolean flag = true;
		/**
		 * 开始检测是否有重复班级的创建
		 */
		try {
			List<GroupInfo> allgroup = DAOFactory.getIGroupInfoDAOInstance()
					.findByName(classname);
			subject = DAOFactory.getISubjectInfoDAOInstance().findByName(
					subjectname);
			if(subject==null){
				flag = true;
			}
			Iterator<GroupInfo> iter = allgroup.iterator();
			while (iter.hasNext()) {
				if(flag){
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
			 * 开始班级的创建
			 */
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
			Iterator<StudentInfo> iterStudent = allStudent.iterator();
			while(iterStudent.hasNext()){
				group.setStudentid(iterStudent.next().getStudentid());
				try {
					DAOFactory.getIGroupInfoDAOInstance().doCreate(group);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			/**
			 * 班级创建结束
			 * 重新获取教师的课程列表
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
		} else {
			/**
			 * 重复创建班级，跳转至错误界面
			 */
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
