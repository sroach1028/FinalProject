package com.skilldistillery.giggity.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.giggity.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {
	public Booking findById(int id);
//	@Query("SELECT b FROM Booking b WHERE b.completeDate is null AND seller.id = :uid")
//	List<Booking> queryActive(@Param("uid") int uid);
//	@Query("SELECT b FROM Booking b WHERE b.completeDate < NOW() AND seller.id = :uid")
//	List<Booking> queryByTransactionHistory(@Param("uid") int uid);
	public List<Booking> findByStartDateBetween(LocalDate start, LocalDate end);
	public List<Booking> findByMessages_Id(int id);
	public List<Booking> findByJob_Id(int id);
	public List<Booking> findByJob_User_Id(int id);
	public List<Booking> findByBid_Bidder_Id(int id);
	public List<Booking> findByJob_TitleLike(String title);
}
