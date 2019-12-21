package com.skilldistillery.giggity.services;

import java.util.List;

import com.skilldistillery.giggity.entities.Skill;
import com.skilldistillery.giggity.entities.UserSkill;

public interface SkillService {

	List<Skill> findAll();

	Skill findById(int id);

	List <UserSkill> findSkillsByUsername(String name);

}
