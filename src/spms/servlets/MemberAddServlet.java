package spms.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>회원 등록</title></head>");
		out.println("<body><h1>회원 등록</h1>");
		out.println("<form action='add' method='post'>");
		out.println("이름: <input type='text' name='name'><br>");
		out.println("이메일: <input type='text' name='email'><br>");
		out.println("암호: <input type='password' name='password'><br>");
		out.println("<input type='submit' value='추가'>");
		out.println("<input type='reset' value='취소'>");
		out.println("</form>");
		out.println("</body></html>");
	
		RequestDispatcher rd = request.getRequestDispatcher(
				"/member/MemberForm.jsp");
		rd.forward(request, response);*/
		request.setAttribute("viewUrl", "/member/MemberForm.jsp");
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// CharacterEncodingFilter에서 처리
		//request.setCharacterEncoding("UTF-8");
		
		//Connection conn = null;
		//PreparedStatement stmt = null;

		try {
			ServletContext sc = this.getServletContext();
			
			request.setCharacterEncoding("UTF-8");
			
			/*Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
						sc.getInitParameter("url"),
						sc.getInitParameter("username"),
						sc.getInitParameter("password")); */
			//Connection conn = (Connection)sc.getAttribute("conn");
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			//memberDao.setConnection(conn);
			
			Member member = (Member)request.getAttribute("member");
			
			memberDao.insert(member);
			/*memberDao.insert(new Member().setEmail(request.getParameter("email"))
					.setPassword(request.getParameter("password"))
					.setName(request.getParameter("name")));
			
			stmt = conn.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
					+ " VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("password"));
			stmt.setString(3, request.getParameter("name"));
			stmt.executeUpdate();
			
			response.sendRedirect("list");
			*/
			request.setAttribute("viewUrl", "redirect:list.do");
		} catch (Exception e) {
			throw new ServletException(e);
			/*e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			*/
		} //finally {
			//try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		//}

	}
}
