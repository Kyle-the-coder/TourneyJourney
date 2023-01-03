package com.nightcrew.tourneyjourney.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="event_participants")	
public class Participant {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	@Size(min=3)
    private String participantName;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="event_id")
    private Event eventParticipant;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User userParticipant;
    
    public Participant() {}
    
    @PrePersist
    protected void onCreate(){
    	this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
    	this.updatedAt = new Date();
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Event getEventParticipant() {
		return eventParticipant;
	}
	public void setEventParticipant(Event eventParticipant) {
		this.eventParticipant = eventParticipant;
	}
	public User getUserParticipant() {
		return userParticipant;
	}
	public void setUserParticipant(User userParticipant) {
		this.userParticipant = userParticipant;
	}
    
	
    
    
}
