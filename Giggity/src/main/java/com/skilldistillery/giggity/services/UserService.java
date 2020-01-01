package com.skilldistillery.giggity.services;

import java.util.List;

import com.skilldistillery.giggity.entities.User;

public interface UserService {
	public User getUserById(int id);

	public User getUserByUsername(String username);

	public User findUserByUsername(String username);
	
	public List<User> getUsersByUsername(String username);
	
	public User updateUser(User u, int uid);

	public boolean destroy(Integer id);
}
