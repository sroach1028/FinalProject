package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Bid;

public interface BidRepo extends JpaRepository<Bid, Integer> {
	List<Bid> findByJob_Id(int id);
}
