package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling page navigation
 * @author derek lundy
 *
 */
@Controller
public class HomeController {
	
	/**
	 * Function to display the home page
	 * @param model
	 * @return home
	 */
	@GetMapping("/")
	public String displayHomePage(Model model)
	{ 
		model.addAttribute("title", "GottaDo Home");
		return "home";
	}
}
