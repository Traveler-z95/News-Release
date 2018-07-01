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
		Blog blog = null; // ��������
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		// ��ѯ������ϸ��Ϣ
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
		
		//��ѯ��������
		try {
			Connection conn = SQLServerConnection.getConnrction();	//�������ݿ�
			String sql2 = "select * from comment where ARTICLE_ID="+id;	//��ѯ����
			ps2 = conn.prepareStatement(sql2);
			rs2 = ps2.executeQuery();
			ArrayList<Blog> list2 = new ArrayList<Blog>();
			 while(rs2.next()) {			//ѭ����ʾ��������
				 blog=new Blog();
				 blog.setId(rs2.getInt("ID_"));
				 blog.setArticle_id(rs2.getInt("ARTICLE_ID"));
				 blog.setContent(rs2.getString("CONTENT_"));
				 blog.setTime(rs2.getString("TIME_"));
				list2.add(blog);
			}
			request.setAttribute("list2",list2);	//����list2��ֵ
			rs2.close();							//�ر�����
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
