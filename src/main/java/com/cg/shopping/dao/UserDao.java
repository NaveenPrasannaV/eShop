package com.cg.shopping.dao;

import com.cg.shopping.entity.User;

public interface UserDao {

	boolean isExistsByUserEmailAndUserPassword(String userEmail, String userPassword);
	
	void saveUser(User user);
	
	//Optional<User> findByEmailId(String userEmailId);

	User getByEmailId(String userEmailId);

	boolean existsByEmailId(String userEmailId);
	
	User findByEmailIdAndPassword(String userEmail, String userPassword);
}
