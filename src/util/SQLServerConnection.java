package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLServerConnection {
	private static String url = "jdbc:sqlserver://localhost:1433; DatabaseName=study";
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static Connection getConnrction() throws Exception {
		Class.forName(driver); // 加载数据库驱动
		return DriverManager.getConnection(url, "sa", "sd951208");
	}

	public static void main(String[] args) {
		try {
			Connection conn = getConnrction(); // 创建数据库连接
			PreparedStatement pre = conn.prepareStatement("select * from article"); // 执行查询语句
			ResultSet rs = pre.executeQuery();
			while (rs.next()) { // 循环显示数据表中数据
				rs.getInt("ID_");
				rs.getString("TITLE_");
				rs.getString("CONTENT_");
				rs.getString("TIME_");
				rs.getInt("NUMBER_");
			}
			rs.close(); // 关闭连接
			pre.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
