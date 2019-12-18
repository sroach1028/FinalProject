package com.skilldistillery.giggity.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.repositories.SellerReviewRepo;

@Service
@Transactional
public class SellerReviewSvcImpl implements SellerReviewService {
	@Autowired
	private SellerReviewRepo sellRevRepo;
}
