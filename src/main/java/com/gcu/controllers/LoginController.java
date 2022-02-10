package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.model.UserModel;

/**
 * Controller for login functionality
 * @author derek lundy
 *
 */
@Controller
public class LoginController {

	/**
	 * Send user to the login page
	 * @param model
	 * @return
	 */ 
	@GetMapping("/login")
	public String display(Model model) {
		// add attribute title as "Login Form"
		model.addAttribute("title", "Login Form");
		// add attribute user as a default UserModel for login form to use
		model.addAttribute("user", new UserModel());
		// return login view
		return "login";
	}
	
	/**
	 * Send user to the login page
	 * @param model
	 * @return
	 */ 
	@GetMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("title", "GottaDo");
		// return login view
		return "home";
	}

}
