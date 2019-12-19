package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Skill;

public interface SkillRepo extends JpaRepository<Skill, Integer> {
	public Skill findById(int id);
	public List<Skill> findByNameLike(String skillName);
	public List<Skill> findByJobSkill_TitleLike(String jobTitle);
	public List<Skill> findByForumMessages_Id(int id);
	public List<Skill> findByForumMessages_MessageLike(String message);
	public List<Skill> findByForumMessages_Poster_Id(int id);
	public List<Skill> findByForumMessages_Poster_UsernameLike(String posterName);
	public List<Skill> findByForumMessages_Poster_EmailLike(String posterEmail);
	public List<Skill> findByUserSkills_Id(int id);
}
