package com.skilldistillery.giggity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.repositories.AddressRepo;
import com.skilldistillery.giggity.repositories.ImageRepo;
import com.skilldistillery.giggity.repositories.UserRepo;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepo uRepo;
	@Autowired
	private AddressRepo addyRepo;
	@Autowired
	private ImageRepo imageRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user) {

		String encrypted = encoder.encode(user.getPassword());
		user.setPassword(encrypted);

		user.setEnabled(true);
		
		user.setAvatarImage(imageRepo.findById(1));

		user.setRole("standard");
		addyRepo.saveAndFlush(user.getAddress());
		uRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public boolean isUserUnique(String username, String email) {
		List<User> allUsers = uRepo.findAll();

		boolean isUnique = true;

		for (User user : allUsers) {
			if ((user.getEmail().equalsIgnoreCase(email)) || (user.getUsername().equalsIgnoreCase(username))) {
				isUnique = false;
				System.err.println("EMAIL: "+ user.getEmail() + " " + "UserNmae: " + user.getUsername());
			}

		}

		return isUnique;
	}

}
