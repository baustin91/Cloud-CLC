package com.gcu.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gcu.data.AdminDAO;
import com.gcu.models.AdminModel;

@Service
public class AdminBusinessService implements IAdminBusinessService, UserDetailsService {
	
	@Autowired
	AdminDAO adminDAO;
	
	@Autowired
    PasswordEncoder passwordEncoder;

	@Override
	public AdminModel getByUsername(String username) {
		return adminDAO.getByUsername(username);
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminModel admin = getByUsername(username);
	    if (admin == null) {
	        throw new UsernameNotFoundException("User not found");
	    }
	    
	    return new User(admin.getUsername(), admin.getPassword(), new ArrayList<>());
	}
	
}
