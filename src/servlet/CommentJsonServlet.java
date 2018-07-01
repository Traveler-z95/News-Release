package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import been.Blog;
import net.sf.json.util.JSONStringer;

/**
 * Servlet implementation class CommentJsonServlet
 */
@WebServlet(description = "CommentJsonServlet", urlPatterns = { "/CommentJsonServlet" })
public class CommentJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentJsonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null; // 定义属性
		PreparedStatement ps = null;
		ResultSet rs = null;
		Blog blog = null;
		try {
			conn = util.SQLServerConnection.getConnrction(); // 连接数据库
			String sql = "select * from comment "; // 查询操作
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ArrayList<Blog> list2 = new ArrayList<Blog>();
			while (rs.next()) { // 循环显示数据表中数据
				blog = new Blog();
				blog.setId(rs.getInt("ID_"));
				blog.setArticle_id(rs.getInt("ARTICLE_ID"));
				blog.setContent(rs.getString("CONTENT_"));
				blog.setTime(rs.getString("TIME_"));
				list2.add(blog);
			}
			request.setAttribute("list2", list2);

			Iterator<Blog> it = list2.iterator();
			JSONStringer jsonStringer = new JSONStringer();
			jsonStringer.object();
			jsonStringer.key("blog");
			jsonStringer.array();

			for (int i = 0; i < list2.size(); i++) { // 将数据转换成json格式
				blog = list2.get(i);

				jsonStringer.object();
				jsonStringer.key("id").value(blog.getId());
				jsonStringer.key("article_id").value(blog.getArticle_id());
				jsonStringer.key("content").value(blog.getContent());
				jsonStringer.key("time").value(blog.getTime());
				jsonStringer.endObject();
			}
			jsonStringer.endArray();
			jsonStringer.endObject();
			String s = jsonStringer.toString();
			s = s.substring(8,s.length()-1);
			System.out.println(s);
			response.getOutputStream().write(s.getBytes("UTF-8")); // 输出json
			response.setContentType("text/json; charset=UTF-8");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
