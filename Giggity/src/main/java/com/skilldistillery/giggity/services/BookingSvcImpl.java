package com.skilldistillery.giggity.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.repositories.BookingRepo;

@Service
@Transactional
public class BookingSvcImpl implements BookingService {
	
	@Autowired
	private BookingRepo bookingRepo;

}
