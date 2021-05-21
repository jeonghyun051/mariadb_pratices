package com.douzone.bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.douzone.bookmall.vo.OrderVo;

public class OrderDao {
	public Boolean insert(OrderVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {


			// 2. 연결 하기
			conn = getConnection();

			String sql = "insert into orders values (null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(Binding)
			pstmt.setString(1, vo.getNumber());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getAddress());
			pstmt.setInt(4, vo.getMemberNo());

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

	public List<OrderVo> findAll() {

		List<OrderVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			String sql = "\r\n"
					+ "select o.number, m.name, m.email, o.price, o.address\r\n"
					+ "from members m, orders o\r\n"
					+ "where m.no = o.member_no";

			// 3. sql문 준비
			pstmt = conn.prepareStatement(sql);
	
			// 4. SQL문을 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			while (rs.next()) {
				
				String number = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				int price = rs.getInt(4);
				String address = rs.getString(5);
				
				OrderVo vo = new OrderVo();
				
				vo.setNumber(number);
				vo.setName(name);
				vo.setEmail(email);
				vo.setPrice(price);
				vo.setAddress(address);
				
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
