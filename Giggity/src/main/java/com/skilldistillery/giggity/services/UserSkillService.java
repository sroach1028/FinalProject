package com.skilldistillery.giggity.services;

import java.util.List;

import com.skilldistillery.giggity.entities.UserSkill;

public interface UserSkillService {
	public UserSkill getById(int id);

	public List<UserSkill> getUserSkillByUserId(int id);

	public List<UserSkill> getUserSkillByUsername(String username);

	public List<UserSkill> getByUserSkillBySkillName(String skillName);

	public List<UserSkill> getByUserPorfolioImagesId(int id);

	public UserSkill create(UserSkill userSkill);

	public UserSkill update(int id, UserSkill userSkill);

	public boolean delete(int id);
}
