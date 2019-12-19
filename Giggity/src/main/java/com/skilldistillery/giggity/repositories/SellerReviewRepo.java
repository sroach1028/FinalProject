package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.SellerReview;

public interface SellerReviewRepo extends JpaRepository<SellerReview, Integer> {
//	public SellerReview findById(int id);
//	public List<SellerReview> findByRating(int id);
//	public List<SellerReview> findByBooking_id(int id);

}
