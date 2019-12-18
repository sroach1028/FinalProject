package com.skilldistillery.giggity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.BookingMessage;

public interface BookingMessageRepo extends JpaRepository<BookingMessage, Integer> {

}
