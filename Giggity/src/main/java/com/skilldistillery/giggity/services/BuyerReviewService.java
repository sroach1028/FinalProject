package com.skilldistillery.giggity.services;

import com.skilldistillery.giggity.entities.BuyerReview;

public interface BuyerReviewService {

	public BuyerReview findByJobId(int jid);

	public BuyerReview create(BuyerReview buyerReview);

	public BuyerReview update(int bid, BuyerReview buyerReview);

	public boolean delete(int bid);
}
