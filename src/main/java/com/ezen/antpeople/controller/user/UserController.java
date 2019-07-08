package com.ezen.antpeople.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class UserController {
	@RequestMapping(value="login.do", method=RequestMethod.GET)
	public String login() {
		
		return "login";
	
		
	}
	
}
