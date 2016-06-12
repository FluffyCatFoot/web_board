package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Connection conn = null;
		//Statement stmt = null;
		//ResultSet rs = null;
		try {
			ServletContext sc = this.getServletContext();
			/*Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
						sc.getInitParameter("url"),
						sc.getInitParameter("username"),
						sc.getInitParameter("password")); */
			//conn = (Connection)sc.getAttribute("conn");
			/*stmt = conn.createStatement();
			rs = stmt.executeQuery(
				"SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + 
				" WHERE MNO=" + request.getParameter("no"));	
			if(rs.next()){
			request.setAttribute("member",new Member().setNo(rs.getInt("MNO"))
										.setEmail(rs.getString("EMAIL"))
										.setName(rs.getString("MNAME"))
										.setCreatedDate(rs.getDate("CRE_DATE")));
			}else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			*/
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			//memberDao.setConnection(conn);

			request.setAttribute("member", memberDao.selectOne(Integer.parseInt(request.getParameter("no"))));
			request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			rd.forward(request, response);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><title>회원정보</title></head>");
			out.println("<body><h1>회원정보</h1>");
			out.println("<form action='update' method='post'>");
			out.println("번호: <input type='text' name='no' value='" +
				request.getParameter("no") + "' readonly><br>");
			out.println("이름: <input type='text' name='name'" +
				" value='" + rs.getString("MNAME")  + "'><br>");
			out.println("이메일: <input type='text' name='email'" +
				" value='" + rs.getString("EMAIL")  + "'><br>");
			out.println("가입일: " + rs.getDate("CRE_DATE") + "<br>");
			out.println("<input type='submit' value='저장'>");
			out.println("<input type='button' value='삭제' "
					+ "onclick='location.href=\"delete?no=" + 
					request.getParameter("no") + "\";'>");
			out.println("<input type='button' value='취소'" + 
				" onclick='location.href=\"list\"'>");
			out.println("</form>");
			out.println("</body></html>");
			*/
		} catch (Exception e) {
			throw new ServletException(e);
			/*RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			*/
			
		} //finally {
			//try {if (rs != null) rs.close();} catch(Exception e) {}
			//try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		//}
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
			
			/*request.setCharacterEncoding("UTF-8");
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
						sc.getInitParameter("url"),
						sc.getInitParameter("username"),
						sc.getInitParameter("password")); */
			//conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			//memberDao.setConnection(conn);
			
			Member member = (Member)request.getAttribute("member");
			memberDao.update(member);
			request.setAttribute("viewUrl", "redirect:list.do");
			/*memberDao.update(new Member().setEmail(request.getParameter("email"))
						.setName(request.getParameter("name"))
						.setNo(Integer.parseInt(request.getParameter("no")))
						);
			
			stmt = conn.prepareStatement(
					"UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()"
					+ " WHERE MNO=?");
			stmt.setString(1, request.getParameter("email"));
			stmt.setString(2, request.getParameter("name"));
			stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
			stmt.executeUpdate();
			
			response.sendRedirect("list");*/
			
		} catch (Exception e) {
			throw new ServletException(e);
			
		} //finally {
			//try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		//}
	}
}
