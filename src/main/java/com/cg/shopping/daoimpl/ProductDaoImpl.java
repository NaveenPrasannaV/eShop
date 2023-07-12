package com.cg.shopping.daoimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.shopping.dao.ProductDao;
import com.cg.shopping.entity.Product;
import com.cg.shopping.repository.ProductRepository;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	ProductRepository productRepo;

	/*
	 * This method will save the product into product database.
	 */
	@Override
	public void saveProduct(Product product) {
		productRepo.save(product);
	}

	/*
	 * This method will return the Product based on product id.
	 */
	@Override
	public Product getByProductId(int productId) {
		return productRepo.getById(productId);
	}

	/*
	 * This method will check the existance of a product.
	 */
	@Override
	public boolean existsByProductId(int productId) {
		return productRepo.existsById(productId);
	}

	/*
	 * This method will delete a product.
	 */
	@Override
	public void deleteProduct(int productId) {
		productRepo.deleteById(productId);
	}

	/*
	 * This method will find the product based on the given character.
	 */
	@Override
	public List<Product> findByProductNameLike(String productName) {
		return productRepo.findByProductNameIgnoreCaseContaining(productName);
	}

	/*
	 * This method will return the product count based on the product id.
	 */
	@Override
	public int getProductQty(int productId) {
		return productRepo.getProductQty(productId);
	}
}
