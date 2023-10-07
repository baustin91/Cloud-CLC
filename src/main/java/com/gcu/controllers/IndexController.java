package com.gcu.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.AdminModel;



@Controller
@RequestMapping("/")
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	
	@GetMapping("/adminlogin")
	public String displayAdminLogin(Model model)
	{
		logger.info("Displaying admin login page from Index Controller");
		model.addAttribute("adminModel", new AdminModel());
		return "redirect:/adminlogin/";
	}
	
	@GetMapping("/shop")
	public String displayShop(Model model)
	{
		logger.info("Displaying shop page from Index Controller");
		model.addAttribute("adminModel", new AdminModel());
		return "redirect:/shop/";
	}

}
