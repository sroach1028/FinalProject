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

class SkillTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Skill skill;
	
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
		skill = em.find(Skill.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		skill = null;
	}

	@Test
	void test() {
		assertNotNull(skill);
	}
	@Test
	void test2() {
		assertNotNull(skill.getJobSkill());
	}
	@Test
	void test3() {
		assertNotNull(skill.getForumMessages());
	}
	@Test
	void test4() {
		assertNotNull(skill.getUserSkills());
	}

}
