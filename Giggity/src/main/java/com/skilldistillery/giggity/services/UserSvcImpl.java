package com.skilldistillery.giggity.services;

import java.util.ArrayList;
import java.util.List;
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
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	@Override
	public List<User> getUsersByUsername(String username) {
		
		List<User> users = new ArrayList<User>();
		
		username = "%" + username + "%";
		
		users = userRepo.findByUsernameLike(username);
		System.err.println(users);
		
		
		
		
		return users;
	}

	@Override
	public User getUserById(int id) {
		return userRepo.findById(id);
	}

	@Override
	public boolean destroy(Integer id) {
		Optional<User> opt = userRepo.findById(id);
		if (opt.isPresent()) {
			userRepo.deleteById(id);
			return true;
		}
		else
			return false;
	}

}
