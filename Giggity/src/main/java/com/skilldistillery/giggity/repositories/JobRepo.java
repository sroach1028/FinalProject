package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {
	Job findById(int id);
	List<Job> findByAddress_CityLike(String city);
	List<Job> findByTitle(String title);
	List<Job> findBySkill_Name(String name);
	
}
