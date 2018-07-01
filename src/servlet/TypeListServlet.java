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
		// 定义四个分页会用到的变量
		int pageSize = 5;
		int pageNow = 1; // 默认显示第一页
		int rowCount = 0; // 该值从数据库中查询
		int pageCount = 0; // 该值是通过pageSize和rowCount

		// 接受用户希望显示的页数（pageNow）
		String pageNow1 = request.getParameter("pageNow");
		if (pageNow1 != null) {
			// 接收到了pageNow
			pageNow = Integer.parseInt(pageNow1);
		}

		try {
			conn = SQLServerConnection.getConnrction(); // 连接数据库
			String sql1 = "select count(ID_) from type"; // 执行查询操作
			ps1 = conn.prepareStatement(sql1);
			rs1 = ps1.executeQuery();

			if (rs1.next()) {
				rowCount = rs1.getInt(1); // 获取变量总数
			}

			// 计算pageCount
			if (rowCount % pageSize == 0) {
				pageCount = rowCount / pageSize;
			} else {
				pageCount = (rowCount / pageSize) + 1;
			}

			// 查询出需要显示的记录
			String sql2 = "select top " + pageSize + " * from type where ID_ not in(select top "
					+ (pageSize * (pageNow - 1)) + " ID_ from type order by id_ desc)order by id_ desc"; // 执行查询操作
			ps2 = conn.prepareStatement(sql2);
			rs2 = ps2.executeQuery();

			ArrayList<Blog> list = new ArrayList<Blog>();
			while (rs2.next()) { // 循环显示数据表中的数据
				blog = new Blog();
				blog.setId(rs2.getInt("ID_"));
				blog.setName(rs2.getString("NAME_"));
				blog.setDescription(rs2.getString("DESCRIPTION_"));
				list.add(blog);
			}
			request.setAttribute("list", list); // 传输list的值
			request.setAttribute("pageNow", pageNow + ""); // 传输pageNow的值
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
