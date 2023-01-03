package com.nightcrew.tourneyjourney.controllers;

import java.io.IOException;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.nightcrew.tourneyjourney.models.Comment;
import com.nightcrew.tourneyjourney.models.Event;
import com.nightcrew.tourneyjourney.models.Participant;
import com.nightcrew.tourneyjourney.models.User;
import com.nightcrew.tourneyjourney.services.CommentService;
import com.nightcrew.tourneyjourney.services.EventService;
import com.nightcrew.tourneyjourney.services.ParticipantService;
import com.nightcrew.tourneyjourney.services.UserService;

@Controller
public class TourneyController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ParticipantService partService;
	
	//Dashboard
	
	@RequestMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		//List for all Events
		List<Event> event = eventService.allEvents();
		model.addAttribute("eventList", event);
		
		//List for the Users Events
		Long userId = (Long) session.getAttribute("userId");
		User userEvent = userService.findUser(userId);
		model.addAttribute("userEventList", userEvent.getEvents());
		
		
	
		
		return "dashboard.jsp";
	}
	
	//Create New Event
	@GetMapping("/create/new/event")
	public String createEvent(@ModelAttribute("newEvent")Event event, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		return "createEvent.jsp";
	}
	
	//Add New Event
	@PostMapping("/create/new/event")
	public String addEvent(@Valid @ModelAttribute("newEvent")Event event, BindingResult result,
			HttpSession session, Model model) throws IOException {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		//process form
		if(result.hasErrors()) {
			
			return "createEvent.jsp";
			
		} else {
			
			MultipartFile multipartFile = event.getPhotoFile();
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			event.setPhotos(fileName);
			
			Event savedEvent = eventService.createEvent(event);
			
			
			String uploadDir = "./posted-img/" + savedEvent.getId();
			
			Path uploadPath = Paths.get(uploadDir);
			
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			
			try (InputStream inputStream = multipartFile.getInputStream()){
				Path filePath = uploadPath.resolve(fileName);
				System.out.println("hello");
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new IOException("Could not save uploaded file: " + fileName);
			}
			
			return "redirect:/dashboard";
		}
			
	}
	
	
	//View One Event Details
	
	@GetMapping("/view/one/event/{id}")
	public String oneEvent(Model model, 
			@PathVariable("id")Long id, HttpSession session, 
			@ModelAttribute("newComment")Comment comments,
			@ModelAttribute("signUp") Participant signUp) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		//Details for the one Event
		Event event = eventService.findEvent(id);
		model.addAttribute("event", event);
		
		//Info for the likes button
		Long userId = (Long) session.getAttribute("userId");
		User loggedInUser = userService.findUser(userId);
		model.addAttribute("loggedInUser" , loggedInUser);
		
		//Methods for the comment section
		List<Comment> comment2 = commentService.findByEventId(id);
		model.addAttribute("commentList", comment2);
		List<Comment> eventComments = eventService.getUserComments(event);
		model.addAttribute("eventComments", eventComments);
	
		
		//List for Sign Up (Participants)
		model.addAttribute("partList", event.getEvent_participants());
		
		//Photo
		Event eventPhoto = (Event) eventService.findEvent(id);
		model.addAttribute("eventPhoto", eventPhoto);
		
		
		System.out.println(eventPhoto);
		return "showOne.jsp";
	}
 		
 		
 		
		
		
		
		
		
	
	

	//Edit Users Posted Event
	@RequestMapping("/edit/event/{id}")
	public String edit(@PathVariable("id")Long id,
			Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		Event event2 = eventService.findEvent(id);
		model.addAttribute("editEvent", event2);
		return "editOne.jsp";
	}
	
	//Add Users Edited Event
	@PutMapping("/update/event/{id}")
	public String editEvent(@Valid @ModelAttribute("editEvent")Event event,
			BindingResult result, @PathVariable("id")Long id, Model model, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "editOne.jsp";
		} else {
			eventService.updateEvent(event);
			return "redirect:/view/one/event/{id}";
		}
	}
	
	
	//Delete Event
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id, HttpSession session) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		eventService.deleteEvent(id);
		return "redirect:/dashboard";
	}
	
	//Likes
	@PutMapping("/likes/{id}")
	public String likes(@PathVariable("id")Long id, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		Long userId = (Long) session.getAttribute("userId");
		User loggedInUser = userService.findUser(userId);
		Event event = eventService.findEvent(id);
		
		eventService.likeEvent(loggedInUser, event);
		return "redirect:/view/one/event/{id}";
	}
	
	//Unlike
	@PutMapping("/unLike/{id}")
	public String unLike(@PathVariable("id")Long id, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		Long userId = (Long) session.getAttribute("userId");
		User loggedInUser = userService.findUser(userId);
		Event event = eventService.findEvent(id);
		
		eventService.unlikeEvent(loggedInUser, event);
		return "redirect:/view/one/event/{id}";
	}
	
	//Comment Save
	@PostMapping("/comment/{eventId}")
	public String comment(@PathVariable("eventId") Event id,
			HttpSession session,
			@Valid @ModelAttribute("newComment")Comment comment, BindingResult result) {
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			System.out.println(result);
			return "showOne.jsp";
		} else {
			System.out.println("comment2");
			//service method to save comment
			commentService.createComment(comment);
			return "redirect:/view/one/event/{eventId}";
		}
	}
	
	
	//Sign Up For Event(Participant)
	@PostMapping("/sign/up/{eventId}")
	public String signUp(@PathVariable("eventId")Event Id, HttpSession session,
			@Valid @ModelAttribute("signUp") Participant signUp, BindingResult result) {
	
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "showOne.jsp";
		} else {
			partService.createParticipant(signUp);
			return "redirect:/view/one/event/{eventId}";
		}
		
	}
	
	//Delete Sign Up (Participant)
	@PutMapping("/delete/part/{id}")
	public String unSignPart(@PathVariable("id")Long id, HttpSession session) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}
		
		Long userId = (Long) session.getAttribute("userId");
		User loggedInUser = userService.findUser(userId);
		Event event = eventService.findEvent(id);
		
		eventService.unPartEvent(loggedInUser, event);
		return "redirect:/view/one/event/{id}";
	}
	
	
	
	
}
		
		
		
	
	

	

