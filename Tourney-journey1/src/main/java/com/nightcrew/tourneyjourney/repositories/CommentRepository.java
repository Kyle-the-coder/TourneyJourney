package com.nightcrew.tourneyjourney.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nightcrew.tourneyjourney.models.Comment;


public interface CommentRepository extends CrudRepository<Comment, Long> {
	List<Comment> findAll();

	@Query(value="SELECT * FROM COMMENTS WHERE EVENT_ID = ?1", nativeQuery=true)
	List<Comment> findByeventId(Long eventId);
	
	
	
}
