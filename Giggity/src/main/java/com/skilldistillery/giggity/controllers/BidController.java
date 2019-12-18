package com.skilldistillery.giggity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giggity.entities.Bid;
import com.skilldistillery.giggity.services.BidService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4350" })
public class BidController {
	
	@Autowired
	BidService bidSvc;
	
	@GetMapping("bids/{jid}")
	public List<Bid> getBids(@PathVariable Integer jobId) {

		return bidSvc.getBidsByJobId(jobId);
	}

}
