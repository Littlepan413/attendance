package org.attendance.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hyit.zyy.dao.factory.DAOFactory;
import cn.hyit.zyy.vo.LoginLog;
import cn.hyit.zyy.vo.TeacherInfo;

/**
 * Servlet implementation class loginServlet
 * 
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("account:" + username);
		System.out.println("passwd:" + password);
		Calendar cld = Calendar.getInstance();
		int year = cld.get(Calendar.YEAR);
		int month = cld.get(Calendar.MONTH) + 1;
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
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		TeacherInfo teacher = null;
		ServletOutputStream os = response.getOutputStream();
		LoginLog log = new LoginLog();
		try {
			teacher = DAOFactory.getITeacherInfoDAOInstance().findByUser(
					username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		if (teacher != null) {
			log.setTeacherid(teacher.getTeacherid());
			log.setLoginip(request.getRemoteAddr());
			log.setTime(java.sql.Time.valueOf(time));
			log.setDate(new Date(datetime.getTime()));
			try {
				DAOFactory.getILoginLogDAOInstance().doCreate(log);
			} catch (Exception e) {
				e.printStackTrace();
			}
			os.println(teacher.getTeacherid());

		} else {
			os.println("ERROR");
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
