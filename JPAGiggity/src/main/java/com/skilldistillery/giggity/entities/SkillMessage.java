package com.skilldistillery.giggity.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SkillMessage {
	// F I E L D S
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "poster_id")
	private int posterId;

	@Column(name = "skill_id")
	private int skillId;

	private String message;

	@Column(name = "message_date")
	private LocalDateTime messageDate;

	@Column(name = "in_reply_to")
	private int inReplyTo;
}
