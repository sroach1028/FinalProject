package com.skilldistillery.giggity.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.Bid;
import com.skilldistillery.giggity.repositories.BidRepo;

@Service
@Transactional
public class BidSvcImpl implements BidService {
	
	@Autowired
	BidRepo bidRepo;
	
	@Override
	public List<Bid> getBidsByBidderId(int bidderId) {
		List<Bid> bids = bidRepo.findByBidderId(bidderId);
		return bids;
	}

	@Override
	public List<Bid> getBidsByJobId(Integer jobId) {
		List<Bid> bids = bidRepo.findByJob_Id(jobId);
		if (bids != null) {
			return bids;
		}
		else
			return null;
	}

	@Override
	public Bid addBid(Bid bid) {
		return bidRepo.saveAndFlush(bid);
	}

	@Override
	public Bid updateBid(int bidID, Bid bid) {
		Bid toUpdate = bidRepo.findById(bidID);
		
		if(toUpdate != null) {
			toUpdate.setAvailable(bid.getAvailable());
			toUpdate.setBidAmount(bid.getBidAmount());
			toUpdate.setDescription(bid.getDescription());
			toUpdate.setAccepted(bid.getAccepted());
			toUpdate.setRejected(bid.getRejected());
			toUpdate.setBookings(bid.getBookings());
			toUpdate.setBidder(bid.getBidder());			
		}
		
		bidRepo.saveAndFlush(toUpdate);
		
		return toUpdate;
	}

	@Override
	public boolean deleteBid(int bidID) {
		Bid toDelete = bidRepo.findById(bidID);
		
		if(toDelete != null) {
			toDelete.setRejected(true);
			toDelete.setAccepted(false);
		}
		
		bidRepo.saveAndFlush(toDelete);
		
		return true;
		
	}

	
	
	

}
