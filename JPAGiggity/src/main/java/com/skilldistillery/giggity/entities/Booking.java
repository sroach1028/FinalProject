package com.skilldistillery.giggity.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "start_date")
	private LocalDate startDate;

	@JoinColumn(name = "complete_date")
	private LocalDate completeDate;

	@JoinColumn(name = "expected_complete_date")
	private LocalDate expectedCompleteDate;

	private String notes;
	private boolean accepted;

	@OneToMany
	@JoinColumn(name = "job_id")
	private Job job;

	public int getId() {
		return id;
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

	public Booking(int id, LocalDate startDate, LocalDate completeDate, LocalDate expectedCompleteDate, String notes,
			boolean accepted) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.completeDate = completeDate;
		this.expectedCompleteDate = expectedCompleteDate;
		this.notes = notes;
		this.accepted = accepted;
	}

	public Booking() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accepted ? 1231 : 1237);
		result = prime * result + ((completeDate == null) ? 0 : completeDate.hashCode());
		result = prime * result + ((expectedCompleteDate == null) ? 0 : expectedCompleteDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
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
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
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
		return "Booking [id=" + id + ", startDate=" + startDate + ", completeDate=" + completeDate
				+ ", expectedCompleteDate=" + expectedCompleteDate + ", notes=" + notes + ", accepted=" + accepted
				+ "]";
	}

}
