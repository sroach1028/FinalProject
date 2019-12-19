package com.skilldistillery.giggity.services;

import com.skilldistillery.giggity.entities.User;

public interface UserService {
	public User getUserById(int id);
//
	public User getUserByUsername(String username);
	public boolean destroy(Integer id);
}
