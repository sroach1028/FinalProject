package com.skilldistillery.giggity.services;

import java.util.List;

import com.skilldistillery.giggity.entities.Skill;

public interface SkillService {

	List<Skill> findAll();

	Skill findById(int id);

}
