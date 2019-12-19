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
	
	@Override
	public Job getById(int jobId) {
		return repo.findById(jobId);
	}

	@Override
	public List<Job> getAll() {
		List<Job> allJobs = repo.findAll();
		return allJobs;
	}

	@Override
	public List<Job> getByTitle(String title) {
		List<Job> jobs = repo.findByTitleLike('%' + title + '%');
		return jobs;
	}

	@Override
	public List<Job> getByCity(String city) {
		List<Job> jobs = repo.findByAddress_CityLike('%' + city + '%');
		return jobs;
	}
	
	@Override
	public List<Job> getByState(String state) {
		List<Job> jobs = repo.findByAddress_State(state);
		return jobs;
	}
	
	@Override
	public List<Job> getByZip(int zip) {
		List<Job> jobs = repo.findByAddress_Zip(zip);
		return jobs;
	}
	
	@Override
	public List<Job> getBySkillName(String skillName) {
		List<Job> jobs = repo.findBySkill_NameLike("%" + skillName + "%");
		return jobs;
				
	}
	
	@Override
	public List<Job> getByRemote(Boolean remote) {
		List<Job> jobs = repo.findByRemote(remote);
		return jobs;		
	}
	
	@Override
	public List<Job> getByUserId(int userId) {
		List<Job> jobs = repo.findByUser_Id(userId);
		return jobs;		
	}

	@Override
	public List<Job> getByUsername(String username) {
		User user = userRepo.findByUsername('%' + username + '%');
		List<Job> jobs = repo.findByUser_UsernameLike(user.getUsername());
		return jobs;
	}
	
	@Override
	public List<Job> getByUserEmail(String email) {
		List<Job> jobs = repo.findByUser_UsernameLike('%' + email + '%');
		return jobs;
	}
	
	@Override
	public List<Job> getByBookingId(int bookingId) {
		List<Job> jobs = repo.findByBookings_Id(bookingId);
		return jobs;
	}
	
	@Override
	public List<Job> getByBuyerRating(int rating) {
		List<Job> jobs = repo.findByBuyerReviews_Rating(rating);
		return jobs;
	}
	
	@Override
	public List<Job> getByJobBidId(int bidId) {
		List<Job> jobs = repo.findByJobBids_Id(bidId);
		return jobs;		
	}
	
	@Override
	public List<Job> getByBookingMessageId(int bookingMessageId) {
		List<Job> jobs = repo.findByBookingMessages_Id(bookingMessageId);
		return jobs;
	}
	
	@Override
	public List<Job> getByBookingMessage(String bookingMessage) {
		List<Job> jobs = repo.findByBookingMessages_MessageLike(bookingMessage);
		return jobs;
	}
	
	@Override
	public List<Job> getByJobImagesId(int jobImageId) {
		List<Job> jobs = repo.findByJobImages_Id(jobImageId);
		return jobs;
	}

	@Override
	public Job create(Job job) {
		return repo.saveAndFlush(job);
	}

	@Override
	public Job update(int jid, Job job) {
		LocalDateTime now = LocalDateTime.now();
		Job update = repo.findById(jid);
		update.setActive(job.getActive());
		update.setAddress(job.getAddress());
		update.setBookingMessages(job.getBookingMessages());
		update.setBookings(job.getBookings());
		update.setBuyerReviews(job.getBuyerReviews());
		update.setDateCreated(job.getDateCreated());
		update.setDateUpdated(now);
		update.setDescription(job.getDescription());
		update.setImageUrl(job.getImageUrl());
		update.setJobBids(job.getJobBids());
		update.setJobImages(job.getJobImages());
		update.setPrice(job.getPrice());
		update.setRemote(job.getRemote());
		update.setSkill(job.getSkill());
		update.setTitle(job.getTitle());
		update.setUser(job.getUser());
		return update;
	}

	@Override
	public boolean delete(int jid) {
		try {
			Job job = repo.findById(jid);
			repo.delete(job);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
