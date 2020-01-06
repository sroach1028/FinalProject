package com.skilldistillery.giggity.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.Address;
import com.skilldistillery.giggity.entities.Job;
import com.skilldistillery.giggity.entities.Skill;
import com.skilldistillery.giggity.repositories.JobRepo;

@Transactional
@Service
public class JobSvcImpl implements JobService {

	@Autowired
	JobRepo repo;
	
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
		List<Job> jobs = repo.findByUser_UsernameLike('%' + username + '%');
		return jobs;
	}
	
	@Override
	public List<Job> getByUserEmail(String email) {
		List<Job> jobs = repo.findByUser_EmailLike('%' + email + '%');
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
	public List<Job> getByJobImagesId(int jobImageId) {
		List<Job> jobs = repo.findByJobImages_Id(jobImageId);
		return jobs;
	}

	@Override
	public Job create(Job job) {
		if(job.getImageUrl().length() < 1) {
			job.setImageUrl("http://fullhdwall.com/wp-content/uploads/2016/08/Work-Expression-In-English.jpg");
		}
		job.setDateCreated(LocalDateTime.now());
		job.setDateUpdated(LocalDateTime.now());
		
		return repo.saveAndFlush(job);
	}

	@Override
	public Job update(int jid, Job job) {
		LocalDateTime now = LocalDateTime.now();
		Job update = repo.findById(jid);
		update.setUser(job.getUser());
		update.setAddress(job.getAddress());
		update.setSkill(job.getSkill());
		update.setTitle(job.getTitle());
		update.setDescription(job.getDescription());
		update.setPrice(job.getPrice());
		update.setImageUrl(job.getImageUrl());
		update.setRemote(job.getRemote());
		update.setActive(job.getActive());
//		update.setBookings(job.getBookings());
//		update.setBuyerReviews(job.getBuyerReviews());
//		update.setDateCreated(job.getDateCreated());
		update.setDateUpdated(now);
//		update.setJobBids(job.getJobBids());
//		update.setJobImages(job.getJobImages());
		repo.saveAndFlush(update);
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

	@Override
	public Skill findSkillsByJob(Integer jid) {
		Skill skill = null;
		Optional<Job> opt  = repo.findById(jid);
		if (opt.isPresent()) {
			skill = opt.get().getSkill();
		}
		return skill;
	}
	
	@Override
	public Address findAddressByJob(Integer jid) {
		Address address = null;
		Optional<Job> opt  = repo.findById(jid);
		if (opt.isPresent()) {
			address = opt.get().getAddress();
		}
		return address;
	}

	@Override
	public List<Job> getJobsByUsername(String username) {
		return repo.findByUser_Username(username);
	}

//	@Override
//	public List<Job> getByJobByBidderId(int bidderId) {
//		return repo.findByBidderId(bidderId); 
//	}

}
