package com.cg.shopping.service;

import com.cg.shopping.entity.Cart;

public interface CartService {

	Cart findCartByUserEmail(String userEmail);

	Cart createCartForUserEmail(String userEmail);
}
