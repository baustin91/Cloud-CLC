package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.models.AdminMapper;
import com.gcu.models.AdminModel;

@Repository
public class AdminDAO implements IAdminDAO{
	
	private static final Logger logger = LoggerFactory.getLogger(AdminDAO.class);

	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public AdminModel getByUsername(String username) {
		
		logger.info("getByUsername() method being called from AdminDAO");


		List<AdminModel> results = jdbcTemplate.query("SELECT * FROM admins WHERE USERNAME = ?", new AdminMapper(), username);
		
		if (results.size() > 0) {
			logger.info("User found with the given username: {}", username);
			return results.get(0);
		}
		else {
			logger.warn("No user found with the given username: {}", username);
			return null;
		}
	}

}
