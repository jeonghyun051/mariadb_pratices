package com.douzone.bookmall.vo;

public class BookVo {

	private int no;
	private String name;
	private int price;
	private int categoryNo;
	
	
	
	@Override
	public String toString() {
		return "BookVo [name=" + name + ", price=" + price + "]";
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
