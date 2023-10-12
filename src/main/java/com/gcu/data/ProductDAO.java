package com.gcu.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.gcu.models.ProductMapper;
import com.gcu.models.ProductModel;


@Repository
public class ProductDAO implements IProductDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductDAO.class);
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	//Method to get a single inventory item from the database by its ID 
	@Override
	public ProductModel getById(int id) {
		logger.info("getById() method being called in ProductDAO. Getting product by ID: {}", id);
		List<ProductModel> results = jdbcTemplate.query("SELECT * FROM products WHERE ID = ?", new ProductMapper(), id);
		
		if (results.size() > 0) {
			logger.info("Product found with ID: {}", id);
			return results.get(0);
		}
		else {
			logger.warn("No product found with ID: {}", id);
			return null;
		}
	}

	//Method to get all the inventory items from the database 
	@Override
	public List<ProductModel> getAllProducts() {
		logger.info("getAllProducts() method being called from ProductDAO");
		List<ProductModel> results = jdbcTemplate.query("SELECT * FROM products", new ProductMapper());
		logger.info("Found {} products from database.", results.size());
		return results;
	}

	//Method to add an item from the business service to the database 
	@Override
	public int addOne(ProductModel newProduct) {
		
		logger.info("addOne() being called from ProductDAO. Adding a new product: {}", newProduct.getName());

		SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(jdbcTemplate);
		
		simpleInsert.withTableName("products").usingGeneratedKeyColumns("ID");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("NAME", newProduct.getName());
		parameters.put("PRICE", newProduct.getPrice());
		parameters.put("DESCRIPTION", newProduct.getDescription());
		parameters.put("IMG_URL", newProduct.getImg_url());
		
		Number result = simpleInsert.executeAndReturnKey(parameters);
		logger.info("New product added with ID: {}", result.intValue());
		
		return result.intValue();
	}

	//Receives request from business service and removes the item from the database 
	@Override
	public boolean deleteOne(int id) {
		logger.info("deleteOne() being called from ProductDAO. Deleting product with ID: {}", id);

		int results = jdbcTemplate.update("DELETE FROM products WHERE ID = ?", id);
		
		if (results > 0) {
			logger.info("SUCCESS. Deleted product with ID: {}", id);
			return true;
		}
		else {
			logger.warn("FAIL. Did not delete product with ID: {}", id);
			return false;
		}
	}

	//Gets the updated info from the busienss service and updates the database with the new data 
	@Override
	public ProductModel updateOne(int id, ProductModel updateProduct) {
		logger.info("updateOne() being called from ProductDAO. Updating product with ID: {}", id);
		int results = jdbcTemplate.update("UPDATE products SET NAME = ?, PRICE = ?, DESCRIPTION = ?, IMG_URL = ?  WHERE ID = ?", 
				updateProduct.getName(),
				updateProduct.getPrice(),
				updateProduct.getDescription(),
				updateProduct.getImg_url(),
				id
				);
		
		if (results > 0) {
			logger.info("SUCCESS. Updated product with ID: {}", id);
			return updateProduct;
		}
		else {
			logger.warn("FAIL. Did not update product with ID: {}", id);
			return null;
		}
	}

}
