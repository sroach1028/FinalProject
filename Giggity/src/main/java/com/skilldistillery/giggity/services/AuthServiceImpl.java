package com.skilldistillery.giggity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.repositories.UserRepo;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	

	@Override
	public User register(User user) {
		
		String encrypted = encoder.encode(user.getPassword());
		user.setPassword(encrypted);
		
		user.setEnabled(true);
		
		user.setRole("standard");
		
		uRepo.saveAndFlush(user);
		
		
		
		
		
		return user;
	}

}

//encrypt and set the password for the User.
//set the enabled field of the object to true.
//set the role field of the object to "standard".
//saveAndFlush the user using the UserRepo.
//return the User object.