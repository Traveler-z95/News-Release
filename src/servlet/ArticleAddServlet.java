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
 * Servlet implementation class ArticleAddServlet
 */
@WebServlet(description = "ArticleAddServlet", urlPatterns = { "/ArticleAddServlet" })
public class ArticleAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleAddServlet() {
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

		String Type_id = request.getParameter("type_id");
		int type_id = Integer.parseInt(Type_id);
		System.out.println(type_id);
		blog.setType_id(type_id);
		blog.setTitle(request.getParameter("title"));
		blog.setContent(request.getParameter("content"));
		blog.setTime(request.getParameter("time"));
		
		String temp = blog.getContent();		//������ŵ�ת��
		if (temp != null) {
			temp = temp.replaceAll("<", "&lt;");
			temp = temp.replaceAll(">", "&gt;");
			temp = temp.replaceAll("&", "&amp;");
			temp = temp.replaceAll("��", "&quot;");
			temp = temp.replaceAll(" ", "&nbsp;");
			blog.setContent(temp);
		}

		String sql = "insert into article(TYPE_ID,TITLE_,CONTENT_,TIME_) values(?,?,?,?)";
		try {
			conn = SQLServerConnection.getConnrction(); // �������ݿ�
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher d = request.getRequestDispatcher("ArticleListServlet");
			d.forward(request, response);
		}
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, blog.getType_id());
			ps.setString(2, blog.getTitle());
			ps.setString(3, blog.getContent());
			ps.setString(4, blog.getTime());
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
		RequestDispatcher d = request.getRequestDispatcher("ArticleListServlet");
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
