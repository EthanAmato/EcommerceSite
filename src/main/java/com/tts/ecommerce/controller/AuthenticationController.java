package com.tts.ecommerce.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tts.ecommerce.model.User;
import com.tts.ecommerce.service.UserService;

@Controller
public class AuthenticationController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/signin")
	public String login() {
		return "signin";
	}
	
	
	@PostMapping("/signin")
	public String signup(@Valid User user,
						 @RequestParam String submit,
						 BindingResult bindingResult,
						 HttpServletRequest request) throws ServletException {
		
		/*** CHANGE ****/
		System.out.println(userService.getLoggedInUser());

		if(userService.getLoggedInUser() != null) {
			bindingResult.reject("error.user", "Already logged in.");
		}
		/*** END CHANGE ***/
		
		String password = user.getPassword();
		/*** CHANGE ***/
		if(!bindingResult.hasErrors() && submit.equals("up")) {
		/*** END CHANGE ***/
			if(userService.findByUsername(user.getUsername()) == null) {
				userService.saveNew(user);
			} else {
				bindingResult.rejectValue("username", "error.user", "Username is already taken.");
				/*** CHANGE (REMOVAL) ****/
				/*** END CHANGE ***/
			}		
		}
		/*** CHANGE ***/
		if(!bindingResult.hasErrors() && userService.findByUsername(user.getUsername()) == null) {
			bindingResult.rejectValue("username",  "error.user", "User does not exist.");
		}
		if(!bindingResult.hasErrors()) {
		/** END CHANGE ***/	
			request.login(user.getUsername(), password);
			return "redirect:/";
		/*** CHANGE ***/
		}
		System.out.println(bindingResult.getAllErrors());
		return "signin";
		/*** END CHANGE ***/
	}
	
	
}
