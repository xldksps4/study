package com.update.semi.sduser.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.update.semi.sduser.dao.SduserDao;
import com.update.semi.sduser.dto.SduserDto;

@Service
public class SduserBizImpl implements SduserBiz{
	
	@Autowired
	private SduserDao dao;

	@Override
	public int userRegist(SduserDto dto) {
		// TODO Auto-generated method stub
		return dao.userRegist(dto);
	}

	@Override
	public SduserDto selectOne(String sdueamil) {
		// TODO Auto-generated method stub
		return dao.selectOne(sdueamil);
	}

	@Override
	public SduserDto idChk(String sdueamil) {
		// TODO Auto-generated method stub
		return dao.idChk(sdueamil);
	}

	@Override
	public SduserDto login(SduserDto dto) {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}


	
}
