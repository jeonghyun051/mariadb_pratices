package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.dao.OrderDao;
import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.MemberVo;
import com.douzone.bookmall.vo.OrderVo;

public class OrderDaoTest {
	public static void main(String[] args) {

		// insertTest();
		findAllTest();
	}

	private static void insertTest() {

		OrderVo orderVo = null;
		orderVo = new OrderVo();
		orderVo.setNumber("0521001");
		orderVo.setPrice(60000);
		orderVo.setAddress("부산시 사상구");
		orderVo.setMemberNo(1);

		new OrderDao().insert(orderVo);
	}

	private static void findAllTest() {
		CartDao cartDao = new CartDao();
		List<CartVo> list4 = cartDao.findAll(1);
		for (CartVo cartList : list4) {
			System.out.println(cartList);
		}
	}
}
