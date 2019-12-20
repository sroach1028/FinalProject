package com.skilldistillery.giggity.services;

import java.util.List;

import com.skilldistillery.giggity.entities.SellerReview;


public interface SellerReviewService {
public SellerReview findById(int id);
	
	public SellerReview findByBookingId(int id);

	public SellerReview findByJobID(int id);
	
//	public List<SellerReview> findByJobTitle(String title);
	
	public SellerReview create(SellerReview buyerReview);

	public SellerReview update(int bid, SellerReview sellerReview);

	public boolean delete(int bid);

}
