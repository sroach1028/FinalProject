package com.skilldistillery.giggity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {

}
