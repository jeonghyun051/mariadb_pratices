package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
	

	public List<EmployeeVo> findeBySalary(int minSalary, int maxSalary) {
		List<EmployeeVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver 로딩
			conn = getConnection();
			
			System.out.println("ok");

			// 2. 연결 하기
			
			String sql = "select e.emp_no, e.first_name, e.last_name, s.salary\r\n"
					+ "from employees e, salaries s\r\n"
					+ "where e.emp_no = s.emp_no and s.to_date='9999-01-01' \r\n"
					+ "	and s.salary between ? and ? \r\n"
					+ "    order by s.salary asc";

			// 3. sql문 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, minSalary);
			pstmt.setInt(2, maxSalary);

			// 4. SQL문을 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			while (rs.next()) {
				Long empNo = rs.getLong(1);
				String firstNmae = rs.getString(2);
				String lastName = rs.getString(3);
				int salary = rs.getInt(4);

				EmployeeVo vo = new EmployeeVo();
				vo.setEmpNo(empNo);
				vo.setFirstName(firstNmae);
				vo.setLastName(lastName);
				vo.setSalary(salary);
				
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

	public List<EmployeeVo> findByName(String name) {
		List<EmployeeVo> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC Driver 로딩

			System.out.println("ok");

			// 2. 연결 하기
			String url = "jdbc:mysql://192.168.254.35:3307/employees?charset=utf-8";
			conn = DriverManager.getConnection(url, "hr", "hr");
			String sql = "select emp_no, first_name, last_name, date_format(hire_date, '%Y-%m-%d')\r\n"
					+ "from employees\r\n" + "where first_name like ? \r\n" + "	and last_name like ?";

			// 3. sql문 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, "%" + name + "%");

			// 4. SQL문을 실행
			rs = pstmt.executeQuery();

			// 5. 결과 가져오기
			while (rs.next()) {
				Long empNo = rs.getLong(1);
				String firstNmae = rs.getString(2);
				String lastName = rs.getString(3);
				String hireDate = rs.getString(4);

				EmployeeVo vo = new EmployeeVo();
				vo.setEmpNo(empNo);
				vo.setFirstName(firstNmae);
				vo.setLastName(lastName);
				vo.setHireDate(hireDate);

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
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.254.35:3307/employees?charset=utf-8";
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}catch(SQLException e) {
			System.out.println("error" + e);
		}
		return conn;
	}
}