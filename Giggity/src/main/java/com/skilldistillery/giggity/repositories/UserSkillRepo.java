package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.UserSkill;

public interface UserSkillRepo extends JpaRepository<UserSkill, Integer> {
	public UserSkill findById(int id);
	public List<UserSkill> findByUser_Id(int id);
	public List<UserSkill> findByUser_UsernameLike(String username);
	public List<UserSkill> findByUser_Username(String username);
	public List<UserSkill> findBySkill_NameLike(String skillName);
	public List<UserSkill> findByPortfolioImages_Id(int id);
}
