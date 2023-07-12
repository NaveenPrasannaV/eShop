package com.cg.shopping.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
public class OrdersDTO {

	private int orderId;
	private String userEmail;
	private String productName;
	private int productQty;
	private double productPrice;
	private Date orderDate;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public Date getOrderedDate() {
		return orderDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderDate = orderedDate;
	}

}
