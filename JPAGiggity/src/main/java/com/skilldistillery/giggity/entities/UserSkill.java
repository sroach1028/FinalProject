package com.skilldistillery.giggity.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_skill")
public class UserSkill {
	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;
	
	private Double price;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "logo_image_id")
	private Image logoImage;
	
	@JsonIgnoreProperties({"userSkills"})
	@ManyToOne
	@JoinColumn(name = "skill_id")
	private Skill skill;

	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "user_skill_image", joinColumns = @JoinColumn(name = "user_skill_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
	private List<Image> portfolioImages;

	// C O N S T R U C T O R
	public UserSkill() {

	}

	// G E T T E R S && S E T T E R S

	public int getId() {
		return id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Image> getPortfolioImages() {
		return portfolioImages;
	}

	public void setPortfolioImages(List<Image> portfolioImages) {
		this.portfolioImages = portfolioImages;
	}

	public Image getLogoImage() {
		return logoImage;
	}

	public void setLogoImage(Image logoImage) {
		this.logoImage = logoImage;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((logoImage == null) ? 0 : logoImage.hashCode());
		result = prime * result + ((portfolioImages == null) ? 0 : portfolioImages.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserSkill other = (UserSkill) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (logoImage == null) {
			if (other.logoImage != null)
				return false;
		} else if (!logoImage.equals(other.logoImage))
			return false;
		if (portfolioImages == null) {
			if (other.portfolioImages != null)
				return false;
		} else if (!portfolioImages.equals(other.portfolioImages))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (skill == null) {
			if (other.skill != null)
				return false;
		} else if (!skill.equals(other.skill))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserSkill [id=");
		builder.append(id);
		builder.append(", description=");
		builder.append(description);
		builder.append(", price=");
		builder.append(price);
		builder.append(", user=");
		builder.append(user);
		builder.append(", logoImage=");
		builder.append(logoImage);
		builder.append(", skill=");
		builder.append(skill);
		builder.append("]");
		return builder.toString();
	}

}
