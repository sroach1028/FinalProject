package com.skilldistillery.giggity.services;

import java.util.List;

import com.skilldistillery.giggity.entities.Address;
import com.skilldistillery.giggity.entities.Job;
import com.skilldistillery.giggity.entities.Skill;
import com.skilldistillery.giggity.entities.User;

public interface JobService {
	public Job getById(int jobId);
	public List<Job> getAll();
	public  List<Job> getByTitle(String title);
	public  List<Job> getByCity(String city);
	public  List<Job> getByState(String state);
	public  List<Job> getByZip(int zip);
	public  List<Job> getBySkillName(String skillName);
	public  List<Job> getByRemote(Boolean remote);
	public  List<Job> getByUserId(int userId);
	public  List<Job> getByUsername(String username);
	public  List<Job> getByUserEmail(String email);
	public  List<Job> getByBookingId(int bookingId);
	public  List<Job> getByBuyerRating(int rating);
	public  List<Job> getByJobBidId(int bidId);
	public  List<Job> getByJobImagesId(int jobImageId);
	
    public Job create(Job job);

    public Job update(int jid, Job job);

    public boolean delete(int jid);
	public Skill findSkillsByJob(Integer jid);
	public Address findAddressByJob(Integer jid);
	public List<Job> getJobsByUsername(String name);
}
