package com.cg.shopping.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.shopping.dao.ProductDao;
import com.cg.shopping.entity.Product;
import com.cg.shopping.exception.IllegalValueException;
import com.cg.shopping.exception.NoProductFoundException;
import com.cg.shopping.exception.NullValueFieldException;
import com.cg.shopping.exception.ProductNotAvailableException;
import com.cg.shopping.exception.UserSignInRequiredException;
import com.cg.shopping.model.ProductDTO;
import com.cg.shopping.service.ProductService;
import com.cg.shopping.service.UserService;
import com.cg.shopping.utils.ProductUtil;
import com.cg.shopping.validation.ShoppingValidation;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	UserService userService;
	@Autowired
	private ProductDao productDao;

	/*
	 * This method will allow the admin to add a product into the database. First it
	 * will verify the user authentication and product validation, after that the
	 * Product DTO is converted into Product Entity. Finally saved into the
	 * database.
	 * 
	 */
	@Override
	public String addProductToTable(ProductDTO productdto)
			throws UserSignInRequiredException, NullValueFieldException, IllegalValueException {

		if (userService.userSignInVerification()
				&& ShoppingValidation.checkIsAdminSignIn(UserServiceImpl.getUserEmail())) {
			if (ShoppingValidation.addProductValidation(productdto)) {
				Product product = ProductUtil.convertToProduct(productdto);
				productDao.saveProduct(product);
				return "Product " + productdto.getProductName() + " Added Successfully into the DataBase";
			}
		}
		throw new UserSignInRequiredException("Please sign-in as Admin to add a Product");
	}

	/*
	 * This method will allow the admin to add a List of products into the database.
	 * First it will verify the user authentication and product in the list
	 * validation, after that the Product DTO list is converted into Product
	 * Entities list. Finally saved into the database.
	 */
	@Override
	public String addProductListToTable(List<ProductDTO> productdtolist)
			throws UserSignInRequiredException, NullValueFieldException, IllegalValueException {
		if (productdtolist != null) {
			for (ProductDTO productdto : productdtolist) {
				ShoppingValidation.addProductValidation(productdto);
			}
			for (ProductDTO productdto : productdtolist) {
				addProductToTable(productdto);
			}
			return "List of Products added Successfully";
		}
		throw new NullPointerException("Product List is empty!");
	}

	/*
	 * This method will allow the admin to delete a product from the database. First
	 * it will verify the user authentication and product Id verification. If the
	 * product is existed in the Database will be deleted.
	 */
	@Override
	public String deleteProductFromTable(int productId)
			throws ProductNotAvailableException, UserSignInRequiredException {
		if (userService.userSignInVerification()
				&& ShoppingValidation.checkIsAdminSignIn(UserServiceImpl.getUserEmail())) {
			if (productDao.existsByProductId(productId)) {
				productDao.deleteProduct(productId);
				return "Product Deleted Successfully";
			}
			throw new ProductNotAvailableException("Product Doesn't exist for ID: " + productId);
		}
		throw new UserSignInRequiredException("Please sign-in as Admin to delete a Product");
	}

	/*
	 * This method will return a list of products based on the search character.
	 * Based on the characters, It will get a product entities list, converted into
	 * DTO list and finally returned it.
	 */
	@Override
	public List<ProductDTO> searchProductLike(String productName)
			throws NullValueFieldException, NoProductFoundException {
		List<ProductDTO> productdtoList = null;
		if (ShoppingValidation.searchProductValidation(productName)) {
			List<Product> productList = productDao.findByProductNameLike(productName);
			if (productList.isEmpty()) {
				throw new NoProductFoundException("No items found for the search " + productName);
			} else {
				productdtoList = ProductUtil.convertToProductDTOList(productList);
			}
		}
		return productdtoList;

	}
}
