package com.skilldistillery.giggity.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.Booking;
import com.skilldistillery.giggity.entities.SellerReview;
import com.skilldistillery.giggity.repositories.BookingRepo;
import com.skilldistillery.giggity.repositories.SellerReviewRepo;

@Service
@Transactional
public class SellerReviewSvcImpl implements SellerReviewService {
	@Autowired
	private SellerReviewRepo repo;
	@Autowired
	private BookingRepo bookRepo;

	@Override
	public SellerReview findById(int id) {
		return repo.findById(id);
	}

	@Override
	public SellerReview findByBookingId(int id) {
		return repo.findByBooking_Id(id);
	}

	@Override
	public SellerReview findByJobID(int id) {
		return repo.findByBooking_Job_Id(id);
	}

//	@Override
//	public List<SellerReview> findByJobTitle(String title) {
//		return repo.findByBooking_Job_TitleLike("%" + title + "%");
//	}

	@Override
	public SellerReview create(SellerReview sellerReview, int id) {

		Booking booking = bookRepo.findById(id);

		sellerReview.setReviewDate(LocalDateTime.now());
		sellerReview.setBooking(booking);
		System.err.println("SELLER REVIEW BOOKING: " + sellerReview.getBooking());
		repo.saveAndFlush(sellerReview);
		booking.setSellerReview(sellerReview);
		bookRepo.saveAndFlush(booking);
		return sellerReview;
	}

	@Override
	public SellerReview update(int bid, SellerReview sellerReview) {
		LocalDateTime now = LocalDateTime.now();
		SellerReview update = repo.findById(bid);
		update.setComment(sellerReview.getComment());
		update.setBooking(sellerReview.getBooking());
		update.setRating(sellerReview.getRating());
		update.setReviewDate(now);
		return update;
	}

	@Override
	public boolean delete(int bid) {
		try {
			SellerReview review = repo.findById(bid);
			repo.delete(review);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<SellerReview> findAll() {
		List<SellerReview> allReview = repo.findAll();

		return allReview;
	}

}
