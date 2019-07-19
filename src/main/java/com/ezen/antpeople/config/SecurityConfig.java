package com.ezen.antpeople.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	//비밀번호 암호화
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	//로그인 보안
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()                                                               
	            .antMatchers("/pages/**").permitAll()                  
	            .antMatchers("/admin/**").hasRole("ADMIN")                                      
	            .anyRequest().authenticated()                                                   
	            .and()
	        .formLogin()
	            .loginPage("/user/login")
	            .loginProcessingUrl("/login-processing")
	            .failureUrl("/login-error")
	            .defaultSuccessUrl("/loginSuccess", true)
	            .usernameParameter("email")
	            .passwordParameter("password")
	            .permitAll();
	    http	
	    	.logout()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/")
		        .invalidateHttpSession(true);
	}


}
