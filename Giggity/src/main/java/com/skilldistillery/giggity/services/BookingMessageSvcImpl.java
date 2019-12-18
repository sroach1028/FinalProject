package com.skilldistillery.giggity.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.repositories.BookingMessageRepo;

@Service
@Transactional
public class BookingMessageSvcImpl implements BookingMessageService {
	
	@Autowired
	BookingMessageRepo bookMessRepo;

}
