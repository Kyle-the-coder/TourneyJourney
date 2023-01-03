package com.nightcrew.tourneyjourney.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.nightcrew.tourneyjourney.models.LoginUser;
import com.nightcrew.tourneyjourney.models.User;
import com.nightcrew.tourneyjourney.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	
	// TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
        // TO-DO: Additional validations!
    	Optional<User> optionalUser = userRepo.findByEmail(newUser.getEmail());
    	
    	if(optionalUser.isPresent()) {
    		result.rejectValue("email", "unique", "Email is already registered");
    	}
    	
    	if(!newUser.getPassword().equals(newUser.getConfirm())) {
    		result.rejectValue("password", "matches", "Passwords don't match");
    	}
    	
    	if(result.hasErrors()) {
    		return null;
    	}
    	
    	String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	
    	newUser.setPassword(hashed);
    	userRepo.save(newUser);
    	return newUser;
    	
    		
    }
    public User login(LoginUser newLogin, BindingResult result) {
        // TO-DO: Additional validations!
    	Optional<User> optionalUser = userRepo.findByEmail(newLogin.getEmail());
    	
    	if(!optionalUser.isPresent()) {
    		result.rejectValue("email", "unique", "Email is not registered");
    		return null;
    	}

    	
    	User user = optionalUser.get();
    	
    	if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
    	    result.rejectValue("password", "Matches", "Invalid Password!");
    	}
    	
    	if(result.hasErrors()) {
    		return null;
    	}

    	
        return user;
    }

    public User findUser(Long id) {
		Optional<User>optionalUser = userRepo.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}
	}


}
