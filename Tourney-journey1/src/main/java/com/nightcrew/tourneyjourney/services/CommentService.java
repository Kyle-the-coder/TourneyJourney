package com.nightcrew.tourneyjourney.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nightcrew.tourneyjourney.models.Comment;
import com.nightcrew.tourneyjourney.repositories.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	public CommentService(CommentRepository commentRepo) {
		this.commentRepo = commentRepo;
	}
	
	public List<Comment> allComments(){
		return commentRepo.findAll();
	}
	
	public Comment createComment(Comment c) {
		return commentRepo.save(c);
	}
	
	public Comment findComment(Long id) {
		Optional<Comment>optionalComment = commentRepo.findById(id);
		if(optionalComment.isPresent()) {
			return optionalComment.get();
		} else {
			return null;
		}
	}
	
	public Comment updateComment(Comment c) {
		return commentRepo.save(c);
	}
	
	public void deleteComment(Long id) {
		commentRepo.deleteById(id);
	}
	
	public List<Comment> findByEventId(Long eventId){
		return commentRepo.findByeventId(eventId);
	}


	
		
		
	
}
