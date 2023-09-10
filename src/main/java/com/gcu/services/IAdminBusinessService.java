package com.gcu.services;

import com.gcu.models.AdminModel;

public interface IAdminBusinessService {

	public AdminModel getByUsername(String username);
}
