package com.gcu.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.ProductModel;
import com.gcu.services.ProductBusinessService;


@Controller
@RequestMapping("/inventory")
public class InventoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);


	@Autowired
	ProductBusinessService service;

	@GetMapping("/")
	public String showAllInventory(Model model)
	{
		logger.info("Displaying Inventory Management page from Inventory Controller");
		
		List<ProductModel> inventoryList = service.getProducts();
		
		model.addAttribute("inventoryList", inventoryList);
		
		return "inventory";
	}
	
	@GetMapping("/newInventoryForm")
	public String newInventoryForm(Model model)
	{
		logger.info("Displaying New Inventory form from Inventory Controller");

		model.addAttribute("product", new ProductModel());
		return "add";
	}
	
	@PostMapping("/addNew")
	public String addNew(ProductModel newProduct, BindingResult br, Model model)
	{
		logger.info("addNew() method being called from Inventory Controller");
		
		service.addProduct(newProduct);
		List<ProductModel> inventoryList =service.getProducts();
		
		model.addAttribute("inventoryList", inventoryList);
		
		return "redirect:/inventory/";
	}
	
	@PostMapping("/editProduct")
	public String editProduct(ProductModel editProduct, Model model)
	{
		logger.info("Displaying Edit Product Form");

		model.addAttribute("editProduct", editProduct);
		return "edit";
	}
	
	@PostMapping("editProductSubmit")
	public String editProduct(ProductModel editProduct, BindingResult br, Model model)
	{
		logger.info("editProduct() method being called from Inventory Controller");

		service.updateProduct(editProduct);
		List<ProductModel> inventoryList = service.getProducts();
		
		model.addAttribute("inventoryList", inventoryList);
		
		return"inventory";
	}
	
	@PostMapping("/deleteProduct")
	public String deleteProduct(ProductModel product, BindingResult br, Model model)
	{
		logger.info("deleteProduct() method being called from Inventory Controller");

		service.deleteProduct(product);
		List<ProductModel> inventoryList =service.getProducts();
		
		model.addAttribute("inventoryList", inventoryList);
		
		return "inventory";
	}

}
