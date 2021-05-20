package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.AuthorVo;
import bookshop.vo.BookVo;

public class BookDao {
	
	public Boolean insert(BookVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			// 1. JDBC Driver 로딩

			System.out.println("ok");

			// 2. 연결 하기
			conn = getConnection();

			String sql = "insert into book values (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(Binding)
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getStatus());
			pstmt.setLong(3, vo.getAuthorNo());

			
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
	

	public Boolean update(Long no, String status) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			// 1. JDBC Driver 로딩
			conn = getConnection();
			String sql = "update book set status=? where no = ?";
			
			
			// 3. Statement 생성
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(Binding)
			pstmt.setString(1,status);
			pstmt.setLong(2,no);
			
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
	
	public List<BookVo> findAll() {
		
		List<BookVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			String sql = "select no, title, status, author_no from book";

			// 3. sql문 준비
			pstmt = conn.prepareStatement(sql);

			// 4. SQL문을 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			while (rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				String status = rs.getString(3);
				int authorNo = rs.getInt(4);
				
				AuthorDao authorDao = new AuthorDao();
				AuthorVo vo = authorDao.findById(authorNo);

				BookVo bo = new BookVo();
				bo.setNo(no);
				bo.setTitle(title);
				bo.setStatus(status);
				bo.setAuthorName(vo.getName());


				result.add(bo);
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
			String url = "jdbc:mysql://192.168.254.35:3307/webdb?charset=utf-8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
		return conn;
	}




}
