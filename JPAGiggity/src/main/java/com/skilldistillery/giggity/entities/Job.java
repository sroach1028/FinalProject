package com.skilldistillery.giggity.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int price;
	private String description;
	private String title;
	private boolean active;
	private boolean remote;

	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "date_created")
	private LocalDateTime dateCreated;
	@Column(name = "date_updated")
	private LocalDateTime dateUpdated;

	@OneToMany(mappedBy = "address")
	private List<Address> addresses;

	@OneToOne
	@JoinColumn(name = "job_id")
	private BuyerReview buyerReview;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isRemote() {
		return remote;
	}

	public void setRemote(boolean remote) {
		this.remote = remote;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public BuyerReview getBuyerReview() {
		return buyerReview;
	}

	public void setBuyerReview(BuyerReview buyerReview) {
		this.buyerReview = buyerReview;
	}

	public Job(int id, int price, String description, String title, boolean active, boolean remote, String imageUrl,
			LocalDateTime dateCreated, LocalDateTime dateUpdated, List<Address> addresses, BuyerReview buyerReview) {
		super();
		this.id = id;
		this.price = price;
		this.description = description;
		this.title = title;
		this.active = active;
		this.remote = remote;
		this.imageUrl = imageUrl;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
		this.addresses = addresses;
		this.buyerReview = buyerReview;
	}

	public Job() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + price;
		result = prime * result + (remote ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Job other = (Job) obj;
		if (active != other.active)
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (dateUpdated == null) {
			if (other.dateUpdated != null)
				return false;
		} else if (!dateUpdated.equals(other.dateUpdated))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (price != other.price)
			return false;
		if (remote != other.remote)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", price=" + price + ", description=" + description + ", title=" + title + ", active="
				+ active + ", remote=" + remote + ", imageUrl=" + imageUrl + ", dateCreated=" + dateCreated
				+ ", dateUpdated=" + dateUpdated + ", buyerReview=" + buyerReview + "]";
	}

}
