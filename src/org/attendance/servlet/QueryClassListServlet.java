package org.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hyit.zyy.dao.factory.DAOFactory;

/**
 * Servlet implementation class QueryClassListServlet
 */
@WebServlet("/QueryClassListServlet")
public class QueryClassListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryClassListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String PATH = "addclass.jsp";
		List<String> classList = null;
		PrintWriter out = null;
		try {
			classList = DAOFactory.getIStudentInfoDAOInstance().findClassName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		out = response.getWriter();
		if(classList!=null){
			request.setAttribute("classlist", classList);
			request.getRequestDispatcher(PATH).forward(request, response);
		}else{
			out.println("Error,管理员未添加学生名单，请联系管理员！");
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
