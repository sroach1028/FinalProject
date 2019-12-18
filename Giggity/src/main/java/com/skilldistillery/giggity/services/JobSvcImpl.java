package com.skilldistillery.giggity.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.repositories.JobRepo;

@Transactional
@Service
public class JobSvcImpl implements JobService {
	
	@Autowired
	JobRepo jobRepo;

}
