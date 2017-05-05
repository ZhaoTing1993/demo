package cn.ting.spring.dynamicdatasourcedemo.model;

import java.io.Serializable;

public class Account implements Serializable{
	
	private int id;
	private Integer userId;
	private double money;
	
	public Account() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", money=" + money
				+ "]";
	}
}
