package com.gcu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.ProductDAO;
import com.gcu.models.ProductModel;

@Service
public class ProductBusinessService implements IProductBusinessService{
	
	@Autowired
	ProductDAO productDAO;

	//Gets request from the controller and passes it to the database then sends info received from the DAO class back to the controller 
	@Override
	public List<ProductModel> getProducts() {
		List<ProductModel> productList = productDAO.getAllProducts();
		return productList;
	}

	//Sends the data from the controller to the DAO class to add to the database
	@Override
	public boolean addProduct(ProductModel product) {
		try
		{
			productDAO.addOne(product);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	//Verifies that the product exists then passes to the DAO for database changes and returns to controller 
	@Override
	public boolean updateProduct(ProductModel product) {
		try
		{
			productDAO.updateOne(product.getId(), product);
			return true;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	//Tests to make sure item exists then passes to DAO class to remove from the database 
	@Override
	public boolean deleteProduct(ProductModel product) {
		try
		{
			productDAO.deleteOne(product.getId());
		}
		
		catch (Exception e)
		{
			return false;
		}
		
		return true;
	}

	

}
