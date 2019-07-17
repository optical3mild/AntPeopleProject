package com.ezen.antpeople.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public class SecurityWebApplicationInitializer 
		extends AbstractSecurityWebApplicationInitializer { 
		
	}
	
	@Override protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests() 
						.antMatchers("/users/**").permitAll()
						.antMatchers("/admin/**").access("hasRole('ADMIN_MASTER') or hasRole('ADMIN') and hasRole('DBA')") 
						.antMatchers("/register/**").hasRole("ANONYMOUS") 
						.anyRequest().authenticated()
						.and() 
					.formLogin() 
						.loginPage("/login") 
						.usernameParameter("email") 
						.passwordParameter("password") 
						.permitAll(); 
		}



}
