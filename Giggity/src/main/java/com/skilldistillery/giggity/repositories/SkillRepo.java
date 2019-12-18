package com.skilldistillery.giggity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Skill;

public interface SkillRepo extends JpaRepository<Skill, Integer> {

}
