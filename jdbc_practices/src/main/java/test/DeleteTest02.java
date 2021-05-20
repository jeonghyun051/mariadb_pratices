package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTest02 {
	public static void main(String[] args) {
		Boolean result = delete(5L);
		System.out.println(result ? "성공" : "실패");
		
	}
	
	public static boolean delete(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("ok");

			// 2. 연결 하기
			String url = "jdbc:mysql://192.168.254.35:3307/employees?charset=utf-8";
			conn = DriverManager.getConnection(url, "hr", "hr");
			//String sql = "delete from dept where no =?";
			String sql =
					"delete" +
				    "  from dept" + 
					" where no = ?";
			
			// 3. Statement 생성
			pstmt = conn.prepareStatement(sql);

			//4. 바인딩(binding)
			pstmt.setLong(1, no);
			
			// 5. SQL문을 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;


		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return result;
	}
}
