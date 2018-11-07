package com.apap.tutorial8.service;

import com.apap.tutorial8.model.PasswordModel;
import com.apap.tutorial8.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	public String encrypt(String password);
	UserRoleModel findUserByUsername (String username);
	public Boolean checkIfPassValid(UserRoleModel user, String oldPass);
	void changePassword(UserRoleModel user, String newPass);
	public String changeUserPass(PasswordModel password, UserRoleModel user);
}
