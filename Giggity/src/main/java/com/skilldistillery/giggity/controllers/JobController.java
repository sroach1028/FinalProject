package com.skilldistillery.giggity.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giggity.entities.Job;
import com.skilldistillery.giggity.services.JobService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4350" })
public class JobController {

	@Autowired
	JobService svc;

//	@GetMapping("jobs/{title}")
//	public List<Job> getByCity(@PathVariable String city) {
//		return svc.getByCity(city);
//	}
//
//	@GetMapping("jobs/{title}")
//	public List<Job> getByTitle(@PathVariable String title) {
//		return svc.getByTitle(title);
//	}
//
//	@GetMapping("jobs/{title}")
//	public List<Job> getAll() {
//		return svc.getAll();
//	}
//	
//	@GetMapping("jobs/{username}")
//	public List<Job> getByUsername(@PathVariable String username) {
//		return svc.getByUsername(username);
//	}
}
