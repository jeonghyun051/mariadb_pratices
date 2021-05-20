package bookshop.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bookshop.dao.AuthorDao;
import bookshop.vo.AuthorVo;

public class AuthorDaoTest {

	public static void main(String[] args) {

		// 1번
		// insertTest();

		// 2번
		// findAllTest();

		findById(2);
	}

	public static AuthorVo findById(int no) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			String sql = "select name from author where no = ?";

			// 3. sql문 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			// 4. SQL문을 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			while (rs.next()) {

				String name = rs.getString(1);

				System.out.println(name);
				AuthorVo vo = new AuthorVo();
				vo.setName(name);
			}
			//String name = rs.getString(1);
			
			//System.out.println(vo.getName());
			//return vo;

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
		return null;
	}

	private static void findAllTest() {
		List<AuthorVo> list = new AuthorDao().findAll();
		for (AuthorVo vo : list) {
			System.out.println(vo);
		}
	}

	public static void insertTest() {

		AuthorVo vo = null;
		vo = new AuthorVo();
		vo.setName("스테파니메이어");
		new AuthorDao().insert(vo);

		vo = new AuthorVo();
		vo.setName("조정래");
		new AuthorDao().insert(vo);

		vo = new AuthorVo();
		vo.setName("김동인");
		new AuthorDao().insert(vo);

		vo = new AuthorVo();
		vo.setName("김난도");
		new AuthorDao().insert(vo);

		vo = new AuthorVo();
		vo.setName("천상병");
		new AuthorDao().insert(vo);

		vo = new AuthorVo();
		vo.setName("조정래");
		new AuthorDao().insert(vo);

	}

	private static Connection getConnection() throws SQLException {
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