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

	@Override
	public List<ProductModel> getProducts() {
		List<ProductModel> productList = productDAO.getAllProducts();
		return productList;
	}

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
