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
	public List<Bid> getBidsByJobId(Integer jobId) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Bid> getBidsByJobId(Integer jobId) {
//		List<Bid> bids = bidRepo.findByJob_Id(jobId);
//		if (bids != null) {
//			return bids;
//		}
//		else
//			return null;
//	}

}
