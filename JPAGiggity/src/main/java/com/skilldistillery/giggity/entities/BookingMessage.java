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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(LocalDateTime messageDate) {
		this.messageDate = messageDate;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public int getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(int buyerid) {
		this.buyerid = buyerid;
	}
	
	
}
