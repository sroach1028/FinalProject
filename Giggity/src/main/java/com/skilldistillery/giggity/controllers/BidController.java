package com.skilldistillery.giggity.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giggity.entities.Bid;
import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.services.BidService;
import com.skilldistillery.giggity.services.JobService;
import com.skilldistillery.giggity.services.UserService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4350" })
public class BidController {
	
	@Autowired
	BidService bidSvc;
	@Autowired
	JobService jobSvc;
	@Autowired
	UserService userSvc;
	
	@GetMapping("bids/{jid}")
	public List<Bid> getBids(@PathVariable Integer jid, HttpServletResponse res) {
		List<Bid> bids = bidSvc.getBidsByJobId(jid);
		if (bids.isEmpty()) {
			res.setStatus(404);
		}
		else
			res.setStatus(200);
		return bids;
	}
	
	@PostMapping("bids/{jid}")
	public Bid addBid(@RequestBody Bid bid, @PathVariable Integer jid, HttpServletResponse res, Principal principal) {
		User bidder = userSvc.getUserByUsername(principal.getName());
		if (jobSvc.getById(jid) == null) {
			res.setStatus(404);
		}
		else {
		bid.setJob(jobSvc.getById(jid));
		bid.setBidder(bidder);
		bid = bidSvc.addBid(bid);
		}
		return bid;
	}
}
