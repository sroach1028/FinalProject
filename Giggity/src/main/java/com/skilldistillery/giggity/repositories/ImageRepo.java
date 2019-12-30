package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {
//	public Image findById(int id);
//	public List<Image> findByJobs_Id(int id);
//	public List<Image> findByJobs_TitleLike(String title);
//	public List<Image> findBySkills_Id(int id);
//	public List<Image> findBySkills_NameLike(String skill);
	public Image findById(int id);
	
}
