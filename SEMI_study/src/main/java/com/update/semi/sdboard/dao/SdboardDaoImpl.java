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
		logger.info("[DaoImpl]____selectAll, dto >>> : " + dto);
		List<SdboardDto> list = new ArrayList<SdboardDto>();
		try {	
			list = sqlSession.selectList(NAMESPACE+"selectAll", dto);
			logger.info("[DaoImpl]__selectAll결과 >>> list : " + list);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectAll__fail ");
			e.printStackTrace();
		}
		
		return list;
	}

	//글 상위폴더 조회
	@Override
	public List<SdboardDto> selectFirst(SdboardDto dto) {
		logger.info("[DaoImpl]____selectFirst, dto >>> : " + dto);
		List<SdboardDto> list = new ArrayList<SdboardDto>();
		try {
			list = sqlSession.selectList(NAMESPACE+"selectFirst", dto);
			logger.info("[DaoImpl]__selectFirst결과 >>> list : " + list);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectFirst__fail ");
			e.printStackTrace();
		}
		return list;
	}
	
	//글 차상위폴더 조회
	@Override
	public List<SdboardDto> selectSecond(SdboardDto dto) {
		logger.info("[DaoImpl]____selectSecond, dto >>> : " + dto);
		List<SdboardDto> list = new ArrayList<SdboardDto>();
		try {
			list = sqlSession.selectList(NAMESPACE+"selectSecond", dto);
			logger.info("[DaoImpl]__selectSecond결과 >>> list : " + list);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectSecond__fail ");
			e.printStackTrace();
		}
		return list;
	}

	//글 하위폴더 조회	
	@Override
	public List<SdboardDto> selectThird(SdboardDto dto) {
		logger.info("[DaoImpl]____selectThird, dto >>> : " + dto);
		List<SdboardDto> list = new ArrayList<SdboardDto>();
		try {
			list = sqlSession.selectList(NAMESPACE+"selectThird", dto);
			logger.info("[DaoImpl]__selectThird결과 >>> list : " + list);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectThird__fail ");
			e.printStackTrace();
		}
		
		return list;
	}

	//글 상세 조회	
	@Override
	public SdboardDto selectDetail(SdboardDto dto) {
		logger.info("[DaoImpl]____selectDetail, dto >>> : " + dto);
		SdboardDto result = null;
		try {
			result = sqlSession.selectOne(NAMESPACE+"selectDetail", dto);
			logger.info("[DaoImpl]__selectDetail결과 >>> result : " + result);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectDetail__fail >>> "+result);
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public SdboardDto selectOne(int sdbseq) {
		logger.info("[DaoImpl]____selectOne 전, sdbseq >>> : " + sdbseq);
		SdboardDto result;
		result = sqlSession.selectOne(NAMESPACE+"selectOne", sdbseq);
		logger.info("[DaoImpl]____selectOne 결과, sdbseq >>> : " + sdbseq);
		
		return result;
	}


	/* 페이징 */
	
	//전체 게시물 개수 가져오기
	@Override
	public int getTotalBoard(SdboardDto dto) {
		logger.info("[DaoImpl]____getTotalBoard, dto >>> : " + dto);
		
		int res = 0;
		res = sqlSession.selectOne(NAMESPACE+"getTotalBoard");
		logger.info("[DaoImpl]__getTotalBoard결과 >>> res : " + res);
		
		return res;
	}
	
	//페이징된 전체 게시물 가져오기
	@Override
	public List<SdboardDto> boardList(SdboardDto dto) {
		logger.info("[DaoImpl]____boardList, dto >>> : " + dto);
		
		List<SdboardDto> list = new ArrayList<SdboardDto>();
		list = sqlSession.selectList(NAMESPACE+"boardList",dto);
		logger.info("[DaoImpl]__boardList결과 >>> list : " + list);
		
		return list;
	}




	/*파일업로드 */

	//이미지 저장
	@Override
	public int insertImg(SdboardDto dto) {
		logger.info("[DaoImpl]____insertImg, dto >>> : " + dto);
		int res = 0; 
	    res = sqlSession.insert(NAMESPACE+"insertImg",dto);
	    logger.info("[DaoImpl]__insertImg결과 >>> res : " + res);
	    return res;
	}
	//글 번호 하나 조회
	@Override
	public int getBoardNo(SdboardDto dto) {
		logger.info("[DaoImpl]____getBoardNo, dto >>> : " + dto); 
		int sdbseq = 0; 
	     
	     try {
			sdbseq = sqlSession.selectOne(NAMESPACE+"getBoardNo",dto);
			logger.info("[DaoImpl]__getBoardNo결과 >>> sdbseq : " + sdbseq);
		} catch (Exception e) {
			logger.info("[Error] : [DaoImpl]__selectOne__fail >>> "+sdbseq);
			e.printStackTrace();
		}
	     					//mybatis가 int를 String으로 자동형변환해주기 때문에 가능합니다.
	     return sdbseq;
	}
	//이미지 x 글작성
	@Override
	public int insertBoardNoImg(SdboardDto dto) {
		logger.info("[DaoImpl]____insertBoardNoImg, dto >>> : " + dto);
	    int res = 0; 
	    res = sqlSession.insert(NAMESPACE+"insertBoardNoImg",dto);
	    logger.info("[DaoImpl]__insertBoardNoImg결과 >>> res : " + res);
	      
	    return res;

	}
	//이미지 o 글수정
	@Override
	public int updateBoardYesImg(SdboardDto dto) {
		logger.info("[DaoImpl]____updateBoardYesImg, dto >>> : " + dto);
		int res = 0; 
	    res = sqlSession.update(NAMESPACE+"updateBoardYesImg",dto);
	    logger.info("[DaoImpl]__updateBoardYesImg결과 >>> res : " + res);
	    return res;

	}
	//이미지 수정
	@Override
	public int updateImg(SdboardDto dto) {
		logger.info("[DaoImpl]____updateImg, dto >>> : " + dto);
		int res = 0;
		res = sqlSession.update(NAMESPACE+"updateImg", dto);
		logger.info("[DaoImpl]____updateImg, res >>> : " + res);
		
		return res;
	}

	// content 안에 img태그가 없을 경우 >>> DB 이미지 썸내일 칼럼 삭제
	@Override
	public int updateNoImgBoard(SdboardDto dto) {
		logger.info("[DaoImpl]____updateNoImgBoard, dto >>> : " + dto);
		int res = 0;
		res = sqlSession.update(NAMESPACE+"updateNoImgBoard", dto);
		logger.info("[DaoImpl]____updateNoImgBoard, res >>> : " + res);
		
		return res;
	}



	/* INSERT / UPDATE / DELETE */
	
	@Override
	public int insert(SdboardDto dto) {
		logger.info("[DaoImpl]____insert, dto >>> : " + dto);
		int res = 0; 
		res = sqlSession.insert(NAMESPACE+"insertwrite", dto);
		logger.info("[DaoImpl]__updateBoardYesImg결과 >>> res : " + res);  
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
		logger.info("[DaoImpl]____replySelectAll, dto >>> : " + dto);
		return null;
	}
	
	@Override
	public int replyInsert(SdreplyDto dto) {
		logger.info("[DaoImpl]____replyInsert, dto >>> : " + dto);
		return 0;
	}

	@Override
	public int replyUpdate(SdreplyDto dto) {
		logger.info("[DaoImpl]____replyUpdate, dto >>> : " + dto);
		return 0;
	}

	@Override
	public int replyDelete(SdreplyDto dto) {
		logger.info("[DaoImpl]____replyDelete, dto >>> : " + dto);
		return 0;
	}










}
