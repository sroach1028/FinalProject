package com.skilldistillery.giggity.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.Skill;
import com.skilldistillery.giggity.entities.UserSkill;
import com.skilldistillery.giggity.repositories.SkillRepo;
import com.skilldistillery.giggity.repositories.UserRepo;
import com.skilldistillery.giggity.repositories.UserSkillRepo;

@Service
@Transactional
public class SkillSvcImpl implements SkillService {

	@Autowired
	private SkillRepo skillRepo;
	@Autowired
	private UserSkillRepo userSkillRepo;

	@Override
	public List<Skill> findAll() {

		List<Skill> skills = skillRepo.findAll();
		if (skills.isEmpty()) {
			return null;
		} else
			return skills;
	}
	@Override
	public Skill findById(int id) {
		return skillRepo.findById(id);
	}
	@Override
	public List<UserSkill> findSkillsByUsername(String name) {
		List<Skill> skills = new ArrayList<>();
		List<UserSkill> userSkills = userSkillRepo.findByUser_Username(name);
//		for (UserSkill userSkill : userSkills) {
//			skills.add(userSkill.getSkill());
//		}
		return userSkills;
	}

}
