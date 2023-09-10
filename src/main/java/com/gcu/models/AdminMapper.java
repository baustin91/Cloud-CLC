package com.gcu.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdminMapper implements RowMapper<AdminModel>{

	@Override
	public AdminModel mapRow(ResultSet rs, int rowNum) throws SQLException {

		int id = rs.getInt("ID");
		String userName = rs.getString("USERNAME");
		String password = rs.getString("PASSWORD");
		
		AdminModel admin = new AdminModel(id, userName, password);
		return admin;
	}

}
