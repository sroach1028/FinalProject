package com.skilldistillery.giggity.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class SellerReview {
	private int id;
	private String comment;
	private int rating;
	
	@Column(name="booking_id")
	private int bookingid;
	
	@Column(name="review_date")
	private LocalDateTime reviewDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getBookingid() {
		return bookingid;
	}

	public void setBookingid(int bookingid) {
		this.bookingid = bookingid;
	}

	public LocalDateTime getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	
}
