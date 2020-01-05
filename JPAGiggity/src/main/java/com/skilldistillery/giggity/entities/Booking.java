package com.skilldistillery.giggity.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Booking {

	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "bid_id")
	private Bid bid;
	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "complete_date")
	private LocalDate completeDate;

	@Column(name = "expected_complete_date")
	private LocalDate expectedCompleteDate;

	private String notes;

	private boolean accepted;

	
	@OneToOne(mappedBy = "booking")
	private SellerReview sellerReview;

	@JsonIgnore
	@OneToMany(mappedBy = "booking")
	private List<BookingMessage> messages;

	// C O N S T R U C T O R
	public Booking() {

	}

	// G E T T E R S && S E T T E R S
	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public int getId() {
		return id;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<BookingMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<BookingMessage> messages) {
		this.messages = messages;
	}

	public SellerReview getSellerReview() {
		return sellerReview;
	}

	public void setSellerReview(SellerReview sellerReview) {
		this.sellerReview = sellerReview;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(LocalDate completeDate) {
		this.completeDate = completeDate;
	}

	public LocalDate getExpectedCompleteDate() {
		return expectedCompleteDate;
	}

	public void setExpectedCompleteDate(LocalDate expectedCompleteDate) {
		this.expectedCompleteDate = expectedCompleteDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accepted ? 1231 : 1237);
		result = prime * result + ((bid == null) ? 0 : bid.hashCode());
		result = prime * result + ((completeDate == null) ? 0 : completeDate.hashCode());
		result = prime * result + ((expectedCompleteDate == null) ? 0 : expectedCompleteDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((sellerReview == null) ? 0 : sellerReview.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		Booking other = (Booking) obj;
		if (accepted != other.accepted)
			return false;
		if (bid == null) {
			if (other.bid != null)
				return false;
		} else if (!bid.equals(other.bid))
			return false;
		if (completeDate == null) {
			if (other.completeDate != null)
				return false;
		} else if (!completeDate.equals(other.completeDate))
			return false;
		if (expectedCompleteDate == null) {
			if (other.expectedCompleteDate != null)
				return false;
		} else if (!expectedCompleteDate.equals(other.expectedCompleteDate))
			return false;
		if (id != other.id)
			return false;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (sellerReview == null) {
			if (other.sellerReview != null)
				return false;
		} else if (!sellerReview.equals(other.sellerReview))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Booking [id=");
		builder.append(id);
		builder.append(", bid=");
		builder.append(bid);
		builder.append(", job=");
		builder.append(job);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", completeDate=");
		builder.append(completeDate);
		builder.append(", expectedCompleteDate=");
		builder.append(expectedCompleteDate);
		builder.append(", notes=");
		builder.append(notes);
		builder.append(", accepted=");
		builder.append(accepted);
		builder.append("]");
		return builder.toString();
	}

}
