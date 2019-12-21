package com.skilldistillery.giggity.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giggity.entities.Skill;
import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.entities.UserSkill;
import com.skilldistillery.giggity.services.SkillService;
import com.skilldistillery.giggity.services.UserService;
import com.skilldistillery.giggity.services.UserSkillService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4350" })
public class UserSkillController {
	@Autowired
	private UserSkillService svc;
	@Autowired
	private UserService userSvc;
	@Autowired
	private SkillService skillSvc;
	
	@GetMapping("userSkill/{id}")
	public UserSkill getBySkillId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		return svc.getById(id);
	}
	
	@GetMapping("userSkill/user/{uid}")
	public List<UserSkill> getSkillsByUserId(@PathVariable int uid, HttpServletRequest req, HttpServletResponse resp) {
		List<UserSkill> results = new ArrayList<>();
		results = svc.getUserSkillByUserId(uid);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@GetMapping("userSkill/username")
	public List<UserSkill> getSkillsByUsername(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		List<UserSkill> results = new ArrayList<>();
		results = svc.getUserSkillByUsername(principal.getName());
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		System.out.println(results);
		return results;
	}
	@GetMapping("userSkill/skillName/{id}")
	public Skill getSkillNameForUserSkill(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		UserSkill skill = svc.getById(id);
		if (skill == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return skill.getSkill();
	}
	
	@GetMapping("userSkill/skill/{skillName}")
	public List<UserSkill> getSKillsBySkillname(@PathVariable String skillName, HttpServletRequest req, HttpServletResponse resp) {
		List<UserSkill> results = new ArrayList<>();
		results = svc.getByUserSkillBySkillName(skillName);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@GetMapping("userSkill/skill/images/{id}")
	public List<UserSkill> getSKillsByPortfolioImageId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		List<UserSkill> results = new ArrayList<>();
		results = svc.getByUserPorfolioImagesId(id);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@PostMapping("userSkill/{sid}")
	public UserSkill create(@RequestBody UserSkill us, @PathVariable Integer sid, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		User user = userSvc.getUserByUsername(principal.getName());
		us.setUser(user);
		us.setSkill(skillSvc.findById(sid));
		
		try {
			// try to create the provided post
			us = svc.create(us);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(us.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			us = null;
		}

		return us;
	}
	
	@PutMapping("userSkill/{id}/{sid}")
	public UserSkill updateJob(@PathVariable Integer id, @PathVariable Integer sid, @RequestBody UserSkill us, HttpServletRequest req, Principal principal,
			HttpServletResponse resp) {
		User user = userSvc.getUserByUsername(principal.getName());
		us.setUser(user);
		us.setSkill(skillSvc.findById(sid));

		try {
			// try to update the provided post
			us = svc.update(id, us);
			if(us==null) {
				resp.setStatus(404);
			}
			// if successful, send 200
			resp.setStatus(200);
		} catch (Exception e) {
			// if update fails, return 404 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			us = null;
		}

		return us;

	}
	
	@DeleteMapping("userSkill/{id}")
	public void deleteJob(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			if (svc.delete(id)) {
				// if successful, send 204 - no content to send back
				resp.setStatus(204);
			} else {
				// if false, id doesn't exist
				// return 404 - not found
				resp.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}

	}
	
	
}
