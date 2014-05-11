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

/**
 * Servlet implementation class querySubjectServlet
 */
@WebServlet("/querySubjectServlet")
public class QueryGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryGroupServlet() {
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
		String id = request.getParameter("subjectid");
		int subjectid = Integer.parseInt(id);
		int teacherid = (int) request.getSession().getAttribute("uid");
		List<Integer> allGroupid = null;
		List<String> allGroupName = new ArrayList<>();
		List<GroupInfo> oneGroup = null;
		String groupName = null;
		Iterator<Integer> iterInt =null;
		Iterator<GroupInfo> iterGroup = null;
		/**
		 * 获取该教师对应课程下所有班级的名字和对应的ID
		 */
		try {
			allGroupid = DAOFactory.getICourseInfoDAOInstance().findAllGroup(
					teacherid, subjectid);
			iterInt = allGroupid.iterator();
			while (iterInt.hasNext()) {
				oneGroup = DAOFactory.getIGroupInfoDAOInstance().findAll(
						iterInt.next().intValue());
				iterGroup = oneGroup.iterator();
				if(iterGroup.hasNext()){
					groupName = iterGroup.next().getGroupname();
					allGroupName.add(groupName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * 以下是对获取的数据的返回,使用服务器跳转方式来转发数据
		 * 待完成
		 */
		request.setAttribute("allGroupid", allGroupid);
		request.setAttribute("allGroupName", allGroupName);
		RequestDispatcher rd = request.getRequestDispatcher("queryStudent");
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
