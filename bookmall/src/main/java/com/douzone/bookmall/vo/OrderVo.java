package com.douzone.bookmall.vo;

public class OrderVo {
	
	private int no;
	private String number;
	private int price;
	private String address;
	private String name;
	private String email;
	private int memberNo;
	
	@Override
	public String toString() {
		return "OrderVo [주문번호=" + number + ", address=" + address + ", name=" + name + ", email=" + email + "]";
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}