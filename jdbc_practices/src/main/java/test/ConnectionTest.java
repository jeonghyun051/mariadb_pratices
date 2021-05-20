package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {
		
		Connection conn = null;
		
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("ok");
			
			// 2. 연결 하기
			String url = "jdbc:mysql://192.168.254.35:3307/webdb?charset=utf-8";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			
			// 3. 연결 성공
			System.out.println("ok" + conn);

		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}catch (SQLException e) {
			System.out.println("error:" + e);
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
