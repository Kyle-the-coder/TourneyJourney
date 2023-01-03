package com.nightcrew.tourneyjourney.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="events")
public class Event {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  
    @Size(min=3, max=30, message="Event name must be between 3 and 30 characters")
    private String eventName;
    
   
    @Size(min=5, max=255, message="Details name must be at least 5 characters")
    private String details;
    
  
    @Size(min=3, max=30)
    private String type;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;
    
    
    @Size(min=3, message="Location name must be more than 3 characters")
    private String location;
    
   //Photos relationship
    @Column(nullable = true, length = 64)
    private String photos;
    @Transient
    private MultipartFile photoFile;
    
    
    
    //Create Event Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    
    
    //Comment Relationship
    @OneToMany(mappedBy="eventComment", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Comment> eventComments;
    
    //Likes Relationship
    @NotNull
    private int likes;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="user_events",
    		joinColumns=@JoinColumn(name="event_id"),
    		inverseJoinColumns= @JoinColumn(name="user_id")
    		)
    private List<User> users_liked;
    
    //Sign Up For Event Relationship(Participants)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="event_participants",
    		joinColumns=@JoinColumn(name="event_id"),
    		inverseJoinColumns= @JoinColumn(name="user_id")
    		)
    private List<User> event_participants;
    			
    
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate(){
    	this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
    	this.updatedAt = new Date();
    }
    
    public Event() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUsers_liked() {
		return users_liked;
	}
	public void setUsers_liked(List<User> users_liked) {
		this.users_liked = users_liked;
	}
	
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
	@Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;
         
        return "/posted-img/" + id + "/" + photos;
    }
	public MultipartFile getPhotoFile() {
		return photoFile;
	}
	public void setPhotoFile(MultipartFile photoFile) {
		this.photoFile = photoFile;
	}
	public List<Comment> getEventComments() {
		return eventComments;
	}
	public void setEventComments(List<Comment> eventComments) {
		this.eventComments = eventComments;
	}
	public List<User> getEvent_participants() {
		return event_participants;
	}
	public void setEvent_participants(List<User> event_participants) {
		this.event_participants = event_participants;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    
    
    

}
