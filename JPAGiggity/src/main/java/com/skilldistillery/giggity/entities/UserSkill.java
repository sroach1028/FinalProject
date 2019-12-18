package com.skilldistillery.giggity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserSkill {
	// F I E L D S
	@Column(name="user_skill_id")
	private int userSkillId;
	
	@Column(name="image_id")
	private int imageId;

}
