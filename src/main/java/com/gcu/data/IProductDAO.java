package com.gcu.data;

import java.util.List;

import com.gcu.models.ProductModel;

public interface IProductDAO {
	
	public ProductModel getById(int id);
	public List<ProductModel> getAllProducts();
	public int addOne(ProductModel newProduct);
	public boolean deleteOne(int id);
	public ProductModel updateOne(int id, ProductModel updateProduct);

}
