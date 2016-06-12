package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			/*Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
						sc.getInitParameter("url"),
						sc.getInitParameter("username"),
						sc.getInitParameter("password")); */
			//Connection conn = (Connection)sc.getAttribute("conn");
		
			//MemberDao memberDao = new MemberDao();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			//memberDao.setConnection(conn);
			
			//request에 회원목록 데이터 보관한다.
			request.setAttribute("members", memberDao.selectList());
			
			request.setAttribute("viewUrl", "/member/MemberList.jsp");
			/*response.setContentType("text/html; charset=UTF-8");
			//JSP로 출력을 위임한다.
			RequestDispatcher rd = request.getRequestDispatcher(
					"/member/MemberList.jsp");
			rd.include(request, response);*/
			
		} catch (Exception e) {
			throw new ServletException(e);
			//request.setAttribute("error", e);
			//RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			//rd.forward(request, response);
			
		}

	}
}
