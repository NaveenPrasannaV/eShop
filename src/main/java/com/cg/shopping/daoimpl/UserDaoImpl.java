package com.cg.shopping.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.shopping.dao.UserDao;
import com.cg.shopping.entity.User;
import com.cg.shopping.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepository userRepo;

	/*
	 * This method will return the user entity by user email id.
	 */
	@Override
	public User getByEmailId(String userEmailId) {
		return userRepo.getById(userEmailId);
	}

	/*
	 * This method will check the existence of an user using email id.
	 */
	@Override
	public boolean existsByEmailId(String userEmailId) {
		return userRepo.existsById(userEmailId);
	}

	/*
	 * This method will return an user based on user email and password.
	 */
	@Override
	public User findByEmailIdAndPassword(String userEmail, String userPassword) {
		return userRepo.findByUserEmailAndUserPassword(userEmail, userPassword);
	}

	/*
	 * This method will check the existance of an user based on user email and
	 * password.
	 */
	@Override
	public boolean isExistsByUserEmailAndUserPassword(String userEmail, String userPassword) {
		return userRepo.existsByUserEmailAndUserPassword(userEmail, userPassword);
	}

	/*
	 * This method will save the user into the user database.
	 */
	@Override
	public void saveUser(User user) {
		userRepo.save(user);
	}

}
