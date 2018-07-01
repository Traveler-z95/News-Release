package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLServerConnection {
	private static String url = "jdbc:sqlserver://localhost:1433; DatabaseName=study";
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static Connection getConnrction() throws Exception {
		Class.forName(driver); // �������ݿ�����
		return DriverManager.getConnection(url, "sa", "sd951208");
	}

	public static void main(String[] args) {
		try {
			Connection conn = getConnrction(); // �������ݿ�����
			PreparedStatement pre = conn.prepareStatement("select * from article"); // ִ�в�ѯ���
			ResultSet rs = pre.executeQuery();
			while (rs.next()) { // ѭ����ʾ���ݱ�������
				rs.getInt("ID_");
				rs.getString("TITLE_");
				rs.getString("CONTENT_");
				rs.getString("TIME_");
				rs.getInt("NUMBER_");
			}
			rs.close(); // �ر�����
			pre.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
