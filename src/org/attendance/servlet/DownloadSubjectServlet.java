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
import cn.hyit.zyy.vo.SubjectInfo;

/**
 * Servlet implementation class DownloadSubjectServlet
 */
@WebServlet("/DownloadSubjectServlet")
public class DownloadSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadSubjectServlet() {
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
		String id = request.getParameter("teacherid");
		int teacherid = Integer.parseInt(id);
		SubjectInfo sub = null;
		PrintWriter out = null;
		int subid = 0;
		List<SubjectInfo> subjectAll = new ArrayList<>();
		try {
			List<Integer> subjectid = DAOFactory.getICourseInfoDAOInstance()
					.findById(teacherid);
			Iterator<Integer> iter = subjectid.iterator();
			while (iter.hasNext()) {
				subid = iter.next().intValue();
				sub = DAOFactory.getISubjectInfoDAOInstance().findById(subid);
				subjectAll.add(sub); // 获得了该id下所有的课程对象
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator<SubjectInfo> iterSub = subjectAll.iterator();
		out = response.getWriter();
		while (iterSub.hasNext()) {
			sub = iterSub.next();
			out.println(sub.getSubjectid() + "," + sub.getSubjectname());
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
