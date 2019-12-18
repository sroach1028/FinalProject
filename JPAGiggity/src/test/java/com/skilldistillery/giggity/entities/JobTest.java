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

class JobTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Job job;
	
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
		job = em.find(Job.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		job = null;
	}

	@Test
	void test() {
		assertNotNull(job);
		assertEquals("test", job.getTitle());
	}
	@Test
	void test2() {
		assertEquals("Denver", job.getAddress().getCity());
	}
	@Test
	void test3() {
		assertEquals("test", job.getUser().getFirstName());
	}
	@Test
	void test4() {
		assertEquals("Software Development", job.getSkill().getName());
	}
	@Test
	void test5() {
		assertEquals("test", job.getBookings().get(0).getJob().getTitle());
	}
	@Test
	void test6() {
		assertEquals(1, job.getBuyerReviews().get(0).getRating());
	}
	@Test
	void test7() {
		assertEquals(0.0, job.getJobBids().get(0).getBidAmount());
	}
	@Test
	void test8() {
		assertEquals(1, job.getBookingMessages().size());
	}
	@Test
	void test9() {
		assertEquals(1, job.getJobImages().size());
	}

}
