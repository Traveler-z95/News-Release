package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import been.Blog;
import util.SQLServerConnection;

/**
 * Servlet implementation class ArticleDetailsSearchServlet
 */
@WebServlet(description = "ArticleDetailsSearchServlet", urlPatterns = { "/ArticleDetailsSearchServlet" })
public class ArticleDetailsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleDetailsSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		Blog blog = null; // 定义属性
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		// 查询文章详细信息
		try {
			Connection conn = SQLServerConnection.getConnrction(); // 连接数据库
			String sql = "select * from article where ID_=" + id; // 查询操作
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) { // 循环显示数据表中数据
				blog = new Blog();
				blog.setId(rs.getInt("ID_"));
				blog.setType_id(rs.getInt("TYPE_ID"));
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
		
		//查询评论详情
		try {
			Connection conn = SQLServerConnection.getConnrction();	//连接数据库
			String sql2 = "select * from comment where ARTICLE_ID="+id;	//查询操作
			ps2 = conn.prepareStatement(sql2);
			rs2 = ps2.executeQuery();
			ArrayList<Blog> list2 = new ArrayList<Blog>();
			 while(rs2.next()) {			//循环显示表中数据
				 blog=new Blog();
				 blog.setId(rs2.getInt("ID_"));
				 blog.setArticle_id(rs2.getInt("ARTICLE_ID"));
				 blog.setContent(rs2.getString("CONTENT_"));
				 blog.setTime(rs2.getString("TIME_"));
				list2.add(blog);
			}
			request.setAttribute("list2",list2);	//传输list2的值
			rs2.close();							//关闭连接
			ps2.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		request.getRequestDispatcher("articledetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
