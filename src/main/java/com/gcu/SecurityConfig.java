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
        return new BCryptPasswordEncoder();
    }

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{
		
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers( "/css/**", "/js/**", "/images/**", "/login/**", "/") //<-- adjust this for security
				.permitAll()
				.anyRequest()
				.authenticated()
			)
			
			.formLogin((form) -> form
					.loginPage("/login/")
					.loginProcessingUrl("/login/processLogin")
					.usernameParameter("username")
		            .passwordParameter("password")
		            .failureForwardUrl("/login/")
		            .defaultSuccessUrl("/ToDoList/", true)
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