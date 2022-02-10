package com.gcu.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.BusinessServiceInterface;
import com.gcu.model.UserModel;

/**
 * Controller for handling register functionality
 * @author derek lundy
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	BusinessServiceInterface<UserModel> userService;
	
	/**
	 * Sends user to the register page
	 * @param model
	 * @return register
	 */
	@GetMapping("/")
	public String displayRegisterPage(Model model)
	{
		// add attribute title as "Register Form"
		model.addAttribute("title", "Register Form");
		// add attribute user as a default UserModel for the register form to use
		model.addAttribute("user", new UserModel());
		// return the register view with added attributes
		return "register";
	}
	
	/**
	 * Sends the user to the main page once registered
	 * @param user
	 * @param bindingResult
	 * @param model
	 * @return main
	 */
	@PostMapping("/doRegister")
	public String doRegister( @Valid @ModelAttribute("user") UserModel user, BindingResult bindingResult, Model model)
	{
		System.out.println(String.format("Form with Username of %s and Password of %s , and Email of %s", user.getUsername(), user.getPassword(), user.getEmail()));
		// if the bindingResult has errors
		if(bindingResult.hasErrors())
		{
			System.out.println("IN ERROR IF STATEMENT");
			// add attribute title as "Register Form"
			model.addAttribute("title", "Register Form");
			// return register view with added attribute
			return "register";
		}
		
		// if the userBusinessService's create method successfully creates the supplied user in the DB
		if (userService.register(user) == 1) {
			// add attribute title as "Main Page"
			model.addAttribute("title", "Main Page");
			// add attribute user as the successfully created user
			model.addAttribute("user", user);
			// return the main view with added attributes
			return "login";
		}
		// else if the userBusinessService failed to create the supplied userModel in the DB
		else {
			// add a reject value to the bindingResult
			bindingResult.rejectValue("username", "Invalid Details", "Incorrect Details");
			// return register view with bindingResult reject value
			return "login";
		}
	}

}
