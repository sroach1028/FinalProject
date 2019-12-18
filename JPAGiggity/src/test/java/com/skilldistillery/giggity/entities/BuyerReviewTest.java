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

class BuyerReviewTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private BuyerReview buyerReview;
	
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
		buyerReview = em.find(BuyerReview.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		buyerReview = null;
	}

	@Test
	void test() {
		assertNotNull(buyerReview);
	}

}
