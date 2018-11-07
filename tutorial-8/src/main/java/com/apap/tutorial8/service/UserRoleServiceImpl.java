package com.apap.tutorial8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial8.model.PasswordModel;
import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.repository.UserRoleDb;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDb userDb;
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		String pass = encrypt(user.getPassword());
		String pattern = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,}";
		if (user.getPassword().matches(pattern)) {
			user.setPassword(pass);
			return userDb.save(user);
		}
		else {
			return null;
		}
		
	}

	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserRoleModel findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userDb.findByUsername(username);
	}

	@Override
	public Boolean checkIfPassValid(UserRoleModel user, String oldPass) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String old = passwordEncoder.encode(oldPass);
		String pass = passwordEncoder.encode(user.getPassword());
		if (pass.equals(oldPass)) {
			return true;
		}
		return false;
	}

	@Override
	public void changePassword(UserRoleModel user, String newPass) {
		// TODO Auto-generated method stub
		String pass = encrypt(newPass);
		user.setPassword(pass);
		userDb.save(user);
	}

	@Override
	public String changeUserPass(PasswordModel password, UserRoleModel user) {
		// TODO Auto-generated method stub
		String message;
		String pattern = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,}";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(password.getOldPass(), user.getPassword())) {
			
			if (password.getNewPass().matches(pattern)) {
				//System.out.println(password.getNewPass()+ "new pass");
				if (password.getNewPass().equals(password.getConfPass())) {
					changePassword(user, password.getNewPass());
					message = "password berhasil diubah";
				}
				else {
					message = "password baru tidak sesuai";
				}
			}
			else {
				message = "password anda harus mengandung angka, huruf, dan memiliki sedikitnya 8 karakter";
			}
		}
		else {
			message = "password lama yang anda masukkan salah";
		}
		return message;
	}
	
}
