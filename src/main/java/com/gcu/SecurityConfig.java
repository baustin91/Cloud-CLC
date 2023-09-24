package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

	
    @Bean
    public PasswordEncoder passwordEncoder() {
    	
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Just for demonstration, not recommended for production
        String rawPassword = "test";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded Password: " + encodedPassword);

        return encoder;
    	
        //return new BCryptPasswordEncoder();
        
    }

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{
		
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers( "/css/**", "/js/**", "/images/**", "/") //<-- adjust this for security
				.permitAll()
				.anyRequest()
				.authenticated()
			)
			
			.formLogin((form) -> form
					.loginPage("/adminlogin/")
					.loginProcessingUrl("/adminlogin/processLogin")
					.usernameParameter("username")
		            .passwordParameter("password")
		            .failureForwardUrl("/adminlogin/")
		            .defaultSuccessUrl("/inventory/", true)
			)
			
			.logout((logout) -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/")
		            .invalidateHttpSession(true)
		            .clearAuthentication(true)
	            );
		
		return http.build();
	}
	
}

