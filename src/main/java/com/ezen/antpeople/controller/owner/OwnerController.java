package com.ezen.antpeople.controller.owner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


@Controller
public class OwnerController {
	private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);
	
	/*@RequestMapping("owner") // UserController check 에서 경로를 "owner"로 수정 할것
	public ModelAndView staffList(ModelAndView mav, int id) throws Exception {
		//List<UserDTO> userList = UserServiceImpl.getUserList(id);
		
	return "ownerMain";
	}*/
}
