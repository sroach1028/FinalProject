package com.skilldistillery.giggity.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.repositories.SkillRepo;

@Service
@Transactional
public class SkillSvcImpl implements SkillService{

	@Autowired
	private SkillRepo skillRepo;
}
