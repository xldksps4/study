package com.update.semi.sduser.biz;

import com.update.semi.sduser.dto.SduserDto;

public interface SduserBiz {
	
	//회원가입
	public int userRegist(SduserDto dto);
	//아이디 조회
	public SduserDto selectOne(String sdueamil);
	//아이디 중복 체크
	public SduserDto idChk(String sdueamil);
	//로그인
	public SduserDto login(SduserDto dto);
}
