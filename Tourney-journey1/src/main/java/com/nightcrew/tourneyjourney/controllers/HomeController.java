package com.nightcrew.tourneyjourney.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nightcrew.tourneyjourney.models.LoginUser;
import com.nightcrew.tourneyjourney.models.User;
import com.nightcrew.tourneyjourney.services.UserService;

@Controller
public class HomeController {

    // Add once service is implemented:
	
     @Autowired
     private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
    
    @GetMapping("/confirmed")
    public String index2(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "regConfirmed.jsp";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
            BindingResult result, Model model, HttpSession session) {

        // TO-DO Later -- call a register method in the service
    	User registeredUser = userService.register(newUser, result);
        // to do some extra validations and create a new user!

        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }

        // No errors!
        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", registeredUser.getId());
        session.setAttribute("userName", registeredUser.getUserName());
        return "redirect:/confirmed";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
            BindingResult result, Model model, HttpSession session) {

        // Add once service is implemented:
         User loginUser = userService.login(newLogin, result);

        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }

        // No errors!
        // TO-DO Later: Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("userId", loginUser.getId());
        session.setAttribute("userName", loginUser.getUserName());
        return "redirect:/dashboard";
    }

    @GetMapping("/home")
    public String dashboard(HttpSession session) {
    	if(session.getAttribute("userId") == null) {
    		return "redirect:/";
    	}
    	return "dashboard.jsp";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}
