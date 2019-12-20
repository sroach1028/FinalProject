package com.skilldistillery.giggity.services;

import java.util.List;

import com.skilldistillery.giggity.entities.Bid;

public interface BidService {

	List<Bid> getBidsByJobId(Integer jobId);

	Bid addBid(Bid bid);

}
