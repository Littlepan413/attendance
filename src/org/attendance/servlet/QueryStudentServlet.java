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
import cn.hyit.zyy.vo.GroupInfo;
import cn.hyit.zyy.vo.StudentInfo;

/**
 * Servlet implementation class QueryStudentServlet
 */
@WebServlet("/QueryStudentServlet")
public class QueryStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryStudentServlet() {
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
		List<Integer> allGroupid = (List<Integer>) request
				.getAttribute("allGroupid");
		List<GroupInfo> allGroup = new ArrayList<>();
		List<GroupInfo> all = null;
		Iterator<Integer> iterInt = allGroupid.iterator();
		while (iterInt.hasNext()) {
			try {
				all = DAOFactory.getIGroupInfoDAOInstance().findAll(
						iterInt.next().intValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (all != null) {
				allGroup.addAll(all);
			}

		}
		List<StudentInfo> allStudent = new ArrayList<>();
		StudentInfo student = null;
		Iterator<GroupInfo> iterGroup = allGroup.iterator();
		while (iterGroup.hasNext()) {
			try {
				student = DAOFactory.getIStudentInfoDAOInstance().findById(
						iterGroup.next().getStudentid());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(student!=null){
				allStudent.add(student);
			}
			
		}
		request.setAttribute("allStudent", allStudent);
		RequestDispatcher rd = request.getRequestDispatcher("table.jsp");
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
