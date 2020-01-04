package com.skilldistillery.giggity.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.Address;
import com.skilldistillery.giggity.entities.Image;
import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.repositories.AddressRepo;
import com.skilldistillery.giggity.repositories.ImageRepo;
import com.skilldistillery.giggity.repositories.UserRepo;

@Service
@Transactional
public class UserSvcImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AddressRepo addRepo;
	
	@Autowired
	private ImageRepo imgRepo;

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
	@Override
	public User updateUser(User u, int uid) {
		User toUpdate = userRepo.findById(uid);
		Image newImage = new Image();
		
			newImage.setImageUrl(u.getAvatarImage().getImageUrl());
			imgRepo.saveAndFlush(newImage);
		if(toUpdate != null) {
			toUpdate.setFirstName(u.getFirstName());
			toUpdate.setLastName(u.getLastName());
			toUpdate.setUsername(u.getUsername());
			toUpdate.setEmail(u.getEmail());
			toUpdate.setPassword(u.getPassword());
			toUpdate.setEnabled(u.getEnabled());
			toUpdate.setRole(u.getRole());
			toUpdate.setPhone(u.getPhone());
			toUpdate.setAddress(u.getAddress());
			toUpdate.setBio(u.getBio());
			toUpdate.setAvatarImage(newImage);
			//update address too
			Address toModify = addRepo.findById(toUpdate.getAddress().getId());
			if(toModify != null) {
				toModify.setCity(toUpdate.getAddress().getCity());
				toModify.setStreet(toUpdate.getAddress().getStreet());
				toModify.setState(toUpdate.getAddress().getState());
				toModify.setZip(toUpdate.getAddress().getZip());
			}
			
			addRepo.saveAndFlush(toModify);
			toUpdate.setAddress(u.getAddress());
		}
		userRepo.saveAndFlush(toUpdate);
		return toUpdate;
	}
	@Override
	public User findUserByUsername(String username) {
		User profile = userRepo.findByUsername(username);
		
		return profile;
	}
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

}
