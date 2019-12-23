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

class UserSkillTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserSkill userSkill;
	
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
		userSkill = em.find(UserSkill.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		userSkill = null;
	}

	@Test
	@DisplayName("test UserSkill")
	void test1() {
		assertEquals("I can do full stack development with dynamic Spring/Angular UIs.", userSkill.getDescription());
	}
	
	@Test
	@DisplayName("test UserSkill to User")
	void test2() {
		assertEquals("admin", userSkill.getUser().getFirstName());
	}

//	@Test
//	@DisplayName("test UserSkill to Image")
//	void test3() {
//		assertEquals("test", userSkill.getLogoImage().getImageUrl());
//	}
	
//	@Test
//	@DisplayName("test UserSkill to SkillImage")
//	void test4() {
//		assertEquals("test", userSkill.getPortfolioImages().get(0).getImageUrl());
//	}
	
//	@Test
//	@DisplayName("test UserSkill to Skill")
//	void test5() {
//		assertEquals("Coding", userSkill.getSkill().getDescription());
//	}
}
