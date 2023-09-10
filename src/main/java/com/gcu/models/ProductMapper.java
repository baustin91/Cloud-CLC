package com.gcu.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductMapper implements RowMapper<ProductModel>{

	@Override
	public ProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int id = rs.getInt("ID");
		String name = rs.getString("NAME");
		float price = rs.getFloat("PRICE");
		String description = rs.getString("DESCRIPTION");
		String img_url = rs.getString("IMG_URL");
		
		ProductModel product = new ProductModel(id, name, price, description, img_url);
		
		return product;
	}

}

