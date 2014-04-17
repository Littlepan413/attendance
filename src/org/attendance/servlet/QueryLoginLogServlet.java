package org.attendance.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hyit.zyy.dao.factory.DAOFactory;
import cn.hyit.zyy.vo.LoginLog;

/**
 * Servlet implementation class QueryLoginLogServlet
 */
@WebServlet("/QueryLoginLogServlet")
public class QueryLoginLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryLoginLogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String Path = "loginlog.jsp";
		int teacherid = (int) request.getSession().getAttribute("uid");
		List<LoginLog> alllog = new ArrayList<LoginLog>();
		try {
			alllog = DAOFactory.getILoginLogDAOInstance().findAll(teacherid);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("在查询日志时发现异常，异常等待处理");
		}
		request.setAttribute("loginlog", alllog);
		request.getRequestDispatcher(Path).forward(request, response);
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
