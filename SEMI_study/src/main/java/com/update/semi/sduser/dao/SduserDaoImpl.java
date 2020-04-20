package com.update.semi.sduser.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.update.semi.sduser.dto.SduserDto;

@Repository
public class SduserDaoImpl implements SduserDao{
	
	private static final Logger logger = LoggerFactory.getLogger(SduserDaoImpl.class);
	
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int userRegist(SduserDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SduserDto selectOne(String sdueamil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SduserDto idChk(String sdueamil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SduserDto login(SduserDto dto) {
		// TODO Auto-generated method stub
		return null;
	}



}
