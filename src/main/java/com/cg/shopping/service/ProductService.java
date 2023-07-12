package com.cg.shopping.service;

import java.util.List;

import com.cg.shopping.exception.IllegalValueException;
import com.cg.shopping.exception.NoProductFoundException;
import com.cg.shopping.exception.NullValueFieldException;
import com.cg.shopping.exception.ProductNotAvailableException;
import com.cg.shopping.exception.UserSignInRequiredException;
import com.cg.shopping.model.ProductDTO;

public interface ProductService {

	public String addProductToTable(ProductDTO productdto)
			throws UserSignInRequiredException, NullValueFieldException, IllegalValueException;

	public String addProductListToTable(List<ProductDTO> productdtolist)
			throws UserSignInRequiredException, NullValueFieldException, IllegalValueException;

	public String deleteProductFromTable(int productId)
			throws ProductNotAvailableException, UserSignInRequiredException;

	public List<ProductDTO> searchProductLike(String productName)
			throws NullValueFieldException, NoProductFoundException;

}
