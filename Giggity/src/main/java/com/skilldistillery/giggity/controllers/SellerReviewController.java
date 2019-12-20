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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giggity.entities.SellerReview;
import com.skilldistillery.giggity.services.SellerReviewService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4350" })
public class SellerReviewController {

	@Autowired
	SellerReviewService svc;
	
	@GetMapping("sellerReview/{id}")
	public SellerReview getById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		return svc.findById(id);
	}
	
	@GetMapping("sellerReview/booking/{id}")
	public SellerReview getByBookingId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		return svc.findByBookingId(id);
	}
	
	@GetMapping("sellerReview/job/{id}")
	public SellerReview getByJobId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		return svc.findByBookingId(id);
	}
	
//	@GetMapping("sellerReview/jobTitle/{title}")
//	public List<SellerReview> getByJobTitle(@PathVariable String title, HttpServletRequest req, HttpServletResponse resp) {
//		List<SellerReview> sr = svc.findByJobTitle(title);
//		if (sr.size() == 0) {
//			resp.setStatus(404);
//		} else {
//			resp.setStatus(200);
//		}
//		
//		return sr;
//	}
	
	@PostMapping("sellerReview")
	public SellerReview create(@RequestBody SellerReview sr, HttpServletRequest req, HttpServletResponse resp, Principal principal) {

		try {
			// try to create the provided post
			sr = svc.create(sr);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(sr.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			sr = null;
		}

		return sr;
	}
	
	@PutMapping("sellerReview/{id}")
	public SellerReview updateJob(@PathVariable Integer id, @RequestBody SellerReview  sr, HttpServletRequest req,
			HttpServletResponse resp) {

		try {
			// try to update the provided post
			sr = svc.update(id, sr);
			if(sr==null) {
				resp.setStatus(404);
			}
			// if successful, send 200
			resp.setStatus(200);
		} catch (Exception e) {
			// if update fails, return 404 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			sr = null;
		}

		return sr;

	}
	
	@DeleteMapping("sellerReview/{id}")
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
