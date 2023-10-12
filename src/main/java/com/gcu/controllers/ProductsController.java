package com.gcu.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

	
	@Autowired
	ProductBusinessService service;
	
	//Shows all the products from the inventory list to the user in the shop 
	@GetMapping("/")
	public String showAllInventory(Model model)
	{
		logger.info("Displaying shop page from Products Controller");

		List<ProductModel> productsList = service.getProducts(); //sends request to business service to process from the database 
		
		model.addAttribute("productsList", productsList);
		
		return "shop";
	}
	
	//Adds item to the cart 
	@GetMapping("/addToCart")
	public String addToCart(@RequestParam("id") int productId, HttpSession session) {
		
		logger.info("addToCart() method being called from Products Controller");


	    ProductModel product = null;
	    
	    //Get the prodcut by id 
	    for (ProductModel p : service.getProducts()) {
	        if (p.getId() == productId) {
	            product = p;
	            break;
	        }
	    }
	    
	    if (product == null) {
			logger.info("Product not found");
	        return "redirect:/shop";  // Redirect to the main shop page if product not found
	    }
	    
	    //build the card object 
	    Object cartObj = session.getAttribute("cart");
	    List<ProductModel> cart = null;
	    if (cartObj instanceof List<?>) {
	        cart = (List<ProductModel>) cartObj;
	    } else {
	        cart = new ArrayList<>();
	    }
	    
	    //add items to the cart 
	    cart.add(product);
	    session.setAttribute("cart", cart);
	    
	    return "redirect:/shop/cart"; 
	}

	@GetMapping("/cart")
	public String showCart(HttpSession session, Model model) {
		
		logger.info("Displaying cart page from Products Controller");
		
		//Get the items in the cart
	    Object cartObj = session.getAttribute("cart");
	    double total = 0.0;
	    
	    //add the prices in the cart to display 
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

	//Delete the cart 
	@GetMapping("/checkout")
	public String checkout(HttpSession session, Model model) {
		
		logger.info("Displaying checkout page from Products Controller");
		
	    session.removeAttribute("cart");

	    return "checkout";
	}


}
