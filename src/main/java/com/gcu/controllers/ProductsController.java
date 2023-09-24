package com.gcu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.ProductModel;
import com.gcu.services.ProductBusinessService;

@Controller
@RequestMapping("/shop")
public class ProductsController {
	
	@Autowired
	ProductBusinessService service;
	
	@GetMapping("/")
	public String showAllInventory(Model model)
	{
		List<ProductModel> productsList = service.getProducts();
		
		model.addAttribute("productsList", productsList);
		
		return "shop";
	}
	
	@GetMapping("/cart")
	public String showCart(Model model)
	{
		return "cart";
	}
	
	@GetMapping("/checkout")
	public String showCheckout(Model model)
	{
		return "checkout";
	}

}
