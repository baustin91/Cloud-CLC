package com.gcu.data;

import com.gcu.models.AdminModel;

public interface IAdminDAO {
	
	public AdminModel getByUsername(String username);

}
