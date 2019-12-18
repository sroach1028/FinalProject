package com.skilldistillery.giggity.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4350" })
public class BookingController {
//	@Autowired
//	BookingService bookingSvc;
	

}
