package com.ezen.antpeople.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@Order(2)
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    private UserDetailsService userDetailService;
    private LoginSuccessHandler loginSuccessHandler;
    
    public SecurityConfig (@Lazy UserDetailsService userDetailService
    		,LoginSuccessHandler loginSuccessHandler) {
    	this.userDetailService = userDetailService;
    	this.loginSuccessHandler = loginSuccessHandler;
    }
	
	
	//비밀번호 암호화
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	//보안이 적용되지않는 페이지
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/**");
    }

	//로그인 보안
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()                                                                            
	        	.antMatchers("/staff/**").hasRole("MEMBER")                                      
	        	.antMatchers("/manager/**").hasRole("MANAGER")                                      
	        	.antMatchers("/admin/**").hasRole("ADMIN")                                      
	        	.antMatchers("/main/**").permitAll()                                                   
	            .and()
	        .formLogin()
	            .loginPage("/user/login")
	            .loginProcessingUrl("${path}/user/logincheck")
	            .failureUrl("/user/login")
	            .usernameParameter("email")
	            .passwordParameter("password")
	            .successHandler(loginSuccessHandler)
	            .permitAll();
	    http	
	    	.logout()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/")
		        .invalidateHttpSession(true);
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailService);
	        authenticationProvider.setPasswordEncoder(passwordEncoder()); //패스워드를 암호활 경우 사용한다
	        return authenticationProvider;
	    }


}
