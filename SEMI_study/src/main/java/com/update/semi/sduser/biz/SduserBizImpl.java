package com.update.semi.sduser.biz;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.update.semi.controller.SduserController;
import com.update.semi.sduser.dao.SduserDao;
import com.update.semi.sduser.dto.SduserDto;

@Service
public class SduserBizImpl implements SduserBiz{
	
	@Autowired
	private SduserDao dao;

	private static final Logger logger = LoggerFactory.getLogger(SduserBizImpl.class);
	
	@Override
	public int insertUser(SduserDto dto) {
		logger.info("insertUser"+dto);
		return dao.insertUser(dto);
	}
	
	@Override
	public int userRegist(SduserDto dto) {
		
		return dao.userRegist(dto);
	}

	@Override
	public SduserDto selectOne(String sduemail) {
		logger.info("selectOne 들어온 이메일 값 >>>>"+sduemail);
		return dao.selectOne(sduemail);
	}

	@Override
	public SduserDto idChk(String sduemail) {
		// TODO Auto-generated method stub
		return dao.idChk(sduemail);
	}

	@Override
	public SduserDto login(SduserDto dto) {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}

	@Override
	public int infoUpdate(SduserDto dto) {
		// TODO Auto-generated method stub
		return dao.infoUpdate(dto);
	}

	
	
}
