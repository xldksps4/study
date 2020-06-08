package com.update.semi.sduser.biz;

import com.update.semi.sduser.dto.SduserDto;

public interface SduserBiz {
	
	
	//sns login
	public int insertUser(SduserDto dto);
	//회원가입
	public int userRegist(SduserDto dto);
	//아이디 조회
	public SduserDto selectOne(String sduemail);
	//아이디 중복 체크
	public SduserDto idChk(String sduemail);
	//로그인
	public SduserDto login(SduserDto dto);
	//회원정보 수정
	public int infoUpdate(SduserDto dto);
}
