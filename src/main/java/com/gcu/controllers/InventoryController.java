package com.gcu.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.models.AdminModel;
import com.gcu.models.ProductModel;
import com.gcu.services.ProductBusinessService;


@Controller
@RequestMapping("/inventory")
public class InventoryController {
	

	@Autowired
	ProductBusinessService service;

	@GetMapping("/")
	public String showAllInventory(Model model)
	{
		List<ProductModel> inventoryList = service.getProducts();
		
		model.addAttribute("inventoryList", inventoryList);
		
		return "inventory";
	}
	
	@GetMapping("/newInventoryForm")
	public String newInventoryForm(Model model)
	{
		model.addAttribute("product", new ProductModel());
		return "add";
	}
	
	@PostMapping("/addNew")
	public String addNew(ProductModel newProduct, BindingResult br, Model model)
	{
		
		service.addProduct(newProduct);
		List<ProductModel> inventoryList =service.getProducts();
		
		model.addAttribute("inventoryList", inventoryList);
		
		return "redirect:/inventory/";
	}
	
	@PostMapping("/editProduct")
	public String editProduct(ProductModel editProduct, Model model)
	{
		model.addAttribute("editProduct", editProduct);
		return "edit";
	}
	
	@PostMapping("editProductSubmit")
	public String editProduct(ProductModel editProduct, BindingResult br, Model model)
	{
		service.updateProduct(editProduct);
		List<ProductModel> inventoryList = service.getProducts();
		
		model.addAttribute("inventoryList", inventoryList);
		
		return"inventory";
	}
	
	@PostMapping("/deleteProduct")
	public String deleteProduct(ProductModel product, BindingResult br, Model model)
	{
		service.deleteProduct(product);
		List<ProductModel> inventoryList =service.getProducts();
		
		model.addAttribute("inventoryList", inventoryList);
		
		return "inventory";
	}

}
