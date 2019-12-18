package com.skilldistillery.giggity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer> {

}
