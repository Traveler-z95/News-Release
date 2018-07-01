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
import util.SQLServerConnection;

/**
 * Servlet implementation class TypeUpdateServlet
 */
@WebServlet(description = "TypeUpdateServlet", urlPatterns = { "/TypeUpdateServlet" })
public class TypeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TypeUpdateServlet() {
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
		String Name = request.getParameter("name");
		String Description = request.getParameter("description");

		// 封装到对象中去
		Blog blog = new Blog();
		blog.setId(id);
		blog.setName(Name);
		blog.setDescription(Description);

		// 调用模型层修改方法
		String sql1 = "update type set NAME_=?,DESCRIPTION_=? where ID_=?"; // 更新操作
		PreparedStatement ps1 = null;
		try {
			conn = SQLServerConnection.getConnrction(); // 连接数据库
		} catch (Exception e1) {
			e1.printStackTrace();
			request.getRequestDispatcher("TypeListServlet").forward(request, response);
			return;
		}

		try {
			conn.setAutoCommit(false);
			ps1 = conn.prepareStatement(sql1);
			ps1.setInt(3, blog.getId());
			ps1.setString(1, blog.getName());
			ps1.setString(2, blog.getDescription());
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
		request.getRequestDispatcher("TypeListServlet").forward(request, response);
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
