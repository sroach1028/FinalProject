package com.skilldistillery.giggity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	User findById(int id);

}
