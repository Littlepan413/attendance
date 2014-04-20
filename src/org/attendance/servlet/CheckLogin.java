package org.attendance.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hyit.zyy.dao.factory.DAOFactory;
import cn.hyit.zyy.vo.LoginLog;
import cn.hyit.zyy.vo.SubjectInfo;
import cn.hyit.zyy.vo.TeacherInfo;

/**
 * Servlet implementation class CheckLogin
 */
@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckLogin() {
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
		String account = request.getParameter("username");
		String password = request.getParameter("password");
		Calendar cld = Calendar.getInstance();
		int year = cld.get(Calendar.YEAR);
		int month = cld.get(Calendar.MONTH) + 1; // 注意月份从0开始，所以此处要+1
		int day = cld.get(Calendar.DATE);
		int hour = cld.get(Calendar.HOUR_OF_DAY);
		int minute = cld.get(Calendar.MINUTE);
		int second = cld.get(Calendar.SECOND);
		String time = hour + ":" + minute + ":" + second;
		String date = year + "-" + month + "-" + day;
		String curTime = year + "年" + month + "月" + day + "日" + hour + "时"
				+ minute + "分" + second + "秒";
		System.out.println(curTime);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date datetime = null;
		try {
			datetime = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		TeacherInfo teacher = null;
		RequestDispatcher rd = null;
		LoginLog log = new LoginLog();
		try {
			teacher = DAOFactory.getITeacherInfoDAOInstance().findByUser(
					account, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (teacher != null) {
			// 记录登陆日志
			log.setTeacherid(teacher.getTeacherid());
			log.setLoginip(request.getRemoteAddr());
			log.setTime(java.sql.Time.valueOf(time));
			log.setDate(new Date(datetime.getTime()));
			try {
				DAOFactory.getILoginLogDAOInstance().doCreate(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 读取教师所教授课程的列表
			List<Integer> allSubjectid = null;
			Iterator<Integer> iterSubid = null;
			SubjectInfo subject = null;
			List<String> allSubjectName = new ArrayList<>();
			try {
				allSubjectid = DAOFactory.getICourseInfoDAOInstance().findById(
						teacher.getTeacherid());
				iterSubid = allSubjectid.iterator();
				while (iterSubid.hasNext()) {
					subject = DAOFactory.getISubjectInfoDAOInstance().findById(
							iterSubid.next().intValue());
					allSubjectName.add(subject.getSubjectname());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 保存登陆人员的信息到session
			request.getSession().setAttribute("uname", teacher.getName());
			request.getSession().setAttribute("urank", teacher.getRank());
			request.getSession().setAttribute("uid", teacher.getTeacherid());
			request.getSession().setAttribute("allSubjectid", allSubjectid);
			request.getSession().setAttribute("allSubjectName", allSubjectName);
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("login_fail.jsp");
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
