package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gcu.models.AdminMapper;
import com.gcu.models.AdminModel;

public class AdminDAO implements IAdminDAO{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public AdminModel getByUsername(String username) {

		List<AdminModel> results = jdbcTemplate.query("SELECT * FROM USERS WHERE USERNAME = ?", new AdminMapper(), username);
		
		if (results.size() > 0)
			return results.get(0);
		else
			return null;
	}

}
