package com.skilldistillery.giggity.services;

import java.util.List;

import com.skilldistillery.giggity.entities.User;

public interface UserService {
	public User getUserById(int id);
//
	public User getUserByUsername(String username);
	public List<User> getUsersByUsername(String username);
	public boolean destroy(Integer id);
}
