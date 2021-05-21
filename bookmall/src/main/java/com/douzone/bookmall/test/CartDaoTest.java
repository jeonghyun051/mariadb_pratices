package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.CartDao;
import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.vo.CartVo;
import com.douzone.bookmall.vo.MemberVo;

public class CartDaoTest {
	public static void main(String[] args) {

		// insertTest();
		findAllTest();
	}

	private static void insertTest() {
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
		
		
	}

	private static void findAllTest() {
		List<MemberVo> list = new MemberDao().findAll();
		for (MemberVo vo : list) {
			System.out.println(vo);
		}
	}
}
