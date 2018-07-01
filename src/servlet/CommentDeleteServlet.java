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

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet(description = "CommentDeleteServlet", urlPatterns = { "/CommentDeleteServlet" })
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Id = request.getParameter("id");
		int id = Integer.parseInt(Id);
		System.out.println(id);
		String sql1 = "delete from comment where ID_=?"; // 删除操作
		PreparedStatement ps1 = null;
		Connection conn;
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
			ps1.setInt(1, id);
			ps1.executeUpdate();

			conn.commit();
		} catch (Exception e) {

			e.printStackTrace();
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
