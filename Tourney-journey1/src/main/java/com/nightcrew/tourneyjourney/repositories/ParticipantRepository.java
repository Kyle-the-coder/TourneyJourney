package com.nightcrew.tourneyjourney.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nightcrew.tourneyjourney.models.Participant;


public interface ParticipantRepository extends CrudRepository<Participant, Long> {
	List<Participant> findAll();
	
	@Query(value="SELECT * FROM EVENT_PARTICIPANTS WHERE EVENT_ID = ?1", nativeQuery=true)
	List<Participant> findByEventId(Long eventId);
}