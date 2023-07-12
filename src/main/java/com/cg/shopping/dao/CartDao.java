package com.cg.shopping.dao;

import java.util.List;

import com.cg.shopping.entity.Cart;

public interface CartDao {

	Cart findCartByCartId(int cartId);

	List<Cart> findAllCarts();

	void saveCart(Cart cart);

	boolean existsByUserUserEmail(String userEmail);

	int getCartIdByUserUserEmail(String userEmail);

	Cart findCartByUserUserEmail(String userEmail);
	
}
