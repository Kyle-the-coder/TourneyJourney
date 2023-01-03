package com.nightcrew.tourneyjourney.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nightcrew.tourneyjourney.models.Event;



public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findAll();
	List<Event> findByUserIdEquals(Long id);
	
}

