package com.skilldistillery.giggity.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.repositories.BuyerReviewRepo;

@Transactional
@Service
public class BuyerReviewSvcImpl implements BuyerReviewService {
	
	@Autowired
	BuyerReviewRepo buyerReviewRepo;

}
