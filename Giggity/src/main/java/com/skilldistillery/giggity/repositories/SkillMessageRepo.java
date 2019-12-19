package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.SkillMessage;

public interface SkillMessageRepo extends JpaRepository<SkillMessage, Integer> {
	public SkillMessage findById(int id);
	public List<SkillMessage> findByInReplyTo(int id);
	public List<SkillMessage> findByPoster_Id(int id);
	public List<SkillMessage> findByPoster_UsernameLike(String username);
	public List<SkillMessage> findByPoster_EmailLike(String email);
	public List<SkillMessage> findBySkill_NameLike(String topic);
}
