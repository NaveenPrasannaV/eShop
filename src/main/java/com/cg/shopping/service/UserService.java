package com.cg.shopping.service;

import java.util.List;

import com.cg.shopping.exception.EmailAlreadyExistException;
import com.cg.shopping.exception.IllegalValueException;
import com.cg.shopping.exception.NoProductFoundException;
import com.cg.shopping.exception.NoUserFoundException;
import com.cg.shopping.exception.NullValueFieldException;
import com.cg.shopping.exception.PasswordLengthMismatchException;
import com.cg.shopping.exception.PasswordMismatchException;
import com.cg.shopping.exception.PasswordWithNoDigitException;
import com.cg.shopping.exception.PasswordWithNoSplCharException;
import com.cg.shopping.exception.ProductNotAvailableException;
import com.cg.shopping.exception.UserSignInRequiredException;
import com.cg.shopping.model.OrdersDTO;
import com.cg.shopping.model.ProductDTO;
import com.cg.shopping.model.UserDTO;

public interface UserService {

	public String usersignUp(UserDTO userdto)
			throws NullValueFieldException, PasswordMismatchException, PasswordLengthMismatchException,
			PasswordWithNoSplCharException, PasswordWithNoDigitException, EmailAlreadyExistException;

	public String userSignIn(String userEmail, String userPassword) throws NoUserFoundException;

	public String userSignOut();

	public boolean userSignInVerification() throws UserSignInRequiredException;

	public String addProductToCart(int productId) throws UserSignInRequiredException, ProductNotAvailableException, IllegalValueException, NoProductFoundException;

	public String removeProductFromCart(int productId) throws UserSignInRequiredException, ProductNotAvailableException;

	public String changeProductQuantityInCart(int productId, int qty)
			throws IllegalValueException, UserSignInRequiredException, NoProductFoundException;

	public List<ProductDTO> getCartProductList() throws UserSignInRequiredException, NoProductFoundException;

	public String viewBill() throws UserSignInRequiredException;
	
	public String placeOrders() throws UserSignInRequiredException, ProductNotAvailableException, NoProductFoundException;
	
	List<OrdersDTO> getAllOrders() throws UserSignInRequiredException, NoProductFoundException ;
}
