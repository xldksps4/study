package com.update.semi.common.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice // 해당 클래스는 Exception을 처리하는 컨트롤러라는 어노테이션
public class ErrorHandler {
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

	@ExceptionHandler(Exception.class) // 파라미터로 받는 예외를 받아서 처리하는 메서드
	public ModelAndView commonException(Exception e) {
		logger.info(e.toString());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", e);
		modelAndView.setViewName("/error/exceptionpage");

		// 해당 컨트롤러를 발전 시켜 각각 에러에 맞는 페이지를 띄우게 해야한다.

		return modelAndView;
	}

}
