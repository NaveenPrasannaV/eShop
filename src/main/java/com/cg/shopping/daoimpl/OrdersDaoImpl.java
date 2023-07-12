package com.cg.shopping.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.shopping.dao.OrdersDao;
import com.cg.shopping.entity.Orders;
import com.cg.shopping.repository.OrdersRepository;

@Repository
public class OrdersDaoImpl implements OrdersDao {

	@Autowired
	OrdersRepository ordersRepo;

	/*
	 * This method will save the orders for an user.
	 */
	@Override
	public void saveOrders(Orders orders) {
		ordersRepo.save(orders);
	}

	/*
	 * This method will return the List of orders ordered by an user using user
	 * email id.
	 */
	@Override
	public List<Orders> getAllOrdersByUserEmail(String userEmail) {
		return ordersRepo.getByUserEmail(userEmail);
	}
}
