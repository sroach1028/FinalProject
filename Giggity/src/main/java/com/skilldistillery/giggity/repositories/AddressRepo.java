package com.skilldistillery.giggity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {
	public Address findById(int id);
//	public List<Address> findByStreetLike(String street);
//	public List<Address> findByCityLike(String city);
//	public List<Address> findByState(String state);
//	public List<Address> findByZip(int zip);
//	
}
