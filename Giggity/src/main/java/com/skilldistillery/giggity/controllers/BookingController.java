package com.skilldistillery.giggity.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giggity.entities.Booking;
import com.skilldistillery.giggity.services.BookingService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4350" })
public class BookingController {
	@Autowired
	private BookingService svc;

	@GetMapping("booking/active")
	public List<Booking> getActive(HttpServletRequest req, HttpServletResponse resp) {
		return svc.getActive();
	}

	@GetMapping("booking/history")
	public List<Booking> getTransactionHistory(HttpServletRequest req, HttpServletResponse resp) {
		return svc.getTransactionHistory();
	}

	@GetMapping("booking/job/{jid}")
	public List<Booking> getAllByJob(@PathVariable int jid, HttpServletRequest req, HttpServletResponse resp) {
		return svc.getAllByJob(jid);
	}

	@GetMapping("booking/user/{uid}")
	public List<Booking> getByUser(@PathVariable int uid, HttpServletRequest req, HttpServletResponse resp) {
		return svc.getAllByUser(uid);
	}

	@PostMapping("booking")
	public Booking create(@RequestBody Booking booking, HttpServletRequest req, HttpServletResponse resp) {

		try {
			// try to create the provided post
			booking = svc.create(booking);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(booking.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			booking = null;
		}
		return booking;
	}

	@PutMapping("jobs/{id}")
	public Booking updateBooking(@PathVariable Integer id, @RequestBody Booking booking, HttpServletRequest req,
			HttpServletResponse resp) {

		try {
			// try to update the provided post
			booking = svc.update(id, booking);
			if (booking == null) {
				resp.setStatus(404);
			}
			// if successful, send 200
			resp.setStatus(200);
		} catch (Exception e) {
			// if update fails, return 404 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			booking = null;
		}
		return booking;
	}

	@DeleteMapping("booking/{id}")
	public void deleteBooking(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {

		try {
			if (svc.delete(id)) {
				// if successful, send 204 - no content to send back
				resp.setStatus(204);
			} else {
				// if false, id doesn't exist
				// return 404 - not found
				resp.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}

	}
}
