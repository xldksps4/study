package com.update.semi.sdboard.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.update.semi.sdboard.dto.SdboardDto;
import com.update.semi.sdboard.dto.SdreplyDto;

@Repository
public class SdboardDaoImpl implements SdboardDao{

	private static final Logger logger = LoggerFactory.getLogger(SdboardDaoImpl.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/* SELECT */
	
	//글 전체 조회
	@Override
	public List<SdboardDto> selectAll(SdboardDto dto) {
		logger.info("[DaoImpl]__selectAll__Start");
		List<SdboardDto> list = new ArrayList<SdboardDto>();
		
		try {
			logger.info("dto >>>"+dto );
			list = sqlSession.selectList(NAMESPACE+"selectAll", dto);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectAll__fail ");
			e.printStackTrace();
		}
		
		return list;
	}

	//글 상위폴더 조회
	@Override
	public List<SdboardDto> selectFirst(SdboardDto dto) {
		List<SdboardDto> list = new ArrayList<SdboardDto>();
		
		try {
			list = sqlSession.selectList(NAMESPACE+"selectFirst", dto);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectFirst__fail ");
			e.printStackTrace();
		}
		return list;
	}
	
	//글 차상위폴더 조회
	@Override
	public List<SdboardDto> selectSecond(SdboardDto dto) {
		List<SdboardDto> list = new ArrayList<SdboardDto>();
		
		try {
			list = sqlSession.selectList(NAMESPACE+"selectSecond", dto);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectSecond__fail ");
			e.printStackTrace();
		}
		return list;
	}

	//글 하위폴더 조회	
	@Override
	public List<SdboardDto> selectThird(SdboardDto dto) {
		List<SdboardDto> list = new ArrayList<SdboardDto>();
		
		try {
			list = sqlSession.selectList(NAMESPACE+"selectThird", dto);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectThird__fail ");
			e.printStackTrace();
		}
		
		return list;
	}

	//글 상세 조회	
	@Override
	public SdboardDto selectDetail(SdboardDto dto) {
		SdboardDto result = null;
		
		try {
			result = sqlSession.selectOne(NAMESPACE+"selectDetail", dto);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectDetail__fail ");
			e.printStackTrace();
		}
		
		return result;
	}


	/*파일업로드 */

	//이미지 저장
	@Override
	public int insertImg(SdboardDto dto) {
	     int res = 0; 
	     res = sqlSession.insert(NAMESPACE+"insertImg",dto);
	     
	     return res;
	}
	//글 번호 하나 조회
	@Override
	public int getBoardNo(SdboardDto dto) {
	     int sdbseq = 0; 
	     
	     try {
			sdbseq = sqlSession.selectOne(NAMESPACE+"getBoardNo",dto);
		} catch (Exception e) {
			logger.info("[DaoImpl]____getBoardNo 실패 >>>"+ e);
			e.printStackTrace();
		}
	     					//mybatis가 int를 String으로 자동형변환해주기 때문에 가능합니다.
	     return sdbseq;
	}
	//이미지 x 글작성
	@Override
	public int insertBoardNoImg(SdboardDto dto) {
	      logger.info("[DaoImpl]____이미지 없는 글작성 dto>>>>" + dto);
	      int res =0 ; 
	      
	      res = sqlSession.insert(NAMESPACE+"insertBoardNoImg",dto);
	      
	      
	      return res;

	}
	//이미지 o 글수정
	@Override
	public int updateBoardYesImg(SdboardDto dto) {
	    int res = 0; 
	    res = sqlSession.update(NAMESPACE+"updateBoardYesImg",dto);
	    return res;

	}


	
	/* INSERT / UPDATE / DELETE */
	


	
	   @Override
	   public int insert(SdboardDto dto) {
	      
	      int res = 0; 
	      res = sqlSession.insert(NAMESPACE+"insertwrite", dto);
	      
	      return 0;
	   }

	   @Override
	   public int update(SdboardDto dto) {
	      
	      int res = 0;
	      res = sqlSession.update(NAMESPACE+"updatewrite",dto);
	      return res;
	   }

	   @Override
	   public int delete(int sdbseq) {
	      int res= 0; 
	      res = sqlSession.delete(NAMESPACE+"deletewrite", sdbseq);
	      
	      return res;
	   }



	/* comment */
	
	@Override
	public List<SdreplyDto> replySelectAll(SdreplyDto dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int replyInsert(SdreplyDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replyUpdate(SdreplyDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int replyDelete(SdreplyDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}









}
