package com.update.semi.sduser.dto;

import java.io.File;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

//회원 
public class SduserDto {

	// 유저시퀀스
	private int sduseq;

	// 아이디
	@Email
	private String sduemail;

	// 비번
	@Pattern(regexp="^.*(?=.{8,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$", message="비밀번호가 틀렸습니다.")
	private String sdupw;

	// 이름
	@Pattern(regexp = "^[가-힣]{2,6}$")
	private String sduname;

	// 성별
	private String sdusex;

	// 닉네임
	@NotEmpty(message="닉네임을 입력하세요")
	private String sdunick;

	// 생년월일
	private String sdudob;

	// 탈퇴여부
	private String sdudeact;

	// 회원등급
	private String sdugrade;

	// 탈퇴날짜
	private Date sdudeactdate;

	// 가입날짜
	private Date sduregdate;
	
	// 프로필 이미지 경로
	private String sduimgpath;

	//파일업로드
	private MultipartFile sdufileupload;
	
	/* Contructer */
	
	public SduserDto() {
		
	}
	
	public SduserDto(int sduseq, String sduemail, String sdupw, String sduname, String sdusex, String sdunick,
			String sdudob, String sdudeact, String sdugrade, Date sdudeactdate, Date sduregdate, String sduimgpath) {
		super();
		this.sduseq = sduseq;
		this.sduemail = sduemail;
		this.sdupw = sdupw;
		this.sduname = sduname;
		this.sdusex = sdusex;
		this.sdunick = sdunick;
		this.sdudob = sdudob;
		this.sdudeact = sdudeact;
		this.sdugrade = sdugrade;
		this.sdudeactdate = sdudeactdate;
		this.sduregdate = sduregdate;
		this.sduimgpath = sduimgpath;
	}

	/* Getter Setter */

	public int getSduseq() {
		return sduseq;
	}

	public void setSduseq(int sduseq) {
		this.sduseq = sduseq;
	}

	public String getSduemail() {
		return sduemail;
	}

	public void setSduemail(String sduemail) {
		this.sduemail = sduemail;
	}

	public String getSdupw() {
		return sdupw;
	}

	public void setSdupw(String sdupw) {
		this.sdupw = sdupw;
	}

	public String getSduname() {
		return sduname;
	}

	public void setSduname(String sduname) {
		this.sduname = sduname;
	}

	public String getSdusex() {
		return sdusex;
	}

	public void setSdusex(String sdusex) {
		this.sdusex = sdusex;
	}

	public String getSdunick() {
		return sdunick;
	}

	public void setSdunick(String sdunick) {
		this.sdunick = sdunick;
	}

	public String getSdudob() {
		return sdudob;
	}

	public void setSdudob(String sdudob) {
		this.sdudob = sdudob;
	}

	public String getSdudeact() {
		return sdudeact;
	}

	public void setSdudeact(String sdudeact) {
		this.sdudeact = sdudeact;
	}

	public String getSdugrade() {
		return sdugrade;
	}

	public void setSdugrade(String sdugrade) {
		this.sdugrade = sdugrade;
	}

	public Date getSdudeactdate() {
		return sdudeactdate;
	}

	public void setSdudeactdate(Date sdudeactdate) {
		this.sdudeactdate = sdudeactdate;
	}

	public Date getSduregdate() {
		return sduregdate;
	}

	public void setSduregdate(Date sduregdate) {
		this.sduregdate = sduregdate;
	}
	
	public String getSduimgpath() {
		return sduimgpath;
	}

	public void setSduimgpath(String sduimgpath) {
		this.sduimgpath = sduimgpath;
	}

	public MultipartFile getSdufileupload() {
		return sdufileupload;
	}

	public void setSdufileupload(MultipartFile sdufileupload) {
		this.sdufileupload = sdufileupload;
	}



	
	/* toString */	

	@Override
	public String toString() {
		return "SduserDto [sduseq=" + sduseq + ", sduemail=" + sduemail + ", sdupw=" + sdupw + ", sduname=" + sduname
				+ ", sdusex=" + sdusex + ", sdunick=" + sdunick + ", sdudob=" + sdudob + ", sdudeact=" + sdudeact
				+ ", sdugrade=" + sdugrade + ", sdudeactdate=" + sdudeactdate + ", sduregdate=" + sduregdate
				+ ", sduimgpath=" + sduimgpath + ", sdufileupload=" + sdufileupload + "]";
	}

}
