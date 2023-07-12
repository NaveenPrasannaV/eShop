package com.cg.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.shopping.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUserEmailAndUserPassword(String userEmail, String userPassword);
	
	boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
}
