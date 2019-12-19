package com.skilldistillery.giggity.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.BuyerReview;

public interface BuyerReviewRepo extends JpaRepository<BuyerReview, Integer> {
	public BuyerReview findById(int id);
	public BuyerReview findByJob_Id(int id);
	public BuyerReview findByJob_TitleLike(String title);
}
