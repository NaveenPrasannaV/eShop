package com.cg.shopping.dao;

import java.util.List;

import com.cg.shopping.entity.Orders;

public interface OrdersDao {

	void saveOrders(Orders orders);
	
	List<Orders> getAllOrdersByUserEmail(String userEmail);
}
