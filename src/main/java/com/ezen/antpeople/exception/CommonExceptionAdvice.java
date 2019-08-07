package com.ezen.antpeople.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView commonException(ModelAndView mv, Exception e) {
		logger.info("에러 메세지 : " + e.toString());
		mv.addObject("exception", e);
		mv.setViewName("/error/404");
		return mv;
	}

}
