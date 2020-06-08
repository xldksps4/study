package com.update.semi.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.update.semi.common.util.DownloadFileUtils;
import com.update.semi.common.util.OraclePagination;
import com.update.semi.common.util.UploadFileUtils;
import com.update.semi.sdboard.biz.SdboardBiz;
import com.update.semi.sdboard.dto.SdboardDto;
import com.update.semi.sdboard.dto.SdreplyDto;
import com.update.semi.sduser.dto.SduserDto;

@Controller
public class SdboardController {

	private static final Logger logger = LoggerFactory.getLogger(SdboardController.class);
	
	@Autowired
	private SdboardBiz sdboardBiz;
	
	@Resource(name = "sdbimguploadpath")	  // 이미지 경로
	private String imgUploadPath;	
	   
	@Resource(name = "sdbfileuploadpath") // 실제 파일 경로
	private String fileUploadPath;

	
	//보드 리스트로(페이징은 어떻게...?
	@RequestMapping(value="/BOARD_goboardlist2.do")
	public String goBoardList(HttpSession session, Model model, SdboardDto dto) {
		//세션
		SduserDto loginDto = (SduserDto)session.getAttribute("login");
		//리스트
		List<SdboardDto> boardDto = sdboardBiz.boardList(dto);
		logger.info("boardDto >>>"+boardDto);
		logger.info("loginDto >>>"+loginDto);
		model.addAttribute("boardDto", boardDto);
		model.addAttribute("loginDto", loginDto);
		
		
		return "board/boardlist";
	}
	
	
	//글 상세페이지 + 댓글 상세
//	@RequestMapping(value="/BOARD_boarddetail.do", method = RequestMethod.GET)
//	public String goBoardDetail(HttpSession session, Model model, @ModelAttribute("SdboardDto") SdboardDto dto, SdreplyDto sdreplyDto) {
		//글
//		SdboardDto boardDetail = sdboardBiz.selectDetail(dto);
		//댓글 ???
//		List<SdreplyDto> replyList = sdboardBiz.replySelectAll(sdreplyDto);
		
//		model.addAttribute("boardDto", boardDetail);
		
//		return "board/boarddetail";
//	}
	
	//글작성 페이지
	@RequestMapping(value="/BOARD_boardwrite.do")
	public String goBoardWrite(HttpSession session, Model model) { //, @ModelAttribute("SdboardDto") SdboardDto dto 는 필요없을듯
		logger.info("[Controller]____goBoardWrite");
		
		//세션
		SduserDto loginDto = (SduserDto)session.getAttribute("login");
		//리스트
//		List<SdboardDto> boardDto = sdboardBiz.boardList(dto);
		logger.info("로그인 세션 들어가니? "+ loginDto);
//		model.addAttribute("boardDto", boardDto);
		model.addAttribute("loginDto", loginDto);
				
		return "board/boardwrite";
	}
	
	//작성취소
	@RequestMapping(value="/BOARD_writeCancel.do")
	public String writeCancel(HttpSession session, Model model, SdboardDto dto ) {
		logger.info("[Controller]____writeCancel");
		
		//세션
		SduserDto loginDto = (SduserDto)session.getAttribute("login");
		//리스트
		List<SdboardDto> boardDto = sdboardBiz.boardList(dto);
				
		model.addAttribute("boardDto", boardDto);
		model.addAttribute("loginDto", loginDto);
				
		
		return "board/boardlist";
	}
	
	   // 비동기 멀티 이미지 업로드
	   @ResponseBody
	   @RequestMapping(value = "/BOARD_AjaxFileUpload.do")
	   public Map<String, Object> AjaxFileUpload(@ModelAttribute("fileArr") MultipartFile[] fileArr, SdboardDto sdboardDto,
	         HttpSession session) throws IOException {
	      logger.info("[ajax] Ajax File Uplod : >>>>>>>>>>>>>>>>>>>>>  " + fileArr[0].getOriginalFilename());
	      logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + fileArr.length);
	      
	      Map<String, Object> output = new HashMap<String, Object>();
	      output.put("msg", "fail"); // 디폴트 fail
	      output.put("sdbseq", "0"); // 디폴트 0 >>> 게시판 insert 성공시 seq를 담는다

	      // 회원의 email 주소 = id
	      String id = "";

	      // #1 유저id, 년, 월, 일 폴더 생성
	      String id_ymdPath = "";
	      SduserDto sduserDto = (SduserDto) session.getAttribute("login");
	      if (sduserDto != null) {
	         logger.info("sduserDto :" + sduserDto + "/n >>> 폴더를 생성하겠습니다.");
	         id = sduserDto.getSduemail();
	         // id_ymdPath 파일 경로 설정 >>> /유져id/년/월/일
	         id_ymdPath = UploadFileUtils.calcPath(imgUploadPath, id);
	      }

	      // #2 파일 저장후 성공시 "view에서 불러올 경로 + 파일명" 배열에 담는다[썸내일 제외 원본이미지], 썸내일은 따로 "view에서
	      // 불러올 경로 + 파일명"을 String에 담는다. >> 향후 실패 처리시를 만들어야함
	      String[] imgNameArr = new String[fileArr.length];
	      String thumbImgName = "";

	      int j = 0;
	      for (MultipartFile file : fileArr) {
	         if (file.getSize() != 0) {// file은 항상 잇기 때문에 size가 0일때리를 봐야한다
	            if (j == 0) {
	               // 파일과 썸네일 생성 >> 썸네일이름[0], 원본파일이름[1] 배열로 리턴
	               String[] tempName = UploadFileUtils.imgUploadAndThumb(imgUploadPath, file.getOriginalFilename(),
	                     file.getBytes(), id_ymdPath);
	               // DB에 저장할 경로 : /semi/resources/img/board + /(==File.separator) + 유져id/년/월/일 + /s/ + / 썸네일 파일명
	               thumbImgName = "/semi/resources/img/board" + File.separator + id_ymdPath + "/s/" + tempName[0];
	               // DB에 저장할 경로 : /semi/resources/img/board + / + 유져id/년/월/일 + / + 파일명
	               imgNameArr[0] = "/semi/resources/img/board" + File.separator + id_ymdPath + File.separator
	                     + tempName[1];

	            } else {
	               // 파일 생성
	               String fileName = UploadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(),
	                     file.getBytes(), id_ymdPath);
	               // DB에 저장할 경로 : /semi/resources/img/board + / + 유져id/년/월/일 + / + 파일명
	               imgNameArr[j] = "/semi/resources/img/board" + File.separator + id_ymdPath + File.separator
	                     + fileName;
	            }
	            j++;
	         }
	      }

	      // #3 DB저장 (id, 썸네일이미지명, 이미지명) 저장
	      sdboardDto.setSduemail(id);
	      sdboardDto.setSdbthumbnail(thumbImgName);
	      String imgNames = "";
	      for (int i = 0; i < imgNameArr.length; i++) {
	         if (i == 0) {
	            imgNames = imgNameArr[0];
	         } else {
	            imgNames = imgNames + "??" + imgNameArr[i];
	         }
	      }
	      sdboardDto.setSdbimgpath(imgNames);

	      int res = sdboardBiz.insertImg(sdboardDto);

	      if (res > 0) {
	         logger.info("Board img 추가 성공");

	         // #4 방금 추가한 boardNo 알아낸다.(id, 썸네일 이름를 통해 seq를 알아낸다.)
	         String boardNo = sdboardBiz.getBoardNo(sdboardDto); // map에 담아서 보내줘야 하기 때문에 string으로 바꿔 담아 준다
	         logger.info("Board >>>>>>>>>>>>>>> img 저장 후 가져온 BoardNo : " + boardNo);
	         // #5 여기까지 성공 헀다면 output를 만들어 보낸다
	         if (boardNo != null || boardNo != ("")) {
	        	 logger.info("[Controller]__boardNo 값 가져오기 성공>>>"+boardNo);
	            output.put("imgNameArr", imgNameArr);
	            output.put("msg", "success");
	            output.put("sdbseq", boardNo);
	         }
	      }
	      return output;
	   }

	   // 글쓰기 완료
	   @RequestMapping(value = "/BOARD_boardwriteres.do", method = RequestMethod.POST)
	   public String boardWriteRes(Model model, @ModelAttribute SdboardDto sdboardDto, HttpSession session)
	         throws IOException {
	      logger.info("board Write Res >>>>>>>>>>>>>>>>>>>>> " + sdboardDto);

	      SduserDto sduserDto = (SduserDto) session.getAttribute("member");
	      String id = "";
	      if (sduserDto != null) {
	         id = sduserDto.getSduemail();
	      }

	      MultipartFile[] fileArr = sdboardDto.getFile();
	      System.out.println("파일이 담겨있는 멀티파트파일"+fileArr);
	      String fileNames = "파일없음";

	      logger.info("fileArr[0]에 뭐가 담겨있나요 ? "+ fileArr[0]); // 얘 타입에 맞춰서 if 다시 돌려보자.
	      
	      // 파일 업로드
	      if(fileArr[0].getSize() != 0 || fileArr[0].toString() != null) {
	         System.out.println("1.파일이 담겨 있음을 확인 ");
	         // 게시판 첨부파일 업로드~
	         // 폴더 생성 >> fileUploadPath + /id/yyyy/mm/dd/
	         String id_ymdPath = UploadFileUtils.calcPath(fileUploadPath, id);
	         System.out.println("2.파일 이후에 id에 해당하는 주소생성"+id_ymdPath);
	         for (int i = 0; i < fileArr.length; i++) {
	            if (i == 0) {
	               String temp = UploadFileUtils.fileUpload(fileUploadPath, fileArr[i].getOriginalFilename(), fileArr[i].getBytes(), id_ymdPath);
	               fileNames = fileUploadPath + id_ymdPath + File.separator + temp;
	            } else {
	               String temp = UploadFileUtils.fileUpload(fileUploadPath, fileArr[i].getOriginalFilename(), fileArr[i].getBytes(), id_ymdPath);
	               fileNames = fileNames + "&&" + fileUploadPath + id_ymdPath + File.separator + temp;
	            }
	         }
	      }

	      // id, fileNames(업로드한 파일명) dto 추가
	      sdboardDto.setSduemail(id);
	      sdboardDto.setSdbfilepath(fileNames);     // 첨부된 파일이 없다면 "" DB에 들어감
	      System.out.println("3.sdboarddto에 새로 담은 값이 들어 갔는지 보자 "+sdboardDto);
	      // DB 추가 = 업로드한 이미지 x , 업로드한 파일 o
	      if (sdboardDto.getSdbseq() == 0) {
	         logger.info("board Write Res >>>>>>>>>>>>>>>> [기존 이미지 없음] Board insert " + sdboardDto);

	         int insertRes = sdboardBiz.insertBoardNoImg(sdboardDto);

	         if (insertRes > 0) {
	            logger.info("board Write Res >>>>>>>>>>>>>>>> [추가 성공] Board insert success");
	            return "redirect:/BOARD_goboardlist.do";
	         } else {
	            logger.info("board Write Res >>>>>>>>>>>>>>>> [추가 실패] Board insert fail");
	            // date 포멧 변환
	            // title, content 길이 변경
	            model.addAttribute("sdboardDto", sdboardDto);
	            return "redirect:/BOARD_boardwrite.do";
	         }
	         // DB 수정 = 업로드한 이미지 o , 업로드한 파일 o
	      } else {
	         logger.info("board Write Res >>>>>>>>>>>>>>>> [기존 이미지 있음] Board update" + sdboardDto);

	         int updateRes = sdboardBiz.updateBoardYesImg(sdboardDto);

	         if (updateRes > 0) {
	            logger.info("board Write Res >>>>>>>>>>>>>>>> [수정 성공] Board update success");
	            model.addAttribute("sdboardDto", sdboardDto);
	            return "redirect:/BOARD_goboardlist.do";
	         } else {
	            logger.info("board Write Res >>>>>>>>>>>>>>>> [수정 실패] Board update fail");
	            // date 포멧 변환
	            // title, content 길이 변경
	            model.addAttribute("sdboardDto", sdboardDto);
	            return "redirect:/BOARD_boardwrite.do";
	         }

	      }
	   }

	   // 디테일
	   @RequestMapping(value="/BOARD_boarddetail.do", method = RequestMethod.GET)
	   public String goBoardDetail(Model model, @RequestParam("sdbseq") int sdbseq, SdboardDto sdboardDto) {
	      logger.info("[Controller]____go board detail page");

	      sdboardDto = sdboardBiz.selectDetail(sdboardDto);
	      if(sdboardDto.getSdbfilepath() != null) {
	         if(sdboardDto.getSdbfilepath().contains("??")) {		//없어도 문제없긴함. split으로 잘랐을 때, 값이 하나면 0번지로 다 담기기 때문
	            String[] fileNames = sdboardDto.getSdbfilepath().split("\\?\\?");  // "\\" 두개를 붙이는 이유는  Meta character라서 정규식을 기반으로 구현한 메서드에 그대로 사용 불가하다.
	            /* 
	                # Meta character: / ? *
	                 정규 표현식에는 특별한 의미를 없애고 문자 그대로 표현식 내에서 처리하기 위해 이스케이프해야하는 14 개의 메타 문자
	             */
	            sdboardDto.setSdbfilepath("첨부된 파일 " + fileNames.length +"개 ");
	         } else {
//	            int index = sdboardDto.getSdbfilepath().lastIndexOf("_") + 1;   view에 이름 뿌릴 때 사용 가능
	            sdboardDto.setSdbfilepath("첨부된 파일 1개 ");
	         }
	      }
	      model.addAttribute("boardDto", sdboardDto);
	      
	      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> : " + sdboardDto);
	      return "board/boarddetail";
	   }
	   
	   
	   @RequestMapping(value="/fileDetail.do", method = RequestMethod.POST)
	   @ResponseBody
	   public Map<String, Object> fileDown(@RequestParam("sdbseq") int sdbseq, SdboardDto sdboardDto) {
	      Map<String, Object> output = new HashMap<String, Object>();
	      
	      sdboardDto = sdboardBiz.selectDetail(sdboardDto);
	      if(sdboardDto.getSdbfilepath() != null) {
	         String fileFullNames = sdboardDto.getSdbfilepath(); 
	         if(fileFullNames.contains("??")) {
	            String[] fileFullNameArr = fileFullNames.split("\\?\\?");  // "\\" 두개를 붙이는 이유는  Meta character라서 정규식을 기반으로 구현한 메서드에 그대로 사용 불가하다.
	            String[] fileNameArr = new String[fileFullNameArr.length];
	            for(int i = 0; i < fileFullNameArr.length; i++) {
	               int index = fileFullNameArr[i].lastIndexOf("_") + 1;
	               fileNameArr[i] = fileFullNameArr[i].substring(index, fileFullNameArr[i].length());
	            }
	            output.put("msg", "success");
	            output.put("fileName", fileNameArr);
	            
	         } else {
	            // 파일이 1개 일때
	            int index = sdboardDto.getSdbfilepath().lastIndexOf("_") + 1;
	            String fileName = fileFullNames.substring(index, fileFullNames.length());
	            output.put("msg", "success");
	            output.put("fileName", fileName);
	         }
	      } else {
	         output.put("msg", "fail");
	      }
	      
	      return output;
	   }
	   
	   // 파일 다운로드
	   @RequestMapping(value="/fileDown.do", method = RequestMethod.POST)
	   @ResponseBody
	   public byte[] fileDown(HttpServletRequest request, HttpServletResponse response,@RequestParam("fileName") String fileName ,@RequestParam("sdbseq") int sdbseq, SdboardDto sdboardDto) throws UnsupportedEncodingException {
	      logger.info("board file down");
	      byte[] down = null;
	      String outFilePath = "";
	      
	      sdboardDto = sdboardBiz.selectDetail(sdboardDto);
	      if(sdboardDto.getSdbfilepath() != null) {
	         String fileFullNames = sdboardDto.getSdbfilepath(); 
	         if(fileFullNames.contains("??")) {
	            String[] fileFullNameArr = fileFullNames.split("\\?\\?");  // "\\" 두개를 붙이는 이유는  Meta character라서 정규식을 기반으로 구현한 메서드에 그대로 사용 불가하다.
	            for(int i = 0; i < fileFullNameArr.length; i++) {      
	               int index = fileFullNameArr[i].lastIndexOf("_") + 1;   // 뒤에서 처음으로 _가 나오는 인덱스 번호를 찾는다.
	               String tempFileName = fileFullNameArr[i].substring(index, fileFullNameArr[i].length());   // 원본 파일명을 가져온다.
	               if(fileName.equals(tempFileName)) {         // 다운요청한 파일명과 일치하는 파일명을 찾는다
	                  outFilePath = fileFullNameArr[i];
	               }
	            }
	         } else {
	            int index = fileFullNames.lastIndexOf("_") + 1;   // 뒤에서
	            String tempFileName = fileFullNames.substring(index, fileFullNames.length());
	            if(fileName.equals(tempFileName)) {         // 다운요청한 파일명과 일치하는 파일명을 찾는다
	               outFilePath = fileFullNames;
	            }
	         }
	         // 단일 파일 다운로드
	         logger.info("[fileDown.do] >>>>>>>>>>>> 다운로드 파일명 : " + outFilePath);
	         File file = new File(outFilePath);
	         down = DownloadFileUtils.file_toByte(file);   // == FileCopyUtils.copyToByteArray(file);   #스프링에서 제공하는 파일 다운로드 유틸 
	         logger.info("다운로드한 파일명, 파일화 완료 >>> " +down);   
	         String filename = new String(file.getName().getBytes("utf-8"), "8859_1");             // 파일 이름을 "utf-8"의 바이트 코드로 변환, 8859_1 인코딩 설정
	         response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");   
	         response.setContentType("application/octet-stream");                            // # application/octet-stream는 다른 모든 경우를 위한 기본값입니다. ,알려지지 않은 파일 타입은 이 타입을 사용해야 합니다
	         response.setContentLength(down.length);
	         
	         //HttpHeaders headers = new HttpHeaders();  >>> 스프링에서 지원하는 Http 헤더설정 클래스 
	      }
	      return down;
	   }
	   
	   
	   //------------------ pagenation ----------------------//
	   
	   // 페이징처리 후 메인리스트로
	   @RequestMapping(value = "/BOARD_goboardlist.do")                           
	   public String boardMain(HttpSession session, Model model, @ModelAttribute SdboardDto dto, @RequestParam(defaultValue = "1") int currentPage) {
	      logger.info("[Controller]____BOARD_pageingList >>> [input] sdboardDto : " + dto);   
	   //세션
	      SduserDto loginDto = (SduserDto)session.getAttribute("login");
	   //페이징   
	      //1) 전체 게시물 개수 가져오기
	      int totalBoardCount = sdboardBiz.getTotalBoard(dto);   // 전체게시물 수  or 검색한 게시물 수
	      
	      /*2) 페이징 클래스 >> 쿼리에 필요한 시작페이지 번호, 끝 페이지 번호를 계산해서 가지고 있음  */
	      OraclePagination pagination = new OraclePagination(totalBoardCount, currentPage);   // 전체 게시물 수, 현재 페이지 (== 요청된 페이지) 
	      logger.info("board list page >>> [페이징] OraclePagination : " + pagination );
	      
	    
	      //3) boardDto에 시작 페이지, 끝 페이지 추가
	      dto.setStartBoardNo(pagination.getStartBoardNo());
		  dto.setEndBoardNo(pagination.getEndBoardNo());
	   
	   //리스트
		  // top N 쿼리를 사용하여 게시물 리스트 가져오기 
		  List<SdboardDto> boardDto = sdboardBiz.boardList(dto);
		  
		  if(loginDto == null) {
			  logger.info("세션 값이 넘어오지 않습니다.");
		  }
		  if(boardDto == null) {
			  logger.info("게시판 값이 넘어오지 않습니다.");
		  }
		  if(pagination.equals(0)) {
			  logger.info("페이징 값이 넘어오지 않습니다.");
		  }
		  
		  model.addAttribute("loginDto", loginDto);
	      model.addAttribute("boardDto", boardDto);
	      model.addAttribute("pagination", pagination);
	      return "board/boardlist";
	   }
	
	
	   
}
