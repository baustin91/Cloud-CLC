package com.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	AdminBusinessService service;
	
	@GetMapping("/")
	public String displayShop(Model model)
	{
		model.addAttribute("adminModel", new AdminModel());
		return "adminlogin";
	}
	
	@PostMapping("/processLogin")
	public String processLogin(AdminModel user, Model model)
	{
		AdminModel userFromDb = service.getByUsername(user.getUsername());
		
		if (userFromDb != null && user.getPassword().equals(userFromDb.getPassword()))
			return "redirect:/inventory/";
		else 
			model.addAttribute("errorMessage", "Invalid username or password.");
			return "adminlogin.html";	
	}

}
