package com.cg.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.shopping.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>
{
	boolean existsByUserUserEmail(String userEmail);
	
	Cart findByUserUserEmail(String userEmail);
	
	int	getByUserUserEmail(String userEmail);
	
}
