package com.skilldistillery.giggity.services;

import java.util.List;

import com.skilldistillery.giggity.entities.BuyerReview;

public interface BuyerReviewService {

	public BuyerReview findById(int id);
	
	public BuyerReview findByJobId(int id);

	public List<BuyerReview> findByJobTitle(String title);
	
	public BuyerReview create(BuyerReview buyerReview);

	public BuyerReview update(int bid, BuyerReview buyerReview);

	public boolean delete(int bid);
}
