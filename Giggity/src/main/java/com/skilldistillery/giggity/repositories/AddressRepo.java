package com.skilldistillery.giggity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
