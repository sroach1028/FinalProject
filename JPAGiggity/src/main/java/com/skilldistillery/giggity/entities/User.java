package com.skilldistillery.giggity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	private String username;
	private String email;
	private String password;
	private Boolean enabled;
	private String role;
	private String phone;
	@Column(name="address_id")
	private int addressId;
	@Column(name="image_id")
	private int imageId;
	

	

}
