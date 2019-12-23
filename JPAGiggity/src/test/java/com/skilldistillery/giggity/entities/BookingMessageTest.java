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

class BookingMessageTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private BookingMessage bMess;
	
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
		bMess = em.find(BookingMessage.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		bMess = null;
	}

	@Test
	void test() {
		assertNotNull(bMess);
	}
	@Test
	void test2() {
		assertEquals("I would like to learn french.", bMess.getBooking().getJob().getTitle());
	}

}
