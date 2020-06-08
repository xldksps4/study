package com.update.semi.sdboard.dao;

import java.util.List;

import com.update.semi.sdboard.dto.SdboardDto;
import com.update.semi.sdboard.dto.SdreplyDto;

public interface SdboardDao {
	
	String NAMESPACE = "sdboard.";
	
	/* SELECT */
	
	//글 전체 보기
	public List<SdboardDto> selectAll(SdboardDto dto);
	//글 상위폴더만 보기
	public List<SdboardDto> selectFirst(SdboardDto dto); 
	//글 차상위폴더만 보기
	public List<SdboardDto> selectSecond(SdboardDto dto);
	//글 하위폴더만 보기
	public List<SdboardDto> selectThird(SdboardDto dto);
	//글 하나만 보기
	public SdboardDto selectDetail(SdboardDto dto);
	//글 즐겨찾기 보기

	
	/*페이징*/
	
	//전체 게시물 개수 가져오기
	public int getTotalBoard(SdboardDto dto);
	//페이징된 전체 게시물 가져오기
	public List<SdboardDto> boardList(SdboardDto dto);
		
	
	/* 파일업로드 글작성 */
	
	//파일업로드, 이미지 넣기
	public int insertImg(SdboardDto dto);
	//글 번호 하나 가져오기
	public int getBoardNo(SdboardDto dto);
	//DB에 글 추가(이미지 x)
	public int insertBoardNoImg(SdboardDto dto);
	//DB에 글 수정(이미지o이기 때문에 수정인거)
	public int updateBoardYesImg(SdboardDto dto);
	
	/* INSERT / UPDATE / DELETE */
	
	//글 작성
	public int insert(SdboardDto dto);
	//글 수정
	public int update(SdboardDto dto);
	//글 삭제
	public int delete(int sdbseq);
	
	//폴더 채로 삭제
	
	
	/* comment */
	
	//댓글 리스트
	public List<SdreplyDto> replySelectAll(SdreplyDto dto);
	//댓글 작성
	public int replyInsert(SdreplyDto dto);
	//댓글 수정
	public int replyUpdate(SdreplyDto dto);
	//댓글 삭제
	public int replyDelete(SdreplyDto dto);
}
