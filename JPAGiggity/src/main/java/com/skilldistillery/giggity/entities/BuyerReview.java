package com.skilldistillery.giggity.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="buyer_review")
public class BuyerReview {

	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String comment;

	private int rating;

	@Column(name = "review_date")
	private LocalDateTime reviewDate;

	@ManyToOne
	@JoinColumn(name = "job_id")
	private Job job;

	// C O N S T R U C T O R
	public BuyerReview() {

	}

	// G E T T E R S && S E T T E R S

	public int getId() {
		return id;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
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


	public LocalDateTime getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}

	// H A S H && E Q U A L S
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + id;
		result = prime * result + rating;
		result = prime * result + ((reviewDate == null) ? 0 : reviewDate.hashCode());
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
		BuyerReview other = (BuyerReview) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (id != other.id)
			return false;
		if (rating != other.rating)
			return false;
		if (reviewDate == null) {
			if (other.reviewDate != null)
				return false;
		} else if (!reviewDate.equals(other.reviewDate))
			return false;
		return true;
	}

	// T O S T R I N G
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BuyerReview [id=");
		builder.append(id);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", reviewDate=");
		builder.append(reviewDate);
		builder.append("]");
		return builder.toString();
	}

}
