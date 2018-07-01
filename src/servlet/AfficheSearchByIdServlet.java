package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import been.Blog;
import util.SQLServerConnection;

/**
 * Servlet implementation class AfficheSearchByIdServlet
 */
@WebServlet(description = "AfficheSearchByIdServlet", urlPatterns = { "/AfficheSearchByIdServlet" })
public class AfficheSearchByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AfficheSearchByIdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取序号ID
		String id = request.getParameter("id");
		Blog blog = null; // 定义属性
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 查询news中信息
		try {
			Connection conn = SQLServerConnection.getConnrction(); // 连接数据库
			String sql = "select * from affiche where ID_=" + id; // 查询操作
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) { // 循环显示数据表中数据
				blog = new Blog();
				blog.setId(rs.getInt("ID_"));
				blog.setTitle(rs.getString("TITLE_"));
				blog.setContent(rs.getString("CONTENT_"));
				blog.setTime(rs.getString("TIME_"));

			}
			request.setAttribute("blog", blog); // 传输blog的值
			rs.close(); // 关闭连接
			ps.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher("afficheupdate.jsp").forward(request, response);
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
