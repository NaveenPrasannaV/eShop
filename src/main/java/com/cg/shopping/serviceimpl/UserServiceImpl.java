package com.cg.shopping.serviceimpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.shopping.dao.CartDao;
import com.cg.shopping.dao.OrdersDao;
import com.cg.shopping.dao.ProductDao;
import com.cg.shopping.dao.UserDao;
import com.cg.shopping.entity.Cart;
import com.cg.shopping.entity.Orders;
import com.cg.shopping.entity.Product;
import com.cg.shopping.entity.User;
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
import com.cg.shopping.factory.ObjectFactory;
import com.cg.shopping.model.CartBillDTO;
import com.cg.shopping.model.OrdersDTO;
import com.cg.shopping.model.ProductDTO;
import com.cg.shopping.model.UserDTO;
import com.cg.shopping.service.CartService;
import com.cg.shopping.service.UserService;
import com.cg.shopping.utils.OrdersUtil;
import com.cg.shopping.utils.ProductUtil;
import com.cg.shopping.utils.UserUtil;
import com.cg.shopping.validation.ShoppingValidation;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private CartService cartService;

	private static String userEmail = null;
	private static String userPassword = null;

	/*
	 * This method is for User Sign-in Verification. A common method used by all
	 * other methods in this class. Check the values and the existence of the
	 * database.
	 */
	@Override
	public boolean userSignInVerification() throws UserSignInRequiredException {
		if (UserServiceImpl.userEmail != null && UserServiceImpl.userPassword != null && userDao
				.isExistsByUserEmailAndUserPassword(UserServiceImpl.userEmail, UserServiceImpl.userPassword)) {
			return true;
		}
		return false;
	}

	/*
	 * This is a common method, will return the userEmail.
	 */
	public static String getUserEmail() {
		return UserServiceImpl.userEmail;
	}

	/*
	 * This method will persist the user data into the user database through User
	 * DAO by validating and converting it into the entity. First it will check all
	 * the mandatory fields and is already exist or not. Then it will call the save
	 * method of User DAO. It will sign out the previous user, if any user already
	 * signed-in with other email ID.
	 */
	@Override
	public String usersignUp(UserDTO userdto)
			throws NullValueFieldException, PasswordMismatchException, PasswordLengthMismatchException,
			PasswordWithNoSplCharException, PasswordWithNoDigitException, EmailAlreadyExistException {

		if (userDao.isExistsByUserEmailAndUserPassword(userdto.getUserEmail(), userdto.getUserPassword())) {
			throw new EmailAlreadyExistException("Email Id already exist, Try with new Email Id");
		} else {
			if (ShoppingValidation.signUpValidation(userdto.getUserName(), userdto.getUserEmail(),
					userdto.getUserPassword(), userdto.getUserConfirmPassword(), userdto.getUserMobile(),
					userdto.getUserGender())) {
				User user = UserUtil.convertToUser(userdto);
				userDao.saveUser(user);
				cartService.createCartForUserEmail(user.getUserEmail());
				userSignOut();
				return "User Data added Successfully";
			}
		}
		return "User Data not added, Try again";
	}

	/*
	 * This method sign-in the existing user by verifying id and password with
	 * database and stored it in the global reference variable. It will also
	 * identify whether the email id is of user type or Admin type. A cart is
	 * allocated for that particular user Id.
	 */
	@Override
	public String userSignIn(String userEmail, String userPassword) throws NoUserFoundException {
		if (userDao.isExistsByUserEmailAndUserPassword(userEmail, userPassword)) {
			UserServiceImpl.userEmail = userEmail;
			UserServiceImpl.userPassword = userPassword;
			User user = userDao.findByEmailIdAndPassword(userEmail, userPassword);
			if (ShoppingValidation.checkIsAdminSignIn(userEmail)) {
				return "Signed in as Admin: " + user.getUserName();
			}
			return "Signed in as User: " + user.getUserName();
		}
		throw new NoUserFoundException("No User Found! Please check the Email and Password.");
	}

	/*
	 * This method will sign out the user from the application service.
	 */
	@Override
	public String userSignOut() {
		UserServiceImpl.userEmail = null;
		UserServiceImpl.userPassword = null;
		return "User Successfully signed out";
	}

	/*
	 * This method will return the list of added products from the user cart. It
	 * will get list of product entities and get converted into DTO list, finally
	 * return it.
	 */
	@Override
	public List<ProductDTO> getCartProductList() throws UserSignInRequiredException, NoProductFoundException {
		if (userSignInVerification()) {
			List<ProductDTO> productlist = ProductUtil
					.convertToProductDTOList(cartDao.findCartByUserUserEmail(userEmail).getProductList());
			if (productlist.isEmpty()) {
				throw new NoProductFoundException("Your Cart is empty! Continue shopping");
			}
			productlist = ShoppingValidation.cartListValidation(productlist);
			return productlist;
		}
		throw new UserSignInRequiredException("Please sign-in to View the cart");
	}

	/*
	 * This method will return the final bill, which includes total cart qty and
	 * total cart price. Get cart product list, validating it and return by
	 * converting it into DTO list from entities list.
	 */
	@Override
	public String viewBill() throws UserSignInRequiredException {
		String bill = null;
		if (userSignInVerification()) {
			User user = userDao.getByEmailId(UserServiceImpl.userEmail);
			Cart cart = cartDao.findCartByUserUserEmail(UserServiceImpl.userEmail);
			String productBill = "";
			List<ProductDTO> productList = ProductUtil
					.convertToProductDTOList(cartDao.findCartByUserUserEmail(userEmail).getProductList());
			productList = ShoppingValidation.cartListValidation(productList);
			for (ProductDTO product : productList) {
				productBill = productBill + " Id: " + product.getProductId() + " Name: " + product.getProductName()
						+ " Qty: " + product.getProductQty() + " Price: " + product.getProductPrice() + '\n';
			}
			bill = "***************Invoice-OnlineShop.com***********************" + '\n' + "Bill Name: "
					+ user.getUserName() + '\n' + "Email Id: " + user.getUserEmail() + '\n'
					+ "************************************************************" + '\n' + productBill + '\n'
					+ "************************************************************" + '\n' + "Total Qty: "
					+ cart.getTotalProductQty() + "       Total Price: " + cart.getTotalCartPrice() + '\n'
					+ "************************************************************";
		} else {
			throw new UserSignInRequiredException("Please sign-in to View the Bill Invoice");
		}
		return bill;
	}

	/*
	 * This method will place the order by getting all the products from the user
	 * cart. Once the order is placed, Products in the cart will be removed and
	 * saved into the order table.
	 */
	@Override
	public String placeOrders()
			throws UserSignInRequiredException, ProductNotAvailableException, NoProductFoundException {
		if (userSignInVerification()) {
			List<ProductDTO> productList = getCartProductList();
			for (ProductDTO product : productList) {
				OrdersDTO ordersdto = new OrdersDTO();
				ordersdto.setUserEmail(UserServiceImpl.userEmail);
				ordersdto.setProductName(product.getProductName());
				ordersdto.setProductQty(product.getProductQty());
				ordersdto.setProductPrice(product.getProductPrice());
				// DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				ordersdto.setOrderedDate(date);
				Orders orders = OrdersUtil.convertToOrders(ordersdto);
				ordersDao.saveOrders(orders);

				Cart cart = cartDao.findCartByUserUserEmail(UserServiceImpl.userEmail);
				int index = cart.getProductList().indexOf(productDao.getByProductId(product.getProductId()));
				Product product1 = cart.getProductList().get(index);
				cart.getProductList().remove(index);
				cart.setTotalCartPrice(cart.getTotalCartPrice() - product1.getProductPrice());
				cart.setTotalProductQty(cart.getTotalProductQty() - 1);
				cartDao.saveCart(cart);
			}
			return "Order Placed Successfully";
		}
		throw new UserSignInRequiredException("Please sign-in to Place the orders");

	}

	/*
	 * This method will return all the placed orders of an user. Based on the email
	 * id it will get all the orders and converting this orders DTO list to entities
	 * list.
	 */
	@Override
	public List<OrdersDTO> getAllOrders() throws UserSignInRequiredException, NoProductFoundException {
		if (userSignInVerification()) {
			List<Orders> orderList = ordersDao.getAllOrdersByUserEmail(UserServiceImpl.userEmail);
			if (orderList.isEmpty()) {
				throw new NoProductFoundException("No Orders found! Continue shopping");
			}
			return OrdersUtil.convertToOrdersDTOList(orderList);
		}
		throw new UserSignInRequiredException("Please sign-in to View the orders");
	}

	/*
	 * This method will add the new product into the cart. If it already presents,
	 * throw exception with message as product already in the cart, you can increase
	 * the quantity.
	 * 
	 * This method will add the new product into the cart. If it already presents,
	 * it will increase the quantity.
	 */
	@Override
	public String addProductToCart(int productId) throws ProductNotAvailableException, UserSignInRequiredException,
			IllegalValueException, NoProductFoundException {
		if (userSignInVerification()) {
			if (productDao.existsByProductId(productId) && productDao.getProductQty(productId) > 0) {
				Cart cart = cartService.findCartByUserEmail(userEmail);
				if (ShoppingValidation.cartProductCountValidation(cart, productId) == 0) {
					return addProductToGivenCartAndUpdateTable(cart, productId);
				}
				return changeProductQuantityInCart(productId, 1);
			}
			throw new ProductNotAvailableException(
					"Out of Stock! Product is unavailable now, try with different product");
		}
		throw new UserSignInRequiredException("Please sign-in to add product into the cart");
	}

	/*
	 * This method will search a cart for an user after that it will add the product
	 * into the cart. Also reduce the product count in the product table. This is a
	 * common method.
	 */
	public String addProductToGivenCartAndUpdateTable(Cart cart, int productId) {
		Product product = productDao.getByProductId(productId);
		int productQty = product.getProductQty();
		cart.getProductList().add(product);
		cart.setTotalCartPrice(cart.getTotalCartPrice() + product.getProductPrice());
		cart.setTotalProductQty(cart.getTotalProductQty() + 1);
		cartDao.saveCart(cart);
		product.setProductQty(productQty - 1);
		productDao.saveProduct(product);
		return "Product " + product.getProductName() + " has been added successfully to the cart";
	}

	/*
	 * This method will remove the product from the cart. Based on the email, first
	 * it will get the cart and inside the product list, it will find the product
	 * based on product id. Finally removed it, if the product is present.
	 */

	@Override
	public String removeProductFromCart(int productId)
			throws UserSignInRequiredException, ProductNotAvailableException {
		int productQty = 0;
		String msg = null;
		if (userSignInVerification()) {
			Cart cart = cartDao.findCartByUserUserEmail(userEmail);
			productQty = ShoppingValidation.cartProductCountValidation(cart, productId);
			if (productQty > 0) {
				for (int i = 0; i < productQty; i++) {
					msg = removeProductFromGivenCartAndUpdateTable(cart, productId);
				}
				return msg;
			}
			throw new ProductNotAvailableException("Unable to remove! Product is not available in the cart");

		}
		throw new UserSignInRequiredException("Please sign-in as User to delete a Product from the cart");
	}

	/*
	 * This method will remove a product from the cart and add it back to the
	 * Product table. This is a common method.
	 */
	public String removeProductFromGivenCartAndUpdateTable(Cart cart, int productId) {
		int index = cart.getProductList().indexOf(productDao.getByProductId(productId));
		Product product = cart.getProductList().get(index);
		String productName = product.getProductName();
		cart.getProductList().remove(index);
		cart.setTotalCartPrice(cart.getTotalCartPrice() - product.getProductPrice());
		cart.setTotalProductQty(cart.getTotalProductQty() - 1);
		cartDao.saveCart(cart);
		product.setProductQty(product.getProductQty() + 1);
		productDao.saveProduct(product);
		return "Product " + productName + " has been removed successfully from the cart";
	}

	/*
	 * This method will either increase or decrease the product quantity in the
	 * cart, If the product is present. If you want to increase the qty, it will
	 * check with product database. If you want to decrease the quantity, it will
	 * check with product inside the user cart.
	 */
	@Override
	public String changeProductQuantityInCart(int productId, int qty)
			throws IllegalValueException, UserSignInRequiredException, NoProductFoundException {
		String msg = null;
		if (userSignInVerification()) {
			int cartQty = 0;
			Cart cart = cartDao.findCartByUserUserEmail(UserServiceImpl.userEmail);
			List<Product> productList = cart.getProductList();
			int productQty = productDao.getProductQty(productId);
			for (Product product : productList) {
				if (product.getProductId() == productId) {
					cartQty++;
				}
			}
			if (cartQty == 0) {
				throw new NoProductFoundException("No Product found! Please add the product into the cart");
			}
			if (qty > 0 && productQty >= qty) {
				for (int i = 0; i < qty; i++) {
					msg = addProductToGivenCartAndUpdateTable(cart, productId);
				}
				return msg;
			} else if (qty < 0 && cartQty >= (-qty)) {
				for (int i = qty; i < 0; i++) {
					msg = removeProductFromGivenCartAndUpdateTable(cart, productId);
				}
				return msg;
			}
			throw new IllegalValueException("Invalid Quantity!");
		}
		throw new UserSignInRequiredException("Please sign-in to Change the product Quantity in the cart");
	}

	// ============================Updating===============================================
	/*
	 * This method will return the final bill, which includes total cart qty and
	 * total cart price. Get cart product list, validating it and return by
	 * converting it into DTO list from entities list.
	 */

	public String viewBill_Version() throws UserSignInRequiredException {
		String bill = null;
		if (userSignInVerification()) {
			User user = userDao.getByEmailId(UserServiceImpl.userEmail);
			Cart cart = cartDao.findCartByUserUserEmail(UserServiceImpl.userEmail);
			String productBill = "";
			List<ProductDTO> productList = ProductUtil
					.convertToProductDTOList(cartDao.findCartByUserUserEmail(userEmail).getProductList());
			productList = ShoppingValidation.cartListValidation(productList);
			for (ProductDTO product : productList) {
				productBill = productBill + " Id: " + product.getProductId() + " Name: " + product.getProductName()
						+ " Qty: " + product.getProductQty() + " Price: " + product.getProductPrice() + '\n';
			}
			bill = "***************Invoice-OnlineShop.com***********************" + '\n' + "Bill Name: "
					+ user.getUserName() + '\n' + "Email Id: " + user.getUserEmail() + '\n'
					+ "************************************************************" + '\n' + productBill + '\n'
					+ "************************************************************" + '\n' + "Total Qty: "
					+ cart.getTotalProductQty() + "       Total Price: " + cart.getTotalCartPrice() + '\n'
					+ "************************************************************";
		} else {
			throw new UserSignInRequiredException("Please sign-in to View the Bill Invoice");
		}
		return bill;
	}

}
