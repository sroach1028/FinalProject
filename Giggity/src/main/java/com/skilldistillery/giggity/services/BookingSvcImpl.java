package com.skilldistillery.giggity.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.entities.Booking;
import com.skilldistillery.giggity.entities.User;
import com.skilldistillery.giggity.repositories.BookingRepo;
import com.skilldistillery.giggity.repositories.UserRepo;

@Service
@Transactional
public class BookingSvcImpl implements BookingService {

	@Autowired
	private BookingRepo repo;
	

	@Override
	public List<Booking> getActive(int uid) {
		return repo.queryActive(uid);
	}

	@Override
	public List<Booking> getTransactionHistory(int uid) {
		return repo.queryByTransactionHistory(uid);
	}

	@Override
	public List<Booking> getAllByJob(int jid) {
		return repo.findByJob_Id(jid);
	}

	@Override
	public List<Booking> getAllByUser(int uid) {
		return repo.findBySeller_Id(uid);
	}

	@Override
	public Booking create(Booking booking) {
		return repo.saveAndFlush(booking);
	}

	@Override
	public Booking update(int bid, Booking booking) {
		Booking update = repo.findById(bid);
		update.setAccepted(booking.isAccepted());
		update.setCompleteDate(booking.getCompleteDate());
		update.setExpectedCompleteDate(booking.getExpectedCompleteDate());
		update.setJob(booking.getJob());
		update.setMessages(booking.getMessages());
		update.setNotes(booking.getNotes());
		update.setSeller(booking.getSeller());
		update.setSellerReviews(booking.getSellerReviews());
		update.setStartDate(booking.getStartDate());
		return null;
	}

	@Override
	public boolean delete(int bid) {
		try {
			Booking booking = repo.findById(bid);
			repo.delete(booking);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
