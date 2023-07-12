package com.cg.shopping.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.shopping.dao.CartDao;
import com.cg.shopping.dao.UserDao;
import com.cg.shopping.entity.Cart;
import com.cg.shopping.entity.User;
import com.cg.shopping.factory.ObjectFactory;
import com.cg.shopping.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;
	@Autowired
	private UserDao userDao;

	@Override
	public Cart findCartByUserEmail(String userEmail) {
		if (cartDao.existsByUserUserEmail(userEmail)) {
			return cartDao.findCartByUserUserEmail(userEmail);
		}
		return createCartForUserEmail(userEmail);
	}

	@Override
	public Cart createCartForUserEmail(String userEmail) {
		Cart cart = ObjectFactory.createObject(Cart::new);
		User user = userDao.getByEmailId(userEmail);
		cart.setUser(user);
		cartDao.saveCart(cart);
		return cart;
	}

}
