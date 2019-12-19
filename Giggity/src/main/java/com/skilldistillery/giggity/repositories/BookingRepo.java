package com.skilldistillery.giggity.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
//	public Booking findById(int id);
//	public List<Booking> findByStartDateBetween(LocalDate start, LocalDate end);
//	public List<Booking> findBySeller_Id(int id);
//	public List<Booking> findByMessages_Id(int id);
//	public List<Booking> findByJob_Id(int id);
//	public List<Booking> findByJob_TitleLike(String title);
}
