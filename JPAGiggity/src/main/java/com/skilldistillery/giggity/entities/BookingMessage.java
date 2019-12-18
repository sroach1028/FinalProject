package com.skilldistillery.giggity.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class BookingMessage {
	private int id;
	private int bookingId;
	private String message;
	
	@Column(name="message_date")
	private LocalDateTime messageDate;
	
	@Column(name="seller_id")
	private int sellerid;

	@Column(name="buyer_id")
	private int buyerid;
}
