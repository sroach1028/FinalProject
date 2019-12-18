package com.skilldistillery.giggity.services;

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
	public User getUserByUsername(String username) {
		
		

		
		return userRepo.findByUsername(username);
	}

	@Override
	public User getUserById(int id) {
		return userRepo.findById(id);
	}
}
