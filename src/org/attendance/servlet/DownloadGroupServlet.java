package org.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/*import java.util.Enumeration;
import java.util.HashMap;*/
import java.util.Iterator;
import java.util.List;
/*//import java.util.Map;
*/
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hyit.zyy.dao.factory.DAOFactory;
import cn.hyit.zyy.vo.GroupInfo;

/**
 * Servlet implementation class DownloadGroupServlet
 */
@WebServlet("/DownloadGroupServlet")
public class DownloadGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadGroupServlet() {
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
		String id1 = request.getParameter("subjectid");
		String id2 = request.getParameter("teacherid");
		/*Map map = new HashMap();  
	     Enumeration paramNames = request.getParameterNames();  
	    while (paramNames.hasMoreElements()) {  
	      String paramName = (String) paramNames.nextElement();  
	  
	      String[] paramValues = request.getParameterValues(paramName);  
	      if (paramValues.length == 1) {  
	        String paramValue = paramValues[0];  
	        if (paramValue.length() != 0) {  
	          System.out.println("������" + paramName + "=" + paramValue);  
	          map.put(paramName, paramValue);  
	        }  
	      }  
	    }  */
		int subjectid = Integer.parseInt(id1);
		int teacherid = Integer.parseInt(id2);
		List<Integer> allGroupid = null;
		List<String> allGroupName = new ArrayList<>();
		List<GroupInfo> oneGroup = null;
		String groupName = null;
		Iterator<Integer> iterInt =null;
		Iterator<String> iterString = null;
		Iterator<GroupInfo> iterGroup = null;
		PrintWriter out = null;
		/**
		 * try���л�ȡ�˸ý�ʦ��Ӧ�γ������а༶�����ֺͶ�Ӧ��ID
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * �����ǶԻ�ȡ�����ݵķ���
		 */
		out = response.getWriter();
		iterInt = allGroupid.iterator();
		iterString = allGroupName.iterator();
		while(iterInt.hasNext() && iterString.hasNext()){
			out.println(iterInt.next().intValue()+","+iterString.next());
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
