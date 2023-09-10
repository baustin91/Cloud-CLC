package com.gcu.services;

import java.util.List;

import com.gcu.models.ProductModel;

public interface IProductBusinessService {
	
	public List<ProductModel> getProducts();
	public boolean addProduct(ProductModel product);
	public boolean updateProduct(ProductModel product);
	public boolean deleteProduct(ProductModel product);

}
