package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.CategoryDao;
import com.douzone.bookmall.dao.MemberDao;
import com.douzone.bookmall.vo.CategoryVo;
import com.douzone.bookmall.vo.MemberVo;

public class CategoryDaoTest {
	
	public static void main(String[] args) {
		// insertTest();
		findAllTest();
		
	}

	private static void insertTest() {
		CategoryVo vo = null;
		
		vo =new CategoryVo();
		vo.setCategory("예술");
		new CategoryDao().insert(vo);	
		
		vo =new CategoryVo();
		vo.setCategory("학문");
		new CategoryDao().insert(vo);
		
		vo =new CategoryVo();
		vo.setCategory("경제");
		new CategoryDao().insert(vo);
	}
	
	private static void findAllTest() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for (CategoryVo vo : list) {
			System.out.println(vo);
		}
	}
}