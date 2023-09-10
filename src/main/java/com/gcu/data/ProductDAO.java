package com.gcu.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.gcu.models.ProductMapper;
import com.gcu.models.ProductModel;



public class ProductDAO implements IProductDAO{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public ProductModel getById(int id) {
		List<ProductModel> results = jdbcTemplate.query("SELECT * FROM TODO WHERE ID = ?", new ProductMapper(), id);
		
		if (results.size() > 0)
			return results.get(0);
		else
			return null;
	}

	@Override
	public List<ProductModel> getAllProducts() {
		List<ProductModel> results = jdbcTemplate.query("SELECT * FROM TODO", new ProductMapper());
		return results;
	}

	@Override
	public int addOne(ProductModel newProduct) {
		SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(jdbcTemplate);
		
		simpleInsert.withTableName("PRODUCTS").usingGeneratedKeyColumns("ID");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("NAME", newProduct.getName());
		parameters.put("PRICE", newProduct.getPrice());
		parameters.put("DESCRIPTION", newProduct.getDescription());
		parameters.put("IMG_URL", newProduct.getImg_url());
		
		Number result = simpleInsert.executeAndReturnKey(parameters);
		
		return result.intValue();
	}

	@Override
	public boolean deleteOne(int id) {
		int results = jdbcTemplate.update("DELETE FROM PRODUCTS WHERE ID = ?", id);
		
		if (results > 0)
			return true;
		else
			return false;
	}

	@Override
	public ProductModel updateOne(int id, ProductModel updateProduct) {
		int results = jdbcTemplate.update("UPDATE PRODUCTS SET NAME = ?, PRICE = ?, DESCRIPTION = ?, IMG_URL = ?  WHERE ID = ?", 
				updateProduct.getName(),
				updateProduct.getPrice(),
				updateProduct.getDescription(),
				updateProduct.getImg_url(),
				id
				);
		
		if (results > 0)
			return updateProduct;
		else 
			return null;
	}

}
