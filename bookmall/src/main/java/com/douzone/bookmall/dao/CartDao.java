package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CartVo;


public class CartDao {
	public Boolean insert(CartVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {

			// 2. 연결 하기
			conn = getConnection();

			String sql = "insert into cart values (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(Binding)
			pstmt.setInt(1, vo.getCount());
			pstmt.setInt(2, vo.getMemberNo());
			pstmt.setInt(3, vo.getBookNo());

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

	public List<CartVo> findAll(int no) {

		List<CartVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select b.name, c.count, b.price \r\n"
					+ "from cart c, book b\r\n"
					+ "where c.book_no = b.no and c.member_no = ?";

			// 3. sql문 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			// 4. SQL문을 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			while (rs.next()) {
				String name = rs.getString(1);
				int count = rs.getInt(2);
				int price = rs.getInt(3);
				
				CartVo vo = new CartVo();
				vo.setName(name);
				vo.setCount(count);
				vo.setPrice(price);
			
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
