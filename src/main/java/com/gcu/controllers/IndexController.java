package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.AdminModel;



@Controller
@RequestMapping("/")
public class IndexController {
	
	@GetMapping("/adminlogin")
	public String displayAdminLogin(Model model)
	{
		model.addAttribute("adminModel", new AdminModel());
		return "redirect:/adminlogin/";
	}
	
	@GetMapping("/shop")
	public String displayShop(Model model)
	{
		model.addAttribute("adminModel", new AdminModel());
		return "redirect:/shop/";
	}

}
