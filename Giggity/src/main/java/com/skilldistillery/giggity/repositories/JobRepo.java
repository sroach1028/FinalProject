package com.skilldistillery.giggity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {

}
