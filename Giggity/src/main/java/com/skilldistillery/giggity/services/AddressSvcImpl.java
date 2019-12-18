package com.skilldistillery.giggity.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.giggity.repositories.AddressRepo;

@Transactional
@Service
public class AddressSvcImpl implements AddressService {
	
	@Autowired
	private AddressRepo addRepo;

}
