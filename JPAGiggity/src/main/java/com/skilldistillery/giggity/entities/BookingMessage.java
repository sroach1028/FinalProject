package com.skilldistillery.giggity.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookingMessage {

	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "booking_id")
	private int bookingId;

	private String message;

	@Column(name = "message_date")
	private LocalDateTime messageDate;

	@Column(name = "seller_id")
	private int sellerid;

	@Column(name = "buyer_id")
	private int buyerid;

	// C O N S T R U C T O R
	public BookingMessage() {

	}

	// G E T T E R S && S E T T E R S
	public int getId() {
		return id;
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

	public void setId(int id) {
		this.id = id;
	}

	// H A S H && E Q U A L S
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookingId;
		result = prime * result + buyerid;
		result = prime * result + id;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((messageDate == null) ? 0 : messageDate.hashCode());
		result = prime * result + sellerid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingMessage other = (BookingMessage) obj;
		if (bookingId != other.bookingId)
			return false;
		if (buyerid != other.buyerid)
			return false;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageDate == null) {
			if (other.messageDate != null)
				return false;
		} else if (!messageDate.equals(other.messageDate))
			return false;
		if (sellerid != other.sellerid)
			return false;
		return true;
	}

	// T O S T R I N G
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookingMessage [id=");
		builder.append(id);
		builder.append(", bookingId=");
		builder.append(bookingId);
		builder.append(", message=");
		builder.append(message);
		builder.append(", messageDate=");
		builder.append(messageDate);
		builder.append(", sellerid=");
		builder.append(sellerid);
		builder.append(", buyerid=");
		builder.append(buyerid);
		builder.append("]");
		return builder.toString();
	}

}
