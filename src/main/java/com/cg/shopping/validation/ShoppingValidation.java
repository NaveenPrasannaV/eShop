package com.cg.shopping.validation;

import java.util.ArrayList;
import java.util.List;

import com.cg.shopping.commonconstant.Constant;
import com.cg.shopping.entity.Cart;
import com.cg.shopping.entity.Product;
import com.cg.shopping.exception.EmailAlreadyExistException;
import com.cg.shopping.exception.IllegalValueException;
import com.cg.shopping.exception.NullValueFieldException;
import com.cg.shopping.exception.PasswordLengthMismatchException;
import com.cg.shopping.exception.PasswordMismatchException;
import com.cg.shopping.exception.PasswordWithNoDigitException;
import com.cg.shopping.exception.PasswordWithNoSplCharException;
import com.cg.shopping.model.ProductDTO;
import com.cg.shopping.validation.ShoppingValidation;

public class ShoppingValidation {

	/*
	 * This method validates the User DTO object that whether it contains any Null
	 * value or not and also verify that the email already exist in the database or
	 * not
	 */
	public static boolean signUpValidation(String name, String email, String password, String confirmPassword,
			String mobile, String gender)
			throws NullValueFieldException, PasswordMismatchException, PasswordLengthMismatchException,
			PasswordWithNoSplCharException, PasswordWithNoDigitException, EmailAlreadyExistException {
		if (name != null && email != null && password != null && confirmPassword != null && mobile != null
				&& gender != null) {
			if (password.equals(confirmPassword) && passwordValidation(password)) {
				return true;
			}
			throw new PasswordMismatchException("Password and Confirm Password should be same");
		}
		throw new NullValueFieldException("Field Should not be empty");
	}

	/*
	 * This method validates the given password by verifying that it has atleast 1
	 * numerical and 1 special character.
	 */
	public static boolean passwordValidation(String password)
			throws PasswordLengthMismatchException, PasswordWithNoSplCharException, PasswordWithNoDigitException {
		int splcharCount = 0;
		int numCount = 0;
		String specialCharacters = Constant.SPECIALCHARACTERS;
		String numbers = Constant.NUMBERS;

		if (password.length() >= 8) {
			for (int i = 0; i < password.length(); i++) {
				char ch = password.charAt(i);
				if (specialCharacters.contains(Character.toString(ch))) {
					splcharCount++;
				} else if (numbers.contains(Character.toString(ch))) {
					numCount++;
				}
			}
			if (splcharCount >= 1) {
				if (numCount >= 1) {
					return true;
				}
				throw new PasswordWithNoDigitException("Password should contains atleast 1 Digit");
			}
			throw new PasswordWithNoSplCharException("Password should contains atleast 1 Special Character");

		}
		throw new PasswordLengthMismatchException("Password should contains atleast 8 characters");
	}

	/*
	 * This method validates the Product by verifying all fields, Price should be in
	 * positive values and count in positive values.
	 */
	public static boolean addProductValidation(ProductDTO productdto)
			throws NullValueFieldException, IllegalValueException {
		if (productdto.getProductName() != null && productdto.getProductDescription() != null
				&& productdto.getProductQty() != 0) {
			if (productdto.getProductPrice() >= 0 && productdto.getProductQty() >= 0) {
				return true;
			}
			throw new IllegalValueException("Field value Shouldn't be Negative");
		}
		throw new NullValueFieldException("Field Should not be empty");
	}

	/*
	 * This method validates the searching string productName
	 */
	public static boolean searchProductValidation(String productName) throws NullValueFieldException {
		if (productName == null || productName == " ") {
			throw new NullValueFieldException("Field Should not be empty, Enter some character");
		}
		return true;
	}

	/*
	 * This method validates whether the user or an admin is signed-in.
	 */
	public static boolean checkIsAdminSignIn(String userEmail) {
		String str = "";
		String Characters = Constant.ADMINEMAILDOMAIN;
		for (int i = 0; i < userEmail.length(); i++) {
			char ch = userEmail.charAt(i);
			if (ch == '@') {
				str = userEmail.substring(i);
				if (str.equals(Characters)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * This method will customize the cart items. If a Product with the same Product
	 * Id is present more than once, It will make it as a single entity by
	 * increasing the qty field accordingly.
	 */
	public static List<ProductDTO> cartListValidation(List<ProductDTO> productList) {
		List<ProductDTO> newList = new ArrayList<ProductDTO>();
		String ids = "";
		for (ProductDTO product : productList) {
			int id = product.getProductId();
			if (ids.contains(Integer.toString(id))) {
				for (ProductDTO newProduct : newList) {
					if (newProduct.getProductId() == id) {
						newProduct.setProductQty(newProduct.getProductQty() + 1);
						newProduct.setProductPrice(newProduct.getProductPrice() * 2);

					}
				}
			} else {
				product.setProductQty(1);
				newList.add(product);
				ids = ids + id;
			}

		}
		return newList;
	}

	/*
	 * This method will count and return the total cart products for validation.
	 */
	public static int cartProductCountValidation(Cart cart, int productId) {
		int productCount = 0;
		List<Product> productlist = cart.getProductList();
		for (Product product : productlist) {
			if (product.getProductId() == productId) {
				productCount++;
			}
		}
		return productCount;
	}
}
