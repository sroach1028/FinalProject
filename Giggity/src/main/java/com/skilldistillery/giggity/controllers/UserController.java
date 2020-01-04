package com.skilldistillery.giggity.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.services.AuthService;
import com.skilldistillery.giggity.services.UserService;

@RestController
//@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4350" })
public class UserController {
	@Autowired
	private UserService svc;

	@Autowired
	private AuthService authService;

	@GetMapping("users/id/{id}")
	public User getUser(@PathVariable int id, HttpServletResponse res) {
		User user = svc.getUserById(id);
		if (user == null) {
			res.setStatus(404);
		} else
			res.setStatus(200);
		return user;
	}

	@GetMapping("users/username/{username}")
	public List<User> getUsersByUsername(@PathVariable String username, HttpServletResponse res) {

		List<User> users = svc.getUsersByUsername(username);
		if (users == null) {
			res.setStatus(404);
		} else
			res.setStatus(200);
		return users;
	}
	@GetMapping("api/users/all")
	public List<User> getAllUsers(HttpServletResponse res, Principal principal) {
		List<User> users = null;
		if(principal.getName().equals("admin")) {
		users = svc.getAllUsers();
		}
		if (users == null) {
			res.setStatus(404);
		} else
			res.setStatus(200);
		return users;
	}
	@GetMapping("profile/username/{username}")
	public User findUsersByUsername(@PathVariable String username, HttpServletResponse res) {
		
		User user = svc.findUserByUsername(username);
		if (user == null) {
			res.setStatus(404);
		} else
			res.setStatus(200);
		return user;
	}

	@GetMapping("users/username/")
	public User getUserByUsername(HttpServletResponse res, Principal principal) {
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

	@PutMapping("api/users/{uid}")
	public User updateJob(@RequestBody User u, @PathVariable Integer uid, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		User loggedInUser = svc.getUserByUsername(principal.getName());
		if (loggedInUser != null) {
			if ((!loggedInUser.getEmail().equalsIgnoreCase(u.getEmail())) && (!loggedInUser.getUsername().equals("admin"))) {
				if (!authService.isUserEmailUnique(u.getEmail())) {
					resp.setStatus(403);
					return null;
				}
			}
			if ((!loggedInUser.getUsername().equalsIgnoreCase(u.getUsername())) && (!loggedInUser.getUsername().equals("admin"))) {
				if (!authService.isUserUsernameUnique(u.getUsername())) {
					resp.setStatus(403);
					return null;

				}
			}

			try {
				// try to update the provided post
				u = svc.updateUser(u, uid);
				if (u == null) {
					resp.setStatus(404);
				}
				// if successful, send 200
				resp.setStatus(200);
			} catch (Exception e) {
				// if update fails, return 404 error
				e.printStackTrace();
				resp.setStatus(400);
				// set the returning post to null
				u = null;
			}

		} else {
			u = null;
			resp.setStatus(403);
		}
//		System.err.println("SJDHFSDFJSDJFLKJSDLFJLJSDLFLSDKGJ");
		return u;

	}

	@DeleteMapping("api/users/remove/{id}")
	public void deleteUser(@PathVariable Integer id, HttpServletResponse res) {
		if (!svc.destroy(id)) {
			res.setStatus(404);
		} else
			res.setStatus(200);
	}

}
