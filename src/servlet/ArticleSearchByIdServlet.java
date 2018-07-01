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
 * Servlet implementation class ArticleSearchByIdServlet
 */
@WebServlet(description = "ArticleSearchByIdServlet", urlPatterns = { "/ArticleSearchByIdServlet" })
public class ArticleSearchByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleSearchByIdServlet() {
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
		String id = request.getParameter("id");
		Blog blog = null; // ��������
		PreparedStatement ps = null;
		ResultSet rs = null;
		// ��ѯnews����Ϣ
		try {
			Connection conn = SQLServerConnection.getConnrction(); // �������ݿ�
			String sql = "select * from article where ID_=" + id; // ��ѯ����
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) { // ѭ����ʾ���ݱ�������
				blog = new Blog();
				blog.setId(rs.getInt("ID_"));
				blog.setType_id(rs.getInt("TYPE_ID"));
				blog.setTitle(rs.getString("TITLE_"));
				blog.setContent(rs.getString("CONTENT_"));
				blog.setTime(rs.getString("TIME_"));

			}
			request.setAttribute("blog", blog); // ����blog��ֵ
			rs.close(); // �ر�����
			ps.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		request.getRequestDispatcher("articleupdate.jsp").forward(request, response);
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
