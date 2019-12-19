package com.skilldistillery.giggity.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.Job;
import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.repositories.JobRepo;
import com.skilldistillery.giggity.repositories.UserRepo;

@Transactional
@Service
public class JobSvcImpl implements JobService {

	@Autowired
	JobRepo repo;

	@Autowired
	UserRepo userRepo;

//	@Override
//	public List<Job> getAll() {
//		List<Job> allJobs = repo.findAll();
//		return allJobs;
//	}
//
//	@Override
//	public List<Job> getByTitle(String title) {
//		List<Job> jobs = repo.findByTitleLike(title);
//		return jobs;
//	}
//
//	@Override
//	public List<Job> getByCity(String city) {
//		List<Job> jobs = repo.findByAddress_CityLike(city);
//		return jobs;
//	}
//
//	@Override
//	public List<Job> getByUsername(String username) {
//		User user = userRepo.findByUsername(username);
//		List<Job> jobs = repo.findByUser_UsernameLike(user.getUsername());
//		return jobs;
//	}
//
//	@Override
//	public Job create(Job job) {
//		return repo.saveAndFlush(job);
//	}
//
//	@Override
//	public Job update(int jid, Job job) {
//		LocalDateTime now = LocalDateTime.now();
//		Job update = repo.findById(jid);
//		update.setActive(job.getActive());
//		update.setAddress(job.getAddress());
//		update.setBookingMessages(job.getBookingMessages());
//		update.setBookings(job.getBookings());
//		update.setBuyerReviews(job.getBuyerReviews());
//		update.setDateCreated(job.getDateCreated());
//		update.setDateUpdated(now);
//		update.setDescription(job.getDescription());
//		update.setImageUrl(job.getImageUrl());
//		update.setJobBids(job.getJobBids());
//		update.setJobImages(job.getJobImages());
//		update.setPrice(job.getPrice());
//		update.setRemote(job.getRemote());
//		update.setSkill(job.getSkill());
//		update.setTitle(job.getTitle());
//		update.setUser(job.getUser());
//		return update;
//	}
//
//	@Override
//	public boolean delete(int jid) {
//		try {
//			Job job = repo.findById(jid);
//			repo.delete(job);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

}
