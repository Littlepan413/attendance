package org.attendance.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hyit.zyy.dao.factory.DAOFactory;
import cn.hyit.zyy.vo.StudentInfo;

/**
 * Servlet implementation class ImportStudentInfoServlet
 */
@WebServlet("/ImportStudentInfoServlet")
public class ImportStudentInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportStudentInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String member = request.getParameter("member");
		String[] student = member.split("	|\n");
		StudentInfo stuInfo = new StudentInfo();
		for(int i=0;i<student.length;i+=3){
			stuInfo.setClassname(student[i]);
			stuInfo.setStudentid(Integer.parseInt(student[i+1]));
			stuInfo.setName(student[i+2]);
			stuInfo.setCardid("00:00:00:00");
			try {
				DAOFactory.getIStudentInfoDAOInstance().doCreate(stuInfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
