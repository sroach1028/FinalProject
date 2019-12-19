package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.BookingMessage;


public interface BookingMessageRepo extends JpaRepository<BookingMessage, Integer> {
//	public BookingMessage findById(int id);
//	public List<BookingMessage> findByBooking_Id(int id);
//	public List<BookingMessage> findByJob_Id(int id);
//	public List<BookingMessage> findByJob_TitleLike(String jobTitle);

}
