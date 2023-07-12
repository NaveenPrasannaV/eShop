package com.cg.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.shopping.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	
	List<Orders> getByUserEmail(String userEmail);
}
