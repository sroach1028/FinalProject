package com.skilldistillery.giggity.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.BuyerReview;
import com.skilldistillery.giggity.repositories.BuyerReviewRepo;

@Transactional
@Service
public class BuyerReviewSvcImpl implements BuyerReviewService {

	@Autowired
	BuyerReviewRepo repo;

	@Override
	public BuyerReview findById(int id) {
		return repo.findById(id);
	}
	
	@Override
	public BuyerReview findByJobId(int jid) {
		return repo.findByJob_Id(jid);
	}
	
	@Override
	public List<BuyerReview> findByJobTitle(String title) {
		List<BuyerReview> results = repo.findByJob_TitleLike("%" + title + "%" );
		
		return results;
		
	}

	@Override
	public BuyerReview create(BuyerReview sellerReview) {
		return repo.saveAndFlush(sellerReview);
	}

	@Override
	public BuyerReview update(int bid, BuyerReview buyerReview) {
		LocalDateTime now = LocalDateTime.now();
		BuyerReview update = repo.findById(bid);
		update.setComment(buyerReview.getComment());
		update.setJob(buyerReview.getJob());
		update.setRating(buyerReview.getRating());
		update.setReviewDate(now);
		return update;
	}

	@Override
	public boolean delete(int bid) {
		try {
			BuyerReview review = repo.findById(bid);
			repo.delete(review);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	

}
