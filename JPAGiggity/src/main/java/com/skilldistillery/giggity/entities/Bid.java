package com.skilldistillery.giggity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bid {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean available;

	private String description;

	@Column(name = "bidder_id")
	private int bidderId;

	@Column(name = "job_id")
	private int jobId;

	private boolean accepted;

}
