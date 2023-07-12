package com.cg.shopping.utils;

import com.cg.shopping.entity.User;
import com.cg.shopping.model.UserDTO;

public class UserUtil {

	/*
	 * Private constructor to prevent instance creation
	 */
	private UserUtil() {
		super();
	}

	/*
	 * It converts the User DTO object into User Entity object.
	 */
	public static User convertToUser(UserDTO userdto) {
		User user = new User();
		user.setUserName(userdto.getUserName());
		user.setUserEmail(userdto.getUserEmail());
		user.setUserPassword(userdto.getUserPassword());
		user.setUserMobile(userdto.getUserMobile());
		user.setUserGender(userdto.getUserGender());
		return user;
	}

}
