package com.skilldistillery.giggity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.SellerReview;

public interface SellerReviewRepo extends JpaRepository<SellerReview, Integer> {

}
