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
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giggity.entities.Address;
import com.skilldistillery.giggity.entities.Job;
import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.services.JobService;
import com.skilldistillery.giggity.services.UserService;

@RestController
@CrossOrigin({ "*", "http://localhost:4350" })
public class JobController {

	@Autowired
	JobService svc;
	@Autowired
	UserService userSvc;

	@GetMapping("jobs/{id}")
	public Job getByJobId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		return svc.getById(id);
	}
	
	@GetMapping("jobs")
	public List<Job> getAll(HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getAll();
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@GetMapping("jobs/title/{title}")
	public List<Job> getByTitle(@PathVariable String title, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByTitle(title);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;		
	}
	
	@GetMapping("jobs/city/{city}")
	public List<Job> getByCity(@PathVariable String city, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByCity(city);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;	
	}
	
	@GetMapping("jobs/state/{state}")
	public List<Job> getByState(@PathVariable String state, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByState(state);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@GetMapping("jobs/zip/{zip}")
	public List<Job> getByZip(@PathVariable int zip, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByZip(zip);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}

	@GetMapping("jobs/skill/{skillName}")
	public List<Job> getBySkillName(@PathVariable String skillName, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getBySkillName(skillName);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@GetMapping("jobs/remote/{remote}")
	public List<Job> getByRemote(@PathVariable Boolean remote, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByRemote(remote);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@GetMapping("jobs/user/{userId}")
	public List<Job>  getByUserId(@PathVariable int userId, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByUserId(userId);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;	
	}
	
	@GetMapping("jobs/username/{username}")
	public List<Job> getByUsername(@PathVariable String username, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByUsername(username);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	@GetMapping("api/jobs/username")
	public List<Job> getByUsername(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		List<Job> results = new ArrayList<>();
		results = svc.getJobsByUsername(principal.getName());
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@GetMapping("jobs/userEmail/{email}")
	public List<Job> getByUserEmail(@PathVariable String email, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByUserEmail(email);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@GetMapping("jobs/bookingId/{bookingId}")
	public List<Job> getByBookingId(@PathVariable int bookingId, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByBookingId(bookingId);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@GetMapping("jobs/buyerRating/{rating}")
	public List<Job> getByBuyerRating(@PathVariable int rating, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByBuyerRating(rating);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
	@GetMapping("jobs/jobBidId/{bidId}")
	public List<Job> getByBidId(@PathVariable int bidId, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByJobBidId(bidId);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return results;
	}
	
//	@GetMapping("jobs/jobBidderId/{bidderId}")
//	public List<Job> getByBidderID(@PathVariable int bidderId, HttpServletRequest req, HttpServletResponse resp) {
//		List<Job> results = new ArrayList<>();
//		results = svc.getByJobByBidderId(bidderId);
//		if (results.size() == 0) {
//			resp.setStatus(404);
//		} else {
//			resp.setStatus(200);
//		}
//		return results;
//	}
	
	@GetMapping("jobs/jobImage/{jobImageId}")
	public List<Job> getByJobImageId(@PathVariable int jobImageId, HttpServletRequest req, HttpServletResponse resp) {
		List<Job> results = new ArrayList<>();
		results = svc.getByJobImagesId(jobImageId);
		return results;
	}
	@GetMapping("jobs/address/{jid}")
	public Address getAddressByJob(@PathVariable int jid, HttpServletRequest req, HttpServletResponse resp) {
		Address address = svc.findAddressByJob(jid);
		if (address == null) {
			resp.setStatus(404);
		}
		return address;
	}
	
	@PostMapping("jobs/create")
	public Job create(@RequestBody Job j, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		System.out.println("hello");
		System.out.println(j);
		try {
			System.out.println("hello");
			// try to create the provided post
			User u = userSvc.getUserByUsername(principal.getName());
			j.setUser(u);
			j.setAddress(u.getAddress());
			j = svc.create(j);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(j.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			j = null;
		}

		return j;
	}
	
	@PutMapping("jobs/{id}")
	public Job updateJob(@PathVariable Integer id, @RequestBody Job j, HttpServletRequest req,
			HttpServletResponse resp) {
		System.err.println("IN UPDATE JOB*************************");
		try {
			// try to update the provided post
			j = svc.update(id, j);
			if(j==null) {
				resp.setStatus(404);
			}
			// if successful, send 200
			resp.setStatus(200);
		} catch (Exception e) {
			// if update fails, return 404 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			j = null;
		}

		return j;

	}
	
	@DeleteMapping("jobs/{id}")
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
