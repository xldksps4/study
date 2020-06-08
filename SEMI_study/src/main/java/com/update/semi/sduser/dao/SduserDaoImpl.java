package com.update.semi.sduser.dao;

import java.util.ArrayList;
import java.util.List;

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
	public int insertUser(SduserDto dto) {
		
		int res = sqlSession.insert(NAMESPACE + "userRegist",dto);
		
		return res;
	}

	@Override
	public int userRegist(SduserDto dto) {
		logger.info("회원가입 성공 전, DaoImpl입니다. /n dto에 담긴 값들은 : "+ dto);
		int res = 0;
		
		try {
			res = sqlSession.insert(NAMESPACE + "userRegist", dto);
		} catch (Exception e) {
			logger.info("[ERROR] daoImpl에서 회원가입 실패, dto : "+ dto);
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public SduserDto selectOne(String sduemail) {
		SduserDto res = null;
		
		logger.info("[daoImpl__selectOne email]"+sduemail);
		try {
			logger.info("selectOne res : "+ res);  //<<<<<<<<<, null
			res = sqlSession.selectOne(NAMESPACE + "selectOne", sduemail);
			logger.info("selectOne res 성공 : "+ res);  // <<<<<<<<<<< null
		} catch (Exception e) {
			System.out.println("[error] : selectOne");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public SduserDto idChk(String sduemail) {
		logger.info("아이디 중복검사 DaoImpl입니다.");
		SduserDto result = sqlSession.selectOne(NAMESPACE + "idChk", sduemail);
		System.out.println(result);
		
		return result;
	}

	@Override
	public SduserDto login(SduserDto dto) {
		SduserDto res = null;
		
		logger.info("[daoImpl__로그인 dto]"+dto);
		try {
			logger.info("login res : "+ res);
			res = sqlSession.selectOne(NAMESPACE + "login", dto);
			logger.info("login res 성공 : "+ res);
		} catch (Exception e) {
			System.out.println("[error] : login");
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int infoUpdate(SduserDto dto) {
		logger.info("[daoImpl__회원정보 수정 dto]" + dto);
		int res = 0;
		
		try {
			res = sqlSession.update(NAMESPACE + "infoUpdate", dto);
			logger.info("[daoImpl__회원정보 수정한듯]" +res);
		} catch (Exception e) {
			logger.info("[daoImpl__회원정보 수정실패], res : "+res);
			e.printStackTrace();
		}
		
		return res;
	}


}
