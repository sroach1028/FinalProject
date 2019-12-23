package com.skilldistillery.giggity.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("GiggityPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test() {
		assertEquals("admin", user.getUsername());
	}
//	@Test
//	void test1() {
//		assertEquals(1, user.getBookings().size());
//	}
	@Test
	void test2() {
		assertEquals(1, user.getPosts().size());
	}
	@Test
	void test3() {
		assertEquals(0, user.getBids().size());
	}
	@Test
	void test4() {
		assertEquals(1, user.getSkills().size());
	}
	@Test
	void test5() {
		assertNotNull(user.getAvatarImage());
	}
	@Test
	void test6() {
		assertEquals("Denver", user.getAddress().getCity());
	}
	@Test
	void test7() {
		assertEquals(0, user.getJobs().size());
	}
	@Test
	void test8() {
		System.out.println(user.getSkills().get(0).getSkill().getDescription());
		assertEquals("I can do full stack development with dynamic Spring/Angular UIs.", user.getSkills().get(0).getDescription());
	}
	@Test
	void test9() {
		assertNotNull(user.getSkills().get(0).getLogoImage());
	}

	
	
}
