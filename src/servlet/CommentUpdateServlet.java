package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import been.Blog;

/**
 * Servlet implementation class CommentUpdateServlet
 */
@WebServlet(description = "CommentUpdateServlet", urlPatterns = { "/CommentUpdateServlet" })
public class CommentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentUpdateServlet() {
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
		String Id = request.getParameter("id");
		int id = Integer.parseInt(Id);
		System.out.println(id);
		String Article_id = request.getParameter("article_id");
		int article_id = Integer.parseInt(Article_id);
		System.out.println(article_id);
		String Content = request.getParameter("content");
		String Time = request.getParameter("time");

		// 封装到对象中去
		Blog blog = new Blog();
		blog.setId(id);
		blog.setArticle_id(article_id);
		blog.setContent(Content);
		blog.setTime(Time);

		// 调用模型层修改方法
		String sql1 = "update comment set ARTICLE_ID=?,CONTENT_=?,TIME_=? where ID_=?"; // 更新操作
		PreparedStatement ps1 = null;
		try {
			conn = util.SQLServerConnection.getConnrction(); // 连接数据库
		} catch (Exception e1) {
			e1.printStackTrace();
			request.getRequestDispatcher("CommentListServlet").forward(request, response);
			return;
		}

		try {
			conn.setAutoCommit(false);
			ps1 = conn.prepareStatement(sql1);
			ps1.setInt(4, blog.getId());
			ps1.setInt(1, blog.getArticle_id());
			ps1.setString(2, blog.getContent());
			ps1.setString(3, blog.getTime());
			ps1.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("CommentListServlet").forward(request, response);
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
