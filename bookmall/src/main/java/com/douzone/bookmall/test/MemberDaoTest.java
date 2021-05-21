package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		
		// insertTest();
		findAllTest();
	}

	private static void insertTest() {
		MemberVo memberVo = null;
		memberVo =new MemberVo();
		memberVo.setName("김정현");
		memberVo.setNumber("010-5013-4747");
		memberVo.setEmail("rlawjdgus051@naver.com");
		memberVo.setPassword("1234");
		new MemberDao().insert(memberVo);	
		
		memberVo =new MemberVo();
		memberVo.setName("박정욱");
		memberVo.setNumber("010-4824-4531");
		memberVo.setEmail("박정욱@naver.com");
		memberVo.setPassword("1455");
		new MemberDao().insert(memberVo);		
		
	}
	
	private static void findAllTest() {
		List<MemberVo> list = new MemberDao().findAll();
		for (MemberVo vo : list) {
			System.out.println(vo);
		}
	}
}

