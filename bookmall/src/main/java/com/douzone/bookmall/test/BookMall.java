package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDao;
import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.dao.OrderBookDao;
import com.douzone.bookmall.dao.OrderDao;
import com.douzone.bookmall.vo.BookVo;
import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.CategoryVo;
import com.douzone.bookmall.vo.MemberVo;
import com.douzone.bookmall.vo.OrderBookVo;
import com.douzone.bookmall.vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {

		System.out.println("=============카테고리=============");
		CategoryVo categoryVo = null;
		categoryVo = new CategoryVo();
		categoryVo.setCategory("예술");
		new CategoryDao().insert(categoryVo);

		categoryVo = new CategoryVo();
		categoryVo.setCategory("학문");
		new CategoryDao().insert(categoryVo);

		categoryVo = new CategoryVo();
		categoryVo.setCategory("경제");
		new CategoryDao().insert(categoryVo);

		// 카테고리 리스트 출력
		CategoryDao categoryDao = new CategoryDao();
		List<CategoryVo> list = categoryDao.findAll();
		for (CategoryVo categoryList : list) {
			System.out.println(categoryList);
		}
		System.out.println("");

		
		System.out.println("=============서적=============");
		BookVo bookVo = null;
		bookVo = new BookVo();
		bookVo.setCategoryNo(1);
		bookVo.setName("트와일라잇");
		bookVo.setPrice(30000);
		new BookDao().insert(bookVo);

		bookVo = new BookVo();
		bookVo.setCategoryNo(2);
		bookVo.setName("어린왕자");
		bookVo.setPrice(20000);
		new BookDao().insert(bookVo);

		bookVo = new BookVo();
		bookVo.setCategoryNo(3);
		bookVo.setName("노인과바다");
		bookVo.setPrice(15000);
		new BookDao().insert(bookVo);

		// 책 목록 출력
		BookDao bookDao = new BookDao();
		List<BookVo> list2 = bookDao.findAll();
		for (BookVo bookList : list2) {
			System.out.println(bookList);
		}
		System.out.println("");

		
		System.out.println("=============회원=============");
		MemberVo memberVo = null;
		memberVo = new MemberVo();
		memberVo.setName("김정현");
		memberVo.setNumber("010-5013-4747");
		memberVo.setEmail("rlawjdgus051@naver.com");
		memberVo.setPassword("1234");
		new MemberDao().insert(memberVo);

		memberVo = new MemberVo();
		memberVo.setName("박정욱");
		memberVo.setNumber("010-4824-4531");
		memberVo.setEmail("박정욱@naver.com");
		memberVo.setPassword("1455");
		new MemberDao().insert(memberVo);

		// 회원 목록 출력
		MemberDao memberDao = new MemberDao();
		List<MemberVo> list3 = memberDao.findAll();
		for (MemberVo memberList : list3) {
			System.out.println(memberList);
		}
		System.out.println("");

		
		System.out.println("=============1번 사용자 카트 목록=============");
		CartVo cartVo = null;
		cartVo = new CartVo();
		cartVo.setCount(1);
		cartVo.setMemberNo(1);
		cartVo.setBookNo(1);
		new CartDao().insert(cartVo);

		cartVo = new CartVo();
		cartVo.setCount(1);
		cartVo.setMemberNo(1);
		cartVo.setBookNo(2);
		new CartDao().insert(cartVo);

		// 1번 사용자 cart 확인
		CartDao cartDao = new CartDao();
		List<CartVo> list4 = cartDao.findAll(1);
		for (CartVo cartList : list4) {
			System.out.println(cartList);
		}
		System.out.println("");
		
		
		System.out.println("=============주문 목록=============");
		OrderVo orderVo = null;
		orderVo = new OrderVo();
		orderVo.setNumber("0521001");
		orderVo.setPrice(30000);
		orderVo.setAddress("부산시 사상구");
		orderVo.setMemberNo(1);

		new OrderDao().insert(orderVo);
		
		OrderDao orderDao = new OrderDao();
		List<OrderVo> list5 = orderDao.findAll();
		for(OrderVo orderList : list5) {
			System.out.println(orderList);
		}
		System.out.println("");
		
		
		System.out.println("=============주문 도서 목록=============");
		OrderBookVo orderBookVo = null;
		orderBookVo = new OrderBookVo();
		orderBookVo.setCount(1);
		orderBookVo.setPrice(30000);
		orderBookVo.setBookNo(1);
		orderBookVo.setOrderNo(1);
		new OrderBookDao().insert(orderBookVo);
		
		orderBookVo = new OrderBookVo();
		orderBookVo.setCount(1);
		orderBookVo.setPrice(30000);
		orderBookVo.setBookNo(2);
		orderBookVo.setOrderNo(1);
		new OrderBookDao().insert(orderBookVo);
		
		OrderBookDao orderBookDao = new OrderBookDao();
		List<OrderBookVo> list6 = orderBookDao.findAll();
		for (OrderBookVo orderBookList : list6) {
			System.out.println(orderBookList);
		}	
	}
}