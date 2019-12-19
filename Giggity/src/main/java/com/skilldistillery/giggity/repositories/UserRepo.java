package com.skilldistillery.giggity.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.giggity.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public User findById(int id);
	public User findByUsername(String username);
//	public List<User> findByUsernameLike(String username);
//	public List<User> findByEmailLike(String email);
//	public List<User> findByBookings_Id(int id);
//	public List<User> findByBookings_StartDateBetween(LocalDate start, LocalDate end);
//	public User findByPosts_Id(int id);
//	public List<User> findByPosts_Poster_UsernameLike(String posterUsername);
//	public User findByBids_Id(int id);
//	public List<User> findBySkillsNameLike(String skillName);
//	public User findByAvatarImage_Id(int id);
//	public List<User> findByAddress_CityLike(String city);
//	public List<User> findByAddress_State(String state);
//	public List<User> findByAddress_Zip(int zip);
//	public List<User> findByJobs_Id(int id);
//	public List<User> findByJobs_TitleLike(String title);

}
