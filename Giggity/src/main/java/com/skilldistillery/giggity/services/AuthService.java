package com.skilldistillery.giggity.services;

import com.skilldistillery.giggity.entities.User;

public interface AuthService {
	
	public User register(User user);
	public boolean isUserUsernameUnique(String username);
	public boolean isUserEmailUnique(String email);
	public boolean isUserUnique(String username, String email);

}
