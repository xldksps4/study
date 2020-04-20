package com.update.semi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.update.semi.sduser.biz.SduserBiz;
import com.update.semi.sduser.dto.SduserDto;

@Controller
public class SduserController {

	private static final Logger logger = LoggerFactory.getLogger(SduserController.class);

	@Autowired
	private SduserBiz biz;

//회원가입폼으로	
	@RequestMapping(value = "/SDUSER_registForm.do")
	public String registForm() {
		logger.info("회원가입 폼으로 갑니다.");
		return "RegistForm";
	}

//회원가입
	@RequestMapping(value = "/SDUSER_userRegist.do", method = RequestMethod.GET)
	public String userRegist(SduserDto dto) {
		logger.info("[Controller]regist__");

		int res = biz.userRegist(dto);
// 질문 : 어떻게 보내더라?	
		if (res > 0) {
			return "redirect:login.do";
		} else {
			System.out.println("회원가입 실패");
			return "redirect:home.do";
		}

	}

	@RequestMapping(value = "/SDUSER_login.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login() {
		return "login";
	}

}
