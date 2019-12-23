package com.skilldistillery.giggity.services;

import java.util.List;

import com.skilldistillery.giggity.entities.Booking;

public interface BookingService {
	
//	public List<Booking> getActive(int uid);
//	
//	public List<Booking> getTransactionHistory(int uid);
	
	public List<Booking> getAllByJob(int jid);
	
//	public List<Booking> getAllByUser(int uid);
	
	public Booking create(Booking booking);

    public Booking update(int bid, Booking booking);

    public boolean delete(int bid);
}
