package com.skilldistillery.giggity.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SkillMessageTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private SkillMessage skillMessage;
	
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
		skillMessage = em.find(SkillMessage.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		skillMessage = null;
	}

	@Test
	@DisplayName("test SkillMessage")
	void test1() {
		assertEquals("test", skillMessage.getMessage());
	}
	
	@Test
	@DisplayName("test SkillMessage to Skill")
	void test2() {
		assertEquals("Coding", skillMessage.getSkill().getDescription());
	}

	@Test
	@DisplayName("test SkillMessage to User")
	void test3() {
		assertEquals("test", skillMessage.getPoster().getFirstName());
	}
	
}
