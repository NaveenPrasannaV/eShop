package com.cg.shopping.dao;

import java.util.List;
import com.cg.shopping.entity.Product;

public interface ProductDao {

	void saveProduct(Product product);

	Product getByProductId(int productId);

	boolean existsByProductId(int productId);

	void deleteProduct(int productId);

	List<Product> findByProductNameLike(String productName);

	int getProductQty(int productId);

}
