package com.skilldistillery.giggity.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.repositories.SkillMessageRepo;

@Service
@Transactional
public class SkillMessageSvcImpl implements SkillMessageService{
	@Autowired
	private SkillMessageRepo smRepo;
}
