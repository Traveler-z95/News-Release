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
import util.SQLServerConnection;

/**
 * Servlet implementation class CommentAddServlet
 */
@WebServlet(description = "CommentAddServlet", urlPatterns = { "/CommentAddServlet" })
public class CommentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentAddServlet() {
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

		String Article_id = request.getParameter("article_id");
		int article_id = Integer.parseInt(Article_id);
		System.out.println(article_id);
		blog.setArticle_id(article_id);
		blog.setContent(request.getParameter("content"));
		blog.setTime(request.getParameter("time"));

		String sql = "insert into comment(ARTICLE_ID,CONTENT_,TIME_) values(?,?,?)";
		try {
			conn = SQLServerConnection.getConnrction(); // 连接数据库
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher d = request.getRequestDispatcher("CommentListServlet");
			d.forward(request, response);
		}
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, blog.getArticle_id());
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
		RequestDispatcher d = request.getRequestDispatcher("CommentListServlet");
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
