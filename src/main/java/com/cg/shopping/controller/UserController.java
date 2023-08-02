package com.cg.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.cg.shopping.model.PaymentStatusDTO;
import com.cg.shopping.model.ProductDTO;
import com.cg.shopping.model.UserDTO;
import com.cg.shopping.service.PaymentMicroservice;
import com.cg.shopping.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PaymentMicroservice paymentMicroservice;

	/*
	 * This method provides API control for the new User to sign-up. //done
	 */
	@PostMapping(path = "/signup", consumes = "application/json")
	@ResponseBody
	public String userSignUp(@RequestBody UserDTO userdto)
			throws NullValueFieldException, PasswordMismatchException, PasswordLengthMismatchException,
			PasswordWithNoSplCharException, PasswordWithNoDigitException, EmailAlreadyExistException {
		return userService.usersignUp(userdto);
	}

	/*
	 * This method provides API control for the existing User to sign-in. //done
	 */
	@GetMapping(path = "/signin/{userEmail}/{userPassword}")
	@ResponseBody
	public String userSignIn(@PathVariable("userEmail") String userEmail,
			@PathVariable("userPassword") String userPassword) throws NoUserFoundException {
		String msg = userService.userSignIn(userEmail, userPassword);
		return msg;
	}

	/*
	 * This method provides API control for the existing User to sign-out. //done
	 */
	@GetMapping(path = "/signout")
	@ResponseBody
	public String userSignOut() {
		return userService.userSignOut();
	}

	/*
	 * This method provides API control to add the product into the cart by product
	 * Id.
	 */
	@GetMapping(path = "/addtocart/{productId}")
	@ResponseBody
	public String addProductToCart(@PathVariable("productId") int productId) throws UserSignInRequiredException,
			ProductNotAvailableException, IllegalValueException, NoProductFoundException {
		return userService.addProductToCart(productId);
	}

	/*
	 * This method provides API control to remove a product from the cart
	 */
	@DeleteMapping(path = "/removefromcart/{productId}")
	@ResponseBody
	public String removeProductFromCart(@PathVariable("productId") int productId)
			throws UserSignInRequiredException, ProductNotAvailableException {
		return userService.removeProductFromCart(productId);
	}

	/*
	 * This method provides API control to change the quantity of the product. User
	 * can either increase or decrease the quantity.
	 */
	@GetMapping(path = "/qty/{productId}/{qty}")
	@ResponseBody
	public String changeProductQuantityInCart(@PathVariable("productId") int productId, @PathVariable("qty") int qty)
			throws IllegalValueException, UserSignInRequiredException, NoProductFoundException {
		return userService.changeProductQuantityInCart(productId, qty);
	}

	/*
	 * This method provides API control to view all the products in the cart
	 */
	@GetMapping(path = "/viewcart", produces = "application/json")
	@ResponseBody
	public List<ProductDTO> viewUserCart() throws UserSignInRequiredException, NoProductFoundException {
		return userService.getCartProductList();
	}

	/*
	 * This method provides API control to view the final bill. User can able to
	 * view the bill invoice after successfully added the products into the cart.
	 */
	@GetMapping(path = "/viewbill")
	@ResponseBody
	public String viewCartBill() throws UserSignInRequiredException {
		return userService.viewBill();
	}

	/*
	 * This method provides API control to place the order from the user cart.
	 */
	@GetMapping(path = "/placeorder")
	@ResponseBody
	public String placeOrders()
			throws UserSignInRequiredException, ProductNotAvailableException, NoProductFoundException {
		return userService.placeOrders();
	}

	/*
	 * This method provides API control to view the placed orders and it's current
	 * status along with Ordered date.
	 */
	@GetMapping(path = "/vieworders")
	@ResponseBody
	public List<OrdersDTO> viewOrders() throws UserSignInRequiredException, NoProductFoundException {
		return userService.getAllOrders();
	}

	/*
	 * ======================================Updating============================
	 */

	/*
	 * This method provides API control for the existing User to sign-in. //done
	 */
	@GetMapping(path = "/")
	public String home() {
		return "home";

	}

	@GetMapping(path = "/msapi")
	public ResponseEntity<PaymentStatusDTO> DemoMicroservice() {
		return paymentMicroservice.DemoMicroServicePaymentCall();

	}

}
