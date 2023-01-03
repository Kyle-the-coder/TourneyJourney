package com.nightcrew.tourneyjourney.services;

import java.util.List;

import java.util.Optional;


import org.springframework.stereotype.Service;

import com.nightcrew.tourneyjourney.models.Comment;
import com.nightcrew.tourneyjourney.models.Event;
import com.nightcrew.tourneyjourney.models.User;
import com.nightcrew.tourneyjourney.repositories.EventRepository;

@Service
public class EventService {

	private final EventRepository eventRepo;
	
	public EventService(EventRepository eventRepo) {
		this.eventRepo = eventRepo;
	}
	
	public List<Event>allEvents(){
		return eventRepo.findAll();
	}
	
	public List<Event> allUserEvents(Long id){
		return eventRepo.findByUserIdEquals(id);
	}
	
	
	
	
	
	public Event createEvent(Event e) {
		return eventRepo.save(e);
	}
	
	public Event findEvent(Long id) {
		Optional<Event> optionalEvent = eventRepo.findById(id);
		if(optionalEvent.isPresent()) {
			return optionalEvent.get();
		} else {
			return null;
		}
	}
	
	
	public Event updateEvent(Event e) {
		return eventRepo.save(e);
	}
	
	public void deleteEvent(Long id) {
		eventRepo.deleteById(id);
	}
	
	public void likeEvent(User user, Event event) {
		event.getUsers_liked().add(user);
		event.setLikes(event.getLikes() + 1);
		eventRepo.save(event);
	}
	
	public void unlikeEvent(User user, Event event) {
		event.getUsers_liked().remove(user);
		event.setLikes(event.getLikes() - 1);
		eventRepo.save(event);
	}
	
	public List<Comment> getUserComments(Event e){
		return e.getEventComments();
	}
	
	public void unPartEvent(User user, Event event) {
		event.getEvent_participants().remove(user);
		eventRepo.save(event);
	}
	
	
	
}
