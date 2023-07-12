package com.cg.shopping.utils;

import java.util.ArrayList;
import java.util.List;

import com.cg.shopping.entity.Product;
import com.cg.shopping.model.ProductDTO;

public class ProductUtil {
	/*
	 * This method converts the ProductDTO to Product Entity.
	 */
	public static Product convertToProduct(ProductDTO productdto) {
		Product product = new Product();
		product.setProductName(productdto.getProductName());
		product.setProductDescription(productdto.getProductDescription());
		product.setProductPrice(productdto.getProductPrice());
		product.setProductQty(productdto.getProductQty());
		return product;
	}

	/*
	 * This method converts the Entity Product to ProductDTO.
	 */
	public static ProductDTO convertToProductDTO(Product product) {
		ProductDTO productdto = new ProductDTO();
		productdto.setProductId(product.getProductId());
		productdto.setProductName(product.getProductName());
		productdto.setProductDescription(product.getProductDescription());
		productdto.setProductPrice(product.getProductPrice());
		productdto.setProductQty(product.getProductQty());
		return productdto;
	}

	/*
	 * This method converts the List of Product Entity to the List of Product DTO.
	 */
	public static List<ProductDTO> convertToProductDTOList(List<Product> productList) {
		List<ProductDTO> productdtoList = new ArrayList<ProductDTO>();
		for (Product product : productList) {
			productdtoList.add(convertToProductDTO(product));
		}
		return productdtoList;
	}

}
