package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.giggity.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
	@Query("SELECT b FROM Booking b WHERE b.completedDate > NOW()")
	List<Booking> queryByActiveGigs();
	@Query("SELECT b FROM Booking b WHERE b.completeDate < NOW()")
	List<Booking> queryByHistory();

}
