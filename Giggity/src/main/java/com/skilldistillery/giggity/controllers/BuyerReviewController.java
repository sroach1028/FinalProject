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

import com.skilldistillery.giggity.entities.BuyerReview;
import com.skilldistillery.giggity.services.BuyerReviewService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4350" })
public class BuyerReviewController {
	@Autowired
	BuyerReviewService buyerRevSvc;
	
	@GetMapping("buyerReview/{id}")
	public BuyerReview getById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		return buyerRevSvc.findById(id);
	}
	
	@GetMapping("buyerReview/job/{jid}")
	public BuyerReview getByByJobId(@PathVariable int jid, HttpServletRequest req, HttpServletResponse resp) {
		return buyerRevSvc.findByJobId(jid);
	}
	
	@GetMapping("buyerReview/jobTitle/{title}")
	public List<BuyerReview> getByJobTitle(@PathVariable String title, HttpServletRequest req, HttpServletResponse resp) {
		List<BuyerReview> results = buyerRevSvc.findByJobTitle(title);
		if (results.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		
		return results;
	}
	
	
	@PostMapping("buyerReview")
	public BuyerReview create(@RequestBody BuyerReview br, HttpServletRequest req, HttpServletResponse resp, Principal principal) {

		try {
			// try to create the provided post
			br = buyerRevSvc.create(br);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(br.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			br = null;
		}

		return br;
	}
	
	@PutMapping("buyerReview/{id}")
	public BuyerReview updateJob(@PathVariable Integer id, @RequestBody BuyerReview  br, HttpServletRequest req,
			HttpServletResponse resp) {

		try {
			// try to update the provided post
			br = buyerRevSvc.update(id, br);
			if(br==null) {
				resp.setStatus(404);
			}
			// if successful, send 200
			resp.setStatus(200);
		} catch (Exception e) {
			// if update fails, return 404 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			br = null;
		}

		return br;

	}
	
	@DeleteMapping("buyerReview/{id}")
	public void deleteJob(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			if (buyerRevSvc.delete(id)) {
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
