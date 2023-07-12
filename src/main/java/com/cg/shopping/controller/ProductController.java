package com.cg.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.shopping.exception.IllegalValueException;
import com.cg.shopping.exception.NoProductFoundException;
import com.cg.shopping.exception.NullValueFieldException;
import com.cg.shopping.exception.ProductNotAvailableException;
import com.cg.shopping.exception.UserSignInRequiredException;
import com.cg.shopping.model.ProductDTO;
import com.cg.shopping.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	/*
	 * This method provides API control to add a product by admin.
	 */
	@PostMapping(path = "/addproduct", consumes = "application/json")
	public String addProduct(@RequestBody ProductDTO productdto)
			throws UserSignInRequiredException, NullValueFieldException, IllegalValueException {
		return productService.addProductToTable(productdto);
	}

	/*
	 * This method provides API control to add a List of products by admin.
	 */
	@PostMapping(path = "/addproductlist", consumes = "application/json")
	public String addProductList(@RequestBody List<ProductDTO> productdtolist)
			throws UserSignInRequiredException, NullValueFieldException, IllegalValueException {
		return productService.addProductListToTable(productdtolist);
	}

	/*
	 * This method provides API control to delete a product by admin.
	 */
	@DeleteMapping(path = "/deleteproduct/{productId}")
	public String deleteProduct(@PathVariable("productId") int productId)
			throws ProductNotAvailableException, UserSignInRequiredException {
		return productService.deleteProductFromTable(productId);
	}

	/*
	 * This method provides API control to search the product. Product Name with
	 * incomplete characters can also be searched.
	 */
	@GetMapping(path = "/searchproduct/{productName}", produces = "application/json")
	@ResponseBody
	public List<ProductDTO> searchProductLike(@PathVariable("productName") String productName)
			throws NullValueFieldException, NoProductFoundException {
		List<ProductDTO> productdtoList = productService.searchProductLike(productName);
		return productdtoList;
	}
	
	//**************************UPDATING********************
	

}
