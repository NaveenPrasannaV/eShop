package com.cg.shopping.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class CartBillDTO {

	private String userName;
	private String userEmail;
	private int producrId;
	private String productName;
	private int productQty;
	private double productPrice;
	private int totalProductQty;
	private double totalProductPrice;

}
