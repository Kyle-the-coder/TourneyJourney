package com.nightcrew.tourneyjourney.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nightcrew.tourneyjourney.models.Participant;
import com.nightcrew.tourneyjourney.repositories.ParticipantRepository;

@Service
public class ParticipantService {
	@Autowired
	private ParticipantRepository partRepo;
	
	public ParticipantService(ParticipantRepository partRepo) {
		this.partRepo = partRepo;
	}
	
	public List<Participant> allParticipants(){
		return partRepo.findAll();
	}
	
	public Participant createParticipant(Participant p) {
		return partRepo.save(p);
	}
	
	public Participant findPart(Long id) {
		Optional<Participant>optionalPart = partRepo.findById(id);
		if(optionalPart.isPresent()) {
			return optionalPart.get();
		} else {
			return null;
		}
	}
	
	
	public Participant updatePart(Participant p) {
		return partRepo.save(p);
	}
	
	public void deletePart(Long id) {
		partRepo.deleteById(id);
	}
	
	
	public List<Participant> findPartByEventId(Long eventId){
		return partRepo.findByEventId(eventId);
	}
	
	
	
	
	
}
