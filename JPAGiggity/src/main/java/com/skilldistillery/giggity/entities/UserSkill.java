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

@Entity
@Table(name="user_skill")
public class UserSkill {
	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "logo_image_id")
	private Image logoImage;

	@ManyToOne
	@JoinColumn(name = "skill_id")
	private Skill skill;

	@ManyToMany
	@JoinTable(name = "user_skill_image", joinColumns = @JoinColumn(name = "user_skill_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
	private List<Image> portfolioImages;

	// C O N S T R U C T O R
	public UserSkill() {

	}

	// G E T T E R S && S E T T E R S

	public int getId() {
		return id;
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

	// H A S H && E Q U A L S
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
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
		return true;
	}

	// T O S T R I N G
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserSkill [id=");
		builder.append(id);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
