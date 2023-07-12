package com.cg.shopping.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.shopping.dao.CartDao;
import com.cg.shopping.entity.Cart;
import com.cg.shopping.repository.CartRepository;

@Repository
public class CartDaoImpl implements CartDao {

	@Autowired
	CartRepository cartRepo;

	/*
	 * This method will return the Cart entity by cart id.
	 */
	@Override
	public Cart findCartByCartId(int cartId) {
		return cartRepo.getById(cartId);
	}

	/*
	 * This method will return the list of carts.
	 */
	@Override
	public List<Cart> findAllCarts() {
		return cartRepo.findAll();
	}

	/*
	 * This method will persist the cart into the cart table.
	 */
	@Override
	public void saveCart(Cart cart) {
		cartRepo.save(cart);

	}

	/*
	 * This method will check whether the cart is exist for the given user or not.
	 */
	@Override
	public boolean existsByUserUserEmail(String userEmail) {
		return cartRepo.existsByUserUserEmail(userEmail);
	}

	/*
	 * This method will return the user cart by using user email id.
	 */
	@Override
	public Cart findCartByUserUserEmail(String userEmail) {
		return cartRepo.findByUserUserEmail(userEmail);
	}

	/*
	 * This method will return the cart id by using user email id.
	 */
	@Override
	public int getCartIdByUserUserEmail(String userEmail) {
		return cartRepo.getByUserUserEmail(userEmail);
	}

}
