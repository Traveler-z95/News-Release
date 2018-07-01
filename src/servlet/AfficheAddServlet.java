package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import been.Blog;

/**
 * Servlet implementation class AfficheAddServlet
 */
@WebServlet(description = "AfficheAddServlet", urlPatterns = { "/AfficheAddServlet" })
public class AfficheAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficheAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement ps = null;
		Blog blog = new Blog();
		blog.setTitle(request.getParameter("title"));
		blog.setContent(request.getParameter("content"));
		blog.setTime(request.getParameter("time"));

		String sql = "insert into affiche(TITLE_,CONTENT_,TIME_) values(?,?,?)";
		try {
			conn = util.SQLServerConnection.getConnrction(); // �������ݿ�
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher d = request.getRequestDispatcher("AfficheListServlet");
			d.forward(request, response);
		}
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, blog.getTitle());
			ps.setString(2, blog.getContent());
			ps.setString(3, blog.getTime());
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		RequestDispatcher d = request.getRequestDispatcher("AfficheListServlet");
		d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
