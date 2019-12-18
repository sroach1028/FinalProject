package com.skilldistillery.giggity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.SkillMessage;

public interface SkillMessageRepo extends JpaRepository<SkillMessage, Integer> {

}
