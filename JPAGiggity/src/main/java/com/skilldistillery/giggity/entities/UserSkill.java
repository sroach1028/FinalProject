package com.skilldistillery.giggity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserSkill {
	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "skill_id")
	private int skillId;

	private String description;

	@Column(name = "logo_image_id")
	private int logoImageId;

	// C O N S T R U C T O R
	public UserSkill() {

	}

	// G E T T E R S && S E T T E R S
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLogoImageId() {
		return logoImageId;
	}

	public void setLogoImageId(int logoImageId) {
		this.logoImageId = logoImageId;
	}

	// H A S H && E Q U A L S
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + logoImageId;
		result = prime * result + skillId;
		result = prime * result + userId;
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
		if (logoImageId != other.logoImageId)
			return false;
		if (skillId != other.skillId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	// T O S T R I N G
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserSkill [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", skillId=");
		builder.append(skillId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", logoImageId=");
		builder.append(logoImageId);
		builder.append("]");
		return builder.toString();
	}

}
