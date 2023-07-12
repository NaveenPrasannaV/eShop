package com.cg.shopping.model;

import org.springframework.stereotype.Component;

import com.cg.shopping.entity.User;

import lombok.Data;

@Component
public class CartDTO {

	private double totalCartPrice;
	private int totalProductQty;
	private User user;
	private int myProductId;

	public double getTotalCartPrice() {
		return totalCartPrice;
	}

	public void setTotalCartPrice(double totalCartPrice) {
		this.totalCartPrice = totalCartPrice;
	}

	public int getTotalProductQty() {
		return totalProductQty;
	}

	public void setTotalProductQty(int totalProductQty) {
		this.totalProductQty = totalProductQty;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getMyProductId() {
		return myProductId;
	}

	public void setMyProductId(int myProductId) {
		this.myProductId = myProductId;
	}

}
