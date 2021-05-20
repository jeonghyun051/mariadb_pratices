package hr;

import java.util.List;
import java.util.Scanner;

public class HRMain2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Salary[min max]>");
		int minSalary = scanner.nextInt();
		int maxSalary = scanner.nextInt();
		
		System.out.println(minSalary+":"+maxSalary);
		
		EmployeeDao dao = new EmployeeDao();
		List<EmployeeVo> list = dao.findeBySalary(minSalary, maxSalary);
		for(EmployeeVo vo : list) {
			System.out.println(vo);
		}
		
		
		scanner.close();

	}
}