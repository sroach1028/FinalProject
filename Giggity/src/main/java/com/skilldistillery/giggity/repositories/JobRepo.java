package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.skilldistillery.giggity.entities.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {
	public Job findById(int id);
	public List<Job> findByTitleLike(String title);
	public List<Job> findByAddress_CityLike(String city);
	public List<Job> findByAddress_State(String city);
	public List<Job> findByAddress_Zip(int zip);
	public List<Job> findBySkill_NameLike(String name);
	public List<Job> findByRemote(Boolean remote);
	public List<Job> findByUser_Id(int id);
	public List<Job> findByUser_UsernameLike(String username);
	public List<Job> findByUser_EmailLike(String email);
	public List<Job> findByBookings_Id(int id);
	public List<Job> findByBuyerReviews_Rating(int rating);
	public List<Job> findByJobBids_Id(int id);
	public List<Job> findByJobImages_Id(int id);
	public List<Job> findByUser_Username(String username);
//	@Query(value="Select * from job j inner join bid b on j.id = b.job_id inner join user u on u.id = b.bidder_id where b.bidder_id = :bidderId", nativeQuery=true)
//	public List<Job> findByBidderId(@Param("bidderId") int bidderId);
//	@Query("Select j from Job j inner join Bid b on j.id = b.job.id inner join User u on u.id = b.bidder.id where b.bidder.id = :bidderId")
//	public List<Job> findByBidderId(@Param("bidderId") int bidderId);
	
}
