package com.gcu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.models.ProductModel;
import com.gcu.services.ProductBusinessService;

import jakarta.servlet.http.HttpSession;

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
	
	
	@GetMapping("/addToCart")
	public String addToCart(@RequestParam("id") int productId, HttpSession session) {
	    
	    System.out.println("Entered addToCart");  // Log statement

	    ProductModel product = null;
	    
	    for (ProductModel p : service.getProducts()) {
	        if (p.getId() == productId) {
	            product = p;
	            break;
	        }
	    }
	    
	    if (product == null) {
	        System.out.println("Product not found");  // Log statement
	        return "redirect:/shop";  // Redirect to the main shop page if product not found
	    }
	    
	    Object cartObj = session.getAttribute("cart");
	    List<ProductModel> cart = null;
	    if (cartObj instanceof List<?>) {
	        cart = (List<ProductModel>) cartObj;
	    } else {
	        cart = new ArrayList<>();
	    }
	    
	    cart.add(product);
	    session.setAttribute("cart", cart);
	    
	    return "redirect:/shop/cart"; 
	}

	@GetMapping("/cart")
	public String showCart(HttpSession session, Model model) {
	    Object cartObj = session.getAttribute("cart");
	    double total = 0.0;
	    
	    if (cartObj instanceof List<?>) {
	        List<ProductModel> cart = (List<ProductModel>) cartObj;
	        model.addAttribute("cart", cart);
	        
	        for (ProductModel product : cart) {
	            total += product.getPrice(); 
	        }
	        
	        model.addAttribute("total", total);
	    } else {

	    }
	    
	    return "cart";
	}

	
	@GetMapping("/checkout")
	public String checkout(HttpSession session, Model model) {
	    session.removeAttribute("cart");

	    return "checkout";
	}


}
