package com.gcu.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(AdminBusinessService.class);

	
	@Autowired
	AdminDAO adminDAO;
	
	@Autowired
    PasswordEncoder passwordEncoder;

	@Override
	public AdminModel getByUsername(String username) {
        logger.info("getByUsername() being called from AdminBusinessService. Getting admin by username: {}", username);
		return adminDAO.getByUsername(username);
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername() being called from AdminBusinessService. Loading user by username: {}", username);
		AdminModel admin = getByUsername(username);
	    if (admin == null) {
            logger.error("User not found for username: {}. Throwing UsernameNotFoundException", username);
	        throw new UsernameNotFoundException("User not found");
	    }
	    
        logger.info("User found for username: {}. Building UserDetails object", username);

	    return new User(admin.getUsername(), admin.getPassword(), new ArrayList<>());
	}
	
}
