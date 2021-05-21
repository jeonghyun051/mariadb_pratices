package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.CategoryVo;
import com.douzone.bookmall.vo.MemberVo;

public class CategoryDao {
	
	public Boolean insert(CategoryVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {

			// 2. 연결 하기
			conn = getConnection();

			String sql = "insert into category values (null, ?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(Binding)
			pstmt.setString(1, vo.getCategory());
			
			// 5. SQL문을 실행
			int count = pstmt.executeUpdate();
			result = count == 1;

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
	
	public List<CategoryVo> findAll() {

		List<CategoryVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			String sql = "select no, category from category";

			// 3. sql문 준비
			pstmt = conn.prepareStatement(sql);

			// 4. SQL문을 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			while (rs.next()) {
				int no = rs.getInt(1);
				String category = rs.getString(2);
				
				CategoryVo vo = new CategoryVo();
				
				vo.setNo(no);
				vo.setCategory(category);
	
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {

				if (rs != null) {
					rs.close();
				}
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

	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {

			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.254.35:3307/bookmall?charset=utf-8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
		return conn;
	}

}
