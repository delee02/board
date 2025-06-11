package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public Connection getConnection() throws ClassNotFoundException {
		Connection conn = null;
		
		try {
			String url="jdbc:mysql://localhost:3306/jspbookdb";
			String user="root";
			String pw="1234";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(url,user,pw);
			System.out.println("연결 성공");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("연결 실패");
		}
		return conn;
	}
}
