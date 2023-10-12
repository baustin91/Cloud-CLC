package com.gcu.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.AdminModel;
import com.gcu.services.AdminBusinessService;

@Controller
@RequestMapping("/adminlogin")
public class AdminLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginController.class);
	
	@Autowired
	AdminBusinessService service;
	
	//Method to access the the admin login page and test if already logged in or not 
	@GetMapping("/")
	public String displayShop(Model model, Authentication authentication)
	{
	    if (authentication != null && authentication.isAuthenticated()) {
	        logger.info("User already logged in. Redirecting to admin page.");
	        return "redirect:/inventory/";
	    }
		
		logger.info("Displaying admin login page from Admin Login Controller");

		model.addAttribute("adminModel", new AdminModel());
		return "adminlogin";
	}
	
	//Authenticates the user through Spring Security 
	@PostMapping("/processLogin")
	public String processLogin(AdminModel user, Model model)
	{
		logger.info("processLogin() method being called from Admin Login Controller");

		AdminModel userFromDb = service.getByUsername(user.getUsername());
		
		if (userFromDb != null && user.getPassword().equals(userFromDb.getPassword())) {
            logger.info("Admin login SUCCESS from processLogin() method in AdminLoginController. Username: {}", user.getUsername());
			return "redirect:/inventory/";
		}
		else {
			logger.info("Admin login FAIL from Index Controller");
			model.addAttribute("errorMessage", "Invalid username or password.");
			return "adminlogin.html";	
		}
	}

}
