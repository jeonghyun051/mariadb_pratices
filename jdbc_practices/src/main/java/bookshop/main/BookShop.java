package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookShop {

	public static void main(String[] args) {
		displayBookInfo();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("대여 하고 싶은 책의 번호를 입력하시오");
		
		Long no = scanner.nextLong();
		new BookDao().update(no, "대여중");
		displayBookInfo();
	}
	
	private static void displayBookInfo() {
		System.out.println("");
		List<BookVo> list = new BookDao().findAll();
		
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	
	}
}