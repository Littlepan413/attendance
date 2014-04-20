package org.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hyit.zyy.dao.factory.DAOFactory;
import cn.hyit.zyy.vo.GroupInfo;
import cn.hyit.zyy.vo.StudentInfo;

/**
 * Servlet implementation class DownloadStudentServlet
 */
@WebServlet("/DownloadStudentServlet")
public class DownloadStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadStudentServlet() {
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
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String id = request.getParameter("groupid");
		int groupid = Integer.parseInt(id);
		List<GroupInfo> allGroup = null;
		List<StudentInfo> allStudent = new ArrayList<>();
		Iterator<GroupInfo> iterGroup = null;
		Iterator<StudentInfo> iterStudent = null;
		GroupInfo group = null;
		StudentInfo student = null;
		PrintWriter out = null;
		/**
		 * 获取id所对应班级的所有学生
		 */
		try {
			allGroup = DAOFactory.getIGroupInfoDAOInstance().findAll(groupid);
			iterGroup = allGroup.iterator();
			while (iterGroup.hasNext()) {
				student = DAOFactory.getIStudentInfoDAOInstance().findById(
						iterGroup.next().getStudentid());
				allStudent.add(student);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * response返回学生名单
		 */
		iterGroup = allGroup.iterator();
		iterStudent = allStudent.iterator();
		out = response.getWriter();
		while (iterGroup.hasNext() && iterStudent.hasNext()) {
			group = iterGroup.next();
			student = iterStudent.next();
			/**
			 * 注意：此处输出的班级信息是虚拟班级的信息，非行政班级信息
			 * 物理卡号初始值均为00:00:00:00
			 */
			out.println(student.getStudentid() + "," + student.getName() + ","
					+ student.getCardid() + "," + group.getGroupname());
		}
		out.close();
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
