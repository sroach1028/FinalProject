package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.BuyerReview;

public interface BuyerReviewRepo extends JpaRepository<BuyerReview, Integer> {
//	public BuyerReview findById(int id);
//	public List<BuyerReview> findByRating(int rating);
//	public List<BuyerReview> findByJob_Id(int id);
//	public List<BuyerReview> findByJob_TitleLike(String title);
}
