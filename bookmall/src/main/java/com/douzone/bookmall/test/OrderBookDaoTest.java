package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.dao.OrderBookDao;
import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.OrderBookVo;

public class OrderBookDaoTest {
	public static void main(String[] args) {

		// insertTest();
		findAllTest();
	}

	private static void insertTest() {

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
	}

	private static void findAllTest() {
		OrderBookDao orderBookDao = new OrderBookDao();
		List<OrderBookVo> list6 = orderBookDao.findAll();
		for (OrderBookVo orderBookList : list6) {
			System.out.println(orderBookList);
		}
	}
}
