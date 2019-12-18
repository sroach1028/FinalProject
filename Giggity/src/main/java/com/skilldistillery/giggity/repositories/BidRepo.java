package com.skilldistillery.giggity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Bid;

public interface BidRepo extends JpaRepository<Bid, Integer> {

}
