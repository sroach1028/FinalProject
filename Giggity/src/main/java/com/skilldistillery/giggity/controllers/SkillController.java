package com.skilldistillery.giggity.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.giggity.entities.Skill;
import com.skilldistillery.giggity.services.JobService;
import com.skilldistillery.giggity.services.SkillService;

@RestController
@CrossOrigin({ "*", "http://localhost:4350" })
public class SkillController {
	@Autowired
	private SkillService svc;
	@Autowired
	private JobService jobSvc;
	
	@GetMapping("skills")
	public List<Skill> getSkills(HttpServletResponse res) {
		List<Skill> allSkills = svc.findAll();
		if (allSkills.isEmpty()) {
			res.setStatus(404);
		}
		else 
			res.setStatus(200);
		return allSkills;
	}
	@GetMapping("skills/{id}")
	public Skill getSkillById(@PathVariable Integer id, HttpServletResponse res) {
		Skill skill = svc.findById(id);
		if (skill == null) {
			res.setStatus(404);
		}
		else 
			res.setStatus(200);
		return skill;
	}
	@GetMapping("jobskill/{jid}")
	public Skill getSkillByJobId(@PathVariable Integer jid, HttpServletResponse res) {
		Skill skill = jobSvc.findSkillsByJob(jid);
		if (skill == null) {
			res.setStatus(404);
		}
		else 
			res.setStatus(200);
		return skill;
	}
}
