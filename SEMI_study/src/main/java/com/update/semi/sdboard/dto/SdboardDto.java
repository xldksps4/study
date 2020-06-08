package com.update.semi.sdboard.dto;

import java.io.File;
import java.util.Arrays;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

//게시판
public class SdboardDto {
//  --글시퀀스(pk) / 탭 번호 / 그룹시퀀스 / 그룹 번호 / 상위폴더 / 차상위폴더 / 하위폴더 / 중요도
//	--글 제목 / 작성자(fk) / 내용 / 최종작성일 / 조회수  / 자주보는 파일 / 파일 이름 / 파일 경로 

	//글 시퀀스(pk)
	private int sdbseq;
	
	//탭 번호
	private int sdbtabno;
	
	//그룹 시퀀스
	private int sdbgroupseq;
	
	//그룹 번호
	private int sdbgroupno;
	
	//상위 폴더
	private String sdbfirstfolder;
	
	//차상위 폴더
	private String sdbsecondfolder;
	
	//하위 폴더
	private String sdbthirdfolder;
	
	//중요도(관심도)
	private int sdbimportance;
	
	//글 제목
	@NotEmpty(message = "제목을 입력하세요")
	private String sdbtitle;
	
	//글 작성자
	private String sduemail;
	
	//글 내용
	private String sdbcontent;
	
	//최종 작성일
	private String sdbregdate;
	
	//조회수
	private String sdbviews;
	
	//자주보는 파일(좋아요)
	private String sdblike;	
	
	//썸네일 경로
	private String sdbthumbnail;
	
	//이미지 파일 경로
	private String sdbimgpath;
	
	//파일 이름
	private String sdbfilename;
	
	//파일 경로
	private String sdbfilepath;
	
	//파일업로드 시 실제 파일 담음
	private MultipartFile[] file;

	//게시물 시작페이지
	private int startBoardNo;
	
	//게시물 끝페이지
	private int endBoardNo;
	
	//검색-검색영역
	private String category;
	//검색-키워드
	private String keyword;
	
	
	/* Contructer */
	
	public SdboardDto() {	
	}
	
	public SdboardDto(int sdbseq, int sdbtabno, int sdbgroupseq, int sdbgroupno, String sdbfirstfolder,
			String sdbsecondfolder, String sdbthirdfolder, int sdbimportance, String sdbtitle, String sduemail,
			String sdbcontent, String sdbregdate, String sdbviews, String sdblike, String sdbthumbnail,
			String sdbimgpath, String sdbfilename, String sdbfilepath, MultipartFile[] file, int startBoardNo,
			int endBoardNo, String category, String keyword) {
		super();
		this.sdbseq = sdbseq;
		this.sdbtabno = sdbtabno;
		this.sdbgroupseq = sdbgroupseq;
		this.sdbgroupno = sdbgroupno;
		this.sdbfirstfolder = sdbfirstfolder;
		this.sdbsecondfolder = sdbsecondfolder;
		this.sdbthirdfolder = sdbthirdfolder;
		this.sdbimportance = sdbimportance;
		this.sdbtitle = sdbtitle;
		this.sduemail = sduemail;
		this.sdbcontent = sdbcontent;
		this.sdbregdate = sdbregdate;
		this.sdbviews = sdbviews;
		this.sdblike = sdblike;
		this.sdbthumbnail = sdbthumbnail;
		this.sdbimgpath = sdbimgpath;
		this.sdbfilename = sdbfilename;
		this.sdbfilepath = sdbfilepath;
		this.file = file;
		this.startBoardNo = startBoardNo;
		this.endBoardNo = endBoardNo;
		this.category = category;
		this.keyword = keyword;
	}





	/* Getter Setter */

	public int getSdbseq() {
		return sdbseq;
	}

	public void setSdbseq(int sdbseq) {
		this.sdbseq = sdbseq;
	}

	public int getSdbtabno() {
		return sdbtabno;
	}

	public void setSdbtabno(int sdbtabno) {
		this.sdbtabno = sdbtabno;
	}

	public int getSdbgroupseq() {
		return sdbgroupseq;
	}

	public void setSdbgroupseq(int sdbgroupseq) {
		this.sdbgroupseq = sdbgroupseq;
	}

	public int getSdbgroupno() {
		return sdbgroupno;
	}

	public void setSdbgroupno(int sdbgroupno) {
		this.sdbgroupno = sdbgroupno;
	}

	public String getSdbfirstfolder() {
		return sdbfirstfolder;
	}

	public void setSdbfirstfolder(String sdbfirstfolder) {
		this.sdbfirstfolder = sdbfirstfolder;
	}

	public String getSdbsecondfolder() {
		return sdbsecondfolder;
	}

	public void setSdbsecondfolder(String sdbsecondfolder) {
		this.sdbsecondfolder = sdbsecondfolder;
	}

	public String getSdbthirdfolder() {
		return sdbthirdfolder;
	}

	public void setSdbthirdfolder(String sdbthirdfolder) {
		this.sdbthirdfolder = sdbthirdfolder;
	}
	
	public int getSdbimportance() {
		return sdbimportance;
	}

	public void setSdbimportance(int sdbimportance) {
		this.sdbimportance = sdbimportance;
	}

	public String getSdbtitle() {
		return sdbtitle;
	}

	public void setSdbtitle(String sdbtitle) {
		this.sdbtitle = sdbtitle;
	}

	public String getSduemail() {
		return sduemail;
	}

	public void setSduemail(String sduemail) {
		this.sduemail = sduemail;
	}

	public String getSdbcontent() {
		return sdbcontent;
	}

	public void setSdbcontent(String sdbcontent) {
		this.sdbcontent = sdbcontent;
	}

	public String getSdbregdate() {
		return sdbregdate;
	}

	public void setSdbregdate(String sdbregdate) {
		this.sdbregdate = sdbregdate;
	}

	public String getSdbviews() {
		return sdbviews;
	}

	public void setSdbviews(String sdbviews) {
		this.sdbviews = sdbviews;
	}

	public String getSdblike() {
		return sdblike;
	}

	public void setSdblike(String sdblike) {
		this.sdblike = sdblike;
	}
	
	public String getSdbthumbnail() {
		return sdbthumbnail;
	}

	public void setSdbthumbnail(String sdbthumbnail) {
		this.sdbthumbnail = sdbthumbnail;
	}

	public String getSdbimgpath() {
		return sdbimgpath;
	}

	public void setSdbimgpath(String sdbimgpath) {
		this.sdbimgpath = sdbimgpath;
	}

	public String getSdbfilename() {
		return sdbfilename;
	}

	public void setSdbfilename(String sdbfilename) {
		this.sdbfilename = sdbfilename;
	}

	public String getSdbfilepath() {
		return sdbfilepath;
	}

	public void setSdbfilepath(String sdbfilepath) {
		this.sdbfilepath = sdbfilepath;
	}
	
	public MultipartFile[] getFile() {
		return file;
	}

	public void setFile(MultipartFile[] file) {
		this.file = file;
	}

	public int getStartBoardNo() {
		return startBoardNo;
	}

	public void setStartBoardNo(int startBoardNo) {
		this.startBoardNo = startBoardNo;
	}

	public int getEndBoardNo() {
		return endBoardNo;
	}

	public void setEndBoardNo(int endBoardNo) {
		this.endBoardNo = endBoardNo;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	/* toString */
	
	@Override
	public String toString() {
		return "SdboardDto [sdbseq=" + sdbseq + ", sdbtabno=" + sdbtabno + ", sdbgroupseq=" + sdbgroupseq
				+ ", sdbgroupno=" + sdbgroupno + ", sdbfirstfolder=" + sdbfirstfolder + ", sdbsecondfolder="
				+ sdbsecondfolder + ", sdbthirdfolder=" + sdbthirdfolder + ", sdbimportance=" + sdbimportance
				+ ", sdbtitle=" + sdbtitle + ", sduemail=" + sduemail + ", sdbcontent=" + sdbcontent + ", sdbregdate="
				+ sdbregdate + ", sdbviews=" + sdbviews + ", sdblike=" + sdblike + ", sdbthumbnail=" + sdbthumbnail
				+ ", sdbimgpath=" + sdbimgpath + ", sdbfilename=" + sdbfilename + ", sdbfilepath=" + sdbfilepath
				+ ", file=" + Arrays.toString(file) + ", startBoardNo=" + startBoardNo + ", endBoardNo=" + endBoardNo
				+ ", category=" + category + ", keyword=" + keyword + "]";
	}




}
