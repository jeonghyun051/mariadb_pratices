package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CategoryVo;

public class BookDaoTest {
	
	public static void main(String[] args) {
		// insertTest();
		findAllTest();
	}

	private static void insertTest() {
		// 서적 더미데이터 3개
		BookVo bookVo = null;
		bookVo =new BookVo();
		bookVo.setCategoryNo(1);
		bookVo.setName("트와일라잇");
		bookVo.setPrice(30000);
		new BookDao().insert(bookVo);

		bookVo =new BookVo();
		bookVo.setCategoryNo(1);
		bookVo.setName("트와일라잇");
		bookVo.setPrice(30000);
		new BookDao().insert(bookVo);

		bookVo =new BookVo();
		bookVo.setCategoryNo(1);
		bookVo.setName("트와일라잇");
		bookVo.setPrice(30000);
		new BookDao().insert(bookVo);
	}
	
	private static void findAllTest() {
		List<BookVo> list = new BookDao().findAll();
		for (BookVo vo : list) {
			System.out.println(vo);
		}
	}
}