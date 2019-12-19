package com.skilldistillery.giggity.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.Job;
import com.skilldistillery.giggity.entities.UserSkill;
import com.skilldistillery.giggity.repositories.UserSkillRepo;

@Service
@Transactional
public class UserSkillSvcImpl implements UserSkillService {

	@Autowired
	private UserSkillRepo repo;

	@Override
	public UserSkill getById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<UserSkill> getUserSkillByUserId(int id) {
		List<UserSkill> allJobs = repo.findByUser_Id(id);
		return allJobs;
	}

	@Override
	public List<UserSkill> getUserSkillByUsername(String username) {
		List<UserSkill> allJobs = repo.findByUser_UsernameLike("%" + username + "%");
		return allJobs;
	}

	@Override
	public List<UserSkill> getByUserSkillBySkillName(String skillName) {
		List<UserSkill> allJobs = repo.findBySkill_NameLike("%" + skillName + "%");
		return allJobs;
	}

	@Override
	public List<UserSkill> getByUserPorfolioImagesId(int id) {
		List<UserSkill> allJobs = repo.findByPortfolioImages_Id(id);
		return allJobs;
	}

	@Override
	public UserSkill create(UserSkill userSkill) {
		return repo.saveAndFlush(userSkill);
	}

	@Override
	public UserSkill update(int id, UserSkill userSkill) {
		UserSkill update = repo.findById(id);
		update.setUser(userSkill.getUser());
		update.setSkill(userSkill.getSkill());
		if(userSkill.getDescription() != null) {
			update.setDescription(userSkill.getDescription());
		}
		if(userSkill.getLogoImage() != null) {
			update.setLogoImage(userSkill.getLogoImage());
		}
		repo.saveAndFlush(update);
		return update;
	}

	@Override
	public boolean delete(int id) {
		try {
			UserSkill us = repo.findById(id);
			repo.delete(us);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
