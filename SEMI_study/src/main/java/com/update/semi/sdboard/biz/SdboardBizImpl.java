package com.update.semi.sdboard.biz;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.update.semi.sdboard.dao.SdboardDao;
import com.update.semi.sdboard.dto.SdboardDto;
import com.update.semi.sdboard.dto.SdreplyDto;

@Service
public class SdboardBizImpl implements SdboardBiz {
	
	private Logger logger = LoggerFactory.getLogger(SdboardBizImpl.class); 
	
	@Autowired
	private SdboardDao dao;

	/* SELECT */
	
	@Override
	public List<SdboardDto> selectAll(SdboardDto dto) {
		logger.info("[BizImpl]____게시판 전체 리스트 ");
		return dao.selectAll(dto);
	}

	@Override
	public List<SdboardDto> selectFirst(SdboardDto dto) {
		// TODO Auto-generated method stub
		return dao.selectFirst(dto);
	}

	@Override
	public List<SdboardDto> selectSecond(SdboardDto dto) {
		// TODO Auto-generated method stub
		return dao.selectSecond(dto);
	}

	@Override
	public List<SdboardDto> selectThird(SdboardDto dto) {
		// TODO Auto-generated method stub
		return dao.selectThird(dto);
	}

	
	
	@Override
	public SdboardDto selectDetail(SdboardDto dto) {
		// TODO Auto-generated method stub
		return dao.selectDetail(dto);
	}

	@Override
	public SdboardDto selectOne(int sdbseq) {
		// TODO Auto-generated method stub
		return dao.selectOne(sdbseq);
	}
	
	/*페이징*/
	
	//전체 게시물 개수 가져오기
	@Override
	public int getTotalBoard(SdboardDto dto) {
		// TODO Auto-generated method stub
		return dao.getTotalBoard(dto);
	}
	//페이징된 전체 게시물 가져오기
	@Override
	public List<SdboardDto> boardList(SdboardDto dto) {
		// TODO Auto-generated method stub
		return dao.boardList(dto);
	}


	/*파일업로드 글작성*/
	
	@Override
	public int insertImg(SdboardDto dto) {
		// TODO Auto-generated method stub
		return dao.insertImg(dto);
	}
	
	@Override
	public String getBoardNo(SdboardDto dto) {
		logger.info("[BizImpl]____getBoardNo 시작, dto >>>"+dto);
	//누나 성공방법
		int boardNo = dao.getBoardNo(dto);
		String sdbseq = Integer.toString(boardNo);
		logger.info("[BizImpl]__sdbseq getBoardNo >>>"+sdbseq);
		return sdbseq;
	}
	
	@Override
	public int insertBoardNoImg(SdboardDto dto) {
		logger.info("[BizImpl]____이미지 없는 글작성 BizImpl ");
		return dao.insertBoardNoImg(dto);
	}

	@Override
	public int updateBoardYesImg(SdboardDto dto) {
		// TODO Auto-generated method stub
		return dao.updateBoardYesImg(dto);
	}
	
	@Override
	public int updateImg(SdboardDto dto) {
		// TODO Auto-generated method stub
		return dao.updateImg(dto);
	}
	
	@Override
	public int updateNoImgBoard(SdboardDto dto) {
		// TODO Auto-generated method stub
		return dao.updateNoImgBoard(dto);
	}


	
	/* INSERT / UPDATE / DELETE */
	

	
	  @Override
	   public int insert(SdboardDto dto) {
	      return dao.insert(dto);
	   }

	   @Override
	   public int update(SdboardDto dto) {
	      return dao.update(dto);
	   }

	   @Override
	   public int delete(int sdbseq) {
	      return dao.delete(sdbseq);
	   }

	   
	/* comment */
	
	@Override
	public List<SdreplyDto> replySelectAll(SdreplyDto dto) {
		// TODO Auto-generated method stub
		return dao.replySelectAll(dto);
	}

	@Override
	public int replyInsert(SdreplyDto dto) {
		// TODO Auto-generated method stub
		return dao.replyInsert(dto);
	}

	@Override
	public int replyUpdate(SdreplyDto dto) {
		// TODO Auto-generated method stub
		return dao.replyUpdate(dto);
	}

	@Override
	public int replyDelete(SdreplyDto dto) {
		// TODO Auto-generated method stub
		return dao.replyDelete(dto);
	}










	
}
