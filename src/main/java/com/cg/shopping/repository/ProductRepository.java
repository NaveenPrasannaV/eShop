package com.cg.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.shopping.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByProductNameIgnoreCaseContaining(String productName);
	
	@Query("select p.productQty from Product p where p.productId=?1")
	int getProductQty(int productId);
}
 