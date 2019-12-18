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
}
