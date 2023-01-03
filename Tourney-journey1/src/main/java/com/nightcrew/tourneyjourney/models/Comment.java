package com.nightcrew.tourneyjourney.models;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="event_id")
	private Event eventComment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User userComment;
	
	@Column(columnDefinition = "TEXT")
	private String comments;
	
	

	public Comment() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEventComment() {
		return eventComment;
	}

	public void setEventComment(Event eventComment) {
		this.eventComment = eventComment;
	}

	public User getUserComment() {
		return userComment;
	}

	public void setUserComment(User userComment) {
		this.userComment = userComment;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	
	
}
