package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import been.Blog;
import util.SQLServerConnection;

/**
 * Servlet implementation class TypeListServlet
 */
@WebServlet(description = "TypeListServlet", urlPatterns = { "/TypeListServlet" })
public class TypeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TypeListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Blog blog = null;
		// �����ĸ���ҳ���õ��ı���
		int pageSize = 5;
		int pageNow = 1; // Ĭ����ʾ��һҳ
		int rowCount = 0; // ��ֵ�����ݿ��в�ѯ
		int pageCount = 0; // ��ֵ��ͨ��pageSize��rowCount

		// �����û�ϣ����ʾ��ҳ����pageNow��
		String pageNow1 = request.getParameter("pageNow");
		if (pageNow1 != null) {
			// ���յ���pageNow
			pageNow = Integer.parseInt(pageNow1);
		}

		try {
			conn = SQLServerConnection.getConnrction(); // �������ݿ�
			String sql1 = "select count(ID_) from type"; // ִ�в�ѯ����
			ps1 = conn.prepareStatement(sql1);
			rs1 = ps1.executeQuery();

			if (rs1.next()) {
				rowCount = rs1.getInt(1); // ��ȡ��������
			}

			// ����pageCount
			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = (rowCount / pageSize) + 1;
			}

			// ��ѯ����Ҫ��ʾ�ļ�¼
			String sql2 = "select top " + pageSize + " * from type where ID_ not in(select top "
					+ (pageSize * (pageNow - 1)) + " ID_ from type order by id_ desc)order by id_ desc"; // ִ�в�ѯ����
			ps2 = conn.prepareStatement(sql2);
			rs2 = ps2.executeQuery();

			ArrayList<Blog> list = new ArrayList<Blog>();
			while (rs2.next()) { // ѭ����ʾ���ݱ��е�����
				blog = new Blog();
				blog.setId(rs2.getInt("ID_"));
				blog.setName(rs2.getString("NAME_"));
				blog.setDescription(rs2.getString("DESCRIPTION_"));
				list.add(blog);
			}
			request.setAttribute("list", list); // ����list��ֵ
			request.setAttribute("pageNow", pageNow + ""); // ����pageNow��ֵ
			request.setAttribute("pageCount", pageCount + "");
			RequestDispatcher d = request.getRequestDispatcher("typelist.jsp");
			d.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
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
