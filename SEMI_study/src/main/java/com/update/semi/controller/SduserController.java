package com.update.semi.controller;

import java.io.File;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.javassist.expr.Instanceof;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.update.semi.common.util.FileTakeAtTheUtilFolder;
import com.update.semi.common.util.UploadFileUtils;
import com.update.semi.sduser.biz.SduserBiz;
import com.update.semi.sduser.dto.SduserDto;

@Controller
public class SduserController {

	private static final Logger logger = LoggerFactory.getLogger(SduserController.class);

	@Autowired
	private SduserBiz sduserBiz;

	@Autowired
	public JavaMailSender emailSender;

	@Resource(name = "sdurealfile")
	private String uploadPath;

	// ======== common CRUD ========//

	// 회원가입폼으로
	@RequestMapping(value = "/SDUSER_registform.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String registForm() {
		logger.info("[Controller]go__registform");

		return "registform";
	}

	// 회원가입 완료
	@RequestMapping(value = "/SDUSER_userRegist.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String userRegist(SduserDto dto) {
		logger.info("[Controller]registRes__ >>>>>>>>>>>>" + dto);

		Object dtodto = dto.getSdudob();

		if (dtodto instanceof String) {
			logger.info("/n/n/n/n/n/n  생년월일 String type : " + dtodto);
		} else if (dtodto instanceof Date) {
			logger.info("/n/n/n/n/n/n 생년월일 date type : " + dtodto);
		} else {
			logger.info("/n/n/n/n/n/n 넌 누구냐");
		}

		int res = sduserBiz.userRegist(dto);

		if (res > 0) {
			return "redirect:SDUSER_login.do";
		} else {
			logger.info("회원가입 실패");
			return "redirect:home.do";
		}

	}

	// ID 중복체크
	// 파라미터 속성 produces는 ajax가 데이터 넘겨받을 때 깨짐 방지입니다.
	@RequestMapping(value = "/idCheck.do")
	@ResponseBody
	public String idCheck(@ModelAttribute("json") String json, SduserDto dto) {
		logger.info("이메일 중복체크입니다.");

		String result = "";
		// dto에 id값 담기
		dto = sduserBiz.idChk(json);

		if (dto != null) { // 중복
			result = "idAlreadyHave";
		} else {
			result = "idDoNotHave";
		}

		return result;
	}

	// 이메일인증 보내기
	@RequestMapping(value = "/SDUSER_sendMail.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String mailSend(Model model, @ModelAttribute("to") String to) {
		logger.info("이메일인증 시 메일로 발송합니다. to : " + to);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("사이 홈페이지 이메일 인증");
		int[] ranV = new int[6];
		String verifyNum = "";
		for (int i = 0; i < ranV.length; i++) {
			ranV[i] = (int) (Math.random() * 9);
			verifyNum += ranV[i] + "";
		}
		message.setText("회원가입을 위한 인증 메일입니다. \n 인증번호 확인 후 입력해주세요. \n인증번호 : " + verifyNum);
		emailSender.send(message);
		model.addAttribute(verifyNum, "verifyNum");
		return verifyNum;
	}

	// 로그인 폼으로 이동
	@RequestMapping(value = "/SDUSER_login.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Model model, SduserDto dto) {
		logger.info("로그인 폼으로 이동합니다.");

		// 로그인 정보가 있을 시
		model.addAttribute("sduserDto", dto);

		return "login";
	}

	// 비동기 로그인
	@RequestMapping(value = "/SDUSER_loginAjax.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> loginAjax(HttpSession session, @RequestBody SduserDto dto) {

		logger.info("login ajax로 넘겨주는 controller : " + dto);

		SduserDto sduserDto = sduserBiz.login(dto);
		logger.info(" sduserDto : >>> " + sduserDto);
		boolean check = false;

		if (sduserDto != null) {
			session.setAttribute("sduserDto", sduserDto);
			check = true;
		}

		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("check", check);

		return map;
	}

	// 로그아웃
	@RequestMapping(value = "/SDUSER_logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("logout합니다.");

		session.invalidate();

		return "main/main";
	}

	// 메인페이지
	@RequestMapping(value = "/MAIN_main.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String goMain(HttpSession session) {
		logger.info("[Controller]____goMain");
		SduserDto sduserDto = (SduserDto) session.getAttribute("sduserDto");
		logger.info("login session 확인 : "+ sduserDto);
		// 그냥들어옴
		if (sduserDto == null) {
			logger.info("[Empty_Data_First]__goMain");
			return "main/main";
		} else {
			// 로그인 했음 >> 세션있는지 확인
			logger.info("[메인페이지] session 있음");
			
			// 회원가입 직후 첫 로그인 시 회원정보 수정페이지로.
			String nickName = sduserDto.getSdunick();
			if (nickName == null) {
				logger.info("닉네임이 없습니다.");
				session.setAttribute("sduserDto", sduserDto);
				// model 써서 페이지 전환해야할 수도 있음.
				return "redirect:/MAIN_plusinfo.do";
			} else {
				logger.info("닉네임이 있어요!!.");
				session.setAttribute("sduserDto", sduserDto);
				logger.info("login세션의 이미지 경로 >>>> " + sduserDto.getSduimgpath());
				return "main/main";
			}
			
		}
		
	}



	// sns로그인
	@RequestMapping(value = "/SDUSER_snslogin.do", method = RequestMethod.POST)
	public String snslogin(HttpSession session, SduserDto dto) {
		logger.info("[Controller]snslogin____");
		logger.info("=========dto: " + dto.getSduemail());

		SduserDto onelogin = sduserBiz.selectOne(dto.getSduemail());
		logger.info("******onelogin: " + onelogin);

		SduserDto snslogin = sduserBiz.login(dto);
		logger.info("login:" + snslogin);

		if (onelogin != null) {
			session.setAttribute("sduserDto", snslogin);
			return "main/main";

		} else {
			int snsjoin = sduserBiz.insertUser(dto);
			logger.info("snsjoin이 0이 아니길... : " + snsjoin);
			if (snsjoin > 0) {
				session.setAttribute("sduserDto", snslogin);
				return "redirect : MAIN_main.do";
			} else {
				return "redirect : SDUSER_login.do";
			}
		}
	}

	// 회원정보 수정 페이지
	@RequestMapping(value = "/MAIN_plusinfo.do", method = RequestMethod.GET)
	public String goPlusinfo(Model model, HttpSession session) {
		logger.info("[Controller]__goPlustinfo" + session);

		// login session
		SduserDto sduserDto = (SduserDto) session.getAttribute("sduserDto");
		model.addAttribute("sduserDto", sduserDto);
		return "main/plusinfo";

	}

	// 회원정보 수정 res
	@RequestMapping(value = "/SDUSER_infoUpdate.do")
	public String infoUpdate(@ModelAttribute("sduserDto") @Valid SduserDto sduserDto, HttpSession session, Model model,
			BindingResult result) {

		// 하이버네이트유효성검사
		if (result.hasErrors()) {
			logger.info("유효성검사 >>>> 실행");
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error);
			}
			logger.info("유효성검사 >>>> 실패");

			model.addAttribute("sduserDto", sduserDto);
			return "";
		} else {
			logger.info("유효성 검사 >>>> 통과  : " + sduserDto);
			// 데이터 받고
			MultipartFile file = sduserDto.getSdufileupload();
			// 파일경로 추가해서
			String uploadPathRes = uploadPath + "/user";
			// 파일데이터와 경로를 인자로 전달 >>> 파일 저장
			String UUIDfileName = UploadFileUtils.SP_ProfileUpload(file, uploadPathRes);
			// Dto에 경로 추가
			sduserDto.setSduimgpath("/semi/resources/img/user/" + UUIDfileName);

			// ------------------------------
			// 로그인 관련 정보들 담아야겠지
			int updateRes = sduserBiz.infoUpdate(sduserDto);
			logger.info("[DB]프로필 업데이트 성공여부. updateRes : " + updateRes);  // <<<<<<<<<<<성공, 1
			if (updateRes > 0) {
				logger.info("[Controller]____infoUpdate 성공");
				SduserDto sduserDtoRes = sduserBiz.selectOne(sduserDto.getSduemail());

				session.setAttribute("sduserDto", sduserDtoRes);
				logger.info("성공 후 화면 전환 직전 값 확인" + sduserDto);	// <<<<<<<<<<<<<얘는 null
				return "redirect:/MAIN_main.do";
			}

		}

		return "main/main";
	}

}
