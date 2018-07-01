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
 * Servlet implementation class TypeSearchServlet
 */
@WebServlet(description = "TypeSearchServlet", urlPatterns = { "/TypeSearchServlet" })
public class TypeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String sql1 = "select * from type where 1=1 ";	//��ѯ���
		// ��ȡ���ID
		String Id = request.getParameter("Id");
		if (Id != null && !Id.equals("")) {
			sql1 += " and ID_=?";
		}

		// ��ȡ�ύ�Ĺؼ���
		String name = request.getParameter("name");
		if (name != null && !name.equals("")) {
			sql1 += " and NAME_ like ? ";
		}


		Connection conn = null;		//��������
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;

		try {
			conn = SQLServerConnection.getConnrction();
			ps1 = conn.prepareStatement(sql1);
			System.out.println(sql1);
			int i = 1;
			if (Id != null && !Id.equals("")) {	//ΪId��ֵ���ǲ�ѯ���ݺ������ʾ��ѯҪ��
				ps1.setString(i, Id);
				i++;
				System.out.println("Id"+Id);
			}
			if(name != null && !name.equals("")){	//Ϊname��ֵ
				ps1.setString(i, "%"+name+"%");
				i++;
				System.out.println("name"+name);
			}

			
			rs1 = ps1.executeQuery();
			ArrayList<Blog> list = new ArrayList<Blog>();
			while (rs1.next()) {		//ѭ����ʾ���ݱ�������
				Blog blog = new Blog();
				blog.setId(rs1.getInt("ID_"));
				blog.setName(rs1.getString("NAME_"));
				blog.setDescription(rs1.getString("DESCRIPTION_"));
				list.add(blog);
			}
			request.setAttribute("list", list);
			request.setAttribute("id", Id);
			request.setAttribute("name", name);
			request.getRequestDispatcher("typelist.jsp").forward(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
