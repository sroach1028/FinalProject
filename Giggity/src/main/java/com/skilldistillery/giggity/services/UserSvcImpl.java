package com.skilldistillery.giggity.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.repositories.UserRepo;

@Service
@Transactional
public class UserSvcImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Override
	public User getUser(int id) {
		Optional<User> opt = userRepo.findById(id);
		User user = null;
		
		if (opt.isPresent()) {
			user = opt.get();
			
		}
		
		return user;
	}
}
