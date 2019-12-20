package com.skilldistillery.giggity.controllers;


import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.services.UserService;

@RestController
//@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4350" })
public class UserController {
	@Autowired
	private UserService svc;

	@GetMapping("users/id/{id}")
	public User getUser(@PathVariable int id, HttpServletResponse res) {
		User user = svc.getUserById(id);
		if (user == null) {
			res.setStatus(404);
		}
		else 
			res.setStatus(200);
		return user;
	}

	@GetMapping("users/username")
	public User getUsersByUsername(@PathVariable String username, HttpServletResponse res, Principal principal) {
		User user = svc.getUserByUsername(principal.getName());
		if (user == null) {
			res.setStatus(404);
		} else
			res.setStatus(200);
		return user;
	}
	
	@GetMapping("api/users/getUser")
	public User getUser(HttpServletResponse res, Principal principal) {
		User loggedInUser = svc.getUserByUsername(principal.getName());		
		if (loggedInUser == null) {
			res.setStatus(404);
		} else
			res.setStatus(200);
		return loggedInUser;
	}
	
	@DeleteMapping("api/users/remove/{id}")
	public void deleteUser(@PathVariable Integer id, HttpServletResponse res) {
		if (!svc.destroy(id)) {
			res.setStatus(404);
		}
		else 
			res.setStatus(200);
	}

}
