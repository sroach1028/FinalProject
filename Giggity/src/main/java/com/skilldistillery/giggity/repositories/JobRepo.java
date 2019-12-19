package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {
//	public Job findById(int id);
//	public List<Job> findByTitleLike(String title);
//	public List<Job> findByAddress_CityLike(String city);
//	public List<Job> findByAddress_State(String city);
//	public List<Job> findByAddress_Zip(int zip);
//	public List<Job> findBySkill_NameLike(String name);
//	public List<Job> findByRemote(Boolean remote);
//	public List<Job> findByUser_Id(int id);
//	public List<Job> findByUser_UsernameLike(String username);
//	public List<Job> findByUser_EmailLike(String email);
//	public List<Job> findByBookings_Id(int id);
//	public List<Job> findByBuyerReviews_Rating(int rating);
//	public List<Job> findByJobBids_Id(int id);
//	public List<Job> findByBookingMessages_Id(int id);
//	public List<Job> findByBookingMessages_MessageLike(String message);
//	public List<Job> findByJobImages_Id(int id);
}
