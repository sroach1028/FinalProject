package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Bid;
import com.skilldistillery.giggity.entities.User;

public interface BidRepo extends JpaRepository<Bid, Integer> {
	public Bid findById(int id);
//	public List<Bid> findByAvailable(Boolean avail);
//	public List<Bid> findByBidAmountBetween(Double lowAmt, Double highAmt);
//	public List<Bid> findByDescriptionLike(String desc);
//	public List<Bid> findByAccepted(Boolean accept);
//	public List<Bid> findByUser_Id(int id);
//	public List<Bid> findByUser_UsernameLike(String username);
//	public List<Bid> findByUser_EmailLike(String email);
	public List<Bid> findByJob_Id(int id);
//	public List<Bid> findByJob_TitleLike(String jobTitle);
}
